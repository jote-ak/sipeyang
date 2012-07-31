package org.sprimaudi.zkutil;

import org.zkoss.zk.ui.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/31/12
 * Time: 3:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class ComponentUtil {
    private static <T extends Component> List<T> find(Class<T> clazz, Component parent, int depth, boolean exitOnFirst, boolean easyFind) {
        List<T> comps = new ArrayList<T>();
        int nextDepth = depth - 1;
        List<Component> children = parent.getChildren();
        for (Iterator<Component> iterator = children.iterator(); iterator.hasNext(); ) {
            Component child = iterator.next();
            if (child.getClass().getName().equals(clazz.getName())) {
                comps.add((T) child);
                if (exitOnFirst) {
                    return comps;
                }
                if (easyFind) {
                    nextDepth = 0;//
                }
            }
            if (nextDepth > 0) {
                List<T> chils = find(clazz, child, nextDepth, exitOnFirst, easyFind);
                comps.addAll(chils);
                if (chils.size() > 0 && exitOnFirst)
                    return comps;
            }
        }
        return comps;
    }

    public static <T extends Component> List<T> find(Class<T> clazz, Component parent, int depth) {
        return find(clazz, parent, depth, false, false);
    }

    public static <T extends Component> List<T> easyFind(Class<T> clazz, Component parent, int depth) {
        return find(clazz, parent, depth, false, true);
    }

    public static <T extends Component> T findFirst(Class<T> clazz, Component parent, int depth) {
        List<T> t = find(clazz, parent, depth, true, false);
        if (t.size() > 0)
            return t.get(0);
        return null;
    }
}
