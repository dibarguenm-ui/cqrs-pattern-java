package co.vinni.cqrs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CqrsCommandApplication {

	public static void main(String[] args) {
		log.info("Inicio la aplicación");
		SpringApplication.run(CqrsCommandApplication.class, args);
	}

}
