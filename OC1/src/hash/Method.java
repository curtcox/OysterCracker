package hash;

import java.util.Arrays;
import oc1.util.Objects;

/**
 *
 * @author Curt
 */
public final class Method {

    static final class Parser
        implements IParser
    {
        final Expression.Parser expressions = new Expression.Parser();
        
        public Method parse(Tokens tokens) {
            String name = tokens.next();
            if (tokens.peekIs("(")) {
                tokens.next();
                tokens.next();
            }
            verify(tokens.next(),"{");
            if (tokens.hasNext() && tokens.peek().equals("}")) {
                return new Method(name);
            }
            Expression expression = expressions.parse(tokens);
            verify(tokens.next(),"}");
            return new Method(name,expression);
        }    
        
        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            if (!copy.hasNext() || !Identifier.isValid(copy.next())) {return false;}
            if (copy.peekIs("(")) {
                copy.next();
                copy.next();
            }
            if (!copy.nextIs("{"))                                   {return false;}
            if (copy.peekIs("}"))                                    {return true;}
            if (!expressions.canParse(copy))                         {return false;}
            expressions.parse(copy);
            return copy.nextIs("}");
        }

        private void verify(String actual, String expected) {
            if (!expected.equals(actual)) {
                throw new IllegalArgumentException(actual);
            }
        }

    }

    final String name;
    final Expression[] body;
    
    Method(String name,Expression...body) {
        this(name,new Args(),body);
    }

    Method(String name,Args params, Expression...body) {
        this.name = name;
        this.body = body;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Method that = (Method) o;
        return name.equals(that.name) &&
               Objects.areEqual(body,that.body);
    }
    
    @Override
    public String toString() {
        return name + Arrays.asList(body);
    }
}
