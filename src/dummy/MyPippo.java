package dummy;

/*
 * Classe di interfaccia
 * E' definita come functional interface per evidenziare
 * il fatto che avr� solo un metodo e pu� essere implementata
 * on demand con le lambda expression 
 */

@FunctionalInterface
public interface MyPippo {
	
	public String getPluto(String v1);

}
