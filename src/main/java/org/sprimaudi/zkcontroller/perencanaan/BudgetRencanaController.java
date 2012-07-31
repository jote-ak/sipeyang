package org.sprimaudi.zkcontroller.perencanaan;

import org.sprimaudi.zkspring.entity.Budget;
import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.entity.Referensi;
import org.sprimaudi.zkspring.repository.BudgetRepository;
import org.sprimaudi.zkspring.repository.ObjectAuditRepository;
import org.sprimaudi.zkspring.repository.ReferensiRepository;
import org.sprimaudi.zkspring.service.BudgetService;
import org.sprimaudi.zkspring.util.PageMgt;
import org.sprimaudi.zkutil.ComponentUtil;
import org.zkoss.util.Converter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/30/12
 * Time: 5:19 AM
 * To change this template use File | Settings | File Templates.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class BudgetRencanaController extends SelectorComposer<Window> {
    public static String zulpath = "zuls/perencanaan/budget.zul";
    public static final String KODE = "kode";
    private ObjectAudit objectAudit;

    @Wire
    Grid gdBudget;

    @WireVariable
    ReferensiRepository referensiRepository;
    @WireVariable
    PageMgt pgm;

    @WireVariable
    BudgetRepository budgetRepository;
    @WireVariable
    BudgetService budgetService;
    @WireVariable
    ObjectAuditRepository objectAuditRepository;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Listen("onAfterCreate=window")
    public void onAfterCreate(Event evt) {
        Rows rows = new Rows();
        rows.setParent(gdBudget);
        List<Referensi> ref = referensiRepository.byGrup(10L);
        for (Iterator<Referensi> iterator = ref.iterator(); iterator.hasNext(); ) {
            Referensi re = iterator.next();
            Row r = new Row();
            r.setParent(rows);

            Label l = new Label(re.getNama() + ":");
            l.setParent(r);
            Div div = new Div();
            Decimalbox db = new Decimalbox(BigDecimal.ZERO);
            db.setAttribute(KODE, re.getKode());
            db.setStyle("text-align:right");
            db.setFormat("#,###.##");
            db.setParent(div);
            db.setHflex("true");
            div.setParent(r);
            Label lspace = new Label("");
            l.setParent(r);
        }
        Long idOa = (Long) pgm.eventParam(Long.class, evt, "objectAudit");
        if (idOa != null) {
            objectAudit = objectAuditRepository.findOne(idOa);
            show(objectAudit);
        }

    }

    public List<Budget> extract() {
        List<Budget> bs = new ArrayList<Budget>();
        Rows rows = gdBudget.getRows();
        List<Row> comps = ComponentUtil.easyFind(Row.class, rows, 3);
        System.out.println("banyakya baris ditemukan: " + comps.size());
        for (Iterator<Row> iterator = comps.iterator(); iterator.hasNext(); ) {
            Row cmp = iterator.next();
            Row r = (Row) cmp;
            bs.add(extract(r));
        }
        return bs;
    }

    /**
     * extract budget entry from row
     *
     * @param row
     * @return
     */
    public Budget extract(Row row) {
        Decimalbox db = ComponentUtil.findFirst(Decimalbox.class, row, 3);
        Long id = (Long) db.getAttribute("idBudget");
        String kode = (String) db.getAttribute(KODE);
        Budget b = (id == null) ? new Budget() : budgetRepository.findOne(id);
        b.setJenis(referensiRepository.byGrupAndKode(10L, kode));
        b.setNilai(db.getValue());
        return b;
    }

    public void show(ObjectAudit oa) {
        if (oa == null)
            return;
        List<Budget> bs = budgetRepository.findFirstDraftOfObjectAudit(oa.getId());
        for (Iterator<Budget> iterator = bs.iterator(); iterator.hasNext(); ) {
            Budget b = iterator.next();
            show(b);
        }
    }

    public void show(Budget bd) {
        List<Decimalbox> dbs = ComponentUtil.easyFind(Decimalbox.class, gdBudget, 6);
        for (Iterator<Decimalbox> iterator = dbs.iterator(); iterator.hasNext(); ) {
            Decimalbox db = iterator.next();
            String kode = (String) db.getAttribute(KODE);
            if (kode != null && !"".equals(kode) && kode.equals(bd.getJenis().getKode())) {
                db.setValue(bd.getNilai());
            }
        }
    }

    @Listen("onClick=#btnSimpan")
    public void btnSimpan(Event evt) {
        if (objectAudit == null)
            return;
        List<Budget> bs = extract();
        for (Iterator<Budget> iterator = bs.iterator(); iterator.hasNext(); ) {
            Budget budget = iterator.next();
            budgetService.simpan(budget);
            budgetService.safeFirstBudgetOfObjectAudit(objectAudit, budget);
        }
    }
}
