package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/30/12
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BudgetRepository extends CrudRepository<Budget, Long> {
    @Query("select b from Budget b,BudgetHeader h, ObjectAudit oa " +
            " where b.header = h and h.seri=1 and h.objectAudit=oa and oa.id=?1")
    public List<Budget> findFirstDraftOfObjectAudit(Long objectAudit);

}
