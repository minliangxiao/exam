package prj;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

//@Controller
public class ControllerBase {
	private final static String CURRENT_USER_SESSION_KEY = "CurrentUser";
	
	protected CurrentUser getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		CurrentUser user = (CurrentUser)session.getAttribute(CURRENT_USER_SESSION_KEY);
		return user;
	}
	
	protected void setCurrentUser(HttpServletRequest request, CurrentUser user){
		HttpSession session = request.getSession();
		session.setAttribute(CURRENT_USER_SESSION_KEY, user);
	}
	
	protected boolean isLogin(HttpServletRequest request) {
		return getCurrentUser(request) != null;
	}
	
	protected void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(CURRENT_USER_SESSION_KEY);
	}
	
	protected String saveUploadFile(HttpServletRequest request,MultipartFile file,String savePathUrl) throws Exception{
		if(savePathUrl==null || savePathUrl.length()==0) savePathUrl = "/upload/";
		if(!file.isEmpty()) {
			String path = request.getServletContext().getRealPath(savePathUrl);
			String filename = file.getOriginalFilename();
			String suffix = filename.substring(filename.lastIndexOf("."));
			
			filename = String.valueOf(UUID.randomUUID().hashCode())+suffix;
			File filePath = new File(path,filename);
			if (!filePath.getParentFile().exists()) { 
				filePath.getParentFile().mkdirs();
            }
			file.transferTo(filePath);
			return savePathUrl + filename;
		}
		return null;
	}	
}
