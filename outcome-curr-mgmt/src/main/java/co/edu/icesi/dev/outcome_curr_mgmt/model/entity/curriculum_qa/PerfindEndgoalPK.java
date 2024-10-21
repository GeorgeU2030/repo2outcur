package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

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
public class PerfindEndgoalPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "PI_PI_ID", insertable = false, updatable = false)
    private long piPiId;

    @Column(name = "EGOAL_EG_ID", insertable = false, updatable = false)
    private long egoalEgId;

    public PerfindEndgoalPK() {
        //Entity constructor
    }

    public long getPiPiId() {
        return this.piPiId;
    }

    public void setPiPiId(long piPiId) {
        this.piPiId = piPiId;
    }

    public long getEgoalEgId() {
        return this.egoalEgId;
    }

    public void setEgoalEgId(long egoalEgId) {
        this.egoalEgId = egoalEgId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PerfindEndgoalPK)) {
            return false;
        }
        PerfindEndgoalPK castOther = (PerfindEndgoalPK) other;
        return
                (this.piPiId == castOther.piPiId)
                        && (this.egoalEgId == castOther.egoalEgId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.piPiId ^ (this.piPiId >>> 32)));
        hash = hash * prime + ((int) (this.egoalEgId ^ (this.egoalEgId >>> 32)));

        return hash;
    }
}