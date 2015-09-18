package wazi.jsontransformer.expression.function;


import com.jayway.jsonpath.JsonPath;

public class J {
	public static Object p(Object json, String jsonPath) {
		return JsonPath.read(json, jsonPath);
	}
}
