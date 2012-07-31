package org.sprimaudi.zkutil.lookup;

import org.zkoss.zul.Window;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/29/12
 * Time: 9:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class LookupWindow<T> {
    private T selected;
    private Window window;

    public static final String LOOKUP_WINDOW_ATTR = "LOOKUP_WINDOW_ATTR";


    public LookupWindow() {
    }

    public LookupWindow(Window window) {
        window.setAttribute(LOOKUP_WINDOW_ATTR, this);
        this.window = window;
    }

    public static void setSelected(Object data, Window window) {
        LookupWindow lw = (LookupWindow) window.getAttribute(LOOKUP_WINDOW_ATTR);
        if (lw != null) {
            lw.setSelected(data);
        }

    }

    public static LookupWindow getLookupWindow(Window window) {
        return (LookupWindow) window.getAttribute(LOOKUP_WINDOW_ATTR);
    }

    public void doModal() {
        window.doModal();
    }


    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }
}
