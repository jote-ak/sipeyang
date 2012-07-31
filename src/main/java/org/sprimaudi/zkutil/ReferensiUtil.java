package org.sprimaudi.zkutil;

import com.djbc.utilities.Converter;
import com.djbc.utilities.StringUtil;
import org.sprimaudi.zkspring.entity.Referensi;
import org.sprimaudi.zkspring.repository.ReferensiRepository;
import org.sprimaudi.zkspring.service.ReferensiService;
import org.springframework.stereotype.Component;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/21/12
 * Time: 6:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Component(value = "referensiUtil")
public class ReferensiUtil {
    @Inject
    ReferensiService referensiService;

    @Inject
    ReferensiRepository referensiRepository;

    private static final String GRUP_KEY = "grup";

    public Referensi fromRadioGrup(Radiogroup radioGrup) {
        String sgrup = (String) radioGrup.getAttribute(GRUP_KEY);
        Long lgrup = (sgrup != null && !"".equalsIgnoreCase(sgrup)) ?
                Long.parseLong(sgrup) : null;
        if (sgrup == null || radioGrup.getSelectedItem() == null) {
            return null;
        }
        return referensiService.byKodeGrup(
                radioGrup.getSelectedItem().getValue(), lgrup);
    }

    public void toRadioGrup(Radiogroup radioGrup, Referensi referensi) {
        if (referensi == null) {
            return;
        }
        String sgrup = (String) radioGrup.getAttribute(GRUP_KEY);
        Long lgrup = (sgrup != null && !"".equalsIgnoreCase(sgrup)) ?
                Long.parseLong(sgrup) : null;
        if (sgrup == null || lgrup != referensi.getGrup()) {
            return;
        }
        List<Radio> rads = radioGrup.getItems();
        for (Iterator<Radio> rad = rads.iterator(); rad.hasNext(); ) {
            Radio next = rad.next();
            if (next.getValue() != null &&
                    !"".equals(next.getValue()) &&
                    next.getValue().equalsIgnoreCase(referensi.getKode())) {
                next.setSelected(true);
                return;
            }
        }
    }

    public void fillCombo(Combobox combo) {
        combo.getChildren().clear();
        Long grup = Converter.convertLong(
                (String) combo.getAttribute(GRUP_KEY));
        if (grup == null)
            return;
        List<Referensi> refs = referensiRepository.byGrup(grup);
        for (Iterator<Referensi> irefs = refs.iterator(); irefs.hasNext(); ) {
            Referensi ref = irefs.next();
            Comboitem ci = new Comboitem(ref.getNama());
            ci.setValue(ref.getKode());
            ci.setParent(combo);
        }
    }

    public Referensi fromCombo(Combobox combo) {
        Long grup = Converter.convertLong(
                (String) combo.getAttribute(GRUP_KEY));
        if (grup == null)
            return null;
        Comboitem ci = combo.getSelectedItem();
        if (ci == null)
            return null;
        String kode = ci.getValue();
        return referensiRepository.byKodeGrup(kode, grup);
    }

    public void toCombo(Combobox combo, Referensi referensi) {
        List<org.zkoss.zk.ui.Component> cis = combo.getChildren();
        //check any combo item found
        boolean anyItem = false;
        combo.setSelectedItem(null);
        if (referensi == null) {
            return;
        }
        for (Iterator<org.zkoss.zk.ui.Component> iterator = cis.iterator(); iterator.hasNext(); ) {
            org.zkoss.zk.ui.Component comp = (org.zkoss.zk.ui.Component) iterator.next();
            if (comp instanceof Comboitem) {
                anyItem = true;
                break;
            }
        }
        if (!anyItem)
            fillCombo(combo);
        cis = combo.getChildren();
        for (Iterator<org.zkoss.zk.ui.Component> iterator = cis.iterator(); iterator.hasNext(); ) {
            org.zkoss.zk.ui.Component comp = (org.zkoss.zk.ui.Component) iterator.next();
            if (comp instanceof Comboitem) {
                Comboitem ci = (Comboitem) comp;
                if (ci.getValue() != null && ((String) ci.getValue()).equals(referensi.getKode())) {
                    combo.setSelectedItem(ci);
                }
            }
        }
    }
}
