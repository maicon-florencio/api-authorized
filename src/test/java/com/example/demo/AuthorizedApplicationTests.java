package com.example.demo;

import com.example.demo.config.AbstractIntegrationTest;
import com.example.demo.config.TestsConfigs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AuthorizedApplicationTests extends AbstractIntegrationTest {

	@Test
	void shouldDisplaySwaggerUiPage(){
		var content=    given()
				.basePath("/swagger-ui/index.html")
				.port(TestsConfigs.SERVER_PORT)
					.when()
						.get()
					.then()
						.statusCode(200)
					.extract()
						.body()
						.asString();

		Assertions.assertTrue(content.contains("Swagger UI"));
	}

}
