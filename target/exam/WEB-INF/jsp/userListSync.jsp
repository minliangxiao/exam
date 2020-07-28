<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>用户管理</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <!-- 
    <link rel="stylesheet" type="text/css" href="styles.css"> 
    -->  
  
  </head>  
    
  <body>  
    <c:forEach items="${list }" var="e">  
        用户名称：${e.loginName}  
        用户密码：${e.password }  
        <a href="<%=path %>/user/show?id=${e.id}">查看</a>&nbsp;
        <a href="<%=path %>/user/edit?id=${e.id}">修改</a>&nbsp;
        <a href="<%=path %>/user/delete?id=${e.id}">删除</a>
        <br/>  
    </c:forEach>  
  </body>  
</html>  