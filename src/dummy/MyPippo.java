package dummy;

/*
 * Classe di interfaccia
 * E' definita come functional interface per evidenziare
 * il fatto che avrà solo un metodo e può essere implementata
 * on demand con le lambda expression 
 */

@FunctionalInterface
public interface MyPippo {
	
	public String getPluto(String v1);

}
