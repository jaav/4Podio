package be.virtualsushi.podio.demo.service.podio.mappers;

import java.util.List;

import be.virtualsushi.podio.demo.dto.item.Item;
import be.virtualsushi.podio.demo.dto.item.ItemCreate;
import be.virtualsushi.podio.demo.dto.item.ItemUpdate;

public interface PodioItemMapper<T> {

	public T map(Item item);

	public ItemCreate mapToCreate(T object);

	public ItemUpdate mapToUpdate(T object);

	public List<String> mapTags(T object);

}
