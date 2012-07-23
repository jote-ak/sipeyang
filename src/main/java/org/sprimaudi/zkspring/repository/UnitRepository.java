package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.Unit;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/23/12
 * Time: 8:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UnitRepository extends CrudRepository<Unit, Long>, UnitRepositoryCustom {
}
