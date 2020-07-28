package prj.controller;
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
 *   考试结果前端控制器
 * </p>
 */
@Controller
@RequestMapping("/userExam")
public class UserExamController extends ControllerBase {
	private static final String ENTITY_NAME = "userExam";
    @Resource  
    private IUserExamService service;  
    @Resource  
    private IUserService userService;  
    @Resource  
    private IExamService examService;  
    
    @RequestMapping("/list")  
    public String list(HttpServletRequest request,Model model){  
    	if("学生".equals(getCurrentUser(request).getRole())) {
            return ENTITY_NAME +"List";  
    	}else{
            return ENTITY_NAME +"List2";  
    	}

    }  
      
    @RequestMapping("/show")  
    public String show(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"Show";  
    }  
    @RequestMapping("/review")  
    public String review(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"Review";  
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
            UserExam entity = null;
            //获取前端传过来的参数id
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {  //判断id是否为空
            	//不为空就进行修改操作
        		int id = Integer.parseInt(eid);  //将id转化成int类型
        		entity = service.selectById(id);  //根据id查出Exam对象
                if(request.getParameter("studentId") != null && request.getParameter("studentId").trim().length() > 0){
                    entity.setStudentId(Integer.valueOf(request.getParameter("studentId")));
                }else{
                    entity.setStudentId(null);
                }
                if(request.getParameter("examId") != null && request.getParameter("examId").trim().length() > 0){
                    entity.setExamId(Integer.valueOf(request.getParameter("examId")));
                }else{
                    entity.setExamId(null);
                }
                if(request.getParameter("time") != null && request.getParameter("time").trim().length() > 0){
                    entity.setTime(Converter.StringToDate(request.getParameter("time")));
                }else{
                    entity.setTime(null);
                }
                if(request.getParameter("rightQty") != null && request.getParameter("rightQty").trim().length() > 0){
                    entity.setRightQty(Integer.valueOf(request.getParameter("rightQty")));
                }else{
                    entity.setRightQty(null);
                }
                if(request.getParameter("errorQty") != null && request.getParameter("errorQty").trim().length() > 0){
                    entity.setErrorQty(Integer.valueOf(request.getParameter("errorQty")));
                }else{
                    entity.setErrorQty(null);
                }
                if(request.getParameter("score") != null && request.getParameter("score").trim().length() > 0){
                    entity.setScore(Integer.valueOf(request.getParameter("score")));
                }else{
                    entity.setScore(null);
                }
                entity.setComment(request.getParameter("comment"));
                if(request.getParameter("teacherId") != null && request.getParameter("teacherId").trim().length() > 0){
                    entity.setTeacherId(Integer.valueOf(request.getParameter("teacherId")));
                }else{
                    entity.setTeacherId(null);
                }
                if(request.getParameter("regTime") != null && request.getParameter("regTime").trim().length() > 0){
                    entity.setRegTime(Converter.StringToDate(request.getParameter("regTime")));
                }else{
                    entity.setRegTime(null);
                }
        		
                service.updateById(entity);
        	}else{
        		entity = new UserExam();
                if(request.getParameter("studentId") != null && request.getParameter("studentId").trim().length() > 0){
                    entity.setStudentId(Integer.valueOf(request.getParameter("studentId")));
                }else{
                    entity.setStudentId(null);
                }
                if(request.getParameter("examId") != null && request.getParameter("examId").trim().length() > 0){
                    entity.setExamId(Integer.valueOf(request.getParameter("examId")));
                }else{
                    entity.setExamId(null);
                }
                if(request.getParameter("time") != null && request.getParameter("time").trim().length() > 0){
                    entity.setTime(Converter.StringToDate(request.getParameter("time")));
                }else{
                    entity.setTime(null);
                }
                if(request.getParameter("rightQty") != null && request.getParameter("rightQty").trim().length() > 0){
                    entity.setRightQty(Integer.valueOf(request.getParameter("rightQty")));
                }else{
                    entity.setRightQty(null);
                }
                if(request.getParameter("errorQty") != null && request.getParameter("errorQty").trim().length() > 0){
                    entity.setErrorQty(Integer.valueOf(request.getParameter("errorQty")));
                }else{
                    entity.setErrorQty(null);
                }
                if(request.getParameter("score") != null && request.getParameter("score").trim().length() > 0){
                    entity.setScore(Integer.valueOf(request.getParameter("score")));
                }else{
                    entity.setScore(null);
                }
                entity.setComment(request.getParameter("comment"));
                if(request.getParameter("teacherId") != null && request.getParameter("teacherId").trim().length() > 0){
                    entity.setTeacherId(Integer.valueOf(request.getParameter("teacherId")));
                }else{
                    entity.setTeacherId(null);
                }
                if(request.getParameter("regTime") != null && request.getParameter("regTime").trim().length() > 0){
                    entity.setRegTime(Converter.StringToDate(request.getParameter("regTime")));
                }else{
                    entity.setRegTime(null);
                }
    		
            // 添加默认数据。
    		    entity.setStatus("正常");
    		    entity.setIsDeleted(false);
    		    service.insert(entity);
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
            	UserExam entity = service.selectById(id);
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
            UserExam entity = service.selectById(id);
            entity.setStudent(userService.selectById(entity.getStudentId()));
            entity.setExam(examService.selectById(entity.getExamId()));
            entity.setTeacher(userService.selectById(entity.getTeacherId()));
//            if("学生".equals(getCurrentUser(request).getRole())) {
//                if ("练习".equals(entity.getExam().getType()) || "已公布成绩".equals(entity.getExam().getStatus())) {
//				}else{
//					entity.setScore(null);
//					entity.setRightQty(null);
//					entity.setErrorQty(null);
//				}
//            }
            
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
        	
        	Wrapper<UserExam> criteria = new EntityWrapper<UserExam>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "studentId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("student_id", q);
        	}
        	qk = "examId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("exam_id", q);
        	}
        	qk = "time";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("time", q);
        	}
        	qk = "timeFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("time", q);
        	}
        	qk = "timeTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("time", q);
        	}
        	qk = "rightQty";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("right_qty", q);
        	}
        	qk = "rightQtyFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("right_qty", q);
        	}
        	qk = "rightQtyTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("right_qty", q);
        	}
        	qk = "errorQty";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("error_qty", q);
        	}
        	qk = "errorQtyFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("error_qty", q);
        	}
        	qk = "errorQtyTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("error_qty", q);
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
        	qk = "comment";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("comment", q);
        	}
        	qk = "teacherId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("teacher_id", q);
        	}
        	qk = "regTime";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("reg_time", q);
        	}
        	qk = "regTimeFrom";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.ge("reg_time", q);
        	}
        	qk = "regTimeTo";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.le("reg_time", q);
        	}
        	qk = "status";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("status", q);
        	}        	
        	if("学生".equals(getCurrentUser(request).getRole())) {
        		criteria = criteria.eq("student_id", getCurrentUser(request).getId());
        	}
        	criteria = criteria.orderBy("id desc");
        	Page<UserExam> list = service.selectPage(new Page<UserExam>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	UserExam e = list.getRecords().get(i);
                e.setStudent(userService.selectById(e.getStudentId()));
                e.setExam(examService.selectById(e.getExamId()));
                e.setTeacher(userService.selectById(e.getTeacherId()));
                
                // 最本科目最高分
                List<UserExam> exams = service.selectList(new EntityWrapper<UserExam>().eq("is_deleted", false).eq("exam_id", e.getExamId()).orderBy("score desc"));
                e.setSubjectMaxScore(exams.get(0).getScore());
                // 最本科目最低分
                e.setSubjectMinScore(exams.get(exams.size()-1).getScore());
                
//                if("学生".equals(getCurrentUser(request).getRole())) {
//                    if ("练习".equals(e.getExam().getType()) || "已公布成绩".equals(e.getExam().getStatus())) {
//    				}else{
//    					e.setScore(null);
//    					e.setRightQty(null);
//    					e.setErrorQty(null);
//    				}
//                }
            }
            
            
            
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }
    
    @RequestMapping("/suggest")  
    public @ResponseBody AjaxCallResult suggest(HttpServletRequest request,Model model){
        try{
        	Wrapper<UserExam> criteria = new EntityWrapper<UserExam>().eq("is_deleted", false);
            String qk = null;
            String q = null;
        	qk = "keyword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	
        	List<UserExam> list = service.selectList(criteria);
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
    
    @RequestMapping("/comment")  
    public String comment(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
    	UserExam ue = service.selectById(eid);
    	Exam exam = examService.selectById(ue.getExamId());
//    	if("练习".equals(exam.getType())) {
    		return "redirect:/userExam/show?id="+eid;  
//    	}
//		return "redirect:/exam/join";  
    	
    	
       // return ENTITY_NAME +"Comment";  
    }
    //saveComment
    @RequestMapping("/saveComment")  
    public @ResponseBody AjaxCallResult saveComment(HttpServletRequest request,Model model){
        try{
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
            	int id = Integer.parseInt(eid);  
            	UserExam entity = service.selectById(id);
            	entity.setComment(request.getParameter("comment"));
            	service.updateById(entity);
            }
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
	}
    
    //mine
    @RequestMapping("/mine")  
    public String mine(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"Mine";  
    }  
    

    
    
}