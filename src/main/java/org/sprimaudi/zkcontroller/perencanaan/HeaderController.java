package org.sprimaudi.zkcontroller.perencanaan;

import com.djbc.utilities.Converter;
import com.djbc.utilities.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.sprimaudi.zkspring.entity.Droa;
import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.repository.DroaRepository;
import org.sprimaudi.zkspring.repository.ObjectAuditRepository;
import org.sprimaudi.zkspring.repository.ReferensiRepository;
import org.sprimaudi.zkspring.service.DroaService;
import org.sprimaudi.zkspring.util.Mapper;
import org.sprimaudi.zkspring.util.PageMgt;
import org.sprimaudi.zkutil.ReferensiUtil;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.util.*;
import java.util.Calendar;

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
    Textbox txtNomor, txtTahun, txtKeterangan, txtIdDroa;
    @Wire
    Datebox txtTanggal;

    private boolean readOnly;

    @WireVariable
    DroaService droaService;
    @WireVariable
    ReferensiRepository referensiRepository;
    @WireVariable
    DroaRepository droaRepository;
    @WireVariable
    ObjectAuditRepository objectAuditRepository;

    @WireVariable
    ReferensiUtil referensiUtil;
    @WireVariable
    Mapper mapper;

    private Droa theDroa;
    @Wire
    Listbox lstItemPerencanaan;

    @Wire("window")
    Window self;


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
        Long idDroa = pgm.windowParam(Long.class, self, "droa");
        show(idDroa);
        lstItemPerencanaan.setItemRenderer(new ListitemRenderer<ObjectAudit>() {
            @Override
            public void render(Listitem listitem, ObjectAudit o, int i) throws Exception {
                new Listcell((o.getUnit() != null) ? o.getUnit().getNama() : "").setParent(listitem);
                new Listcell(StringUtil.nvl(o.getTopik())).setParent(listitem);
                listitem.setValue(o);

                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    private void manageState() {
        //
        txtNomor.setReadonly(true);
        txtTanggal.setReadonly(true);
    }

    @Listen("onClick=#btnSimpanRencana")
    public void simpan() {
        Droa droa = extract();
        theDroa = droaService.simpanDroa(droa);
        txtNomor.setText(theDroa.getNomor());
        txtTanggal.setValue(theDroa.getTanggal());
    }

    public Droa extract() {
        Droa droa = new Droa();
        droa.setNomor(txtNomor.getText());
        droa.setTanggal(txtTanggal.getValue());
        droa.setKeterangan(txtKeterangan.getText());
        //TODO  Consider to use former Converter.
        droa.setTahun(Long.parseLong(txtTahun.getText()));
        droa.setJenis(referensiUtil.fromRadioGrup(jnsAudit));
        System.out.println("observer nilai default value convertoer");
        System.out.println(Converter.convertLong(txtIdDroa.getText()));
        droa.setId(Converter.convertLong(txtIdDroa.getText()));
        return droa;
    }

    public void show(Long idDroa) {
        if (idDroa == null) {
            return;
        }
        theDroa = droaRepository.findOne(idDroa);
        show(theDroa);
    }

    public void show(Droa droa) {
        if (droa == null) {
            return;
        }
        txtNomor.setText(droa.getNomor());
        txtTanggal.setValue(droa.getTanggal());
        txtKeterangan.setText(droa.getKeterangan());
        txtTahun.setText(StringUtil.nvl(droa.getTahun()));
        referensiUtil.toRadioGrup(jnsAudit, droa.getJenis());
        txtIdDroa.setText(StringUtil.nvl(droa.getId()));
        prepareList();

    }

    @Listen("onSelect=#lstItemPerencanaan")
    public void onListSelect(Event evt) {
        pgm.broadcast("onShowObjectAudit",
                mapper.map("droa", theDroa.getId())
                        .map("objectAudit",
                                ((ObjectAudit) lstItemPerencanaan
                                        .getSelectedItem().getValue()).getId())
                        .map("edit", true));
    }

    @Listen("onClick=#btnEditRencana")
    public void onEditRencana(Event event) {
        Map<String, Object> m = mapper
                .map("droa", (theDroa != null) ? theDroa.getId() : null)
                .map("edit", true);
        pgm.broadcast("onShowObjectAudit", m);
    }

    public void prepareList() {
        List<ObjectAudit> oas = new ArrayList<ObjectAudit>();
        CollectionUtils.addAll(oas, objectAuditRepository.findByDroa(theDroa.getId()).iterator());
        lstItemPerencanaan.setModel(
                new ListModelList<ObjectAudit>(oas, true)
        );
    }
}
