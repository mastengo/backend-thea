package edu.caece.app.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="users_incomes")
@Getter
@Setter
public class UserIncome {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
		
	@Column(name="event_type")
	private EventType eventType;
	
	@Column(name="message")
	private String message;
	
	@Column(name = "access_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date accessDate;
	
	public UserIncome() {
		
	}
	
	public UserIncome(User user, EventType eventType, String message) {
		this.user = user;
		this.eventType = eventType;
		this.message = message;
	}
}
