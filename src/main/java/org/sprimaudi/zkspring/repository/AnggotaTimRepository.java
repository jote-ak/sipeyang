package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.AnggotaTim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/28/12
 * Time: 12:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AnggotaTimRepository extends CrudRepository<AnggotaTim, Long> {
    @Query("select at from AnggotaTim at, SuratTugas st, ObjectAudit  oa" +
            " where at.suratTugas = st and st.objectAudit = oa and oa.id = ?1")
    public List<AnggotaTim> findRancanganTimByObjectAudit(Long idOa);
}
