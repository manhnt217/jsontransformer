# JSON Transformer
Transform JSON to JSON using user friendly syntax.
(Underconstruction)
## Usage
Suppose that you have a json document with structure that looks like `{"temperature": xxx}` where xxx is your real data.
So you want to define a "rule" that everytime the temperature reaches higher than 50 degree celcius, you'll receive:
```
{
    "feeling": "hot"
}
```
and other times, you'll receive:
```
{
    "feeling": "cold"
}
```
then your JTEX (JSON transformation expression) will look like (`C.ite(D.gt(J.p("$.temperature"), 50), "hot", "cold")`):
```
{
    "feeling": "=C.ite(D.gt(J.p(\"$.temperature\"), 50), \"hot\", \"cold\")"
}
```
## Code Example
```java
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
```

_* This version only support basic types (Int, Double, String, Boolean, Null) and function call for arithmatic and logical operations.The next version will focus on simplify JTEX syntax (I know that currently it looks a bit messy :D)_
#### Next version
- Support simple logical and arithmetic expressions
- Support control statement: **if ... then ... else ...** and **for (... in ...) yeild ...**

#### Info
- Author: Nguyen Tien Manh (aka Wazi Armstrong)
- Contact me at: manh.nguyen.217 (at) gmail (dot) com
