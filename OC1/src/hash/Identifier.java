package hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Curt
 */
final class Identifier {

    static final String[] SPECIAL = new String[] {
        " ", ".", "?", ":", ",", "\"", "^", "{", "}", "(", ")", "^"
    };
    
    private static final Set<String> reject = new HashSet(Arrays.asList(SPECIAL));
    static boolean isValid(String value) {
        for (int i=0; i<value.length(); i++) {
            String c = value.substring(i, i+1);
            if (reject.contains(c)) {
                return false;
            }
        }
        return true;
    }

}