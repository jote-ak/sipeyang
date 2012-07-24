package org.sprimaudi.zkutil.lookup;

import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/23/12
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Component(value = "lookupUtil")
public abstract class LookupUtil<T, L> implements ListitemRenderer<T> {
    private T selected;
    private Listbox lbx;
    private Button btnLookupClose, btnLookupCari, btnLookupCancel;
    private Textbox txtCari, txtTarget;
    private final static String TXT_DISPLAY = "textlookupdisplay";
    private final static String TXT_KEY = "textlookupkey";


    public Window showLookup() {
        return showLookup(null);

    }

    public void storeValue(Textbox text) {
        if (text != null) {
            text.setAttribute(TXT_KEY, getKey(selected));
        }
    }

    public T getValue(Textbox text) {
        if (text != null) {
            Object okey = text.getAttribute(TXT_KEY);
            if (okey == null) {
                //no key stored here
                return null;
            } else {
                L key = (L) okey;
                return getById(key);
            }
        }
        return null;
    }

    public Window showLookup(Textbox textbox) {
        final Window w = (Window) Executions.createComponents("zuls/main/lookup.zul", null, null);
        lbx = (Listbox) w.getFellowIfAny("lstLookup");
        txtCari = (Textbox) w.getFellowIfAny("txtLookupCari");
        btnLookupCari = (Button) w.getFellowIfAny("btnLookupCari");
        btnLookupClose = (Button) w.getFellowIfAny("btnLookupClose");
        btnLookupCancel = (Button) w.getFellowIfAny("btnLookupCancel");
        lbx.setItemRenderer(this);
        List<LookupColumn> lcs = Arrays.asList(getColumns());
        System.out.println("jumlah yang terdaftar" + lcs.size());
        for (LookupColumn lc : lcs) {
            Listheader lhr = new Listheader(lc.getLabel(), "", lc.getWidth());
            lhr.setParent(lbx.getListhead());
        }
        //attach text reference into listbox
        lbx.setAttribute(TXT_DISPLAY, textbox);
        lbx.addEventListener("onSelect", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                if (lbx != null && lbx.getSelectedItem() != null) {
                    System.out.println("this is when on select");
                    selected = (T) lbx.getSelectedItem().getValue();
                    Object oText = lbx.getAttribute(TXT_DISPLAY);
                    if (oText != null && oText instanceof Textbox) {
                        Textbox txt = (Textbox) oText;
                        txt.setText(getDisplayer(selected));

                        txt.setAttribute(TXT_KEY, getKey(selected));
                    }
                }

            }
        });
        btnLookupCari.addEventListener("onClick", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                List<T> model = getModel(txtCari.getText());
                lbx.setModel(new ListModelList<T>(model));
            }
        });
        btnLookupClose.addEventListener("onClick", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                w.detach();
            }
        });
        w.setClosable(true);
        return w;
    }

    public abstract void rendering(Listitem listitem, T t, int i) throws Exception;

    @Override
    public final void render(Listitem listitem, T t, int i) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.  ]
        listitem.setValue(t);
        rendering(listitem, t, i);
    }

    /**
     * Please overid this method to be displayed as getDescription of the object
     *
     * @param data
     * @return
     */
    public String getDescription(T data) {
        return "";
    }

    public abstract String getDisplayer(T data);

    public abstract T getById(L id);

    public abstract L getKey(T data);

    public abstract List<T> getModel(String lookupParams);

    public abstract LookupColumn[] getColumns();

    public T getSelected() {
        return selected;
    }

}
