package wazi.parser.jsontransformer.expression.function;

import com.jayway.jsonpath.JsonPath;

public class J {
	public static Object p(String jsonPath, Object json) {
		return JsonPath.read(json, jsonPath);
	}
}
