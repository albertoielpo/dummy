package dummy;
/*
 * posso non implementare solo i metodi della classe interface 
 * e posso definire nuovi prototipi sia implementati (getPaperino) 
 * sia non implementati (brum)
 */

public abstract class MyPippoAbs implements MyPippo {

	private String paperino;
	
	public String pluto(String v1) {
		return v1 + "abs";
	}

	public String getPaperino() {
		return paperino;
	}

	public void setPaperino(String paperino) {
		this.paperino = paperino;
	}
	
	public abstract String brum();
}
