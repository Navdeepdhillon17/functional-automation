package selenium_udemy.functional_tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	
	public List<HashMap<String, String>> getJsonToString() throws IOException {
		//json to string
		String jsonString = FileUtils.readFileToString(new File(System.getProperty("user.dire")+"\\functional-tests\\src\\test\\java\\selenium_udemy\\functional_tests\\data.json"),
				StandardCharsets.UTF_8);
		//string to hashmap (we would need jackson databind)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonString, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
}
