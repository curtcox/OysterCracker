package se.views.editor;

import config.ShouldRun;
import fake.FakeSERegistryLoader;
import org.junit.Before;
import org.junit.Test;
import se.events.Events;
import se.events.ViewObjectEvent;
import se.frame.FrameMeta;
import se.frame.JavaSourceCodeLookup;
import se.frame.SEJavaSourceCodeLookup;
import x.app.Registry;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class SEObjectGraphViewerTest {

    Events events = new Events();
    Object target = new Object();
    SEObjectGraphViewer viewer;

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.JavaSE_UI);
        FakeSERegistryLoader.load();
        Registry.put(JavaSourceCodeLookup.class,new SEJavaSourceCodeLookup());
        Registry.put(Events.class,events);
        viewer = new SEObjectGraphViewer();
    }

    @Test
    public void can_create() {
        assertNotNull(SEObjectGraphViewer.of());
    }

    @Test
    public void is_singleton() {
        assertSame(SEObjectGraphViewer.of(), SEObjectGraphViewer.of());
    }

    @Test
    public void register_registers_for_view_object_events() {
        viewer.register();
        Collection<Events.Listener> listeners = Registry.get(Events.class).getListenersFor(ViewObjectEvent.class);
        assertTrue(listeners.contains(viewer));
    }

    @Test
    public void posting_a_view_command_event_causes_target_to_be_viewed_when_registered() {
        post();

        assertEquals(target, viewer.model.get());
    }

    @Test
    public void frame_has_appropriate_meta() {
        FrameMeta meta = viewer.frame.meta;
        assertEquals("For examining how an object relates to other objects.",meta.what_its_for);
        assertEquals("Select objects of interest.",meta.how_to_use_it);
        assertEquals(SEObjectGraphViewer.class.getName().toString(),meta.source_code_location);
    }

    @Test
    public void view_makes_the_viewing_frame_visible() {
        viewer.view(target);

        assertTrue(viewer.frame.isVisible());
    }

    @Test
    public void view_sets_the_ObjectGraphModel_target() {
        viewer.view(target);

        assertSame(target,viewer.model.get());
    }

    @Test
    public void view_sets_the_targetLabel_object() {
        viewer.view(target);

        assertSame(target,viewer.targetLabel.get());
    }

    void post() {
        viewer.register();
        events.post(new ViewObjectEvent(target));
    }
}