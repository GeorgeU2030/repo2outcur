package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
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
@Table(name = "USR_ASSMT_GEN")
@NamedQuery(name = "UsrAssmtGen.findAll", query = "SELECT u FROM UsrAssmtGen u")
public class UsrAssmtGen implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsrAssmtGenPK id;

    private String many2many;

    //bi-directional many-to-one association to AssmtGenPlan
    @ManyToOne
    @JoinColumn(name = "AGENP_ASGPLA_ID", insertable = false, updatable = false)
    private AssmtGenPlan assmtGenPlan;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "USR_USR_ID", insertable = false, updatable = false)
    private User user;

    public UsrAssmtGen() {
        //Entity constructor
    }

    public UsrAssmtGenPK getId() {
        return this.id;
    }

    public void setId(UsrAssmtGenPK id) {
        this.id = id;
    }

    public String getMany2many() {
        return this.many2many;
    }

    public void setMany2many(String many2many) {
        this.many2many = many2many;
    }

    public AssmtGenPlan getAssmtGenPlan() {
        return this.assmtGenPlan;
    }

    public void setAssmtGenPlan(AssmtGenPlan assmtGenPlan) {
        this.assmtGenPlan = assmtGenPlan;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}