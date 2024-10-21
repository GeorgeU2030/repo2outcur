package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.matcher;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class FacultyMatcher implements ArgumentMatcher<Faculty> {

    private final Faculty facultyLeft;

    public FacultyMatcher(Faculty facultyLeft){
        this.facultyLeft = facultyLeft;
    }

    @Override
    public boolean matches(Faculty facultyRight) {
        return  Objects.equals(facultyRight.getFacNameSpa(), facultyLeft.getFacNameSpa()) &&
                Objects.equals(facultyRight.getFacNameEng(), facultyLeft.getFacNameEng()) &&
                Objects.equals(facultyRight.getFacIsActive(), facultyLeft.getFacIsActive());
    }
}
