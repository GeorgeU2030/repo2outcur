package co.edu.icesi.dev.outcome_curr.mgmt.rs.management;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="NotificationWebService")
@RestController
@RequestMapping(value = "/v1/auth/users/{userId}/notifications")
public interface AuthNotificationController {

}
