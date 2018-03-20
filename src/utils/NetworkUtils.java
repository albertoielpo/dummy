package utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;

public class NetworkUtils {
    
	public static InetAddress getInetAddress() throws Exception {
		return InetAddress.getLocalHost();
	}
	
	public static String getIp() throws Exception {
		return getInetAddress().getHostAddress();
	}
	
	public static String getHostname() throws Exception {
		return getInetAddress().getHostName();
	}
	
	public static NetworkInterface getNetworkInterface() throws Exception {
		return NetworkInterface.getByInetAddress(getInetAddress());
	}
	
	public static byte[] getHardwareAddress() throws Exception {
		return getNetworkInterface().getHardwareAddress();
	}
	
	public static String getMacAddress() {
		try {
			return new String(getHardwareAddress(), StandardCharsets.UTF_8);
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
