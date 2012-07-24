package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ObjectAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date awal;
    @Temporal(TemporalType.DATE)
    private Date akhir;
    @Column(length = 256)
    private String alasan;
    @Column(length = 64)
    private String topik;
    @Column(length = 256)
    private String keterangan;
    @Column(length = 12)
    private String npaNomor;
    @Temporal(TemporalType.DATE)
    private Date npaTanggal;
    @ManyToOne(targetEntity = Referensi.class)
    private Referensi status;
    @ManyToOne(targetEntity = Referensi.class)
    private Referensi auditeeJenis;
    @Column(length = 64)
    private String auditeeNama;
    @Column(length = 18)
    private String auditeeKode;
    @Column(length = 128)
    private String auditeeAlamat;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 19)
    private Date created;
    @OneToOne(targetEntity = Unit.class)
    private Unit unit;
    //
    //Relationship identifier
    //
    @OneToMany(targetEntity = PersonalAuditee.class, mappedBy = "objectAudit")
    List<PersonalAuditee> auditess;


    @OneToMany(mappedBy = "objectAudit", targetEntity = Budget.class)
    List<Budget> budget;

    @ManyToOne(targetEntity = Droa.class)
    private Droa droa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAwal() {
        return awal;
    }

    public void setAwal(Date awal) {
        this.awal = awal;
    }

    public Date getAkhir() {
        return akhir;
    }

    public void setAkhir(Date akhir) {
        this.akhir = akhir;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNpaNomor() {
        return npaNomor;
    }

    public void setNpaNomor(String npaNomor) {
        this.npaNomor = npaNomor;
    }

    public Date getNpaTanggal() {
        return npaTanggal;
    }

    public void setNpaTanggal(Date npaTanggal) {
        this.npaTanggal = npaTanggal;
    }

    public Referensi getStatus() {
        return status;
    }

    public void setStatus(Referensi status) {
        this.status = status;
    }

    public Referensi getAuditeeJenis() {
        return auditeeJenis;
    }

    public void setAuditeeJenis(Referensi auditeeJenis) {
        this.auditeeJenis = auditeeJenis;
    }

    public String getAuditeeNama() {
        return auditeeNama;
    }

    public void setAuditeeNama(String auditeeNama) {
        this.auditeeNama = auditeeNama;
    }

    public String getAuditeeKode() {
        return auditeeKode;
    }

    public void setAuditeeKode(String auditeeKode) {
        this.auditeeKode = auditeeKode;
    }

    public String getAuditeeAlamat() {
        return auditeeAlamat;
    }

    public void setAuditeeAlamat(String auditeeAlamat) {
        this.auditeeAlamat = auditeeAlamat;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<PersonalAuditee> getAuditess() {
        return auditess;
    }

    public void setAuditess(List<PersonalAuditee> auditess) {
        this.auditess = auditess;
    }

    public List<Budget> getBudget() {
        return budget;
    }

    public void setBudget(List<Budget> budget) {
        this.budget = budget;
    }

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
