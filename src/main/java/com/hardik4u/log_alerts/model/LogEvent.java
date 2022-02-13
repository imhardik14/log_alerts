package com.hardik4u.log_alerts.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LogEvent {

	@Id
	String id;
	Long timestamp = 0l;	
	
	Long duration ;
	
	String type;
	String host;
	
	@Formula("duration > 4")
	Boolean alert;
	
			
}
