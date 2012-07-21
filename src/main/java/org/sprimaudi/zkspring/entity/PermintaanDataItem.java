package org.sprimaudi.zkspring.entity;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 9:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class PermintaanDataItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 128)
    private String uraian;
    @ManyToOne(targetEntity = PermintaanData.class)
    private PermintaanData permintaanData;
}
