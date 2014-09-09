package hash;

import oc1.util.Strings;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class MethodTest {
    
    @Test
    public void equals_returns_true_for_methods_with_the_same_values() {
        assertEquals(new Method(""),new Method(""));
        assertEquals(new Method("a"),new Method("a"));
        assertEquals(new Method("b",new Constant("!")),new Method("b",new Constant("!")));
    }

    @Test
    public void equals_returns_false_for_methods_with_different_values() {
        assertNotEquals(new Method("a"),new Method("b"));
        assertNotEquals(new Method("b",new Constant("!")),new Method("b",new Constant("?")));
    }

    private void assertEquals(Method a, Method b) {
        Assert.assertEquals(a,b);
        Assert.assertEquals(b,a);
        assertTrue(a.hashCode()==b.hashCode());
    }

    private void assertNotEquals(Method a, Method b) {
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
    @Test
    public void canParse_empty_method() {
        assertTrue(canParse("x{}"));
    }

    @Test
    public void canParse_method_with_constant() {
        assertTrue(canParse("x{ \"word\" }"));
    }

    @Test
    public void canParse_method_with_invocation() {
        assertTrue(canParse("x{ stuff }"));
    }
    
    @Test
    public void parse_returns_correct_value_for_empty_method() {
        parse(new Method("x"),"x{}");
    }

    @Test
    public void parse_returns_correct_value_for_method_with_constant() {
        parse(new Method("x",new Constant("word")),"x{ \"word\" }");
    }

    @Test
    public void parse_returns_correct_value_for_method_with_invocation() {
        parse(new Method("x",new Invocation("stuff")),"x{ stuff }");
    }

    @Test
    public void parse_returns_correct_value_for_method_with_return() {
        parse(new Method("x",new Return(new Constant("word"))),"x{ ^ \"word\" }");
    }

    private void parse(Method method,String string) {
        assertEquals(method,new Method.Parser().parse(Tokens.from(string)));
    }

    private boolean canParse(String string) {
        return new Method.Parser().canParse(Tokens.from(string));
    }
    
    @Test
    public void toString_contains_name() {
        assertTrue(Strings.contains(new Method("nuts").toString(),"nuts"));
    }

    @Test
    public void toString_contains_expressions() {
        String string = new Method("tinker",new Invocation("evars")).toString();
        assertTrue(string,Strings.contains(string,"evars"));
    }

}