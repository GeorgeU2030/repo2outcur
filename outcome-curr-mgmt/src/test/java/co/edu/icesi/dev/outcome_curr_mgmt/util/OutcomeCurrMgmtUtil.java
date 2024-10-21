package co.edu.icesi.dev.outcome_curr_mgmt.util;


import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.audit.ChangeLogFilterInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.PerfLvlInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.AcadPeriodInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.PerfLvlOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.audit.Changelog;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PerfLvl;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrFac;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrFacPK;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;

import java.sql.Timestamp;
import java.util.Date;

import java.util.List;

public class OutcomeCurrMgmtUtil {
    private static final char ACTIVE='Y';

    private static final String ACTIVE_STR="Y";

    private static final String NOT_ACTIVE="N";

    //Academic period test data
    private static final long ACPERIOD_ID = 1000L;
    private static final String ACPERIOD_NAME_SPA = "Periodo academico 2023-2";
    private static final String ACPERIOD_NAME_ENG = "Academic period 2023-2";
    private static final int ACPERIOD_NUMERIC = 202302;
    private static final long ACPERIOD_ID2 = 2000L;
    private static final String ACPERIOD_NAME_SPA2 = "Periodo academico 2024-1";
    private static final String ACPERIOD_NAME_ENG2 = "Academic period 2024-1";
    private static final int ACPERIOD_NUMERIC2 = 202401;

    //AcadProgram
    private static final String ACP_PROG_NAME_SPA_1="Ingenieria de Sistemas";
    private static final String ACP_PROG_NAME_ENG_1="Software Systems Engineering";
    private static final String ACP_PROG_NAME_SPA_2="Derecho";
    private static final String ACP_PROG_NAME_ENG_2="Law";
    private static final String ACP_PROG_NAME_SPA_3="Medicina";
    private static final String ACP_PROG_NAME_ENG_3="Medicine";

    //PerfLvl
    private static final String PL_NAME_SPA_1="Principiante";
    private static final String PL_NAME_ENG_1="Beginning";

    private static final String PL_NAME_SPA_2="Principiante1";
    private static final String PL_NAME_ENG_2="Beginning1";

    //Faculty
    private static final String FACULTY_NAME_SPA_1="Ciencias Humanas";
    private static final String FACULTY_NAME_ENG_1="Human sciences";
    private static final String FACULTY_NAME_SPA_2="Ciencias de la salud";
    private static final String FACULTY_NAME_ENG_2="Health sciences";
    private static final String FACULTY_NAME_SPA_3="Ingeniería";
    private static final String FACULTY_NAME_ENG_3="Engineering";
    private static final String FACULTY_NAME_SPA_1_UP="Ciencias Humanas y psicología";
    private static final String FACULTY_NAME_ENG_1_UP="Human sciences and Psychology";

    //Changelog
    private static final String CLOG_LOG_NEW_VAL =
            "{"
                    + "\"plId\":"+1234+","
                    + "\"plNameEng\":\""+PL_NAME_ENG_1+"\","
                    + "\"plNameSpa\":\""+PL_NAME_SPA_1+"\","
                    + "\"plOrder\":"+1+","
                    + "\"plIsActive\":\""+ACTIVE+"\","
                    + "\"acadProgramId\":"+12345
                    + "}";

    private static final String CLOG_LOG_OLD_VAL =
            null;

    public static final String CLOG_AFFECTED_TABLE = "PerfLvl";

    //User
    public static final String USRNAME = "OutCurrTestUser";

    public static AcPeriod DEFAULT_ACPERIOD(){
        return AcPeriod.builder()
                .acPeriodId(ACPERIOD_ID)
                .acPeriodNumeric(ACPERIOD_NUMERIC)
                .acPeriodNameSpa(ACPERIOD_NAME_SPA)
                .acPeriodNameEng(ACPERIOD_NAME_ENG)
                .build();
    }

    public static AcPeriod DEFAULT_ACPERIOD2(){
        return AcPeriod.builder()
                .acPeriodId(ACPERIOD_ID2)
                .acPeriodNumeric(ACPERIOD_NUMERIC2)
                .acPeriodNameSpa(ACPERIOD_NAME_SPA2)
                .acPeriodNameEng(ACPERIOD_NAME_ENG2)
                .build();
    }

