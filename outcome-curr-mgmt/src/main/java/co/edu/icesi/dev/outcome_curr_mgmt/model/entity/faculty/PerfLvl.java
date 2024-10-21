package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.rubric.RubricCell;
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
@Table(name = "PERF_LVL")
@NamedQuery(name = "PerfLvl.findAll", query = "SELECT p FROM PerfLvl p")
public class PerfLvl implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "PERF_LVL_PLID_GENERATOR", allocationSize = 1, sequenceName = "PERF_LVL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERF_LVL_PLID_GENERATOR")
    @Column(name = "PL_ID")
    private long plId;

    @Column(name = "PL_IS_ACTIVE")
    private char plIsActive;

    @Column(name = "PL_NAME_ENG")
    private String plNameEng;

    @Column(name = "PL_NAME_SPA")
    private String plNameSpa;

    @Column(name = "PL_ORDER")
    private int plOrder;

    //bi-directional many-to-one association to AcadProgram
    @ManyToOne
    @JoinColumn(name = "ACADP_ACP_ID")
    private AcadProgram acadProgram;

    //bi-directional many-to-one association to RubricCell
    @OneToMany(mappedBy = "perfLvl")
    private List<RubricCell> rubricCells;

    public PerfLvl() {
        //Entity constructor
    }

    public long getPlId() {
        return this.plId;
    }

    public void setPlId(long plId) {
        this.plId = plId;
    }

    public char getPlIsActive() {
        return this.plIsActive;
    }

    public void setPlIsActive(char plIsActive) {
        this.plIsActive = plIsActive;
    }

    public String getPlNameEng() {
        return this.plNameEng;
    }

    public void setPlNameEng(String plNameEng) {
        this.plNameEng = plNameEng;
    }

    public String getPlNameSpa() {
        return this.plNameSpa;
    }

    public void setPlNameSpa(String plNameSpa) {
        this.plNameSpa = plNameSpa;
    }

    public int getPlOrder() {
        return this.plOrder;
    }

    public void setPlOrder(int plOrder) {
        this.plOrder = plOrder;
    }

    public AcadProgram getAcadProgram() {
        return this.acadProgram;
    }

    public void setAcadProgram(AcadProgram acadProgram) {
        this.acadProgram = acadProgram;
    }

    public List<RubricCell> getRubricCells() {
        return this.rubricCells;
    }

    public void setRubricCells(List<RubricCell> rubricCells) {
        this.rubricCells = rubricCells;
    }

    public RubricCell addRubricCell(RubricCell rubricCell) {
        getRubricCells().add(rubricCell);
        rubricCell.setPerfLvl(this);

        return rubricCell;
    }

    public RubricCell removeRubricCell(RubricCell rubricCell) {
        getRubricCells().remove(rubricCell);
        rubricCell.setPerfLvl(null);

        return rubricCell;
    }

}