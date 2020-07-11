package edu.caece.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="persons_incomes")
@Getter
@Setter
public class PersonIncome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
		
	@Column(name="event_type")
	private EventType eventType;
	
	@Column(name="message")
	private String message;
	
	@Column(name = "access_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date accessDate;
	
	public PersonIncome() {
		
	}
	
	public PersonIncome(Person person, EventType eventType, String message) {
		this.person = person;
		this.eventType = eventType;
		this.message = message;
	}
	
}
