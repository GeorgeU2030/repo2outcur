package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PerfLvl;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class PerfLvlMatcher implements ArgumentMatcher<PerfLvl> {

    private PerfLvl perfLvlLeft;

    public PerfLvlMatcher(PerfLvl perfLvlLeft){
        this.perfLvlLeft=perfLvlLeft;
    }
    @Override
    public boolean matches(PerfLvl perfLvlRight) {
        return
                Objects.equals(perfLvlRight.getPlNameSpa(),perfLvlLeft.getPlNameSpa()) &&
               Objects.equals(perfLvlRight.getPlNameEng(),perfLvlLeft.getPlNameEng()) &&
                Objects.equals(perfLvlRight.getAcadProgram().getAcpId(),perfLvlLeft.getAcadProgram().getAcpId()) &&
                Objects.equals(perfLvlRight.getPlOrder(),perfLvlLeft.getPlOrder()) &&
                Objects.equals(perfLvlRight.getPlIsActive(),perfLvlLeft.getPlIsActive());

    }
}
