package prj.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
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
import prj.entity.School;
import prj.entity.User;
import prj.mapper.SchoolMapper;
import prj.service.ISchoolService;
import prj.service.IUserService;

@Controller
@RequestMapping("/home")
public class HomeController extends ControllerBase {
	private static final String ENTITY_NAME = "home";
    @Resource  
    private IUserService service;  
    @Resource  
    private ISchoolService schoolService;  
      
    @RequestMapping("/index")  
    public String index(HttpServletRequest request,Model model){  
    	//TODO: 登录判断
    	if(!isLogin(request)){
    		return "redirect:/"+ENTITY_NAME+"/login";  
    	}
    	
    	model.addAttribute("currentUser",getCurrentUser(request));
        return ENTITY_NAME +"Index";  
    }  
    
    @RequestMapping("/login")  
    public String login(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"Login";  
    }  

    
    @RequestMapping("/doLogin")  
    public String doLogin(HttpServletRequest request,Model model){  
    	String name = request.getParameter("loginName");
    	String password = request.getParameter("password");
    	
    	Wrapper<User> criteria = new EntityWrapper<User>().eq("is_deleted", false)
    			.eq("name", name)
    			.eq("password", password);
    	
    	List<User> list = service.selectList(criteria);
    	
    	if(list.size()>0) {
    		User entity = list.get(0);
    		entity.setSchool(schoolService.selectById(entity.getSchoolId()));
    		CurrentUser user = new CurrentUser(list.get(0).getId(), list.get(0).getRealname());
    		user.setRole(list.get(0).getRole());
    		user.setFace(list.get(0).getFace());
    		if(entity.getSchool()!=null){
    			user.setSchoolId(entity.getSchool().getId());
    			user.setSchoolName(entity.getSchool().getName());
    		}
    		setCurrentUser(request, user);
    		return "redirect:/"+ENTITY_NAME+"/index";  
    	}else{
    		model.addAttribute("Message", "用户名或密码错误！");
    		return "redirect:/"+ENTITY_NAME+"/login";  
    	}
   	
    }  

    @RequestMapping("/logout")  
    public String logout(HttpServletRequest request,Model model){  
    	logout(request);
        return "redirect:/"+ENTITY_NAME+"/login";  
    }  

    @RequestMapping("/register")  
    public String register(HttpServletRequest request,Model model){  
    	model.addAttribute("schools",schoolService.selectList(new EntityWrapper<School>().eq("is_deleted", false)));
        return ENTITY_NAME +"Register";  
    }  

    @RequestMapping("/doRegister")  
    public String doRegister(HttpServletRequest request,Model model){  
            User entity = null;
    		entity = new User();
            entity.setSchoolId(1);
            entity.setName(request.getParameter("loginName"));
            entity.setPassword(request.getParameter("password"));
            entity.setRealname(request.getParameter("name"));
            entity.setNo(request.getParameter("no"));
            // 添加默认数据。
            if(entity.getNo()!=null && "8888".equals(entity.getNo())) {
                entity.setRole("教师");
            } else{
                entity.setRole("学生");
            }
		    entity.setIsDeleted(false);
		    service.insert(entity);
        	return "redirect:/"+ENTITY_NAME+"/login";  
    }      
}
