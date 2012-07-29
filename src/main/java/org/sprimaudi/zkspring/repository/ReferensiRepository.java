package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.Referensi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/21/12
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReferensiRepository extends
        CrudRepository<Referensi, Long>, ReferensiRepositoryCustom {
    @Query("from Referensi r where r.grup=? and kode=?")
    public Referensi byGrupAndKode(Long grup, String kode);

    @Query("from Referensi r where r.grup=?")
    public List<Referensi> byGrup(Long grup);
}
