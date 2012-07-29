package org.sprimaudi.zkspring.service;

import org.sprimaudi.zkspring.entity.AnggotaTim;
import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.entity.SuratTugas;
import org.sprimaudi.zkspring.repository.AnggotaTimRepository;
import org.sprimaudi.zkspring.repository.SuratTugasRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/28/12
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "anggotaTimService")
public class AnggotaTimService {
    @Inject
    AnggotaTimRepository anggotaTimRepository;

    @Inject
    SuratTugasRepository suratTugasRepository;

    public AnggotaTim simpan(AnggotaTim anggota) {
        anggota = anggotaTimRepository.save(anggota);
        return anggota;
    }

    public AnggotaTim simpanToOa(ObjectAudit oa, AnggotaTim anggota) {
        if (oa == null) {
            return null;
        }
        SuratTugas st = acquireStPrimer(oa);
        simpanToTimST(st, anggota);
        return anggota;
    }

    public boolean cekStPrimer(ObjectAudit oa) {
        return (suratTugasRepository.findSTPrimer(oa.getId()) != null);
    }

    public SuratTugas acquireStPrimer(ObjectAudit oa) {
        if (cekStPrimer(oa)) {
            return suratTugasRepository.findSTPrimer(oa.getId());
        } else {
            SuratTugas st = new SuratTugas();
            st.setPrimer("Y");
            st.setObjectAudit(oa);
            suratTugasRepository.save(st);
            return st;
        }
    }

    public void simpanToTimST(SuratTugas st, AnggotaTim anggota) {
        if (st == null || anggota == null)
            return;
        anggota.setSuratTugas(st);
        anggotaTimRepository.save(anggota);
    }
}
