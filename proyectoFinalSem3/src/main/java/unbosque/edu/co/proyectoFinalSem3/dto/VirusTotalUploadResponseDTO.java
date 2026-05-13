package unbosque.edu.co.proyectoFinalSem3.dto;

public class VirusTotalUploadResponseDTO {
	
	private long id_Response;
	
	private DataDTO data;
	
	public VirusTotalUploadResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public VirusTotalUploadResponseDTO(DataDTO data) {
		super();
		this.data = data;
	}

	public DataDTO getData() {
		return data;
	}

	public void setData(DataDTO data) {
		this.data = data;
	}

	public long getId_Response() {
		return id_Response;
	}

	public void setId_Response(long id_Response) {
		this.id_Response = id_Response;
	}

	@Override
	public String toString() {
		return data.toString();
	}
	
	
	
}
