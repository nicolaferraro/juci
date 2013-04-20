package it.nerdammer.juci;

public class EnginePreferences {

	private long maxComputationTimeMillis = 5000;
	
	public EnginePreferences() {
	}
	
	public long getMaxComputationTimeMillis() {
		return maxComputationTimeMillis;
	}
	
	public void setMaxComputationTimeMillis(long maxComputationTimeMillis) {
		this.maxComputationTimeMillis = maxComputationTimeMillis;
	}
	
}
