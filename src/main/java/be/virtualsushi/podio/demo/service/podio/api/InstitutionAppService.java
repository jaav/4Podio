package be.virtualsushi.podio.demo.service.podio.api;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import be.virtualsushi.podio.demo.dto.item.AppField;
import be.virtualsushi.podio.demo.dto.item.CategoryOption;
import be.virtualsushi.podio.demo.dto.item.Institution;
import be.virtualsushi.podio.demo.service.cache.ProxyCacheKey;
import be.virtualsushi.podio.demo.service.podio.mappers.InstitutionItemMapper;
import be.virtualsushi.podio.demo.service.podio.mappers.PodioItemMapper;

@Service
public class InstitutionAppService extends AbstractAppService<Institution> {

	@Inject
	private InstitutionItemMapper mapper;

	@Value("${podio.virtualsushi.institution.id}")
	private Integer appId;

	@Value("${podio.virtualsushi.institution.category.id}")
	private Integer categoryFieldId;

	@Override
	protected Integer getAppId() {
		return appId;
	}

	@Override
	protected Class<Institution> getItemClass() {
		return Institution.class;
	}

	@Override
	protected PodioItemMapper<Institution> getMapper() {
		return mapper;
	}

	@SuppressWarnings("unchecked")
	public List<CategoryOption> getCategories() {
		List<CategoryOption> result = getCacheService().getFromCache(new ProxyCacheKey("institution", "categories"), List.class);
		if (result == null) {
			result = getPodioService().get("/app/{appId}/field/{fieldId}", AppField.class, getAppId(), categoryFieldId).getConfiguration().getSettings().getOptions();
			getCacheService().putToCache(new ProxyCacheKey("institution", "categories"), result);
		}
		return result;
	}

}
