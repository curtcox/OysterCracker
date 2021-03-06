package c1.event;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.util.EventDispatcher;
import x.event.Change;
import x.event.SwappableList;

import java.util.*;

public final class C1SwappableList
    implements SwappableList
{
    private List list = new ArrayList();
    private final EventDispatcher dataListeners = new EventDispatcher();

    public C1SwappableList() {}
    public C1SwappableList(List list) {
        become(list);
    }

    public void become(List list) {
        this.list = list;
        fireAllDataChanged();
    }
    
    private void fireAllDataChanged() {
        dataChanged(DataChangedListener.CHANGED,0);
    }

    public void addListener(Change.Listener listener) {
        dataListeners.addListener(listener);
    }

    public void removeListener(Change.Listener listener) {
        dataListeners.removeListener(listener);
    }
    
    public void dataChanged(int type, int index) {
        dataListeners.fireDataChangeEvent(index, type);
    }

    public Object get(int index) {
        if (index<0) {
            return null;
        }
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean                       isEmpty() { throw unsupported(); }
    public boolean         contains(Object object) { throw unsupported(); }
    public Iterator                     iterator() { throw unsupported(); }
    public Object[]                      toArray() { throw unsupported(); }
    public Object[]        toArray(Object[] array) { throw unsupported(); }
    public boolean              add(Object object) { throw unsupported(); }
    public boolean           remove(Object object) { throw unsupported(); }
    public void                            clear() { throw unsupported(); }
    public Object set(int location, Object object) { throw unsupported(); }
    public void   add(int location, Object object) { throw unsupported(); }
    public Object             remove(int location) { throw unsupported(); }
    public int              indexOf(Object object) { throw unsupported(); }
    public int          lastIndexOf(Object object) { throw unsupported(); }
    public ListIterator             listIterator() { throw unsupported(); }
    public ListIterator listIterator(int location) { throw unsupported(); }
    public List        subList(int start, int end) { throw unsupported(); }
    public boolean          containsAll(Collection collection) { throw unsupported(); }
    public boolean               addAll(Collection collection) { throw unsupported(); }
    public boolean addAll(int location, Collection collection) { throw unsupported(); }
    public boolean            removeAll(Collection collection) { throw unsupported(); }
    public boolean            retainAll(Collection collection) { throw unsupported(); }
    
    private RuntimeException unsupported() {
        return new RuntimeException("Not supported yet.");
    }

}
