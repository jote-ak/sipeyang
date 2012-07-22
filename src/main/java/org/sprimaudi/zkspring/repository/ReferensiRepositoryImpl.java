package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.entity.QReferensi;
import org.sprimaudi.zkspring.entity.Referensi;
import org.sprimaudi.zkspring.filter.Fentry;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/21/12
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReferensiRepositoryImpl
        extends QueryDslRepositorySupport
        implements ReferensiRepositoryCustom {
    @Override
    public Iterable<Referensi> filter(List<Fentry> filterItem) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Referensi byKodeGrup(String kode, Long grup) {
        List<Referensi> refs = from(QReferensi.referensi)
                .where(QReferensi.referensi.grup.eq(grup)
                        .and(QReferensi.referensi.kode.equalsIgnoreCase(kode)))
                .list(QReferensi.referensi);
        return (refs.size() > 0) ? refs.get(0) : null;

    }

}
