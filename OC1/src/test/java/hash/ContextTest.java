package hash;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.util.Strings;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class ContextTest {

    String name = "foo";
    Expression value = new StringConstant("Galore");
    Args args = new Args(new Expression[] {value});
    NamedExpression invokable = new NamedExpression(name) {
        @Override
        public Object invoke(Object[] args) {
            return null;
        }
    };
    Context context = new Context("#",NamedExpression.namedValues(new Hash(),invokable));

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    @Test
    public void get_returns_named_invokable_from_context() {
        assertSame(invokable,context.get(name));
    }

    @Test
    public void get_returns_arg_value_before_constructor_value() {
        Context withArgValues = context
                .withArgValues("#",args)
                .withArgNames("invoked",new ArgNames("foo"));
        assertSame(value,withArgValues.get(name));
    }

    @Test
    public void toString_contains_arg_names() {
        Context testObject = new Context("#",new HashMap())
                .withArgValues("#",args)
                .withArgNames("invoked",new ArgNames("Kitty"));
        assertTrue(Strings.contains(testObject.toString(),"Kitty"));
    }

    @Test
    public void toString_contains_arg_values() {
        Context testObject = new Context("#",new HashMap())
                .withArgValues("#",args);
        assertTrue(Strings.contains(testObject.toString(),"Galore"));
    }

    @Test
    public void toString_contains_invokable_names() {
        Map<String,Expression> map = new HashMap<String,Expression>();
        map.put("Plenty",value);
        Context testObject = new Context("#",map);
        assertTrue(Strings.contains(testObject.toString(),"Plenty"));
    }

}
