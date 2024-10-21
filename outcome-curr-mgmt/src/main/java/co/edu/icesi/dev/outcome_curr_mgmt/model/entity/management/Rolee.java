package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@NamedQuery(name = "Rolee.findAll", query = "SELECT r FROM Rolee r")
public class Rolee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ROLEE_ROLEEID_GENERATOR", sequenceName = "ROLEE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLEE_ROLEEID_GENERATOR")
    @Column(name = "ROLEE_ID")
    private long roleeId;

    //bi-directional many-to-one association to NotificationRole
    @OneToMany(mappedBy = "rolee")
    private List<NotificationRole> notificationRoles;

    public Rolee() {
        //Entity constructor
    }

    public long getRoleeId() {
        return this.roleeId;
    }

    public void setRoleeId(long roleeId) {
        this.roleeId = roleeId;
    }

    public List<NotificationRole> getNotificationRoles() {
        return this.notificationRoles;
    }

    public void setNotificationRoles(List<NotificationRole> notificationRoles) {
        this.notificationRoles = notificationRoles;
    }

    public NotificationRole addNotificationRole(NotificationRole notificationRole) {
        getNotificationRoles().add(notificationRole);
        notificationRole.setRolee(this);

        return notificationRole;
    }

    public NotificationRole removeNotificationRole(NotificationRole notificationRole) {
        getNotificationRoles().remove(notificationRole);
        notificationRole.setRolee(null);

        return notificationRole;
    }
}