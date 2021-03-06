package c1.screen;


import config.ShouldRun;
import fake.FakeC1UI;
import x.page.Page;
import x.screen.Screen;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;
import fake.FakeC1RegistryLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

public class C1ScreenTest {

    PageLink link = PageLink.of("");
    Page page = new Page(link) {
        @Override
        public XComponent layoutForPortrait() {
            return null;
        }
    };
    IForm form;
    Screen previous;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.CodenameOne);
        FakeC1RegistryLoader.load();
    }

    @Test
    public void can_create() {
        Screen.of(page);
    }
    
    @Test
    public void can_create_with_form() throws Exception {
        assertNotNull(createScreenOnEDT(previous));
    }
    
    private Screen createScreenOnEDT(final Screen previous) throws Exception {
        return (Screen) FakeC1UI.onEDT(new Callable(){
            public Object call() throws Exception {
                form = FakeC1UI.newForm();
                return Screen.of(page);
            }
        });
    }


}
