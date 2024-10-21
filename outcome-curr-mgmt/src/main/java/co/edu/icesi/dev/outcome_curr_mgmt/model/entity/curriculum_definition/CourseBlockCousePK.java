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
public class CourseBlockCousePK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "BL_BLOCK_ID", insertable = false, updatable = false)
    private long blBlockId;

    @Column(name = "CRS_COURSE_ID", insertable = false, updatable = false)
    private long crsCourseId;

    public CourseBlockCousePK() {
        //Entity constructor
    }

    public long getBlBlockId() {
        return this.blBlockId;
    }

    public void setBlBlockId(long blBlockId) {
        this.blBlockId = blBlockId;
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
        if (!(other instanceof CourseBlockCousePK)) {
            return false;
        }
        CourseBlockCousePK castOther = (CourseBlockCousePK) other;
        return
                (this.blBlockId == castOther.blBlockId)
                        && (this.crsCourseId == castOther.crsCourseId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.blBlockId ^ (this.blBlockId >>> 32)));
        hash = hash * prime + ((int) (this.crsCourseId ^ (this.crsCourseId >>> 32)));

        return hash;
    }
}