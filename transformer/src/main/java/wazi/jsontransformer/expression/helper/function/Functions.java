package wazi.jsontransformer.expression.helper.function;

import com.jayway.jsonpath.JsonPath;

import java.util.LinkedList;
import java.util.List;

/**
 * General function
 * Created by wazi on 2015-10-02 002.
 */
@SuppressWarnings("unused")
public class Functions {

	public static final List<String> INPUT_JSON_FUNCTIONS = new LinkedList<String>(){{
		add("wazi.jsontransformer.expression.helper.function.Functions.p");
	}};

	/**
	 * JSON Path selector
	 * @param path Json path
	 * @param document JSONObject or JSONArray
	 * @return
	 */
	public static Object p(String path, Object document) {
		return JsonPath.read(document, path);
	}

	public static String s(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	public static String concat(Object... objs) {
		StringBuilder builder = new StringBuilder();
		for (Object obj : objs) {

			if (obj == null) {
				continue;
			}
			builder.append(obj.toString());
		}
		return builder.toString();
	}
}
