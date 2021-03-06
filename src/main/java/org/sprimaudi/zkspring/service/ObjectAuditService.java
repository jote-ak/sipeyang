package org.sprimaudi.zkspring.service;

import org.sprimaudi.zkspring.entity.Droa;
import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.repository.ObjectAuditRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/24/12
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */

@Service(value = "objectAuditService")
public class ObjectAuditService {
    @Inject
    ObjectAuditRepository objectAuditRepository;

    public ObjectAudit simpan(ObjectAudit oa) {
        //Add validation and bussines logic here
        return objectAuditRepository.save(oa);
    }

    public void addToDroa(Droa droa, ObjectAudit objectAudit) {
        if (droa == null || objectAudit == null) {
            return;
        }
        if (objectAudit.getDroa().equals(droa)) {
            return;//don't need to save to droa again
        }
        objectAudit.setDroa(droa);
        objectAuditRepository.save(objectAudit);
    }
}
