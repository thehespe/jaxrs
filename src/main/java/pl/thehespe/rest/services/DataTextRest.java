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

import pl.thehespe.dao.DataTextDao;
import pl.thehespe.dao.impl.DataTextDaoImpl;

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
		String json = new Gson().toJson(dataTextDao.get(id));
		
		return Response.ok(json, MediaType.APPLICATION_JSON).build();

	}
	
	@POST
	@Path("/{value}")
	public void save(@PathParam("value") String value) {
		DataTextDao dataTextDao = new DataTextDaoImpl();
		dataTextDao.save(value);

	}
	
	@PUT
	@Path("/{id}/{value}")
	public void update(@PathParam("id") Integer id, @PathParam("value") String value) {
		DataTextDao dataTextDao = new DataTextDaoImpl();
		dataTextDao.update(id, value);

	}
}