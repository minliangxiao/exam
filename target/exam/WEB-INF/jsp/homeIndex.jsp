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
		<title><%=PrjSettings.PROJECT_NAME %></title>
	    <meta name="renderer" content="webkit">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="format-detection" content="telephone=no">
	    <link rel="stylesheet" type="text/css" href="css/normalize.css">
	    <link rel="stylesheet" type="text/css" href="css/global.css">
	    <link rel="stylesheet" type="text/css" href="css/pc-main.css">
        <link rel="stylesheet" type="text/css" href="js/libs/iview/iview.css">
    
	    <script type="text/javascript" src="js/libs/vue.min.js"></script>
        <script type="text/javascript" src="js/libs/iview/iview.min.js"></script>
    	<script type="text/javascript" src="js/jquery.js"></script>
    	<script type="text/javascript" src="js/common/common.js"></script>
    		
	</head>
	<body>
	
		<div id="app">
			<div class="layout">
	            <i-menu mode="horizontal" theme="primary">
	                <div class="1layout-logo" v-on:click="goHome"></div>
	                <div class="layout-nav">
<!-- 
	                    <menu-item name="1" style="display:none;">
	                        <Icon type="gear-b"></Icon>
	                        任务（0）
	                    </menu-item>
 -->	                    
	                </div>
	                <div class="layout-ceiling">
	                    <dropdown class="layout-ceiling-main" @on-click="onMenuClick">
	                        <a>
	                            {{data.currentUserName}}
	                            <icon type="arrow-down-b"></icon>
	                        </a>
	                        <dropdown-menu slot="list">
	                            <dropdown-item name="usercenter">返回首页</dropdown-item>
	                            <dropdown-item name="logout">退出登录</dropdown-item>
	                        </dropdown-menu>
	                    </dropdown>
	                    <avatar icon="person" v-bind:src="data.currentHeadImg" />
	                </div>
	            </i-menu>
            <Row type="flex">
                <i-col span="3" class="layout-menu-left">
                    <i-menu theme="dark" width="auto" open-names="['1']">
                        
<Submenu name="1" v-if="data.currentRole==='管理员' ">
    <template slot="title">
        <Icon type="document-text"></Icon>
        管理员功能
    </template>
    <menu-item name="editInfo"><div v-on:click="menuSelect('user/editInfo')">修改资料</div></menu-item>
    <menu-item name="user"><div v-on:click="menuSelect('user/list')">用户管理</div></menu-item>
    <menu-item name="grade"><div v-on:click="menuSelect('grade/list')">年级管理</div></menu-item>
    <menu-item name="subject"><div v-on:click="menuSelect('subject/list')">试题类型管理</div></menu-item>
    <menu-item name="question"><div v-on:click="menuSelect('question/list')">试题库管理</div></menu-item>
    <menu-item name="testpaper"><div v-on:click="menuSelect('testpaper/list')">试卷生成管理</div></menu-item>
    <menu-item name="exam"><div v-on:click="menuSelect('exam/list')">考试管理</div></menu-item>
    <menu-item name="user_exam"><div v-on:click="menuSelect('userExam/list')">考试结果</div></menu-item>
</Submenu>

<Submenu name="4" v-if="data.currentRole==='学生'">
    <template slot="title">
        <Icon type="document-text"></Icon>
        学生功能
    </template>
    <menu-item name="editInfo"><div v-on:click="menuSelect('user/editInfo')">修改资料</div></menu-item>
    <!-- 
    <menu-item name="user_subject"><div v-on:click="menuSelect('userSubject/list')">我的课程</div></menu-item>
 -->
    <menu-item name="toExercise"><div v-on:click="menuSelect('exam/toExercise')">练习</div></menu-item>
    <menu-item name="user_subject2"><div v-on:click="menuSelect('exam/join')">参加考试</div></menu-item>
    <menu-item name="user_exam"><div v-on:click="menuSelect('userExam/mine')">考试结果</div></menu-item>
    <!-- <menu-item name="user_exam2"><div v-on:click="menuSelect('userExam/mine')">考试分析</div></menu-item> -->
    <menu-item name="user_exam3"><div v-on:click="menuSelect('userExamQuestion/errors')">我的错题</div></menu-item>
    
</Submenu>
              


                    </i-menu>
                </i-col>
                <i-col span="21">
                    <div class="layout-content">
                        <iframe id="myFrame" src="main.html" style="width:100%; height:100%;border:none; "></iframe>
                    </div>
                </i-col>
            </Row>
				
			</div>
	        <modal v-model="editModal" v-bind:width="700">
	            <iframe style="width:100%; border-width:0px; min-height:500px;" id="editFrame"></iframe>
	            <div slot="footer"></div>
	        </modal>
		</div>
	
	    <script>
	        var app = new Vue({
	            el: "#app",
	            data: {
	                data: {
	                    currentUserId: '${currentUser.id}',
	                    currentUserName: '${currentUser.name}',
	                    currentSchoolId:'${currentUser.schoolId}',
	                    currentSchoolName:'${currentUser.schoolName}',
	                    currentRole:'${currentUser.role}',
	                    currentHeadImg: '${currentUser.face}',

	                    ActiveModule: "1",
	                    postIng: false,
	                    spanLeft: 3,
	                    spanRight: 21
	                },
	                editModal: false
	            },
	            created: function () {
	                var _self = this;
/*	
	                common.callApi({
	                    url: "@Url.Action("IndexViewModel")"
	                    , cb: function (r) {
	                        _self.data = r;
	                    }
	                });
*/
	            },
	            mounted:function(){
	                var _self = this;
	            },
	            methods: {
	                menuSelect: function (item) {
	                    if (item ) {
	                        $("#myFrame").attr("src", item);
	                    }
	                },
	                toggleClick () {
	                    if (this.data.spanLeft === 3) {
	                        this.data.spanLeft = 1;
	                        this.data.spanRight = 23;
	                    } else {
	                        this.data.spanLeft = 3;
	                        this.data.spanRight = 21;
	                    }
	                },
	                openWin: function (url) {
	                    $("#editFrame").attr("src", url);
	                    this.editModal = true;
	                },
	                closeWin: function () {
	                    this.editModal = false;
	                    this.logout();
	                },
	                logout: function () {
	                    window.location.href = 'home/logout';
	                },
	                changepwd: function () {
	                    this.openWin("user/changePassword");
	                },
	                goHome: function () {
	                    $("#myFrame").attr("src", 'main.html');
	                },
	                onMenuClick: function (name) {
	                    name = "this." + name;
	                    var exp = "if(" + name + " && typeof(" + name + ") === 'function') { " + name + "(); } else { common.alert('" + name + "尚未实现！');}";
	                    eval(exp);
	                },
	                usercenter: function () {
	                    this.goHome();
	                }
                },
	            computed: {
	                iconSize () {
	                    return this.data.spanLeft === 5 ? 14 : 24;
	                }
	            }
	
	        });
	    </script>
    	
	</body>
</html>