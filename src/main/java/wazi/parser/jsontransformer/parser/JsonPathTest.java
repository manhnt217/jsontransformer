package wazi.parser.jsontransformer.parser;

import org.junit.Test;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import static org.junit.Assert.*;

public class JsonPathTest {

	@Test
	public void test() {
		String json = 	"{\n" + 
						"  \"ab\": [\n" + 
						"    \"TempF3R302\",\n" + 
						"    30.3\n" + 
						"  ],\n" + 
						"  \"dfdfdf\": 232\n" + 
						"}";
		
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
		
		assertEquals("TempF3R302", JsonPath.read(document, "$.ab.[0]"));
		assertEquals(30.3, JsonPath.read(document, "$.ab.[1]"));
	}
	
	@Test
	public void test2() {
		String json = 	"[\n" + 
						"  \"abc\",\n" + 
						"  123,\n" + 
						"  \"{}\"\n" + 
						"]";
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
		
		System.out.println(document.getClass().getName());
	}
}
