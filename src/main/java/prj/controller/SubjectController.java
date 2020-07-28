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
 *   课程前端控制器
 * </p>
 */
@Controller
@RequestMapping("/subject")
public class SubjectController extends ControllerBase {
	private static final String ENTITY_NAME = "subject";
    @Resource  
    private ISubjectService service;  
    @Resource  
    private IUserService userService;  
    @Resource  
    private ISchoolService schoolService;  
    
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
            Subject entity = null;
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
        		int id = Integer.parseInt(eid);  
        		entity = service.selectById(id);
                entity.setName(request.getParameter("name"));
                if(request.getParameter("schoolId") != null && request.getParameter("schoolId").trim().length() > 0){
                    entity.setSchoolId(Integer.valueOf(request.getParameter("schoolId")));
                }else{
                    entity.setSchoolId(1);
                }
                entity.setIntro(request.getParameter("intro"));
                if(request.getParameter("teacherId") != null && request.getParameter("teacherId").trim().length() > 0){
                    entity.setTeacherId(Integer.valueOf(request.getParameter("teacherId")));
                }else{
                    entity.setTeacherId(null);
                }
                if(request.getParameter("gradeId") != null && request.getParameter("gradeId").trim().length() > 0){
                    entity.setGradeId(Integer.valueOf(request.getParameter("gradeId")));
                }else{
                    entity.setGradeId(null);
                }
                service.updateById(entity);
        	}else{
        		entity = new Subject();
                entity.setName(request.getParameter("name"));
                if(request.getParameter("schoolId") != null && request.getParameter("schoolId").trim().length() > 0){
                    entity.setSchoolId(Integer.valueOf(request.getParameter("schoolId")));
                }else{
                    entity.setSchoolId(1);
                }
                entity.setIntro(request.getParameter("intro"));
                if(request.getParameter("teacherId") != null && request.getParameter("teacherId").trim().length() > 0){
                    entity.setTeacherId(Integer.valueOf(request.getParameter("teacherId")));
                }else{
                    entity.setTeacherId(null);
                }
                if(request.getParameter("gradeId") != null && request.getParameter("gradeId").trim().length() > 0){
                    entity.setGradeId(Integer.valueOf(request.getParameter("gradeId")));
                }else{
                    entity.setGradeId(null);
                }
            // 添加默认数据。
    		    entity.setIsDeleted(false);
    		    if(!"超级管理员".equals(getCurrentUser(request).getRole())) {
    		    	entity.setSchoolId(getCurrentUser(request).getSchoolId());
    		    }
    		    
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
            	Subject entity = service.selectById(id);
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
            Subject entity = service.selectById(id);
            entity.setTeacher(userService.selectById(entity.getTeacherId()));
            entity.setSchool(schoolService.selectById(entity.getSchoolId()));
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
        	
        	Wrapper<Subject> criteria = new EntityWrapper<Subject>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "name";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "schoolId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("school_id", q);
        	}
        	qk = "intro";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("intro", q);
        	}
        	qk = "teacherId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("teacher_id", q);
        	}
        	qk = "gradeId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("grade_id", q);
        	}
		    
        	Page<Subject> list = service.selectPage(new Page<Subject>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	Subject e = list.getRecords().get(i);
                e.setTeacher(userService.selectById(e.getTeacherId()));
                e.setSchool(schoolService.selectById(e.getSchoolId()));
            }

            
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }
    
    @RequestMapping("/suggest")  
    public @ResponseBody AjaxCallResult suggest(HttpServletRequest request,Model model){
        try{
        	Wrapper<Subject> criteria = new EntityWrapper<Subject>().eq("is_deleted", false);
            String qk = null;
            String q = null;
        	qk = "keyword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "gradeId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0 && !q.trim().equals("0")) {
        		criteria = criteria.eq("grade_id", q);
        	}			
        	List<Subject> list = service.selectList(criteria);
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
}