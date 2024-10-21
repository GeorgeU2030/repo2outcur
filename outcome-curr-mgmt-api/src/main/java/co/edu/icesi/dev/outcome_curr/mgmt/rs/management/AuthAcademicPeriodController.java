package co.edu.icesi.dev.outcome_curr.mgmt.rs.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.AcadPeriodInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.AcadPeriodOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "AcademicPeriodWebService")
@RestController
@RequestMapping(value = "/v1/auth/acad_periods")
public interface AuthAcademicPeriodController {

    String OK = "OK";

    String ACPERIOD_ID_NOTFOUND = "Academic period not found";

    String DUPLICATED_SPA_NAME = "Spanish name duplicated";

    String ADMIN_ACAD_PERIODS_REQUIRED = "A permission of Admin-per-state is required for this operation";

    String DUPLICATED_NUMERIC = "Numeric duplicated";

    String UN_AUTHORIZED = "User credentials are needed for this request";

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_Admin-per-state')")
    @Operation(summary = "Create academic period")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AcadPeriodOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_ACAD_PERIODS_REQUIRED))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = DUPLICATED_SPA_NAME))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = DUPLICATED_NUMERIC))})})
    AcadPeriodOutDTO addAcademicPeriod(@Valid @RequestBody AcadPeriodInDTO academicPeriodToCreate);

    @GetMapping("/{acadPeriodId}")
    @Operation(summary = "Get academic period")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AcadPeriodOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = ACPERIOD_ID_NOTFOUND))})})
    AcadPeriodOutDTO getAcademicPeriod(@PathVariable Long acadPeriodId);

    @GetMapping
    @Operation(summary = "Get all academic periods")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AcadPeriodOutDTO.class, $comment = OK), mediaType = "application/json")})})
    List<AcadPeriodOutDTO> getAllAcademicPeriods();

    @PutMapping("/{acadPeriodId}")
    @PreAuthorize("hasAnyRole('ROLE_Admin-per-state')")
    @Operation(summary = "Edit academic period")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AcadPeriodOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_ACAD_PERIODS_REQUIRED))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = ACPERIOD_ID_NOTFOUND))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = DUPLICATED_SPA_NAME))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = DUPLICATED_NUMERIC))})
    })
    AcadPeriodOutDTO setAcademicPeriod(@PathVariable Long acadPeriodId, @Valid @RequestBody AcadPeriodInDTO academicPeriodToCreate);


    @DeleteMapping("/{acadPeriodId}")
    @PreAuthorize("hasAnyRole('ROLE_Admin-per-state')")
    @Operation(summary = "Delete academic period")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Academic period delete"),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_ACAD_PERIODS_REQUIRED))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = ACPERIOD_ID_NOTFOUND))})
    })
    ResponseEntity<Void> deleteAcademicPeriod(@PathVariable Long acadPeriodId);
}
