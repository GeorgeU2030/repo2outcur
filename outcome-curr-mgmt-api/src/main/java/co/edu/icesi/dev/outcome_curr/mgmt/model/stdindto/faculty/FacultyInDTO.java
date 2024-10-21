package co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record FacultyInDTO(
        @ApiModelProperty(required = true)
        @NotBlank(message = "The faculty status cannot blank or contain only white spaces")
        @Size(min = 1, max = 1, message = "The faculty status must be a single character Y or N")
        String isActive,
        @ApiModelProperty(required = true)
        @NotBlank(message = "Name of a faculty in english cannot be blank or contain only white spaces")
        String facNameEng,
        @ApiModelProperty(required = true)
        @NotBlank(message = "Name of a faculty in spanish cannot be blank or contain only white spaces")
        String facNameSpa,
        @JsonProperty("facId")
        String externalId
) {

}
