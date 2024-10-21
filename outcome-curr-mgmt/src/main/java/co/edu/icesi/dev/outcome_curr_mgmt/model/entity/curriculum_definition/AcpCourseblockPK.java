package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

/**
 * The primary key class for the ACP_COURSEBLOCK database table.
 */

@Embeddable
@DataModelerGenerated
@Builder
@AllArgsConstructor
public class AcpCourseblockPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "ACADP_ACP_ID", insertable = false, updatable = false)
    private long acadpAcpId;

    @Column(name = "BL_BLOCK_ID", insertable = false, updatable = false)
    private long blBlockId;

    public AcpCourseblockPK() {
        //Entity constructor
    }

    public long getAcadpAcpId() {
        return this.acadpAcpId;
    }

    public void setAcadpAcpId(long acadpAcpId) {
        this.acadpAcpId = acadpAcpId;
    }

    public long getBlBlockId() {
        return this.blBlockId;
    }

    public void setBlBlockId(long blBlockId) {
        this.blBlockId = blBlockId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AcpCourseblockPK)) {
            return false;
        }
        AcpCourseblockPK castOther = (AcpCourseblockPK) other;
        return
                (this.acadpAcpId == castOther.acadpAcpId)
                        && (this.blBlockId == castOther.blBlockId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.acadpAcpId ^ (this.acadpAcpId >>> 32)));
        hash = hash * prime + ((int) (this.blBlockId ^ (this.blBlockId >>> 32)));

        return hash;
    }
}