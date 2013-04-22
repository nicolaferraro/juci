package it.nerdammer.juci.rs;

import it.nerdammer.juci.Engine;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class EnginesServiceImpl implements EnginesService {
	
	private Logger logger = Logger.getLogger(EnginesServiceImpl.class);
	
	public String computeBestMove(String engineName, String status) {
		
		logger.debug("Computing best move for engine " + engineName);
		logger.debug("Status: " + status);
		
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
		
		try {
			String reply = engine.computeBestMove(moves);
			
			logger.info("Best move: " + reply);
			
			return reply;
		} catch(RuntimeException e) {
			logger.error("Error computing the best move", e);
			throw e;
		}
	}
	
	
	public String listEngines() {
		Collection<String> engines = EnginesManager.getInstance().getEngineNames();
		return engines.toString();
	}
	
	
	
}
