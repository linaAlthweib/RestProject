package utilities;

import infrastructure.EnvironmentVariables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CapitalsApiUtilities {
	
	public static String getCapitalNameByIndex (int index) {
		JsonPath body = CountriesApiUtilities.getAllCountries(EnvironmentVariables.aPICapitals).then().extract().jsonPath();
		return body.getString("capital["+index+"]");
	}
	//**********
	public static String setCapitalApiUrl(String capitalName, String [] parameters) {
		String parametersString = CountriesApiUtilities.setParameters(parameters); 
		String capitalAPIURL = EnvironmentVariables.baseUrl + EnvironmentVariables.capitalApi + capitalName+ "?" + parametersString;
		
		return capitalAPIURL;
	}
	
	public static Response getCapitalByIndex (int capitalIndex, String[] parameters) {
		String capitalName = CapitalsApiUtilities.getCapitalNameByIndex(capitalIndex);
		String capitalApiUrl = setCapitalApiUrl(capitalName, parameters); 
		
		Response resp = RestAssured.get(capitalApiUrl);
		return resp;
	}
	
	public static Response getCapitalByName (String capitalName, String[] parameters) {
		String capitalApiUrl = setCapitalApiUrl(capitalName, parameters); 
		
		Response resp = RestAssured.get(capitalApiUrl);
		return resp;
	}

}
