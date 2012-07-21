package org.sprimaudi.zkspring.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/17/12
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
@Component(value = "pgm")
@Scope("desktop")
public class PageMgt {

    public Include mainInclude;
    public Include subInclude;
    public Include navInclude;
    public Include propInclude;

    public Center mainPanel;
    public South subPanel;
    public West navPanel;
    public East propPanel;


    public String tesBean() {
        return "dektop scope bean " + UUID.randomUUID().toString();
    }

    public void initInclude(Include main, Include sub, Include nav, Include prop) {
        this.mainInclude = main;
        this.subInclude = sub;
        this.navInclude = nav;
        this.propInclude = prop;
    }

    public void initPanel(Center mainPanel, South subPanel, West navPanel, East propPanel) {
        this.mainPanel = mainPanel;
        this.subPanel = subPanel;
        this.navPanel = navPanel;
        this.propPanel = propPanel;
    }


    public void closePanels() {
        this.subPanel.setOpen(false);
        this.navPanel.setOpen(false);
        this.propPanel.setOpen(false);
    }

    public void showMain(String pagePath, Map<String, Object> arg) {
        System.out.println("this hashcode: " + this.toString());
        if (mainInclude != null) {
            mainInclude.setSrc(pagePath);
        }
    }

    public void showMainOnly(String pagePath) {
        showPage(mainInclude, pagePath, false, mainPanel, true);
    }

    public void showMain(String pagePath) {
        showPage(mainInclude, pagePath, false, mainPanel, false);
    }

    public void showNav(String pagePath) {
        showPage(navInclude, pagePath, false, navPanel, false);
    }

    public void showProp(String pagePath) {
        showPage(propInclude, pagePath, false, propPanel, false);
    }

    public void showPage(Include icl, String pagePath,
                         boolean progressing, LayoutRegion region, boolean closeOther) {
        icl.setProgressing(progressing);
        if (closeOther) {
            closePanels();
        }
        icl.setSrc(pagePath);
        Window win = findChildWindow(icl);
        postOnAfterCreate(win);
        if (!(region instanceof Center)) {
            region.setOpen(true);
        }

    }

    private Window findChildWindow(Include include) {
        Collection<org.zkoss.zk.ui.Component> childs = include.getFellows();
        for (org.zkoss.zk.ui.Component child : childs) {
            if (child instanceof Window) {
                return (Window) child;
            }
        }
        return null;
    }

    private void postOnAfterCreate(Window win) {
        if (win != null) {
            Events.echoEvent("onAfterCreate", win, "Data After Create");
        }
    }
}
