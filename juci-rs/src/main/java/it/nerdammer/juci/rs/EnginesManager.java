package it.nerdammer.juci.rs;

import it.nerdammer.juci.Engine;
import it.nerdammer.juci.EngineFactory;
import it.nerdammer.juci.EnginePreferences;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public enum EnginesManager {

	INSTANCE;
	
	private Map<String, Engine> engines;
	
	private EnginesManager() {
		this.engines = new TreeMap<String, Engine>();
	}
	
	public static EnginesManager getInstance() {
		return INSTANCE;
	}
	
	public synchronized void registerEngine(String name, String command, EnginePreferences prefs) {
		if(name==null || command==null) {
			throw new IllegalArgumentException("Null parameters");
		}
		
		if(engines.containsKey(name)) {
			throw new IllegalArgumentException("Name already present");
		}
		
		Engine engine;
		if(prefs==null) {
			engine = EngineFactory.getInstance().createEngine(command);
		} else {
			engine = EngineFactory.getInstance().createEngine(command, prefs);
		}
		
		this.engines.put(name, engine);
	}
	
	protected synchronized Engine getEngine(String name) {
		return this.engines.get(name);
	}
	
	public synchronized Collection<String> getEngineNames() {
		return Collections.unmodifiableCollection(new TreeSet<String>(this.engines.keySet()));
	}
	
}
