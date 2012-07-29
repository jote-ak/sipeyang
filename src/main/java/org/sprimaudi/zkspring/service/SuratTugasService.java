package org.sprimaudi.zkspring.service;

import org.sprimaudi.zkspring.entity.SuratTugas;
import org.sprimaudi.zkspring.repository.SuratTugasRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/28/12
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "suratTugasService")
public class SuratTugasService {
    @Inject
    SuratTugasRepository suratTugasRepository;

    public void simpan(SuratTugas st) {
        suratTugasRepository.save(st);

    }
}
