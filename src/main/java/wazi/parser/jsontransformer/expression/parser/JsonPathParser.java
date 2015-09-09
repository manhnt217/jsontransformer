package wazi.parser.jsontransformer.expression.parser;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * Json Path selector
 * 
 * @author wazi
 *
 */
public class JsonPathParser {

	private Object json;

	public JsonPathParser(String jsonString) {
		json = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
	}
	
	/**
	 * Selector method
	 * @param jsonPath
	 * @return
	 */
	public Object select(String jsonPath) {
		return JsonPath.read(json, jsonPath);
	}
}
