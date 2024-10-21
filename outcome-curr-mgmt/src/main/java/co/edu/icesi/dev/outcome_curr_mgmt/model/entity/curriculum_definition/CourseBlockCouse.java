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
@Table(name = "COURSE_BLOCK_COUSE")
@NamedQuery(name = "CourseBlockCouse.findAll", query = "SELECT c FROM CourseBlockCouse c")
public class CourseBlockCouse implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CourseBlockCousePK id;

    private String many2many;

    //bi-directional many-to-one association to Course
    @ManyToOne
    @JoinColumn(name = "CRS_COURSE_ID", insertable = false, updatable = false)
    private Course course;

    //bi-directional many-to-one association to CourseBlock
    @ManyToOne
    @JoinColumn(name = "BL_BLOCK_ID", insertable = false, updatable = false)
    private CourseBlock courseBlock;

    public CourseBlockCouse() {
        //Entity constructor
    }

    public CourseBlockCousePK getId() {
        return this.id;
    }

    public void setId(CourseBlockCousePK id) {
        this.id = id;
    }

    public String getMany2many() {
        return this.many2many;
    }

    public void setMany2many(String many2many) {
        this.many2many = many2many;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseBlock getCourseBlock() {
        return this.courseBlock;
    }

    public void setCourseBlock(CourseBlock courseBlock) {
        this.courseBlock = courseBlock;
    }

}