package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 8:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class SuratTugas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 12)
    private String nomor;
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Temporal(TemporalType.DATE)
    private Date awal;
    @Temporal(TemporalType.DATE)
    private Date akhir;
    @Column(length = 18)
    private String nipTtd;
    @Column(length = 128)
    private String alamat;
    @Column(length = 64)
    private String tujuan;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 19)
    private Date created;
}
