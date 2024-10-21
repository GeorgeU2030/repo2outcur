package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
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
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "ASSMT_PLAN_PI")
@NamedQuery(name = "AssmtPlanPi.findAll", query = "SELECT a FROM AssmtPlanPi a")
public class AssmtPlanPi implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ASSMT_PLAN_PI_ASPNPIID_GENERATOR", allocationSize = 1, sequenceName = "ASSMT_PLAN_PI_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSMT_PLAN_PI_ASPNPIID_GENERATOR")
    @Column(name = "ASPNPI_ID")
    private long aspnpiId;

    @Column(name = "ASPNPI_ASSESSMENT_METHOD")
    private String aspnpiAssessmentMethod;

    @Column(name = "ASPNPI_FILE_URL")
    private String aspnpiFileUrl;

    //bi-directional many-to-one association to AssmtPlanOut
    @ManyToOne
    @JoinColumn(name = "APLANOUT_ASPNOUT_ID")
    private AssmtPlanOut assmtPlanOut;

    //bi-directional many-to-one association to PerfIndicator
    @ManyToOne
    @JoinColumn(name = "PI_PI_ID")
    private PerfIndicator perfIndicator;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "USR_USR_ID")
    private User user;

    public AssmtPlanPi() {
        //Entity constructor
    }

    public long getAspnpiId() {
        return this.aspnpiId;
    }

    public void setAspnpiId(long aspnpiId) {
        this.aspnpiId = aspnpiId;
    }

    public String getAspnpiAssessmentMethod() {
        return this.aspnpiAssessmentMethod;
    }

    public void setAspnpiAssessmentMethod(String aspnpiAssessmentMethod) {
        this.aspnpiAssessmentMethod = aspnpiAssessmentMethod;
    }

    public String getAspnpiFileUrl() {
        return this.aspnpiFileUrl;
    }

    public void setAspnpiFileUrl(String aspnpiFileUrl) {
        this.aspnpiFileUrl = aspnpiFileUrl;
    }

    public AssmtPlanOut getAssmtPlanOut() {
        return this.assmtPlanOut;
    }

    public void setAssmtPlanOut(AssmtPlanOut assmtPlanOut) {
        this.assmtPlanOut = assmtPlanOut;
    }

    public PerfIndicator getPerfIndicator() {
        return this.perfIndicator;
    }

    public void setPerfIndicator(PerfIndicator perfIndicator) {
        this.perfIndicator = perfIndicator;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}