package co.edu.icesi.dev.outcome_curr.mgmt.rs.audit;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.audit.ChangeLogFilterInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.audit.ChangeLogOutDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name ="ChangeLogWebService")
@RestController
@RequestMapping(value = "/v1/admin/change_logs")
public interface AuthChangeLogController {

    String OK = "Ok";
    String UNAUTHORIZED = "A permission of administrator is required for this operation";

    String BAD_REQUEST="Date in filter has invalid format";
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_Query-changelog')")
    @Operation(summary = "Get the changes in ChangeLog")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation =ChangeLogOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UNAUTHORIZED))})
    })
    List<ChangeLogOutDTO> getAllChanges();
    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ROLE_Query-changelog')")
    @Operation(summary = "Get the changes in ChangeLog according to the filter")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation =ChangeLogOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UNAUTHORIZED))}),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema = @Schema($comment = BAD_REQUEST))})
    })
    List<ChangeLogOutDTO> getAllChangesByFilter(@Valid @RequestBody ChangeLogFilterInDTO changeLogDateFilterInDTO);

}
