package pl.thehespe.mapper;

import java.util.List;

import pl.thehespe.db.model.DataPicture;
import pl.thehespe.json.JsonDataPicture;

public interface DataPictureMapper {

	public JsonDataPicture toJson(DataPicture dataPicture);
	
	public List<JsonDataPicture> toJsonList(List<DataPicture> dataPictures);
}
