package it.nerdammer.juci;

import java.util.HashMap;
import java.util.Map;

public class EnginePreferences {

	private long maxComputationTimeMillis = 5000;
	
	private Map<String, String> options;
	
	public EnginePreferences() {
		this.options = new HashMap<String, String>();
	}
	
	public long getMaxComputationTimeMillis() {
		return maxComputationTimeMillis;
	}
	
	public void setMaxComputationTimeMillis(long maxComputationTimeMillis) {
		this.maxComputationTimeMillis = maxComputationTimeMillis;
	}
	
	public void setOption(String name, String value) {
		this.options.put(name, value);
	}
	
	public void removeOption(String name) {
		this.options.remove(name);
	}
	
	public Map<String, String> getOptions() {
		return options;
	}
	
}
