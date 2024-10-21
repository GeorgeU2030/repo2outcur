package co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PerfLvlInDTO(
        @ApiModelProperty(required = true)
        @NotBlank(message = "is missing")
        String plNameEng,
        @ApiModelProperty(required = true)
        @NotBlank(message = "is missing")
        String plNameSpa,
        @ApiModelProperty
        int plOrder,
        @ApiModelProperty(required = true)
        @NotNull(message = "is missing")
        char plIsActive

) {

}
