package mach;

import x.util.Objects;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A method invocation with parameters.
 */
final class Invocation {

    final Object proxy;
    final Method method;
    final List<Object> args = new ArrayList();
    final List<Object> wildcards = new ArrayList();

    public Invocation(Object proxy, Method method, Object[] args, Object[] wildcards) {
        this.proxy = proxy;
        this.method = method;
        if (args!=null) {
            this.args.addAll(Arrays.asList(args));
        }
        if (wildcards!=null) {
            this.wildcards.addAll(Arrays.asList(wildcards));
        }
        if (wildcards!=null && this.args.size()!=this.wildcards.size()) {
            throw new IllegalArgumentException("Must have the same number of arguments and wildcards");
        }
    }

    @Override
    public boolean equals(Object o) {
        Invocation that = (Invocation) o;
        return proxy==that.proxy && method.equals(that.method) && sameArgsAs(that);
    }

    private boolean sameArgsAs(Invocation that) {
        return argCountsMatch(that) && argValuesMatch(that);
    }

    private boolean argCountsMatch(Invocation that) {
        return args.size()==that.args.size();
    }

    private boolean argValuesMatch(Invocation that) {
        return args.equals(that.args) || sameWithWildcardMatches(that);
    }

    private boolean sameWithWildcardMatches(Invocation that) {
        for (int i=0; i<args.size(); i++) {
            if (!isWild(i) && !that.isWild(i) && !args.get(i).equals(that.args.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isWild(int i) {
        return wildcards.size() > i && Objects.areEqual(args.get(i),wildcards.get(i));
    }

    public boolean returnsVoid() {
        return method.getReturnType().equals(void.class);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return method.toString() + args + wildcards;
    }

}
