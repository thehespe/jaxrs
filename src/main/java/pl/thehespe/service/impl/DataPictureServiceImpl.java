package pl.thehespe.service.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.glassfish.jersey.server.internal.scanning.ResourceFinderException;

import pl.thehespe.dao.DataPictureDao;
import pl.thehespe.dao.impl.DataPictureDaoImpl;
import pl.thehespe.db.model.DataPicture;
import pl.thehespe.json.JsonDataPicture;
import pl.thehespe.mapper.DataPictureMapper;
import pl.thehespe.mapper.impl.DataPictureMapperImpl;
import pl.thehespe.service.DataPictureService;

public class DataPictureServiceImpl implements DataPictureService {
	
	private DataPictureDao dataPictureDao = new DataPictureDaoImpl();
	private DataPictureMapper dataPictureMapper = new DataPictureMapperImpl();

	@Override
	public List<JsonDataPicture> getAll() {
		return dataPictureMapper.toJsonList(dataPictureDao.getAll());
	}

	@Override
	public JsonDataPicture getById(Integer id) {
		return dataPictureMapper.toJson(dataPictureDao.get(id));
	}

	@Override
	public JsonDataPicture save(String fileName) throws ResourceFinderException{
		dataPictureDao.save(fileName);
		DataPicture dataPicture = dataPictureDao.getLastEntry();
		
		return dataPictureMapper.toJson(dataPicture);
	}

	@Override
	public JsonDataPicture update(Integer id, String fileName) throws ResourceFinderException, NotFoundException {
		dataPictureDao.update(id, fileName);
		DataPicture dataPicture = dataPictureDao.get(id);
		
		if (dataPicture.getId() == null) {
			throw new NotFoundException();
		}
		
		return dataPictureMapper.toJson(dataPicture);
	}

}
