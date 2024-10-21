package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.AcadProgCurriculum;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AssessmentType;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PiLvlCateg;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "CURR_MAP")
@NamedQuery(name = "CurrMap.findAll", query = "SELECT c FROM CurrMap c")
public class CurrMap extends Cell implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CURR_MAP_CMID_GENERATOR", allocationSize = 1, sequenceName = "CURR_MAP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURR_MAP_CMID_GENERATOR")
    @Column(name = "CM_ID")
    private long cmId;

    @Temporal(TemporalType.DATE)
    @Column(name="CM_ACCEPTED_DATE")
    private Date cmAcceptedDate;

    @Column(name="CM_COMMENT")
    private String cmComment;

    @Temporal(TemporalType.DATE)
    @Column(name="CM_REJECTED_DATE")
    private Date cmRejectedDate;

    @Temporal(TemporalType.DATE)
    @Column(name="CM_REQUEST_DATE")
    private Date cmRequestDate;

    @Column(name="REQUEST_STATE")
    private String requestState;

    //bi-directional many-to-one association to AcadProgCurriculum
    @ManyToOne
    @JoinColumn(name="ACADP_CUR_APC_ID")
    private AcadProgCurriculum acadProgCurriculum;

    //bi-directional many-to-one association to AssessmentType
    @ManyToOne
    @JoinColumn(name="ASSMT_TYPE_AT_ID")
    private AssessmentType assessmentType;

    //bi-directional many-to-one association to Course
    @ManyToOne
    @JoinColumn(name="CRS_COURSE_ID")
    private Course course;

    //bi-directional many-to-one association to PerfIndicator
    @ManyToOne
    @JoinColumn(name="PI_PI_ID")
    private PerfIndicator perfIndicator;

    //bi-directional many-to-one association to PiLvlCateg
    @ManyToOne
    @JoinColumn(name="PILV_CATEG_ID")
    private PiLvlCateg piLvlCateg;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name="CREATOR_USR_ID")
    private User user1;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name="APPR_USR_ID")
    private User user2;

    public CurrMap() {
        //Entity constructor
    }

    @Override
    public void initializeCellValues() {
        putKeyValueInMap("currMapId", String.valueOf(cmId));
        putKeyValueInMap("piLvlCategory", piLvlCateg.getCategNameEng()); //TODO: ADD THE NAME IN SPANISH
        if (assessmentType != null) {
            putKeyValueInMap("assessmentType", assessmentType.getAtNameEng()); //TODO: ADD THE NAME IN SPANISH
        }
    }

    public long getCmId() {
        return this.cmId;
    }

    public void setCmId(long cmId) {
        this.cmId = cmId;
    }

    public Date getCmAcceptedDate() {
        return this.cmAcceptedDate;
    }

    public void setCmAcceptedDate(Date cmAcceptedDate) {
        this.cmAcceptedDate = cmAcceptedDate;
    }

    public String getCmComment() {
        return this.cmComment;
    }

    public void setCmComment(String cmComment) {
        this.cmComment = cmComment;
    }

    public Date getCmRejectedDate() {
        return this.cmRejectedDate;
    }

    public void setCmRejectedDate(Date cmRejectedDate) {
        this.cmRejectedDate = cmRejectedDate;
    }

    public Date getCmRequestDate() {
        return this.cmRequestDate;
    }

    public void setCmRequestDate(Date cmRequestDate) {
        this.cmRequestDate = cmRequestDate;
    }

    public String getRequestState() {
        return this.requestState;
    }

    public void setRequestState(String requestState) {
        this.requestState = requestState;
    }

    public AcadProgCurriculum getAcadProgCurriculum() {
        return this.acadProgCurriculum;
    }

    public void setAcadProgCurriculum(AcadProgCurriculum acadProgCurriculum) {
        this.acadProgCurriculum = acadProgCurriculum;
    }

    public AssessmentType getAssessmentType() {
        return this.assessmentType;
    }

    public void setAssessmentType(AssessmentType assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public PerfIndicator getPerfIndicator() {
        return this.perfIndicator;
    }

    public void setPerfIndicator(PerfIndicator perfIndicator) {
        this.perfIndicator = perfIndicator;
    }

    public PiLvlCateg getPiLvlCateg() {
        return this.piLvlCateg;
    }

    public void setPiLvlCateg(PiLvlCateg piLvlCateg) {
        this.piLvlCateg = piLvlCateg;
        putKeyValueInMap("piLvlCategory", piLvlCateg.getCategNameEng()); //TODO: ADD THE NAME IN SPANISH
    }

    public User getUser1() {
        return this.user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return this.user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

}