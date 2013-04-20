package it.nerdammer.juci.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/engines")
public interface EnginesService {

	@GET
	@Path("/bestmove/{engine}")
	@Produces("text/plain")
	public String computeBestMove(@PathParam("engine") String engineName, @QueryParam("status") String status);

	@GET
	@Path("/list")
	@Produces("text/plain")
	public String listEngines();
}