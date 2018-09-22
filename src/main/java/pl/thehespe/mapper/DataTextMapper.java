package pl.thehespe.mapper;

import java.util.List;

import pl.thehespe.db.model.DataText;
import pl.thehespe.json.JsonDataText;

public interface DataTextMapper {

	public JsonDataText toJson(DataText dataText);
	
	public List<JsonDataText> toJsonList(List<DataText> dataTexts);
	
}
