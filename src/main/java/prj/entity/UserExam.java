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
 * 考试结果
 * </p>
 */
@TableName("user_exam")
public class UserExam implements Serializable {
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

    @TableField(value="student_id")
    private Integer studentId;
    public void setStudentId(Integer studentId){
        this.studentId = studentId;
    }
    public Integer getStudentId(){
        return this.studentId;
    }
    @TableField(exist=false)
    private User student;
    public void setStudent(User student){
        this.student = student;
    }
    public User getStudent(){
        return this.student;
    }       
    @TableField(value="exam_id")
    private Integer examId;
    public void setExamId(Integer examId){
        this.examId = examId;
    }
    public Integer getExamId(){
        return this.examId;
    }
    @TableField(exist=false)
    private Exam exam;
    public void setExam(Exam exam){
        this.exam = exam;
    }
    public Exam getExam(){
        return this.exam;
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

    @TableField(value="time")
    private Date time;
    public void setTime(Date time){
        this.time = time;
    }
    public Date getTime(){
        return this.time;
    }
    @TableField(value="right_qty")
    private Integer rightQty;
    public void setRightQty(Integer rightQty){
        this.rightQty = rightQty;
    }
    public Integer getRightQty(){
        return this.rightQty;
    }
    @TableField(value="error_qty")
    private Integer errorQty;
    public void setErrorQty(Integer errorQty){
        this.errorQty = errorQty;
    }
    public Integer getErrorQty(){
        return this.errorQty;
    }
    @TableField(value="score")
    private Integer score;
    public void setScore(Integer score){
        this.score = score;
    }
    public Integer getScore(){
        return this.score;
    }
    @TableField(value="is_deleted")
    private Boolean isDeleted;
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
    @TableField(value="comment")
    private String comment;
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return this.comment;
    }
    @TableField(value="status")
    private String status;
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    @TableField(value="reg_time")
    private Date regTime;
    public void setRegTime(Date regTime){
        this.regTime = regTime;
    }
    public Date getRegTime(){
        return this.regTime;
    }

    public Integer getSubjectMaxScore() {
		return subjectMaxScore;
	}
	public void setSubjectMaxScore(Integer subjectMaxScore) {
		this.subjectMaxScore = subjectMaxScore;
	}

	public Integer getSubjectMinScore() {
		return subjectMinScore;
	}
	public void setSubjectMinScore(Integer subjectMinScore) {
		this.subjectMinScore = subjectMinScore;
	}
	@TableField(exist=false)
	private Integer subjectMaxScore;
	@TableField(exist=false)
    private Integer subjectMinScore;
}


