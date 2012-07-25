package org.sprimaudi.zkspring.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
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
    private static final String WINDOW_PARAM_ATTR = "window_param_attr";
    private static final String INCLUDE_PARAM_ATTR = "include_param_attr";


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
        showPage(mainInclude, pagePath, false, mainPanel, false, arg);
    }

    public void showMainOnly(String pagePath, Map<String, Object> arg) {

        showPage(mainInclude, pagePath, false, mainPanel, true, arg);
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

    public void showNav(String pagePath, Map<String, Object> arg) {
        showPage(navInclude, pagePath, false, navPanel, false, arg);
    }

    public void showProp(String pagePath) {
        showPage(propInclude, pagePath, false, propPanel, false);
    }

    public void showProp(String pagePath, Map<String, Object> arg) {
        showPage(propInclude, pagePath, false, propPanel, false, arg);
    }

    public void showPage(Include icl,
                         String pagePath,
                         boolean progressing,
                         LayoutRegion region,
                         boolean closeOther) {
        showPage(icl, pagePath, progressing, region, closeOther, null);
    }

    public void showPage(Include icl,
                         String pagePath,
                         boolean progressing,
                         LayoutRegion region,
                         boolean closeOther,
                         Map<String, Object> arg) {
        icl.setProgressing(progressing);
        if (closeOther) {
            closePanels();
        }
        icl.setDynamicProperty(INCLUDE_PARAM_ATTR, arg);
        icl.setSrc(pagePath);
        Window win = findChildWindow(icl);
        postOnAfterCreate(win, arg);
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

    private void postOnAfterCreate(Window win, Map<String, Object> arg) {
        if (win != null) {
            Events.echoEvent("onAfterCreate", win, arg);
        }
    }

    public <T> T eventParam(Class<T> clazz, Event event, String key) {
        Object odata = event.getData();
        if (odata == null)
            return null;
        if (!(odata instanceof Map))
            return null;
        Object opar = ((Map<String, Object>) odata).get(key);
        if (opar == null)
            return null;
        return (T) opar;
    }

    public <T> T windowParam(Class<T> clazz, Window window, String key) {
        Object odata = window.getDesktop().getExecution().getAttribute(INCLUDE_PARAM_ATTR);
        if (odata == null)
            return null;
        if (!(odata instanceof Map))
            return null;
        Object opar = ((Map<String, Object>) odata).get(key);
        if (opar == null)
            return null;
        return (T) opar;
    }
}
