package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "ASSMT_PLAN_OUT")
@NamedQuery(name = "AssmtPlanOut.findAll", query = "SELECT a FROM AssmtPlanOut a")
public class AssmtPlanOut implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ASSMT_PLAN_OUT_ASPNOUTID_GENERATOR", allocationSize = 1, sequenceName = "ASSMT_PLAN_OUT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSMT_PLAN_OUT_ASPNOUTID_GENERATOR")
    @Column(name = "ASPNOUT_ID")
    private long aspnoutId;

    @Column(name = "ASPNOUT_CDIO_LEVEL_3")
    private String aspnoutCdioLevel3;

    @Temporal(TemporalType.DATE)
    @Column(name = "ASPNOUT_EVALUATION_DATE")
    private Date aspnoutEvaluationDate;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "COLLECT_AC_PERIOD_ID")
    private AcPeriod collectAcPeriod;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "ASSESS_AC_PERIOD_ID")
    private AcPeriod assessAcPeriod;

    //bi-directional many-to-one association to AssmtPlanSubcyclev
    @ManyToOne
    @JoinColumn(name = "APLANSUB_ASGPLASUBCYCLE_ID")
    private AssmtPlanSubcyclev assmtPlanSubcyclev;

    //bi-directional many-to-one association to StudOutcome
    @ManyToOne
    @JoinColumn(name = "SO_SO_ID")
    private StudOutcome studOutcome;

    //bi-directional many-to-one association to AssmtPlanPi
    @OneToMany(mappedBy = "assmtPlanOut")
    private List<AssmtPlanPi> assmtPlanPis;

    public AssmtPlanOut() {
        //Entity constructor
    }

    public long getAspnoutId() {
        return this.aspnoutId;
    }

    public void setAspnoutId(long aspnoutId) {
        this.aspnoutId = aspnoutId;
    }

    public String getAspnoutCdioLevel3() {
        return this.aspnoutCdioLevel3;
    }

    public void setAspnoutCdioLevel3(String aspnoutCdioLevel3) {
        this.aspnoutCdioLevel3 = aspnoutCdioLevel3;
    }

    public Date getAspnoutEvaluationDate() {
        return this.aspnoutEvaluationDate;
    }

    public void setAspnoutEvaluationDate(Date aspnoutEvaluationDate) {
        this.aspnoutEvaluationDate = aspnoutEvaluationDate;
    }

    public AcPeriod getCollectAcPeriod() {
        return this.collectAcPeriod;
    }

    public void setCollectAcPeriod(AcPeriod acPeriod1) {
        this.collectAcPeriod = acPeriod1;
    }

    public AcPeriod getAssessAcPeriod() {
        return this.assessAcPeriod;
    }

    public void setAssessAcPeriod(AcPeriod acPeriod2) {
        this.assessAcPeriod = acPeriod2;
    }

    public AssmtPlanSubcyclev getAssmtPlanSubcyclev() {
        return this.assmtPlanSubcyclev;
    }

    public void setAssmtPlanSubcyclev(AssmtPlanSubcyclev assmtPlanSubcyclev) {
        this.assmtPlanSubcyclev = assmtPlanSubcyclev;
    }

    public StudOutcome getStudOutcome() {
        return this.studOutcome;
    }

    public void setStudOutcome(StudOutcome studOutcome) {
        this.studOutcome = studOutcome;
    }

    public List<AssmtPlanPi> getAssmtPlanPis() {
        return this.assmtPlanPis;
    }

    public void setAssmtPlanPis(List<AssmtPlanPi> assmtPlanPis) {
        this.assmtPlanPis = assmtPlanPis;
    }

    public AssmtPlanPi addAssmtPlanPi(AssmtPlanPi assmtPlanPi) {
        getAssmtPlanPis().add(assmtPlanPi);
        assmtPlanPi.setAssmtPlanOut(this);

        return assmtPlanPi;
    }

    public AssmtPlanPi removeAssmtPlanPi(AssmtPlanPi assmtPlanPi) {
        getAssmtPlanPis().remove(assmtPlanPi);
        assmtPlanPi.setAssmtPlanOut(null);

        return assmtPlanPi;
    }

}