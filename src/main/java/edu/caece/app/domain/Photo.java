package edu.caece.app.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="photos")
@Setter
@Getter
public class Photo {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToMany(mappedBy = "photos", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Person> persons;
	
	@ManyToMany(mappedBy = "photos", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<User> users;
	
	@Lob
	@Column(name = "photo")
	@Basic(fetch = FetchType.LAZY)
	private byte[] photo;
	
	
}
