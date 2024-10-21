package co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.MatrixDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.AssmtGenPlanOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CurricularMappingWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/acad_programs/{acadprogId}/acadprg_curriculums/"
        + "{acadProgCurrId}/curr_map")
public interface AuthCurrMapController {

    String OK = "Ok";

    String CONFLICT = "Incorrect faculty, program ID or curriculum ID";
    String UN_AUTHORIZED = "User credentials are needed for this request";
    String QUERY_FUTURE_OWN_ACADEMIC_PROGRAMS = "A permission of Query-future-acad_programs-own or superior is required for this operation";
    String BAD_REQUEST = "Missing required fields";
    String NO_CONTENT = "No content";

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_Query-future-acad_programs-own', "
            + "'ROLE_Admin-current-acad_programs-own', "
            + "'ROLE_Query-inactive-acad_programs-own')")
    @Operation(summary = "Get the matrix of the current curriculum")
    //TODO roles above own should also have access to the matrix. Also, there roles al wrong, should be 'Query-future-curr_map-any' 'Query-XXXXXX-curr_map-XXX
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssmtGenPlanOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = QUERY_FUTURE_OWN_ACADEMIC_PROGRAMS))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = CONFLICT))})})
    MatrixDTO getMatrix(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadprogId") long acadprogId,
            @PathVariable("acadProgCurrId") long acadProgCurrId);

    @PatchMapping(value = "/prevCurrMapId/{prevCurrMapId}/successorCurrMapId/{successorCurrMapId}/destinationState/{destinationState}")
    @Operation(summary = "Update the state of a curricular mapping")
    @PreAuthorize("hasAnyRole('ROLE_Admin-current-acad_programs-own', "
            + "'ROLE_Admin-future-acad_programs-own', "
            + "'ROLE_Admin-inactive-acad_programs-own')")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {
                    @Content(schema = @Schema($comment = NO_CONTENT))}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = QUERY_FUTURE_OWN_ACADEMIC_PROGRAMS))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = CONFLICT))}),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema($comment = BAD_REQUEST))})})
    void updateSuggestedCurrMapRequestStatus(@PathVariable long facultyId, @PathVariable long acadprogId, @PathVariable long acadProgCurrId, @PathVariable long prevCurrMapId, @PathVariable long successorCurrMapId, @PathVariable String destinationState);
}

