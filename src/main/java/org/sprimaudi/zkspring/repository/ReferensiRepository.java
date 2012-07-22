package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.Referensi;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/21/12
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReferensiRepository extends
        CrudRepository<Referensi, Long>, ReferensiRepositoryCustom {
}
