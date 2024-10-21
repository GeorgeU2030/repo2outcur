package co.edu.icesi.dev.outcome_curr_mgmt.academic_registry.dto_std_in;

import lombok.Builder;

@Builder
public record BannerPersonIntInDTO(String id, String nombres, String apellidos, String identificacion, String celular,
                                   String correo) {

}