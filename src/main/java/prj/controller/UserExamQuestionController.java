package prj.controller;
import java.util.ArrayList;
import java.util.List;

  

import java.math.BigDecimal;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import prj.AjaxCallResult;
import prj.ControllerBase;
import prj.entity.*;
import prj.service.*;
import prj.util.*;

/**
 * <p>
 *   答题前端控制器
 * </p>
 */
@Controller
@RequestMapping("/userExamQuestion")
public class UserExamQuestionController extends ControllerBase {
	private static final String ENTITY_NAME = "userExamQuestion";
    @Resource  
    private IUserExamQuestionService service;  
    @Resource  
    private IUserExamService userExamService;  
    @Resource  
    private IQuestionService questionService;  
    @Resource  
    private IExamService examService;  
    
    @RequestMapping("/list")  
    public String list(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"List";  
    }  
      
    @RequestMapping("/show")  
    public String show(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"Show";  
    }  
      
    @RequestMapping("/edit")  
    public String edit(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"Edit";  
    }  
      
    @RequestMapping("/save")  
    public @ResponseBody AjaxCallResult save(HttpServletRequest request,Model model){  
        try{
            UserExamQuestion entity = null;
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
        		int id = Integer.parseInt(eid);  
        		entity = service.selectById(id);
        		Question question = questionService.selectById(entity.getQuestionId());
                if(request.getParameter("userexamId") != null && request.getParameter("userexamId").trim().length() > 0){
                    entity.setUserexamId(Integer.valueOf(request.getParameter("userexamId")));
                }else{
                    entity.setUserexamId(null);
                }
                if(request.getParameter("questionId") != null && request.getParameter("questionId").trim().length() > 0){
                    entity.setQuestionId(Integer.valueOf(request.getParameter("questionId")));
                }else{
                    entity.setQuestionId(null);
                }
                if(request.getParameter("score") != null && request.getParameter("score").trim().length() > 0){
                    entity.setScore(Integer.valueOf(request.getParameter("score")));
                }else{
                    entity.setScore(null);
                }
                entity.setCorrectAnswer(request.getParameter("correctAnswer"));
                entity.setAnswer(request.getParameter("answer"));
                if(request.getParameter("isRight") != null && request.getParameter("isRight").trim().length() > 0){
                    entity.setIsRight(Boolean.valueOf(request.getParameter("isRight")));
                }else{
                    entity.setIsRight(null);
                }
                if(request.getParameter("isMark") != null && request.getParameter("isMark").trim().length() > 0){
                    entity.setIsMark(Boolean.valueOf(request.getParameter("isMark")));
                }else{
                    entity.setIsMark(null);
                }
                if(request.getParameter("totalQty") != null && request.getParameter("totalQty").trim().length() > 0){
                    entity.setTotalQty(Integer.valueOf(request.getParameter("totalQty")));
                }else{
                    entity.setTotalQty(null);
                }
                if(request.getParameter("answerQty") != null && request.getParameter("answerQty").trim().length() > 0){
                    entity.setAnswerQty(Integer.valueOf(request.getParameter("answerQty")));
                }else{
                    entity.setAnswerQty(null);
                }
                entity.setExplainText(request.getParameter("explainText"));
        		
                String qType = request.getParameter("qtype");
                if("选择题".equals(qType) || "判断题".equals(qType) || "填空题".equals(qType)) {
                	if(entity.getAnswer().equalsIgnoreCase(entity.getCorrectAnswer())) {
                		entity.setIsRight(true);
                		entity.setAnswerQty(1);
                		entity.setScore(entity.getQscore());
                	}
//                } else if("填空题".equals(qType)) {
//                	
                } else{
                	if(entity.getAnswer().contains((entity.getCorrectAnswer()))) {
                		entity.setIsRight(true);
                		entity.setAnswerQty(1);
                		entity.setScore(entity.getQscore());
                	}
                }
                
                service.updateById(entity);
        	}else{
        		entity = new UserExamQuestion();
                if(request.getParameter("userexamId") != null && request.getParameter("userexamId").trim().length() > 0){
                    entity.setUserexamId(Integer.valueOf(request.getParameter("userexamId")));
                }else{
                    entity.setUserexamId(null);
                }
                if(request.getParameter("questionId") != null && request.getParameter("questionId").trim().length() > 0){
                    entity.setQuestionId(Integer.valueOf(request.getParameter("questionId")));
                }else{
                    entity.setQuestionId(null);
                }
                if(request.getParameter("score") != null && request.getParameter("score").trim().length() > 0){
                    entity.setScore(Integer.valueOf(request.getParameter("score")));
                }else{
                    entity.setScore(null);
                }
                entity.setCorrectAnswer(request.getParameter("correctAnswer"));
                entity.setAnswer(request.getParameter("answer"));
                if(request.getParameter("isRight") != null && request.getParameter("isRight").trim().length() > 0){
                    entity.setIsRight(Boolean.valueOf(request.getParameter("isRight")));
                }else{
                    entity.setIsRight(null);
                }
                if(request.getParameter("isMark") != null && request.getParameter("isMark").trim().length() > 0){
                    entity.setIsMark(Boolean.valueOf(request.getParameter("isMark")));
                }else{
                    entity.setIsMark(null);
                }
                if(request.getParameter("totalQty") != null && request.getParameter("totalQty").trim().length() > 0){
                    entity.setTotalQty(Integer.valueOf(request.getParameter("totalQty")));
                }else{
                    entity.setTotalQty(null);
                }
                if(request.getParameter("answerQty") != null && request.getParameter("answerQty").trim().length() > 0){
                    entity.setAnswerQty(Integer.valueOf(request.getParameter("answerQty")));
                }else{
                    entity.setAnswerQty(null);
                }
                entity.setExplainText(request.getParameter("explainText"));
    		
            // 添加默认数据。
    		    entity.setIsDeleted(false);
    		    service.insert(entity);
        	}
            
            int rightQty=0, errorQty=0, score=0;
            List<UserExamQuestion> list = service.selectList(new EntityWrapper<UserExamQuestion>().eq("userexam_id", entity.getUserexamId()));
            for (UserExamQuestion q : list) {
				if(q.getIsRight()) {
					rightQty++;
					score+=q.getScore();
				}else{
					errorQty++;
				}
			}
            UserExam uExam = userExamService.selectById(entity.getUserexamId());
            uExam.setRightQty(rightQty);
            uExam.setErrorQty(errorQty);
            uExam.setScore(score);
            userExamService.updateById(uExam);
            
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
    
    
   @RequestMapping("/review")  
   public @ResponseBody AjaxCallResult review(HttpServletRequest request,Model model){  
       try{
           UserExamQuestion entity = null;
           String eid = request.getParameter("id");
           int oldScore = 0,newScore =0;
           if(eid != null && eid.trim().length()>0) {
       		int id = Integer.parseInt(eid);  
       		entity = service.selectById(id);
       		oldScore = entity.getScore();
               if(request.getParameter("userexamId") != null && request.getParameter("userexamId").trim().length() > 0){
                   entity.setUserexamId(Integer.valueOf(request.getParameter("userexamId")));
               }else{
                   entity.setUserexamId(null);
               }
               if(request.getParameter("questionId") != null && request.getParameter("questionId").trim().length() > 0){
                   entity.setQuestionId(Integer.valueOf(request.getParameter("questionId")));
               }else{
                   entity.setQuestionId(null);
               }
               if(request.getParameter("score") != null && request.getParameter("score").trim().length() > 0){
                   entity.setScore(Integer.valueOf(request.getParameter("score")));
                   newScore=entity.getScore();
               }else{
                   entity.setScore(0);
                   newScore=0;
               }
               entity.setCorrectAnswer(request.getParameter("correctAnswer"));
               entity.setAnswer(request.getParameter("answer"));
               if(request.getParameter("isRight") != null && request.getParameter("isRight").trim().length() > 0){
                   entity.setIsRight(Boolean.valueOf(request.getParameter("isRight")));
               }else{
                   entity.setIsRight(null);
               }
               if(request.getParameter("isMark") != null && request.getParameter("isMark").trim().length() > 0){
                   entity.setIsMark(Boolean.valueOf(request.getParameter("isMark")));
               }else{
                   entity.setIsMark(null);
               }
               if(request.getParameter("totalQty") != null && request.getParameter("totalQty").trim().length() > 0){
                   entity.setTotalQty(Integer.valueOf(request.getParameter("totalQty")));
               }else{
                   entity.setTotalQty(null);
               }
               if(request.getParameter("answerQty") != null && request.getParameter("answerQty").trim().length() > 0){
                   entity.setAnswerQty(Integer.valueOf(request.getParameter("answerQty")));
               }else{
                   entity.setAnswerQty(null);
               }
               entity.setExplainText(request.getParameter("explainText"));
               service.updateById(entity);
       	}
           
           int rightQty=0, errorQty=0, score=0;
           List<UserExamQuestion> list = service.selectList(new EntityWrapper<UserExamQuestion>().eq("userexam_id", entity.getUserexamId()));
           for (UserExamQuestion q : list) {
				if(q.getIsRight()) {
					rightQty++;
				}else{
					errorQty++;
				}
			}
           UserExam uExam = userExamService.selectById(entity.getUserexamId());
           uExam.setRightQty(rightQty);
           uExam.setErrorQty(errorQty);
           uExam.setScore(uExam.getScore()-oldScore+newScore);
           userExamService.updateById(uExam);
           
           return new AjaxCallResult();
   	}catch(Exception ex){
   		return new AjaxCallResult(null, false, ex.getMessage());  
   	}
   }  
    @RequestMapping("/delete")  
    public @ResponseBody AjaxCallResult delete(HttpServletRequest request,Model model){
        try{
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
            	int id = Integer.parseInt(eid);  
            	UserExamQuestion entity = service.selectById(id);
            	entity.setIsDeleted(true);
            	service.updateById(entity);
            }
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
	}
    
    @RequestMapping("/get")  
    public @ResponseBody AjaxCallResult get(HttpServletRequest request,Model model){
        try{
            int id = Integer.parseInt(request.getParameter("id"));  
            UserExamQuestion entity = service.selectById(id);
            entity.setUserexam(userExamService.selectById(entity.getUserexamId()));
            entity.setQuestion(questionService.selectById(entity.getQuestionId()));
            return new AjaxCallResult(entity);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
    
    @RequestMapping("/search")  
    public @ResponseBody AjaxCallResult search(HttpServletRequest request,Model model){
        try{
        	int pageNo = 1;
        	int pageSize = 10;
        	String sPageNo = request.getParameter("pageNo");
        	String sPageSize = request.getParameter("pageSize");
        	if(sPageNo!=null && sPageNo.trim().length()>0) {
        		pageNo = Integer.valueOf(sPageNo);
        	}
        	if(sPageSize!=null && sPageSize.trim().length()>0) {
        		pageSize = Integer.valueOf(sPageSize);
        	}
        	
        	Wrapper<UserExamQuestion> criteria = new EntityWrapper<UserExamQuestion>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "userexamId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("userexam_id", q);
        	}
        	qk = "questionId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("question_id", q);
        	}
        	qk = "score";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("score", q);
        	}
        	qk = "scoreFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("score", q);
        	}
        	qk = "scoreTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("score", q);
        	}
        	qk = "correctAnswer";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("correct_answer", q);
        	}
        	qk = "answer";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("answer", q);
        	}
        	qk = "isRight";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("is_right", q);
        	}
        	qk = "isRightFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("is_right", q);
        	}
        	qk = "isRightTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("is_right", q);
        	}
        	qk = "isMark";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("is_mark", q);
        	}
        	qk = "isMarkFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("is_mark", q);
        	}
        	qk = "isMarkTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("is_mark", q);
        	}
        	qk = "totalQty";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("total_qty", q);
        	}
        	qk = "totalQtyFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("total_qty", q);
        	}
        	qk = "totalQtyTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("total_qty", q);
        	}
        	qk = "answerQty";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("answer_qty", q);
        	}
        	qk = "answerQtyFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("answer_qty", q);
        	}
        	qk = "answerQtyTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("answer_qty", q);
        	}
        	qk = "explainText";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("explain_text", q);
        	}
        	
        	qk = "myErrorOnly";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		Boolean myErrorOnlyBoolean = Boolean.valueOf(q);
        		if(myErrorOnlyBoolean) {
        			List<UserExam> exams=userExamService.selectList(new EntityWrapper<UserExam>().eq("is_deleted", false).eq("student_id", getCurrentUser(request).getId()));
        			List<Integer> examIds = new ArrayList<Integer>();
        			for(UserExam ue:exams){
        				examIds.add(ue.getId());
        			}
        			if(examIds.size()==0){
        				examIds.add(-1);
        			}
        			criteria=criteria.in("userexam_id", examIds);
        		}
        		criteria=criteria.eq("is_right", false);
        	}
        	
        	Page<UserExamQuestion> list = service.selectPage(new Page<UserExamQuestion>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	UserExamQuestion e = list.getRecords().get(i);
                e.setUserexam(userExamService.selectById(e.getUserexamId()));
                e.setQuestion(questionService.selectById(e.getQuestionId()));
                Exam exam = examService.selectById(e.getUserexam().getExamId());
                /*
                if("学生".equals(getCurrentUser(request).getRole())) {
                    if ("练习".equals(exam.getType()) || "是".equals(exam.getPublicAnswer())) {
    				}else{
    					e.setCorrectAnswer("");
    					e.setExplainText("");
    					e.setIsRight(null);
    					e.setScore(null);
    				}
                }
                */
            }
            
            

            
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }
    
    @RequestMapping("/suggest")  
    public @ResponseBody AjaxCallResult suggest(HttpServletRequest request,Model model){
        try{
        	Wrapper<UserExamQuestion> criteria = new EntityWrapper<UserExamQuestion>().eq("is_deleted", false);
            String qk = null;
            String q = null;
        	qk = "keyword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	
        	List<UserExamQuestion> list = service.selectList(criteria);
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
    @RequestMapping("/mark")  
    public @ResponseBody AjaxCallResult mark(HttpServletRequest request,Model model){
        try{
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
            	int id = Integer.parseInt(eid);  
            	UserExamQuestion entity = service.selectById(id);
            	entity.setIsMark(true);
            	service.updateById(entity);
            }
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
	}
    @RequestMapping("/unmark")  
    public @ResponseBody AjaxCallResult unmark(HttpServletRequest request,Model model){
        try{
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
            	int id = Integer.parseInt(eid);  
            	UserExamQuestion entity = service.selectById(id);
            	entity.setIsMark(false);
            	service.updateById(entity);
            }
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
	}
    
    @RequestMapping("/errors")  
    public String errors(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"Errors";  
    }  
    
}