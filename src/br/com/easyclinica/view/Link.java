package br.com.easyclinica.view;

public class Link {
	private String href;
	private String description;
	private String target;
	
	public Link(){ }
	
	public Link(String href, String description)
	{
		this.href = href;
		this.description = description;
		this.target = "_self";
	}
	
	public Link(String href, String description, String target) 
	{
		this.href = href;
		this.description = description;
		this.target = target;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	public String getHref() {
		return href;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTarget() {
		return target;
	}
}
