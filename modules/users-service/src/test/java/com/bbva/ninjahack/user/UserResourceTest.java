package com.bbva.ninjahack.user;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
// @QuarkusTestResource(DatabaseResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserResourceTest {

	private static final String FIRST_NAME = "Rubén";
	private static final String LAST_NAME = "Ramos Salamanca";
	private static final String EMAIL = "reramos011@gmail.com";
	private static final String PASSWORD = "1234";
	private static final String BAD_PASSWORD = "01234";

	@Test
	void shouldPingOpenAPI() {
		given().header(ACCEPT, APPLICATION_JSON).when().get("/openapi").then().statusCode(OK.getStatusCode());
	}

	@Test
	void shouldPingSwaggerUI() {
		given().when().get("/swagger-ui").then().statusCode(OK.getStatusCode());
	}

	@Test
	@Order(1)
	void shouldLoginUser() {

		Person user = new Person();
		user.email = EMAIL;
		user.password = PASSWORD;

		Person result = given().body(user).header(CONTENT_TYPE, APPLICATION_JSON).header(ACCEPT, APPLICATION_JSON)
				.when().post("/api/users/login").then().statusCode(OK.getStatusCode())
				.header(CONTENT_TYPE, APPLICATION_JSON).extract().body().as(Person.class);

		assertEquals(FIRST_NAME, result.firstName);
		assertEquals(LAST_NAME, result.lastName);
		assertEquals(EMAIL, result.email);
	}

	@Test
	@Order(2)
	void shouldNoAuthUser() {

		Person user = new Person();
		user.email = EMAIL;
		user.password = BAD_PASSWORD;

		given().body(user).header(CONTENT_TYPE, APPLICATION_JSON).header(ACCEPT, APPLICATION_JSON).when()
				.post("/api/users/login").then().statusCode(UNAUTHORIZED.getStatusCode());

	}
	
	@Test
	void shouldPingLiveness() {
	    given()
	        .when().get("/health/live")
	        .then()
	        .statusCode(OK.getStatusCode());
	}

	@Test
	void shouldPingReadiness() {
	    given()
	        .when().get("/health/ready")
	        .then()
	        .statusCode(OK.getStatusCode());
	}

}