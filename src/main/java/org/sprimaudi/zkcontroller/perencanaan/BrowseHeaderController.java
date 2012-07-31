package org.sprimaudi.zkcontroller.perencanaan;

import com.djbc.utilities.StringUtil;
import com.djbc.zkcomponent.fusion.FusionChart;
import com.djbc.zkcomponent.fusion.model.SingleSeriesModel;
import org.apache.commons.collections.CollectionUtils;
import org.sprimaudi.zkspring.entity.Droa;
import org.sprimaudi.zkspring.repository.DroaRepository;
import org.sprimaudi.zkspring.util.Mapper;
import org.sprimaudi.zkspring.util.PageMgt;
import org.springframework.util.xml.StaxUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/19/12
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class BrowseHeaderController extends SelectorComposer<Window> {

    @WireVariable
    PageMgt pgm;

    @WireVariable
    Mapper mapper;

    @WireVariable
    DroaRepository droaRepository;


    @Wire
    Listbox lstHeaderPerencanaan;

    @Wire
    FusionChart fcFrequent;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
        System.out.println("prepare to render");
        System.out.println("pgm " + pgm);

        SingleSeriesModel ssm = new SingleSeriesModel();
        ssm.addSerie("Q1", "500");
        ssm.addSerie("Q2", "650");
        ssm.addSerie("Q3", "575");
        fcFrequent.setModel(ssm);

        lstHeaderPerencanaan.setItemRenderer(new ListitemRenderer<Droa>() {
            @Override
            public void render(Listitem listitem, Droa o, int i) throws Exception {
                //To change body of implemented methods use File | Settings | File Templates.
                new Listcell(StringUtil.nvl(o.getNomor(), "")).setParent(listitem);
                new Listcell(StringUtil.fromDate(o.getTanggal())).setParent(listitem);
                new Listcell("" + o.getTahun()).setParent(listitem);
                new Listcell((o.getJenis() != null) ? o.getJenis().getNama() : "").setParent(listitem);
                new Listcell(o.getKeterangan()).setParent(listitem);
                listitem.setValue(o);
            }
        });
    }


    @Listen("onSelect=#lstHeaderPerencanaan")
    public void onListHeaderClick(Event evt) {
        System.out.println("observe listHeader");
        System.out.println(lstHeaderPerencanaan);

        pgm.showMainOnly("zuls/perencanaan/draft_header.zul",
                mapper.map("droa",
                        ((Droa) ((Listbox) evt.getTarget())
                                .getSelectedItem().getValue()).getId()));
    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate() {
        prepareList();
    }

    private void prepareList() {
        List<Droa> droas = new ArrayList<Droa>();
        CollectionUtils.addAll(droas, droaRepository.findAll().iterator());
        ListModelList<Droa> lml = new ListModelList<Droa>(droas, true);
        lstHeaderPerencanaan.setModel(lml);
    }
}
