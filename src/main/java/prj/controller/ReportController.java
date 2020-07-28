package prj.controller;



  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
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
import prj.entity.BarChartDataItem;
import prj.entity.ProductReportDto;
import prj.entity.SalesInfo;

import prj.service.ISalesInfoService;



@Controller
@RequestMapping("/report")
public class ReportController extends ControllerBase {
	private static final String ENTITY_NAME = "report";
    @Resource  
    private ISalesInfoService service;  
      
    @RequestMapping("/product")  
    public String product(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"Product";  
    }  
      
    @RequestMapping("/getProductReportData")  
    public @ResponseBody AjaxCallResult getProductReportData(HttpServletRequest request,Model model){
    	int month1 = Integer.valueOf(request.getParameter("month1"));
    	int month2 = Integer.valueOf(request.getParameter("month2"));
    	String productString = request.getParameter("product");
    	List<SalesInfo> products=service.selectList(new EntityWrapper<SalesInfo>().eq("product", productString));
    	if(products.size()>0){
    		ProductReportDto dto = new ProductReportDto();
    		List<BarChartDataItem> data = service.summaryTypeRateByBrand(month1, products.get(0).getBrand());
    		for(BarChartDataItem item : data){
    			dto.getLabel1().add(item.getName());
    			dto.getData1().add(item.getRate().intValue());
    		}
    		data = service.summaryBrandRateByType(month2, products.get(0).getType());
    		for(BarChartDataItem item : data){
    			dto.getLabel2().add(item.getName());
    			dto.getData2().add(item.getRate().intValue());
    		}
    		return new AjaxCallResult(dto);
    	}
    	return new AjaxCallResult(null,false,"找不到对应的商品！");
    }
    
    @RequestMapping("/customerPrice")  
    public String customerPrice(HttpServletRequest request,Model model){  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("date", sdf.format(new Date()));  
        return ENTITY_NAME +"CustomerPrice";  
    }      
    
    @RequestMapping("/getCustomerPriceReportData")  
    public @ResponseBody AjaxCallResult getCustomerPriceReportData(HttpServletRequest request,Model model) throws Exception{
    	Calendar c = Calendar.getInstance(); 
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = sdf.parse(request.getParameter("date"));
    	c.setTime(date);
    	c.add(Calendar.DAY_OF_MONTH, -3);
    	date = c.getTime();
    	ProductReportDto dto = new ProductReportDto();
    	List<SalesInfo> data;
    	
    	for (int i = 0; i < 7; i++) {
    		data=service.selectList(new EntityWrapper<SalesInfo>().eq("status", "售出").eq("sell_time", date));
    		int count = data.size();
    		if(count>0){
    			Double total = 0D;
    			for (SalesInfo s : data) {
    				if(s.getSellPrice()!=null) {
    					total += s.getSellPrice().doubleValue();
    				}
				}
    			dto.getLabel1().add(sdf.format(date));
    			dto.getAmount1().add(total);
    			dto.getAmount2().add(total/count);
    		}else{
    			dto.getLabel1().add(sdf.format(date));
    			dto.getAmount1().add(0D);
    			dto.getAmount2().add(0D);
    		}
    		c.setTime(date);
        	c.add(Calendar.DAY_OF_MONTH, 1);
        	date = c.getTime();
		}
		return new AjaxCallResult(dto);
    }    
    
