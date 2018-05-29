package utils.bean;

import java.util.ArrayList;
import java.util.List;

public class NetworkInfo {

	private List<String> addresses;
	private String displayName;
	private String mac;
	private boolean virtual;
	
	public NetworkInfo() {
		this.addresses = new ArrayList<String>();
	}
	
	public NetworkInfo(List<String> addresses, String displayName, String mac, boolean virtual) {
		this.addresses = addresses;
		this.displayName = displayName;
		this.mac = mac;
		this.virtual = virtual;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> address) {
		this.addresses = address;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isVirtual() {
		return virtual;
	}

	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	
}
