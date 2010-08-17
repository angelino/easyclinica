package br.com.easyclinica.view;

public class Link {
	private String Href;
	private String Description;
	private String Target;
	
	public Link(){ }
	
	public Link(String Href, String Description)
	{
		this.Href = Href;
		this.Description = Description;
		this.Target = "_self";
	}
	
	public Link(String Href, String Description, String Target) 
	{
		this.Href = Href;
		this.Description = Description;
		this.Target = Target;
	}
	
	public void setHref(String href) {
		Href = href;
	}
	public String getHref() {
		return Href;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	public String getDescription() {
		return Description;
	}
	
	public void setTarget(String target) {
		Target = target;
	}
	public String getTarget() {
		return Target;
	}
}
