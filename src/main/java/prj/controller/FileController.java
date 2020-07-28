package prj.controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import prj.AjaxCallResult;
import prj.ControllerBase;

import prj.util.CsvHelper;

@Controller
@RequestMapping("/file")
public class FileController extends ControllerBase {
//	private static final String ENTITY_NAME = "file";
//    @Resource  
//    private ISalesInfoService service;  
//	@RequestMapping("/upload") 
//	public String upload(HttpServletRequest request,Model model){
//		return ENTITY_NAME +"Upload";  
//	}
	@RequestMapping("/upload")  
	public @ResponseBody AjaxCallResult upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws Exception{
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";  
		String fileUrl = saveUploadFile(request, file, "/upload/");
		
		return new AjaxCallResult(fileUrl);

	}
	
	@RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam("filename") String filename, Model model)throws Exception {
    	String urlString = filename.substring(filename.indexOf("/upload"));
       //下载文件路径
       String path = request.getServletContext().getRealPath("/");
       //File file = new File(path + File.separator + filename);
       File file = new File(path + File.separator + urlString);
       HttpHeaders headers = new HttpHeaders();  
       //下载显示的文件名，解决中文名称乱码问题  
       String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
       //通知浏览器以attachment（下载方式）打开图片
       headers.setContentDispositionFormData("attachment", downloadFielName); 
       //application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);  
    }
}
