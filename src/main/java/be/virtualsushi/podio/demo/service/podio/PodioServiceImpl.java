package be.virtualsushi.podio.demo.service.podio;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import be.virtualsushi.podio.demo.dto.file.FileInfo;
import be.virtualsushi.podio.demo.dto.file.FileLink;
import be.virtualsushi.podio.demo.dto.file.FileProxy;
import be.virtualsushi.podio.demo.security.PodioUserDetails;
import be.virtualsushi.podio.demo.service.FileBytesResource;
import be.virtualsushi.podio.demo.service.cache.CacheService;
import be.virtualsushi.podio.demo.service.cache.FileCacheKey;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;
import com.podio.oauth.OAuthClientCredentials;
import com.podio.oauth.OAuthUserCredentials;
import com.sun.jersey.api.client.WebResource;

@Service("podioService")
public class PodioServiceImpl implements PodioService {

	private static final String PODIO_BASE_URL = "https://api.podio.com";

	private final Logger log = LoggerFactory.getLogger(PodioService.class);

	@Inject
	private OAuthService oauthService;

	@Inject
	private OAuthClientCredentials clientCredentials;

	@Inject
	private RestTemplate restTemplate;

	@Inject
	private CacheService cacheService;

	@Inject
	private Environment environment;

	@Value("${podio.direct.space.id}")
	private int spaceId;

	private ResourceFactory resourceFactory;

	@Override
	public PodioUserDetails doPodioLogin(OAuthUserCredentials userCredentials) throws BadCredentialsException {
		return null;
	}

	@Override
	public <T extends BaseAPI> T getAPI(Class<T> apiClass) {
		return getAPI(apiClass, resourceFactory);
	}

	private <T extends BaseAPI> T getAPI(Class<T> apiClass, ResourceFactory resourceFactory) {
		try {
			return apiClass.getConstructor(ResourceFactory.class).newInstance(resourceFactory);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	private ResourceFactory createResourceFactory(OAuthTokenProvider tokenProvider) throws ReflectiveOperationException, RuntimeException {
		ResourceFactory result = new ResourceFactory(clientCredentials, null);
		Field loginFilterField = ResourceFactory.class.getDeclaredField("loginFilter");
		ReflectionUtils.setField(loginFilterField, result, new LoginFilter(tokenProvider));
		return result;
	}

	@Override
	public WebResource getCustomApiResource(String path) {
		return resourceFactory.getApiResource(path);
	}

	@Override
	public <T> T post(String path, Object request, Class<T> responseType, Object... uriVariables) {
		try {
			return restTemplate.postForObject(PODIO_BASE_URL + path, request, responseType, uriVariables);
		} catch (RestClientException e) {
			handleRestClientError(e);
			throw e;
		}
	}

	private void handleRestClientError(RestClientException e) {
		if (e instanceof HttpStatusCodeException) {
			log.error("Podio error:" + ((HttpStatusCodeException) e).getResponseBodyAsString());
		} else {
			log.error("Non podio error while executing Rest request.");
		}
	}

	@Override
	public <T> T get(String path, Class<T> responseType, Object... uriVariables) {
		try {
			return restTemplate.getForObject(PODIO_BASE_URL + path, responseType, uriVariables);
		} catch (RestClientException e) {
			handleRestClientError(e);
			throw e;
		}
	}

	@Override
	public void delete(String path, Object... uriVariables) {
		try {
			restTemplate.exchange(PODIO_BASE_URL + path, HttpMethod.DELETE, null, Void.class, uriVariables);
		} catch (RestClientException e) {
			handleRestClientError(e);
			throw e;
		}
	}

	@Override
	public void put(String path, Object request, Object... uriVariables) {
		try {
			restTemplate.put(PODIO_BASE_URL + path, request, uriVariables);
		} catch (RestClientException e) {
			handleRestClientError(e);
			throw e;
		}
	}

	@Override
	public FileLink uploadFile(FileInfo info) {
		FileCacheKey key = new FileCacheKey(info.getCacheKey());
		FileLink result = uploadFile(cacheService.getFromCache(key, FileProxy.class).getContent(), info.getContentType(), info.getName());
		cacheService.removeFromCache(key);
		return result;
	}

	@Override
	public FileLink uploadFile(byte[] bytes, String contentType, String name) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", contentType);
		HttpEntity<FileBytesResource> sourceEntity = new HttpEntity<FileBytesResource>(new FileBytesResource(name, bytes), headers);

		MultiValueMap<String, Object> multipart = new LinkedMultiValueMap<String, Object>();
		multipart.add("source", sourceEntity);
		multipart.add("filename", name);

		return post("/file/", multipart, FileLink.class);
	}

	@Override
	public <T> T get(String path, Class<T> responseType, Map<String, ?> params) {
		StringBuilder urlString = new StringBuilder(PODIO_BASE_URL).append(path).append("?");
		for (String param : params.keySet()) {
			String paramString = params.get(param).toString();
			if (StringUtils.isNotBlank(paramString)) {
				urlString.append(param).append("=").append(encodeParam(paramString)).append("&");
			}
		}
		try {
			return restTemplate.getForObject(URI.create(urlString.toString()), responseType);
		} catch (RestClientException e) {
			handleRestClientError(e);
			throw e;
		}
	}

	private String encodeParam(String param) {
		try {
			return URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return param;
		}
	}

	@Override
	public ResponseEntity<byte[]> downloadFile(Integer fileId) {
		return restTemplate.exchange(URI.create("https://files.podio.com/" + fileId), HttpMethod.GET, new HttpEntity<>(null), byte[].class);
	}

}
