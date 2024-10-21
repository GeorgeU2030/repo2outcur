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
public class UsrOffcoursePK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "OFC_OFC_ID", insertable = false, updatable = false)
    private long ofcOfcId;

    @Column(name = "USR_USR_ID", insertable = false, updatable = false)
    private long usrUsrId;

    public UsrOffcoursePK() {
        //Entity constructor
    }

    public long getOfcOfcId() {
        return this.ofcOfcId;
    }

    public void setOfcOfcId(long ofcOfcId) {
        this.ofcOfcId = ofcOfcId;
    }

    public long getUsrUsrId() {
        return this.usrUsrId;
    }

    public void setUsrUsrId(long usrUsrId) {
        this.usrUsrId = usrUsrId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UsrOffcoursePK)) {
            return false;
        }
        UsrOffcoursePK castOther = (UsrOffcoursePK) other;
        return
                (this.ofcOfcId == castOther.ofcOfcId)
                        && (this.usrUsrId == castOther.usrUsrId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.ofcOfcId ^ (this.ofcOfcId >>> 32)));
        hash = hash * prime + ((int) (this.usrUsrId ^ (this.usrUsrId >>> 32)));

        return hash;
    }
}