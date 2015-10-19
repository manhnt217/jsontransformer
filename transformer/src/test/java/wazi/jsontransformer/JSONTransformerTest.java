package wazi.jsontransformer;

import net.minidev.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wazi on 2015-10-18 018.
 */
public class JSONTransformerTest {

	@Test
	public void testTransform() throws Exception {
		String json1 = "{temperature: 60}";

		String json2 = "{temperature: 30.6}";

		String transformer = "{feeling: = if p('$.temperature') > 50 then 'hot' else 'cold'}";

		String transformer2 = ""
				+	"{"
				+	"    info: {"
				+	"      feeling: = if p('$.temperature') > 50 then 'hot' else 'cold',"
				+	"      realFeel: = 10 + p('$.temperature'))"
				+	"    },"
				+	"    = concat(2, 3, 4): false"
				+	"}";

		JSONObject result1 = (JSONObject) JSONTransformer.transform(json1, transformer);
		assertTrue(result1.containsKey("feeling"));
		assertEquals("hot", result1.get("feeling"));

		JSONObject result2 = (JSONObject) JSONTransformer.transform(json2, transformer);
		assertTrue(result2.containsKey("feeling"));
		assertEquals("cold", result2.get("feeling"));

		JSONObject result3 = (JSONObject) JSONTransformer.transform(json2, transformer2);
		assertEquals(40.6, ((JSONObject)result3.get("info")).get("realFeel"));
	}
}