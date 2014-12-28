package common.screen.dynamic;

import common.ui.UIGridLayout;
import common.ui.UILabel;
import common.ui.UILayout;
import fake.FakeRegistryLoader;
import hash.NamedExpression;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DynamicScreenLayoutMethodsTest {
    
    DynamicScreenLayoutMethods testObject = new DynamicScreenLayoutMethods();
    
    @Before
    public void setUp() {
        FakeRegistryLoader.load();
    }

    @Test
    public void get_grid() {
        Object value = testObject.get("grid");
        
        NamedExpression expression = (NamedExpression) value;
        UILayout screen = (UILayout) expression.invoke(new Object[] {4,2,"red","green"});
        UIGridLayout grid = (UIGridLayout) screen;
        assertEquals(4,grid.rows);
        assertEquals(2,grid.columns);
        UILabel red = (UILabel) screen.components[0];
        UILabel green = (UILabel) screen.components[1];
        assertEquals("red",red.text);
        assertEquals("green",green.text);
    }

    @Test
    public void get_table() {
        Object value = testObject.get("table");
        
        NamedExpression expression = (NamedExpression) value;
        UILayout screen = (UILayout) expression.invoke(new Object[] {4,2,"red","green"});
        UIGridLayout grid = (UIGridLayout) screen;
        assertEquals(4,grid.rows);
        assertEquals(2,grid.columns);
        UILabel red = (UILabel) screen.components[0];
        UILabel green = (UILabel) screen.components[1];
        assertEquals("red",red.text);
        assertEquals("green",green.text);
    }

}
