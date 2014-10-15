package be.virtualsushi.podio.demo.service.podio.api;

import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import be.virtualsushi.podio.demo.Application;
import be.virtualsushi.podio.demo.dto.FilterRequest;
import be.virtualsushi.podio.demo.dto.OAuthToken;
import be.virtualsushi.podio.demo.dto.item.AppItem;
import be.virtualsushi.podio.demo.service.podio.OAuthService;
import be.virtualsushi.podio.demo.service.podio.PodioService;
import be.virtualsushi.podio.demo.service.podio.SessionTokenInterceptor;
import be.virtualsushi.podio.demo.service.podio.SessionTokenProvider;

import com.podio.oauth.OAuthUsernameCredentials;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("dev")
@WebAppConfiguration
public class AbstractApiServiceTest {

	@Mock
	public SessionTokenProvider tokenProvider;

	@Inject
	public OAuthService oauthService;

	@Inject
	public SessionTokenInterceptor oAuthHttpRequestInterceptor;

	@Inject
	protected PodioService podioService;

	@Value("${podio.demo.username}")
	private String username;

	@Value("${podio.demo.password}")
	private String password;

	@Value("${podio.demo.console}")
	private boolean readCredentials;

	protected OAuthToken token;

	@Before
	public void init() throws Exception {
		if (readCredentials) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Username:");
			username = scanner.next();
			System.out.print("Password:");
			password = scanner.next();
			scanner.close();
		}
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(oAuthHttpRequestInterceptor, "tokenProvider", tokenProvider);
		ReflectionTestUtils.setField(unwrapProxy(podioService), "tokenProvider", tokenProvider);
		token = oauthService.getToken(new OAuthUsernameCredentials(username, password));
		Mockito.when(tokenProvider.getToken()).thenReturn(token);
	}

	@SuppressWarnings("unchecked")
	protected <T> T unwrapProxy(T proxy) throws Exception {
		if (AopUtils.isAopProxy(proxy) && proxy instanceof Advised) {
			Object target = ((Advised) proxy).getTargetSource().getTarget();
			return (T) target;
		}
		return proxy;
	}

	protected Integer readConsoleInt(String text) {
		System.out.print(text + ":");
		Scanner scanner = new Scanner(System.in);
		Integer id = scanner.nextInt();
		scanner.close();
		return id;
	}

	protected <V extends AppItem, T extends AbstractAppService<V>> void runDelete(T service) {
		List<V> items = list(service, new FilterRequest());
		Integer number = readConsoleInt("Enter nunmber to delete");
		service.deleteItem(items.get(number - 1).getId());
		System.out.println("Done");
		list(service, new FilterRequest());
	}

	protected <V extends AppItem, T extends AbstractAppService<V>> List<V> list(T service, FilterRequest filter) {
		List<V> items = service.findItems(new FilterRequest(), true);
		System.out.println("=================");
		int index = 1;
		for (V item : items) {
			System.out.println(index + ": " + item);
			index++;
		}
		System.out.println("=================");
		return items;
	}

	protected int randomIndex() {
		return (int) Math.round((Math.random() * 10)) % 10;
	}
}
