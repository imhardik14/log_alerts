package com.hardik4u.log_alerts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hardik4u.log_alerts.model.LogEvent;

public interface LogAlertsRepository extends JpaRepository<LogEvent,String>{

}
