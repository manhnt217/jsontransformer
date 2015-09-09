package wazi.parser.jsontransformer.expression;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * Json Path selector
 * 
 * @author wazi
 *
 */
public class J {

	private Object json;

	public J(String jsonString) {
		json = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
	}
	
	/**
	 * Selector method
	 * @param jsonPath
	 * @return
	 */
	public Object s(String jsonPath) {
		return JsonPath.read(json, jsonPath);
	}
}
