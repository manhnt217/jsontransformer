package wazi.jsontransformer;

import com.jayway.jsonpath.Configuration;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import wazi.jsontransformer.expression.BaseExpression;
import wazi.jsontransformer.expression.FunctionExpression;
import wazi.jsontransformer.expression.jtex.JTEX;
import wazi.jsontransformer.parser.ExpressionParser;
import wazi.jsontransformer.parser.helper.MultiChoiceParser;

import javax.xml.crypto.dsig.TransformException;
import java.util.HashMap;
import java.util.Map.Entry;

public class JSONTransformer {

	public static Object transform(String srcJsonString, String transformer) throws Exception {

		ExpressionParser expressionParser = new ExpressionParser();
		Object srcJson = Configuration.defaultConfiguration().jsonProvider().parse(srcJsonString);

		JSONParser jsonParser = new JSONParser(JSONParser.MODE_PERMISSIVE);
		Object parse = jsonParser.parse(transformer);

		if (parse instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) parse;
			return processJSONObject(jsonObject, expressionParser.parser, srcJson);
		} else if (parse instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray) parse;
			return processJSONArray(jsonArray, expressionParser.parser, srcJson);
		} else {
			throw new TransformException("Tranformer object is neither JSONObject nor JSONArray");
		}
	}

	private static JSONArray processJSONArray(JSONArray jsonArray, MultiChoiceParser<? extends BaseExpression> parser, Object srcJson) throws Exception {

		JSONArray result = new JSONArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			result.add(evaluate(jsonArray.get(i), parser, srcJson));
		}
		return result;
	}

	private static JSONObject processJSONObject(JSONObject jsonObject, MultiChoiceParser<? extends BaseExpression> parser, Object srcJson) throws Exception {

		JSONObject result = new JSONObject();
		for (Entry<String, Object> entry : jsonObject.entrySet()) {
			result.put(evaluate(entry.getKey(), parser, srcJson).toString(), evaluate(entry.getValue(), parser, srcJson));
		}
		return result;
	}

	private static Object evaluate(Object exp, MultiChoiceParser<? extends BaseExpression> parser, Object srcJson) throws Exception {

		if (exp instanceof String) {
			String expString = (String) exp;
			if (expString.charAt(0) == '=') {
				if (expString.charAt(1) != '=') {//JTEX expression
					return parser.read(new JTEX(expString.substring(1))).eval(new HashMap<String, Object>() {
						{
							put(FunctionExpression.SRC_JSON_SYMBOL, srcJson);
						}
					});
				} else {
					return expString.substring(1);
				}
			} else {
				return expString;
			}
		} else if (exp instanceof JSONObject) {
			return processJSONObject((JSONObject) exp, parser, srcJson);
		} else if (exp instanceof JSONArray) {
			return processJSONArray((JSONArray) exp, parser, srcJson);
		} else return exp;
	}
}
