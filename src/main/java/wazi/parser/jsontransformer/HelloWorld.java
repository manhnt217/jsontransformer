package wazi.parser.jsontransformer;

public class HelloWorld {
	public static void main(String[] args) {
		String helloWorld = "{\n" +
				"  \"array\": [\n" +
				"    1,\n" +
				"    2,\n" +
				"    3\n" +
				"  ],\n" +
				"  \"boolean\": true,\n" +
				"  \"null\": null,\n" +
				"  \"number\": 12.3,\n" +
				"  \"object\": {\n" +
				"    \"a\": \"b\",\n" +
				"    \"c\": \"d\",\n" +
				"    \"e\": \"f\"\n" +
				"  },\n" +
				"  \"string\": \"Hello World\"\n" +
				"}";

		System.out.println(helloWorld);
	}
}