    @RequestMapping("/assertRate1")  
    public String assertRate1(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"AssertRate1";  
    }  
    @RequestMapping("/getAssertReportData1")  
    public @ResponseBody AjaxCallResult getAssertReportData1(HttpServletRequest request,Model model){
    	int year = Calendar.getInstance().get(Calendar.YEAR);
    	int month = Integer.valueOf(request.getParameter("month"));
    	ProductReportDto dto = new ProductReportDto();
		List<BarChartDataItem> data;
		
		month = month -3;
		if(month<=0) {
			year--;
			month+=12;
		}
		BarChartDataItem prevItem = null;
		BarChartDataItem item = null;
		for(int i =0;  i<6; i++) {
			data = service.summaryAssertRate1(year, month);
			if(prevItem==null) {
				prevItem = data.get(0);
			} else{
				item = data.get(0);
				dto.getLabel1().add(item.getName());
				if(item.getRate()!=null){
					dto.getAmount1().add(item.getRate().doubleValue());
				}else{
					dto.getAmount1().add(0D);
				}
				if(item.getRate()==null || prevItem.getRate()==null || prevItem.getRate().doubleValue()== 0) {
					dto.getData1().add(0);
				}else{
					int rate = (int)(item.getRate().doubleValue() / prevItem.getRate().doubleValue() * 100 - 100);
					dto.getData1().add(rate);					
				}
				prevItem=item;
			}
			month++;
			if(month>12){
				month-=12;
				year++;
			}
		}
			
		return new AjaxCallResult(dto);
    }          
    @RequestMapping("/assertRate2")  
    public String assertRate2(HttpServletRequest request,Model model){  
    	String eid = request.getParameter("id");
    	model.addAttribute("id",eid);
        return ENTITY_NAME +"AssertRate2";  
    }  
    @RequestMapping("/getAssertReportData2")  
    public @ResponseBody AjaxCallResult getAssertReportData2(HttpServletRequest request,Model model){
    	int year = Calendar.getInstance().get(Calendar.YEAR);
    	int month = Integer.valueOf(request.getParameter("month"));
    	ProductReportDto dto = new ProductReportDto();
		List<BarChartDataItem> data;
		
		month = month -2;
		if(month<=0) {
			year--;
			month+=12;
		}
		BarChartDataItem item1 = null;
		BarChartDataItem item2 = null;
		for(int i =0;  i<5; i++) {
			data = service.summaryAssertRate1(year, month);
			item1 = data.get(0);
			data = service.summaryAssertRate2(year, month);
			item2 = data.get(0);
			dto.getLabel1().add(item1.getName());
			if(item2.getRate()!=null){
				dto.getAmount1().add(item2.getRate().doubleValue());
			}else{
				dto.getAmount1().add(0D);
			}
			
			if(item1.getRate()==null || item2.getRate()==null || item1.getRate().doubleValue()== 0) {
				dto.getData1().add(0);
			}else{
				if(item2.getRate()!=null){
					int rate = (int)(item2.getRate().doubleValue() / item1.getRate().doubleValue() * 100);
					dto.getData1().add(rate);			
				}else{
					dto.getData1().add(0);		
				}
			}
			month++;
			if(month>12){
				month-=12;
				year++;
			}
		}
			
		return new AjaxCallResult(dto);
    }                
    @RequestMapping("/brand")  
    public String brand(HttpServletRequest request,Model model){  
        return ENTITY_NAME +"Brand";  
    }      
    @RequestMapping("/getBrandReportData")  
    public @ResponseBody AjaxCallResult getBrandReportData(HttpServletRequest request,Model model){
    	int month = Integer.valueOf(request.getParameter("month"));
		ProductReportDto dto = new ProductReportDto();
		List<BarChartDataItem> data = service.summaryBrandRate(month);
		for(BarChartDataItem item : data){
			dto.getLabel1().add(item.getName());
			dto.getData1().add(item.getRate().intValue());
		}
		return new AjaxCallResult(dto);
    }    
    
    
 
    
    @RequestMapping("/search")  
    public @ResponseBody AjaxCallResult search(HttpServletRequest request,Model model){  
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
    	
    	Wrapper<SalesInfo> criteria = new EntityWrapper<SalesInfo>().eq("is_deleted", false);
    	String qk = "loginName";
    	String q = request.getParameter(qk);
    	if(q != null && q.trim().length()>0) {
    		criteria = criteria.like("login_name", q);
    	}
    	
    	Page<SalesInfo> list = service.selectPage(new Page<SalesInfo>(pageNo,pageSize), criteria);
        return new AjaxCallResult(list);  
    }  
    

}
