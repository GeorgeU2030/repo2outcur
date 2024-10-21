package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.CurrMap;
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
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "ASSESSMENT_TYPE")
@NamedQuery(name = "AssessmentType.findAll", query = "SELECT a FROM AssessmentType a")
public class AssessmentType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ASSESSMENT_TYPE_ATID_GENERATOR", allocationSize = 1, sequenceName = "ASSESSMENT_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSESSMENT_TYPE_ATID_GENERATOR")
    @Column(name = "AT_ID")
    private long atId;

    @Column(name = "AT_IS_ACTIVE")
    private char atIsActive;

    @Column(name = "AT_NAME_ENG")
    private String atNameEng;

    @Column(name = "AT_NAME_SPA")
    private String atNameSpa;

    //bi-directional many-to-one association to AcadProgram
    @ManyToOne
    @JoinColumn(name = "ACADP_ACP_ID")
    private AcadProgram acadProgram;

    //bi-directional many-to-one association to CurrMap
    @OneToMany(mappedBy = "assessmentType")
    private List<CurrMap> currMaps;

    public AssessmentType() {
        //Entity constructor
    }

    public long getAtId() {
        return this.atId;
    }

    public void setAtId(long atId) {
        this.atId = atId;
    }

    public char getAtIsActive() {
        return this.atIsActive;
    }

    public void setAtIsActive(char atIsActive) {
        this.atIsActive = atIsActive;
    }

    public String getAtNameEng() {
        return this.atNameEng;
    }

    public void setAtNameEng(String atNameEng) {
        this.atNameEng = atNameEng;
    }

    public String getAtNameSpa() {
        return this.atNameSpa;
    }

    public void setAtNameSpa(String atNameSpa) {
        this.atNameSpa = atNameSpa;
    }

    public AcadProgram getAcadProgram() {
        return this.acadProgram;
    }

    public void setAcadProgram(AcadProgram acadProgram) {
        this.acadProgram = acadProgram;
    }

    public List<CurrMap> getCurrMaps() {
        return this.currMaps;
    }

    public void setCurrMaps(List<CurrMap> currMaps) {
        this.currMaps = currMaps;
    }

    public CurrMap addCurrMap(CurrMap currMap) {
        getCurrMaps().add(currMap);
        currMap.setAssessmentType(this);

        return currMap;
    }

    public CurrMap removeCurrMap(CurrMap currMap) {
        getCurrMaps().remove(currMap);
        currMap.setAssessmentType(null);

        return currMap;
    }

}