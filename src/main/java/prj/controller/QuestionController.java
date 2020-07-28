package prj.controller;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

  

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
 *   试题前端控制器
 * </p>
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends ControllerBase {
	private static final String ENTITY_NAME = "question";
    @Resource  
    private IQuestionService service;  
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
            Question entity = null;
            String eid = request.getParameter("id");
            if(eid != null && eid.trim().length()>0) {
        		int id = Integer.parseInt(eid);  
        		entity = service.selectById(id);
                if(request.getParameter("userId") != null && request.getParameter("userId").trim().length() > 0){
                    entity.setUserId(Integer.valueOf(request.getParameter("userId")));
                }else{
                    entity.setUserId(null);
                }
                entity.setName(request.getParameter("name"));
                entity.setType(request.getParameter("type"));
                if(request.getParameter("subjectId") != null && request.getParameter("subjectId").trim().length() > 0){
                    entity.setSubjectId(Integer.valueOf(request.getParameter("subjectId")));
                }else{
                    entity.setSubjectId(null);
                }
                entity.setA(request.getParameter("a"));
                entity.setB(request.getParameter("b"));
                entity.setC(request.getParameter("c"));
                entity.setD(request.getParameter("d"));
                entity.setCorrect(request.getParameter("correct"));
                if(request.getParameter("score") != null && request.getParameter("score").trim().length() > 0){
                    entity.setScore(Integer.valueOf(request.getParameter("score")));
                }else{
                    entity.setScore(null);
                }
                if(request.getParameter("difficult") != null && request.getParameter("difficult").trim().length() > 0){
                    entity.setDifficult(Integer.valueOf(request.getParameter("difficult")));
                }else{
                    entity.setDifficult(null);
                }
                entity.setExplainText(request.getParameter("explainText"));
        		
                if("判断".equals(entity.getType())){
                    entity.setA(request.getParameter("正确"));
                    entity.setB(request.getParameter("错误"));
                    entity.setC(null);
                    entity.setD(null);
                }
                
                service.updateById(entity);
        	}else{
        		entity = new Question();
                if(request.getParameter("userId") != null && request.getParameter("userId").trim().length() > 0){
                    entity.setUserId(Integer.valueOf(request.getParameter("userId")));
                }else{
                    entity.setUserId(null);
                }
                entity.setName(request.getParameter("name"));
                entity.setType(request.getParameter("type"));
                if(request.getParameter("subjectId") != null && request.getParameter("subjectId").trim().length() > 0){
                    entity.setSubjectId(Integer.valueOf(request.getParameter("subjectId")));
                }else{
                    entity.setSubjectId(null);
                }
                entity.setA(request.getParameter("a"));
                entity.setB(request.getParameter("b"));
                entity.setC(request.getParameter("c"));
                entity.setD(request.getParameter("d"));
                entity.setCorrect(request.getParameter("correct"));
                if(request.getParameter("score") != null && request.getParameter("score").trim().length() > 0){
                    entity.setScore(Integer.valueOf(request.getParameter("score")));
                }else{
                    entity.setScore(null);
                }
                if(request.getParameter("difficult") != null && request.getParameter("difficult").trim().length() > 0){
                    entity.setDifficult(Integer.valueOf(request.getParameter("difficult")));
                }else{
                    entity.setDifficult(null);
                }                
                entity.setExplainText(request.getParameter("explainText"));
    		
            // 添加默认数据。
    		    entity.setIsDeleted(false);
    		    entity.setUserId(getCurrentUser(request).getId());
                if("判断".equals(entity.getType())){
                    entity.setA(request.getParameter("正确"));
                    entity.setB(request.getParameter("错误"));
                    entity.setC(null);
                    entity.setD(null);
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
            	Question entity = service.selectById(id);
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
            Question entity = service.selectById(id);
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
        	
        	Wrapper<Question> criteria = new EntityWrapper<Question>().eq("is_deleted", false);
            String qk = null;
            String q = null;
            
        	qk = "userId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("user_id", q);
        	}
        	qk = "name";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "type";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("type", q);
        	}
        	qk = "subjectId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("subject_id", q);
        	}
        	qk = "a";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("a", q);
        	}
        	qk = "b";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("b", q);
        	}
        	qk = "c";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("c", q);
        	}
        	qk = "d";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("d", q);
        	}
        	qk = "correct";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("correct", q);
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
        	qk = "explainText";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("explain_text", q);
        	}
        	qk = "difficult";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.eq("difficult", q);
        	}        	
        	Page<Question> list = service.selectPage(new Page<Question>(pageNo,pageSize), criteria);
            for(int i = 0; i<list.getRecords().size(); i++) {
            	Question e = list.getRecords().get(i);
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
        	Wrapper<Question> criteria = new EntityWrapper<Question>().eq("is_deleted", false);
            String qk = null;
            String q = null;
        	qk = "keyword";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0) {
        		criteria = criteria.like("name", q);
        	}
        	qk = "subjectId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0 && !q.trim().equals("0")) {
        		criteria = criteria.eq("subject_id", q);
        	}
        	qk = "gradeId";
        	q = request.getParameter(qk);
        	if(q != null && q.trim().length()>0 && !q.trim().equals("0")) {
        		List<Subject> subjects=subjectService.selectList(new EntityWrapper<Subject>().eq("is_deleted", false).eq("grade_id", q));
        		List<Integer> subjectIds=new ArrayList<Integer>();
        		for(Subject subject:subjects){
        			subjectIds.add(subject.getId());
        		}
        		if(subjectIds.size()==0) subjectIds.add(-1);
        		criteria = criteria.in("subject_id", subjectIds);
        	}        	
        	
        	List<Question> list = service.selectList(criteria);
            return new AjaxCallResult(list);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
    //随机返回一个题
    @RequestMapping("/rand")  
    public @ResponseBody AjaxCallResult rand(HttpServletRequest request,Model model){
        try{
            int sid = Integer.parseInt(request.getParameter("sid"));  
            Wrapper<Question> criteria = new EntityWrapper<Question>().eq("is_deleted", false).eq("subject_id", sid);
            List<Question> list = service.selectList(criteria);
            int r=new Random().nextInt(list.size());
            Question entity = list.get(r);
            entity.setUser(userService.selectById(entity.getUserId()));
            entity.setSubject(subjectService.selectById(entity.getSubjectId()));
            return new AjaxCallResult(entity);
    	}catch(Exception ex){
    		return new AjaxCallResult(null, false, ex.getMessage());  
    	}
    }  
	@RequestMapping("/upload")  
	public @ResponseBody AjaxCallResult upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws Exception{
		String fileUrl = saveUploadFile(request, file, "/upload/");
            if(fileUrl!=null){
                List<Question> list = new ArrayList<Question>();
                Question o = new Question();
                String v=null;
                POIFSFileSystem fs=new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook wb=new HSSFWorkbook(fs);
                HSSFSheet hssfSheet =wb.getSheetAt(0);
                if (hssfSheet==null){
                    return new AjaxCallResult(false,"表格数据为空" );
                }
                // 遍历行
                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow=hssfSheet.getRow(rowNum);
                    if (hssfRow==null){
                        continue;
                    }
                    // 遍历列
                    for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                        HSSFCell hssfCell=hssfRow.getCell(cellNum);
                        if (hssfCell==null){
                            continue;
                        }

                    }
                }
                service.insertBatch(list);
            }
        return new AjaxCallResult();
    }
	}
