package it.nerdammer.juci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class SimpleUciEngine implements Engine {
	
	private String command;
	
	private EnginePreferences preferences;
	
	private Process process;
	
	private PrintWriter toEngine;
	
	private BufferedReader fromEngine;
	
	public SimpleUciEngine(String command, EnginePreferences preferences) {
		this.command = command;
		this.preferences = preferences;
	}
	
	private synchronized void connect() {
		try {
			this.process = Runtime.getRuntime().exec(this.command);
			
			// Use the system charset
			this.toEngine = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "UTF-8"));
			this.fromEngine = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
			
		} catch(Exception e) {
			throw new IllegalStateException("Unable to initialize", e);
		}
	}
	
	private synchronized void disconnect() {
		try {
			process.destroy();
			
		} catch(Exception e) {
			throw new IllegalStateException("Unable to disconnect", e);
		} finally {
			toEngine = null;
			fromEngine = null;
			process = null;
		}
	}

	public synchronized String computeBestMove(List<String> moves) {
		
		try {
			connect();
			
			skipLines(1);
			
			write("uci");
		
			readUntil("uciok");
			
			Map<String, String> options = preferences.getOptions();
			for(String name : options.keySet()) {
				String value = options.get(name);
				
				write("setoption name " + name + " value " + value);
			}
			
			write("isready");
			
			assertReply("readyok");
			
			write("ucinewgame");
			
			StringBuilder moveList = new StringBuilder();
			for(String m : moves) {
				moveList.append(" " + m);
			}
			
			write("position startpos moves" + moveList);
			write("go movetime " + preferences.getMaxComputationTimeMillis());
			
			String res = readStartsWith("bestmove");
			
			return getToken(res, 1);
		} catch(Exception e) {
			throw new IllegalStateException(e);
		} finally {
			disconnect();
		}
	}
	
	private String read() {
		try {
			return fromEngine.readLine();
		} catch(IOException e) {
			throw new IllegalStateException("Unable to read from stream", e);
		}
	}
	
	private void assertReply(String result) {
		String reply = read();
		if(!result.equalsIgnoreCase(reply)) {
			throw new IllegalStateException("Unexpected reply: " + reply);
		}
	}
	
	private void skipLines(int number) {
		for(int i=0; i<number; i++) {
			read();
		}
	}
	
	private void readUntil(String expectedResult) {
		String line;
		while((line=read())!=null) {
			if(line.equalsIgnoreCase(expectedResult)) {
				return;
			}
		}
		
		throw new IllegalStateException("No more input from uci engine");
	}
	
	private String readStartsWith(String expectedResult) {
		String line;
		while((line=read())!=null) {
			if(line.startsWith(expectedResult)) {
				return line;
			}
		}
		
		throw new IllegalStateException("No more input from uci engine");
	}
	
	private String getToken(String s, int pos) {
		StringTokenizer st = new StringTokenizer(s, " ");
		int i=0;
		while(st.hasMoreTokens()) {
			String t = st.nextToken();
			if(i==pos) {
				return t;
			}
			i++;
		}
		return null;
	}
	
	private void write(String command) {
		toEngine.println(command);
		toEngine.flush();
	}

}
