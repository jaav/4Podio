package be.virtualsushi.podio.demo.service.cache;

public class FileCacheKey extends SessionDependedCacheKey {

	private static final long serialVersionUID = -4388126092665343117L;

	public FileCacheKey(String key) {
		super("file", key);
	}

}
