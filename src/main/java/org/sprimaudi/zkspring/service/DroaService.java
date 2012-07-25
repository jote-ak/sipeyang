package org.sprimaudi.zkspring.service;

import org.apache.commons.lang.StringUtils;
import org.sprimaudi.zkspring.entity.Droa;
import org.sprimaudi.zkspring.repository.DroaRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/21/12
 * Time: 3:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "droaService")
public class DroaService {
    @Inject
    DroaRepository droaRepository;

    @Inject
    ReferensiService referensiService;


    public Droa simpanDroa(Droa droa) {
        if (cekPenomoran(droa))
            penomoranDroa(droa);
        return droaRepository.save(droa);
    }

    public Droa penomoranDroa(Droa droa) {
        if (droa != null) {
            droa.setNomor(StringUtils.leftPad("" + referensiService.increment("IDR"), 6, "0"));
            droa.setTanggal(new Date());
            droa.setStatus(referensiService.byKodeGrup("NMR", 5L));
        }
        return droa;
    }

    /**
     * Periksa apakah kondisi droa ini memang layak untuk di lakukan penomoran
     * sesuakan dengan aturan penomoran yang di inginkan
     *
     * @param droa
     * @return
     */
    public boolean cekPenomoran(Droa droa) {
        if (droa.getNomor() == null || "".equals(droa.getNomor())) {
            return true;
        }
        return false;
    }
}
