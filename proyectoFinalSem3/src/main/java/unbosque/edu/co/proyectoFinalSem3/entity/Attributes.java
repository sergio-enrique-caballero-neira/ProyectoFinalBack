package unbosque.edu.co.proyectoFinalSem3.entity;

import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Attributes {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id_Attributes;

	private String status;
	@Embedded
	private Stats stats;
	@OneToMany(cascade = CascadeType.ALL)
	private Map<String, EngineResult> results;

	public Attributes() {
		// TODO Auto-generated constructor stub
	}

	public Attributes(String status, Stats stats, Map<String, EngineResult> results) {
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

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public Map<String, EngineResult> getResults() {
		return results;
	}

	public void setResults(Map<String, EngineResult> results) {
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
