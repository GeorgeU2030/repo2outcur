package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.rubric;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_instance.RubricCellFilled;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PerfLvl;
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
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "RUBRIC_CELL")
@NamedQuery(name = "RubricCell.findAll", query = "SELECT r FROM RubricCell r")
public class RubricCell implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "RUBRIC_CELL_RCID_GENERATOR", allocationSize = 1, sequenceName = "RUBRIC_CELL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RUBRIC_CELL_RCID_GENERATOR")
    @Column(name = "RC_ID")
    private long rcId;

    @Column(name = "RC_PERF_LEVEL_DESCRIP_ENG")
    private String rcPerfLevelDescripEng;

    @Column(name = "RC_PERF_LEVEL_DESCRIP_SPA")
    private String rcPerfLevelDescripSpa;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "AP_AC_PERIOD_ID")
    private AcPeriod acPeriod;

    //bi-directional many-to-one association to PerfLvl
    @ManyToOne
    @JoinColumn(name = "PELV_PL_ID")
    private PerfLvl perfLvl;

    //bi-directional many-to-one association to Rubric
    @ManyToOne
    @JoinColumn(name = "RUB_RB_ID")
    private Rubric rubric;

    //bi-directional many-to-one association to RubricCellFilled
    @OneToMany(mappedBy = "rubricCell")
    private List<RubricCellFilled> rubricCellFilleds;

    public RubricCell() {
        //Entity constructor
    }

    public long getRcId() {
        return this.rcId;
    }

    public void setRcId(long rcId) {
        this.rcId = rcId;
    }

    public String getRcPerfLevelDescripEng() {
        return this.rcPerfLevelDescripEng;
    }

    public void setRcPerfLevelDescripEng(String rcPerfLevelDescripEng) {
        this.rcPerfLevelDescripEng = rcPerfLevelDescripEng;
    }

    public String getRcPerfLevelDescripSpa() {
        return this.rcPerfLevelDescripSpa;
    }

    public void setRcPerfLevelDescripSpa(String rcPerfLevelDescripSpa) {
        this.rcPerfLevelDescripSpa = rcPerfLevelDescripSpa;
    }

    public AcPeriod getAcPeriod() {
        return this.acPeriod;
    }

    public void setAcPeriod(AcPeriod acPeriod) {
        this.acPeriod = acPeriod;
    }

    public PerfLvl getPerfLvl() {
        return this.perfLvl;
    }

    public void setPerfLvl(PerfLvl perfLvl) {
        this.perfLvl = perfLvl;
    }

    public Rubric getRubric() {
        return this.rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }

    public List<RubricCellFilled> getRubricCellFilleds() {
        return this.rubricCellFilleds;
    }

    public void setRubricCellFilleds(List<RubricCellFilled> rubricCellFilleds) {
        this.rubricCellFilleds = rubricCellFilleds;
    }

    public RubricCellFilled addRubricCellFilled(RubricCellFilled rubricCellFilled) {
        getRubricCellFilleds().add(rubricCellFilled);
        rubricCellFilled.setRubricCell(this);

        return rubricCellFilled;
    }

    public RubricCellFilled removeRubricCellFilled(RubricCellFilled rubricCellFilled) {
        getRubricCellFilleds().remove(rubricCellFilled);
        rubricCellFilled.setRubricCell(null);

        return rubricCellFilled;
    }

}