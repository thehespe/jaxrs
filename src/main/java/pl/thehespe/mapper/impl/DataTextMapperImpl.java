package pl.thehespe.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import pl.thehespe.db.model.DataText;
import pl.thehespe.json.JsonDataText;
import pl.thehespe.mapper.DataTextMapper;

public class DataTextMapperImpl implements DataTextMapper {

	@Override
	public JsonDataText toJson(DataText dataText) {
		JsonDataText jsonDataText = new JsonDataText();
		jsonDataText.setId(dataText.getId());
		jsonDataText.setValue(dataText.getValue());
		
		return jsonDataText;
	}

	@Override
	public List<JsonDataText> toJsonList(List<DataText> dataTexts) {
		List<JsonDataText> jsonDataTexts = new ArrayList<>();		
		dataTexts.forEach(e -> jsonDataTexts.add(toJson(e)));
		
		return jsonDataTexts;
	}

}
