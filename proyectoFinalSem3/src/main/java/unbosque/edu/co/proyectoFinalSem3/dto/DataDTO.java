package unbosque.edu.co.proyectoFinalSem3.dto;


public class DataDTO {
	
	private long data_id;
	
	private String id;
	private String type;
	private String nombreArchivo;

	private AttributesDTO attributes;

	public DataDTO() {
		// TODO Auto-generated constructor stub
	}

	public DataDTO(String type, AttributesDTO attributes) {
		super();
		this.type = type;
		this.attributes = attributes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AttributesDTO getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributesDTO attributes) {
		this.attributes = attributes;
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public long getData_id() {
		return data_id;
	}

	public void setData_id(long data_id) {
		this.data_id = data_id;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", type=" + type + ", attributes=" + "]";
	}

}
