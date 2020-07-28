<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<base href="<%=basePath%>">  
        <title>练习</title>
        
        <link rel="stylesheet" type="text/css" href="js/libs/layui/css/layui.css">
        <link rel="stylesheet" type="text/css" href="js/libs/iview/iview.css">
        <!-- EDIT  -->
		<style>
		    select {
		        width: 100%;
		        height: 38px;
		        border: 1px solid #e6e6e6;
		        padding-left: 10px;
		    }
		
		        select[disabled] {
		            background-color: #fff;
		        }
		
		    .required {
		        border: 1px solid red;
		    }
		
		    .imgBox {
		        width: 137px;
		        height: 137px;
		    }
		
		        .imgBox + .imgBox {
		            padding-left: 5px;
		        }
		
		    .demo-upload-list {
		        display: inline-block;
		        width: 60px;
		        height: 60px;
		        text-align: center;
		        line-height: 60px;
		        border: 1px solid transparent;
		        border-radius: 4px;
		        overflow: hidden;
		        background: #fff;
		        position: relative;
		        box-shadow: 0 1px 1px rgba(0,0,0,.2);
		        margin-right: 4px;
		    }
		
		        .demo-upload-list img {
		            width: 100%;
		            height: 100%;
		        }
		
		    .demo-upload-list-cover {
		        display: none;
		        position: absolute;
		        top: 0;
		        bottom: 0;
		        left: 0;
		        right: 0;
		        background: rgba(0,0,0,.6);
		    }
		
		    .demo-upload-list:hover .demo-upload-list-cover {
		        display: block;
		    }
		
		    .demo-upload-list-cover i {
		        color: #fff;
		        font-size: 20px;
		        cursor: pointer;
		        margin: 0 2px;
		    }
		    
		    <style type="text/css">  
.barcontainer{  
   width:450px;  
   border:1px solid black;  
   height:25px;  
 }
#bar{  
   background:#95CA0D;  
   float:left; 
   height:100%;  
   text-align:center;  
   line-height:150%; 
 }  
</style> 
		</style>
        <!-- EDIT -->
    </head>
    <body>

<div style="padding: 10px;" id="app">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>练习</legend>
    </fieldset>
    <div style="margin-bottom:15px;">
    	<div style="padding-top:5px;">
           <i-select v-model="subjectId" placeholder="请选择课程"  style="width:200px;" >
               <i-option v-for="item in subjectIdSuggestDataList" :value="item.id" :key="item.id">{{ item.name }}</i-option>
           </i-select>
            <i-button type="ghost" icon="ios-search" v-on:click="start()">开 始</i-button>
    	</div>
    </div>
    <div style="margin-bottom:15px;">
       <br/>
        
    </div>
    
    <div v-if="question.type === '选择题'">
        <div style="font-weight:bolder; ">{{question.name}}</div>
        <div style="padding:1em;">
            <div v-show="question.a">A. {{question.a}}</div>
            <div v-show="question.b">B. {{question.b}}</div>
            <div v-show="question.c">C. {{question.c}}</div>
            <div v-show="question.d">D. {{question.d}}</div>
            <i-select v-model="answer">
                <i-option value="A" v-show="question.a">A</i-option>
                <i-option value="B" v-show="question.b">B</i-option>
                <i-option value="C" v-show="question.c">C</i-option>
                <i-option value="D" v-show="question.d">D</i-option>
            </i-select>
        </div>    
    </div>
    <div v-if="question.type === '判断题'">
        <div style="font-weight:bolder; ">{{question.name}}</div>
        <div style="padding:1em;">
            <i-select v-model="answer">
                <i-option value="正确" >正确</i-option>
                <i-option value="错误" >错误</i-option>
            </i-select>
        </div>    
    </div>    
    <div v-if="question.type === '填空题'">
        <div style="font-weight:bolder; ">{{question.name}}</div>
        <div style="padding:1em;">
            <i-input v-model="answer" placeholder="答案"></i-input>
        </div>    
    </div>
    <div v-if="question.type === '简答题' || question.type === '简答题'  || question.type === '问答题'  || question.type === '编程题' ">
        <div style="font-weight:bolder; ">{{question.name}}</div>
        <div style="padding:1em;">
            <i-input v-model="answer" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入答案..."></i-input>
        </div>    
    </div>
        <div style="text-align:center;">
               <i-button type="primary" v-on:click="showAnswer" >查看答案</i-button>
               <i-button type="primary" v-on:click="next" >继续</i-button>
        </div>
        
       <div style="margin-top:15px;" v-show="isShowAnswer">
       正确答案/参考答案：{{question.correct}} <br />
       解析：{{question.explainText}}
       <br/>
        
    </div>
