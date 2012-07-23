package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.Unit;
import org.sprimaudi.zkspring.filter.Fentry;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/23/12
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnitRepositoryImpl
        extends QueryDslRepositorySupport
        implements UnitRepositoryCustom {
    @Override
    public Iterable<Unit> filter(List<Fentry> filterItem) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
