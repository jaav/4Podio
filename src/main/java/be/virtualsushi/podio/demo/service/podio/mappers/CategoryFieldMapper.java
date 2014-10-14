package be.virtualsushi.podio.demo.service.podio.mappers;

import java.util.Collections;
import java.util.Map;

import be.virtualsushi.podio.demo.dto.item.CategoryOption;
import be.virtualsushi.podio.demo.dto.item.CategoryOptionStatus;

public class CategoryFieldMapper extends PodioFieldMapper<CategoryOption, Integer> {

	@Override
	protected CategoryOption doMap(WrappedField fieldWrapper) {
		CategoryOption result = new CategoryOption();
		result.setId(fieldWrapper.getFieldValue("[value][id]", Integer.class));
		result.setText(fieldWrapper.getFieldValue("[value][text]", String.class));
		result.setColor(fieldWrapper.getFieldValue("[value][color]", String.class));
		result.setStatus(CategoryOptionStatus.getByName(fieldWrapper.getFieldValue("[value][status]", String.class)));
		return result;
	}

	@Override
	public Map<String, Integer> mapBack(CategoryOption value) {
		return Collections.singletonMap("value", value.getId());
	}

}
