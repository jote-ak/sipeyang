package org.sprimaudi.zkcontroller.main;

import org.sprimaudi.zkspring.util.PageMgt;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/15/12
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class MainController extends SelectorComposer<Window> {
    @WireVariable
    PageMgt pgm;
    @Wire("#mainWin > include")
    Include mainInclude;
    @Wire
    Include subInclude, navInclude, propInclude;
    @Wire
    South subPanel;
    @Wire
    East propPanel;
    @Wire
    West navPanel;
    @Wire
    Center mainPanel;
    @Wire("window")
    Window self;


    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
        if (pgm != null) {
            pgm.initInclude(mainInclude, subInclude, navInclude, propInclude);
            pgm.initPanel(mainPanel, subPanel, navPanel, propPanel);
            pgm.initWindow(self);
        }
    }

    @Listen("onClick=#btnTes")
    public void klikTes() {

    }

}
