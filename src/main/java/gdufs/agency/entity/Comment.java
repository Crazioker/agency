package gdufs.agency.entity;

public class Comment {
    private Integer commentid;

    private String publishid;

    private String acceptid;

    private Integer indentid;

    private String content;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public String getPublishid() {
        return publishid;
    }

    public void setPublishid(String publishid) {
        this.publishid = publishid == null ? null : publishid.trim();
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}