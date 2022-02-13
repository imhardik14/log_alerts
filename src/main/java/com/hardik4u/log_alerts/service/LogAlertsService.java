package com.hardik4u.log_alerts.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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

			ObjectMapper jsonMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);

			BufferedReader br = new BufferedReader(Channels.newReader(inputChannel, "UTF-8"));
			Stream<String> lines = br.lines();
			long starttime = System.currentTimeMillis();
			lines.parallel().forEach(line -> {

				try {

					LogEvent logEvent = jsonMapper.readerFor(LogEvent.class).readValue(line);

					Optional<LogEvent> optional = alertsRepository.findById(logEvent.getId());
					if (optional.isPresent()) {
						LogEvent logEventObj = optional.get();

						Long duration = Math.abs(logEvent.getTimestamp() - logEventObj.getTimestamp());
						logEvent.setDuration(duration);

					}

					alertsRepository.save(logEvent);

				} catch (JsonMappingException e) {
					logger.error(e.getMessage());
				} catch (JsonProcessingException e) {
					logger.error(e.getMessage());
				}

			});

			// alertsRepository.saveAll(list);

			long endtime = System.currentTimeMillis();
			logger.info("Time Taken ms: " + (endtime - starttime));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

}
