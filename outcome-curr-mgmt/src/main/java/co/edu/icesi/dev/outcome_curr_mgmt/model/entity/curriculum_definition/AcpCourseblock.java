package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
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
@Table(name = "ACP_COURSEBLOCK")
@NamedQuery(name = "AcpCourseblock.findAll", query = "SELECT a FROM AcpCourseblock a")
public class AcpCourseblock implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AcpCourseblockPK id;

    private String many2many;

    //bi-directional many-to-one association to AcadProgram
    @ManyToOne
    @JoinColumn(name = "ACADP_ACP_ID", insertable = false, updatable = false)
    private AcadProgram acadProgram;

    //bi-directional many-to-one association to CourseBlock
    @ManyToOne
    @JoinColumn(name = "BL_BLOCK_ID", insertable = false, updatable = false)
    private CourseBlock courseBlock;

    public AcpCourseblock() {
        //Entity constructor
    }

    public AcpCourseblockPK getId() {
        return this.id;
    }

    public void setId(AcpCourseblockPK id) {
        this.id = id;
    }

    public String getMany2many() {
        return this.many2many;
    }

    public void setMany2many(String many2many) {
        this.many2many = many2many;
    }

    public AcadProgram getAcadProgram() {
        return this.acadProgram;
    }

    public void setAcadProgram(AcadProgram acadProgram) {
        this.acadProgram = acadProgram;
    }

    public CourseBlock getCourseBlock() {
        return this.courseBlock;
    }

    public void setCourseBlock(CourseBlock courseBlock) {
        this.courseBlock = courseBlock;
    }

}