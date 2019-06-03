package test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class FunWithInstant {

	public static void main(String[] args) throws InterruptedException {
		
		Instant instant = Instant.now();
		
		LocalDateTime datetime1 = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
		LocalDateTime datetime2 = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(2));
		LocalDateTime datetime3 = LocalDateTime.ofInstant(instant, ZoneOffset.ofHoursMinutes(1, 30));
		String formatted1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss:SSS").format(datetime1);
		String formatted2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss:SSS").format(datetime2);
		String formatted3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss:SSS").format(datetime3);
		
		System.out.println("instant: " + instant); 
		System.out.println("instant.getEpochSecond(): " + instant.getEpochSecond());
		System.out.println("instant.getNano(): " + instant.getNano());	//10^9 sec
		System.out.println("formatted with: " + ZoneOffset.UTC + " " + formatted1); 
		System.out.println("formatted with: " + ZoneOffset.ofHours(2) + " " + formatted2);
		System.out.println("formatted with: " + ZoneOffset.ofHoursMinutes(1, 30) + " " + formatted3);
		
	}
}
