package co.edu.icesi.dev.outcome_curr_mgmt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OutCurrExceptionType {
    FACULTY_INVALID_FAC_ID(4101, "Faculty ID not found", Constants.FAC_ID, HttpStatus.NOT_FOUND, LogLevel.INFO),
    FACULTY_INVALID_FAC_NAME_ENG(4102, "Faculty name (English) required", Constants.FAC_NAME_ENG, HttpStatus.UNPROCESSABLE_ENTITY,
            LogLevel.INFO),
    FACULTY_INVALID_FAC_NAME_SPA(4103, "Faculty name (Spanish) required", Constants.FAC_NAME_SPA, HttpStatus.UNPROCESSABLE_ENTITY,
            LogLevel.INFO),
    FACULTY_INVALID_FAC_IS_ACTIVE(4104, "Faculty isActive required", "facIsActive", HttpStatus.UNPROCESSABLE_ENTITY,
            LogLevel.INFO),
    FACULTY_FORBIDDEN_FAC_ID(4105, "Faculty not accessible", Constants.FAC_ID, HttpStatus.FORBIDDEN, LogLevel.INFO),
    FACULTY_DUPLICATED_FAC_ID(4106, "There is another faculty with the same ID", Constants.FAC_ID, HttpStatus.CONFLICT,
            LogLevel.INFO),
    FACULTY_DUPLICATED_FAC_NAME_ENG(4107, "There is another faculty with the same name in English",
            Constants.FAC_NAME_ENG, HttpStatus.CONFLICT,
            LogLevel.INFO),
    FACULTY_DUPLICATED_FAC_NAME_SPA(4108, "There is another faculty with the same name in Spanish",
            Constants.FAC_NAME_SPA, HttpStatus.CONFLICT,
            LogLevel.INFO),
    FACULTY_NOT_DELETED(4109, "A faculty cannot be deleted if has academic programs, courses or users associated.",
            Constants.FAC_ID, HttpStatus.CONFLICT, LogLevel.INFO),
    FACULTY_NOT_FOUND(4110, "Faculty not found", Constants.ACP_ID, HttpStatus.NOT_FOUND, LogLevel.INFO),
    PROGACAD_INVALID_PROGRAM_ID(4111, "Academic Program ID not found", Constants.ACP_ID, HttpStatus.NOT_FOUND, LogLevel.INFO),
    PROGACAD_INVALID_ACP_PROG_NAME_ENG(4112, "Academic program name (English) required", "acpProgNameEng",
            HttpStatus.UNPROCESSABLE_ENTITY, LogLevel.INFO),
    PROGACAD_INVALID_ACP_PROG_NAME_SPA(4113, "Academic program name (Spanish) required", "acpProgNameSpa",
            HttpStatus.UNPROCESSABLE_ENTITY, LogLevel.INFO),
    PROGACAD_INVALID_ACP_PROG_DESC_ENG(4114, "Academic program description (English) required", "acpProgDescEng",
            HttpStatus.UNPROCESSABLE_ENTITY, LogLevel.INFO),
    PROGACAD_INVALID_ACP_PROG_DESC_SPA(4115, "Academic program description (Spanish) required", "acpProgDescSpa",
            HttpStatus.UNPROCESSABLE_ENTITY, LogLevel.INFO),
    PROGACAD_INVALID_ACP_SNIES(4116, "Academic program SNIES required", "acpSnies", HttpStatus.UNPROCESSABLE_ENTITY,
            LogLevel.INFO),
    PROGACAD_INVALID_ACP_IS_ACTIVE(4117, "Faculty isActive required", "facIsActive", HttpStatus.UNPROCESSABLE_ENTITY,
            LogLevel.INFO),
    PROGACAD_FORBIDDEN_PROGRAM_ID(4118, "Academic Program not accessible", Constants.ACP_ID, HttpStatus.FORBIDDEN,
            LogLevel.INFO),
    PROGACAD_CONFLICT_PROGRAM_ID(4119, "Academic Program does not belong to the faculty", Constants.ACP_ID, HttpStatus.CONFLICT,
            LogLevel.INFO),
    USER_INVALID_PERMISSIONS(4121, "User does not have permissions", null, HttpStatus.FORBIDDEN, LogLevel.INFO),
    USER_INVALID_CREDENTIALS(4122, "Incorrect login credentials", null, HttpStatus.FORBIDDEN, LogLevel.INFO),
    USER_NOT_FOUND(4123, "User not found", "userId", HttpStatus.NOT_FOUND, LogLevel.INFO),
    ASSMTGENPLAN_INVALID_ASGPLA_ID(4131, "AssessmentGenPlan ID not found", "asgplaId", HttpStatus.NOT_FOUND,
            LogLevel.INFO),
    ASSMTGENPLAN_INVALID_START_AC_PERIOD(4132, "AssessmentGenPlan start period required", "startAcPeriod",
            HttpStatus.BAD_REQUEST, LogLevel.INFO),
    ASSMTGENPLAN_INVALID_END_AC_PERIOD(4133, "AssessmentGenPlan end period required", "endAcPeriod",
            HttpStatus.UNPROCESSABLE_ENTITY, LogLevel.INFO),
    ASSMTGENPLAN_INVALID_AC_PERIODS(4134, "AssessmentGenPlan start period must be before end period", "startAcPeriod",
            HttpStatus.UNPROCESSABLE_ENTITY, LogLevel.INFO),
    ASSMTGENPLAN_INVALID_CYCLES(4135, "Number of cycles must be between one and ten", "numberCycles",
            HttpStatus.UNPROCESSABLE_ENTITY, LogLevel.INFO),
    ASSMTGENPLAN_INVALID_SUBCYCLES(4136, "Number of sub cycles must be between one and ten", "subCyclesPerCycles",
            HttpStatus.UNPROCESSABLE_ENTITY, LogLevel.INFO),
    ASSMTGENPLAN_INVALID_NEW_CYCLES(4137, "Number of cycles can not be less than previously defined cycles",
            "numberCycles", HttpStatus.UNPROCESSABLE_ENTITY, LogLevel.INFO),
    ASSMTGENPLAN_CONFLICT_ASSMTGENPLAN_ID(4138, "AssessmentGenPlan does not belong to the academic program", "asgplaId",
            HttpStatus.CONFLICT, LogLevel.INFO),
    ACADPERIOD_NOTFOUND_ACPERIOD_ID(4139, "Academic period ID not found", Constants.AC_PERIOD_ID, HttpStatus.NOT_FOUND,
            LogLevel.INFO),
    ACADPERIOD_INVALID_ACPERIOD_ID(4140, "Academic period ID required", Constants.AC_PERIOD_ID, HttpStatus.BAD_REQUEST,
            LogLevel.INFO),
    ACADPERIOD_INVALID_REQ_ACPERIOD_NAME_SPA(4141, "Academic period name (spanish) required",
            Constants.AC_PERIOD_NAME_SPA,
            HttpStatus.UNPROCESSABLE_ENTITY,
            LogLevel.INFO),
    ACADPERIOD_INVALID_DUP_ACPERIOD_NAME_SPA(4142, "Academic period name (spanish) duplicate",
            Constants.AC_PERIOD_NAME_SPA,
            HttpStatus.CONFLICT,
            LogLevel.INFO),
    ACADPERIOD_INVALID_REQ_ACPERIOD_NUMERIC(4143, "Academic period numeric required", Constants.AC_PERIOD_NUMERIC,
            HttpStatus.UNPROCESSABLE_ENTITY,
            LogLevel.INFO),
    ACADPERIOD_INVALID_DUP_ACPERIOD_NUMERIC(4144, "Academic period numeric duplicate", Constants.AC_PERIOD_NUMERIC,
            HttpStatus.CONFLICT,
            LogLevel.INFO),
    ACADPERIOD_NOTFOUND_ACPERIOD_NUMERIC(4145, "Academic period numeric not found", Constants.AC_PERIOD_NUMERIC,
            HttpStatus.NOT_FOUND,
            LogLevel.INFO),
    ACADPERIOD_NOTFOUND_ACPERIOD_NAME_SPA(4146, "Academic period name (spanish) not found",
            Constants.AC_PERIOD_NAME_SPA,
            HttpStatus.NOT_FOUND,
            LogLevel.INFO),
    ACADPERIOD_INVALID_DUP_ACPERIOD_ID(4147, "Academic period ID duplicate", Constants.AC_PERIOD_ID, HttpStatus.CONFLICT,
            LogLevel.INFO),
    PERFLVL_INVALID_PL_NAME_ENG(4148, "Performance Level name (English) already exists for this academic program",
            "plNameEng", HttpStatus.CONFLICT,
            LogLevel.INFO),
    PERFLVL_INVALID_PL_NAME_SPA(4149, "Performance Level name (Spanish) already exists for this academic program",
            "plNameSpa", HttpStatus.CONFLICT,
            LogLevel.INFO),
    PERFLVL_INVALID_PL_ID(4150, "Performance Level ID not found in the academic program", "plId", HttpStatus.NOT_FOUND,
            LogLevel.INFO),
    CLOG_INVALID_VAL(4151,"ChangeLog new value or old value is not valid","clogLogNewVal or clogLogOldValStr",HttpStatus.INTERNAL_SERVER_ERROR,
            LogLevel.INFO),
    CLOG_INVALID_DATE_FORMAT(4152,"Date in filter for changeLog has invalid format","clogStartDate or clogEndDate",HttpStatus.BAD_REQUEST,
            LogLevel.INFO),
    FACULTY_ALREADY_IMPORTED(4153, "Faculty already imported", Constants.FAC_ID, HttpStatus.CONFLICT, LogLevel.INFO),
    ;

    private final int code;
    private final String message;
    private final String parameterName;
    private final HttpStatus responseStatus;
    private final LogLevel logLevel;

    private static class Constants {
        public static final String ACP_ID = "acpId";
        public static final String AC_PERIOD_ID = "acPeriodId";
        public static final String AC_PERIOD_NAME_SPA = "acPeriodNameSpa";
        public static final String AC_PERIOD_NUMERIC = "acPeriodNumeric";
        public static final String FAC_ID = "facId";
        public static final String FAC_NAME_ENG = "facNameEng";
        public static final String FAC_NAME_SPA = "facNameSpa";
    }
}
