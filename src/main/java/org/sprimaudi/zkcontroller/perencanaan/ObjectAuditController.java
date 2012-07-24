package org.sprimaudi.zkcontroller.perencanaan;

import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.repository.UnitRepository;
import org.sprimaudi.zkspring.service.ObjectAuditService;
import org.sprimaudi.zkutil.ReferensiUtil;
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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
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

    @Wire
    Textbox txtUnit, txtAlasan, txtKeterangan;
    @Wire
    Datebox txtAwalObjectAudit, txtAkhirObjectAudit;

    @Wire
    Radiogroup jnsAuditee;

    @WireVariable
    ObjectAuditService objectAuditService;

    @WireVariable
    UnitLookuper unitLookuper;


    @WireVariable
    ReferensiUtil referensiUtil;

    private ObjectAudit extract() {
        ObjectAudit oa = new ObjectAudit();
        oa.setAlasan(txtAlasan.getText());
        oa.setKeterangan(txtKeterangan.getText());
        oa.setAuditeeJenis(referensiUtil.fromRadioGrup(jnsAuditee));
        oa.setUnit(unitLookuper.getValue(txtUnit));
        oa.setAwal(txtAwalObjectAudit.getValue());
        oa.setAkhir(txtAkhirObjectAudit.getValue());

        return oa;
    }

    @Listen("onClick=#btnSimpanObjectAudit")
    public void simpanObjectAudit(Event evt) {
        objectAuditService.simpan(extract());
        alert("penyimpanan berhasil,.  apa nggak ya ?");
    }

    @Listen("onClick=#btnUnitObjectAudit")
    public void btnUnitObjectAuditClick(Event evt) {
        Window w = unitLookuper.showLookup(txtUnit);
        w.doModal();

    }
}
