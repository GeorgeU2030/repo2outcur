package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.AcadPeriodInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.AcadPeriodOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.management.AcademicPeriodMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.AcPeriodRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogServiceImpl;
import co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AcPeriodServiceTest {

    AcademicPeriodMapper academicPeriodMapper = Mappers.getMapper(AcademicPeriodMapper.class);

    @Mock
    AcPeriodRepository acPeriodRepository;

    @Mock
    AcPeriodServiceImpl acPeriodService;

    @Mock
    ChangeLogServiceImpl changeLogService;

    Validator validator;

    @BeforeEach
    void setup(){
        acPeriodService = new AcPeriodServiceImpl(acPeriodRepository, academicPeriodMapper, changeLogService);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void createAcademicPeriodHappyPath() {
        AcadPeriodInDTO acPeriodToCreate = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD_IN_DTO();
        Set<ConstraintViolation<AcadPeriodInDTO>> numViolations = getViolations(acPeriodToCreate);

        assertEquals(0, numViolations.size());
        when(acPeriodRepository.findByAcPeriodNumeric(acPeriodToCreate.acPeriodNumeric())).thenReturn(Optional.empty());

        acPeriodService.addAcademicPeriod(acPeriodToCreate);

        verify(changeLogService, times(1)).addChange(any(), any(), any(), any(), any());
        verify(acPeriodRepository, times(1)).findByAcPeriodNumeric(acPeriodToCreate.acPeriodNumeric());
        verify(acPeriodRepository, times(1)).findByAcPeriodNameSpa(any());
        verify(acPeriodRepository, times(1)).save(any());
    }

    @Test
    void createAcademicPeriodWithNullEngName() {
        AcadPeriodInDTO acPeriodToCreate = defaultAcademicPeriodWithEmptyEngName();
        Set<ConstraintViolation<AcadPeriodInDTO>> numViolations = getViolations(acPeriodToCreate);

        assertEquals(1, numViolations.size());
        assertEquals("English Name may not be empty", numViolations.stream().toList().get(0).getMessage());
    }

    @Test
    void createAcademicPeriodWithNullSpaName() {
        AcadPeriodInDTO acPeriodToCreate = defaultAcademicPeriodWithEmptySpaName();
        Set<ConstraintViolation<AcadPeriodInDTO>> numViolations = getViolations(acPeriodToCreate);

        assertEquals(1, numViolations.size());
        assertEquals("Spanish Name may not be empty", numViolations.stream().toList().get(0).getMessage());
    }

    @Test
    void createAcademicPeriodWithNullNumeric() {
        AcadPeriodInDTO acPeriodToCreate = defaultAcademicPeriodWithEmptyNumeric();
        Set<ConstraintViolation<AcadPeriodInDTO>> numViolations = getViolations(acPeriodToCreate);

        assertEquals(1, numViolations.size());
        assertEquals("Min value is an academic period greater than 199999",
                numViolations.stream().toList().get(0).getMessage());
    }

    @Test
    void createAcademicPeriodWithNumericValueGreaterThanTheLimit() {
        AcadPeriodInDTO acPeriodToCreate = defaultAcademicPeriodWithNumericValueGreaterThanTheLimit();
        Set<ConstraintViolation<AcadPeriodInDTO>> numViolations = getViolations(acPeriodToCreate);

        assertEquals(1, numViolations.size());
        assertEquals("Max value is an academic period less than 300000",
                numViolations.stream().toList().get(0).getMessage());
    }

    @Test
    void createAcademicPeriodWithNullAttributes() {
        AcadPeriodInDTO acPeriodToCreate = defaultAcademicPeriodWithNullAttributes();
        Set<ConstraintViolation<AcadPeriodInDTO>> numViolations = getViolations(acPeriodToCreate);

        assertEquals(3, numViolations.size());

        List<String> messages = convertViolationsMessages(numViolations);

        assertTrue(messages.contains("Spanish Name may not be empty"));
        assertTrue(messages.contains("Min value is an academic period greater than 199999"));
        assertTrue(messages.contains("English Name may not be empty"));

    }

    private List<String> convertViolationsMessages(Set<ConstraintViolation<AcadPeriodInDTO>> numViolations) {
        return numViolations.stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

    @Test
    void createAcademicPeriodWithNumericAlreadyExists() {

        AcadPeriodInDTO acPeriodToCreate = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD_IN_DTO();
        when(acPeriodRepository.findByAcPeriodNumeric(acPeriodToCreate.acPeriodNumeric()))
                .thenReturn(Optional.of(academicPeriodMapper.fromAcadPeriodInDTO(acPeriodToCreate)));

        var exception = assertThrows(OutCurrException.class, () -> acPeriodService.addAcademicPeriod(acPeriodToCreate),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        verify(acPeriodRepository, times(0)).save(any());
        verify(changeLogService, times(0)).addChange(any(),any(),any(),any(),any());

        assertNotNull(error);
        assertEquals("Conflict", statusMessage.getReasonPhrase());
        assertEquals(409, statusCode);
        assertEquals("Academic period numeric duplicate", error);
    }

    @Test
    void createAcademicPeriodWithSpaNameAlreadyExists() {
        AcadPeriodInDTO acPeriodToCreate = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD_IN_DTO();
        when(acPeriodRepository.findByAcPeriodNameSpa(any()))
                .thenReturn(Optional.of(academicPeriodMapper.fromAcadPeriodInDTO(acPeriodToCreate)));

        var exception = assertThrows(OutCurrException.class, () -> acPeriodService.addAcademicPeriod(acPeriodToCreate),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        verify(acPeriodRepository, times(0)).save(any());
        verify(changeLogService, times(0)).addChange(any(),any(),any(),any(),any());

        assertNotNull(error);
        assertEquals("Conflict", statusMessage.getReasonPhrase());
        assertEquals(409, statusCode);
        assertEquals("Academic period name (spanish) duplicate", error);
    }

    @Test
    void searchAcademicPeriodByIdHappyPath() {
        AcPeriod acPeriodToCreate = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD();
        when(acPeriodRepository.findById(any())).thenReturn(Optional.of(acPeriodToCreate));

        var acPeriod = acPeriodService.searchAcademicPeriod(acPeriodToCreate.getAcPeriodId());

        verify(acPeriodRepository, times(1)).findById(any());
        assertEquals(acPeriod.acPeriodNumeric(), acPeriodToCreate.getAcPeriodNumeric());
        assertEquals(acPeriod.acPeriodId(), acPeriodToCreate.getAcPeriodId());
    }

    @Test
    void searchAcademicPeriodByIdNotFound() {
        AcPeriod acPeriodToSearch = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD();
        when(acPeriodRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(OutCurrException.class,
                () -> acPeriodService.searchAcademicPeriod(acPeriodToSearch.getAcPeriodId()),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(error);
        assertEquals("Not Found", statusMessage.getReasonPhrase());
        assertEquals(404, statusCode);
        assertEquals("Academic period ID not found", error);
    }

    @Test
    void searchAcademicPeriodByIdRequiredField() {
        var exception = assertThrows(OutCurrException.class, () -> acPeriodService.searchAcademicPeriod(null),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(error);
        assertEquals("Bad Request", statusMessage.getReasonPhrase());
        assertEquals(400, statusCode);
        assertEquals("Academic period ID required", error);
    }

    @Test
    void searchAcademicPeriodByNumericHappyPath() {
        AcadPeriodInDTO acPeriodToSearch = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD_IN_DTO();
        when(acPeriodRepository.findByAcPeriodNumeric(acPeriodToSearch.acPeriodNumeric()))
                .thenReturn(Optional.of(academicPeriodMapper.fromAcadPeriodInDTO(acPeriodToSearch)));

        var acPeriod = acPeriodService.searchAcademicPeriodByNumber(acPeriodToSearch.acPeriodNumeric());

        verify(acPeriodRepository, times(1)).findByAcPeriodNumeric(acPeriod.acPeriodNumeric());
        assertEquals(acPeriod.acPeriodNumeric(), acPeriodToSearch.acPeriodNumeric());
        assertEquals(acPeriod.acPeriodNameSpa(), acPeriodToSearch.acPeriodNameSpa());
    }

    @Test
    void searchAcademicPeriodByNumericNotFound() {
        AcadPeriodInDTO acPeriodToSearch = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD_IN_DTO();
        when(acPeriodRepository.findByAcPeriodNumeric(acPeriodToSearch.acPeriodNumeric())).thenReturn(Optional.empty());

        var exception = assertThrows(OutCurrException.class,
                () -> acPeriodService.searchAcademicPeriodByNumber(acPeriodToSearch.acPeriodNumeric()),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(error);
        assertEquals("Not Found", statusMessage.getReasonPhrase());
        assertEquals(404, statusCode);
        assertEquals("Academic period numeric not found", error);
    }

    @Test
    void searchAcademicPeriodBySpaNameHappyPath() {
        AcPeriod acPeriodToSearch = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD();
        when(acPeriodRepository.findByAcPeriodNameSpa(any())).thenReturn(Optional.of(acPeriodToSearch));

        var acPeriod = acPeriodService.searchAcademicPeriodBySpaName(acPeriodToSearch.getAcPeriodNameSpa());

        verify(acPeriodRepository, times(1)).findByAcPeriodNameSpa(any());
        assertEquals(acPeriod.acPeriodNumeric(), acPeriodToSearch.getAcPeriodNumeric());
        assertEquals(acPeriod.acPeriodId(), acPeriodToSearch.getAcPeriodId());
    }

    @Test
    void searchAcademicPeriodBySpaNameNotFound() {
        AcPeriod acPeriodToSearch = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD();
        when(acPeriodRepository.findByAcPeriodNameSpa(any())).thenReturn(Optional.empty());

        var exception = assertThrows(OutCurrException.class,
                () -> acPeriodService.searchAcademicPeriodBySpaName(acPeriodToSearch.getAcPeriodNameSpa()),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(error);
        assertEquals("Not Found", statusMessage.getReasonPhrase());
        assertEquals(404, statusCode);
        assertEquals("Academic period name (spanish) not found", error);
    }

    @Test
    void getAllAcademicPeriodsHappyPath() {
        List<AcPeriod> acPeriods = new ArrayList<>();
        acPeriods.add(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD());
        acPeriods.add(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD2());

        when(acPeriodRepository.findAll()).thenReturn(acPeriods);
        List<AcadPeriodOutDTO> acadPeriodsObtained = acPeriodService.getAllAcademicPeriods();

        verify(acPeriodRepository, times(1)).findAll();
        assertEquals(2, acadPeriodsObtained.size(), "Expected 2 academic periods");
        assertEquals(1000L, acadPeriodsObtained.get(0).acPeriodId(), "Expected 1000 in first academic period");
        assertEquals(2000L, acadPeriodsObtained.get(1).acPeriodId(), "Expected 1000 in second academic period");
    }

    @Test
    void getAllOfferedCoursesWithEmptyList() {
        when(acPeriodRepository.findAll()).thenReturn(Collections.emptyList());
        List<AcadPeriodOutDTO> acadPeriodsObtained = acPeriodService.getAllAcademicPeriods();

        verify(acPeriodRepository, times(1)).findAll();
        assertEquals(0, acadPeriodsObtained.size());
        assertTrue(acadPeriodsObtained.isEmpty());
    }

    @Test
    void editAcademicPeriodHappyPath(){
        AcadPeriodInDTO acadPeriodToEdit = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD_IN_DTO();
        when(acPeriodRepository.findById(any())).thenReturn(Optional.of(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD2()));

        Set<ConstraintViolation<AcadPeriodInDTO>> numViolations = getViolations(acadPeriodToEdit);
        assertEquals(0, numViolations.size());

        AcadPeriodOutDTO acPeriodUpdated = acPeriodService.setAcademicPeriod(
                OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD2().getAcPeriodId(), acadPeriodToEdit);

        verify(changeLogService, times(1)).addChange(any(), any(), any(), any(), any());
        verify(acPeriodRepository, times(1)).findByAcPeriodNumeric(acadPeriodToEdit.acPeriodNumeric());
        verify(acPeriodRepository, times(1)).findByAcPeriodNameSpa(any());
        verify(acPeriodRepository, times(1)).save(any());

        assertEquals(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD2().getAcPeriodId(), acPeriodUpdated.acPeriodId());
        assertEquals(acadPeriodToEdit.acPeriodNumeric(), acPeriodUpdated.acPeriodNumeric());
        assertEquals(acadPeriodToEdit.acPeriodNameSpa(), acPeriodUpdated.acPeriodNameSpa());
        assertEquals(acadPeriodToEdit.acPeriodNameEng(), acPeriodUpdated.acPeriodNameEng());
    }

    @Test
    void editAcademicPeriodByIdNotFound() {
        AcPeriod acPeriodToEdit = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD();
        when(acPeriodRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(OutCurrException.class,
                () -> acPeriodService.setAcademicPeriod(acPeriodToEdit.getAcPeriodId(), OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD_IN_DTO()),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(error);
        assertEquals("Not Found", statusMessage.getReasonPhrase());
        assertEquals(404, statusCode);
        assertEquals("Academic period ID not found", error);
    }

    @Test
    void editAcademicPeriodWithDuplicatedSpaName() {
        AcPeriod acPeriodToEdit = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD();
        when(acPeriodRepository.findById(any())).thenReturn(Optional.of(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD()));
        when(acPeriodRepository.findById(any())).thenReturn(Optional.of(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD2()));
        when(acPeriodRepository.findByAcPeriodNameSpa(any())).thenReturn(Optional.of(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD2()));

        var exception = assertThrows(OutCurrException.class,
                () -> acPeriodService.setAcademicPeriod(acPeriodToEdit.getAcPeriodId(), OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD_IN_DTO()),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(error);
        assertEquals("Conflict", statusMessage.getReasonPhrase());
        assertEquals(409, statusCode);
        assertEquals("Academic period name (spanish) duplicate", error);
    }

    @Test
    void editAcademicPeriodWithDuplicatedNumeric() {
        AcPeriod acPeriodToEdit = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD2();
        when(acPeriodRepository.findById(any())).thenReturn(Optional.of(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD()));
        when(acPeriodRepository.findById(any())).thenReturn(Optional.of(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD2()));
        when(acPeriodRepository.findByAcPeriodNumeric(anyInt())).thenReturn(Optional.of(OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD2()));

        var exception = assertThrows(OutCurrException.class,
                () -> acPeriodService.setAcademicPeriod(acPeriodToEdit.getAcPeriodId(), OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD_IN_DTO()),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(error);
        assertEquals("Conflict", statusMessage.getReasonPhrase());
        assertEquals(409, statusCode);
        assertEquals("Academic period numeric duplicate", error);
    }

    @Test
    void deleteAcademicPeriodHappyPath(){
        AcPeriod acPeriodToDelete = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD();
        when(acPeriodRepository.findById(any())).thenReturn(Optional.of(acPeriodToDelete));

        acPeriodService.deleteAcademicPeriod(acPeriodToDelete.getAcPeriodId());

        verify(changeLogService, times(1)).addChange(any(), any(), any(), any(), any());
        verify(acPeriodRepository, times(1)).deleteById(any());

        when(acPeriodRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(OutCurrException.class,
                () -> acPeriodService.searchAcademicPeriod(acPeriodToDelete.getAcPeriodId()),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(error);
        assertEquals("Not Found", statusMessage.getReasonPhrase());
        assertEquals(404, statusCode);
        assertEquals("Academic period ID not found", error);
    }

    @Test
    void deleteAcademicPeriodFail(){
        AcPeriod acPeriodToDelete = OutcomeCurrMgmtUtil.DEFAULT_ACPERIOD();
        when(acPeriodRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(OutCurrException.class,
                () -> acPeriodService.deleteAcademicPeriod(acPeriodToDelete.getAcPeriodId()),
                "No exception was thrown");

        String error = exception.getMessage();
        HttpStatus statusMessage = exception.getOutCurrExceptionType().getResponseStatus();
        int statusCode = exception.getOutCurrExceptionType().getResponseStatus().value();

        assertNotNull(error);
        assertEquals("Not Found", statusMessage.getReasonPhrase());
        assertEquals(404, statusCode);
        assertEquals("Academic period ID not found", error);
    }

    private Set<ConstraintViolation<AcadPeriodInDTO>> getViolations(AcadPeriodInDTO acadPeriodToCreate) {
        return validator.validate(acadPeriodToCreate);
    }

    private AcadPeriodInDTO defaultAcademicPeriodWithEmptyEngName() {
        return AcadPeriodInDTO.builder()
                .acPeriodNameSpa("Ingeniería de sistemas")
                .acPeriodNumeric(202302)
                .build();
    }

    private AcadPeriodInDTO defaultAcademicPeriodWithEmptySpaName() {
        return AcadPeriodInDTO.builder()
                .acPeriodNameEng("Systems engineer")
                .acPeriodNumeric(202302)
                .build();
    }

    private AcadPeriodInDTO defaultAcademicPeriodWithEmptyNumeric() {
        return AcadPeriodInDTO.builder()
                .acPeriodNameSpa("Ingeniería de sistemas")
                .acPeriodNameEng("Systems engineer")
                .build();
    }

    private AcadPeriodInDTO defaultAcademicPeriodWithNumericValueGreaterThanTheLimit() {
        return AcadPeriodInDTO.builder()
                .acPeriodNameSpa("Ingeniería de sistemas")
                .acPeriodNameEng("Systems engineer")
                .acPeriodNumeric(301002)
                .build();
    }

    private AcadPeriodInDTO defaultAcademicPeriodWithNullAttributes() {
        return AcadPeriodInDTO.builder()
                .build();
    }
}
