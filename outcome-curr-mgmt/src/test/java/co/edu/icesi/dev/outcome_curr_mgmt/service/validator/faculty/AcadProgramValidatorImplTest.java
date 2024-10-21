package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrPrg;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.AcadProgramRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrPrgRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.component.management.UserProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.matcher.AcadProgramMatcher;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.BaseValidatorTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil.defaultAcadProgram;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AcadProgramValidatorImplTest extends BaseValidatorTest {
    @Mock
    UsrPrgRepository usrPrgRepository;
    @Mock
    UserProvider userProvider;
    @Mock
    AcadProgramRepository acadProgramRepository;
    @InjectMocks
    AcadProgramValidatorImpl acadProgramValidator;

    private static final long FACULTY_ID = 1L;
    private static final long USER_ID = 0L;
    private static final long ACADPROGRAM_ID = 1L;

    @ParameterizedTest(name = "#{index} - validateUsrPrg for AcadProgram {0} with AcadProgramPermStatus {2}")
    @CsvSource(value = {
            "ADMIN, Admin, FUTURE, future, any, fac, ", "QUERY, Query, FUTURE, future, any, fac, Admin",
            "ADMIN, Admin, CURRENT, current, any, fac, ", "QUERY, Query, CURRENT, current, any, fac, Admin",
            "ADMIN, Admin, INACTIVE, inactive, any, fac, ", "QUERY, Query, INACTIVE, inactive, any, fac, Admin"
    })
    void Given_Permissions_When_UserIsTryingToValidateWithAdminAndQueryPermissions_Then_UserIsValidated
            (String userPermAccess, String permAccess, String acadProgramPermStatus, String permStatus,
                    String permLevel1, String permLevel2, String AdminPermAccess){
        List<String> permissions = permAccess.equalsIgnoreCase("Query") ?  List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel2) :
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2);

        when(saamfiJwtTools.loggedInUserHasPermission(permissions)).thenReturn(true);

        acadProgramValidator.enforceUsrPrgForAcadProgram(FACULTY_ID, UserPermAccess.valueOf(userPermAccess),
                AcadProgramPermType.AcadProgramPermStatus.valueOf(acadProgramPermStatus));

        verify(usrPrgRepository, never()).findByAcadProgramAcpIdAndUserUsrId(ACADPROGRAM_ID, USER_ID);
    }

    @ParameterizedTest(name = "#{index} - validateUsrPrg for AcadProgram {0} with AcadProgramPermStatus {2}")
    @CsvSource(value = {
            "ADMIN, Admin, FUTURE, future, fac, own, ", "QUERY, Query, FUTURE, future, fac, own, Admin",
            "ADMIN, Admin, CURRENT, current, fac, own, ", "QUERY, Query, CURRENT, current , fac, own, Admin",
            "ADMIN, Admin, INACTIVE, inactive, fac, own, ", "QUERY, Query, INACTIVE, inactive , fac, own, Admin"
    })
    void Given_Permissions_When_UserIsTryingToValidateWithAdminAndQueryOwnPermissions_Then_UserIsValidated
            (String userPermAccess, String permAccess, String acadProgramPermStatus, String permStatus,
                    String permLevel1, String permLevel2, String AdminPermAccess){
        List<String> permissions = permAccess.equalsIgnoreCase("Query") ? List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel2) :
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2);

        List<String> anyPermissions = permAccess.equalsIgnoreCase("Query") ? List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-any",
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-any",
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel1) :
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-any",
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1);

        User user = User.builder().usrId(USER_ID).build();

        when(userProvider.getUserIdFromSession()).thenReturn(USER_ID);
        when(usrPrgRepository.findByAcadProgramAcpIdAndUserUsrId(ACADPROGRAM_ID, USER_ID)).thenReturn(
                Optional.of(UsrPrg.builder().user(user).build()));

        when(saamfiJwtTools.loggedInUserHasPermission(anyPermissions)).thenReturn(false);
        when(saamfiJwtTools.loggedInUserHasPermission(permissions)).thenReturn(true);

        acadProgramValidator.enforceUsrPrgForAcadProgram(FACULTY_ID, UserPermAccess.valueOf(userPermAccess),
                AcadProgramPermType.AcadProgramPermStatus.valueOf(acadProgramPermStatus));

        verify(usrPrgRepository, times(1)).findByAcadProgramAcpIdAndUserUsrId(ACADPROGRAM_ID, USER_ID);
    }

    @ParameterizedTest(name = "#{index} - validateUsrPrg for AcadProgram {0} with AcadProgramPermStatus {2}")
    @CsvSource(value = {
            "ADMIN, Admin, FUTURE, future, fac, own, ", "QUERY, Query, FUTURE, future, fac, own, Admin",
            "ADMIN, Admin, CURRENT, current, fac, own, ", "QUERY, Query, CURRENT, current , fac, own, Admin",
            "ADMIN, Admin, INACTIVE, inactive, fac, own, ", "QUERY, Query, INACTIVE, inactive , fac, own, Admin"
    })
    void Given_Permissions_When_UserIsTryingToValidateWithAdminAndQueryOwnPermissions_Then_ExceptionHasThrown
            (String userPermAccess, String permAccess, String acadProgramPermStatus, String permStatus,
                    String permLevel1, String permLevel2, String AdminPermAccess){
        List<String> permissions = permAccess.equalsIgnoreCase("Query") ? List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel2) :
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2);

        List<String> anyPermissions = permAccess.equalsIgnoreCase("Query") ? List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-any",
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-any",
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel1) :
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-any",
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1);

        when(saamfiJwtTools.loggedInUserHasPermission(anyPermissions)).thenReturn(false);
        when(saamfiJwtTools.loggedInUserHasPermission(permissions)).thenReturn(false);

        assertThrows(OutCurrException.class, () -> acadProgramValidator.enforceUsrPrgForAcadProgram(FACULTY_ID, UserPermAccess.valueOf(userPermAccess),
                AcadProgramPermType.AcadProgramPermStatus.valueOf(acadProgramPermStatus)));

        verify(usrPrgRepository, never()).findByAcadProgramAcpIdAndUserUsrId(ACADPROGRAM_ID, USER_ID);
    }

    @ParameterizedTest(name = "#{index} - validateUsrPrg for AcadProgram {0} with AcadProgramPermStatus {2}")
    @CsvSource(value = {
            "ADMIN, Admin, FUTURE, future, any, ", "QUERY, Query, FUTURE, future, any, Admin",
            "ADMIN, Admin, CURRENT, current, any, ", "QUERY, Query, CURRENT, current, any, Admin",
            "ADMIN, Admin, INACTIVE, inactive, any, ", "QUERY, Query, INACTIVE, inactive, any, Admin"
    })
    void Given_Permissions_When_UserIsTryingToValidateWithAdminAndQueryAnyPermissions_Then_UserIsValidatedInFacultyPermissions
            (String userPermAccess, String permAccess, String acadProgramPermStatus, String permStatus,
                    String permLevel1, String AdminPermAccess){
        List<String> permissions = permAccess.equalsIgnoreCase("Query") ?  List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel1):
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1);

        when(saamfiJwtTools.loggedInUserHasPermission(permissions)).thenReturn(true);

        acadProgramValidator.enforceUsrFacForAcadProgram(FACULTY_ID, UserPermAccess.valueOf(userPermAccess),
                AcadProgramPermType.AcadProgramPermStatus.valueOf(acadProgramPermStatus));

        verify(usrPrgRepository, never()).findByAcadProgramAcpIdAndUserUsrId(ACADPROGRAM_ID, USER_ID);
    }

    @ParameterizedTest(name = "#{index} - validateUsrPrg for AcadProgram {0} with AcadProgramPermStatus {2}")
    @CsvSource(value = {
            "ADMIN, Admin, FUTURE, future, fac, own, ", "QUERY, Query, FUTURE, future, fac, own, Admin",
            "ADMIN, Admin, CURRENT, current, fac, own, ", "QUERY, Query, CURRENT, current, fac, own, Admin",
            "ADMIN, Admin, INACTIVE, inactive, fac, own, ", "QUERY, Query, INACTIVE, inactive, fac, own, Admin"
    })
    void Given_Permissions_When_UserIsTryingToValidateWithAdminAndQueryOwnPermissions_Then_UserIsValidatedInFacultyPermissions
            (String userPermAccess, String permAccess, String acadProgramPermStatus, String permStatus,
                    String permLevel1, String permLevel2, String AdminPermAccess){
        List<String> permissions = permAccess.equalsIgnoreCase("Query") ? List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel2) :
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2);

        List<String> anyPermissions = permAccess.equalsIgnoreCase("Query") ? List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-any",
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-any") :
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-any");

        User user = User.builder().usrId(USER_ID).build();

        when(userProvider.getUserIdFromSession()).thenReturn(USER_ID);
        when(usrPrgRepository.findByAcadProgramAcpIdAndUserUsrId(ACADPROGRAM_ID, USER_ID)).thenReturn(
                Optional.of(UsrPrg.builder().user(user).build()));

        when(saamfiJwtTools.loggedInUserHasPermission(anyPermissions)).thenReturn(false);
        when(saamfiJwtTools.loggedInUserHasPermission(permissions)).thenReturn(true);

        acadProgramValidator.enforceUsrFacForAcadProgram(FACULTY_ID, UserPermAccess.valueOf(userPermAccess),
                AcadProgramPermType.AcadProgramPermStatus.valueOf(acadProgramPermStatus));

        verify(usrPrgRepository, times(1)).findByAcadProgramAcpIdAndUserUsrId(ACADPROGRAM_ID, USER_ID);
    }

    @ParameterizedTest(name = "#{index} - validateUsrPrg for AcadProgram {0} with AcadProgramPermStatus {2}")
    @CsvSource(value = {
            "ADMIN, Admin, FUTURE, future, fac, own, ", "QUERY, Query, FUTURE, future, fac, own, Admin",
            "ADMIN, Admin, CURRENT, current, fac, own, ", "QUERY, Query, CURRENT, current, fac, own, Admin",
            "ADMIN, Admin, INACTIVE, inactive, fac, own, ", "QUERY, Query, INACTIVE, inactive, fac, own, Admin"
    })
    void Given_Permissions_When_UserIsTryingToValidateWithAdminAndQueryOwnPermissions_Then_ExceptionHasThrownWithFacultyPermissions
            (String userPermAccess, String permAccess, String acadProgramPermStatus, String permStatus,
                    String permLevel1, String permLevel2, String AdminPermAccess){
        List<String> permissions = permAccess.equalsIgnoreCase("Query") ? List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-" + permLevel2) :
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel1,
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-" + permLevel2);

        List<String> anyPermissions = permAccess.equalsIgnoreCase("Query") ? List.of(
                "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-any",
                "ROLE_" + AdminPermAccess + "-" + permStatus + "-acad_programs-any") :
                List.of(
                        "ROLE_" + permAccess + "-" + permStatus + "-acad_programs-any");

        when(saamfiJwtTools.loggedInUserHasPermission(anyPermissions)).thenReturn(false);
        when(saamfiJwtTools.loggedInUserHasPermission(permissions)).thenReturn(false);

        assertThrows(OutCurrException.class, () -> acadProgramValidator.enforceUsrFacForAcadProgram(FACULTY_ID, UserPermAccess.valueOf(userPermAccess),
                AcadProgramPermType.AcadProgramPermStatus.valueOf(acadProgramPermStatus)));

        verify(usrPrgRepository, never()).findByAcadProgramAcpIdAndUserUsrId(ACADPROGRAM_ID, USER_ID);
    }

    @Test
    void Given_FacultyIdAndAcadProgramId_When_UserIsVerified_Then_AcadProgramOnFacultyIsValidated(){
        AcadProgramMatcher matcher;
        when(acadProgramRepository.findByFacultyFacIdAndAcpId(FACULTY_ID, ACADPROGRAM_ID)).thenReturn(
                Optional.ofNullable(defaultAcadProgram()));

        AcadProgram acadProgram = acadProgramValidator.validatAcadProgOnFaculty(FACULTY_ID, ACADPROGRAM_ID);

        assertNotNull(acadProgram);
        matcher = new AcadProgramMatcher(acadProgram);
        assertTrue(matcher.matches(defaultAcadProgram()));
    }

    @Test
    void Given_FacultyIdAndAcadProgramId_When_UserIsVerified_Then_AnExceptionIsThrown(){
        AcadProgramMatcher matcher;
        when(acadProgramRepository.findByFacultyFacIdAndAcpId(FACULTY_ID, ACADPROGRAM_ID))
                .thenThrow(new OutCurrException(OutCurrExceptionType.PROGACAD_CONFLICT_PROGRAM_ID));

        var exception = assertThrows(OutCurrException.class,
                () -> acadProgramValidator.validatAcadProgOnFaculty(FACULTY_ID, ACADPROGRAM_ID));

        String error = exception.getMessage();
        HttpStatus errorStatus = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(exception);
        assertEquals("Academic Program does not belong to the faculty", error);
        assertEquals("Conflict", errorStatus.getReasonPhrase());
        assertEquals(409, statusCode);
    }
}
