package com.hardik4u.log_alerts.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LogEvent {

	@Id
	String id;
	LogState state;
	Long timestamp;
	Long duration;
	
	String type;
	String host;
	
	Boolean alert;
	
}
