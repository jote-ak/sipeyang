package org.sprimaudi.zkcontroller.perencanaan;

import com.djbc.utilities.Converter;
import com.djbc.utilities.StringUtil;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import org.sprimaudi.zkspring.entity.Droa;
import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.entity.Unit;
import org.sprimaudi.zkspring.repository.DroaRepository;
import org.sprimaudi.zkspring.repository.ObjectAuditRepository;
import org.sprimaudi.zkspring.repository.UnitRepository;
import org.sprimaudi.zkspring.service.ObjectAuditService;
import org.sprimaudi.zkspring.util.Mapper;
import org.sprimaudi.zkspring.util.PageMgt;
import org.sprimaudi.zkutil.ReferensiUtil;
import org.sprimaudi.zkutil.lookup.LookupUtil;
import org.sprimaudi.zkutil.lookup.LookupWindow;
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

    public static final String zulpath = "zuls/perencanaan/draft_detail.zul";
    private Droa theDroa;
    private ObjectAudit theObjectAudit;
    @Wire
    Window self;

    @Wire
    Textbox txtUnit, txtAlasan, txtKeterangan, txtTopikObjectAudit, txtObjectAudit;
    @Wire
    Datebox txtAwalObjectAudit, txtAkhirObjectAudit;

    @Wire
    Radiogroup jnsAuditee;

    @WireVariable
    ObjectAuditService objectAuditService;

    @WireVariable
    ObjectAuditRepository objectAuditRepository;

    @WireVariable
    DroaRepository droaRepository;

    @WireVariable
    UnitLookuper unitLookuper;

    @WireVariable
    ReferensiUtil referensiUtil;

    @WireVariable
    PageMgt pgm;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.

    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate(Event evt) {
        Long idDroa = pgm.eventParam(Long.class, evt, "droa");
        if (idDroa != null) {
            theDroa = droaRepository.findOne(idDroa);
        }
        Long idObjectAudit = pgm.eventParam(Long.class, evt, "objectAudit");
        if (idObjectAudit != null) {
            if (idObjectAudit == 0L) {
                //0 Represent new object
                theObjectAudit = new ObjectAudit();
            } else {
                theObjectAudit = objectAuditRepository.findOne(idObjectAudit);
                theDroa = theObjectAudit.getDroa();
            }
            show(theObjectAudit);
        }
    }


    private ObjectAudit extract() {
        ObjectAudit oa = new ObjectAudit();
        oa.setId(Converter.convertLong(txtObjectAudit.getText()));
        oa.setAlasan(txtAlasan.getText());
        oa.setKeterangan(txtKeterangan.getText());
        oa.setAuditeeJenis(referensiUtil.fromRadioGrup(jnsAuditee));
        oa.setUnit(unitLookuper.getValue(txtUnit));
        oa.setAwal(txtAwalObjectAudit.getValue());
        oa.setAkhir(txtAkhirObjectAudit.getValue());
        oa.setTopik(txtTopikObjectAudit.getText());
        return oa;
    }

    private void show(ObjectAudit oa) {
        if (oa == null) return;
        txtObjectAudit.setText(StringUtil.nvl(oa.getId()));
        txtAlasan.setText(StringUtil.nvl(oa.getAlasan()));
        txtKeterangan.setText(StringUtil.nvl(oa.getKeterangan()));
        referensiUtil.toRadioGrup(jnsAuditee, oa.getAuditeeJenis());
        txtAwalObjectAudit.setValue(oa.getAwal());
        txtAkhirObjectAudit.setValue(oa.getAkhir());
        txtTopikObjectAudit.setText(StringUtil.nvl(oa.getTopik()));
        unitLookuper.setValue(txtUnit, oa.getUnit());
        //TODO how to set unit reference ?
    }

    private void show(Long idObjectAudit) {
        if (idObjectAudit == null) {
            return;
        }
        theObjectAudit = objectAuditRepository.findOne(idObjectAudit);
        show(theObjectAudit);
    }

    @WireVariable
    Mapper mapper;

    @Listen("onClick=#btnSimpanObjectAudit")
    public void simpanObjectAudit(Event evt) {
        theObjectAudit = objectAuditService.simpan(extract());
        objectAuditService.addToDroa(theDroa, theObjectAudit);
        alert("penyimpanan berhasil,");
        pgm.showProp(BrowseObjectAuditSiderController.zulpath,
                mapper.map("droa", theDroa.getId()));
    }

    @Listen("onClick=#btnUnitObjectAudit")
    public void btnUnitObjectAuditClick(Event evt) {
        LookupWindow<Unit> w = unitLookuper.showLookup(txtUnit);
        w.doModal();

    }
}
