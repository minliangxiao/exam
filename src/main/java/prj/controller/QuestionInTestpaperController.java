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
 *   【QuestionInTestpaper】前端控制器
 * </p>
 */
@Controller
@RequestMapping("/questionInTestpaper")
public class QuestionInTestpaperController extends ControllerBase {
	private static final String ENTITY_NAME = "questionInTestpaper";
    @Resource  
    private IQuestionInTestpaperService service;  
    @Resource  
    private ITestpaperService testpaperService;  
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
      
   @RequestMapping("/save")  
    public @ResponseBody AjaxCallResult save(HttpServletRequest request,Model model){  
        try{
            QuestionInTestpaper entity = null;
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
        		int id = Integer.parseInt(eid);  
        		entity = service.selectById(id);
                if(request.getParameter("testpaperId") != null && request.getParameter("testpaperId").trim().length() > 0){
                    entity.setTestpaperId(Integer.valueOf(request.getParameter("testpaperId")));
                }else{
                    entity.setTestpaperId(null);
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
        		
                service.updateById(entity);
        	}else{
        		entity = new QuestionInTestpaper();
                if(request.getParameter("testpaperId") != null && request.getParameter("testpaperId").trim().length() > 0){
                    entity.setTestpaperId(Integer.valueOf(request.getParameter("testpaperId")));
                }else{
                    entity.setTestpaperId(null);
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
            	QuestionInTestpaper entity = service.selectById(id);
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
            QuestionInTestpaper entity = service.selectById(id);
            entity.setTestpaper(testpaperService.selectById(entity.getTestpaperId()));
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
        	
        	Wrapper<QuestionInTestpaper> criteria = new EntityWrapper<QuestionInTestpaper>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "testpaperId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("testpaper_id", q);
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
        	
        	Page<QuestionInTestpaper> list = service.selectPage(new Page<QuestionInTestpaper>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	QuestionInTestpaper e = list.getRecords().get(i);
                e.setTestpaper(testpaperService.selectById(e.getTestpaperId()));
                e.setQuestion(questionService.selectById(e.getQuestionId()));
            }

            
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }
    
  @RequestMapping("/suggest")  
    public @ResponseBody AjaxCallResult suggest(HttpServletRequest request,Model model){
        try{
        	Wrapper<QuestionInTestpaper> criteria = new EntityWrapper<QuestionInTestpaper>().eq("is_deleted", false);
            String qk = null;
            String q = null;
        	qk = "keyword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	
        	List<QuestionInTestpaper> list = service.selectList(criteria);
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
}  