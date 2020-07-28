package prj.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.math.BigDecimal;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import prj.AjaxCallResult;
import prj.ControllerBase;
import prj.CurrentUser;
import prj.entity.*;
import prj.service.*;
import prj.util.*;

/**
 * <p>
 *   考试前端控制器
 * </p>
 */
@Controller
@RequestMapping("/exam")
public class ExamController extends ControllerBase {
	private static final String ENTITY_NAME = "exam";
    @Autowired
    private IExamService service;  
    @Resource  
    private ITestpaperService testpaperService;  
    @Resource
    private IUserExamService ueService;
    @Resource
    private IQuestionInTestpaperService qtService;
    @Resource
    private IQuestionService qService;
    @Resource
    private IUserExamQuestionService ueqService;
    @Resource
    private ISubjectService subjectService;
    @Resource
    private IUserSubjectService userSubjectService;
    @Resource
    private IUserExamService userExamService;
    
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
            Exam entity = null;
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {  //判断id是否为空
            	//不为空进行修改操作
        		int id = Integer.parseInt(eid);   //将id转化成int类型
        		entity = service.selectById(id); //根据id查出exam对象
        		//为EXAM设置相关参数
                entity.setName(request.getParameter("name")); 
                if(request.getParameter("testpaperId") != null && request.getParameter("testpaperId").trim().length() > 0){
                    entity.setTestpaperId(Integer.valueOf(request.getParameter("testpaperId")));
                }else{
                    entity.setTestpaperId(null);
                }
                if(request.getParameter("startTime") != null && request.getParameter("startTime").trim().length() > 0){
                    entity.setStartTime(Converter.StringToDate(request.getParameter("startTime")));
                }else{
                    entity.setStartTime(null);
                }
                if(request.getParameter("totalStudentQty") != null && request.getParameter("totalStudentQty").trim().length() > 0){
                    entity.setTotalStudentQty(Integer.valueOf(request.getParameter("totalStudentQty")));
                }else{
                    entity.setTotalStudentQty(null);
                }
                if(request.getParameter("examStudentQty") != null && request.getParameter("examStudentQty").trim().length() > 0){
                    entity.setExamStudentQty(Integer.valueOf(request.getParameter("examStudentQty")));
                }else{
                    entity.setExamStudentQty(null);
                }
                if(request.getParameter("endTime") != null && request.getParameter("endTime").trim().length() > 0){
                    entity.setEndTime(Converter.StringToDate(request.getParameter("endTime")));
                }else{
                    entity.setEndTime(null);
                }
                if(request.getParameter("finishStudentQty") != null && request.getParameter("finishStudentQty").trim().length() > 0){
                    entity.setFinishStudentQty(Integer.valueOf(request.getParameter("finishStudentQty")));
                }else{
                    entity.setFinishStudentQty(null);
                }
                if(request.getParameter("totalScore") != null && request.getParameter("totalScore").trim().length() > 0){
                    entity.setTotalScore(Integer.valueOf(request.getParameter("totalScore")));
                }else{
                    entity.setTotalScore(null);
                }
                if(request.getParameter("avgScore") != null && request.getParameter("avgScore").trim().length() > 0){
                    entity.setAvgScore(BigDecimal.valueOf(Double.valueOf(request.getParameter("avgScore"))));
                }else{
                    entity.setAvgScore(null);
                }
                if(request.getParameter("subjectId") != null && request.getParameter("subjectId").trim().length() > 0){
                    entity.setSubjectId(Integer.valueOf(request.getParameter("subjectId")));
                }else{
                    entity.setSubjectId(null);
                }                
                entity.setType(request.getParameter("type"));
                entity.setPublicAnswer(request.getParameter("publicAnswer"));
        		
                Testpaper tp=testpaperService.selectById(entity.getTestpaperId());
                entity.setTotalScore(tp.getTotalScore());
                
                service.updateById(entity);
        	}else{
        		//如果id为空 新增一个exam
        		entity = new Exam();
        		//设置相关参数
                entity.setName(request.getParameter("name"));
                if(request.getParameter("testpaperId") != null && request.getParameter("testpaperId").trim().length() > 0){
                    entity.setTestpaperId(Integer.valueOf(request.getParameter("testpaperId")));
                }else{
                    entity.setTestpaperId(null);
                }
                if(request.getParameter("startTime") != null && request.getParameter("startTime").trim().length() > 0){
                    entity.setStartTime(Converter.StringToDate(request.getParameter("startTime")));
                }else{
                    entity.setStartTime(null);
                }
                if(request.getParameter("totalStudentQty") != null && request.getParameter("totalStudentQty").trim().length() > 0){
                    entity.setTotalStudentQty(Integer.valueOf(request.getParameter("totalStudentQty")));
                }else{
                    entity.setTotalStudentQty(null);
                }
                if(request.getParameter("examStudentQty") != null && request.getParameter("examStudentQty").trim().length() > 0){
                    entity.setExamStudentQty(Integer.valueOf(request.getParameter("examStudentQty")));
                }else{
                    entity.setExamStudentQty(null);
                }
                if(request.getParameter("endTime") != null && request.getParameter("endTime").trim().length() > 0){
                    entity.setEndTime(Converter.StringToDate(request.getParameter("endTime")));
                }else{
                    entity.setEndTime(null);
                }
                if(request.getParameter("finishStudentQty") != null && request.getParameter("finishStudentQty").trim().length() > 0){
                    entity.setFinishStudentQty(Integer.valueOf(request.getParameter("finishStudentQty")));
                }else{
                    entity.setFinishStudentQty(null);
                }
                if(request.getParameter("totalScore") != null && request.getParameter("totalScore").trim().length() > 0){
                    entity.setTotalScore(Integer.valueOf(request.getParameter("totalScore")));
                }else{
                    entity.setTotalScore(null);
                }
                if(request.getParameter("avgScore") != null && request.getParameter("avgScore").trim().length() > 0){
                    entity.setAvgScore(BigDecimal.valueOf(Double.valueOf(request.getParameter("avgScore"))));
                }else{
                    entity.setAvgScore(null);
                }
                if(request.getParameter("subjectId") != null && request.getParameter("subjectId").trim().length() > 0){
                    entity.setSubjectId(Integer.valueOf(request.getParameter("subjectId")));
                }else{
                    entity.setSubjectId(null);
                }                
                entity.setType(request.getParameter("type"));
                entity.setPublicAnswer(request.getParameter("publicAnswer"));
    		
            // 添加默认数据。
    		    entity.setStatus("正常");
    		    entity.setIsDeleted(false);
    		    entity.setTotalStudentQty(0);
    		    entity.setStartTime(new Date());
    		    entity.setEndTime(new Date());
    		    entity.setExamStudentQty(0);
    		    entity.setFinishStudentQty(0);
    		    entity.setStatus("未发布");
    		    
                Testpaper tp=testpaperService.selectById(entity.getTestpaperId());
                entity.setTotalScore(tp.getTotalScore());
    		    //插入数据库
    		    service.insert(entity);
        	}
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
    
