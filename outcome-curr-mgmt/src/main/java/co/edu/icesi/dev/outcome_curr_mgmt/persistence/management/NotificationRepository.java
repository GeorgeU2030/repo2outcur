package co.edu.icesi.dev.outcome_curr_mgmt.persistence.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
