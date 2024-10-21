package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "NOTIFICATION_NOTIID_GENERATOR", sequenceName = "NOTIFICATION_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTIFICATION_NOTIID_GENERATOR")
    @Column(name = "NOTI_ID")
    private long notiId;

    @Temporal(TemporalType.DATE)
    @Column(name = "NOTI_CREATION_DATE")
    private Date notiCreationDate;

    @Column(name = "NOTI_DESCRIPTION")
    private String notiDescription;

    @Column(name = "NOTI_TITLE")
    private String notiTitle;

    //bi-directional many-to-one association to NotificationRole
    @OneToMany(mappedBy = "notification")
    private List<NotificationRole> notificationRoles;

    //bi-directional many-to-one association to ReadingStatus
    @OneToMany(mappedBy = "notification")
    private List<ReadingStatus> readingStatuses;

    public Notification() {
        //Entity constructor
    }

    public long getNotiId() {
        return this.notiId;
    }

    public void setNotiId(long notiId) {
        this.notiId = notiId;
    }

    public Date getNotiCreationDate() {
        return this.notiCreationDate;
    }

    public void setNotiCreationDate(Date notiCreationDate) {
        this.notiCreationDate = notiCreationDate;
    }

    public String getNotiDescription() {
        return this.notiDescription;
    }

    public void setNotiDescription(String notiDescription) {
        this.notiDescription = notiDescription;
    }

    public String getNotiTitle() {
        return this.notiTitle;
    }

    public void setNotiTitle(String notiTitle) {
        this.notiTitle = notiTitle;
    }

    public List<NotificationRole> getNotificationRoles() {
        return this.notificationRoles;
    }

    public void setNotificationRoles(List<NotificationRole> notificationRoles) {
        this.notificationRoles = notificationRoles;
    }

    public NotificationRole addNotificationRole(NotificationRole notificationRole) {
        getNotificationRoles().add(notificationRole);
        notificationRole.setNotification(this);

        return notificationRole;
    }

    public NotificationRole removeNotificationRole(NotificationRole notificationRole) {
        getNotificationRoles().remove(notificationRole);
        notificationRole.setNotification(null);

        return notificationRole;
    }

    public List<ReadingStatus> getReadingStatuses() {
        return this.readingStatuses;
    }

    public void setReadingStatuses(List<ReadingStatus> readingStatuses) {
        this.readingStatuses = readingStatuses;
    }

    public ReadingStatus addReadingStatus(ReadingStatus readingStatus) {
        getReadingStatuses().add(readingStatus);
        readingStatus.setNotification(this);

        return readingStatus;
    }

    public ReadingStatus removeReadingStatus(ReadingStatus readingStatus) {
        getReadingStatuses().remove(readingStatus);
        readingStatus.setNotification(null);

        return readingStatus;
    }

}