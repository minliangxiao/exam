package prj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * <p>
 * 用户
 * </p>
 */
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
	 * 主键，自动增长ID。
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

    @TableField(value="school_id")
    private Integer schoolId;
    public void setSchoolId(Integer schoolId){
        this.schoolId = schoolId;
    }
    public Integer getSchoolId(){
        return this.schoolId;
    }
    @TableField(exist=false)
    private School school;
    public void setSchool(School school){
        this.school = school;
    }
    public School getSchool(){
        return this.school;
    }       

    @TableField(value="no")
    private String no;
    public void setNo(String no){
        this.no = no;
    }
    public String getNo(){
        return this.no;
    }
    @TableField(value="name")
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @TableField(value="password")
    private String password;
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    @TableField(value="realname")
    private String realname;
    public void setRealname(String realname){
        this.realname = realname;
    }
    public String getRealname(){
        return this.realname;
    }
    @TableField(value="role")
    private String role;
    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }
    @TableField(value="is_deleted")
    private Boolean isDeleted;
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
    @TableField(value="reg_password")
    private String regPassword;
    public void setRegPassword(String regPassword){
        this.regPassword = regPassword;
    }
    public String getRegPassword(){
        return this.regPassword;
    }
    public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}

	private String face;

    @TableField(value="gender")
    private String gender;
    public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}

	@TableField(value="college")
    private String college;
    @TableField(value="grade")
    private String grade;
    @TableField(value="profession")
    private String profession;
    
}


