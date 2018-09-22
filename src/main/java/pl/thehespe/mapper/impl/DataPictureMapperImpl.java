package pl.thehespe.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import pl.thehespe.db.model.DataPicture;
import pl.thehespe.json.JsonDataPicture;
import pl.thehespe.mapper.DataPictureMapper;

public class DataPictureMapperImpl implements DataPictureMapper {

	@Override
	public JsonDataPicture toJson(DataPicture dataPicture) {
		JsonDataPicture jsonDataPicture = new JsonDataPicture();
		jsonDataPicture.setId(dataPicture.getId());
		jsonDataPicture.setPicture(dataPicture.getPicture());
		
		return jsonDataPicture;
	}

	@Override
	public List<JsonDataPicture> toJsonList(List<DataPicture> dataPictures) {
		List<JsonDataPicture> jsonDataPictures = new ArrayList<>();		
		dataPictures.forEach(e -> jsonDataPictures.add(toJson(e)));
		
		return jsonDataPictures;
	}

}
