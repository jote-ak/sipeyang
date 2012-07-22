package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.Referensi;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/21/12
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReferensiRepositoryCustom
        extends FilterRepository<Referensi> {

    Referensi byKodeGrup(String kode, Long grup);
}
