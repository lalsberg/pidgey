# pidgey
Pidgey is a Java annotation-based parser between Objects and Strings.
Pidgey uses specified positions to insert the value of the field in the String.

For example:

Parsing the class 
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

