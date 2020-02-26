package gdufs.agency.entity;

public class IndentAccept {
	 private Integer indentid;

	    private Integer type;

	    private Float price;

	    private String description;

	    private String address;

	    private Integer state;

	    private String publishid;

	    private String publishtime;

	    private String plantime;
	    
	    private String acceptid;
	    
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

	    public Integer getType() {
	        return type;
	    }

	    public void setType(Integer type) {
	        this.type = type;
	    }

	    public Float getPrice() {
	        return price;
	    }

	    public void setPrice(Float price) {
	        this.price = price;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description == null ? null : description.trim();
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address == null ? null : address.trim();
	    }

	    public Integer getState() {
	        return state;
	    }

	    public void setState(Integer state) {
	        this.state = state;
	    }

	    public String getPublishid() {
	        return publishid;
	    }

	    public void setPublishid(String publishid) {
	        this.publishid = publishid == null ? null : publishid.trim();
	    }

	    public String getPublishtime() {
	        return publishtime;
	    }

	    public void setPublishtime(String publishtime) {
	        this.publishtime = publishtime == null ? null : publishtime.trim();
	    }

	    public String getPlantime() {
	        return plantime;
	    }

	    public void setPlantime(String plantime) {
	        this.plantime = plantime == null ? null : plantime.trim();
	    }
}
