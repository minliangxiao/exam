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
 *   年级前端控制器
 * </p>
 */
@Controller
@RequestMapping("/grade")
public class GradeController extends ControllerBase {
    private static final String ENTITY_NAME = "grade";
    @Resource
    private IGradeService service;

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
            Grade entity = null;
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
                int id = Integer.parseInt(eid);
                entity = service.selectById(id);
                entity.setName(request.getParameter("name"));

                service.updateById(entity);
            }else{
                entity = new Grade();
                entity.setName(request.getParameter("name"));

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
                Grade entity = service.selectById(id);
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
            Grade entity = service.selectById(id);
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

            Wrapper<Grade> criteria = new EntityWrapper<Grade>().eq("is_deleted", false);
            String qk = null;
            String q = null;

            qk = "name";
            q = request.getParameter(qk);
            if(q != null && q.trim().length()>0) {
                criteria = criteria.like("name", q);
            }

            Page<Grade> list = service.selectPage(new Page<Grade>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
                Grade e = list.getRecords().get(i);
            }


            return new AjaxCallResult(list);
        }catch(Exception ex){
            return new AjaxCallResult(null, false, ex.getMessage());
        }
    }

    @RequestMapping("/suggest")
    public @ResponseBody AjaxCallResult suggest(HttpServletRequest request,Model model){
        try{
            Wrapper<Grade> criteria = new EntityWrapper<Grade>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            qk = "keyword";
            q = request.getParameter(qk);
            if(q != null && q.trim().length()>0) {
                criteria = criteria.like("name", q);
            }

            List<Grade> list = service.selectList(criteria);
            return new AjaxCallResult(list);
        }catch(Exception ex){
            return new AjaxCallResult(null, false, ex.getMessage());
        }
    }
}