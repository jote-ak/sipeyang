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
}
