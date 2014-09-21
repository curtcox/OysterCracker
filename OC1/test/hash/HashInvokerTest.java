package hash;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Curt
 */
public class HashInvokerTest {

    Parser parser = new Parser();

    @Test
    public void invoke_method_that_returns_constant() {
        Hash hash = hash("foo { ^ \"foo\" }");
        Object value = hash.invoke("foo",ArgNames(),Args(),Context(hash));
        assertEquals("foo",value);
    }

    @Test
    public void invoke_method_with_ternary() {
        Hash hash = hash(
            "layout           { ^ (portrait) ? layout_portrait : layout_landscape }",
            "layout_landscape { ^ \"Landscape!!\" }"
        );
        SimpleInvokable invokable = new SimpleInvokable("portrait") {
            public Object invoke(Object[] args) { return false; }
        };
        Object value = hash.invoke("layout",ArgNames(),Args(),Context(hash,invokable));
        assertEquals("Landscape!!",value);
    }
    
    @Test
    public void invoke_method_with_arguments() {
        Hash hash = hash(
            "button(text image to) {^ textAndImageLeadingTo(text image to) }"
        );
        SimpleInvokable invokable = new SimpleInvokable("textAndImageLeadingTo") {
            public Object invoke(Object[] args) {
                return "clickable(" + args[0] + ",img:" + args[1] + "," + args[2] + ")";
            }
        };
        Object value = hash.invoke("button",ArgNames("text","image","to"),Args(Const("exciting"),Const("beach"),Const("ftp:neato")),Context(hash,invokable));
        assertEquals("clickable(exciting,img:beach,ftp:neato)",value);
    }
    
    @Test
    public void invoke_nested_with_mixed_arguments() {
        Hash hash = hash(
            "layout     {^ screen( grid(2 1) provider navigation ) }",
            "provider   {^ \"Provider!\"}",
            "navigation {^ \"NAV\"}"
        );
        SimpleInvokable screen = new SimpleInvokable("screen") {
            public Object invoke(Object[] args) {
                return "screen(" + args[0] + " " + args[1] + " " + args[2] + ")";
            }
        };
        SimpleInvokable grid = new SimpleInvokable("grid") {
            public Object invoke(Object[] args) {
                return "grid(" + args[0] + " " + args[1] + ")";
            }
        };

        Object value = hash.invoke("layout",ArgNames(),Args(),Context(hash,screen,grid));
        assertEquals("screen(grid(2 1) Provider! NAV)",value);
    }
    
    private Hash hash(String...lines) {
        return parse(lines(lines));    
    }
    
    private ArgNames ArgNames(String... names) {
        return new ArgNames(names);
    }
    
    private Args Args(Expression...expressions) {
        return new Args(expressions);
    }
    
    private Context Context(Hash hash,SimpleInvokable... invokables) {
        return SimpleInvokable.newContext(hash,invokables);
    }

    private StringConstant Const(String string) {
        return new StringConstant(string);
    }
    
    private Hash parse(String original) {
        return parser.parse(original);
    }
    
    private String lines(String...lines) {
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line + " ");
        }
        return out.toString();
    }

}
