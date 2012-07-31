package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/30/12
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class BudgetHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 12)
    private String nomor;
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @ManyToOne(targetEntity = SuratTugas.class)
    SuratTugas suratTugas;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 19)
    private Date created = new Date();
    @Column(length = 256)
    private String keterangan;
    private Integer seri;
    @ManyToOne(targetEntity = ObjectAudit.class)
    ObjectAudit objectAudit;

    @OneToMany(targetEntity = Budget.class, mappedBy = "header")
    private List<Budget> budjets;

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

    public SuratTugas getSuratTugas() {
        return suratTugas;
    }

    public void setSuratTugas(SuratTugas suratTugas) {
        this.suratTugas = suratTugas;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getSeri() {
        return seri;
    }

    public void setSeri(Integer seri) {
        this.seri = seri;
    }

    public ObjectAudit getObjectAudit() {
        return objectAudit;
    }

    public void setObjectAudit(ObjectAudit objectAudit) {
        this.objectAudit = objectAudit;
    }
}
