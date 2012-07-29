package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.Pegawai;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/28/12
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PegawaiRepository extends CrudRepository<Pegawai, Long> {
    @Query("select p from Pegawai p where p.nip like ?1 or p.nama like ?1")
    public List<Pegawai> lookupNipNama(String lookup);
}
