package co.edu.icesi.dev.outcome_curr_mgmt.service.provider.managment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AcPeriodProviderImpl implements AcPeriodProvider {

    @Value("${current-academic-period}")
    private int currentAcPeriod;

    @Override
    @Transactional
    public int getCurrentAcPeriod() {
        return currentAcPeriod;
    }
}
