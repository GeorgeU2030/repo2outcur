package co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record FacultyNamesRequestDTO(
        @ApiModelProperty(required = true)
        @NotEmpty(message = "Must enter at least the name of one faculty.")
        List<String> facultiesName
) {
}
