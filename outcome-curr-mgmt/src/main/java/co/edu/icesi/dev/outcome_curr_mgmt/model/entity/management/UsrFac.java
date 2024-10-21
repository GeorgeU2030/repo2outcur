package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
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
@Table(name = "USR_FAC")
@NamedQuery(name = "UsrFac.findAll", query = "SELECT u FROM UsrFac u")
public class UsrFac implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsrFacPK id;

    private String many2many;

    //bi-directional many-to-one association to Faculty
    @ManyToOne
    @JoinColumn(name = "FAC_FAC_ID", insertable = false, updatable = false)
    private Faculty faculty;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "USR_USR_ID", insertable = false, updatable = false)
    private User user;

    public UsrFac() {
        //Entity constructor
    }

    public UsrFacPK getId() {
        return this.id;
    }

    public void setId(UsrFacPK id) {
        this.id = id;
    }

    public String getMany2many() {
        return this.many2many;
    }

    public void setMany2many(String many2many) {
        this.many2many = many2many;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}