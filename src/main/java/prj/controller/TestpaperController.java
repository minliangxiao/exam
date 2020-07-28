package prj.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

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
 *   试卷前端控制器
 * </p>
 */
@Controller
@RequestMapping("/testpaper")
public class TestpaperController extends ControllerBase {
	private static final String ENTITY_NAME = "testpaper";
    @Resource  
    private ITestpaperService service;  
    @Resource  
    private ISubjectService subjectService;  
    @Resource
    private IQuestionInTestpaperService questionInTestpaperService;
    @Resource
    private IUserService userService;
    @Resource
    private IQuestionService questionService;
    
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
    @RequestMapping("/edit2")  
    public String edit2(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"Edit2";  
    }  
      
    @RequestMapping("/save")  
    public @ResponseBody AjaxCallResult save(HttpServletRequest request,Model model){  
        try{
            Testpaper entity = null;
            String eid = request.getParameter("id");

            
            if(eid != null && eid.trim().length()>0) {
        		int id = Integer.parseInt(eid);  
        		entity = service.selectById(id);
                entity.setName(request.getParameter("name"));
                entity.setCode(request.getParameter("code"));
                if(request.getParameter("minutes") != null && request.getParameter("minutes").trim().length() > 0){
                    entity.setMinutes(Integer.valueOf(request.getParameter("minutes")));
                }else{
                    entity.setMinutes(null);
                }
                if(request.getParameter("totalScore") != null && request.getParameter("totalScore").trim().length() > 0){
                    entity.setTotalScore(Integer.valueOf(request.getParameter("totalScore")));
                }else{
                    entity.setTotalScore(100);
                }
                if(request.getParameter("subjectId") != null && request.getParameter("subjectId").trim().length() > 0){
                    entity.setSubjectId(Integer.valueOf(request.getParameter("subjectId")));
                }else{
                    entity.setSubjectId(null);
                }
                if(request.getParameter("gradeId") != null && request.getParameter("gradeId").trim().length() > 0){
                    entity.setGradeId(Integer.valueOf(request.getParameter("gradeId")));
                }else{
                    entity.setGradeId(null);
                }
                service.updateById(entity);
        	}else{
        		entity = new Testpaper();
                entity.setName(request.getParameter("name"));
                entity.setCode(request.getParameter("code"));
                if(request.getParameter("minutes") != null && request.getParameter("minutes").trim().length() > 0){
                    entity.setMinutes(Integer.valueOf(request.getParameter("minutes")));
                }else{
                    entity.setMinutes(null);
                }
                if(request.getParameter("totalScore") != null && request.getParameter("totalScore").trim().length() > 0){
                    entity.setTotalScore(Integer.valueOf(request.getParameter("totalScore")));
                }else{
                    entity.setTotalScore(100);
                }
                if(request.getParameter("subjectId") != null && request.getParameter("subjectId").trim().length() > 0){
                    entity.setSubjectId(Integer.valueOf(request.getParameter("subjectId")));
                }else{
                    entity.setSubjectId(null);
                }
                if(request.getParameter("gradeId") != null && request.getParameter("gradeId").trim().length() > 0){
                    entity.setGradeId(Integer.valueOf(request.getParameter("gradeId")));
                }else{
                    entity.setGradeId(null);
                }
            // 添加默认数据。
    		    entity.setIsDeleted(false);
//    		    String code = service.findMaxCode(getCurrentUser(request).getSchoolId());
//    		    if(code==null || code.length()==0) {
//    		    	code = "0001";
//    		    }else{
//    		    	Integer c = Integer.valueOf(code);
//    		    	c++;
//    		    	code = c.toString();
//    		    	while(code.length()<4){
//    		    		code = "0"+code;
//    		    	}
//    		    }
//    		    
//                //entity.setCode(request.getParameter("code"));
//    		    entity.setCode(code);
    		    //entity.setTotalScore(0);
    		    service.insert(entity);
        	}
            
            String addedIds = request.getParameter("added");
            String deletedIds=request.getParameter("deleted");
            String scores=request.getParameter("scores");
            String[] added = addedIds.split(",");
            String[] deleted = deletedIds.split(",");
            String[] scoreArr = scores.split(",");
            for (int i =0; i < added.length; i++) {
            	String qid = added[i];
            	if(qid.trim().length()==0) continue;
				QuestionInTestpaper item = new QuestionInTestpaper();
				item.setIsDeleted(false);
				item.setQuestionId(Integer.valueOf(qid));
				item.setScore(Integer.valueOf(scoreArr[i]));
            	item.setTestpaperId(entity.getId());
            	questionInTestpaperService.insert(item);
			}
            for(int i =0; i<deleted.length;i++){
            	if(deleted[i]!=null && deleted[i].length()>0) {
                	questionInTestpaperService.deleteById(Integer.valueOf(deleted[i]));
            	}
            }
            
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
   @RequestMapping("/save2")  
    public @ResponseBody AjaxCallResult save2(HttpServletRequest request,Model model){  
        try{
            Testpaper entity = null;
           
    		entity = new Testpaper();
            entity.setName(request.getParameter("name"));
            entity.setCode(request.getParameter("code"));
            if(request.getParameter("minutes") != null && request.getParameter("minutes").trim().length() > 0){
                entity.setMinutes(Integer.valueOf(request.getParameter("minutes")));
            }else{
                entity.setMinutes(null);
            }
            if(request.getParameter("totalScore") != null && request.getParameter("totalScore").trim().length() > 0){
                entity.setTotalScore(Integer.valueOf(request.getParameter("totalScore")));
            }else{
                entity.setTotalScore(null);
            }
            if(request.getParameter("subjectId") != null && request.getParameter("subjectId").trim().length() > 0 && !request.getParameter("subjectId").trim().equals("0")){
                entity.setSubjectId(Integer.valueOf(request.getParameter("subjectId")));
            }else{
                entity.setSubjectId(null);
            }
            if(request.getParameter("gradeId") != null && request.getParameter("gradeId").trim().length() > 0 && !request.getParameter("gradeId").trim().equals("0")){
                entity.setGradeId(Integer.valueOf(request.getParameter("gradeId")));
            }else{
                entity.setGradeId(null);
            }
        // 添加默认数据。
		    entity.setIsDeleted(true);
		    service.insert(entity);
        
		    int qty1 =0,qty2=0,qty3=0,qty4=0,qty5=0;
		    int score1 =0,score2=0,score3=0,score4=0,score5=0;
            if(request.getParameter("qty1") != null && request.getParameter("qty1").trim().length() > 0){
            	qty1 = Integer.valueOf(request.getParameter("qty1"));
            }		    
            if(request.getParameter("qty2") != null && request.getParameter("qty2").trim().length() > 0){
            	qty2 = Integer.valueOf(request.getParameter("qty2"));
            }		    
            if(request.getParameter("qty3") != null && request.getParameter("qty3").trim().length() > 0){
            	qty3 = Integer.valueOf(request.getParameter("qty3"));
            }		    
            if(request.getParameter("qty4") != null && request.getParameter("qty4").trim().length() > 0){
            	qty4 = Integer.valueOf(request.getParameter("qty4"));
            }		    
            if(request.getParameter("qty5") != null && request.getParameter("qty5").trim().length() > 0){
            	qty5 = Integer.valueOf(request.getParameter("qty5"));
            }		    
            if(request.getParameter("score1") != null && request.getParameter("score1").trim().length() > 0){
            	score1 = Integer.valueOf(request.getParameter("score1"));
            }		    
            if(request.getParameter("score2") != null && request.getParameter("score2").trim().length() > 0){
            	score2 = Integer.valueOf(request.getParameter("score2"));
            }		    
            if(request.getParameter("score3") != null && request.getParameter("score3").trim().length() > 0){
            	score3 = Integer.valueOf(request.getParameter("score3"));
            }		    
            if(request.getParameter("score4") != null && request.getParameter("score4").trim().length() > 0){
            	score4 = Integer.valueOf(request.getParameter("score4"));
            }		    
            if(request.getParameter("score5") != null && request.getParameter("score5").trim().length() > 0){
            	score5 = Integer.valueOf(request.getParameter("score5"));
            }				    
		    List<Integer> qidsIntegers=new ArrayList<Integer>();
		    if(qty1>0) {
		    	List<Integer> qids=getQuestions("选择题", qty1,entity.getSubjectId(),entity.getGradeId());
	            for (int i =0; i < qids.size(); i++) {
	            	int qid = qids.get(i);
					QuestionInTestpaper item = new QuestionInTestpaper();
					item.setIsDeleted(false);
					item.setQuestionId(Integer.valueOf(qid));
					item.setScore(Integer.valueOf(score1));
	            	item.setTestpaperId(entity.getId());
	            	questionInTestpaperService.insert(item);
				}
		    }
		    if(qty2>0) {
		    	List<Integer> qids=getQuestions("填空题", qty2,entity.getSubjectId(),entity.getGradeId());
	            for (int i =0; i < qids.size(); i++) {
	            	int qid = qids.get(i);
					QuestionInTestpaper item = new QuestionInTestpaper();
					item.setIsDeleted(false);
					item.setQuestionId(Integer.valueOf(qid));
					item.setScore(Integer.valueOf(score2));
	            	item.setTestpaperId(entity.getId());
	            	questionInTestpaperService.insert(item);
				}
		    }
		    if(qty3>0) {
		    	List<Integer> qids=getQuestions("判断题", qty3,entity.getSubjectId(),entity.getGradeId());
	            for (int i =0; i < qids.size(); i++) {
	            	int qid = qids.get(i);
					QuestionInTestpaper item = new QuestionInTestpaper();
					item.setIsDeleted(false);
					item.setQuestionId(Integer.valueOf(qid));
					item.setScore(Integer.valueOf(score3));
	            	item.setTestpaperId(entity.getId());
	            	questionInTestpaperService.insert(item);
				}
		    }
		    if(qty4>0) {
		    	List<Integer> qids=getQuestions("简答题", qty4,entity.getSubjectId(),entity.getGradeId());
	            for (int i =0; i < qids.size(); i++) {
	            	int qid = qids.get(i);
					QuestionInTestpaper item = new QuestionInTestpaper();
					item.setIsDeleted(false);
					item.setQuestionId(Integer.valueOf(qid));
					item.setScore(Integer.valueOf(score4));
	            	item.setTestpaperId(entity.getId());
	            	questionInTestpaperService.insert(item);
				}
		    }
		    if(qty5>0) {
		    	List<Integer> qids=getQuestions("编程题", qty5,entity.getSubjectId(),entity.getGradeId());
	            for (int i =0; i < qids.size(); i++) {
	            	int qid = qids.get(i);
					QuestionInTestpaper item = new QuestionInTestpaper();
					item.setIsDeleted(false);
					item.setQuestionId(Integer.valueOf(qid));
					item.setScore(Integer.valueOf(score5));
	            	item.setTestpaperId(entity.getId());
	            	questionInTestpaperService.insert(item);
				}
		    }
		    entity.setTotalScore(100);
		    entity.setIsDeleted(false);
		    
		    service.updateById(entity);

            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }      
    private List<Integer> getQuestions(String type,int qty,Integer subjectId, Integer gradeId) throws Exception {
    	List<Integer> qidsIntegers=new ArrayList<Integer>();
    	Wrapper<Question> criteria = new EntityWrapper<Question>().eq("is_deleted", false).eq("type", type);
    	if(subjectId!=null && subjectId>0) {
    		criteria = criteria.eq("subject_id", subjectId);
    	}else{
    		if(gradeId!=null && gradeId>0) {
    			List<Subject> subjects = subjectService.selectList(new EntityWrapper<Subject>().eq("is_deleted", false).eq("grade_id", gradeId));
    			List<Integer> subjectIds=new ArrayList<Integer>();
    			for(Subject subject:subjects) {
    				subjectIds.add(subject.getId());
    			}
    			if(subjectIds.size()==0) subjectIds.add(-1);
    			criteria = criteria.in("subject_id", subjectIds);
    		}
    	}
    	
    	List<Question> questions = questionService.selectList(criteria);
    	if(questions.size() < qty) {
        	throw new Exception(type + "试题的数量不足，最多" + questions.size() + "道题！");
    	}
    	if(questions.size()==qty) {
    		for(Question question : questions) {
    			qidsIntegers.add(question.getId());
    		}
    		return qidsIntegers;
    	}
    
    	Random rand = new Random();
    	while (qidsIntegers.size()<qty) {
			int idx = rand.nextInt(questions.size());
			if(!qidsIntegers.contains(questions.get(idx).getId())) qidsIntegers.add(questions.get(idx).getId());
		}
    	return qidsIntegers;
    }
    
    @RequestMapping("/delete")  
    public @ResponseBody AjaxCallResult delete(HttpServletRequest request,Model model){
        try{
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
            	int id = Integer.parseInt(eid);  
            	Testpaper entity = service.selectById(id);
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
            Testpaper entity = service.selectById(id);
            entity.setSubject(subjectService.selectById(entity.getSubjectId()));
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
        	
        	Wrapper<Testpaper> criteria = new EntityWrapper<Testpaper>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "name";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "code";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("code", q);
        	}
        	qk = "minutes";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("minutes", q);
        	}
        	qk = "minutesFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("minutes", q);
        	}
        	qk = "minutesTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("minutes", q);
        	}
        	qk = "totalScore";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("total_score", q);
        	}
        	qk = "totalScoreFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("total_score", q);
        	}
        	qk = "totalScoreTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("total_score", q);
        	}
        	qk = "subjectId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("subject_id", q);
        	}
        	qk = "gradeId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("grade_id", q);
        	}
        	//criteria.in("subjectId", value);
        	
        	Page<Testpaper> list = service.selectPage(new Page<Testpaper>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	Testpaper e = list.getRecords().get(i);
                e.setSubject(subjectService.selectById(e.getSubjectId()));
            }

            
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }
    
    @RequestMapping("/suggest")  
    public @ResponseBody AjaxCallResult suggest(HttpServletRequest request,Model model){
        try{
        	Wrapper<Testpaper> criteria = new EntityWrapper<Testpaper>().eq("is_deleted", false);
            String qk = null;
            String q = null;
        	qk = "keyword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "subjectId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("subject_id", q);
        	}
        	qk = "gradeId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("grade_id", q);
        	}
        	List<Testpaper> list = service.selectList(criteria);
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
}