</div>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/libs/layui/layui.js"></script>
        <script type="text/javascript" src="js/libs/vue.min.js"></script>
        <script type="text/javascript" src="js/libs/iview/iview.min.js"></script>
        <script type="text/javascript" src="js/common/common.js"></script>
		<script type="text/javascript" src="js/common/components/pagination.js"></script>
        <!-- 
	    <script src="ueditor/ueditor.config.js"></script>
	    <script src="ueditor/ueditor.all.min.js"></script>
	    <script src="ueditor/lang/zh-cn/zh-cn.js"></script>
	     -->
        <script>
	        common.alert = function (msg) {
	            app.$Message.error(msg);
	        }
	    </script>
<script type="text/javascript">
    var entityId = "${id}";
	var examId = "${id}";
	var timer;
	layui.use(['layer']);
    var app;
    (function () {
        //var bodyUe = UE.getEditor('body');
        app = new Vue({
            el: "#app",
            data () {
                var numberPattern = /^(\-)?\d+(\.\d{1,2})?$/;
                return {
                	subjectIdSuggestDataList: [],
                	subjectId:'',
                	question:{name:'', type:''},
                	ueq:{score:0},
                	answer:null,
                	//qit:{},
                    isShowAnswer:false,
                };
            },
            created: function () {
                var me = this;
                me.getSubjectIdSuggestDataList();
                //me.getData();
            },
            methods: {
               getSubjectIdSuggestDataList: function () {
                     var me = this;
                     common.callApi({
                         url: "subject/suggest"
                         , data: { keyword: "" }
                         , cb: function (r) {
                             me.subjectIdSuggestDataList = r;
                         }
                     });
                 },
                 start:function(){
                 	this.getData();
                 },

                getData: function () {
                    var me = this;
                   	me.getQuestion();
                },

                next:function(){
                	var me = this;
                	me.isShowAnswer = false;
                	me.getData();
                },
				showAnswer:function(){
					var me = this;
                	me.isShowAnswer = true;
				},
                getQuestion: function () {
                    var me = this;
                    common.callApi({
                        url: "question/rand"
                        , data: { sid: me.subjectId }
                        , cb: function (r) {
                            me.question = r;
                        }
                    });
                },
            }
        });
    })();
            function getIndex(arr, ele){
    	var idx = -1;
    	for(i=0; i < arr.length; i++) {
    		if(arr[i].id==ele.id) {
    			idx=i;
    			break;
    		}
    	}
    	return idx;
    }
    function removeItem(arr, ele){
    	var idx = getIndex(arr, ele);
    	if(idx>=0) {
    		for(i=idx; i < arr.length -1; i++) {
    			arr[i]=arr[i+1];
    		}
    		arr.pop();
    	}
    }
        function getIndex2(arr, ele){
    	var idx = -1;
    	for(i=0; i < arr.length; i++) {
    		if(arr[i].id==ele) {
    			idx=i;
    			break;
    		}
    	}
    	return idx;
    }    
    function removeItemAtIndex(arr, idx){
    	if(idx>=0) {
    		for(i=idx; i < arr.length -1; i++) {
    			arr[i]=arr[i+1];
    		}
    		arr.pop();
    	}
    }        
</script>
    </body>
</html>  