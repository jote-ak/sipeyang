package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class TindakLanjutItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = TindakLanjut.class)
    private TindakLanjut tindakLanjut;
    @Column(length = 128)
    private String rekomendasi;
    @Temporal(TemporalType.DATE)
    private Date jatuhTempo;
    BigDecimal nilaiResiko;
    @Column(length = 256)
    private String keterangan;
    @Column(length = 1)
    private String tertutup;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 19)
    private Date waktuTutup;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 19)
    private Date created;

}
