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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import pl.thehespe.json.JsonDataText;
import pl.thehespe.service.DataTextService;
import pl.thehespe.service.impl.DataTextServiceImpl;

@Path("/data_text")
@Api("/Data Text")
@SwaggerDefinition(tags = { @Tag(name = "Data Text", description = "REST endpoint for Data Text") })
public class DataTextRest {

	private DataTextService dataTextService = new DataTextServiceImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "find all data text")
	public List<JsonDataText> getAllValues() {
		return dataTextService.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "find data text by id")
	public JsonDataText getById(@ApiParam(value = "id of data text in database", required = true) 
	@PathParam("id") Integer id, @Context HttpServletResponse response) throws IOException {
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
	@ApiOperation(value = "save data text")
	public JsonDataText save(@ApiParam(value = "value to save in database", required = true) 
	@PathParam("value") String value) {
		return dataTextService.save(value);
	}

	@PUT
	@Path("/{id}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "update data text")
	public JsonDataText update(@ApiParam(value = "id of updated value", required = true) 
	@PathParam("id") Integer id, @ApiParam(value = "value of updated entry", required = true)  
	@PathParam("value") String value,
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
