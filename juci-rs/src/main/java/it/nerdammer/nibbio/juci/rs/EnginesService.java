package it.nerdammer.nibbio.juci.rs;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import it.nerdammer.nibbio.juci.Engine;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/engines")
public class EnginesService {

	
	@GET
	@Path("/bestmove/{engine}")
	@Produces("text/plain")
	public String computeBestMove(@PathParam("engine") String engineName, @QueryParam("status") String status) {
		
		Engine engine = EnginesManager.getInstance().getEngine(engineName);
		if(engine==null) {
			throw new IllegalArgumentException("Engine not registered: " + engineName);
		}
		
		List<String> moves = new LinkedList<String>();
		if(status!=null) {
			StringTokenizer st = new StringTokenizer(status, " ");
			while(st.hasMoreTokens()) {
				moves.add(st.nextToken());
			}
		}
		
		String reply = engine.computeBestMove(moves);
		return reply;
	}
	
	@GET
	@Path("/list")
	@Produces("text/plain")
	public String listEngines() {
		Collection<String> engines = EnginesManager.getInstance().getEngineNames();
		return engines.toString();
	}
	
	
	
}
