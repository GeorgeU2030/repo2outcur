package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.rubric;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.PerfIndicator;
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
@NamedQuery(name = "Factor.findAll", query = "SELECT f FROM Factor f")
public class Factor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "FACTOR_FACTORID_GENERATOR", allocationSize = 1, sequenceName = "FACTOR_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACTOR_FACTORID_GENERATOR")
    @Column(name = "FACTOR_ID")
    private long factorId;

    @Column(name = "FACTOR_DESC_ENG")
    private String factorDescEng;

    @Column(name = "FACTOR_DESC_SPA")
    private String factorDescSpa;

    //bi-directional many-to-one association to PerfIndicator
    @ManyToOne
    @JoinColumn(name = "PI_PI_ID")
    private PerfIndicator perfIndicator;

    //bi-directional many-to-one association to Rubric
    @OneToMany(mappedBy = "factor")
    private List<Rubric> rubrics;

    public Factor() {
        //Entity constructor
    }

    public long getFactorId() {
        return this.factorId;
    }

    public void setFactorId(long factorId) {
        this.factorId = factorId;
    }

    public String getFactorDescEng() {
        return this.factorDescEng;
    }

    public void setFactorDescEng(String factorDescEng) {
        this.factorDescEng = factorDescEng;
    }

    public String getFactorDescSpa() {
        return this.factorDescSpa;
    }

    public void setFactorDescSpa(String factorDescSpa) {
        this.factorDescSpa = factorDescSpa;
    }

    public PerfIndicator getPerfIndicator() {
        return this.perfIndicator;
    }

    public void setPerfIndicator(PerfIndicator perfIndicator) {
        this.perfIndicator = perfIndicator;
    }

    public List<Rubric> getRubrics() {
        return this.rubrics;
    }

    public void setRubrics(List<Rubric> rubrics) {
        this.rubrics = rubrics;
    }

    public Rubric addRubric(Rubric rubric) {
        getRubrics().add(rubric);
        rubric.setFactor(this);

        return rubric;
    }

    public Rubric removeRubric(Rubric rubric) {
        getRubrics().remove(rubric);
        rubric.setFactor(null);

        return rubric;
    }

}