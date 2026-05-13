package unbosque.edu.co.proyectoFinalSem3.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class VirusTotalUploadResponse {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id_Response;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Data data;
	
	public VirusTotalUploadResponse() {
		// TODO Auto-generated constructor stub
	}

	public VirusTotalUploadResponse(Data data) {
		super();
		this.data = data;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
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
