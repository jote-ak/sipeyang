package org.sprimaudi.zkspring.service;

import org.sprimaudi.zkspring.entity.Referensi;
import org.sprimaudi.zkspring.repository.ReferensiRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/21/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "referensiService")
public class ReferensiService {
    @Inject
    ReferensiRepository referensiRepository;

    public Referensi byKodeGrup(String kode, Long grup) {
        return referensiRepository.byKodeGrup(kode, grup);
    }

}
