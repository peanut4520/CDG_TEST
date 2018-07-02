package th.co.cdg.train.workshop.bean;

import java.io.Serializable;

public class SkillBean implements Serializable{

	private static final long serialVersionUID = -6900292983241060057L;
	private String skillId;
	private String employeeId;
	private String skillName;
	private String skillDesc;
	
	public String getSkillId() {
		return skillId;
	}
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getSkillDesc() {
		return skillDesc;
	}
	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}
	
}
