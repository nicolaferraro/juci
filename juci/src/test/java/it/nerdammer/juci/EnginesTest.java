package it.nerdammer.juci;

import it.nerdammer.juci.Engine;
import it.nerdammer.juci.EngineFactory;
import it.nerdammer.juci.EnginePreferences;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;


public class EnginesTest {
    
	@Test
	public void stockfishTest() throws Exception {
		
		String command = "src/test/resources/engines/stockfish/stockfish-231-64";
		
		EnginePreferences prefs = new EnginePreferences();
		prefs.setMaxComputationTimeMillis(50);
		
		Engine engine = EngineFactory.getInstance().createEngine(command, prefs);
		
		List<String> moves = new LinkedList<String>();
		moves.add("e2e4");
		moves.add("e7e5");
		String move = engine.computeBestMove(moves);
		System.out.println(move);
		
		moves.add(move);
		move = engine.computeBestMove(moves);
		
		System.out.println(move);
	}
	
	@Test
	public void stockfishTestEasyLevel() throws Exception {
		
		String command = "src/test/resources/engines/stockfish/stockfish-231-64";
		
		EnginePreferences prefs = new EnginePreferences();
		prefs.setMaxComputationTimeMillis(1);
		prefs.setOption("Skill Level", "0");
		
		Engine engine = EngineFactory.getInstance().createEngine(command, prefs);
		
		List<String> moves = new LinkedList<String>();
		moves.add("e2e4");
		moves.add("e7e5");
		String move = engine.computeBestMove(moves);
		System.out.println(move);
		
		moves.add(move);
		move = engine.computeBestMove(moves);
		
		System.out.println(move);
	}
	
	
	@Test
	public void critterTest() throws Exception {
		
		String command = "src/test/resources/engines/critter/critter-16a";
		
		EnginePreferences prefs = new EnginePreferences();
		prefs.setMaxComputationTimeMillis(50);
		
		Engine engine = EngineFactory.getInstance().createEngine(command, prefs);
		
		List<String> moves = new LinkedList<String>();
		moves.add("e2e4");
		moves.add("e7e5");
		String move = engine.computeBestMove(moves);
		System.out.println(move);
		
		moves.add(move);
		move = engine.computeBestMove(moves);
		
		System.out.println(move);
	}
	
	
}
