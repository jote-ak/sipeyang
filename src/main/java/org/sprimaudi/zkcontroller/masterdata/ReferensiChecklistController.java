package org.sprimaudi.zkcontroller.masterdata;

import org.sprimaudi.zkutil.ReferensiUtil;
import org.zkoss.spring.DelegatingVariableResolver;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Window;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/31/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class ReferensiChecklistController extends SelectorComposer<Window> {
    public static final String zulpath = "zuls/masterdata/checklist_referensi.zul";

    @WireVariable
    ReferensiUtil referensiUtil;

    @Wire
    Combobox txtKategori;


    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate(Event evt) {
        referensiUtil.fillCombo(txtKategori);
    }
}
