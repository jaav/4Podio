package be.virtualsushi.podio.demo.service.cache;

import be.virtualsushi.podio.demo.security.SecurityUtils;

/**
 * Use only inside controllers.
 * 
 * @author spv
 * 
 */
public class SessionDependedCacheKey extends ProxyCacheKey {

	private static final long serialVersionUID = 2616518345022163879L;

	public SessionDependedCacheKey(Object... parts) {
		super(String.valueOf(SecurityUtils.getCurrenUser().getUserId()), parts);
	}

}
