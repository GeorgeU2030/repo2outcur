package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.AssmtGenPlanInDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.curriculum_qa.AssmtGenPlanMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.AssmtGenPlanRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.curriculum_qa.AssessmentGenPlanValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.AcadProgramValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssessmentGenPlanServiceTest {
    private static final long CORRECT_FACULTY = 1L;
    private static final long CORRECT_PROGRAM = 1L;
    AssmtGenPlanMapper assessmentGenPlanMapper = Mappers.getMapper(AssmtGenPlanMapper.class);

    @Mock
    AcadProgramValidator acadProgramValidator;
    @Mock
    AssessmentGenPlanValidator assessmentGenPlanValidator;
    @Mock
    AssmtGenPlanRepository assessmentGenPlanRepository;
    @InjectMocks
    AssessmentGenPlanServiceImpl assessmentGenPlanService;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(assessmentGenPlanService, "assessmentGenPlanMapper", assessmentGenPlanMapper);
    }

    @Test
        //TODO complete with required validations and asserts
    void createAssessmentGenPlan_happyPath() {
        AssmtGenPlanInDTO assmtGenPlanInDTO = new AssmtGenPlanInDTO(20231, 20232, 0L, 0L, 0L);
        AssmtGenPlan assessmentGenPlanSaved = new AssmtGenPlan();

        when(assessmentGenPlanRepository.save(any())).thenReturn(assessmentGenPlanSaved);
        doNothing().when(assessmentGenPlanValidator).enforceUsrFacForAssessmentGenPlan(anyLong(), any(), any());
        doNothing().when(assessmentGenPlanValidator).enforceUsrPrgForAssessmentGenPlan(anyLong(), any(), any());
        doNothing().when(assessmentGenPlanValidator).enforceUsrAssmtGenForAssessmentGenPlan(anyLong(), any(), any());
        doNothing().when(assessmentGenPlanValidator).validateAssmtGenPlanOnAcadProg(anyLong(),anyLong());
        doNothing().when(assessmentGenPlanValidator).validateAssmtGenPlanCreation(assmtGenPlanInDTO);

        assessmentGenPlanService.createAssmtGenPlan(CORRECT_FACULTY, CORRECT_PROGRAM, assmtGenPlanInDTO);

    }

}
