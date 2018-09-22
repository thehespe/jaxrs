package pl.thehespe.service;

import java.util.List;

import pl.thehespe.json.JsonDataText;

public interface DataTextService {

	public List<JsonDataText> getAll();
	
	public JsonDataText getById(Integer id);
	
	public JsonDataText save(String value);
	
	public JsonDataText update(Integer id, String value);
}
