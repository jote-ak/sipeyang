package org.sprimaudi.zkcontroller.perencanaan;

import org.sprimaudi.zkutil.lookup.LookupUtil;
import org.sprimaudi.zkutil.lookuper.UnitLookuper;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
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
    @WireVariable
    LookupUtil lookupUtil;

    @WireVariable
    UnitLookuper unitLookuper;

    @Listen("onClick=#btnAddAuditee")
    public void addAuditee(Event evt) {
        Window w = unitLookuper.showLookup();
        w.doModal();
    }
}