    public static AcadPeriodInDTO DEFAULT_ACPERIOD_IN_DTO(){
        return AcadPeriodInDTO.builder()
                .acPeriodNumeric(ACPERIOD_NUMERIC)
                .acPeriodNameSpa(ACPERIOD_NAME_SPA)
                .acPeriodNameEng(ACPERIOD_NAME_ENG)
                .build();
    }

    public static AcadProgram defaultAcadProgram(){
        return AcadProgram.builder()
                .acpId(1L)
                .acpSnies("1")
                .acpProgNameEng("EnglishName")
                .acpProgDescEng("English description")
                .acpProgNameSpa("SpanishName")
                .acpProgDescSpa("Spanish description")
                .acpIsActive(ACTIVE)
                .faculty(defaultFaculty())
                .build();
    }

    public static AcadProgram acadProgramSIS(){
        return AcadProgram.builder()
                .acpId(12345L)
                .acpProgNameSpa(ACP_PROG_NAME_SPA_1)
                .acpProgNameEng(ACP_PROG_NAME_ENG_1)
                .build();
    }

    public static PerfLvl perfLvl1(){
        return PerfLvl.builder()
                .plId(1234L)
                .plOrder(1)
                .plNameSpa(PL_NAME_SPA_1)
                .plNameEng(PL_NAME_ENG_1)
                .plIsActive(ACTIVE)
                .acadProgram(acadProgramSIS())
                .build();
    }

    public static PerfLvl perfLvl2(){
        return PerfLvl.builder()
                .plId(1234L)
                .plOrder(1)
                .plNameSpa(PL_NAME_SPA_2)
                .plNameEng(PL_NAME_ENG_2)
                .plIsActive(ACTIVE)
                .acadProgram(acadProgramSIS())
                .build();
    }



    public static PerfLvlInDTO perfLvl1InDTO(){
        return PerfLvlInDTO.builder()
                .plOrder(1)
                .plNameSpa(PL_NAME_SPA_1)
                .plNameEng(PL_NAME_ENG_1)
                .plIsActive(ACTIVE)
                .build();
    }

    public static PerfLvlInDTO perfLvl2InDTO(){
        return PerfLvlInDTO.builder()
                .plOrder(1)
                .plNameSpa(PL_NAME_SPA_2)
                .plNameEng(PL_NAME_ENG_2)
                .plIsActive(ACTIVE)
                .build();
    }

    public static Faculty defaultFaculty(){
        return Faculty.builder()
                .facId(15263582L)
                .facIsActive(ACTIVE)
                .facNameSpa(FACULTY_NAME_SPA_1)
                .facNameEng(FACULTY_NAME_ENG_1)
                .acadPrograms(List.of(acadProgramLAW()))
                .courses(List.of())
                .users(List.of())
                .usrFacs(List.of())
                .build();
    }

    public static Faculty secondFaculty(){
        return Faculty.builder()
                .facId(15263692L)
                .facIsActive(ACTIVE)
                .facNameSpa(FACULTY_NAME_SPA_2)
                .facNameEng(FACULTY_NAME_ENG_2)
                .acadPrograms(List.of(acadProgramMED()))
                .build();
    }

    public static Faculty thirdFaculty(){
        return Faculty.builder()
                .facId(15268759L)
                .facIsActive(ACTIVE)
                .facNameSpa(FACULTY_NAME_SPA_3)
                .facNameEng(FACULTY_NAME_ENG_3)
                .acadPrograms(List.of())
                .courses(List.of())
                .usrFacs(List.of())
                .users(List.of())
                .build();
    }

    public static AcadProgramOutDTO acadProgramLAWOutDTO(){
        return AcadProgramOutDTO.builder()
                .acpId(1L)
                .acpProgNameSpa(ACP_PROG_NAME_SPA_2)
                .acpProgNameEng(ACP_PROG_NAME_ENG_2)
                .build();
    }

    public static AcadProgram acadProgramLAW(){
        return AcadProgram.builder()
                .acpId(1L)
                .acpProgNameSpa(ACP_PROG_NAME_SPA_2)
                .acpProgNameEng(ACP_PROG_NAME_ENG_2)
                .build();

    }

