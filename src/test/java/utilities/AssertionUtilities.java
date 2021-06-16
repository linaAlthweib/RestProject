package utilities;


import io.restassured.response.Response;
import models.Currency;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.List;

public class AssertionUtilities {

	
	public static void validateStatusCode(Response response, int expectedStatusCode) {
		response.then().statusCode(expectedStatusCode);
	}
	
	
	public static void validateSchema(Response response, String schemaFilePath) {
		response.then().body(matchesJsonSchemaInClasspath(schemaFilePath));
	}
	
	public static void validateCurrencies(List<Currency> currenciesFromCountries, List<Currency> currenciesFromCapital) {
		assert currenciesFromCountries.size() == currenciesFromCapital.size();
		for(int i=0; i < currenciesFromCountries.size();i++) {
			assert currenciesFromCountries.get(i).code.equals(currenciesFromCapital.get(i).code);
		}
	}
	
	
	
	//vallidate currency for the task #3
	//latlng to add
	
}
