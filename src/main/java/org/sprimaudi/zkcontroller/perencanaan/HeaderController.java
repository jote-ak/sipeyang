package org.sprimaudi.zkcontroller.perencanaan;

import org.sprimaudi.zkspring.entity.Droa;
import org.sprimaudi.zkspring.repository.ReferensiRepository;
import org.sprimaudi.zkspring.service.DroaService;
import org.sprimaudi.zkspring.util.Mapper;
import org.sprimaudi.zkspring.util.PageMgt;
import org.sprimaudi.zkutil.ReferensiUtil;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 5:20 AM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class HeaderController extends SelectorComposer<Window> {
    @WireVariable
    PageMgt pgm;
    @Wire
    Radiogroup jnsAudit;
    @Wire
    Textbox txtNomor, txtTahun, txtKeterangan;
    @Wire
    Datebox txtTanggal;

    private boolean readOnly;
    @WireVariable
    DroaService droaService;
    @WireVariable
    ReferensiRepository referensiRepository;
    @WireVariable
    ReferensiUtil referensiUtil;
    @WireVariable
    Mapper mapper;

    private Droa theDroa;
    @Wire
    Listbox lstItemPerencanaan;


    @WireVariable
    Execution _execution;


    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
        //txt tahun selalu bernilai tahun sekarang, apabila data telah terisi dengan
        //data tertentu, maka nilai ini nanti akan di overide dengan onbind atau yang lain
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        txtTahun.setText("" + cal.get(Calendar.YEAR));
        manageState();
    }

    private void manageState() {
        //
        txtNomor.setReadonly(true);
        txtTanggal.setReadonly(true);
    }

    @Listen("onClick=#btnSimpanRencana")
    public void simpan() {
        Droa droa = extractDroa();
        theDroa = droaService.simpanDroa(droa);

    }

    public Droa extractDroa() {
        Droa droa = new Droa();
        droa.setNomor(txtNomor.getText());
        droa.setTanggal(txtTanggal.getValue());
        droa.setKeterangan(txtKeterangan.getText());
        //TODO  Consider to use former Converter.
        droa.setTahun(Long.parseLong(txtTahun.getText()));
        droa.setJenis(referensiUtil.fromRadioGrup(jnsAudit));
        return droa;
    }

    @Listen("onSelect=#lstItemPerencanaan")
    public void onListSelect(Event evt) {

    }

    @Listen("onClick=#btnEditRencana")
    public void onEditRencana(Event event) {
        Map<String, Object> m = mapper.map("droa", theDroa)
                .map("tes", "Value");
        pgm.showNav("zuls/perencanaan/draft_header_sider.zul", m);
        pgm.showProp("zuls/perencanaan/browse_draft_detail_sider.zul", m);
        pgm.showMain("zuls/perencanaan/draft_detail.zul", m);
    }
}
