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
 * 我的课程
 * </p>
 */
@TableName("user_subject")
public class UserSubject implements Serializable {
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

    @TableField(value="user_id")
    private Integer userId;
    public void setUserId(Integer userId){
        this.userId = userId;
    }
    public Integer getUserId(){
        return this.userId;
    }
    @TableField(exist=false)
    private User user;
    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return this.user;
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

    @TableField(value="time")
    private Date time;
    public void setTime(Date time){
        this.time = time;
    }
    public Date getTime(){
        return this.time;
    }
    @TableField(value="is_deleted")
    private Boolean isDeleted;
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
    @TableField(value="is_exam")
    private Boolean isExam;
    public void setIsExam(Boolean isExam){
        this.isExam = isExam;
    }
    public Boolean getIsExam(){
        return this.isExam;
    }
}


