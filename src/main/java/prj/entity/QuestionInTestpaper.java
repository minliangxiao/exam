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
 * 【QuestionInTestpaper】
 * </p>
 */
@TableName("question_in_testpaper")
public class QuestionInTestpaper implements Serializable {
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

    @TableField(value="is_deleted")
    private Boolean isDeleted;
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
    @TableField(value="score")
    private Integer score;
    public void setScore(Integer score){
        this.score = score;
    }
    public Integer getScore(){
        return this.score;
    }

}