    @RequestMapping("/publish")  
    public @ResponseBody AjaxCallResult publish(HttpServletRequest request,Model model){
        try{
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
            	int id = Integer.parseInt(eid);  
            	Exam entity = service.selectById(id);
            	entity.setStatus("已发布");
            	service.updateById(entity);
            }
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
	}    
    
    @RequestMapping("/publishScore")  
    public @ResponseBody AjaxCallResult publishScore(HttpServletRequest request,Model model){
        try{
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
            	int id = Integer.parseInt(eid);  
            	Exam entity = service.selectById(id);
            	entity.setStatus("已公布成绩");
            	entity.setPublicAnswer(request.getParameter("publishAnswer"));
            	service.updateById(entity);
            }
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
            	Exam entity = service.selectById(id);
            	entity.setIsDeleted(true);
            	service.updateById(entity);
            }
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
	}
    @RequestMapping("/deleteItems")  
    public @ResponseBody AjaxCallResult deleteItems(HttpServletRequest request,Model model){
        try{
            String[] ids = request.getParameterValues("ids[]");
            if(ids.length>0) {
            	for(String eid:ids){
                    if(eid != null && eid.trim().length()>0) {
                    	int id = Integer.parseInt(eid);  
                    	Exam entity = service.selectById(id);
                    	entity.setIsDeleted(true);
                    	service.updateById(entity);
                    }
            	}
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
            Exam entity = service.selectById(id);
            entity.setTestpaper(testpaperService.selectById(entity.getTestpaperId()));
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
        	
        	Wrapper<Exam> criteria = new EntityWrapper<Exam>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "name";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "testpaperId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("testpaper_id", q);
        	}
        	qk = "startTime";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("start_time", q);
        	}
        	qk = "startTimeFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("start_time", q);
        	}
        	qk = "startTimeTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("start_time", q);
        	}
        	qk = "totalStudentQty";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("total_student_qty", q);
        	}
        	qk = "totalStudentQtyFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("total_student_qty", q);
        	}
        	qk = "totalStudentQtyTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("total_student_qty", q);
        	}
        	qk = "examStudentQty";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("exam_student_qty", q);
        	}
        	qk = "examStudentQtyFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("exam_student_qty", q);
        	}
        	qk = "examStudentQtyTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("exam_student_qty", q);
        	}
        	qk = "endTime";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("end_time", q);
        	}
        	qk = "endTimeFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("end_time", q);
        	}
        	qk = "endTimeTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("end_time", q);
        	}
        	qk = "finishStudentQty";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("finish_student_qty", q);
        	}
        	qk = "finishStudentQtyFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("finish_student_qty", q);
        	}
        	qk = "finishStudentQtyTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("finish_student_qty", q);
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
        	qk = "avgScore";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("avg_score", q);
        	}
        	qk = "avgScoreFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("avg_score", q);
        	}
        	qk = "avgScoreTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("avg_score", q);
        	}
        	qk = "type";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("type", q);
        	}
        	qk = "publicAnswer";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("public_answer", q);
        	}
        	qk = "unexamOnly";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		if(Boolean.valueOf(q)){
        			List<UserExam> exams = ueService.selectList(new EntityWrapper<UserExam>().eq("is_deleted", false).eq("student_id", getCurrentUser(request).getId()));
        			List<Integer> examIds = new ArrayList<Integer>();
        			if(exams.size() > 0) {
        				for (UserExam ue : exams) {
							examIds.add(ue.getExamId());
						}
            			criteria.notIn("id", examIds);
        			}
        		}
        	}
        	qk = "status";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("status", q);
        	}
        	
        	qk = "subjectId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		List<Testpaper> papers = testpaperService.selectList(new EntityWrapper<Testpaper>().eq("is_deleted", false).eq("subject_id", q));
    			List<Integer> examIds = new ArrayList<Integer>();
    			if(papers.size() > 0) {
    				for (Testpaper ue : papers) {
						examIds.add(ue.getId());
					}
        			criteria.in("testpaper_id", examIds);
    			}
    		}
        	
        	Page<Exam> list = service.selectPage(new Page<Exam>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	Exam e = list.getRecords().get(i);
                e.setTestpaper(testpaperService.selectById(e.getTestpaperId()));
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
        	Wrapper<Exam> criteria = new EntityWrapper<Exam>().eq("is_deleted", false);
            String qk = null;
            String q = null;
        	qk = "keyword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	
        	List<Exam> list = service.selectList(criteria);
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
    
    @RequestMapping("/join")  
    public String join(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"Join";  
    }
    //考试
    @RequestMapping("/exam")  
    public String exam(HttpServletRequest request,Model model){  
    	//接受参数中的id
    	int examId =  Integer.parseInt(request.getParameter("id"));  
    	//根据参数中的id查询出具体的Exam对象
        Exam exam = service.selectById(examId);
        //获取session中的user（当前的user）
        CurrentUser user = getCurrentUser(request);
        Testpaper testpaper=testpaperService.selectById(exam.getTestpaperId());
        List<QuestionInTestpaper> qits = qtService.selectList(new EntityWrapper<QuestionInTestpaper>().eq("is_deleted", false).eq("testpaper_id", testpaper.getId()));
        //创建一个UserExam对象
        UserExam  userExam = new UserExam();
        //设置相关参数
        userExam.setStudentId(user.getId());
        userExam.setExamId(examId);
        userExam.setTime(new Date());
        userExam.setRightQty(0);
        userExam.setErrorQty(0);
        userExam.setScore(0);
	    userExam.setStatus("已参加");
	    userExam.setIsDeleted(false);
	    //插入数据库
	    ueService.insert(userExam);
	    
	    for (QuestionInTestpaper qt : qits) {
			Question question=qService.selectById(qt.getQuestionId());
			UserExamQuestion ueq= new UserExamQuestion();
        	ueq.setUserexamId(userExam.getId());
        	ueq.setQuestionId(qt.getQuestionId());
        	ueq.setScore(0);	//qt.getScore()
        	ueq.setQscore(qt.getScore());
            ueq.setCorrectAnswer(question.getCorrect());
            ueq.setAnswer(null);
        	ueq.setIsRight(false);
        	ueq.setIsMark(false);
        	ueq.setTotalQty(1);
        	ueq.setAnswerQty(0);
            ueq.setExplainText(question.getExplainText());
            ueq.setIsDeleted(false);
		    ueqService.insert(ueq);
		}	    

	    List<UserExamQuestion> questions = ueqService.selectList(new EntityWrapper<UserExamQuestion>().eq("is_deleted", false).eq("userexam_id", userExam.getId()).orderBy("id"));
	    int current = questions.get(0).getId();
	    int maxId = questions.get(questions.size()-1).getId();
	
    	model.addAttribute("exam",exam);
    	model.addAttribute("id",examId);
    	model.addAttribute("startId",current);
    	model.addAttribute("maxId",maxId);
    	model.addAttribute("qty",maxId-current+1);
    	model.addAttribute("minutes",testpaper.getMinutes());
    	model.addAttribute("seconds",testpaper.getMinutes() * 60);
    	
        return ENTITY_NAME +"Exam";  
    }      

    /*
     * 需要报名的
    @RequestMapping("/exam")  
    public String exam(HttpServletRequest request,Model model){  
        int userExamId = Integer.parseInt(request.getParameter("id"));  
        UserExam  userExam = ueService.selectById(userExamId);
    	int examId = userExam.getExamId();
        Exam exam = service.selectById(examId);
        CurrentUser user = getCurrentUser(request);
        Testpaper testpaper=testpaperService.selectById(exam.getTestpaperId());
        List<QuestionInTestpaper> qits = qtService.selectList(new EntityWrapper<QuestionInTestpaper>().eq("is_deleted", false).eq("testpaper_id", testpaper.getId()));
        
//        userExam.setStudentId(user.getId());
//        userExam.setExamId(examId);
//        userExam.setTime(new Date());
//        userExam.setRightQty(0);
//        userExam.setErrorQty(0);
//        userExam.setScore(0);
	    userExam.setStatus("已参加");
//	    userExam.setIsDeleted(false);
	    ueService.updateById(userExam);

	    List<UserExamQuestion> questions = ueqService.selectList(new EntityWrapper<UserExamQuestion>().eq("is_deleted", false).eq("userexam_id", userExam.getId()).orderBy("id"));
	    int current = questions.get(0).getId();
	    int maxId = questions.get(questions.size()-1).getId();
	
    	model.addAttribute("exam",exam);
    	model.addAttribute("id",examId);
    	model.addAttribute("startId",current);
    	model.addAttribute("maxId",maxId);
    	model.addAttribute("qty",maxId-current+1);
    	model.addAttribute("minutes",testpaper.getMinutes());
    	model.addAttribute("seconds",testpaper.getMinutes() * 60);
    	
        return ENTITY_NAME +"Exam";  
    }      
*/
    
    
    @RequestMapping("/toRegister")  
    public String toRegister(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"ToRegister";  
    }
    @RequestMapping("/getUnRegister")  
    public @ResponseBody AjaxCallResult getUnRegister(HttpServletRequest request,Model model){
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
        	
        	Wrapper<Exam> criteria = new EntityWrapper<Exam>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "name";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "testpaperId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("testpaper_id", q);
        	}
        	qk = "startTime";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("start_time", q);
        	}
        	qk = "startTimeFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("start_time", q);
        	}

        	// 未报名的
			List<UserExam> exams = ueService.selectList(new EntityWrapper<UserExam>().eq("is_deleted", false).eq("student_id", getCurrentUser(request).getId()));
			List<Integer> examIds = new ArrayList<Integer>();
			if(exams.size() > 0) {
				for (UserExam ue : exams) {
					examIds.add(ue.getExamId());
				}
    			criteria.notIn("id", examIds);
			}

			qk = "status";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("status", q);
        	}
        	
        	// 我有报名的课程的
        	List<UserSubject> userSubjects = userSubjectService.selectList(new EntityWrapper<UserSubject>().eq("is_deleted", false).eq("user_id", getCurrentUser(request).getId()));
			List<Integer> subjectIds = new ArrayList<Integer>();
        	for(UserSubject us : userSubjects) {
        		subjectIds.add(us.getSubjectId());
        	}
        	criteria.in("subject_id", subjectIds);
        	
        	Page<Exam> list = service.selectPage(new Page<Exam>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	Exam e = list.getRecords().get(i);
                e.setTestpaper(testpaperService.selectById(e.getTestpaperId()));
                e.setSubject(subjectService.selectById(e.getSubjectId()));
            }

            
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }
    
    @RequestMapping("/register")  
    public @ResponseBody AjaxCallResult register(HttpServletRequest request,Model model){  
        try{
            UserExam entity = null;
            int examId = Integer.valueOf(request.getParameter("id"));
            int userId=getCurrentUser(request).getId();
            Exam exam = service.selectById(examId);
    		entity = new UserExam();
    		entity.setStudentId(userId);
    		entity.setExamId(examId);
    		entity.setTime(exam.getStartTime());
    		entity.setRightQty(0);
    		entity.setErrorQty(0);
            entity.setScore(0);
            entity.setTeacherId(null);
            entity.setRegTime(new Date());
            // 添加默认数据。
		    entity.setStatus("未参加");
		    entity.setIsDeleted(false);
		    userExamService.insert(entity);
		    
	        Testpaper testpaper=testpaperService.selectById(exam.getTestpaperId());
	        List<QuestionInTestpaper> qits = qtService.selectList(new EntityWrapper<QuestionInTestpaper>().eq("is_deleted", false).eq("testpaper_id", testpaper.getId()));
	        
		    for (QuestionInTestpaper qt : qits) {
				Question question=qService.selectById(qt.getQuestionId());
				UserExamQuestion ueq= new UserExamQuestion();
	        	ueq.setUserexamId(entity.getId());
	        	ueq.setQuestionId(qt.getQuestionId());
	        	ueq.setScore(0);	//qt.getScore()
	        	ueq.setQscore(qt.getScore());
	            ueq.setCorrectAnswer(question.getCorrect());
	            ueq.setAnswer(null);
	        	ueq.setIsRight(false);
	        	ueq.setIsMark(false);
	        	ueq.setTotalQty(1);
	        	ueq.setAnswerQty(0);
	            ueq.setExplainText(question.getExplainText());
	            ueq.setIsDeleted(false);
			    ueqService.insert(ueq);
			}
		    

            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}

    }  
    //toExercise
    @RequestMapping("/toExercise")  
    public String toExercise(HttpServletRequest request,Model model){  
    	
    	return ENTITY_NAME +"ToExercise";   
    }
    
    
}