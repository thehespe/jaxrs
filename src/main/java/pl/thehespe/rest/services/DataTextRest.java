package pl.thehespe.rest.services;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import pl.thehespe.dao.DataTextDao;
import pl.thehespe.dao.impl.DataTextDaoImpl;
import pl.thehespe.db.model.DataText;

@Path("/data_text")
public class DataTextRest {

	@GET
	@Produces("application/json")
	public Response getAllValues() {
		DataTextDao dataTextDao = new DataTextDaoImpl();
		String json = new Gson().toJson(dataTextDao.getAll());

		return Response.ok(json, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getValue(@PathParam("id") Integer id) {
		DataTextDao dataTextDao = new DataTextDaoImpl();
		DataText dataText = dataTextDao.get(id);
		if (dataText.getId() != null) {
			String json = new Gson().toJson(dataTextDao.get(id));
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@POST
	@Path("/{value}")
	public Response save(@PathParam("value") String value) {
		DataTextDao dataTextDao = new DataTextDaoImpl();
		try {
			dataTextDao.save(value);
			return Response.ok().build();

		} catch (ClassNotFoundException | SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PUT
	@Path("/{id}/{value}")
	public Response update(@PathParam("id") Integer id, @PathParam("value") String value) {
		DataTextDao dataTextDao = new DataTextDaoImpl();
		try {
			dataTextDao.update(id, value);
			return Response.ok().build();

		} catch (ClassNotFoundException | SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
