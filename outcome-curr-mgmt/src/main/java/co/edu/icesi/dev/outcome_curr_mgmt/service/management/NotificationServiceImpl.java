package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

}
