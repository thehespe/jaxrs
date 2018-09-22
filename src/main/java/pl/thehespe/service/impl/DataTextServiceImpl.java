package pl.thehespe.service.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import pl.thehespe.dao.DataTextDao;
import pl.thehespe.dao.impl.DataTextDaoImpl;
import pl.thehespe.db.model.DataText;
import pl.thehespe.json.JsonDataText;
import pl.thehespe.mapper.DataTextMapper;
import pl.thehespe.mapper.impl.DataTextMapperImpl;
import pl.thehespe.service.DataTextService;

public class DataTextServiceImpl implements DataTextService {
	
	private DataTextDao dataTextDao = new DataTextDaoImpl();
	private DataTextMapper dataTextMapper = new DataTextMapperImpl();

	@Override
	public List<JsonDataText> getAll() {
		return dataTextMapper.toJsonList(dataTextDao.getAll());
	}

	@Override
	public JsonDataText getById(Integer id) {
		return dataTextMapper.toJson(dataTextDao.get(id));
	}

	@Override
	public JsonDataText save(String value) {
		dataTextDao.save(value);
		DataText dataText = dataTextDao.getLastEntry();
		
		return dataTextMapper.toJson(dataText);
	}

	@Override
	public JsonDataText update(Integer id, String value) throws NotFoundException {
		dataTextDao.update(id, value);
		DataText dataText = dataTextDao.get(id);
		if (dataText.getId() == null) {
			throw new NotFoundException();
		}
		
		return dataTextMapper.toJson(dataText);
	}

}
