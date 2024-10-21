package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_instance.OfferedCourse;
import jakarta.persistence.Column;
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
@Table(name = "USR_OFFCOURSE")
@NamedQuery(name = "UsrOffcourse.findAll", query = "SELECT u FROM UsrOffcourse u")
public class UsrOffcourse implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsrOffcoursePK id;

    @Column(name = "USFOFC_IS_TEACHER")
    private String usfofcIsTeacher;

    //bi-directional many-to-one association to OfferedCourse
    @ManyToOne
    @JoinColumn(name = "OFC_OFC_ID", insertable = false, updatable = false)
    private OfferedCourse offeredCourse;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "USR_USR_ID", insertable = false, updatable = false)
    private User user;

    public UsrOffcourse() {
        //Entity constructor
    }

    public UsrOffcoursePK getId() {
        return this.id;
    }

    public void setId(UsrOffcoursePK id) {
        this.id = id;
    }

    public String getUsfofcIsTeacher() {
        return this.usfofcIsTeacher;
    }

    public void setUsfofcIsTeacher(String usfofcIsTeacher) {
        this.usfofcIsTeacher = usfofcIsTeacher;
    }

    public OfferedCourse getOfferedCourse() {
        return this.offeredCourse;
    }

    public void setOfferedCourse(OfferedCourse offeredCourse) {
        this.offeredCourse = offeredCourse;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}