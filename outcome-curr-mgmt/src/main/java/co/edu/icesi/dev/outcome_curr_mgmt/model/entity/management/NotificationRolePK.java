package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Embeddable
@DataModelerGenerated
@Builder
@AllArgsConstructor
public class NotificationRolePK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "NOT_NOTI_ID", insertable = false, updatable = false)
    private long notNotiId;

    @Column(name = "ROLEE_ROLE_ID", insertable = false, updatable = false)
    private long roleeRoleId;

    public NotificationRolePK() {
        //Entity constructor
    }

    public long getNotNotiId() {
        return this.notNotiId;
    }

    public void setNotNotiId(long notNotiId) {
        this.notNotiId = notNotiId;
    }

    public long getRoleeRoleId() {
        return this.roleeRoleId;
    }

    public void setRoleeRoleId(long roleeRoleId) {
        this.roleeRoleId = roleeRoleId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotificationRolePK)) {
            return false;
        }
        NotificationRolePK castOther = (NotificationRolePK) other;
        return
                (this.notNotiId == castOther.notNotiId)
                        && (this.roleeRoleId == castOther.roleeRoleId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.notNotiId ^ (this.notNotiId >>> 32)));
        hash = hash * prime + ((int) (this.roleeRoleId ^ (this.roleeRoleId >>> 32)));

        return hash;
    }
}