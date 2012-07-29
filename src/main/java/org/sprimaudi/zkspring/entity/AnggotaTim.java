package org.sprimaudi.zkspring.entity;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AnggotaTim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = Pegawai.class)
    private Pegawai pegawai;
    @ManyToOne(targetEntity = SuratTugas.class)
    private SuratTugas suratTugas;
    @ManyToOne(targetEntity = Referensi.class)
    private Referensi posisi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public Referensi getPosisi() {
        return posisi;
    }

    public void setPosisi(Referensi posisi) {
        this.posisi = this.posisi;
    }

    public SuratTugas getSuratTugas() {
        return suratTugas;
    }

    public void setSuratTugas(SuratTugas suratTugas) {
        this.suratTugas = suratTugas;
    }
}
