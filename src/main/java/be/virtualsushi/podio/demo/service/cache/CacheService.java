package be.virtualsushi.podio.demo.service.cache;

import java.util.List;

import javax.inject.Inject;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

	@Inject
	private CacheManager cacheManager;

	@SuppressWarnings("unchecked")
	public <T> T getFromCache(ProxyCacheKey key, Class<T> type) {
		ValueWrapper wrappedValue = cacheManager.getCache(key.getCacheName()).get(key);
		if (wrappedValue == null) {
			return null;
		}
		Object value = wrappedValue.get();
		if (type != null && !type.isInstance(value)) {
			throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]: " + value);
		}
		return (T) value;
	}

	public void putToCache(ProxyCacheKey key, Object value) {
		cacheManager.getCache(key.getCacheName()).put(key, value);
	}

	public void removeFromCache(ProxyCacheKey key) {
		cacheManager.getCache(key.getCacheName()).evict(key);
	}

	public void clearRelatedCache(ProxyCacheKey key) {
		cacheManager.getCache(key.getCacheName()).clear();
	}

	@SuppressWarnings("unchecked")
	public <T> void updateCachedList(ProxyCacheKey key, T value) {
		List<T> cachedList = getFromCache(key, List.class);
		if (cachedList != null) {
			cachedList.remove(cachedList.indexOf(value));
			cachedList.add(value);
			putToCache(key, cachedList);
		}
	}

}
