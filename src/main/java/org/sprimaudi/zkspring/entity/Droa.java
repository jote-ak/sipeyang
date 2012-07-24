package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Droa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 12)
    private String nomor;
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    private Long tahun;
    @Column(length = 256)
    private String keterangan;
    @ManyToOne(targetEntity = Referensi.class)
    private Referensi status;
    @ManyToOne(targetEntity = Referensi.class)
    private Referensi jenis;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 19)
    private Date created = new Date();

    //Listing referensi
    @OneToMany(targetEntity = ObjectAudit.class, mappedBy = "droa")
    private List<ObjectAudit> audits;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Long getTahun() {
        return tahun;
    }

    public void setTahun(Long tahun) {
        this.tahun = tahun;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Referensi getStatus() {
        return status;
    }

    public void setStatus(Referensi status) {
        this.status = status;
    }

    public Referensi getJenis() {
        return jenis;
    }

    public void setJenis(Referensi jenis) {
        this.jenis = jenis;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
