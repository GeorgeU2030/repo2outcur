package co.edu.icesi.dev.outcome_curr_mgmt.rs.faculty;


import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.PerfLvlInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.PerfLvlOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthPerfLvlController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.PerfLvlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthPerfLvlControllerImpl implements AuthPerfLvlController {
    private final PerfLvlService perfLvlService;

    @Override
    public PerfLvlOutDTO getPerfLvl(long perfLvlId, long acadProgId, long facultyId) {
        return perfLvlService.getPerfLvl(perfLvlId, acadProgId,facultyId);
    }

    @Override
    public List<PerfLvlOutDTO> getAllPerfLvls(long acadProgId, long facultyId) {
        return perfLvlService.getAllPerfLvls(acadProgId,facultyId);
    }
    @Override
    public PerfLvlOutDTO addPerfLvl(PerfLvlInDTO perfLvlInDTO, long acadProgId,long facultyId) {
        return perfLvlService.addPerfLvl(perfLvlInDTO,acadProgId,facultyId);
    }

    @Override
    public PerfLvlOutDTO updatePerfLvl(PerfLvlInDTO perfLvlInDTONew, long acadProgId, long facultyId, long perfLvlId) {
        return perfLvlService.updatePerfLvl(perfLvlInDTONew,acadProgId,facultyId,perfLvlId);
    }

    @Override
    public ResponseEntity<Void> deletePerfLvl(long acadProgId, long facultyId, long perfLvlId) {
         perfLvlService.deletePerfLvl(acadProgId, facultyId, perfLvlId);
        return ResponseEntity.noContent().build();
    }

}
