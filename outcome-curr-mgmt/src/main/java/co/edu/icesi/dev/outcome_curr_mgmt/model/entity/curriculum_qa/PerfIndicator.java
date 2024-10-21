package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.EndGoal;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.rubric.Factor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "PERF_INDICATOR")
@NamedQuery(name = "PerfIndicator.findAll", query = "SELECT p FROM PerfIndicator p")
public class PerfIndicator extends Cell implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "PERF_INDICATOR_PIID_GENERATOR", allocationSize = 1, sequenceName = "PERF_INDICATOR_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERF_INDICATOR_PIID_GENERATOR")
    @Column(name = "PI_ID")
    private long piId;

    @Column(name = "PI_ACRONYM")
    private String piAcronym;

    @Column(name = "PI_LONG_NAME_ENG", length = 1000)
    private String piLongNameEng;

    @Column(name = "PI_LONG_NAME_SPA", length = 1000)
    private String piLongNameSpa;

    @Column(name = "PI_ORDINAL_NUMBER")
    private int piOrdinalNumber;

    @Column(name = "PI_SHORT_NAME_ENG")
    private String piShortNameEng;

    @Column(name = "PI_SHORT_NAME_SPA")
    private String piShortNameSpa;

    //bi-directional many-to-one association to AssmtPlanPi
    @OneToMany(mappedBy = "perfIndicator")
    private List<AssmtPlanPi> assmtPlanPis;

    //bi-directional many-to-one association to CurrMap
    @OneToMany(mappedBy = "perfIndicator")
    private List<CurrMap> currMaps;

    //bi-directional many-to-one association to Factor
    @OneToMany(mappedBy = "perfIndicator")
    private List<Factor> factors;

    //bi-directional many-to-one association to PerfindEndgoal
    @OneToMany(mappedBy = "perfIndicator")
    private List<PerfindEndgoal> perfindEndgoals;

    //bi-directional many-to-many association to EndGoal
    @ManyToMany
    @JoinTable(
            name = "PERFIND_ENDGOAL"
            , joinColumns = {
            @JoinColumn(name = "PI_PI_ID")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "EGOAL_EG_ID")
    }
    )
    private List<EndGoal> endGoals;

    //bi-directional many-to-one association to StudOutcome
    @ManyToOne
    @JoinColumn(name = "SO_SO_ID")
    private StudOutcome studOutcome;

    public PerfIndicator() {
        //Entity constructor
    }

    @Override
    public void initializeCellValues() {
        putKeyValueInMap("piId", String.valueOf(piId));
        putKeyValueInMap("pi", piLongNameEng); //TODO: ADD THE NAME IN SPANISH
        if (studOutcome != null)
            putKeyValueInMap("so", studOutcome.getSoLongNameEng()); //TODO: ADD THE NAME IN SPANISH
    }

    public long getPiId() {
        return this.piId;
    }

    public void setPiId(long piId) {
        this.piId = piId;
    }

    public String getPiAcronym() {
        return this.piAcronym;
    }

    public void setPiAcronym(String piAcronym) {
        this.piAcronym = piAcronym;
    }

    public String getPiLongNameEng() {
        return this.piLongNameEng;
    }

    public void setPiLongNameEng(String piLongNameEng) {
        this.piLongNameEng = piLongNameEng;
    }

    public String getPiLongNameSpa() {
        return this.piLongNameSpa;
    }

    public void setPiLongNameSpa(String piLongNameSpa) {
        this.piLongNameSpa = piLongNameSpa;
    }

    public int getPiOrdinalNumber() {
        return this.piOrdinalNumber;
    }

    public void setPiOrdinalNumber(int piOrdinalNumber) {
        this.piOrdinalNumber = piOrdinalNumber;
    }

    public String getPiShortNameEng() {
        return this.piShortNameEng;
    }

    public void setPiShortNameEng(String piShortNameEng) {
        this.piShortNameEng = piShortNameEng;
    }

    public String getPiShortNameSpa() {
        return this.piShortNameSpa;
    }

    public void setPiShortNameSpa(String piShortNameSpa) {
        this.piShortNameSpa = piShortNameSpa;
    }

    public List<AssmtPlanPi> getAssmtPlanPis() {
        return this.assmtPlanPis;
    }

    public void setAssmtPlanPis(List<AssmtPlanPi> assmtPlanPis) {
        this.assmtPlanPis = assmtPlanPis;
    }

    public AssmtPlanPi addAssmtPlanPi(AssmtPlanPi assmtPlanPi) {
        getAssmtPlanPis().add(assmtPlanPi);
        assmtPlanPi.setPerfIndicator(this);

        return assmtPlanPi;
    }

    public AssmtPlanPi removeAssmtPlanPi(AssmtPlanPi assmtPlanPi) {
        getAssmtPlanPis().remove(assmtPlanPi);
        assmtPlanPi.setPerfIndicator(null);

        return assmtPlanPi;
    }

    public List<CurrMap> getCurrMaps() {
        return this.currMaps;
    }

    public void setCurrMaps(List<CurrMap> currMaps) {
        this.currMaps = currMaps;
    }

    public CurrMap addCurrMap(CurrMap currMap) {
        getCurrMaps().add(currMap);
        currMap.setPerfIndicator(this);

        return currMap;
    }

    public CurrMap removeCurrMap(CurrMap currMap) {
        getCurrMaps().remove(currMap);
        currMap.setPerfIndicator(null);

        return currMap;
    }

    public List<Factor> getFactors() {
        return this.factors;
    }

    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }

    public Factor addFactor(Factor factor) {
        getFactors().add(factor);
        factor.setPerfIndicator(this);

        return factor;
    }

    public Factor removeFactor(Factor factor) {
        getFactors().remove(factor);
        factor.setPerfIndicator(null);

        return factor;
    }

    public List<PerfindEndgoal> getPerfindEndgoals() {
        return this.perfindEndgoals;
    }

    public void setPerfindEndgoals(List<PerfindEndgoal> perfindEndgoals) {
        this.perfindEndgoals = perfindEndgoals;
    }

    public PerfindEndgoal addPerfindEndgoal(PerfindEndgoal perfindEndgoal) {
        getPerfindEndgoals().add(perfindEndgoal);
        perfindEndgoal.setPerfIndicator(this);

        return perfindEndgoal;
    }

    public PerfindEndgoal removePerfindEndgoal(PerfindEndgoal perfindEndgoal) {
        getPerfindEndgoals().remove(perfindEndgoal);
        perfindEndgoal.setPerfIndicator(null);

        return perfindEndgoal;
    }

    public List<EndGoal> getEndGoals() {
        return this.endGoals;
    }

    public void setEndGoals(List<EndGoal> endGoals) {
        this.endGoals = endGoals;
    }

    public StudOutcome getStudOutcome() {
        return this.studOutcome;
    }

    public void setStudOutcome(StudOutcome studOutcome) {
        this.studOutcome = studOutcome;
    }

}