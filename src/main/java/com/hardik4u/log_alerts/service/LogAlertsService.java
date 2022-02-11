package com.hardik4u.log_alerts.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardik4u.log_alerts.model.LogEvent;
import com.hardik4u.log_alerts.repository.LogAlertsRepository;

@Service
public class LogAlertsService {

	private static final Logger logger = LoggerFactory.getLogger(LogAlertsService.class);

	@Value("${logfile.path}")
	private String logFilePath;

	@Autowired
	LogAlertsRepository alertsRepository;
	


	public void processFile() {

		
		try (FileChannel inputChannel = new FileInputStream(logFilePath).getChannel()) {
			
			ObjectMapper jsonMapper = new ObjectMapper();
			
			List<LogEvent> list = new ArrayList<>();
			BufferedReader br = new BufferedReader(Channels.newReader(inputChannel, "UTF-8"));
			br = Files.newBufferedReader(Paths.get(logFilePath));
			Stream<String> lines = br.lines();
			long starttime = System.currentTimeMillis();
			lines.forEach(line -> {

				try {

					LogEvent logEvent = jsonMapper.readerFor(LogEvent.class).readValue(line);
					Optional<LogEvent> optional = alertsRepository.findById(logEvent.getId());
					if (optional.isPresent()) {
						LogEvent logEventObj = optional.get();
						if (logEventObj.getState() != null) {
							Long duration = Math.abs(logEvent.getTimestamp() - logEventObj.getTimestamp());
							logEvent.setDuration(duration);
							if (duration > 4)
								logEvent.setAlert(true);
							else
								logEvent.setAlert(false);

						}
					}
					list.add(logEvent);

				} catch (JsonMappingException e) {
					logger.error(e.getMessage());
				} catch (JsonProcessingException e) {
					logger.error(e.getMessage());
				}

			});

			alertsRepository.saveAll(list);

			long endtime = System.currentTimeMillis();
			logger.info("Time Taken ms: " + (endtime - starttime));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

}
