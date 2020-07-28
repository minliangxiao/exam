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
 * 试题
 * </p>
 */
@TableName("question")
public class Question implements Serializable {
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

    @TableField(value="name")
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @TableField(value="type")
    private String type;
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    @TableField(value="a")
    private String a;
    public void setA(String a){
        this.a = a;
    }
    public String getA(){
        return this.a;
    }
    @TableField(value="b")
    private String b;
    public void setB(String b){
        this.b = b;
    }
    public String getB(){
        return this.b;
    }
    @TableField(value="c")
    private String c;
    public void setC(String c){
        this.c = c;
    }
    public String getC(){
        return this.c;
    }
    @TableField(value="d")
    private String d;
    public void setD(String d){
        this.d = d;
    }
    public String getD(){
        return this.d;
    }
    @TableField(value="correct")
    private String correct;
    public void setCorrect(String correct){
        this.correct = correct;
    }
    public String getCorrect(){
        return this.correct;
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
    @TableField(value="explain_text")
    private String explainText;
    public void setExplainText(String explainText){
        this.explainText = explainText;
    }
    public String getExplainText(){
        return this.explainText;
    }

    @TableField(value="difficult")
    private Integer difficult;
    public void setDifficult(Integer difficult){
        this.difficult = difficult;
    }
    public Integer getDifficult(){
        return this.difficult;
    }
    @TableField(exist=false)
    private String difficultString;
    public String getDifficultString(){
    	if(difficult==null) return "";
    	if(difficult==1) return "容易";
    	if(difficult==2) return "一般";
    	if(difficult==3) return "困难";
    	return "";
    }
    public void setDifficultString(){
    }    
}


