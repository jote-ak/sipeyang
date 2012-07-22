package org.sprimaudi.zkcontroller.perencanaan;

import org.sprimaudi.zkspring.util.PageMgt;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/19/12
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class BrowseHeaderController extends SelectorComposer<Window> {

    @WireVariable
    PageMgt pgm;



    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
        System.out.println("prepare to render");
        System.out.println("pgm " + pgm);

    }



    @Listen("onClick=#lstHeaderPerencanaan")
    public void onListHeaderClick() {
        pgm.showMainOnly("zuls/perencanaan/draft_header.zul");
    }
//    @Listen("onAfterCreate=window")
//    public void onAfterCreate() {
//        pgm.showMainOnly("zuls/perencanaan/draft_header");
//    }
}
