package pl.thehespe.dao;

import java.util.List;

import pl.thehespe.db.model.DataText;

public interface DataTextDao {

	public List<DataText> getAll();

	public DataText get(Integer id);

	public void save(String value);

	public void update(Integer id, String value);

	public DataText getLastEntry();
}
