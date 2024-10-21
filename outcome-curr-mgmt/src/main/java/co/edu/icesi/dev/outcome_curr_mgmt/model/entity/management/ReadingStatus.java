package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "READING_STATUS")
@NamedQuery(name = "ReadingStatus.findAll", query = "SELECT r FROM ReadingStatus r")
public class ReadingStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ReadingStatusPK id;

    @Column(name = "HAS_BEEN_READ")
    private String hasBeenRead;

    //bi-directional many-to-one association to Notification
    @ManyToOne
    @JoinColumn(name = "NOT_NOTI_ID", insertable = false, updatable = false)
    private Notification notification;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "USR_USR_ID", insertable = false, updatable = false)
    private User user;

    public ReadingStatus() {
        //Entity constructor
    }

    public ReadingStatusPK getId() {
        return this.id;
    }

    public void setId(ReadingStatusPK id) {
        this.id = id;
    }

    public String getHasBeenRead() {
        return this.hasBeenRead;
    }

    public void setHasBeenRead(String hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }

    public Notification getNotification() {
        return this.notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}