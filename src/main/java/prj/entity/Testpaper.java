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
 * 试卷
 * </p>
 */
@TableName("testpaper")
public class Testpaper implements Serializable {
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

    @TableField(value="subject_id")
    private Integer subjectId;
    public void setSubjectId(Integer subjectId){
        this.subjectId = subjectId;
    }
    public Integer getSubjectId(){
        return this.subjectId;
    }
    @TableField(exist=false)
    private Subject subject;
    public void setSubject(Subject subject){
        this.subject = subject;
    }
    public Subject getSubject(){
        return this.subject;
    }       

    @TableField(value="name")
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @TableField(value="code")
    private String code;
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    @TableField(value="minutes")
    private Integer minutes;
    public void setMinutes(Integer minutes){
        this.minutes = minutes;
    }
    public Integer getMinutes(){
        return this.minutes;
    }
    @TableField(value="total_score")
    private Integer totalScore;
    public void setTotalScore(Integer totalScore){
        this.totalScore = totalScore;
    }
    public Integer getTotalScore(){
        return this.totalScore;
    }
    @TableField(value="is_deleted")
    private Boolean isDeleted;
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public Boolean getIsDeleted(){
        return this.isDeleted;
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


