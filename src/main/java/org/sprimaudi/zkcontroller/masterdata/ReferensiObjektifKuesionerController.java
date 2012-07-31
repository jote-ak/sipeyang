package org.sprimaudi.zkcontroller.masterdata;

import com.djbc.utilities.Converter;
import com.djbc.utilities.StringUtil;
import org.sprimaudi.zkspring.entity.KuesionerObjektif;
import org.sprimaudi.zkspring.repository.KuesionerObjektifRepository;
import org.sprimaudi.zkutil.ReferensiUtil;
import org.zkoss.spring.DelegatingVariableResolver;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.*;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/31/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class ReferensiObjektifKuesionerController extends SelectorComposer<Window> {
    public static final String zulpath = "zuls/masterdata/checklist_objectif_referensi.zul";

    @WireVariable
    ReferensiUtil referensiUtil;
    @WireVariable
    KuesionerObjektifRepository kuesionerObjektifRepository;

    @Wire
    Textbox txtId, txtKode, txtObjektif;
    @Wire
    Listbox lstObjektif;

    @Wire
    Combobox txtKategori;
    @Wire
    Button btnAdd, btnEdit,
            btnDelete, btnSimpan, btnRefresh,
            btnCancel;


    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
        switchButtonState(false);
    }

    private void switchButtonState(boolean editing) {
        btnAdd.setVisible(!editing);
        btnEdit.setVisible((!editing));
        btnDelete.setVisible(!editing);
        btnSimpan.setVisible(editing);
        btnRefresh.setVisible(editing);
        btnCancel.setVisible(editing);
    }

    private void switchEditable(boolean editable) {
        btnAdd.setVisible(editable);
        btnEdit.setVisible((editable));
        btnDelete.setVisible(editable);
    }

    private KuesionerObjektif extract() {
        KuesionerObjektif ko = ((StringUtil.nvl(txtId.getText()).equals("")) ? new KuesionerObjektif() :
                kuesionerObjektifRepository.findOne(Converter.convertLong(txtId.getText())));
        ko.setKategori(referensiUtil.fromCombo(txtKategori));
        ko.setKode(txtKode.getText());
        ko.setObjektif(txtObjektif.getText());
        return ko;
    }

    private void show(KuesionerObjektif ko) {
        if (ko == null) {
            return;
        }
        txtId.setText(StringUtil.nvl(ko.getId()));
        referensiUtil.toCombo(txtKategori, ko.getKategori());
        txtKode.setText(StringUtil.nvl(ko.getKode()));
        txtObjektif.setText(StringUtil.nvl(ko.getObjektif()));
    }


    @Listen("onClick=#btnSimpan")
    public void onSimpan(Event evt) {
        kuesionerObjektifRepository.save(extract());
    }

    @Listen("onClick=#btnCancel")
    public void onCancel() {
        switchButtonState(false);
    }

    @Listen("onClick=#btnAdd")
    public void onAdd() {
        show(new KuesionerObjektif());
        switchButtonState(true);
    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate(Event evt) {
        alert("is it wrong here?");
        referensiUtil.fillCombo(txtKategori);
    }
}
