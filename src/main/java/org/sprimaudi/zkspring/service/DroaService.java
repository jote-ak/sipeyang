package org.sprimaudi.zkspring.service;

import org.sprimaudi.zkspring.entity.Droa;
import org.sprimaudi.zkspring.repository.DroaRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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

    public Droa simpanDroa(Droa droa) {

        return droaRepository.save(droa);
    }
}
