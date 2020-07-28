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
 * 答题
 * </p>
 */
@TableName("user_exam_question")
public class UserExamQuestion implements Serializable {
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

    @TableField(value="userexam_id")
    private Integer userexamId;
    public void setUserexamId(Integer userexamId){
        this.userexamId = userexamId;
    }
    public Integer getUserexamId(){
        return this.userexamId;
    }
    @TableField(exist=false)
    private UserExam userexam;
    public void setUserexam(UserExam userexam){
        this.userexam = userexam;
    }
    public UserExam getUserexam(){
        return this.userexam;
    }       
    @TableField(value="question_id")
    private Integer questionId;
    public void setQuestionId(Integer questionId){
        this.questionId = questionId;
    }
    public Integer getQuestionId(){
        return this.questionId;
    }
    @TableField(exist=false)
    private Question question;
    public void setQuestion(Question question){
        this.question = question;
    }
    public Question getQuestion(){
        return this.question;
    }       

    @TableField(value="score")
    private Integer score;
    public void setScore(Integer score){
        this.score = score;
    }
    public Integer getScore(){
        return this.score;
    }
    @TableField(value="correct_answer")
    private String correctAnswer;
    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer = correctAnswer;
    }
    public String getCorrectAnswer(){
        return this.correctAnswer;
    }
    @TableField(value="answer")
    private String answer;
    public void setAnswer(String answer){
        this.answer = answer;
    }
    public String getAnswer(){
        return this.answer;
    }
    @TableField(value="is_right")
    private Boolean isRight;
    public void setIsRight(Boolean isRight){
        this.isRight = isRight;
    }
    public Boolean getIsRight(){
        return this.isRight;
    }
    @TableField(value="is_deleted")
    private Boolean isDeleted;
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
    @TableField(value="is_mark")
    private Boolean isMark;
    public void setIsMark(Boolean isMark){
        this.isMark = isMark;
    }
    public Boolean getIsMark(){
        return this.isMark;
    }
    @TableField(value="total_qty")
    private Integer totalQty;
    public void setTotalQty(Integer totalQty){
        this.totalQty = totalQty;
    }
    public Integer getTotalQty(){
        return this.totalQty;
    }
    @TableField(value="answer_qty")
    private Integer answerQty;
    public void setAnswerQty(Integer answerQty){
        this.answerQty = answerQty;
    }
    public Integer getAnswerQty(){
        return this.answerQty;
    }
    @TableField(value="explain_text")
    private String explainText;
    public void setExplainText(String explainText){
        this.explainText = explainText;
    }
    public String getExplainText(){
        return this.explainText;
    }
    @TableField(value="qscore")
    private Integer qscore;
    public void setQscore(Integer score){
        this.qscore = score;
    }
    public Integer getQscore(){
        return this.qscore;
    }

}


