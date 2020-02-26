package gdufs.agency.entity;

public class Acceptance extends AcceptanceKey {
    private String acceptedtime;

    private String finishedtime;

    private Integer state;

    public String getAcceptedtime() {
        return acceptedtime;
    }

    public void setAcceptedtime(String acceptedtime) {
        this.acceptedtime = acceptedtime == null ? null : acceptedtime.trim();
    }

    public String getFinishedtime() {
        return finishedtime;
    }

    public void setFinishedtime(String finishedtime) {
        this.finishedtime = finishedtime == null ? null : finishedtime.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}