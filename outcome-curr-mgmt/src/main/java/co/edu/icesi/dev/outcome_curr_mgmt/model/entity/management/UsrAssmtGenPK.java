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
public class UsrAssmtGenPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "USR_USR_ID", insertable = false, updatable = false)
    private long usrUsrId;

    @Column(name = "AGENP_ASGPLA_ID", insertable = false, updatable = false)
    private long agenpAsgplaId;

    public UsrAssmtGenPK() {
        //Entity constructor
    }

    public long getUsrUsrId() {
        return this.usrUsrId;
    }

    public void setUsrUsrId(long usrUsrId) {
        this.usrUsrId = usrUsrId;
    }

    public long getAgenpAsgplaId() {
        return this.agenpAsgplaId;
    }

    public void setAgenpAsgplaId(long agenpAsgplaId) {
        this.agenpAsgplaId = agenpAsgplaId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UsrAssmtGenPK)) {
            return false;
        }
        UsrAssmtGenPK castOther = (UsrAssmtGenPK) other;
        return
                (this.usrUsrId == castOther.usrUsrId)
                        && (this.agenpAsgplaId == castOther.agenpAsgplaId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.usrUsrId ^ (this.usrUsrId >>> 32)));
        hash = hash * prime + ((int) (this.agenpAsgplaId ^ (this.agenpAsgplaId >>> 32)));

        return hash;
    }
}