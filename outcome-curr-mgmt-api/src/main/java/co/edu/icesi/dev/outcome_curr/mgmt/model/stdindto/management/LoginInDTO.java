package co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginInDTO(@ApiModelProperty(required = true) @NotBlank String username,
                         @ApiModelProperty(required = true) @NotBlank String password) {
}
