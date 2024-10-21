package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrFac;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.AssessmentGenPlanStatus;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrFacRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import co.edu.icesi.dev.outcome_curr_mgmt.service.component.management.UserProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.BaseValidatorTest;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssessmentGenPlanValidatorImplTest  extends BaseValidatorTest {
    private static final long FACULTY_ID = 1L;
    private static final long USER_ID = 0L;
    @Mock
    UsrFacRepository usrFacRepository;
    @Mock
    UserProvider userProvider;

    @InjectMocks
    AssessmentGenPlanValidatorImpl assessmentGenPlanValidator;

    @ParameterizedTest(name = "#{index} - enforceUsrFac for AssessmentGenPlan {0} Any in {2}} ")
    @CsvSource(value = {"ADMIN,Admin,,FUTURE,future", "QUERY,Admin,Query,FUTURE,future", "ADMIN,Admin,,EXECUTING,exec",
            "QUERY,Admin,Query,EXECUTING,exec", "ADMIN,Admin,,REVIEW,review", "QUERY,Admin,Query,REVIEW,review",
            "ADMIN,Admin,,CLOSED,closed", "QUERY,Admin,Query,CLOSED,closed"})
    void enforceUsrFacForAssessmentGenPlan_ANY_happyPath(String userPermAccess, String permAccess1, String permAccess2,
            String assessmentGenPlanStatus, String planStatus) {
        List<String> permissions = permAccess2 == null ?
                List.of("ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-any") :
                List.of("ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-any",
                        "ROLE_" + permAccess2 + "-" + planStatus + "-gen-assmnt-plan-any");

        when(saamfiJwtTools.loggedInUserHasPermission(permissions)).thenReturn(true);

        assessmentGenPlanValidator.enforceUsrFacForAssessmentGenPlan(FACULTY_ID, UserPermAccess.valueOf(userPermAccess),
                AssessmentGenPlanStatus.valueOf(assessmentGenPlanStatus));

        verify(usrFacRepository, never()).findByFacultyFacIdAndUserUsrId(FACULTY_ID, USER_ID);
    }

    @ParameterizedTest(name = "#{index} - enforceUsrFac for AssessmentGenPlan {0} Own in {2}} ")
    @CsvSource(value = {"ADMIN,Admin,,FUTURE,future", "QUERY,Admin,Query,FUTURE,future", "ADMIN,Admin,,EXECUTING,exec",
            "QUERY,Admin,Query,EXECUTING,exec", "ADMIN,Admin,,REVIEW,review", "QUERY,Admin,Query,REVIEW,review",
            "ADMIN,Admin,,CLOSED,closed", "QUERY,Admin,Query,CLOSED,closed"})
    void enforceUsrFacForAssessmentGenPlan_OWN_happyPath(String userPermAccess, String permAccess1, String permAccess2,
            String assessmentGenPlanStatus, String planStatus) {
        UserPermAccess userPermAccessValue = UserPermAccess.valueOf(userPermAccess);
        AssessmentGenPlanStatus assessmentGenPlanStatusValue = AssessmentGenPlanStatus.valueOf(assessmentGenPlanStatus);
        User user = User.builder().usrId(USER_ID).build();

        when(userProvider.getUserIdFromSession()).thenReturn(USER_ID);
        when(usrFacRepository.findByFacultyFacIdAndUserUsrId(FACULTY_ID, USER_ID)).thenReturn(
                Optional.of(UsrFac.builder().user(user).build()));
        List<String> permissionsFalse = permAccess2 == null ?
                List.of("ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-any") :
                List.of("ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-any",
                        "ROLE_" + permAccess2 + "-" + planStatus + "-gen-assmnt-plan-any");
        when(saamfiJwtTools.loggedInUserHasPermission(permissionsFalse)).thenReturn(false);
        List<String> permissionsTrue = permAccess2 == null ?
                List.of("ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-fac",
                        "ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-prg",
                        "ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-own") :
                List.of("ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-fac",
                        "ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-prg",
                        "ROLE_" + permAccess1 + "-" + planStatus + "-gen-assmnt-plan-own",
                        "ROLE_" + permAccess2 + "-" + planStatus + "-gen-assmnt-plan-fac",
                        "ROLE_" + permAccess2 + "-" + planStatus + "-gen-assmnt-plan-prg",
                        "ROLE_" + permAccess2 + "-" + planStatus + "-gen-assmnt-plan-own");
        when(saamfiJwtTools.loggedInUserHasPermission(permissionsTrue)).thenReturn(true);

        assessmentGenPlanValidator.enforceUsrFacForAssessmentGenPlan(FACULTY_ID, userPermAccessValue,
                assessmentGenPlanStatusValue);

        verify(usrFacRepository, times(1)).findByFacultyFacIdAndUserUsrId(FACULTY_ID, USER_ID);
    }

    @ParameterizedTest(name = "#{index} - enforceUsrFac for AssessmentGenPlan {0} Own in {2}} ")
    @CsvSource(value = {"ADMIN,Admin,FUTURE,future", "QUERY,Query,FUTURE,future", "ADMIN,Admin,EXECUTING,exec",
            "QUERY,Query,EXECUTING,exec", "ADMIN,Admin,REVIEW,review", "QUERY,Query,REVIEW,review",
            "ADMIN,Admin,CLOSED,closed", "QUERY,Query,CLOSED,closed"})
    void enforceUsrFacForAssessmentGenPlan_exception(String userPermAccess, String permAccess,
            String assessmentGenPlanStatus, String planStatus) {
        UserPermAccess userPermAccessValue = UserPermAccess.valueOf(userPermAccess);
        AssessmentGenPlanStatus assessmentGenPlanStatusValue = AssessmentGenPlanStatus.valueOf(assessmentGenPlanStatus);

//        when(saamfiJwtTools.loggedInUserHasPermission(
//                List.of("ROLE_" + permAccess + "-" + planStatus + "-gen-assmnt-plan-any"))).thenReturn(false);
//        when(saamfiJwtTools.loggedInUserHasPermission(
//                List.of("ROLE_" + permAccess + "-" + planStatus + "-gen-assmnt-plan-fac",
//                        "ROLE_" + permAccess + "-" + planStatus + "-gen-assmnt-plan-prg",
//                        "ROLE_" + permAccess + "-" + planStatus + "-gen-assmnt-plan-own"))).thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> assessmentGenPlanValidator.enforceUsrFacForAssessmentGenPlan(FACULTY_ID, userPermAccessValue,
                        assessmentGenPlanStatusValue));

        verify(usrFacRepository, never()).findByFacultyFacIdAndUserUsrId(FACULTY_ID, USER_ID);
    }
}
