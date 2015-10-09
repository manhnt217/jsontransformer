package wazi.jsontransformer.expression.function;

import com.jayway.jsonpath.JsonPath;

/**
 * Created by wazi on 10/2/15.
 */
public class J {
	public static Object p(Object json, String jsonPath) {
		return JsonPath.read(json, jsonPath);
	}
}
