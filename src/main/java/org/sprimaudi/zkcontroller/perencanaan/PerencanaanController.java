package org.sprimaudi.zkcontroller.perencanaan;

import org.zkoss.lang.Threads;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/17/12
 * Time: 5:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class PerencanaanController extends SelectorComposer<Window> {
    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.

    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate(Event evt) {
        System.out.println("Event from annotated event");
        System.out.println(evt);
        System.out.println("before perencanaan on after create, think about heavy load");
        Threads.sleep(4000);
        System.out.println("after perencanaan on after creation, heavy load done");

    }
}
