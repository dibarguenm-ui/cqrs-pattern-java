package co.vinni.cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@SpringBootApplication
public class CqrsQueryApplication {

	public static void main(String[] args) {
		log.info("Inicio la aplicación Query");
		SpringApplication.run(CqrsQueryApplication.class, args);
	}

}
