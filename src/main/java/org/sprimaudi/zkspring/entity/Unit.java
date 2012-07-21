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
}
