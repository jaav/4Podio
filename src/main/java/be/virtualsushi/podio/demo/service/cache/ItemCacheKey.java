package be.virtualsushi.podio.demo.service.cache;

public class ItemCacheKey extends ProxyCacheKey {

	private static final long serialVersionUID = -7064790056169412830L;

	public ItemCacheKey(Integer id) {
		super("item", id);
	}

}
