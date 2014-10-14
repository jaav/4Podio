package be.virtualsushi.podio.demo.service.podio;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import be.virtualsushi.podio.demo.dto.file.FileInfo;
import be.virtualsushi.podio.demo.dto.file.FileLink;
import be.virtualsushi.podio.demo.security.PodioUserDetails;

import com.podio.BaseAPI;
import com.podio.oauth.OAuthUserCredentials;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by spv on 21.03.2014.
 */

public interface PodioService {

	public PodioUserDetails doPodioLogin(OAuthUserCredentials userCredentials) throws BadCredentialsException;

	public <T extends BaseAPI> T getAPI(Class<T> apiClass);

	public WebResource getCustomApiResource(String path);

	public <T> T post(String path, Object request, Class<T> responseType, Object... uriVariables);

	public <T> T get(String path, Class<T> responseType, Object... uriVariables);

	public <T> T get(String path, Class<T> responseType, Map<String, ?> params);

	public void delete(String path, Object... uriVariables);

	public void put(String path, Object request, Object... uriVariables);

	public FileLink uploadFile(FileInfo info);

	public FileLink uploadFile(byte[] bytes, String contentType, String name);

	public ResponseEntity<byte[]> downloadFile(Integer fileId);

}
