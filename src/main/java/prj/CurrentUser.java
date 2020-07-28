package prj;

import java.io.Serializable;

public class CurrentUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1166604362451353527L;
	private int id;
	private String textId;
	private String name;
	private String role;
	private Integer schoolId;
	private String schoolName;
	private String face;
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTextId() {
		return textId;
	}
	public void setTextId(String textId) {
		this.textId = textId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	public CurrentUser(){}
	public CurrentUser(int id, String name){
		this(id,name,null,null);
	}
	public CurrentUser(int id, String name,String role,String textId){
		setId(id);
		setName(name);
		setRole(role);
		setTextId(textId);
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
}
