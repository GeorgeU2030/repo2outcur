package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.PerfIndicator;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.PerfindEndgoal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "END_GOAL")
@NamedQuery(name = "EndGoal.findAll", query = "SELECT e FROM EndGoal e")
public class EndGoal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "END_GOAL_EGID_GENERATOR", allocationSize = 1, sequenceName = "END_GOAL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "END_GOAL_EGID_GENERATOR")
    @Column(name = "EG_ID")
    private long egId;

    @Column(name = "EG_LONG_NAME_ENG")
    private String egLongNameEng;

    @Column(name = "EG_LONG_NAME_SPA")
    private String egLongNameSpa;

    @Column(name = "EG_SHORT_NAME_ENG")
    private String egShortNameEng;

    @Column(name = "EG_SHORT_NAME_SPA")
    private String egShortNameSpa;

    //bi-directional many-to-one association to Course
    @ManyToOne
    @JoinColumn(name = "CRS_COURSE_ID")
    private Course course;

    //bi-directional many-to-one association to PerfindEndgoal
    @OneToMany(mappedBy = "endGoal")
    private List<PerfindEndgoal> perfindEndgoals;

    //bi-directional many-to-many association to PerfIndicator
    @ManyToMany(mappedBy = "endGoals")
    private List<PerfIndicator> perfIndicators;

    public EndGoal() {
        //Entity constructor
    }

    public long getEgId() {
        return this.egId;
    }

    public void setEgId(long egId) {
        this.egId = egId;
    }

    public String getEgLongNameEng() {
        return this.egLongNameEng;
    }

    public void setEgLongNameEng(String egLongNameEng) {
        this.egLongNameEng = egLongNameEng;
    }

    public String getEgLongNameSpa() {
        return this.egLongNameSpa;
    }

    public void setEgLongNameSpa(String egLongNameSpa) {
        this.egLongNameSpa = egLongNameSpa;
    }

    public String getEgShortNameEng() {
        return this.egShortNameEng;
    }

    public void setEgShortNameEng(String egShortNameEng) {
        this.egShortNameEng = egShortNameEng;
    }

    public String getEgShortNameSpa() {
        return this.egShortNameSpa;
    }

    public void setEgShortNameSpa(String egShortNameSpa) {
        this.egShortNameSpa = egShortNameSpa;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<PerfindEndgoal> getPerfindEndgoals() {
        return this.perfindEndgoals;
    }

    public void setPerfindEndgoals(List<PerfindEndgoal> perfindEndgoals) {
        this.perfindEndgoals = perfindEndgoals;
    }

    public PerfindEndgoal addPerfindEndgoal(PerfindEndgoal perfindEndgoal) {
        getPerfindEndgoals().add(perfindEndgoal);
        perfindEndgoal.setEndGoal(this);

        return perfindEndgoal;
    }

    public PerfindEndgoal removePerfindEndgoal(PerfindEndgoal perfindEndgoal) {
        getPerfindEndgoals().remove(perfindEndgoal);
        perfindEndgoal.setEndGoal(null);

        return perfindEndgoal;
    }

    public List<PerfIndicator> getPerfIndicators() {
        return this.perfIndicators;
    }

    public void setPerfIndicators(List<PerfIndicator> perfIndicators) {
        this.perfIndicators = perfIndicators;
    }

}