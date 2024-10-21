package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "NOTIFICATION_ROLE")
@NamedQuery(name = "NotificationRole.findAll", query = "SELECT n FROM NotificationRole n")
public class NotificationRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private NotificationRolePK id;

    private String many2many;

    //bi-directional many-to-one association to Notification
    @ManyToOne
    @JoinColumn(name = "NOT_NOTI_ID", insertable = false, updatable = false)
    private Notification notification;

    //bi-directional many-to-one association to Rolee
    @ManyToOne
    @JoinColumn(name = "ROLEE_ROLE_ID", insertable = false, updatable = false)
    private Rolee rolee;

    public NotificationRole() {
        //Entity constructor
    }

    public NotificationRolePK getId() {
        return this.id;
    }

    public void setId(NotificationRolePK id) {
        this.id = id;
    }

    public String getMany2many() {
        return this.many2many;
    }

    public void setMany2many(String many2many) {
        this.many2many = many2many;
    }

    public Notification getNotification() {
        return this.notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Rolee getRolee() {
        return this.rolee;
    }

    public void setRolee(Rolee rolee) {
        this.rolee = rolee;
    }

}