package x.event;

import x.util.CollectionAsList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class XLiveList<E>
    implements LiveList<E>
{
    private final List<E> list;
    private Change.Listener listener;

    private XLiveList(List list) {
        this.list = list;
    }

    public static XLiveList of(List list) {
        return new XLiveList(list);
    }

    public static XLiveList of(Collection collection) {
        return of(new CollectionAsList(collection));
    }

    @Override
    public void addListener(Change.Listener listener) {
        this.listener = listener;
    }

    @Override
    public E get(int index) {
        if (index<0) {
            return null;
        }
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean add(E object) {
        boolean added = list.add(object);
        fireChangeEvent();
        return added;
    }

    public void fireChangeEvent() {
        if (listener!=null) {
            listener.onChange();
        }
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public Iterator iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }
    // ----------------------- Never
    private RuntimeException unsupported() {
        return new RuntimeException("Not supported yet.");
    }

    public boolean                       isEmpty() { throw unsupported(); }
    public boolean         contains(Object object) { throw unsupported(); }
    public Object[]        toArray(Object[] array) { throw unsupported(); }
    public boolean           remove(Object object) { throw unsupported(); }
    public void                            clear() { throw unsupported(); }
    public E set(int location, E object) { throw unsupported(); }
    public void   add(int location, E object) { throw unsupported(); }
    public E             remove(int location) { throw unsupported(); }
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
    
}
