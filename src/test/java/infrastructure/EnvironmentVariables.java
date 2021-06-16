package infrastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentVariables {
	public static String baseUrl ;
	public static String capitalApi;
	public static String countriesApi;
	public static String name;
	public static String capital;
	public static String currencies;
	public static String latlng;
	public static String regionalBlocs;
	public static final String RESOURCES_PATH = "src/test/resources";
	public static final String CONFIG_PROPERITIES_FILE = "config.properties";
	public static String[] aPICapitals;
	public static String[] aPICountries;
	
	
	public static void setEnvironmentProperties() throws IOException {
		Properties props = new Properties();
		File configFile = new File(RESOURCES_PATH + "/" + CONFIG_PROPERITIES_FILE);
		FileReader fileReader = new FileReader(configFile);
		
		  try {
		         props.load(fileReader);
		         baseUrl = props.getProperty("base_url");
		         capitalApi = props.getProperty("capital_api");
		         countriesApi = props.getProperty("countries_api");
		         name = props.getProperty("name_Field");
		         capital = props.getProperty("capital_Field");
		         currencies = props.getProperty("currencies_Field");
		         latlng = props.getProperty("latlng_Field");
		         regionalBlocs = props.getProperty("regional_block");

		      } catch(FileNotFoundException fnfe) {
		         fnfe.printStackTrace();
		      } catch(IOException ioe) {
		         ioe.printStackTrace();
		      } finally {
		         fileReader.close();
		      }
		
		  aPICapitals = new String[] { "name", "capital","currencies","latlng","regionalBlocs"};
		  aPICountries = new String[] { "name", "capital","currencies","latlng"};

		
	}
	
	
	
	
	
	
	
}
