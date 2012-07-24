package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/24/12
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ObjectAuditRepository extends CrudRepository<ObjectAudit,Long>,ObjectAuditRepositoryCustom{
}
