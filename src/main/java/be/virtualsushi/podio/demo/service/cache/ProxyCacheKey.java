package be.virtualsushi.podio.demo.service.cache;

import java.io.Serializable;
import java.util.Arrays;

import be.virtualsushi.podio.demo.config.Constants;

public class ProxyCacheKey implements Serializable {

	private static final long serialVersionUID = -1490527109415245317L;

	private final Object[] parts;

	private final String prefix;

	public ProxyCacheKey(String prefix, Object... parts) {
		this.parts = parts;
		this.prefix = prefix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(parts);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProxyCacheKey other = (ProxyCacheKey) obj;
		if (!Arrays.equals(parts, other.parts))
			return false;
		return true;
	}

	public String getCacheName() {
		return Constants.PROXY_CACHE_NAME + (prefix != null ? prefix : "");
	}

}
