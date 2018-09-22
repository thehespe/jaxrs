package pl.thehespe.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pl.thehespe.dao.DataPictureDao;
import pl.thehespe.db.model.DataPicture;
import pl.thehespe.util.ConnectData;

public class DataPictuteDaoImpl extends ConnectData implements DataPictureDao {

	private Properties properties = getPropValues();

	@Override
	public List<DataPicture> getAll() {
		List<DataPicture> dataPictures = new ArrayList<>();
		String sql = "SELECT * FROM public.data_picture";
		try {

			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(properties.getProperty(URL),
					properties.getProperty(USERNAME), properties.getProperty(PASSWORD));

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DataPicture dataPicture = new DataPicture();
				dataPicture.setId(rs.getInt("dpi_id"));
				dataPicture.setPicture(rs.getBytes("dpi_value"));
				dataPictures.add(dataPicture);
			}

			connection.close();
			ps.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dataPictures;
	}

	@Override
	public DataPicture get(Integer id) {
		String sql = "SELECT * FROM public.data_picture WHERE dpi_id = ?";
		DataPicture dataPicture = new DataPicture();
		try {

			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(properties.getProperty(URL),
					properties.getProperty(USERNAME), properties.getProperty(PASSWORD));

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				dataPicture.setId(rs.getInt("dpi_id"));
				dataPicture.setPicture(rs.getBytes("dpi_value"));
			}

			connection.close();
			ps.close();
		} catch (SQLException |

				ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dataPicture;
	}

	@Override
	public void save(String fileName) throws NullPointerException {
		String sql = "INSERT INTO public.data_picture(dpi_value) VALUES (?)";

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(properties.getProperty(URL),
					properties.getProperty(USERNAME), properties.getProperty(PASSWORD));

			PreparedStatement ps = connection.prepareStatement(sql);
			String resource = "pictures/" + fileName;
			InputStream is = getClass().getClassLoader().getResourceAsStream(resource);

			ps.setBinaryStream(1, is);
			ps.executeUpdate();

			connection.close();
			ps.close();
			is.close();

		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Integer id, String fileName) throws NullPointerException {
		String sql = "UPDATE public.data_picture SET dpi_value = ? WHERE dpi_id = ?";

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(properties.getProperty(URL),
					properties.getProperty(USERNAME), properties.getProperty(PASSWORD));

			PreparedStatement ps = connection.prepareStatement(sql);
			String resource = "pictures/" + fileName;
			InputStream is = getClass().getClassLoader().getResourceAsStream(resource);

			ps.setBinaryStream(1, is);
			ps.setInt(2, id);
			ps.executeUpdate();

			connection.close();
			ps.close();
			is.close();

		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
