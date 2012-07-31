package org.sprimaudi.zkcontroller.perencanaan;

import com.djbc.utilities.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.sprimaudi.zkspring.entity.Droa;
import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.repository.DroaRepository;
import org.sprimaudi.zkspring.repository.ObjectAuditRepository;
import org.sprimaudi.zkspring.util.Mapper;
import org.sprimaudi.zkspring.util.PageMgt;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;
import sun.font.BidiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/28/12
 * Time: 5:19 AM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class BrowseObjectAuditSiderController extends SelectorComposer<Window> {
    @WireVariable
    PageMgt pgm;
    @WireVariable
    DroaRepository droaRepository;
    @WireVariable
    ObjectAuditRepository objectAuditRepository;
    @Wire
    Button btnAdd, btnEdit;

    @Wire
    Listbox lstObjectAudit;

    private Droa theDroa;
    public static String zulpath = "zuls/perencanaan/browse_draft_detail_sider.zul";


    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
        lstObjectAudit.setItemRenderer(new ListitemRenderer<ObjectAudit>() {
            @Override
            public void render(Listitem listitem, ObjectAudit o, int i) throws Exception {
                //To change body of implemented methods use File | Settings | File Templates.
                new Listcell(StringUtil.nvl(o.getTopik())).setParent(listitem);
                listitem.setValue(o);
            }
        });
    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate(Event evt) {
        Long idDroa = pgm.eventParam(Long.class, evt, "droa");
        if (idDroa != null) {
            theDroa = droaRepository.findOne(idDroa);
            prepareList();
        }
    }

    @WireVariable
    Mapper mapper;

    @Listen("onSelect=#lstObjectAudit")
    public void onSelect(Event evt) {
        Listbox lst = (Listbox) evt.getTarget();
        ObjectAudit oa = (ObjectAudit) lst.getSelectedItem().getValue();
        if (oa != null) {
            pgm.showMain(ObjectAuditController.zulpath,
                    mapper.map("objectAudit", oa.getId())
                            .map("edit", false)
                            .map("show", true));
        }
    }

    @Listen("onClick=#btnAdd")
    public void onAdd(Event evt) {
        pgm.showMain(ObjectAuditController.zulpath,
                mapper.map("objectAudit", 0L)
                        .map("edit", true));
    }

    @Listen("onClick=#miEdit")
    public void onEdit(Event evt) {
        if (lstObjectAudit.getSelectedCount() > 0) {
            ObjectAudit oa = (ObjectAudit) lstObjectAudit.getSelectedItem().getValue();
            pgm.showMain(ObjectAuditController.zulpath,
                    mapper.map("objectAudit", oa.getId())
                            .map("edit", true));
        } else {
            alert("No Auditobject selected");
        }

    }

    @Listen("onClick=#miTim")
    public void onTim(Event evt) {
        if (lstObjectAudit.getSelectedItem() != null && lstObjectAudit.getSelectedItem().getValue() != null) {
            pgm.showMain(TimAuditController.zulpath, mapper
                    .map("objectAudit", ((ObjectAudit) lstObjectAudit.getSelectedItem().getValue()).getId()));
        } else {
            alert("Not Any Object Audit Selected, please select one");
        }

    }

    @Listen("onClick=#miBudget")
    public void onBudget(Event evt) {
        if (lstObjectAudit.getSelectedItem() != null && lstObjectAudit.getSelectedItem().getValue() != null) {
            pgm.showMain(BudgetRencanaController.zulpath, mapper
                    .map("objectAudit", ((ObjectAudit) lstObjectAudit.getSelectedItem().getValue()).getId()));
        } else {
            alert("Not Any Object Audit Selected, please select one");
        }

    }

    @Listen("onClick=#miChecklist")
    public void miChecklist(Event evt) {
        if (lstObjectAudit.getSelectedItem() != null && lstObjectAudit.getSelectedItem().getValue() != null) {
            pgm.showMain(RencanaChecklistController.zulpath, mapper
                    .map("objectAudit", ((ObjectAudit) lstObjectAudit.getSelectedItem().getValue()).getId()));
        } else {
            alert("Not Any Object Audit Selected, please select one");
        }

    }

    public void prepareList() {
        List<ObjectAudit> oas = new ArrayList<ObjectAudit>();
        CollectionUtils.addAll(oas, objectAuditRepository.findByDroa(theDroa.getId()).iterator());
        lstObjectAudit.setModel(
                new ListModelList<ObjectAudit>(oas, true)
        );
    }

}
