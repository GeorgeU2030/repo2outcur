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
@Table(name = "PI_LVL_CATEG")
@NamedQuery(name = "PiLvlCateg.findAll", query = "SELECT p FROM PiLvlCateg p")
public class PiLvlCateg implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "PI_LVL_CATEG_CATEGID_GENERATOR", allocationSize = 1, sequenceName = "PI_LVL_CATEG_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PI_LVL_CATEG_CATEGID_GENERATOR")
    @Column(name = "CATEG_ID")
    private long categId;

    @Column(name = "CATEG_IS_ACTIVE")
    private char categIsActive;

    @Column(name = "CATEG_NAME_ENG")
    private String categNameEng;

    @Column(name = "CATEG_NAME_SPA")
    private String categNameSpa;

    @Column(name = "CATEG_POSITION")
    private int categPosition;

    //bi-directional many-to-one association to CurrMap
    @OneToMany(mappedBy = "piLvlCateg")
    private List<CurrMap> currMaps;

    //bi-directional many-to-one association to AcadProgram
    @ManyToOne
    @JoinColumn(name = "ACADP_ACP_ID")
    private AcadProgram acadProgram;

    public PiLvlCateg() {
        //Entity constructor
    }

    public long getCategId() {
        return this.categId;
    }

    public void setCategId(long categId) {
        this.categId = categId;
    }

    public char getCategIsActive() {
        return this.categIsActive;
    }

    public void setCategIsActive(char categIsActive) {
        this.categIsActive = categIsActive;
    }

    public String getCategNameEng() {
        return this.categNameEng;
    }

    public void setCategNameEng(String categNameEng) {
        this.categNameEng = categNameEng;
    }

    public String getCategNameSpa() {
        return this.categNameSpa;
    }

    public void setCategNameSpa(String categNameSpa) {
        this.categNameSpa = categNameSpa;
    }

    public int getCategPosition() {
        return this.categPosition;
    }

    public void setCategPosition(int categPosition) {
        this.categPosition = categPosition;
    }

    public List<CurrMap> getCurrMaps() {
        return this.currMaps;
    }

    public void setCurrMaps(List<CurrMap> currMaps) {
        this.currMaps = currMaps;
    }

    public CurrMap addCurrMap(CurrMap currMap) {
        getCurrMaps().add(currMap);
        currMap.setPiLvlCateg(this);

        return currMap;
    }

    public CurrMap removeCurrMap(CurrMap currMap) {
        getCurrMaps().remove(currMap);
        currMap.setPiLvlCateg(null);

        return currMap;
    }

    public AcadProgram getAcadProgram() {
        return this.acadProgram;
    }

    public void setAcadProgram(AcadProgram acadProgram) {
        this.acadProgram = acadProgram;
    }

}