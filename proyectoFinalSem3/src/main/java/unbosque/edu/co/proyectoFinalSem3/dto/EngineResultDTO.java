package unbosque.edu.co.proyectoFinalSem3.dto;

public class EngineResultDTO {

	private long id_EngineResult;

	private String engine_name;
	private String category;
	private String result;

	public EngineResultDTO() {
		// TODO Auto-generated constructor stub
	}

	public EngineResultDTO(String engine_name, String category, String result) {
		super();
		this.engine_name = engine_name;
		this.category = category;
		this.result = result;
	}

	public long getId_EngineResult() {
		return id_EngineResult;
	}

	public void setId_EngineResult(long id_EngineResult) {
		this.id_EngineResult = id_EngineResult;
	}

	public String getEngine_name() {
		return engine_name;
	}

	public void setEngine_name(String engine_name) {
		this.engine_name = engine_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
