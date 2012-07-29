package org.sprimaudi.zkcontroller.perencanaan;

import com.djbc.utilities.StringUtil;
import org.sprimaudi.zkspring.entity.Droa;
import org.sprimaudi.zkspring.repository.DroaRepository;
import org.sprimaudi.zkspring.util.PageMgt;
import org.sprimaudi.zkutil.ReferensiUtil;
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

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/24/12
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class HeaderSiderController extends SelectorComposer<Window> {
    private Droa theDroa;
    @WireVariable
    PageMgt pgm;
    @WireVariable
    DroaRepository droaRepository;
    @WireVariable
    ReferensiUtil referensiUtil;

    @Wire
    Textbox txtNomorDroa,
            txtKeteranganDroa, txtTahunDroa;

    @Wire
    Datebox txtTanggalDroa;

    @Wire
    Radiogroup jenisAudit;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate(Event evt) {
        System.out.println("ini after create sider");
        Long idDroa = pgm.eventParam(Long.class, evt, "droa");
        System.out.println(idDroa);
        if (idDroa != null) {
            theDroa = droaRepository.findOne(idDroa);
            showDroa(theDroa);
        }
    }

    public void showDroa(Droa droa) {
        if (droa == null) {
            return;
        }
        txtTanggalDroa.setValue(droa.getTanggal());
        txtNomorDroa.setText(droa.getNomor());
        txtKeteranganDroa.setText(droa.getKeterangan());
        txtTahunDroa.setText(StringUtil.nvl(droa.getTahun()));
        referensiUtil.toRadioGrup(jenisAudit, droa.getJenis());
    }

}
