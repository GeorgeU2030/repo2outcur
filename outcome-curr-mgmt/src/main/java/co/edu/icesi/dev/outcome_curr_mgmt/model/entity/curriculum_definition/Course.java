package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_instance.OfferedCourse;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.Cell;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.CurrMap;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Semester;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
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
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
public class Course extends Cell implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "COURSE_COURSEID_GENERATOR", allocationSize = 1, sequenceName = "COURSE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_COURSEID_GENERATOR")
    @Column(name = "COURSE_ID")
    private long courseId;

    @Column(name = "COURSE_CREDITS")
    private int courseCredits;

    @Column(name = "COURSE_DESCRIPTION_ENG")
    private String courseDescriptionEng;

    @Column(name = "COURSE_DESCRIPTION_SPA")
    private String courseDescriptionSpa;

    @Column(name = "COURSE_GENERAL_GOAL_ENG")
    private String courseGeneralGoalEng;

    @Column(name = "COURSE_GENERAL_GOAL_SPA")
    private String courseGeneralGoalSpa;

    @Column(name = "COURSE_HOURLY_INTENSITY")
    private int courseHourlyIntensity;

    @Column(name = "COURSE_IS_ACTIVE")
    private char courseIsActive;

    @Column(name = "COURSE_NAME_ENG")
    private String courseNameEng;

    @Column(name = "COURSE_NAME_SPA")
    private String courseNameSpa;

    @Column(name = "COURSE_WEEKLY_INTENSITY")
    private int courseWeeklyIntensity;

    @Column(name = "EXTERNAL_ID")
    private String externalId;

    //bi-directional many-to-many association to AcadProgCurriculum
    @ManyToMany
    @JoinTable(
            name = "COURSE_CURR"
            , joinColumns = {
            @JoinColumn(name = "CRS_COURSE_ID")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "ACADP_CUR_APC_ID")
    }
    )
    private List<AcadProgCurriculum> acadProgCurriculums;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "END_AC_PERIOD_ID")
    private AcPeriod endAcPeriod;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "START_AC_PERIOD_ID")
    private AcPeriod startAcPeriod;

    //bi-directional many-to-one association to Course
    @ManyToOne
    @JoinColumn(name = "PREVIOUS_COURSE_ID")
    private Course course;

    //bi-directional many-to-one association to Course
    @OneToMany(mappedBy = "course")
    private List<Course> courses;

    //bi-directional many-to-one association to Faculty
    @ManyToOne
    @JoinColumn(name = "FAC_FAC_ID")
    private Faculty faculty;

    //bi-directional many-to-one association to Semester
    @ManyToOne
    @JoinColumn(name = "SEM_SEM_ID")
    private Semester semester;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "CREATOR_USR_ID")
    private User user;

    //bi-directional many-to-one association to CourseBlockCouse
    @OneToMany(mappedBy = "course")
    private List<CourseBlockCouse> courseBlockCouses;

    //bi-directional many-to-one association to CourseCurr
    @OneToMany(mappedBy = "course")
    private List<CourseCurr> courseCurrs;

    //bi-directional many-to-one association to CurrMap
    @OneToMany(mappedBy = "course")
    private List<CurrMap> currMaps;

    //bi-directional many-to-one association to EndGoal
    @OneToMany(mappedBy = "course")
    private List<EndGoal> endGoals;

    //bi-directional many-to-one association to OfferedCourse
    @OneToMany(mappedBy = "course")
    private List<OfferedCourse> offeredCourses;

    public Course() {
        //Entity constructor
    }

    @Override
    public void initializeCellValues() {
        putKeyValueInMap("course", courseNameEng); //TODO: ADD THE NAME IN SPANISH
        putKeyValueInMap("courseId", String.valueOf(courseId));
    }

    public void setCourseNameEng(String courseNameEng) {
        this.courseNameEng = courseNameEng;
        putKeyValueInMap("course", courseNameEng); //TODO: ADD THE NAME IN SPANISH
    }

    public long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public int getCourseCredits() {
        return this.courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }

    public String getCourseDescriptionEng() {
        return this.courseDescriptionEng;
    }

    public void setCourseDescriptionEng(String courseDescriptionEng) {
        this.courseDescriptionEng = courseDescriptionEng;
    }

    public String getCourseDescriptionSpa() {
        return this.courseDescriptionSpa;
    }

    public void setCourseDescriptionSpa(String courseDescriptionSpa) {
        this.courseDescriptionSpa = courseDescriptionSpa;
    }

    public String getCourseGeneralGoalEng() {
        return this.courseGeneralGoalEng;
    }

    public void setCourseGeneralGoalEng(String courseGeneralGoalEng) {
        this.courseGeneralGoalEng = courseGeneralGoalEng;
    }

    public String getCourseGeneralGoalSpa() {
        return this.courseGeneralGoalSpa;
    }

    public void setCourseGeneralGoalSpa(String courseGeneralGoalSpa) {
        this.courseGeneralGoalSpa = courseGeneralGoalSpa;
    }

    public int getCourseHourlyIntensity() {
        return this.courseHourlyIntensity;
    }

    public void setCourseHourlyIntensity(int courseHourlyIntensity) {
        this.courseHourlyIntensity = courseHourlyIntensity;
    }

    public char getCourseIsActive() {
        return this.courseIsActive;
    }

    public void setCourseIsActive(char courseIsActive) {
        this.courseIsActive = courseIsActive;
    }

    public String getCourseNameEng() {
        return this.courseNameEng;
    }

    public String getCourseNameSpa() {
        return this.courseNameSpa;
    }

    public void setCourseNameSpa(String courseNameSpa) {
        this.courseNameSpa = courseNameSpa;
    }

    public int getCourseWeeklyIntensity() {
        return this.courseWeeklyIntensity;
    }

    public void setCourseWeeklyIntensity(int courseWeeklyIntensity) {
        this.courseWeeklyIntensity = courseWeeklyIntensity;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<AcadProgCurriculum> getAcadProgCurriculums() {
        return this.acadProgCurriculums;
    }

    public void setAcadProgCurriculums(List<AcadProgCurriculum> acadProgCurriculums) {
        this.acadProgCurriculums = acadProgCurriculums;
    }

    public AcPeriod getEndAcPeriod() {
        return this.endAcPeriod;
    }

    public void setEndAcPeriod(AcPeriod endAcPeriod) {
        this.endAcPeriod = endAcPeriod;
    }

    public AcPeriod getStartAcPeriod() {
        return this.startAcPeriod;
    }

    public void setStartAcPeriod(AcPeriod startAcPeriod) {
        this.startAcPeriod = startAcPeriod;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course addCours(Course cours) {
        getCourses().add(cours);
        cours.setCourse(this);

        return cours;
    }

    public Course removeCours(Course cours) {
        getCourses().remove(cours);
        cours.setCourse(null);

        return cours;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Semester getSemester() {
        return this.semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CourseBlockCouse> getCourseBlockCouses() {
        return this.courseBlockCouses;
    }

    public void setCourseBlockCouses(List<CourseBlockCouse> courseBlockCouses) {
        this.courseBlockCouses = courseBlockCouses;
    }

    public CourseBlockCouse addCourseBlockCous(CourseBlockCouse courseBlockCous) {
        getCourseBlockCouses().add(courseBlockCous);
        courseBlockCous.setCourse(this);

        return courseBlockCous;
    }

    public CourseBlockCouse removeCourseBlockCous(CourseBlockCouse courseBlockCous) {
        getCourseBlockCouses().remove(courseBlockCous);
        courseBlockCous.setCourse(null);

        return courseBlockCous;
    }

    public List<CourseCurr> getCourseCurrs() {
        return this.courseCurrs;
    }

    public void setCourseCurrs(List<CourseCurr> courseCurrs) {
        this.courseCurrs = courseCurrs;
    }

    public CourseCurr addCourseCurr(CourseCurr courseCurr) {
        getCourseCurrs().add(courseCurr);
        courseCurr.setCourse(this);

        return courseCurr;
    }

    public CourseCurr removeCourseCurr(CourseCurr courseCurr) {
        getCourseCurrs().remove(courseCurr);
        courseCurr.setCourse(null);

        return courseCurr;
    }

    public List<CurrMap> getCurrMaps() {
        return this.currMaps;
    }

    public void setCurrMaps(List<CurrMap> currMaps) {
        this.currMaps = currMaps;
    }

    public CurrMap addCurrMap(CurrMap currMap) {
        getCurrMaps().add(currMap);
        currMap.setCourse(this);

        return currMap;
    }

    public CurrMap removeCurrMap(CurrMap currMap) {
        getCurrMaps().remove(currMap);
        currMap.setCourse(null);

        return currMap;
    }

    public List<EndGoal> getEndGoals() {
        return this.endGoals;
    }

    public void setEndGoals(List<EndGoal> endGoals) {
        this.endGoals = endGoals;
    }

    public EndGoal addEndGoal(EndGoal endGoal) {
        getEndGoals().add(endGoal);
        endGoal.setCourse(this);

        return endGoal;
    }

    public EndGoal removeEndGoal(EndGoal endGoal) {
        getEndGoals().remove(endGoal);
        endGoal.setCourse(null);

        return endGoal;
    }

    public List<OfferedCourse> getOfferedCourses() {
        return this.offeredCourses;
    }

    public void setOfferedCourses(List<OfferedCourse> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }

    public OfferedCourse addOfferedCours(OfferedCourse offeredCours) {
        getOfferedCourses().add(offeredCours);
        offeredCours.setCourse(this);

        return offeredCours;
    }

    public OfferedCourse removeOfferedCours(OfferedCourse offeredCours) {
        getOfferedCourses().remove(offeredCours);
        offeredCours.setCourse(null);

        return offeredCours;
    }
}