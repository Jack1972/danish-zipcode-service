package biz.petersen.zipcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Defines a springboot application
 * 
 * forklar kort hvad der sker
 * 
 * @author Jack Petersen
 * 		   http://wwww.petersen.biz
 * 		   https://github.com/Jack1972	
 */
@ComponentScan
@SpringBootApplication
public class ZipcodeService {
	
	private static final Logger log = LoggerFactory.getLogger(ZipcodeService.class);

	public static void main(String[] args) {
		SpringApplication.run(ZipcodeService.class, args);
	}

	/**
	 * Forklar lidt om CommandlineRunner
	 * 
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner init(CityZipCodeRepository repository) {
		return (args) -> {
			log.info("Start populate danish zipcode data to h2 in memory database");

			// read zipcodes and citys from file
			InputStream in = getClass().getResourceAsStream("/postnummer.dat");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			try {
				String line = reader.readLine();
				String[] result;
				while (line != null) {
					result = line.split(",");
					// save zipcodes and citys in the h2 in memory database
					repository.save(new CityZipCodeEntity(result[0], result[1]));
					line = reader.readLine();
				}
			} finally {
				reader.close();
			}
			
			log.info("Finished populating danish zipcode data to h2 in memory database");
		};
	}
}