package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.EndGoal;
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
@Table(name = "PERFIND_ENDGOAL")
@NamedQuery(name = "PerfindEndgoal.findAll", query = "SELECT p FROM PerfindEndgoal p")
public class PerfindEndgoal implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PerfindEndgoalPK id;

    private String many2many;

    //bi-directional many-to-one association to EndGoal
    @ManyToOne
    @JoinColumn(name = "EGOAL_EG_ID", insertable = false, updatable = false)
    private EndGoal endGoal;

    //bi-directional many-to-one association to PerfIndicator
    @ManyToOne
    @JoinColumn(name = "PI_PI_ID", insertable = false, updatable = false)
    private PerfIndicator perfIndicator;

    public PerfindEndgoal() {
        //Entity constructor
    }

    public PerfindEndgoalPK getId() {
        return this.id;
    }

    public void setId(PerfindEndgoalPK id) {
        this.id = id;
    }

    public String getMany2many() {
        return this.many2many;
    }

    public void setMany2many(String many2many) {
        this.many2many = many2many;
    }

    public EndGoal getEndGoal() {
        return this.endGoal;
    }

    public void setEndGoal(EndGoal endGoal) {
        this.endGoal = endGoal;
    }

    public PerfIndicator getPerfIndicator() {
        return this.perfIndicator;
    }

    public void setPerfIndicator(PerfIndicator perfIndicator) {
        this.perfIndicator = perfIndicator;
    }

}