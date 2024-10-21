package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.PerfLvlInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.PerfLvlOutDTO;

import java.util.List;

public interface PerfLvlService {

    PerfLvlOutDTO addPerfLvl(PerfLvlInDTO perfLvlInDTO, long acadprogId,long facultyId);

    PerfLvlOutDTO getPerfLvl(long perfLvlId, long acadprogId,long facultyId);
    List<PerfLvlOutDTO> getAllPerfLvls(long acadprogId,long facultyId);

    PerfLvlOutDTO updatePerfLvl(PerfLvlInDTO perfLvlInDTO, long acadprogId,long facultyId,long perfLvlId);

    void deletePerfLvl(long acadProgId, long facultyId, long perfLvlId);


}
