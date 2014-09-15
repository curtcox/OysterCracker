package hash;

/**
 *
 * @author Curt
 */
public final class Return
    extends Expression
{
    static final class Parser
        extends AbstractParser
    {
        public Return parse(Tokens tokens) {
            verifyReturn(tokens.next());
            return new Return(new Expression.Parser().parse(tokens));
        }    

        private void verifyReturn(String string) {
            if (!string.equals("^")) {
                throw new IllegalArgumentException();
            }
        }

        public boolean canParseTokens(Tokens tokens) {
            return tokens.nextIs("^");
        }
    }
    
    final Expression expression;
    
    Return(Expression expression) {
        this.expression = expression;
    }
    
    public Object invokeIn(Context context) {
        return null;
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Return that = (Return) o;
        return expression.equals(that.expression);
    }
    
    @Override
    public String toString() {
        return "^" + expression;
    }
}
