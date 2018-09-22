package pl.thehespe.dao;

import java.util.List;

import pl.thehespe.db.model.DataPicture;

public interface DataPictureDao {

	public List<DataPicture> getAll();

	public DataPicture get(Integer id);

	public void save(String fileName) throws NullPointerException;

	public void update(Integer id, String fileName) throws NullPointerException;
}
