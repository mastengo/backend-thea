package edu.caece.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Foto") 
public class Foto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
	private Integer id = 0;
	
	@Lob
	@Column(name = "archivo")
	private byte[] archivo;
	
	@Column(name="nombre_archivo")
	private String nombreArchivo;

	@Column(name="dni_persona")
	private String dniPersona;
	
	//@ManyToOne
	//@JoinColumn(name="dni_persona2")
	//private Persona persona;

	public Foto(byte[] archivo, 
				String idPersona) {
		this.archivo = archivo;
		this.dniPersona = idPersona;
	}
	
	public Foto() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getDniPersona() {
		return dniPersona;
	}

	public void setDniPersona(String idPersona) {
		this.dniPersona = idPersona;
	}
	
	/*public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}*/
	
	public String toString (){
        String datosFoto = "Foto::" +
        					archivo + ":" +
        					dniPersona;
        return datosFoto;
    }

}
