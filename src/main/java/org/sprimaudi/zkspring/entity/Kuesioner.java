package org.sprimaudi.zkspring.entity;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 9:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Kuesioner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = ObjectAudit.class)
    private Unit objectAudit;
    @ManyToOne(targetEntity = KuesionerReferensi.class)
    KuesionerReferensi kuesioner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Unit getObjectAudit() {
        return objectAudit;
    }

    public void setObjectAudit(Unit objectAudit) {
        this.objectAudit = objectAudit;
    }

    public KuesionerReferensi getKuesioner() {
        return kuesioner;
    }

    public void setKuesioner(KuesionerReferensi kuesioner) {
        this.kuesioner = kuesioner;
    }
}
