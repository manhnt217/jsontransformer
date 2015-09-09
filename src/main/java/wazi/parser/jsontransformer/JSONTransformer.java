package wazi.parser.jsontransformer;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class JSONTransformer {

	/**
	 * 
	 * @param srcJson
	 * @param transformer
	 * @return
	 */
	public static JSONObject transform(String srcJson, Object transformer) {
		
		if (transformer instanceof JSONObject) {//transformer as JSONObject (with jtex in values)
			
		} else if (transformer instanceof JSONArray) {//transformer as JSONArray (with jtex in values)

		} else if (transformer instanceof String) {//entire jtex expression

		} else {//exception
			throw new IllegalArgumentException("Input invalid!");
		}
		throw new UnsupportedOperationException("Not implemented yet!!!!");
	}
}
