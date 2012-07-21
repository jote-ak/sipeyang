package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class KuesionerReferensi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 256)
    private String pertanyaan;
    @ManyToOne(targetEntity = Referensi.class)
    private Referensi jenis;
    @OneToMany(mappedBy = "kuesioner", targetEntity = Kuesioner.class)
    List<Kuesioner> kuesioners;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public Referensi getJenis() {
        return jenis;
    }

    public void setJenis(Referensi jenis) {
        this.jenis = jenis;
    }

    public List<Kuesioner> getKuesioners() {
        return kuesioners;
    }

    public void setKuesioners(List<Kuesioner> kuesioners) {
        this.kuesioners = kuesioners;
    }
}
