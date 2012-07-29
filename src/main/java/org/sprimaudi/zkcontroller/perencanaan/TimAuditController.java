package org.sprimaudi.zkcontroller.perencanaan;

import com.djbc.utilities.Converter;
import org.sprimaudi.zkspring.entity.AnggotaTim;
import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.entity.Pegawai;
import org.sprimaudi.zkspring.repository.AnggotaTimRepository;
import org.sprimaudi.zkspring.repository.ObjectAuditRepository;
import org.sprimaudi.zkspring.service.AnggotaTimService;
import org.sprimaudi.zkspring.util.PageMgt;
import org.sprimaudi.zkutil.ReferensiUtil;
import org.sprimaudi.zkutil.lookuper.PegawaiLookuper;
import org.sprimaudi.zkutil.lookuper.UnitLookuper;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/28/12
 * Time: 9:39 AM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class TimAuditController extends SelectorComposer<Window> {
    @WireVariable
    PegawaiLookuper pegawaiLookuper;
    @WireVariable
    UnitLookuper unitLookuper;
    @WireVariable
    ReferensiUtil referensiUtil;
    @WireVariable
    AnggotaTimService anggotaTimService;
    @WireVariable
    AnggotaTimRepository anggotaTimRepository;
    @WireVariable
    PageMgt pgm;
    @WireVariable
    ObjectAuditRepository objectAuditRepository;
    @Wire
    Textbox txtAuditor, txtUnit, txtIdAnggota;
    @Wire
    Listbox lstAuditor;
    @Wire
    Combobox txtPosisi;

    private AnggotaTim theAnggota;
    private ObjectAudit objectAudit;

    public static String zulpath = "zuls/perencanaan/tim_audit.zul";

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
        referensiUtil.fillCombo(txtPosisi);
        lstAuditor.setItemRenderer(new ListitemRenderer<AnggotaTim>() {
            @Override
            public void render(Listitem listitem, AnggotaTim o, int i) throws Exception {
                //To change body of implemented methods use File | Settings | File Templates.
                listitem.setValue(o);
                new Listcell((o.getPegawai() != null) ? o.getPegawai().getNama() : "").setParent(listitem);
                new Listcell((o.getPosisi() != null) ? o.getPosisi().getNama() : "").setParent(listitem);
                if (o.getPegawai() != null) {
                    new Listcell((o.getPegawai().getUnit() != null) ? o.getPegawai().getUnit().getNama() : "").setParent(listitem);

                }

            }
        });
    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate(Event evt) {
        Long idOa = pgm.eventParam(Long.class, evt, "objectAudit");
        if (idOa != null) {
            this.objectAudit = objectAuditRepository.findOne(idOa);
        }
    }

    public void showPegawai(Pegawai pegawai) {
        unitLookuper.setValue(txtUnit, pegawai.getUnit());
    }

    @Listen("onClick=#btnAuditor")
    public void onBtnAuditor(Event evt) {
        Window w = pegawaiLookuper.showLookup(txtAuditor);
        w.doModal();
        showPegawai(pegawaiLookuper.getSelected());
    }

    public AnggotaTim extract() {
        AnggotaTim at = new AnggotaTim();
        at.setId(Converter.convertLong(txtIdAnggota.getText()));
        at.setPegawai(pegawaiLookuper.getValue(txtAuditor));
        at.setPosisi(referensiUtil.fromCombo(txtPosisi));
        return at;
    }

    @Listen("onClick=#btnSimpanAnggota")
    public void onSimpanAnggota(Event evt) {
        theAnggota = anggotaTimService.simpan(extract());
        anggotaTimService.simpanToOa(objectAudit, theAnggota);
    }

    public void prepareList() {
        List<AnggotaTim> ats = anggotaTimRepository.findRancanganTimByObjectAudit(objectAudit.getId());
        ListModelList<AnggotaTim> lml = new ListModelList<AnggotaTim>(ats, true);
        lstAuditor.setModel(lml);
    }
}
