package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrFac;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UserRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrFacRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import co.edu.icesi.dev.outcome_curr_mgmt.service.component.management.UserProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.BaseValidatorTest;
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
public class FacultyValidatorImplTest extends BaseValidatorTest {

    private static final long FACULTY_ID = 1L;
    private static final long USER_ID = 0L;
    @Mock
    UsrFacRepository usrFacRepository;
    @Mock
    UserProvider userProvider;
    @InjectMocks
    FacultyValidatorImpl facultyValidator;

    @ParameterizedTest(name = "#{index} - enforceUsrFac for Faculty {0} any")
    @CsvSource(value = {"ADMIN, Admin, , any", "QUERY, Admin, Query, any"})
    void enforceUsrFacForFaculty_ANY_happyPath(String userPermAccess, String permAccess1, String permAccess2,
            String permLevel) {
        List<String> permissions = permAccess2 == null ?
                List.of("ROLE_" + permAccess1 + "-faculties-" + permLevel) :
                List.of("ROLE_" + permAccess1 + "-faculties-" + permLevel,
                        "ROLE_" + permAccess2 + "-faculties-" + permLevel);

        when(saamfiJwtTools.loggedInUserHasPermission(
                permissions)).thenReturn(true);

        facultyValidator.enforceUsrFacForFaculty(FACULTY_ID, UserPermAccess.valueOf(userPermAccess));

        verify(usrFacRepository, never()).findByFacultyFacIdAndUserUsrId(FACULTY_ID, USER_ID);

    }

    @ParameterizedTest(name = "#{index} - enforceUsrFac for Faculty {0} own")
    @CsvSource(value = {"ADMIN, Admin, , own", "QUERY, Admin, Query, own"})
    void enforceUsrFacForFaculty_OWN_happyPath(String userPermAccess, String permAccess1, String permAccess2,
            String permLevel) {

        UserPermAccess userPermAccessValue = UserPermAccess.valueOf(userPermAccess);
        User user = User.builder().usrId(USER_ID).build();

        when(userProvider.getUserIdFromSession()).thenReturn(USER_ID);
        when(usrFacRepository.findByFacultyFacIdAndUserUsrId(FACULTY_ID, USER_ID)).thenReturn(
                Optional.of(UsrFac.builder().user(user).build()));

        List<String> permissions = permAccess2 == null ?
                List.of("ROLE_" + permAccess1 + "-faculties-" + permLevel) :
                List.of("ROLE_" + permAccess1 + "-faculties-" + permLevel,
                        "ROLE_" + permAccess2 + "-faculties-" + permLevel);

        List<String> anyPermissions = permAccess2 == null ?
                List.of("ROLE_" + permAccess1 + "-faculties-any") :
                List.of("ROLE_" + permAccess1 + "-faculties-any", "ROLE_" + permAccess2 + "-faculties-any");

        when(saamfiJwtTools.loggedInUserHasPermission(anyPermissions)).thenReturn(false);

        when(saamfiJwtTools.loggedInUserHasPermission(permissions)).thenReturn(true);

        facultyValidator.enforceUsrFacForFaculty(FACULTY_ID, userPermAccessValue);

        verify(usrFacRepository, times(1)).findByFacultyFacIdAndUserUsrId(FACULTY_ID, USER_ID);

    }

    @ParameterizedTest(name = "#{index} - enforceUsrFac for Faculty {0} own")
    @CsvSource(value = {"ADMIN, Admin, , own", "QUERY, Admin, Query, own"})
    void enforceUsrFacForFaculty_OWN_Exception(String userPermAccess, String permAccess1, String permAccess2,
            String permLevel) {

        UserPermAccess userPermAccessValue = UserPermAccess.valueOf(userPermAccess);

        List<String> permissions = permAccess2 == null ?
                List.of("ROLE_" + permAccess1 + "-faculties-" + permLevel) :
                List.of("ROLE_" + permAccess1 + "-faculties-" + permLevel,
                        "ROLE_" + permAccess2 + "-faculties-" + permLevel);

        List<String> anyPermissions = permAccess2 == null ?
                List.of("ROLE_" + permAccess1 + "-faculties-any") :
                List.of("ROLE_" + permAccess1 + "-faculties-any", "ROLE_" + permAccess2 + "-faculties-any");

        when(saamfiJwtTools.loggedInUserHasPermission(anyPermissions)).thenReturn(false);

        when(saamfiJwtTools.loggedInUserHasPermission(permissions)).thenReturn(false);

        assertThrows(OutCurrException.class,
                () -> facultyValidator.enforceUsrFacForFaculty(FACULTY_ID, userPermAccessValue));

        verify(usrFacRepository, never()).findByFacultyFacIdAndUserUsrId(FACULTY_ID, USER_ID);
    }

}
