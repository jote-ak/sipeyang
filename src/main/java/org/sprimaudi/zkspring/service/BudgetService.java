package org.sprimaudi.zkspring.service;

import org.sprimaudi.zkspring.entity.Budget;
import org.sprimaudi.zkspring.entity.BudgetHeader;
import org.sprimaudi.zkspring.entity.ObjectAudit;
import org.sprimaudi.zkspring.repository.BudgetHeaderRepository;
import org.sprimaudi.zkspring.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/30/12
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "budgetService")
public class BudgetService {
    @Inject
    BudgetRepository budgetRepository;
    @Inject
    BudgetHeaderRepository budgetHeaderRepository;

    public Budget simpan(Budget budget) {
        Budget b = budgetRepository.save(budget);

        return null;
    }

    public boolean hasBudgetHeader(Budget budget) {
        return budget.getHeader() != null;
    }

    public BudgetHeader findNewHeader(ObjectAudit oa) {
        if (oa == null)
            return null;
        List<BudgetHeader> bhs = budgetHeaderRepository.findByObjectAudit(oa.getId());
        BudgetHeader bh = new BudgetHeader();
        bh.setSeri(bhs.size() + 1);
        bh.setObjectAudit(oa);
        budgetHeaderRepository.save(bh);
        return bh;
    }

    public BudgetHeader findLastHeader(ObjectAudit oa) {
        if (oa == null)
            return null;
        List<BudgetHeader> bhs = budgetHeaderRepository.findByObjectAudit(oa.getId());
        return bhs.get(bhs.size() - 1);
    }

    public void safeFirstBudgetOfObjectAudit(ObjectAudit oa, Budget budget) {
        if (oa == null)
            return;
        List<BudgetHeader> bhs = budgetHeaderRepository.findByObjectAudit(oa.getId());
        BudgetHeader bh;
        if (bhs.size() <= 0) {
            bh = findNewHeader(oa);
        } else {
            bh = bhs.get(0);
        }
        budget.setHeader(bh);
        budgetRepository.save(budget);
    }
}
