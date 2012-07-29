package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private Date created = new Date();
    /**
     * Menyatakan status surat tugas sebagai surat tugas pertama.
     * Surat tugas pertama dibuat pada sesi perencanaan untuk menyusun tim audit
     */
    @Column(length = 1)
    private String primer;
    @OneToMany(targetEntity = AnggotaTim.class, mappedBy = "suratTugas")
    private List<AnggotaTim> tim;

    @ManyToOne(targetEntity = ObjectAudit.class)
    private ObjectAudit objectAudit;

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

    public String getNipTtd() {
        return nipTtd;
    }

    public void setNipTtd(String nipTtd) {
        this.nipTtd = nipTtd;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPrimer() {
        return primer;
    }

    public void setPrimer(String primer) {
        this.primer = primer;
    }

    public List<AnggotaTim> getTim() {
        return tim;
    }

    public void setTim(List<AnggotaTim> tim) {
        this.tim = tim;
    }

    public ObjectAudit getObjectAudit() {
        return objectAudit;
    }

    public void setObjectAudit(ObjectAudit objectAudit) {
        this.objectAudit = objectAudit;
    }
}
