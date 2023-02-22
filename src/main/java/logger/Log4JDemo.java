package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JDemo {

	public static void main(String[] args) {
		
		Logger logger = LogManager.getLogger(Log4JDemo.class);

		System.out.println("\n Hello World..! \n");
		
		logger.trace("This is trace message");
		logger.info("This is information message");
		logger.error("This is error message");
		logger.warn("This is warning message");
		logger.fatal("This is fatal message");
		
		System.out.println("\n Completed");
	}

}
