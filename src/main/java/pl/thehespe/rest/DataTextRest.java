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

import pl.thehespe.json.JsonDataText;
import pl.thehespe.service.DataTextService;
import pl.thehespe.service.impl.DataTextServiceImpl;

@Path("/data_text")
public class DataTextRest {

	private DataTextService dataTextService = new DataTextServiceImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<JsonDataText> getAllValues() {
		return dataTextService.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonDataText getValue(@PathParam("id") Integer id, @Context HttpServletResponse response)
			throws IOException {
		JsonDataText jsonDataText = dataTextService.getById(id);
		if (jsonDataText.getId() != null) {
			return jsonDataText;
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.flushBuffer();
			return null;
		}
	}

	@POST
	@Path("/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonDataText save(@PathParam("value") String value) {
		
		return dataTextService.save(value);		
	}

	@PUT
	@Path("/{id}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonDataText update(@PathParam("id") Integer id, @PathParam("value") String value,
			@Context HttpServletResponse response) throws IOException {
		try {
			
			return dataTextService.update(id, value);
		} catch (NotFoundException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.flushBuffer();
			
			return null;
		}
	}
}
