package pl.thehespe.rest.services;

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

import pl.thehespe.dao.DataPictureDao;
import pl.thehespe.dao.impl.DataPictuteDaoImpl;
import pl.thehespe.db.model.DataPicture;

@Path("/data_picture")
public class DataPictureRest {

	@GET
	@Produces("application/json")
	public Response getAll() {
		DataPictureDao dataPictureDao = new DataPictuteDaoImpl();
		String json = new Gson().toJson(dataPictureDao.getAll());

		return Response.ok(json, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response get(@PathParam("id") Integer id) {

		DataPictureDao dataPictureDao = new DataPictuteDaoImpl();
		DataPicture dataPicture = dataPictureDao.get(id);
		if (dataPicture.getId() != null) {
			String json = new Gson().toJson(dataPicture);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@POST
	@Path("/{fileName}")
	public Response save(@PathParam("fileName") String fileName) {
		DataPictureDao dataPictureDao = new DataPictuteDaoImpl();

		try {
			dataPictureDao.save(fileName);
			return Response.ok().build();
		} catch (NullPointerException e) {
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@PUT
	@Path("/{id}/{fileName}")
	public Response update(@PathParam("id") Integer id, @PathParam("fileName") String fileName) {
		DataPictureDao dataPictureDao = new DataPictuteDaoImpl();
		try {
			dataPictureDao.update(id, fileName);
			return Response.ok().build();
		} catch (NullPointerException e) {
			return Response.status(Status.NOT_FOUND).build();
		}

	}
}
