package x.parse;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;
import x.parse.Tokenizer;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class TokenizerTest {

    @Before
    public void setUp() {
        Assume.assumeTrue(ShouldRun.Hash);
    }

    @Test
    public void tokenize_returns_right_counts_for_all_delimiters() {
        Assert.assertEquals(1, tokenize("=", "=").length);
        Assert.assertEquals(1, tokenize(" ", " ").length);
        Assert.assertEquals(2, tokenize("  ", " ").length);
        Assert.assertEquals(2, tokenize(" =", " ", "=").length);
        Assert.assertEquals(3, tokenize("   ", " ").length);
        Assert.assertEquals(4, tokenize("    ", " ").length);
    }

    @Test
    public void tokenize_returns_right_counts_for_no_delimiters() {
        Assert.assertEquals(0, tokenize("").length);
        Assert.assertEquals(1, tokenize("xyz", "=").length);
        Assert.assertEquals(1, tokenize("x=y", " ").length);
    }

    @Test
    public void tokenize_returns_right_values_for_x_equals_y() {
        Assert.assertEquals("x", tokenize("x=y", "=")[0]);
        Assert.assertEquals("=", tokenize("x=y", "=")[1]);
        Assert.assertEquals("y", tokenize("x=y", "=")[2]);
    }

    @Test
    public void tokenize_returns_right_values_for_key_equals_value() {
        Assert.assertEquals("name", tokenize("name=y", "=")[0]);
        Assert.assertEquals("=", tokenize("name=y", "=")[1]);
        Assert.assertEquals("value", tokenize("x=value", "=")[2]);
    }

    @Test
    public void tokenize_returns_right_values_for_x_y_z() {
        Assert.assertEquals("x", tokenize("x y z", " ")[0]);
        Assert.assertEquals(" ", tokenize("x y z", " ")[1]);
        Assert.assertEquals("y", tokenize("x y z", " ")[2]);
        Assert.assertEquals(" ", tokenize("x y z", " ")[3]);
        Assert.assertEquals("z", tokenize("x y z", " ")[4]);
    }

    @Test
    public void tokenize_returns_right_values_when_given_multiple_delimiters() {
        Assert.assertEquals("x", tokenize("x y:z", ":", " ")[0]);
        Assert.assertEquals(" ", tokenize("x y:z", ":", " ")[1]);
        Assert.assertEquals("y", tokenize("x y:z", ":", " ")[2]);
        Assert.assertEquals(":", tokenize("x y:z", ":", " ")[3]);
        Assert.assertEquals("z", tokenize("x y:z", ":", " ")[4]);
    }

    private String[] tokenize(String string,String... tokens) {
        return Tokenizer.tokenize(string, tokens);
    }
}
