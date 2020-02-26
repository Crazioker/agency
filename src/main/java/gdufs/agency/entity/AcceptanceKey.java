package gdufs.agency.entity;

public class AcceptanceKey {
    private String acceptid;

    private Integer indentid;

    public String getAcceptid() {
        return acceptid;
    }

    public void setAcceptid(String acceptid) {
        this.acceptid = acceptid == null ? null : acceptid.trim();
    }

    public Integer getIndentid() {
        return indentid;
    }

    public void setIndentid(Integer indentid) {
        this.indentid = indentid;
    }
}