    public static AcadProgram acadProgramMED() {
        return AcadProgram.builder()
                .acpId(5L)
                .acpProgNameSpa(ACP_PROG_NAME_SPA_3)
                .acpProgNameEng(ACP_PROG_NAME_ENG_3)
                .build();
    }

    public static FacultyInDTO facultyToUpdateDTO(){
        return FacultyInDTO.builder()
                .isActive(NOT_ACTIVE)
                .facNameEng(FACULTY_NAME_ENG_1_UP)
                .facNameSpa(FACULTY_NAME_SPA_1_UP)
                .build();
    }

    public static FacultyInDTO facultyInDTO(){
        return FacultyInDTO.builder()
                .isActive(ACTIVE_STR)
                .facNameEng(FACULTY_NAME_ENG_1)
                .facNameSpa(FACULTY_NAME_SPA_1)
                .build();
    }

    public static FacultyInDTO secondFacultyInDTO(){
        return FacultyInDTO.builder()
                .isActive(ACTIVE_STR)
                .facNameEng(FACULTY_NAME_ENG_2)
                .facNameSpa(FACULTY_NAME_SPA_2)
                .externalId("123456")
                .build();
    }

    public static FacultyOutDTO facultyOutDTO(){
        return FacultyOutDTO.builder()
                .facId(15263582L)
                .facIsActive(ACTIVE)
                .facNameEng(FACULTY_NAME_ENG_1)
                .facNameSpa(FACULTY_NAME_SPA_1)
                .acadPrograms(List.of(acadProgramLAWOutDTO()))
                .build();
    }

    public static FacultyOutDTO secondFacultyOutDTO() {
        return FacultyOutDTO.builder()
                .facId(28536251L)
                .facIsActive(ACTIVE)
                .facNameEng(FACULTY_NAME_ENG_2)
                .facNameSpa(FACULTY_NAME_SPA_2)
                .build();
    }

    public static PerfLvlOutDTO perfLvl1OutDTO(){
        return PerfLvlOutDTO.builder()
                .plId(1234L)
                .plNameSpa(PL_NAME_SPA_1)
                .plNameEng(PL_NAME_ENG_1)
                .plOrder(1)
                .plIsActive(ACTIVE)
                .acadProgramId(12345L)
                .build();
    }

    public static UsrFac testUsrFac(){
        return UsrFac.builder()
                .id( new UsrFacPK(testUser().getUsrId(), 15263582L))
                .many2many("")
                .build();
    }

    //User

    public static User testUser(){
      return User.builder().usrName(USRNAME).build();

    }

    //ChangeLog


    public static Changelog changePerfLvl(){
        return Changelog.builder()
                .clogAction(ChangeLogAction.CREATE.toString())
                .clogAffectedRecordId("1234")
                .clogAffectedTable(CLOG_AFFECTED_TABLE)
                .clogLogNewVal(CLOG_LOG_NEW_VAL)
                .clogLogOldVal(CLOG_LOG_OLD_VAL)
                .clogTimestamp(new Timestamp((new Date()).getTime()))
                .user(testUser())
                .build();
    }
    public static ChangeLogFilterInDTO changeLogDateFilterInDTO(){
        return ChangeLogFilterInDTO.builder()
                .clogStartDate("01/08/2022 00:00")
                .clogEndDate("10/08/2022 23:59")
                .build();
    }
    public static ChangeLogFilterInDTO changeLogDateFilterInDTOInvalid(){
        return ChangeLogFilterInDTO.builder()
                .clogStartDate("10/08/2022 00:00")
                .clogEndDate("1/08/2022 23:59")
                .build();
    }

    public static ChangeLogFilterInDTO changeLogUserFilterInDTO(){
        return ChangeLogFilterInDTO.builder()
                .usrName(USRNAME)
                .build();
    }

    public static ChangeLogFilterInDTO changeLogEntityFilterInDTO(){
        return ChangeLogFilterInDTO.builder()
                .entityName(CLOG_AFFECTED_TABLE)
                .build();
    }

    public static ChangeLogFilterInDTO changeLogDateFilterInDTOBadRequest(){
        return ChangeLogFilterInDTO.builder()
                .clogStartDate("01-08-2022")
                .clogEndDate("10-08-2022")
                .build();
    }
}
