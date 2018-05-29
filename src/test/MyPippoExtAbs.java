package test;

/* 
 * devo implementare tutti i metodi 
 * della classe interface e della classe abstract
 */

public class MyPippoExtAbs extends MyPippoAbs{
	
	private String paperoga;
	
	public String getPaperoga() {
		return this.paperoga;
	}
	
	public void setPaperoga(String paperoga) {
		this.paperoga = paperoga;
	}
	
	public String brum() {
		return "brum";
	}

	@Override
	public String getPluto(String v1) {
		return v1;
	}
	
}
