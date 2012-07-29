package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/24/12
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ObjectAuditRepository extends CrudRepository<ObjectAudit, Long>, ObjectAuditRepositoryCustom {

    @Query("select oa from ObjectAudit oa, Droa d where oa.droa=d and d.id=?1")
    public List<ObjectAudit> findByDroa(Long idDroa);

}
