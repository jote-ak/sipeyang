package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Temuan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(targetEntity = ObjectAudit.class)
    private Unit objectAudit;
    @Column(length = 12)
    private String nomor;
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Column(length = 256)
    private String keterangan;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 19)
    private Date created;
    @OneToMany(targetEntity = TemuanItem.class, mappedBy = "temuan")
    List<TemuanItem> temuans;

}
