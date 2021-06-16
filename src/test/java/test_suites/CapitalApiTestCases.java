package test_suites;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import infrastructure.EnvironmentVariables;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.List;
import io.restassured.specification.SpecificationQuerier;
import models.Currency;
import utilities.AssertionUtilities;
import utilities.CapitalsApiUtilities;
import utilities.CountriesApiUtilities;
import infrastructure.EnvironmentVariables;

public class CapitalApiTestCases {
	
	
	
	@BeforeClass 
	public void beforeClass() throws IOException {
		EnvironmentVariables.setEnvironmentProperties();
	}
	
	@Test 
	void verify_API_response_of_valid_capital() {
		int index = (int)(Math.random()*100);
		Response capitalResponse = CapitalsApiUtilities.getCapitalByIndex(index, EnvironmentVariables.aPICapitals); 
		AssertionUtilities.validateStatusCode(capitalResponse, HttpStatus.SC_OK);
		AssertionUtilities.validateSchema(capitalResponse, "Schemas/capitalSchema.json"); 
		List<Currency> currenciesFromCountries = CountriesApiUtilities.getAllCountries(EnvironmentVariables.aPICountries).then().extract().jsonPath().getList("currencies["+index+"]",Currency.class);
		List<Currency> currenciesFromCapital = capitalResponse.then().extract().jsonPath().getList("currencies[0]",Currency.class);
		AssertionUtilities.validateCurrencies(currenciesFromCountries, currenciesFromCapital);
		
	}
	

	@Test 
	void verify_API_response_of_invalid_capital() {
		String invalidCapitalName = "invaid";

		Response capitalResponse = CapitalsApiUtilities.getCapitalByName(invalidCapitalName, EnvironmentVariables.aPICountries); //latlng,current
		AssertionUtilities.validateStatusCode(capitalResponse, HttpStatus.SC_NOT_FOUND);
		AssertionUtilities.validateSchema(capitalResponse, "Schemas/capitalNotfoundSchema.json"); 
	}


}
