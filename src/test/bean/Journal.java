package test.bean;

public class Journal {
	
	public Journal(long timestamp) {
		this.timestamp = timestamp;
	}
	
	private long timestamp;
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}
}
