package pl.thehespe.rest.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import pl.thehespe.dao.DataPictureDao;
import pl.thehespe.dao.impl.DataPictuteDaoImpl;

@Path("/data_picture")
public class DataPictureRest {

	@GET
	@Produces("application/json")
	public Response getAllValues() {
		DataPictureDao dataPictureDao = new DataPictuteDaoImpl();
		String json = new Gson().toJson(dataPictureDao.getAll());

		return Response.ok(json, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Produces("application/json")
	@Path("/{id}")
	public Response get(@PathParam("id") Integer id) {

		DataPictureDao dataPictureDao = new DataPictuteDaoImpl();
		String json = new Gson().toJson(dataPictureDao.get(id));

		return Response.ok(json, MediaType.APPLICATION_JSON).build();

	}

	@POST
	@Path("/{fileName}")
	public void save(@PathParam("fileName") String fileName) {
		DataPictureDao dataPictureDao = new DataPictuteDaoImpl();
		dataPictureDao.save(fileName);

	}

	@PUT
	@Path("/{id}/{fileName}")
	public void update(@PathParam("id") Integer id, @PathParam("fileName") String fileName) {
		DataPictureDao dataPictureDao = new DataPictuteDaoImpl();
		dataPictureDao.update(id, fileName);

	}
}
