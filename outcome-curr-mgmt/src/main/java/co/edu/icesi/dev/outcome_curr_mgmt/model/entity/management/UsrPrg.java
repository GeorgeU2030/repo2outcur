package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

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
@Table(name = "USR_PRG")
@NamedQuery(name = "UsrPrg.findAll", query = "SELECT u FROM UsrPrg u")
public class UsrPrg implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsrPrgPK id;

    private String many2many;

    //bi-directional many-to-one association to AcadProgram
    @ManyToOne
    @JoinColumn(name = "ACADP_ACP_ID", insertable = false, updatable = false)
    private AcadProgram acadProgram;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "USR_USR_ID", insertable = false, updatable = false)
    private User user;

    public UsrPrg() {
        //Entity constructor
    }

    public UsrPrgPK getId() {
        return this.id;
    }

    public void setId(UsrPrgPK id) {
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}