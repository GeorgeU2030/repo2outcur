package co.edu.icesi.dev.outcome_curr.mgmt.rs.banner;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyNamesRequestDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthFacultyPermissions.ROLE_ADMIN_FACULTIES_ANY;
import static co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthFacultyPermissions.ROLE_QUERY_FACULTIES_ANY;

@Tag(name ="FacultyWebService")
@RestController
@RequestMapping(value = "/v1/external/banner/faculties")
public interface BannerFacultyImportController {
    String OK = "Ok";
    String NOT_FOUND = "Missing required fields";
    String UN_AUTHORIZED = "User credentials are needed for this request";

    @GetMapping("/")
    @Operation(summary = "Get the list of all faculties in banner")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FACULTIES_ANY+"','"+ROLE_QUERY_FACULTIES_ANY+"')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FacultyOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ROLE_ADMIN_FACULTIES_ANY+" AND "+ROLE_QUERY_FACULTIES_ANY))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = "Theres no faculties in banner"))
            })
    })
    List<FacultyOutDTO> getFacultiesList();

    @GetMapping("/page")
    @Operation(summary = "Get a page of faculties in banner")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FACULTIES_ANY+"','"+ROLE_QUERY_FACULTIES_ANY+"')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FacultyOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ROLE_ADMIN_FACULTIES_ANY+" AND "+ROLE_QUERY_FACULTIES_ANY))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = "The faculties page is empty"))
            })
    })
    Page<FacultyOutDTO> getFacultiesPage(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size
    );

    @PostMapping("/")
    @Operation(summary = "Import a faculty")
    @PreAuthorize("hasRole('"+ROLE_ADMIN_FACULTIES_ANY+"')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FacultyOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ ROLE_ADMIN_FACULTIES_ANY))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = "Faculties names not found in banner"))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = "There is another faculty with the same name"))
            })
    })
    List<FacultyOutDTO> importFaculties(
        @Valid @RequestBody FacultyNamesRequestDTO facultyNamesRequestDTO
    );
}
