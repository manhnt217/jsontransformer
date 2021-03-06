# JSON Transformer
Transform JSON to JSON using user friendly syntax.
(Underconstruction - Version 2.0 pre-release)
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
then your JTEX (JSON transformation expression) will look like `if p('$.temperature') > 50 then 'hot' else 'cold'`:
```
{
    "feeling": "if p('$.temperature') > 50 then 'hot' else 'cold'"
}
```
## Code Example
```java
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
```
#### Next version
- Support for statement: **for (... in ...) yeild ...**

#### Info
- Author: Nguyen Tien Manh (aka Wazi Armstrong)
- Contact me at: manh.nguyen.217 (at) gmail (dot) com
