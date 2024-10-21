package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

/**
 * The primary key class for the CB_ACADPCUR database table.
 */

@Embeddable
@DataModelerGenerated
@Builder
@AllArgsConstructor
public class CbAcadpcurPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "BL_BLOCK_ID", insertable = false, updatable = false)
    private long blBlockId;

    @Column(name = "ACADP_CUR_APC_ID", insertable = false, updatable = false)
    private long acadpCurApcId;

    public CbAcadpcurPK() {
        //Entity constructor
    }

    public long getBlBlockId() {
        return this.blBlockId;
    }

    public void setBlBlockId(long blBlockId) {
        this.blBlockId = blBlockId;
    }

    public long getAcadpCurApcId() {
        return this.acadpCurApcId;
    }

    public void setAcadpCurApcId(long acadpCurApcId) {
        this.acadpCurApcId = acadpCurApcId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CbAcadpcurPK)) {
            return false;
        }
        CbAcadpcurPK castOther = (CbAcadpcurPK) other;
        return
                (this.blBlockId == castOther.blBlockId)
                        && (this.acadpCurApcId == castOther.acadpCurApcId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.blBlockId ^ (this.blBlockId >>> 32)));
        hash = hash * prime + ((int) (this.acadpCurApcId ^ (this.acadpCurApcId >>> 32)));

        return hash;
    }
}