package wazi.parser.jsontransformer.expression.parser;

import static org.junit.Assert.*;

import java.util.regex.Matcher;

import org.junit.Test;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import wazi.parser.jsontransformer.JSONTransformer;
import wazi.parser.jsontransformer.expression.Expression;
import wazi.parser.jsontransformer.expression.jtex.JTEX;
import wazi.parser.jsontransformer.expression.parser.ExpressionParser;

public class ExpressionParserTest {

	@Test
	public void test() throws Exception {
		ExpressionParser parser = new ExpressionParser();
		Expression baseEx1 = parser.readExpression(new JTEX("10"));
		assertEquals(10, baseEx1.val());

		Expression baseEx2 = parser.readExpression(new JTEX("true"));
		assertEquals(true, baseEx2.val());

		Expression baseEx3 = parser.readExpression(new JTEX("null"));
		assertNull(baseEx3.val());

		Expression baseEx4 = parser.readExpression(new JTEX("I.add(1, 1)"));
		assertEquals(2, baseEx4.val());

		Expression baseEx5 = parser.readExpression(new JTEX("C.choice(C.ift(false, 18), C.ift(true, 27))"));
		assertEquals(27, baseEx5.val());

		Expression baseEx6 = parser.readExpression(new JTEX("C.ite(I.gt(3, 5), \"AAA\", \"BBB\")"));
		assertEquals("BBB", baseEx6.val());
	}

	@Test
	public void testTransformation() throws Exception {

		String json1 = "{"
				+ "\"temperature\": 60"
				+ "}";

		String json2 = "{"
				+ "\"temperature\": 30.6"
				+ "}";

		String transformer = "{\n" +
								"    \"feeling\": \"=C.ite(D.gt(J.p(\\\"$.temperature\\\"), 50), \\\"hot\\\", \\\"cold\\\")\"\n" +
								"}";

		String transformer2 = "{\n" +
				"    \"info\": {\n" +
				"      \"feeling\": \"=C.ite(D.gt(J.p(\\\"$.temperature\\\"), 50), \\\"hot\\\", \\\"cold\\\")\",\n" +
				"      \"realFeel\": \"=D.add(10, J.p(\\\"$.temperature\\\"))\"\n" +
				"    },\n" +
				"    \"=S.concat(2, 3, 4)\": false\n" +
				"}";

		JSONObject result1 = (JSONObject) JSONTransformer.transform(json1, transformer);
		assertEquals("{\"feeling\":\"hot\"}", result1.toJSONString());

		JSONObject result2 = (JSONObject) JSONTransformer.transform(json2, transformer);
		assertEquals("{\"feeling\":\"cold\"}", result2.toJSONString());

		JSONObject result3 = (JSONObject) JSONTransformer.transform(json2, transformer2);
		assertEquals("{\"234\":false,\"info\":{\"realFeel\":40.6,\"feeling\":\"cold\"}}", result3.toJSONString());
	}
}
