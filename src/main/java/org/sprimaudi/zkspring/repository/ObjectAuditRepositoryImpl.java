package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.filter.Fentry;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/24/12
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectAuditRepositoryImpl implements ObjectAuditRepositoryCustom {
    @Override
    public Iterable<ObjectAudit> filter(List<Fentry> filterItem) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
