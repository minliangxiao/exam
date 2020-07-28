<%@page import="prj.PrjSettings"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<base href="<%=basePath%>">  
		<title><%=PrjSettings.PROJECT_NAME %> - 登录</title>
	    <meta name="renderer" content="webkit">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="format-detection" content="telephone=no">
	    <link rel="stylesheet" type="text/css" href="css/normalize.css">
	    <link rel="stylesheet" type="text/css" href="css/global.css">
	    <link rel="stylesheet" type="text/css" href="css/pc-main.css">
        <!-- <link rel="stylesheet" type="text/css" href="js/libs/iview/iview.css"> -->
        <style type="text/css">
	        html,body{ background: #3f7abf url(images/bg.jpg); background-repeat: repeat;}
	    </style>
    
	    <script type="text/javascript" src="js/libs/vue.min.js"></script>
        <script type="text/javascript" src="js/libs/iview/iview.min.js"></script>
    	<script type="text/javascript" src="js/jquery.js"></script>
    	<script type="text/javascript" src="js/common/common.js"></script>
    		
	</head>

<body class="login">

    <div id="app" class="content">

            <form action="home/doLogin" method="post" class="login-form" onsubmit="return app.Login();">
            <div class="logo-box">
                <p><font size="3" face="arial" color="white">英语四六级题库.</font></p>
            </div>
            <!-- <span class="form-title"><%=PrjSettings.PROJECT_NAME %></span> -->
            <div class="form-box">
                <div class="form-group">
                    <label class="control-label num">账号</label>
                    <input id="loginName" class="form-input" type="text" autocomplete="off" placeholder="账号" name="loginName" v-model="name">
                </div>
                <div class="form-group">
                    <label class="control-label pass">密码</label>
                    <input id="password" class="form-input" type="password" autocomplete="off" placeholder="密码" name="password" v-model="password">
                </div>
<!-- 
                <div>
                    <table border="0">
                        <tbody>
                            <tr>
                                <td class="form-group">
                                    <input id="txtVerCode" name="vcode" class="verify-input" type="text" autocomplete="off" maxlength="5" placeholder="输入验证码" v-model="vcode">
                                </td>
                                <td>
                                    <a id="btnChangeVerCodeImg" title="看不清楚？点击换一个" href="javascript:void(0);">
                                        <img id="imgVerCode" v-on:click="RefValiDataCode" src="@Url.Action("ShowValiDataCode", "Home")">
                                    </a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                 -->
                <div>${message }</div>
                <div class="form-actions">
                    <input name="btnLogin" type="submit" class="form-btn" value="登录"><br/>
                </div>
                <!-- 
                <div class="form-actions">
                    <input type="button" class="form-btn" value="注册" onclick="window.location.href='home/register';">
                </div>
                 -->
            </div>
         </form>

    </div>


    <script>        
        if (self != top) {
            top.location.href = 'home/login';
        }
        var app = new Vue({
            el: "#app",
            data: {
                name: "",
                password: "",
                vcode:""
            },
            created: function () {
                
            },
            methods: {
                Login: function () {
                    var me = this;
                    if (me.name == "")
                    {
                        alert('请输入帐号');
                        return false;
                    }
                    else if (me.password == "") {
                        alert('请输入密码');
                        return false;
                    }
                    /*
                    else if (me.vcode == "") {
                        alert('请输入验证码');
                        return false;
                    }
                    */                    
                }
            },
            computed: {

            }
        });
    </script>

</body>

	
</html>