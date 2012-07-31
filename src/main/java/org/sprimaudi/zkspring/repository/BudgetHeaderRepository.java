package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.BudgetHeader;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/30/12
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BudgetHeaderRepository extends CrudRepository<BudgetHeader, Long> {
    @Query("select bh from BudgetHeader bh,ObjectAudit oa where bh.objectAudit = oa and oa.id=?1")
    public List<BudgetHeader> findByObjectAudit(Long oaId);
}
