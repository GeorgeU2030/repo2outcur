package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrBlock;
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

/**
 * The persistent class for the COURSE_BLOCK database table.
 */
import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "COURSE_BLOCK")
@NamedQuery(name = "CourseBlock.findAll", query = "SELECT c FROM CourseBlock c")
public class CourseBlock implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "COURSE_BLOCK_BLOCKID_GENERATOR", allocationSize = 1, sequenceName = "COURSE_BLOCK_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_BLOCK_BLOCKID_GENERATOR")
    @Column(name = "BLOCK_ID")
    private long blockId;

    @Column(name = "BLOCK_IS_ACTIVE")
    private char blockIsActive;

    @Column(name = "BLOCK_NAME_ENG")
    private String blockNameEng;

    @Column(name = "BLOCK_NAME_SPA")
    private String blockNameSpa;

    //bi-directional many-to-one association to AcpCourseblock
    @OneToMany(mappedBy = "courseBlock")
    private List<AcpCourseblock> acpCourseblocks;

    //bi-directional many-to-one association to CbAcadpcur
    @OneToMany(mappedBy = "courseBlock")
    private List<CbAcadpcur> cbAcadpcurs;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "END_AC_PERIOD_ID")
    private AcPeriod endAcPeriod;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "START_AC_PERIOD_ID")
    private AcPeriod startAcPeriod;

    //bi-directional many-to-one association to CourseBlockCouse
    @OneToMany(mappedBy = "courseBlock")
    private List<CourseBlockCouse> courseBlockCouses;

    //bi-directional many-to-one association to UsrBlock
    @OneToMany(mappedBy = "courseBlock")
    private List<UsrBlock> usrBlocks;

    public CourseBlock() {
        //Entity constructor
    }

    public long getBlockId() {
        return this.blockId;
    }

    public void setBlockId(long blockId) {
        this.blockId = blockId;
    }

    public char getBlockIsActive() {
        return this.blockIsActive;
    }

    public void setBlockIsActive(char blockIsActive) {
        this.blockIsActive = blockIsActive;
    }

    public String getBlockNameEng() {
        return this.blockNameEng;
    }

    public void setBlockNameEng(String blockNameEng) {
        this.blockNameEng = blockNameEng;
    }

    public String getBlockNameSpa() {
        return this.blockNameSpa;
    }

    public void setBlockNameSpa(String blockNameSpa) {
        this.blockNameSpa = blockNameSpa;
    }

    public List<AcpCourseblock> getAcpCourseblocks() {
        return this.acpCourseblocks;
    }

    public void setAcpCourseblocks(List<AcpCourseblock> acpCourseblocks) {
        this.acpCourseblocks = acpCourseblocks;
    }

    public AcpCourseblock addAcpCourseblock(AcpCourseblock acpCourseblock) {
        getAcpCourseblocks().add(acpCourseblock);
        acpCourseblock.setCourseBlock(this);

        return acpCourseblock;
    }

    public AcpCourseblock removeAcpCourseblock(AcpCourseblock acpCourseblock) {
        getAcpCourseblocks().remove(acpCourseblock);
        acpCourseblock.setCourseBlock(null);

        return acpCourseblock;
    }

    public List<CbAcadpcur> getCbAcadpcurs() {
        return this.cbAcadpcurs;
    }

    public void setCbAcadpcurs(List<CbAcadpcur> cbAcadpcurs) {
        this.cbAcadpcurs = cbAcadpcurs;
    }

    public CbAcadpcur addCbAcadpcur(CbAcadpcur cbAcadpcur) {
        getCbAcadpcurs().add(cbAcadpcur);
        cbAcadpcur.setCourseBlock(this);

        return cbAcadpcur;
    }

    public CbAcadpcur removeCbAcadpcur(CbAcadpcur cbAcadpcur) {
        getCbAcadpcurs().remove(cbAcadpcur);
        cbAcadpcur.setCourseBlock(null);

        return cbAcadpcur;
    }

    public AcPeriod getEndAcPeriod() {
        return this.endAcPeriod;
    }

    public void setEndAcPeriod(AcPeriod acPeriod1) {
        this.endAcPeriod = acPeriod1;
    }

    public AcPeriod getStartAcPeriod() {
        return this.startAcPeriod;
    }

    public void setStartAcPeriod(AcPeriod acPeriod2) {
        this.startAcPeriod = acPeriod2;
    }

    public List<CourseBlockCouse> getCourseBlockCouses() {
        return this.courseBlockCouses;
    }

    public void setCourseBlockCouses(List<CourseBlockCouse> courseBlockCouses) {
        this.courseBlockCouses = courseBlockCouses;
    }

    public CourseBlockCouse addCourseBlockCous(CourseBlockCouse courseBlockCous) {
        getCourseBlockCouses().add(courseBlockCous);
        courseBlockCous.setCourseBlock(this);

        return courseBlockCous;
    }

    public CourseBlockCouse removeCourseBlockCous(CourseBlockCouse courseBlockCous) {
        getCourseBlockCouses().remove(courseBlockCous);
        courseBlockCous.setCourseBlock(null);

        return courseBlockCous;
    }

    public List<UsrBlock> getUsrBlocks() {
        return this.usrBlocks;
    }

    public void setUsrBlocks(List<UsrBlock> usrBlocks) {
        this.usrBlocks = usrBlocks;
    }

    public UsrBlock addUsrBlock(UsrBlock usrBlock) {
        getUsrBlocks().add(usrBlock);
        usrBlock.setCourseBlock(this);

        return usrBlock;
    }

    public UsrBlock removeUsrBlock(UsrBlock usrBlock) {
        getUsrBlocks().remove(usrBlock);
        usrBlock.setCourseBlock(null);

        return usrBlock;
    }

}