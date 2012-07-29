package org.sprimaudi.zkcontroller.main;

import org.sprimaudi.zkspring.util.PageMgt;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

/**
 * Created by IntelliJ IDEA.
 * Sebagai Dispatch Global Event yang di route melalui main window oleh pageManager
 * User: jote
 * Date: 7/27/12
 * Time: 10:12 PM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class EventBus extends SelectorComposer<Window> {
    @Wire("window")
    Window self;

    @WireVariable
    PageMgt pgm;

    @Listen("onShowObjectAudit=window")
    public void onShowObjectAudit(Event evt) {
        System.out.println("This is event bus");
        System.out.println(pgm.forward(evt));
        pgm.showMain("zuls/perencanaan/draft_detail.zul", pgm.forward(evt));
        pgm.showNav("zuls/perencanaan/draft_header_sider.zul", pgm.forward(evt));
        pgm.showProp("zuls/perencanaan/browse_draft_detail_sider.zul", pgm.forward(evt));
    }

}
