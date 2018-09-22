package pl.thehespe.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pl.thehespe.dao.DataTextDao;
import pl.thehespe.db.model.DataText;
import pl.thehespe.util.ConnectData;

public class DataTextDaoImpl extends ConnectData implements DataTextDao {

	private Properties properties = getPropValues();

	@Override
	public List<DataText> getAll() {
		List<DataText> dataTexts = new ArrayList<>();
		String sql = "SELECT * FROM public.data_text";
		try {

			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(properties.getProperty(URL), properties.getProperty(USERNAME),
					properties.getProperty(PASSWORD));

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DataText dataText = new DataText();
				dataText.setId(rs.getInt("dte_id"));
				dataText.setValue(rs.getString("dte_value"));
				dataTexts.add(dataText);
			}

			con.close();
			ps.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dataTexts;
	}

	@Override
	public DataText get(Integer id) {
		String sql = "SELECT * FROM public.data_text WHERE dte_id = ?";

		DataText dataText = new DataText();
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(properties.getProperty(URL), properties.getProperty(USERNAME),
					properties.getProperty(PASSWORD));

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();
			dataText.setId(rs.getInt("dte_id"));
			dataText.setValue(rs.getString("dte_value"));

			con.close();
			ps.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dataText;
	}

	@Override
	public void save(String value) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO public.data_text(dte_value) VALUES (?)";

		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(properties.getProperty(URL), properties.getProperty(USERNAME),
				properties.getProperty(PASSWORD));

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, value);
		ps.executeUpdate();

		con.close();
		ps.close();

	}

	@Override
	public void update(Integer id, String value) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE public.data_text SET dte_value = ? WHERE dte_id = ?";

		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(properties.getProperty(URL), properties.getProperty(USERNAME),
				properties.getProperty(PASSWORD));

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, value);
		ps.setInt(2, id);
		ps.executeUpdate();

		con.close();
		ps.close();

	}

}
