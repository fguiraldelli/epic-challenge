package fguiraldelli.epicchallenge;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Francisco Guiraldelli",
						email = "francisco.guiraldelli@gmail.com",
						url = "https://www.linkedin.com/in/fguiraldelli/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.franciscoguiraldelli.com/license"
				)

		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = "www.franciscoguiraldelli.com/user_management"
		)
)
public class EpicChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicChallengeApplication.class, args);
	}

}
