package fake;

import com.codename1.impl.CodenameOneImplementation;
import config.ShouldRun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class FakeCodenameOneImplementationTest {

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
    }

    @Test
    public void is_a_CodenameOneImplementation() {
        assertTrue(new FakeCodenameOneImplementation() instanceof CodenameOneImplementation);
    }
}
