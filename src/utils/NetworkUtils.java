package utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;

public class NetworkUtils {
    
	/**
	 * Get InetAddress
	 * @return
	 * @throws Exception
	 */
	public static InetAddress getInetAddress() throws Exception {
		return InetAddress.getLocalHost();
	}
	
	/**
	 * Get IpAddress
	 * @return
	 * @throws Exception
	 */
	public static String getIp() throws Exception {
		return getInetAddress().getHostAddress();
	}
	
	/**
	 * Get Hostname
	 * @return
	 * @throws Exception
	 */
	public static String getHostname() throws Exception {
		return getInetAddress().getHostName();
	}
	
	/**
	 * Get Network interface
	 * @return
	 * @throws Exception
	 */
	public static NetworkInterface getNetworkInterface() throws Exception {
		return NetworkInterface.getByInetAddress(getInetAddress());
	}
	
	/**
	 * Get hardware address as byte[]
	 * @return
	 * @throws Exception
	 */
	public static byte[] getHardwareAddress() throws Exception {
		return getNetworkInterface().getHardwareAddress();
	}
	
	/**
	 * Get MAC address using a custom separator
	 * @param macSeparator
	 * @return
	 */
	public static String getMacAddress(String macSeparator) {
		try {
			macSeparator = macSeparator == null ? ":" : macSeparator;
			byte[] mac = getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) 
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? macSeparator : ""));		
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Get MAC address (like 0A:01:01:A0:05:01)
	 * @return String
	 */
	public static String getMacAddress() {
		return getMacAddress(":");
	}
	

	
	
}
