package org.sprimaudi.zkspring.entity;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class TemuanItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = Temuan.class)
    private Temuan temuan;
    @Column(length = 256)
    private String uraian;
    @Column(length = 256)
    private String tanggapan;

}
