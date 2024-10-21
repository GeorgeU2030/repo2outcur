package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.audit;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@NamedQuery(name = "Changelog.findAll", query = "SELECT c FROM Changelog c")
public class Changelog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CHANGELOG_CLOGID_GENERATOR", allocationSize = 1, sequenceName = "CHANGELOG_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHANGELOG_CLOGID_GENERATOR")
    @Column(name = "CLOG_ID")
    private long clogId;

    @Column(name="CLOG_ACTION")
    private String clogAction;

    @Column(name="CLOG_AFFECTED_RECORD_ID")
    private String clogAffectedRecordId;

    @Column(name="CLOG_AFFECTED_TABLE")
    private String clogAffectedTable;

    @Column(name="CLOG_LOG_NEW_VAL", length = 1000)
    private String clogLogNewVal;

    @Column(name="CLOG_LOG_OLD_VAL", length = 1000)
    private String clogLogOldVal;

    @Column(name="CLOG_TIMESTAMP")
    private Timestamp clogTimestamp;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name="USR_USR_ID")
    private User user;

    public Changelog() {
    }

    public long getClogId() {
        return this.clogId;
    }

    public void setClogId(long clogId) {
        this.clogId = clogId;
    }

    public String getClogAction() {
        return this.clogAction;
    }

    public void setClogAction(String clogAction) {
        this.clogAction = clogAction;
    }

    public String getClogAffectedRecordId() {
        return this.clogAffectedRecordId;
    }

    public void setClogAffectedRecordId(String clogAffectedRecordId) {
        this.clogAffectedRecordId = clogAffectedRecordId;
    }

    public String getClogAffectedTable() {
        return this.clogAffectedTable;
    }

    public void setClogAffectedTable(String clogAffectedTable) {
        this.clogAffectedTable = clogAffectedTable;
    }

    public String getClogLogNewVal() {
        return this.clogLogNewVal;
    }

    public void setClogLogNewVal(String clogLogNewVal) {
        this.clogLogNewVal = clogLogNewVal;
    }

    public String getClogLogOldVal() {
        return this.clogLogOldVal;
    }

    public void setClogLogOldVal(String clogLogOldVal) {
        this.clogLogOldVal = clogLogOldVal;
    }

    public Timestamp getClogTimestamp() {
        return this.clogTimestamp;
    }

    public void setClogTimestamp(Timestamp clogTimestamp) {
        this.clogTimestamp = clogTimestamp;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}