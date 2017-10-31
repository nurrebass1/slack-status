package slack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

    private Boolean ok;
    private String presence;
    private Boolean online;
    private Boolean auto_away;
    private Boolean manual_away;
    private Integer connection_count;
    private Date last_activity;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getAuto_away() {
        return auto_away;
    }

    public void setAuto_away(Boolean auto_away) {
        this.auto_away = auto_away;
    }

    public Boolean getManual_away() {
        return manual_away;
    }

    public void setManual_away(Boolean manual_away) {
        this.manual_away = manual_away;
    }

    public Integer getConnection_count() {
        return connection_count;
    }

    public void setConnection_count(Integer connection_count) {
        this.connection_count = connection_count;
    }

    public Date getLast_activity() {
        return last_activity;
    }

    public void setLast_activity(Date last_activity) {
        this.last_activity = last_activity;
    }

    @Override
    public String toString() {
        return "User [presence=" + presence + ", online=" + online + "]";
    }

}
