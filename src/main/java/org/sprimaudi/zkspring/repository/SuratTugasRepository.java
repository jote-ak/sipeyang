package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.SuratTugas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/28/12
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SuratTugasRepository extends CrudRepository<SuratTugas, Long> {
    @Query("select st from SuratTugas st,ObjectAudit oa where st.objectAudit=oa and oa.id=?1 and st.primer='Y'")
    public SuratTugas findSTPrimer(Long idObjectAudit);
}
