# pidgey
Pidgey is an annotation-based parser between Objects and Strings.
Pidgey uses specified positions to insert the value of the field in the String.

For example:

When parsing the class 
```
public class HelloWorld {
  @PField(position=0)
  private String string1 = "Hello ";

  @PField(position=6)
  private String string2 = "World!";
}
```
Will result in the String 
```
"Hello World!"
```
Different from json and xml, Strings parsed by Pidgey have no metadata about the 
values contained in the String. In most situations, this is a terrible drawback, but, 
for some specific situations, this String can be much shorter comparing to xml and json. 
That said, Pidgey can be used if bandwidth is critical.
