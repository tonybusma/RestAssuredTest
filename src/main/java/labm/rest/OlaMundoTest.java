package labm.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	
	@Test
	public void testOlaMundo() {
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		Assert.assertTrue("O status code foi diferente de 200", response.statusCode() == 200);
		Assert.assertEquals(200, response.statusCode());
		
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		
		given()
			//Pré-condições
		.when()
			.get("http://restapi.wcaquino.me/ola").
		then()
			.statusCode(200);
		
		
	}
	
	@Test
	public void devoConhecerMatchersHamcrest() {
		int numero = 110;
		Assert.assertThat(numero, Matchers.is(110));
		Assert.assertThat(numero, Matchers.isA(Integer.class));
		Assert.assertThat(numero, Matchers.greaterThan(109));
		Assert.assertThat(numero, Matchers.lessThan(111));
		
		List<Integer> impares = Arrays.asList(1,3,5,7,9);
		assertThat(impares, hasSize(5));
		assertThat(impares, Matchers.contains(1,3,5,7,9));
		assertThat(impares, Matchers.containsInAnyOrder(1,3,5,9,7));
		assertThat(impares, Matchers.hasItem(1));
		assertThat(impares, Matchers.hasItems(1,5));
		
		assertThat("Maria", not("João"));		
		assertThat("Maria", Matchers.anyOf(Matchers.is("Maria"), Matchers.is("Luiz")));
		assertThat("Maria", Matchers.allOf(Matchers.startsWith("Ma"), Matchers.endsWith("ria"), Matchers.containsString("ari")));
	}
	
	@Test
	public void devoValidarBody() {
		
		given()
			//Pré-condições
		.when()
			.get("http://restapi.wcaquino.me/ola").
		then()
			.statusCode(200)
			.body(Matchers.is("Ola Mundo!"))
			.body(Matchers.containsString("Mundo"))
			.body(Matchers.not(Matchers.nullValue()))
			;
		
		
	}
	
}
