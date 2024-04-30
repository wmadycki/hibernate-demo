package pl.hibernate.example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.hibernate.example.modules.examples.ModelExecutor;

@SpringBootApplication
public class HibernateApplication {

	@Autowired
	ModelExecutor modelExecutor;

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@PostConstruct
	public void init() {
//		modelExecutor.createModel();
	}

}
