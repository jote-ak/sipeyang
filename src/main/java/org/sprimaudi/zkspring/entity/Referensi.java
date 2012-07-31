/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author User
 */
@Entity
public class Referensi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long grup;
    @Column(length = 6)
    private String kode;
    @Column(length = 32)
    private String nama;
    @Column(length = 512)
    private String keterangan;
    private Integer aktif = 1;
    @Column(length = 64)
    private String isi;
    private Long num;
    private Long nilai;
    //=====================
    //list of reference type
    //=====================
    @OneToMany(mappedBy = "status", targetEntity = Droa.class)
    List<Droa> droaByStatus;
    @OneToMany(mappedBy = "jenis", targetEntity = Droa.class)
    List<Droa> droaByJenis;

    @OneToMany(mappedBy = "status", targetEntity = ObjectAudit.class)
    List<ObjectAudit> objectAuditByStatus;
    @OneToMany(mappedBy = "auditeeJenis", targetEntity = ObjectAudit.class)
    List<ObjectAudit> objectAuditByJenisAuditee;

    @OneToMany(mappedBy = "pangkat", targetEntity = Pegawai.class)
    List<ObjectAudit> objectAuditByPangkat;
    @OneToMany(mappedBy = "jabatan", targetEntity = Pegawai.class)
    List<ObjectAudit> objectAuditByJabatan;
    @OneToMany(mappedBy = "jenis", targetEntity = Budget.class)
    List<Budget> budgetByJenis;
    @OneToMany(mappedBy = "posisi", targetEntity = AnggotaTim.class)
    List<AnggotaTim> AnggotaTimByPosisi;

    @OneToMany(mappedBy = "jenis", targetEntity = KuesionerReferensi.class)
    List<KuesionerReferensi> kuesionerByJenis;
    @OneToMany(mappedBy = "kategori", targetEntity = KuesionerObjektif.class)
    List<KuesionerReferensi> kuesionerByKategori;

    public Long getGrup() {
        return grup;
    }

    public void setGrup(Long grup) {
        this.grup = grup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public Long getNilai() {
        return nilai;
    }

    public void setNilai(Long nilai) {
        this.nilai = nilai;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Integer getAktif() {
        return aktif;
    }

    public void setAktif(Integer aktif) {
        this.aktif = aktif;
    }
}
