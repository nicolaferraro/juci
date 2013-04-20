package it.nerdammer.juci.rs;

import it.nerdammer.juci.Engine;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class EnginesServiceImpl implements EnginesService {

	
	public String computeBestMove(String engineName, String status) {
		
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
	
	
	public String listEngines() {
		Collection<String> engines = EnginesManager.getInstance().getEngineNames();
		return engines.toString();
	}
	
	
	
}
