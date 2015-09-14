# JSON Transformer
Transform JSON to JSON using user friendly syntax.
(Underconstruction)
## Example
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
_* This version only support basic types (Int, Double, String, Boolean, Null) and function call for arithmatic and logical operations.The next version will focus on simplify JTEX syntax (I know that currently it looks a bit messy :D)_