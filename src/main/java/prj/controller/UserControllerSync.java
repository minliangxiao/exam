package prj.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import prj.ControllerBase;
import prj.entity.User;
import prj.service.IUserService;

/**
 * <p>
 *   鍓嶇鎺у埗鍣�
 * </p>
 *
 
 */
@Controller
@RequestMapping("/sync/user")
public class UserControllerSync extends ControllerBase {
	private static final String ENTITY_NAME = "user";
    @Resource  
    private IUserService service;  
      
    @RequestMapping("/list")  
    public String list(HttpServletRequest request,Model model){  
        List<User> list = service.selectList(new EntityWrapper<User>().eq("is_deleted", false)) ;
        model.addAttribute("list", list);  
        return ENTITY_NAME +"List";  
    }  
      
    @RequestMapping("/show")  
    public String show(HttpServletRequest request,Model model){  
        int id = Integer.parseInt(request.getParameter("id"));  
        User entity = service.selectById(id);  
        model.addAttribute("entity", entity);  
        return ENTITY_NAME +"Show";  
    }  
      
    @RequestMapping("/edit")  
    public String edit(HttpServletRequest request,Model model){  
    	User entity = new User();
    	String eid = request.getParameter("id");
    	if(eid != null && eid.trim().length()>0) {
    		int id = Integer.parseInt(eid);  
    		entity = service.selectById(id);  
    	}else{
    	
    	}
    	model.addAttribute("entity", entity);  
        return ENTITY_NAME +"Edit";  
    }  
      
    @RequestMapping("/save")  
    public String save(HttpServletRequest request,Model model){  
        User entity = null;
        String eid = request.getParameter("id");
        if(eid != null && eid.trim().length()>0) {
    		int id = Integer.parseInt(eid);  
    		entity = service.selectById(id);  
    		entity.setName(request.getParameter("loginName"));
    		entity.setPassword(request.getParameter("password"));
    		service.updateById(entity);
    	}else{
    		entity = new User();
    		entity.setName(request.getParameter("loginName"));
    		entity.setPassword(request.getParameter("password"));
    		// 娣诲姞榛樿鏁版嵁銆�
    		//entity.setStatus("姝ｅ父");
    		entity.setIsDeleted(false);
    		service.insert(entity);
    	}
        return "redirect:/"+ENTITY_NAME+"/list";  
    }  
    
    @RequestMapping("/delete")  
    public String delete(HttpServletRequest request,Model model){  
        String eid = request.getParameter("id");
        if(eid != null && eid.trim().length()>0) {
        	int id = Integer.parseInt(eid);  
        	User entity = service.selectById(id);  
        	entity.setIsDeleted(true);
        	service.updateById(entity);
        	//service.deleteById(id);
        }
        return "redirect:/"+ENTITY_NAME+"/list";  
	}
    
    @RequestMapping("/search")  
    public @ResponseBody Page<User> search(HttpServletRequest request,Model model){  
    	int pageNo = 1;
    	int pageSize = 1;
    	String sPageNo = request.getParameter("pageNo");
    	String sPageSize = request.getParameter("pageSize");
    	if(sPageNo!=null && sPageNo.trim().length()>0) {
    		pageNo = Integer.valueOf(sPageNo);
    	}
    	if(sPageSize!=null && sPageSize.trim().length()>0) {
    		pageSize = Integer.valueOf(sPageSize);
    	}
    	
//        List<User> list = service.selectList(new EntityWrapper<User>().eq("is_deleted", false)) ;
    	Page<User> list = service.selectPage(new Page<User>(pageNo,pageSize), new EntityWrapper<User>().eq("is_deleted", false));
        //model.addAttribute("list", list);  
        return list;  
    }  
}
