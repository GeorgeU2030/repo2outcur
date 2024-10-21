package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition;

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
public class CourseCurrPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "ACADP_CUR_APC_ID", insertable = false, updatable = false)
    private long acadpCurApcId;

    @Column(name = "CRS_COURSE_ID", insertable = false, updatable = false)
    private long crsCourseId;

    public CourseCurrPK() {
        //Entity constructor
    }

    public long getAcadpCurApcId() {
        return this.acadpCurApcId;
    }

    public void setAcadpCurApcId(long acadpCurApcId) {
        this.acadpCurApcId = acadpCurApcId;
    }

    public long getCrsCourseId() {
        return this.crsCourseId;
    }

    public void setCrsCourseId(long crsCourseId) {
        this.crsCourseId = crsCourseId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CourseCurrPK)) {
            return false;
        }
        CourseCurrPK castOther = (CourseCurrPK) other;
        return
                (this.acadpCurApcId == castOther.acadpCurApcId)
                        && (this.crsCourseId == castOther.crsCourseId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.acadpCurApcId ^ (this.acadpCurApcId >>> 32)));
        hash = hash * prime + ((int) (this.crsCourseId ^ (this.crsCourseId >>> 32)));

        return hash;
    }
}