package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
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
@Table(name = "USR_OUTCOME")
@NamedQuery(name = "UsrOutcome.findAll", query = "SELECT u FROM UsrOutcome u")
public class UsrOutcome implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsrOutcomePK id;

    private String many2many;

    //bi-directional many-to-one association to StudOutcome
    @ManyToOne
    @JoinColumn(name = "SO_SO_ID", insertable = false, updatable = false)
    private StudOutcome studOutcome;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "USR_USR_ID", insertable = false, updatable = false)
    private User user;

    public UsrOutcome() {
        //Entity constructor
    }

    public UsrOutcomePK getId() {
        return this.id;
    }

    public void setId(UsrOutcomePK id) {
        this.id = id;
    }

    public String getMany2many() {
        return this.many2many;
    }

    public void setMany2many(String many2many) {
        this.many2many = many2many;
    }

    public StudOutcome getStudOutcome() {
        return this.studOutcome;
    }

    public void setStudOutcome(StudOutcome studOutcome) {
        this.studOutcome = studOutcome;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}