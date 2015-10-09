package wazi.jsontransformer;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.TokenParser;

import java.util.Map.Entry;

public class JSONTransformer {

	public static Object transform(String srcJson, String transformer) throws Exception {

//		ExpressionParser expressionParser = new ExpressionParser();
//		expressionParser.setInputJSON(Configuration.defaultConfiguration().jsonProvider().parse(srcJson));
//
//		JSONParser jsonParser = new JSONParser(JSONParser.MODE_PERMISSIVE);
//		Object parse = jsonParser.parse(transformer);
//
//		if (parse instanceof JSONObject) {
//			JSONObject jsonObject = (JSONObject) parse;
//			return processJSONObject(jsonObject, expressionParser);
//		} else if (parse instanceof JSONArray) {
//			JSONArray jsonArray = (JSONArray) parse;
//			return processJSONArray(jsonArray, expressionParser);
//		} else {
//			throw new TransformException("Tranformer object is neither JSONObject nor JSONArray");
//		}
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	private static JSONArray processJSONArray(JSONArray jsonArray, TokenParser tokenParser) throws Exception {

		JSONArray result = new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			result.add(evaluate(jsonArray.get(i), tokenParser));
		}
		return result;
	}

	private static JSONObject processJSONObject(JSONObject jsonObject, TokenParser tokenParser) throws Exception {

		JSONObject result = new JSONObject();
		for (Entry<String, Object> entry : jsonObject.entrySet()) {
			result.put(evaluate(entry.getKey(), tokenParser).toString(), evaluate(entry.getValue(), tokenParser));
		}
		return result;
	}

	private static Object evaluate(Object exp, TokenParser<BaseExpression> parser) throws Exception {

		if (exp instanceof String) {
			String expString = (String) exp;
			if (expString.charAt(0) == '=') {
				if (expString.charAt(1) != '=') {//JTEX expression
					return parser.read(new JTEX(expString.substring(1))).eval(null);
				} else {
					return expString.substring(1);
				}
			} else {
				return expString;
			}
		} else if (exp instanceof JSONObject) {
			return processJSONObject((JSONObject) exp, parser);
		} else if (exp instanceof JSONArray) {
			return processJSONArray((JSONArray) exp, parser);
		} else return exp;
	}
}
