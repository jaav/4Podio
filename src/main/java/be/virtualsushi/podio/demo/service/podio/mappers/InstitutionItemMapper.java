package be.virtualsushi.podio.demo.service.podio.mappers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import be.virtualsushi.podio.demo.dto.item.FieldValuesUpdate;
import be.virtualsushi.podio.demo.dto.item.FieldValuesView;
import be.virtualsushi.podio.demo.dto.item.Institution;
import be.virtualsushi.podio.demo.dto.item.Item;
import be.virtualsushi.podio.demo.dto.item.ItemCreate;
import be.virtualsushi.podio.demo.dto.item.ItemUpdate;

public class InstitutionItemMapper implements PodioItemMapper<Institution> {

	@Value("${podio.virtualsushi.institution.title.id}")
	private Integer titleFieldId;

	@Value("${podio.virtualsushi.institution.description.id}")
	private Integer descriptionFieldId;

	@Value("${podio.virtualsushi.institution.address.id}")
	private Integer addressFieldId;

	@Value("${podio.virtualsushi.institution.zipcode.id}")
	private Integer zipcodeFieldId;

	@Value("${podio.virtualsushi.institution.city.id}")
	private Integer cityFieldId;

	@Value("${podio.virtualsushi.institution.phone.id}")
	private Integer phoneFieldId;

	@Value("${podio.virtualsushi.institution.fax.id}")
	private Integer faxFieldId;

	@Value("${podio.virtualsushi.institution.email.id}")
	private Integer emailFieldId;

	@Value("${podio.virtualsushi.institution.website.id}")
	private Integer websiteFieldId;

	private StringFieldMapper stringFieldMapper = new StringFieldMapper();

	@Override
	public Institution map(Item podioItem) {
		Institution result = new Institution();
		result.setId(podioItem.getId());
		for (FieldValuesView field : podioItem.getFields()) {
			if (field.getId() == titleFieldId) {
				result.setTitle(stringFieldMapper.map(field));
			}
			if (field.getId() == descriptionFieldId) {
				result.setDescription(stringFieldMapper.map(field));
			}
			if (field.getId() == addressFieldId) {
				result.setAddress(stringFieldMapper.map(field));
			}
			if (field.getId() == zipcodeFieldId) {
				result.setZipcode(stringFieldMapper.map(field));
			}
			if (field.getId() == cityFieldId) {
				result.setCity(stringFieldMapper.map(field));
			}
			if (field.getId() == phoneFieldId) {
				result.setPhone(stringFieldMapper.map(field));
			}
			if (field.getId() == faxFieldId) {
				result.setFax(stringFieldMapper.map(field));
			}
			if (field.getId() == emailFieldId) {
				result.setEmail(stringFieldMapper.map(field));
			}
			if (field.getId() == websiteFieldId) {
				result.setWebsite(stringFieldMapper.map(field));
			}
		}
		if (StringUtils.isBlank(result.getTitle())) {
			result.setTitle(podioItem.getTitle());
		}
		result.setRights(podioItem.getRights());
		result.setTags(podioItem.getTags());
		return result;
	}

	@Override
	public ItemCreate mapToCreate(Institution institution) {
		ItemCreate result = new ItemCreate(createFieldsList(institution), null, mapTags(institution));
		return result;
	}

	@Override
	public ItemUpdate mapToUpdate(Institution institution) {
		ItemUpdate result = new ItemUpdate(createFieldsList(institution));
		return result;
	}

	private List<FieldValuesUpdate> createFieldsList(Institution institution) {
		List<FieldValuesUpdate> result = new ArrayList<>();
		if (StringUtils.isNotBlank(institution.getTitle())) {
			result.add(new FieldValuesUpdate(titleFieldId, stringFieldMapper.mapBack(institution.getTitle())));
		}
		if (StringUtils.isNotBlank(institution.getDescription())) {
			result.add(new FieldValuesUpdate(descriptionFieldId, stringFieldMapper.mapBack(institution.getDescription())));
		}
		if (StringUtils.isNotBlank(institution.getAddress())) {
			result.add(new FieldValuesUpdate(addressFieldId, stringFieldMapper.mapBack(institution.getAddress())));
		}
		if (StringUtils.isNotBlank(institution.getZipcode())) {
			result.add(new FieldValuesUpdate(zipcodeFieldId, stringFieldMapper.mapBack(institution.getZipcode())));
		}
		if (StringUtils.isNotBlank(institution.getCity())) {
			result.add(new FieldValuesUpdate(cityFieldId, stringFieldMapper.mapBack(institution.getCity())));
		}
		if (StringUtils.isNotBlank(institution.getPhone())) {
			result.add(new FieldValuesUpdate(phoneFieldId, stringFieldMapper.mapBack(institution.getPhone())));
		}
		if (StringUtils.isNotBlank(institution.getFax())) {
			result.add(new FieldValuesUpdate(faxFieldId, stringFieldMapper.mapBack(institution.getFax())));
		}
		if (StringUtils.isNotBlank(institution.getEmail())) {
			result.add(new FieldValuesUpdate(emailFieldId, stringFieldMapper.mapBack(institution.getEmail())));
		}
		if (StringUtils.isNotBlank(institution.getWebsite())) {
			result.add(new FieldValuesUpdate(websiteFieldId, stringFieldMapper.mapBack(institution.getWebsite())));
		}
		return result;
	}

	@Override
	public List<String> mapTags(Institution object) {
		return object.getTags();
	}
}
