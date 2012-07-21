package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 7:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal nilai;
    private BigDecimal realisasi;
    @Column(length = 512)
    private String keterangan;
    @ManyToOne(targetEntity = ObjectAudit.class)
    private Unit objectAudit;
    @ManyToOne(targetEntity = Referensi.class)
    private Referensi jenis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getRealisasi() {
        return realisasi;
    }

    public void setRealisasi(BigDecimal realisasi) {
        this.realisasi = realisasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Unit getObjectAudit() {
        return objectAudit;
    }

    public void setObjectAudit(Unit objectAudit) {
        this.objectAudit = objectAudit;
    }

    public Referensi getJenis() {
        return jenis;
    }

    public void setJenis(Referensi jenis) {
        this.jenis = jenis;
    }
}
