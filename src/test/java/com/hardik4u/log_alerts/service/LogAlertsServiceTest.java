package com.hardik4u.log_alerts.service;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hardik4u.log_alerts.model.LogEvent;
import com.hardik4u.log_alerts.repository.LogAlertsRepository;

@SpringBootTest(args = "--logfile.path=logtestfile.txt")
public class LogAlertsServiceTest {

	@Autowired
	LogAlertsService logAlertsService;

	@Mock
	private LogAlertsRepository alertsRepository;

	static {
		System.setProperty("logfile.path", "logtestfile.txt");
	}

	@DisplayName("Test processFile")
	@Test
	void testProcessFile() {

		LogEvent logEvent = new LogEvent();
		logEvent.setAlert(false);
		logEvent.setDuration(4L);
		logEvent.setHost("12345");
		logEvent.setId("scsmbstgra");
		
		long ts = System.currentTimeMillis();
		logEvent.setTimestamp(ts);
		
		
		Optional<LogEvent> optional = Optional.of(logEvent);
		
		Mockito.doReturn(optional).when(alertsRepository).findById(logEvent.getId());
		
		Mockito.doReturn(logEvent).when(alertsRepository).save(logEvent);
		
		logAlertsService.processFile();
		
	}

}
