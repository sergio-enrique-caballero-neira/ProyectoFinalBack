package unbosque.edu.co.proyectoFinalSem3.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Data {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long data_id;
	
	private String id;
	private String type;
	private String nombreArchivo;

	@OneToOne(cascade = CascadeType.ALL)
	private Attributes attributes;

	public Data() {
		// TODO Auto-generated constructor stub
	}

	public Data(String type, Attributes attributes, String nombreArchivo) {
		super();
		this.type = type;
		this.attributes = attributes;
		this.nombreArchivo = nombreArchivo;
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

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Long getData_id() {
		return data_id;
	}

	public void setData_id(Long data_id) {
		this.data_id = data_id;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", type=" + type + ", attributes=" + "]";
	}

}
