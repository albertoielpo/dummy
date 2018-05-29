package utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import utils.bean.NetworkInfo;

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
		return NetworkUtils.getInetAddress().getHostAddress();
	}
	
	/**
	 * Get Hostname
	 * @return
	 * @throws Exception
	 */
	public static String getHostname() throws Exception {
		return NetworkUtils.getInetAddress().getHostName();
	}
	
	/**
	 * Get Network interface
	 * @return
	 * @throws Exception
	 */
	public static NetworkInterface getNetworkInterface() throws Exception {
		return NetworkInterface.getByInetAddress(NetworkUtils.getInetAddress());
	}
	
	/**
	 * Get hardware address as byte[]
	 * @return
	 * @throws Exception
	 */
	public static byte[] getHardwareAddress() throws Exception {
		return NetworkUtils.getNetworkInterface().getHardwareAddress();
	}
	
	/**
	 * 
	 * @param macAddress
	 * @param macSeparator
	 * @return
	 */
	private static String convertByteMacToString(byte[] macAddress, String macSeparator) {
		StringBuilder sb = new StringBuilder();
		if(macAddress != null && macSeparator != null) {
			for (int i = 0; i < macAddress.length; i++) 
				sb.append(String.format("%02X%s", macAddress[i], (i < macAddress.length - 1) ? macSeparator : ""));
			return sb.toString();
		}
		return null;
	}
	
	/**
	 * Get MAC address using a custom separator
	 * @param macSeparator
	 * @return
	 */
	public static String getMacAddress(String macSeparator) {
		try {
			return convertByteMacToString(NetworkUtils.getHardwareAddress(),  macSeparator == null ? ":" : macSeparator);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Get active MAC address (like 0A:01:01:A0:05:01)
	 * @return mac address as String
	 */
	public static String getMacAddress() {
		return NetworkUtils.getMacAddress(":");
	}
	

	/**
	 * 
	 * @return
	 */
	public static List<NetworkInfo> getNetworkInfo() {
		try {
			List<NetworkInfo> res = new ArrayList<NetworkInfo>();
			Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();
			while(ni.hasMoreElements()) {
				NetworkInterface n = ni.nextElement();
				List<String> ha = new ArrayList<String>();
				Enumeration<InetAddress> ia = n.getInetAddresses();
				while(ia.hasMoreElements()) {
					InetAddress iaa = ia.nextElement();
					ha.add(iaa.getHostAddress());
				}
				
				NetworkInfo info = new NetworkInfo(ha, n.getDisplayName(), (convertByteMacToString(n.getHardwareAddress(), ":")), n.isVirtual());
				res.add(info);
			}
			
			return Utils.emptyIfNull(res).stream().filter(Objects::nonNull).collect(Collectors.toList());
		
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Get all MAC addresses of the machine using ":" as separator
	 * @return List<String> of all MAC addresses found. null if and exception is thrown
	 */
	public static List<String> getAllMacAddresses(){
		return Utils.emptyIfNull(getNetworkInfo()).stream().map(NetworkInfo::getMac).filter(Objects::nonNull).collect(Collectors.toList());
	}
	
	/**
	 * Get all mac addresses and interfaces name
	 * <br/><br/>
	 * <b>Example</b>
	 * <br/>
	 * 10:F0:05:6A:6B:1B Intel(R) Dual Band Wireless-AC 3168 <br/>
	 * 3C:52:82:8C:50:9B Realtek PCIe GBE Family Controller #2 <br/>
	 * @return Map<mac, name>
	 */
	public static Map<String, String> getAllMacAndInterfaces(){
		Map<String, String> res = new HashMap<String, String>();
		List<NetworkInfo> ni = Utils.emptyIfNull(getNetworkInfo()).stream().filter(c -> c.getMac() != null).collect(Collectors.toList());
		ni.forEach(n -> res.put(n.getMac(), n.getDisplayName()));
		return res;
	}
	
}
