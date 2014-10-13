package hash;

import hash.lex.Tokens;

/**
 *
 * @author Curt
 */
final class Ternary
    implements Expression
{

    static final class Parser
        extends AbstractParser
    {
        
        public Ternary parse(Tokens tokens) {
            tokens.verifyNextIs("(");
            ExpressionParser expressions = new ExpressionParser();
            Expression condition = expressions.parse(tokens);
            tokens.verifyNextIs(")");
            tokens.verifyNextIs("?");
            Expression pass = expressions.parse(tokens);
            tokens.verifyNextIs(":");
            Expression fail = expressions.parse(tokens);
            return new Ternary(condition,pass,fail);
        }    

        public boolean canParseTokens(Tokens tokens) {
            if (!tokens.nextIs("("))           { return false; }
            ExpressionParser expressions = new ExpressionParser();
            if (!expressions.canParse(tokens)) { return false; }
            expressions.parse(tokens);
            if (!tokens.nextIs(")"))           { return false; }
            if (!tokens.nextIs("?"))           { return false; }
            if (!expressions.canParse(tokens)) { return false; }
            expressions.parse(tokens);
            if (!tokens.nextIs(":"))           { return false; }
            return expressions.canParse(tokens);
        }
    }
    
    final Expression condition;
    final Expression pass;
    final Expression fail;
    
    Ternary(Expression condition, Expression pass, Expression fail) {
        this.condition = condition;
        this.pass = pass;
        this.fail = fail;
    }
    
    public Object invokeIn(Context context) {
        return (Boolean) condition.invokeIn(context)
               ? pass.invokeIn(context) 
               : fail.invokeIn(context);
    }

    @Override
    public int hashCode() {
        return condition.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Ternary that = (Ternary) o;
        return condition.equals(that.condition) &&
               pass.equals(that.pass) &&
               fail.equals(that.fail);
    }

    @Override
    public String toString() {
        return condition.toString();
    }
}
