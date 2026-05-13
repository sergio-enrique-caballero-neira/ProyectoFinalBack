package unbosque.edu.co.proyectoFinalSem3.dto;

public class StatsDTO {

	private int malicious;
	private int suspicious;
	private int undetected;
	private int harmless;
	
	public StatsDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public StatsDTO(int malicious, int suspicious, int undetected, int harmless) {
		super();
		this.malicious = malicious;
		this.suspicious = suspicious;
		this.undetected = undetected;
		this.harmless = harmless;
	}

	public int getMalicious() {
		return malicious;
	}

	public void setMalicious(int malicious) {
		this.malicious = malicious;
	}

	public int getSuspicious() {
		return suspicious;
	}

	public void setSuspicious(int suspicious) {
		this.suspicious = suspicious;
	}

	public int getUndetected() {
		return undetected;
	}

	public void setUndetected(int undetected) {
		this.undetected = undetected;
	}

	public int getHarmless() {
		return harmless;
	}

	public void setHarmless(int harmless) {
		this.harmless = harmless;
	}

	@Override
	public String toString() {
		return "Stats [malicious=" + malicious + ", suspicious=" + suspicious + ", undetected=" + undetected
				+ ", harmless=" + harmless + "]";
	}

}
