package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_instance;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "RUBRIC_CELL_FILLED")
@NamedQuery(name = "RubricCellFilled.findAll", query = "SELECT r FROM RubricCellFilled r")
public class RubricCellFilled implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "RUBRIC_CELL_FILLED_RCFID_GENERATOR", allocationSize = 1, sequenceName = "RUBRIC_CELL_FILLED_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RUBRIC_CELL_FILLED_RCFID_GENERATOR")
    @Column(name = "RCF_ID")
    private long rcfId;

    @Column(name = "RCF_EXP_LEVEL_OF_ACHIEVEMENT")
    private int rcfExpLevelOfAchievement;

    @Column(name = "RCF_REAL_LEVEL_OF_ACHIEVEMENT")
    private int rcfRealLevelOfAchievement;

    //bi-directional many-to-one association to OfferedCourse
    @ManyToOne
    @JoinColumn(name = "OFC_OFC_ID")
    private OfferedCourse offeredCourse;

    //bi-directional many-to-one association to RubricCell
    @ManyToOne
    @JoinColumn(name = "RC_RC_ID")
    private RubricCell rubricCell;

    public RubricCellFilled() {
        //Entity constructor
    }

    public long getRcfId() {
        return this.rcfId;
    }

    public void setRcfId(long rcfId) {
        this.rcfId = rcfId;
    }

    public int getRcfExpLevelOfAchievement() {
        return this.rcfExpLevelOfAchievement;
    }

    public void setRcfExpLevelOfAchievement(int rcfExpLevelOfAchievement) {
        this.rcfExpLevelOfAchievement = rcfExpLevelOfAchievement;
    }

    public int getRcfRealLevelOfAchievement() {
        return this.rcfRealLevelOfAchievement;
    }

    public void setRcfRealLevelOfAchievement(int rcfRealLevelOfAchievement) {
        this.rcfRealLevelOfAchievement = rcfRealLevelOfAchievement;
    }

    public OfferedCourse getOfferedCourse() {
        return this.offeredCourse;
    }

    public void setOfferedCourse(OfferedCourse offeredCourse) {
        this.offeredCourse = offeredCourse;
    }

    public RubricCell getRubricCell() {
        return this.rubricCell;
    }

    public void setRubricCell(RubricCell rubricCell) {
        this.rubricCell = rubricCell;
    }

}