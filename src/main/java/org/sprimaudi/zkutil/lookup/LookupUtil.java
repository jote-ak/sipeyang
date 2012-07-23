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
public abstract class LookupUtil<T> implements ListitemRenderer<T> {
    private T selected;
    private Listbox lbx;
    private Button btnCari;
    private Textbox txtCari;


    public Window showLookup() {
        Window w = (Window) Executions.createComponents("zuls/main/lookup.zul", null, null);
        lbx = (Listbox) w.getFellowIfAny("lstLookup");
        txtCari = (Textbox) w.getFellowIfAny("txtCari");
        btnCari = (Button) w.getFellowIfAny("lookupSearch");
        lbx.setItemRenderer(this);

        List<LookupColumn> lcs = Arrays.asList(getColumns());
        while (lcs.iterator().hasNext()) {
            LookupColumn next = lcs.iterator().next();
            (new Listheader(next.getLabel(), null, next.getWidth())).setParent(lbx.getListhead());

        }

        lbx.addEventListener("onSelect", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                if (lbx != null && lbx.getSelectedItem() != null)
                    selected = lbx.getSelectedItem().getValue();
            }
        });
        btnCari.addEventListener("onClick", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                List<T> model = getModel(txtCari.getText());
                lbx.setModel(new ListModelList<T>(model));
            }
        });
        (new Listheader()).setParent(lbx.getListhead());

        w.setClosable(true);
        return w;
    }

    public abstract List<T> getModel(String lookupParams);

    public abstract LookupColumn[] getColumns();

    public T getSelected() {
        return selected;
    }

}
