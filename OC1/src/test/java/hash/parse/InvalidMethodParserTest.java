package hash.parse;

import config.ShouldRun;
import hash.Expression;
import hash.HashLines;
import hash.Method;
import hash.SyntaxError;
import hash.lex.Tokens;
import org.junit.Before;
import org.junit.Test;

import static hash.SyntaxError.Type.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class InvalidMethodParserTest {

    InvalidMethodParser testObject = new InvalidMethodParser();

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.Hash);
    }

    @Test
    public void canParseTokens_returns_false_when_there_are_no_tokens() {
        assertFalse(testObject.canParseTokens(Tokens.from("")));
    }

    @Test
    public void canParseTokens_returns_true_when_there_are_tokens() {
        assertTrue(testObject.canParseTokens(Tokens.from("x")));
    }
    
    @Test
    public void parse_method_body_with_syntax_error() {
        Method method = Method("foo",new SyntaxError("foo { ? }","{ ? }",INVALID_METHOD_BODY));
        parse("foo{?}",method);
    }

    @Test
    public void parse_method_body_with_multiple_method_invocations() {
        Method method = Method("f",new SyntaxError("f { g h }","{ g h }",INVALID_METHOD_BODY));
        parse("f{g h}",method);
    }

    @Test
    public void parse_method_body_with_multiple_method_invocations_and_parameters() {
        Method method = Method("f",new SyntaxError("f { g h ( 2 ) }","{ g h ( 2 ) }",INVALID_METHOD_BODY));
        parse("f{g h(2)}",method);
    }

    @Test
    public void parse_malformed_method() {
        Method method = Method("foo",new SyntaxError("foo }","foo }",MALFORMED_METHOD));
        parse("foo}",method);
    }

    @Test
    public void parse_method_params_with_syntax_error() {
        Method method = Method("foo",new SyntaxError("foo ( a , b ) { }", "( a , b )",INVALID_METHOD_PARAMS));
        parse("foo(a,b){}",method);
    }

    @Test
    public void parse_method_name_with_syntax_error() {
        Method method = Method("f?o",new SyntaxError("f ? o { }","f?o",INVALID_METHOD_NAME));
        parse("f?o{}",method);
    }
 
    private void parse(String original,Method expected) {
        Tokens tokens = lines(original);
        Method actual = testObject.parse(tokens);
        assertEquals(expected,actual);
        assertFalse(tokens.hasNext());
    }

    Method Method(String name,Expression expression) {
        return new Method(name,expression);
    }

    private Tokens lines(String...lines) {
        return Tokens.from(HashLines.from(lines));
    }

}
