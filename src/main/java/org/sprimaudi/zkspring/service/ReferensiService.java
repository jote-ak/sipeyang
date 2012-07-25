package org.sprimaudi.zkspring.service;

import org.sprimaudi.zkspring.entity.Referensi;
import org.sprimaudi.zkspring.repository.ReferensiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/21/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "referensiService")
public class ReferensiService {
    private final static Long GRUP_INCREMENT = 4L;

    @Inject
    ReferensiRepository referensiRepository;


    public Referensi byKodeGrup(String kode, Long grup) {
        return referensiRepository.byKodeGrup(kode, grup);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Long increment(String kode) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int tahunIni = cal.get(Calendar.YEAR);
        Referensi r = referensiRepository.byGrupAndKode(GRUP_INCREMENT, kode);
        Long cur = r.getNum();
        Long next = cur + 1;
        r.setNum(next);
        Long tahunReferensi = r.getNilai();
        if (tahunIni > tahunReferensi) {
            r.setNilai(tahunReferensi++);
        }
        System.out.println("observe increment");
        System.out.println(cur);
        System.out.println(next);
        referensiRepository.save(r);
        return next;
    }

}
