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
@Table(name = "CB_ACADPCUR")
@NamedQuery(name = "CbAcadpcur.findAll", query = "SELECT c FROM CbAcadpcur c")
public class CbAcadpcur implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CbAcadpcurPK id;

    private String many2many;

    //bi-directional many-to-one association to AcadProgCurriculum
    @ManyToOne
    @JoinColumn(name = "ACADP_CUR_APC_ID", insertable = false, updatable = false)
    private AcadProgCurriculum acadProgCurriculum;

    //bi-directional many-to-one association to CourseBlock
    @ManyToOne
    @JoinColumn(name = "BL_BLOCK_ID", insertable = false, updatable = false)
    private CourseBlock courseBlock;

    public CbAcadpcur() {
        //Entity constructor
    }

    public CbAcadpcurPK getId() {
        return this.id;
    }

    public void setId(CbAcadpcurPK id) {
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

    public CourseBlock getCourseBlock() {
        return this.courseBlock;
    }

    public void setCourseBlock(CourseBlock courseBlock) {
        this.courseBlock = courseBlock;
    }

}