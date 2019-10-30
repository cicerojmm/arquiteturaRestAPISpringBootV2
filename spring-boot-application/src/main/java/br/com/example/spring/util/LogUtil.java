package br.com.example.spring.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

	private static final Logger logger = LogManager.getLogger(LogUtil.class);

	public static void error(String messagem) {
		logger.error(messagem);
	}

	public static void info(String messagem) {
		logger.info(messagem);
	}

	public static void warning(String messagem) {
		logger.warn(messagem);
	}
	
	public static void error(Exception exception) {
		exception.printStackTrace();
	}
}
