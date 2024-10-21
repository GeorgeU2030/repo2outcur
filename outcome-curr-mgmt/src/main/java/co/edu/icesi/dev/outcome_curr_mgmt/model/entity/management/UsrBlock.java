package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.CourseBlock;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * The persistent class for the USR_BLOCK database table.
 *
 */
import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "USR_BLOCK")
@NamedQuery(name = "UsrBlock.findAll", query = "SELECT u FROM UsrBlock u")
public class UsrBlock implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsrBlockPK id;

    private String many2many;

    //bi-directional many-to-one association to CourseBlock
    @ManyToOne
    @JoinColumn(name = "BL_BLOCK_ID", insertable = false, updatable = false)
    private CourseBlock courseBlock;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "USR_USR_ID", insertable = false, updatable = false)
    private User user;

    public UsrBlock() {
        //Entity constructor
    }

    public UsrBlockPK getId() {
        return this.id;
    }

    public void setId(UsrBlockPK id) {
        this.id = id;
    }

    public String getMany2many() {
        return this.many2many;
    }

    public void setMany2many(String many2many) {
        this.many2many = many2many;
    }

    public CourseBlock getCourseBlock() {
        return this.courseBlock;
    }

    public void setCourseBlock(CourseBlock courseBlock) {
        this.courseBlock = courseBlock;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}