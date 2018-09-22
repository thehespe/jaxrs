package pl.thehespe.dao;

import java.util.List;

import org.glassfish.jersey.server.internal.scanning.ResourceFinderException;

import pl.thehespe.db.model.DataPicture;

public interface DataPictureDao {

	public List<DataPicture> getAll();

	public DataPicture get(Integer id);

	public void save(String fileName) throws ResourceFinderException;

	public void update(Integer id, String fileName) throws ResourceFinderException;
	
	public DataPicture getLastEntry();
}
