package prj.controller;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
 *   用户前端控制器
 * </p>
 */
@Controller
@RequestMapping("/user")
public class UserController extends ControllerBase {
	private static final String ENTITY_NAME = "user";
    @Resource  
    private IUserService service;  
    @Resource  
    private ISchoolService schoolService;  
    
    @RequestMapping("/editInfo")  
    //得到用户id
    public String editInfo(HttpServletRequest request,Model model){  
    	int eid = getCurrentUser(request).getId();
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"EditInfo";  
    }  
    
    @RequestMapping("/saList")  
    //返回user Salist
    public String saList(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"SaList";  
    }  
    //从请求参数中拿到id然后将id放入model（前端页面能够拿到model里面的参数）
    @RequestMapping("/editSa")  
    public String editSa(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"EditSa";  
    }    
    //从请求参数中拿到id然后将id放入到model
    
    @RequestMapping("/adminList")  
    public String adminList(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"AdminList";  
    }  
    @RequestMapping("/editAdmin")  
    public String editAdmin(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"EditAdmin";  
    }      
    
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
      //新增或修改用户
    @RequestMapping("/save")  
    public @ResponseBody AjaxCallResult save(HttpServletRequest request,Model model){  
        try{
            User entity = null;
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {  //判断修改条件
            	//修改
        		int id = Integer.parseInt(eid);  
        		entity = service.selectById(id);
                entity.setNo(request.getParameter("no"));
                entity.setName(request.getParameter("name"));
                entity.setPassword(request.getParameter("password"));
                entity.setRealname(request.getParameter("realname"));
                entity.setRole(request.getParameter("role"));
                entity.setFace(request.getParameter("face"));
                entity.setGender(request.getParameter("gender"));
                entity.setCollege(request.getParameter("college"));
                entity.setGrade(request.getParameter("grade"));
                entity.setProfession(request.getParameter("profession"));
                if(request.getParameter("schoolId") != null && request.getParameter("schoolId").trim().length() > 0){
                    entity.setSchoolId(Integer.valueOf(request.getParameter("schoolId")));
                }else{
                    entity.setSchoolId(null);
                }
                entity.setRegPassword(request.getParameter("regPassword"));
        		
                service.updateById(entity);
        	}else{
        		//新增
        		entity = new User();
                entity.setNo(request.getParameter("no"));
                entity.setName(request.getParameter("name"));
                entity.setPassword(request.getParameter("password"));
                entity.setRealname(request.getParameter("realname"));
                entity.setRole(request.getParameter("role"));
                entity.setFace(request.getParameter("face"));
                entity.setGender(request.getParameter("gender"));
                entity.setCollege(request.getParameter("college"));
                entity.setGrade(request.getParameter("grade"));
                entity.setProfession(request.getParameter("profession"));
                if(request.getParameter("schoolId") != null && request.getParameter("schoolId").trim().length() > 0){
                    entity.setSchoolId(Integer.valueOf(request.getParameter("schoolId")));
                }else{
                    entity.setSchoolId(null);
                }
                entity.setRegPassword(request.getParameter("regPassword"));
    		
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
    //删除user（做的是假删除 通过user的标识位is_deleted标识是否被删除）
    @RequestMapping("/delete")  
    public @ResponseBody AjaxCallResult delete(HttpServletRequest request,Model model){
        try{
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
            	int id = Integer.parseInt(eid);  
            	User entity = service.selectById(id);
            	//假删除
            	entity.setIsDeleted(true);
            	service.updateById(entity);
            }
            return new AjaxCallResult();
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
	}
    //通过id查找一个user
    @RequestMapping("/get")  
    public @ResponseBody AjaxCallResult get(HttpServletRequest request,Model model){
        try{
            int id = Integer.parseInt(request.getParameter("id"));  
            User entity = service.selectById(id);
            entity.setSchool(schoolService.selectById(entity.getSchoolId()));
            return new AjaxCallResult(entity);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
    //分页查询
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
        	
        	Wrapper<User> criteria = new EntityWrapper<User>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "no";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("no", q);
        	}
        	qk = "name";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "password";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("password", q);
        	}
        	qk = "realname";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("realname", q);
        	}
        	qk = "role";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		if("普通".equals(q)) {
        			criteria = criteria.ne("role", "超级管理员");
        			criteria = criteria.ne("role", "管理员");
        		}else{
            		criteria = criteria.eq("role", q);
        		}
        	}
        	qk = "schoolId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("school_id", q);
        	}
        	qk = "regPassword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("reg_password", q);
        	}
        	qk = "gender";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("gender", q);
        	}
        	qk = "college";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("college", q);
        	}
        	qk = "grade";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("grade", q);
        	}
        	qk = "profession";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("profession", q);
        	}
        	        	
        	
        	Page<User> list = service.selectPage(new Page<User>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	User e = list.getRecords().get(i);
                e.setSchool(schoolService.selectById(e.getSchoolId()));
            }

            
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }
    //根据关键字查询出一个user list
    @RequestMapping("/suggest")  
    public @ResponseBody AjaxCallResult suggest(HttpServletRequest request,Model model){
        try{
        	Wrapper<User> criteria = new EntityWrapper<User>().eq("is_deleted", false);
            String qk = null;
            String q = null;
        	qk = "keyword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "role";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("role", q);
        	}        	

        	List<User> list = service.selectList(criteria);
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
    //上传文件
	@RequestMapping("/upload")  
	public @ResponseBody AjaxCallResult upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws Exception{
		String fileUrl = saveUploadFile(request, file, "/upload/");
		if(fileUrl!=null){
			ArrayList<ArrayList<String>> data = CsvHelper.export(request.getServletContext().getRealPath(fileUrl));
			List<User> list = new ArrayList<User>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(ArrayList<String> row:data){
				if(row!=null) {
					User o = new User();
					String v=null;
					o.setName(row.get(0));
					o.setPassword(row.get(1));
					o.setRealname(row.get(2));
					o.setRole(row.get(3));
					o.setIsDeleted(false);
					o.setGender(row.get(4));
					o.setCollege(row.get(5));
					o.setGrade(row.get(6));
					o.setProfession(row.get(7));
					list.add(o);
				}
			}
			service.insertBatch(list);
		}
		return new AjaxCallResult();
	}    
    
}