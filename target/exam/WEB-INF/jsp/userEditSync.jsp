<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>编辑用户信息</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <!-- 
    <link rel="stylesheet" type="text/css" href="styles.css"> 
    -->  
  
  </head>  
    
  <body>  
    <form id="add" action="user/save" method="post">   
    <input type="hidden" name="id" value="${entity.id}" />
        用户名： <input id="loginName" name="loginName" value="${entity.loginName }" /><br/>   
        密码： <input id="password" name="password" value="${entity.password }" /><br/>   
        <input type="submit" value="保存"/>   
    </form>  
  </body>  
</html>  