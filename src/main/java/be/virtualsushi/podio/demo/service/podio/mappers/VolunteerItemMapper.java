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
import be.virtualsushi.podio.demo.dto.item.Volunteer;

public class VolunteerItemMapper implements PodioItemMapper<Volunteer> {

	@Value("${podio.virtualsushi.volunteer.firstname.id}")
	private Integer firstNameFieldId;

	@Value("${podio.virtualsushi.volunteer.lastname.id}")
	private Integer lastNameFieldId;

	@Value("${podio.virtualsushi.volunteer.description.id}")
	private Integer descriptionFieldId;

	@Value("${podio.virtualsushi.volunteer.phone.id}")
	private Integer phoneFieldId;

	@Value("${podio.virtualsushi.volunteer.email.id}")
	private Integer emailFieldId;

	@Value("${podio.virtualsushi.volunteer.institution.id}")
	private Integer institutionFieldId;

	private StringFieldMapper stringFieldMapper = new StringFieldMapper();
	private AppFieldMapper<Institution> appFieldMapper = new AppFieldMapper<Institution>(Institution.class);

	@Override
	public Volunteer map(Item podioItem) {
		Volunteer result = new Volunteer();
		result.setId(podioItem.getId());
		for (FieldValuesView field : podioItem.getFields()) {
			if (field.getId() == firstNameFieldId) {
				result.setFirstName(stringFieldMapper.map(field));
			}
			if (field.getId() == lastNameFieldId) {
				result.setLastName(stringFieldMapper.map(field));
			}
			if (field.getId() == descriptionFieldId) {
				result.setDescription(stringFieldMapper.map(field));
			}
			if (field.getId() == phoneFieldId) {
				result.setPhone(stringFieldMapper.map(field));
			}
			if (field.getId() == emailFieldId) {
				result.setEmail(stringFieldMapper.map(field));
			}
			if (field.getId() == institutionFieldId) {
				result.setInstitution(appFieldMapper.map(field));
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
	public ItemCreate mapToCreate(Volunteer volunteer) {
		ItemCreate result = new ItemCreate(createFieldsList(volunteer), null, mapTags(volunteer));
		return result;
	}

	@Override
	public ItemUpdate mapToUpdate(Volunteer volunteer) {
		ItemUpdate result = new ItemUpdate(createFieldsList(volunteer));
		return result;
	}

	private List<FieldValuesUpdate> createFieldsList(Volunteer volunteer) {
		List<FieldValuesUpdate> result = new ArrayList<>();
		if (StringUtils.isNotBlank(volunteer.getFirstName())) {
			result.add(new FieldValuesUpdate(firstNameFieldId, stringFieldMapper.mapBack(volunteer.getFirstName())));
		}
		if (StringUtils.isNotBlank(volunteer.getLastName())) {
			result.add(new FieldValuesUpdate(lastNameFieldId, stringFieldMapper.mapBack(volunteer.getLastName())));
		}
		if (StringUtils.isNotBlank(volunteer.getDescription())) {
			result.add(new FieldValuesUpdate(descriptionFieldId, stringFieldMapper.mapBack(volunteer.getDescription())));
		}
		if (StringUtils.isNotBlank(volunteer.getPhone())) {
			result.add(new FieldValuesUpdate(phoneFieldId, stringFieldMapper.mapBack(volunteer.getPhone())));
		}
		if (StringUtils.isNotBlank(volunteer.getEmail())) {
			result.add(new FieldValuesUpdate(emailFieldId, stringFieldMapper.mapBack(volunteer.getEmail())));
		}
		if (volunteer.getInstitution() != null) {
			result.add(new FieldValuesUpdate(institutionFieldId, appFieldMapper.mapBack(volunteer.getInstitution())));
		}
		return result;
	}

	@Override
	public List<String> mapTags(Volunteer object) {
		return object.getTags();
	}
}
