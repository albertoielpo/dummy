package test;

public interface SabaudoDao {
	
	@SabaudoAnnotation(stato = "statoSabaudo")
	public boolean isSabaudo();
}
