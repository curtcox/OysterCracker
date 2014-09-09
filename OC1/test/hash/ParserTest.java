package hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class ParserTest {

    Parser testObject = new Parser();

    @Test
    public void parse_hash_with_return_foo() {
        Hash hash = Hash(Method("foo", Return(Constant("foo"))));
        parse("foo { ^ \"foo\" }",hash);
    }

    @Test
    public void parse_hash_with_get_layout() {
        Hash hash = Hash(Method(
            "getLayout",
            Return(Ternary(Invocation("portrait"),Invocation("layoutForPortrait"),Invocation("layoutForLandscape"))
        )));
        parse(
            lines(
                "getLayout() {",
	            "^ (portrait) ? layoutForPortrait() : layoutForLandscape()",
                "}"),
            hash
        );
    }
    
    @Test
    public void parse_hash_method_definition_with_arguments() {
//buttonTo(text,image,leadingTo) {
//    ^ textAndImageLeadingTo(text,image,leadingTo);
//}
        fail();
    }
    
    @Test
    public void parse_hash_ivocation_with_arguments() {
//layoutForPortraitWithSelectedProvider() {
//    ^ Screen(Grid(2,1), newProviderContainer(), newNavigationContainer());
//}
        fail();
    }
    
    Hash Hash(Method...methods) {
        return new Hash(methods);
    }
   
    Method Method(String name,Expression...expressions) {
        return new Method(name,expressions);
    }
    
    Return Return(Expression expression) {
        return new Return(expression);
    }

    Ternary Ternary(Expression condition, Expression pass, Expression fail) {
        return new Ternary(condition,pass,fail);
    }

    Invocation Invocation(String text) {
        return new Invocation(text);
    }

    Constant Constant(String text) {
        return new Constant(text);
    }
    
    private void parse(String original,Hash expected) {
        Hash actual = testObject.parse(original);
        assertEquals(expected,actual);
    }
    
    private String lines(String...lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line + " ");
        }
        return out.toString();
    }
}