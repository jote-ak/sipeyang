package org.sprimaudi.zkcontroller.perencanaan;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/22/12
 * Time: 5:12 PM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class ObjectAuditController extends SelectorComposer<Window> {
    @Wire
    Window self;

    @Listen("onClick=#btnAddAuditee")
    public void addAuditee(Event evt) {
        Window w = (Window) Executions
                .createComponents("zuls/main/lookup.zul", null, null);
        w.setClosable(true);
        w.doModal();
        System.out.println("this should wait component to close");
    }
}
