package org.sprimaudi.zkutil;

import org.sprimaudi.zkspring.entity.Referensi;
import org.sprimaudi.zkspring.service.ReferensiService;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Radiogroup;

import javax.inject.Inject;

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

    public Referensi fromRadioGrup(Radiogroup radioGrup) {
        String sgrup = (String) radioGrup.getAttribute("grup");
        Long lgrup = (sgrup != null && !"".equalsIgnoreCase(sgrup)) ?
                Long.parseLong(sgrup) : null;
        if (sgrup == null || radioGrup.getSelectedItem() == null) {
            return null;
        }
        return referensiService.byKodeGrup(
                radioGrup.getSelectedItem().getValue(), lgrup);
    }
}
