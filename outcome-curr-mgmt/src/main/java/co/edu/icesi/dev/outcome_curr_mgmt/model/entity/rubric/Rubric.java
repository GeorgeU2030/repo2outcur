package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.rubric;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
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
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@NamedQuery(name = "Rubric.findAll", query = "SELECT r FROM Rubric r")
public class Rubric implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "RUBRIC_RBID_GENERATOR", sequenceName = "RUBRIC_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RUBRIC_RBID_GENERATOR")
    @Column(name = "RB_ID")
    private long rbId;

    @Column(name = "RB_IS_ACTIVE")
    private char rbIsActive;

    //bi-directional many-to-one association to Factor
    @ManyToOne
    @JoinColumn(name = "FCT_FACTOR_ID")
    private Factor factor;

    //bi-directional many-to-one association to RubricCell
    @OneToMany(mappedBy = "rubric")
    private List<RubricCell> rubricCells;

    public Rubric() {
        //Entity constructor
    }

    public long getRbId() {
        return this.rbId;
    }

    public void setRbId(long rbId) {
        this.rbId = rbId;
    }

    public char getRbIsActive() {
        return this.rbIsActive;
    }

    public void setRbIsActive(char rbIsActive) {
        this.rbIsActive = rbIsActive;
    }

    public Factor getFactor() {
        return this.factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public List<RubricCell> getRubricCells() {
        return this.rubricCells;
    }

    public void setRubricCells(List<RubricCell> rubricCells) {
        this.rubricCells = rubricCells;
    }

    public RubricCell addRubricCell(RubricCell rubricCell) {
        getRubricCells().add(rubricCell);
        rubricCell.setRubric(this);

        return rubricCell;
    }

    public RubricCell removeRubricCell(RubricCell rubricCell) {
        getRubricCells().remove(rubricCell);
        rubricCell.setRubric(null);

        return rubricCell;
    }

}