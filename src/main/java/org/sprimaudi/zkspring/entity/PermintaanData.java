package org.sprimaudi.zkspring.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class PermintaanData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(targetEntity = ObjectAudit.class)
    private Unit objectAudit;
    @OneToOne(targetEntity = SuratTugas.class)
    private SuratTugas suratTugas;
    @OneToMany(targetEntity = PermintaanDataItem.class, mappedBy = "permintaanData")
    private List<PermintaanDataItem> items;

}
