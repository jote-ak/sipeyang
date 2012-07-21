package org.sprimaudi.zkspring.entity;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/20/12
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 * incase
 */
@Entity
public class PersonalAuditee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = ObjectAudit.class)
    ObjectAudit objectAudit;
    @ManyToOne(targetEntity = Pegawai.class)
    Pegawai pegawai;

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

    public ObjectAudit getObjectAudit() {
        return objectAudit;
    }

    public void setObjectAudit(ObjectAudit objectAudit) {
        this.objectAudit = objectAudit;
    }
}
