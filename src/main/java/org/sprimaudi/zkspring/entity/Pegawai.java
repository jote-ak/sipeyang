package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Pegawai implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 64)
    private String nama;
    @Column(length = 18)
    private String nip;
    @ManyToOne(targetEntity = Referensi.class)
    private Referensi pangkat;
    @ManyToOne(targetEntity = Referensi.class)
    private Referensi jabatan;
    @ManyToOne(targetEntity = Unit.class)
    private Unit unit;


    @OneToMany(mappedBy = "objectAudit", targetEntity = Budget.class)
    List<Budget> auditees;
    @OneToMany(mappedBy = "pegawai", targetEntity = AnggotaTim.class)
    List<AnggotaTim> anggotaTimByPegawai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Referensi getPangkat() {
        return pangkat;
    }

    public void setPangkat(Referensi pangkat) {
        this.pangkat = pangkat;
    }

    public Referensi getJabatan() {
        return jabatan;
    }

    public void setJabatan(Referensi jabatan) {
        this.jabatan = jabatan;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Budget> getAuditees() {
        return auditees;
    }

    public void setAuditees(List<Budget> auditees) {
        this.auditees = auditees;
    }

    public List<AnggotaTim> getAnggotaTimByPegawai() {
        return anggotaTimByPegawai;
    }

    public void setAnggotaTimByPegawai(List<AnggotaTim> anggotaTimByPegawai) {
        this.anggotaTimByPegawai = anggotaTimByPegawai;
    }
}
