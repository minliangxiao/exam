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
 * 课程
 * </p>
 */
@TableName("subject")
public class Subject implements Serializable {
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

    @TableField(value="teacher_id")
    private Integer teacherId;
    public void setTeacherId(Integer teacherId){
        this.teacherId = teacherId;
    }
    public Integer getTeacherId(){
        return this.teacherId;
    }
    @TableField(exist=false)
    private User teacher;
    public void setTeacher(User teacher){
        this.teacher = teacher;
    }
    public User getTeacher(){
        return this.teacher;
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

    @TableField(value="name")
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @TableField(value="is_deleted")
    private Boolean isDeleted;
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
    @TableField(value="intro")
    private String intro;
    public void setIntro(String intro){
        this.intro = intro;
    }
    public String getIntro(){
        return this.intro;
    }
    @TableField(value="grade_id")
    private Integer gradeId;
    public void setGradeId(Integer gradeId){
        this.gradeId = gradeId;
    }
    public Integer getGradeId(){
        return this.gradeId;
    }
    @TableField(exist=false)
    private Grade grade;
    public void setGrade(Grade grade){
        this.grade = grade;
    }
    public Grade getGrade(){
        return this.grade;
    }       

}


