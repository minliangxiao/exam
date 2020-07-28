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
 * 测试
 * </p>
 */
@TableName("exam")
public class Exam implements Serializable {
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

    @TableField(value="testpaper_id")
    private Integer testpaperId;
    public void setTestpaperId(Integer testpaperId){
        this.testpaperId = testpaperId;
    }
    public Integer getTestpaperId(){
        return this.testpaperId;
    }
    @TableField(exist=false)
    private Testpaper testpaper;
    public void setTestpaper(Testpaper testpaper){
        this.testpaper = testpaper;
    }
    public Testpaper getTestpaper(){
        return this.testpaper;
    }       

    @TableField(value="name")
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @TableField(value="start_time")
    private Date startTime;
    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }
    public Date getStartTime(){
        return this.startTime;
    }
    @TableField(value="total_student_qty")
    private Integer totalStudentQty;
    public void setTotalStudentQty(Integer totalStudentQty){
        this.totalStudentQty = totalStudentQty;
    }
    public Integer getTotalStudentQty(){
        return this.totalStudentQty;
    }
    @TableField(value="exam_student_qty")
    private Integer examStudentQty;
    public void setExamStudentQty(Integer examStudentQty){
        this.examStudentQty = examStudentQty;
    }
    public Integer getExamStudentQty(){
        return this.examStudentQty;
    }
    @TableField(value="end_time")
    private Date endTime;
    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }
    public Date getEndTime(){
        return this.endTime;
    }
    @TableField(value="finish_student_qty")
    private Integer finishStudentQty;
    public void setFinishStudentQty(Integer finishStudentQty){
        this.finishStudentQty = finishStudentQty;
    }
    public Integer getFinishStudentQty(){
        return this.finishStudentQty;
    }
    @TableField(value="status")
    private String status;
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    @TableField(value="is_deleted")
    private Boolean isDeleted;
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
    @TableField(value="total_score")
    private Integer totalScore;
    public void setTotalScore(Integer totalScore){
        this.totalScore = totalScore;
    }
    public Integer getTotalScore(){
        return this.totalScore;
    }
    @TableField(value="avg_score")
    private BigDecimal avgScore;
    public void setAvgScore(BigDecimal avgScore){
        this.avgScore = avgScore;
    }
    public BigDecimal getAvgScore(){
        return this.avgScore;
    }
    @TableField(value="type")
    private String type;
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    @TableField(value="public_answer")
    private String publicAnswer;
    public void setPublicAnswer(String publicAnswer){
        this.publicAnswer = publicAnswer;
    }
    public String getPublicAnswer(){
        return this.publicAnswer;
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

}


