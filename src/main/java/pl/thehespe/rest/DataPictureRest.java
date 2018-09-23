package pl.thehespe.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.internal.scanning.ResourceFinderException;

import pl.thehespe.json.JsonDataPicture;
import pl.thehespe.service.DataPictureService;
import pl.thehespe.service.impl.DataPictureServiceImpl;

@Path("/data_picture")
public class DataPictureRest {

	private DataPictureService dataPictureService = new DataPictureServiceImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<JsonDataPicture> getAll() {
		return dataPictureService.getAll();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public JsonDataPicture getById(@PathParam("id") Integer id, @Context HttpServletResponse response) throws IOException {
		JsonDataPicture jsonDataPicture = dataPictureService.getById(id);
		if (jsonDataPicture.getId() != null) {
			return jsonDataPicture;
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.flushBuffer();
			return null;
		}

	}

	@POST
	@Path("/{fileName}")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonDataPicture save(@PathParam("fileName") String fileName, 
			@Context HttpServletResponse response) throws IOException {
		try {
			return dataPictureService.save(fileName);
		} catch (ResourceFinderException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.flushBuffer();
			
			return null;
		}

	}

	@PUT
	@Path("/{id}/{fileName}")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonDataPicture update(@PathParam("id") Integer id, @PathParam("fileName") String fileName, 
			@Context HttpServletResponse response) throws IOException {
		try {
			return dataPictureService.update(id, fileName);
		} catch (ResourceFinderException | NotFoundException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.flushBuffer();
			
			return null;
		}

	}
}
