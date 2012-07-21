package org.sprimaudi.zkcontroller.perencanaan;

import org.sprimaudi.zkspring.util.PageMgt;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 5:20 AM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class HeaderController extends SelectorComposer<Window> {
    @WireVariable
    PageMgt pgm;

    @Wire
    Listbox lstItemPerencanaan;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Listen("onSelect=#lstItemPerencanaan")
    public void onListSelect(Event evt) {
        pgm.showNav("zuls/perencanaan/draft_header_sider.zul");
        pgm.showProp("zuls/perencanaan/browse_draft_detail_sider.zul");
        pgm.showMain("zuls/perencanaan/draft_detail.zul");
    }
}
