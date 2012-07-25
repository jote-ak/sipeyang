package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 9:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class KuesionerJawaban implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1)
    private String pilihan;
    @Column(length = 1)
    private String pilihanGanda;
    @Column(length = 4)
    private String pilihanSetuju;
    @Column(length = 128)
    private String uraian;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPilihan() {
        return pilihan;
    }

    public void setPilihan(String pilihan) {
        this.pilihan = pilihan;
    }

    public String getPilihanGanda() {
        return pilihanGanda;
    }

    public void setPilihanGanda(String pilihanGanda) {
        this.pilihanGanda = pilihanGanda;
    }

    public String getPilihanSetuju() {
        return pilihanSetuju;
    }

    public void setPilihanSetuju(String pilihanSetuju) {
        this.pilihanSetuju = pilihanSetuju;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }
}
