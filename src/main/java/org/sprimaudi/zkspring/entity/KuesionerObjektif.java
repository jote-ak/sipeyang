package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 7/31/12
 * Time: 8:41 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class KuesionerObjektif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 64)
    private String objektif;
    @Column(length = 6)
    private String kode;
    @Column(length = 256)
    private String keterangan;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 19)
    private Date created = new Date();

    @OneToMany(targetEntity = KuesionerReferensi.class, mappedBy = "objektif")
    private List<KuesionerReferensi> kuesioner;

    @ManyToOne(targetEntity = Referensi.class)
    private Referensi kategori;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjektif() {
        return objektif;
    }

    public void setObjektif(String objektif) {
        this.objektif = objektif;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<KuesionerReferensi> getKuesioner() {
        return kuesioner;
    }

    public void setKuesioner(List<KuesionerReferensi> kuesioner) {
        this.kuesioner = kuesioner;
    }

    public Referensi getKategori() {
        return kategori;
    }

    public void setKategori(Referensi kategori) {
        this.kategori = kategori;
    }
}
