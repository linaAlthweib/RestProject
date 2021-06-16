package utilities;

import infrastructure.EnvironmentVariables;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CountriesApiUtilities {
		
	
	public static Response getAllCountries(String[] parameters) {
		String parametersString = setParameters(parameters); 
		String countriesAPIURL = EnvironmentVariables.baseUrl + EnvironmentVariables.countriesApi + "?" +  parametersString;
		
		Response resp = RestAssured.get(countriesAPIURL);
		return resp; 
	}
	
	public static String setParameters(String[] parameters) {
		String fieldsLabel = "fields=";
		String parametersString = "";
		if (parameters != null && parameters.length > 0) {
			for(String param : parameters) {
				parametersString += param + ";"; 
			}
			
			parametersString = fieldsLabel+parametersString;
		}
		return parametersString;
	}
	

}
