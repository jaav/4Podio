package be.virtualsushi.podio.demo.service.podio.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import be.virtualsushi.podio.demo.dto.Filter;
import be.virtualsushi.podio.demo.dto.FilterRequest;
import be.virtualsushi.podio.demo.dto.item.AppItem;
import be.virtualsushi.podio.demo.dto.item.Item;
import be.virtualsushi.podio.demo.dto.item.ItemsResponse;
import be.virtualsushi.podio.demo.service.cache.CacheService;
import be.virtualsushi.podio.demo.service.cache.ItemCacheKey;
import be.virtualsushi.podio.demo.service.podio.PodioService;
import be.virtualsushi.podio.demo.service.podio.mappers.PodioItemMapper;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.podio.common.Reference;
import com.podio.common.ReferenceType;
import com.podio.tag.TagAPI;

public abstract class AbstractAppService<T extends AppItem> {

	private PodioService podioService;

	private CacheService cacheService;

	public void updateTags(T item, T originalItem) {
		if (needTagsUpdate(originalItem.getTags(), getMapper().mapTags(item))) {
			getPodioService().getAPI(TagAPI.class).updateTags(new Reference(ReferenceType.ITEM, originalItem.getId()), getMapper().mapTags(item));
		}
	}

	public void updateItem(T item) {
		T originalItem = getItem(item.getId());
		updateTags(item, originalItem);
		cacheService.putToCache(new ItemCacheKey(item.getId()), item);
		getPodioService().put("/item/{item_id}", getMapper().mapToUpdate(item), item.getId());
	}

	public void deleteItem(Integer id) {
		getPodioService().delete("/item/{item_id}?silent={silent}", id, true);
		cacheService.removeFromCache(new ItemCacheKey(id));
	}

	public T addItem(T item, boolean silent) {
		item.setId(getPodioService().post("/item/app/{app_id}/?silent={silent}", getMapper().mapToCreate(item), item.getClass(), getAppId(), silent).getId());
		return item;
	}

	public T getItem(Integer id) {
		T result = cacheService.getFromCache(new ItemCacheKey(id), getItemClass());
		if (result == null) {
			result = getMapper().map(podioService.get("/item/{id}", Item.class, id));
			cacheService.putToCache(new ItemCacheKey(result.getId()), result);
		}
		return result;
	}

	public List<T> findItems(FilterRequest request, final boolean full) {
		ItemsResponse response = getPodioService().post("/item/app/{app_id}/filter/", request, ItemsResponse.class, getAppId());
		return new ArrayList<>(Lists.transform(response.getItems(), new Function<Item, T>() {

			@Override
			public T apply(Item input) {
				if (full) {
					return getItem(input.getId());
				} else {
					return getMapper().map(input);
				}
			}
		}));
	}

	private boolean needTagsUpdate(List<String> originalTags, List<String> tags) {
		if (originalTags == tags) {
			return false;
		}
		if (originalTags.size() != tags.size()) {
			return true;
		}
		for (String tag : tags) {
			if (!originalTags.contains(tag)) {
				return true;
			}
		}
		return false;
	}

	public List<T> filterItems(List<T> object, Filter<T> filter) {
		List<T> result = new ArrayList<>();
		for (T event : object) {
			if (filter.accept(event)) {
				result.add(event);
			}
		}
		return result;
	}

	public PodioService getPodioService() {
		return podioService;
	}

	@Inject
	public void setPodioService(PodioService podioService) {
		this.podioService = podioService;
	}

	public CacheService getCacheService() {
		return cacheService;
	}

	@Inject
	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	protected abstract Integer getAppId();

	protected abstract Class<T> getItemClass();

	protected abstract PodioItemMapper<T> getMapper();

}
