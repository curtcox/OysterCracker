package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oc1.util.Objects;

/**
 *
 * @author curt
 */
final class Args {

    static final class Parser 
        implements IParser
    {
        public Args parse(Tokens tokens) {
            tokens.verifyNextIs("(");
            List<String> args = new ArrayList<String>();
            while (!tokens.peekIs(")")) {
                args.add(tokens.next());
            }
            tokens.verifyNextIs(")");
            return new Args(args.toArray(new String[0]));
        }    

        public boolean canParse(Tokens tokens) {
            Tokens copy = tokens.copy();
            if (!copy.nextIs("(")) { return false; }
            while (!copy.peekIs(")")) {
                if (!copy.hasNext()) { return false;}
                String token = copy.next();
                if (!Identifier.isValid(token)) {
                    return false;
                }
            }
            return copy.nextIs(")");
        }
    }
    
    final String[] args;
    
    Args(String... args) {
        this.args = args;
    }
    
    @Override
    public int hashCode() {
        return Arrays.asList(args).hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        Args that = (Args) o;
        return Objects.areEqual(args, that.args);
    }
    
    @Override
    public String toString() {
        return Arrays.asList(args).toString();
    }
}
