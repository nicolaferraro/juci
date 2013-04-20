package it.nerdammer.nibbio.juci;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;


public class AppTest {
    
	@Test
	public void simpleTest() throws Exception {
		
		String command = "/Users/nicolaferraro/git/juci/juci/src/test/resources/engines/stockfish/stockfish-231-64";
		
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
