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
public class UsrPrgPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "USR_USR_ID", insertable = false, updatable = false)
    private long usrUsrId;

    @Column(name = "ACADP_ACP_ID", insertable = false, updatable = false)
    private long acadpAcpId;

    public UsrPrgPK() {
        //Entity constructor
    }

    public long getUsrUsrId() {
        return this.usrUsrId;
    }

    public void setUsrUsrId(long usrUsrId) {
        this.usrUsrId = usrUsrId;
    }

    public long getAcadpAcpId() {
        return this.acadpAcpId;
    }

    public void setAcadpAcpId(long acadpAcpId) {
        this.acadpAcpId = acadpAcpId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UsrPrgPK)) {
            return false;
        }
        UsrPrgPK castOther = (UsrPrgPK) other;
        return
                (this.usrUsrId == castOther.usrUsrId)
                        && (this.acadpAcpId == castOther.acadpAcpId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.usrUsrId ^ (this.usrUsrId >>> 32)));
        hash = hash * prime + ((int) (this.acadpAcpId ^ (this.acadpAcpId >>> 32)));

        return hash;
    }
}