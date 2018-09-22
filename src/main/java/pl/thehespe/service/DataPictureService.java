package pl.thehespe.service;

import java.util.List;

import pl.thehespe.json.JsonDataPicture;

public interface DataPictureService {

	public List<JsonDataPicture> getAll();
	
	public JsonDataPicture getById(Integer id);
	
	public JsonDataPicture save(String fileName);
	
	public JsonDataPicture update(Integer id, String fileName);
}
