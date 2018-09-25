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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import pl.thehespe.json.JsonDataPicture;
import pl.thehespe.service.DataPictureService;
import pl.thehespe.service.impl.DataPictureServiceImpl;

@Path("/data_picture")
@Api("/Data Picture")
@SwaggerDefinition(tags = { @Tag(name = "Data Picture", description = "REST endpoint for Data Picture") })
public class DataPictureRest {

	private DataPictureService dataPictureService = new DataPictureServiceImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "find all data pictures")
	public List<JsonDataPicture> getAll() {
		return dataPictureService.getAll();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@ApiOperation(value = "find data text by id")
	public JsonDataPicture getById(@ApiParam(value = "id of data picture in database", required = true) 
	@PathParam("id") Integer id, @Context HttpServletResponse response) throws IOException {
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
	@ApiOperation(value = "save data picture")
	public JsonDataPicture save(@ApiParam(value = "picture filename located in resources", required = true)  
	@PathParam("fileName") String fileName, 
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
	@ApiOperation(value = "update data picture")
	public JsonDataPicture update(@ApiParam(value = "id of updated picture", required = true) 
	@PathParam("id") Integer id, @ApiParam(value = "picture filename located in resources", required = true) 
	@PathParam("fileName") String fileName, 
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
