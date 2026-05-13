package unbosque.edu.co.proyectoFinalSem3.dto;

import java.util.Map;

public class AttributesDTO {

	private long id_Attributes;

	private String status;
	private StatsDTO stats;
	private Map<String, EngineResultDTO> results;

	public AttributesDTO() {
		// TODO Auto-generated constructor stub
	}

	public AttributesDTO(String status, StatsDTO stats, Map<String, EngineResultDTO> results) {
		super();
		this.status = status;
		this.stats = stats;
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StatsDTO getStats() {
		return stats;
	}

	public void setStats(StatsDTO stats) {
		this.stats = stats;
	}

	public Map<String, EngineResultDTO> getResults() {
		return results;
	}

	public void setResults(Map<String, EngineResultDTO> results) {
		this.results = results;
	}

	public long getId_Attributes() {
		return id_Attributes;
	}

	public void setId_Attributes(long id_Attributes) {
		this.id_Attributes = id_Attributes;
	}

	@Override
	public String toString() {
		return "Attributes [status=" + status + ", stats=" + stats.toString() + ", results=" + results + "]";
	}

}
