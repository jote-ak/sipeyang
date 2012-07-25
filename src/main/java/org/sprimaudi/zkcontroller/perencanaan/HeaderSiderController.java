package org.sprimaudi.zkcontroller.perencanaan;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/24/12
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderSiderController extends SelectorComposer<Window> {
    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate(Event evt) {


    }

}
