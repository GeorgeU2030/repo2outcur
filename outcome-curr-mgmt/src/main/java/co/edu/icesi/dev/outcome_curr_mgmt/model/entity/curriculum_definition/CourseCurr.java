package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "COURSE_CURR")
@NamedQuery(name = "CourseCurr.findAll", query = "SELECT c FROM CourseCurr c")
public class CourseCurr implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CourseCurrPK id;

    private String many2many;

    //bi-directional many-to-one association to AcadProgCurriculum
    @ManyToOne
    @JoinColumn(name = "ACADP_CUR_APC_ID", insertable = false, updatable = false)
    private AcadProgCurriculum acadProgCurriculum;

    //bi-directional many-to-one association to Course
    @ManyToOne
    @JoinColumn(name = "CRS_COURSE_ID", insertable = false, updatable = false)
    private Course course;

    public CourseCurr() {
        //Entity constructor
    }

    public CourseCurrPK getId() {
        return this.id;
    }

    public void setId(CourseCurrPK id) {
        this.id = id;
    }

    public String getMany2many() {
        return this.many2many;
    }

    public void setMany2many(String many2many) {
        this.many2many = many2many;
    }

    public AcadProgCurriculum getAcadProgCurriculum() {
        return this.acadProgCurriculum;
    }

    public void setAcadProgCurriculum(AcadProgCurriculum acadProgCurriculum) {
        this.acadProgCurriculum = acadProgCurriculum;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}