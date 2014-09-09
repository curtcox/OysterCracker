package hash;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Curt
 */
public class TokensTest {
    
    @Test
    public void from_creates_empty_tokens_for_empty_string() {
        Tokens tokens = Tokens.from("");
        assertFalse(tokens.hasNext());
    }

    @Test
    public void from_creates_token_with_one_element_for_one_element() {
        Tokens tokens = Tokens.from("one");
        assertTrue(tokens.hasNext());
        assertEquals("one",tokens.next());
        assertFalse(tokens.hasNext());
    }

    @Test
    public void peek_returns_one_element_without_removing_it() {
        Tokens tokens = Tokens.from("one");
        assertTrue(tokens.hasNext());
        assertEquals("one",tokens.peek());
        assertTrue(tokens.hasNext());
    }

    @Test
    public void copy_produces_equivalent_tokens() {
        Tokens tokens = Tokens.from("one");
        Tokens copy = tokens.copy();
        
        assertTrue(copy.hasNext());
        assertEquals("one",copy.next());
        assertFalse(copy.hasNext());
    }

    @Test
    public void changes_to_copy_do_not_change_original() {
        Tokens tokens = Tokens.from("one");

        Tokens copy = tokens.copy();
        copy.next();
        
        assertTrue(tokens.hasNext());
        assertEquals("one",tokens.next());
        assertFalse(tokens.hasNext());
    }

}
