package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.matcher;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class AcadProgramMatcher implements ArgumentMatcher<AcadProgram> {
    private final AcadProgram acadProgramLeft;

    public AcadProgramMatcher(AcadProgram acadProgramLeft){
        this.acadProgramLeft = acadProgramLeft;
    }

    @Override
    public boolean matches(AcadProgram acadProgramRight) {
        return Objects.equals(acadProgramRight.getAcpId(), acadProgramLeft.getAcpId()) &&
                Objects.equals(acadProgramRight.getAcpSnies(), acadProgramLeft.getAcpSnies()) &&
                Objects.equals(acadProgramRight.getAcpProgNameEng(), acadProgramLeft.getAcpProgNameEng()) &&
                Objects.equals(acadProgramRight.getAcpProgDescEng(), acadProgramLeft.getAcpProgDescEng()) &&
                Objects.equals(acadProgramRight.getAcpProgNameSpa(), acadProgramLeft.getAcpProgNameSpa()) &&
                Objects.equals(acadProgramRight.getAcpProgDescSpa(), acadProgramLeft.getAcpProgDescSpa()) &&
                Objects.equals(acadProgramRight.getAcpIsActive(), acadProgramLeft.getAcpIsActive());
    }
}