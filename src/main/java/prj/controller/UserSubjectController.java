package prj.controller;
import java.util.Date;
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
 *   我的课程前端控制器
 * </p>
 */
@Controller
@RequestMapping("/userSubject")
public class UserSubjectController extends ControllerBase {
	private static final String ENTITY_NAME = "userSubject";
    @Resource  
    private IUserSubjectService service;  
    @Resource  
    private IUserService userService;  
    @Resource  
    private ISubjectService subjectService;  
    
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
            UserSubject entity = null;
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
        		int id = Integer.parseInt(eid);  
        		entity = service.selectById(id);
                if(request.getParameter("userId") != null && request.getParameter("userId").trim().length() > 0){
                    entity.setUserId(Integer.valueOf(request.getParameter("userId")));
                }else{
                    entity.setUserId(null);
                }
                if(request.getParameter("subjectId") != null && request.getParameter("subjectId").trim().length() > 0){
                    entity.setSubjectId(Integer.valueOf(request.getParameter("subjectId")));
                }else{
                    entity.setSubjectId(null);
                }
                if(request.getParameter("time") != null && request.getParameter("time").trim().length() > 0){
                    entity.setTime(Converter.StringToDate(request.getParameter("time")));
                }else{
                    entity.setTime(null);
                }
        		
                service.updateById(entity);
        	}else{
        		entity = new UserSubject();
                if(request.getParameter("userId") != null && request.getParameter("userId").trim().length() > 0){
                    entity.setUserId(Integer.valueOf(request.getParameter("userId")));
                }else{
                    entity.setUserId(null);
                }
                if(request.getParameter("subjectId") != null && request.getParameter("subjectId").trim().length() > 0){
                    entity.setSubjectId(Integer.valueOf(request.getParameter("subjectId")));
                }else{
                    entity.setSubjectId(null);
                }
                if(request.getParameter("time") != null && request.getParameter("time").trim().length() > 0){
                    entity.setTime(Converter.StringToDate(request.getParameter("time")));
                }else{
                    entity.setTime(null);
                }
                entity.setUserId(getCurrentUser(request).getId());

                entity.setTime(new Date());
            // 添加默认数据。
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
            	UserSubject entity = service.selectById(id);
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
            UserSubject entity = service.selectById(id);
            entity.setUser(userService.selectById(entity.getUserId()));
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
        	
        	Wrapper<UserSubject> criteria = new EntityWrapper<UserSubject>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "userId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("user_id", q);
        	}
        	qk = "subjectId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("subject_id", q);
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
        	criteria = criteria.eq("user_id", getCurrentUser(request).getId());
        	Page<UserSubject> list = service.selectPage(new Page<UserSubject>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	UserSubject e = list.getRecords().get(i);
                e.setUser(userService.selectById(e.getUserId()));
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
        	Wrapper<UserSubject> criteria = new EntityWrapper<UserSubject>().eq("is_deleted", false);
            String qk = null;
            String q = null;
        	qk = "keyword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	
        	List<UserSubject> list = service.selectList(criteria);
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
}