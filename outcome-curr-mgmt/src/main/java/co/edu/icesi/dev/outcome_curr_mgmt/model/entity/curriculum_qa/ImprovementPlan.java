package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "IMPROVEMENT_PLAN")
@NamedQuery(name = "ImprovementPlan.findAll", query = "SELECT i FROM ImprovementPlan i")
public class ImprovementPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "IMPROVEMENT_PLAN_IMPID_GENERATOR", allocationSize = 1, sequenceName = "IMPROVEMENT_PLAN_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMPROVEMENT_PLAN_IMPID_GENERATOR")
    @Column(name = "IMP_ID")
    private long impId;

    private String author;

    @Column(name = "IMP_ACTION")
    private String impAction;

    @Temporal(TemporalType.DATE)
    @Column(name = "TIME_STAMP")
    private Date timeStamp;

    //bi-directional many-to-one association to AssmtGenPlan
    @ManyToOne
    @JoinColumn(name = "AGENP_ASGPLA_ID")
    private AssmtGenPlan assmtGenPlan;

    public ImprovementPlan() {
        //Entity constructor
    }

    public long getImpId() {
        return this.impId;
    }

    public void setImpId(long impId) {
        this.impId = impId;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImpAction() {
        return this.impAction;
    }

    public void setImpAction(String impAction) {
        this.impAction = impAction;
    }

    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public AssmtGenPlan getAssmtGenPlan() {
        return this.assmtGenPlan;
    }

    public void setAssmtGenPlan(AssmtGenPlan assmtGenPlan) {
        this.assmtGenPlan = assmtGenPlan;
    }

}