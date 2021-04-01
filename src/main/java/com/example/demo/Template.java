package com.example.demo;

public class Template {

	private Long id;	
	private String projectUrl;
	private String groupId;
	private String artifactId;
	private String pkg;
	private String version;
	
	protected Template() {
		
	}

	public Template(Long id, String projectUrl, String groupId, String artifactId, String pkg, String version) {
		super();
		this.id = id;
		this.projectUrl = projectUrl;
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.pkg = pkg;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectUrl() {
		return projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}	
	
}