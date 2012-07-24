package org.sprimaudi.zkspring.entity;

import bsh.UtilTargetError;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 7:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 18)
    private String kode;
    @Column(length = 64)
    private String nama;
    @Column(length = 128)
    private String alamat;
    @ManyToOne(targetEntity = Unit.class)
    private Unit parent;

    @OneToMany(mappedBy = "parent", targetEntity = Unit.class)
    List<Unit> cabangs;
    @OneToMany(mappedBy = "unit", targetEntity = Pegawai.class)
    List<Pegawai> pegawais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Unit getParent() {
        return parent;
    }

    public void setParent(Unit parent) {
        this.parent = parent;
    }

    public List<Unit> getCabangs() {
        return cabangs;
    }

    public void setCabangs(List<Unit> cabangs) {
        this.cabangs = cabangs;
    }

    public List<Pegawai> getPegawais() {
        return pegawais;
    }

    public void setPegawais(List<Pegawai> pegawais) {
        this.pegawais = pegawais;
    }
}
