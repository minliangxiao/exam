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
        <title>考试</title>
        
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
        <legend>${exam.name}</legend>
    </fieldset>
    <div style="margin-bottom:15px;">
         <div class="barcontainer"> 
		   <div id="bar" style="width:0%;">  <span>{{finishedQty}} / {{qty}}</span></div>  
		   </div>  
    </div>
    <div style="margin-bottom:15px;">
       <br/>
        
    </div>
    
    <div v-if="question.type === '选择题'">
        <div style="font-weight:bolder; ">{{question.name}}（分数：{{ueq.score}}）</div>
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
        <div style="font-weight:bolder; ">{{question.name}}（分数：{{ueq.score}}）</div>
        <div style="padding:1em;">
            <i-select v-model="answer">
                <i-option value="正确" >正确</i-option>
                <i-option value="错误" >错误</i-option>
            </i-select>
        </div>    
    </div>    
    <div v-if="question.type === '填空题'">
        <div style="font-weight:bolder; ">{{question.name}}（分数：{{ueq.score}}）</div>
        <div style="padding:1em;">
            <i-input v-model="answer" placeholder="答案"></i-input>
        </div>    
    </div>
    <div v-if="question.type === '简答题' || question.type === '简答题'  || question.type === '问答题'  || question.type === '编程题' ">
        <div style="font-weight:bolder; ">{{question.name}}（分数：{{ueq.score}}）</div>
        <div style="padding:1em;">
            <i-input v-model="answer" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入答案..."></i-input>
        </div>    
    </div>
        <div style="text-align:center;">
               <i-button type="primary" v-on:click="saveAnswer" >保存答案</i-button>
	         <i-button type="error" v-on:click="submitPaper()">交卷</i-button>
               <i-button type="primary" v-on:click="prev" v-show="hasPrev">上一题</i-button>
               <i-button type="primary" v-on:click="next" v-show="hasNext">下一题</i-button>
               <!-- 
               <i-button type="primary" v-on:click="mark"  v-on:click="mark" v-show="!isMark">标注</i-button>
               <i-button type="primary" v-on:click="unmark" v-on:click="unmark" v-show="isMark">取消标注</i-button>
               <i-select placeholder="标注的试题"  @on-change="goto" style="width:200px">
                   <i-option v-for="item in markList" :value="item.id" :key="item.id">{{ item.name }}</i-option>
               </i-select>
                -->
                <span style="font-weight:bold; font-size:14pt; color:red;">{{timeText}}</span>
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
                	question:{name:'', type:''},
                	ueq:{score:0},
                	answer:null,
                	//qit:{},
                	startId:${startId},
                	currentId:${startId},
                	maxId:${maxId},
                	qty:${qty},
                	minutes:${minutes},
                	seconds:${seconds},
                	hasNext:false,
                	hasPrev:false,
                    isInit: true,
                    finishedQty:1,
                    percent:0,
                    timeText:'',
                    isMark:false,
                    markList:[]
                };
            },
            created: function () {
                var me = this;
                me.percent = parseInt(me.finishedQty / me.qty * 100);
                $("#bar").css("width", me.percent + "%");
                me.getData();
				me.calcTime();
            },
            methods: {
            	calcTime:function(){
            	var me=this;
            		timer = setInterval(me.timerFunc,1000);
            	},
            	timerFunc:function(){
            		var me = this;
			        me.seconds--;
			        if(me.seconds<=0) {
			            me.autoSubmitPaper();
			            return;
			        }
			        var tm = me.seconds;
			        h = Math.floor(tm / 3600);
			        tm = tm % 3600;
			        m = Math.floor(tm / 60);
			        tm = tm %60;
			        s = tm;
			        me.timeText=h+":"+m+":"+s;
            	},
            	mark:function(){
            			var me = this;
		              common.callApi({
		                  url: "userExamQuestion/mark", data: { id:me.currentId},
		                  cb: function (r) {
								me.markList.push({id:me.currentId, name:me.question.name});
								me.isMark=true;
		                  }
		              });               	
            	},
            	unmark:function(){
            			var me = this;
		              common.callApi({
		                  url: "userExamQuestion/unmark", data: { id:me.currentId},
		                  cb: function (r) {
		                  		me.isMark=false;
		                  		var idx = getIndex2(me.markList, me.currentId);
		                  		removeItemAtIndex(me.markList,idx);
		                  }
		              });               	
            	},
                getData: function () {
                    var me = this;
                    if(!me.isInit) {
                    	//me.saveAnswer();
                    }
                   	me.getQuestion();
                    me.isInit=false;
                	me.hasPrev = (me.currentId > me.startId);
                	me.hasNext = (me.currentId < me.maxId);
                },
                goto:function(item){
                 var me = this;
                //me.saveAnswer();
                	me.currentId=item;
                	me.getData();
                },
                prev:function(){
                	var me = this;
                	//me.saveAnswer();
                	if(me.currentId<=me.startId) return;
                	me.currentId--;
                	me.getData();
//                	me.hasPrev = (me.currentId > me.startId);
//                	me.hasPrev = (me.currentId < me.maxId);
                },
                next:function(){
                	var me = this;
                	//me.saveAnswer();
                	if(me.currentId>=me.maxId) return;
                	me.currentId++;
                	me.getData();
                	var fQty = me.currentId - me.startId + 1;
                	if(fQty>me.finishedQty) me.finishedQty = fQty; 
                	me.percent = parseInt(me.finishedQty / me.qty * 100);
                	 $("#bar").css("width", me.percent + "%");
//                	me.hasPrev = (me.currentId > me.startId);
//                	me.hasPrev = (me.currentId < me.maxId);
                },
                submitPaper:function(){
                 var me = this;
                	me.saveAnswer();
               	      layer.confirm('确认要交卷吗？', { icon: 2, title: '提示' }, function (index) {
                               layer.close(index);
                               window.location.href="userExam/comment?id=" + me.ueq.userexamId;
                           });
                },
                autoSubmitPaper:function(){
                var me = this;
                	me.saveAnswer();
                		window.location.href="userExam/comment?id=" + me.ueq.userexamId;
                },                
                saveAnswer:function(){
		                //保存当前答案
		               	var me = this;
			              common.callApi({
			                  url: "userExamQuestion/get", data: { id:me.currentId},
			                  cb: function (r) {
									r.answer = me.answer;
									r.qtype = me.question.type;
						              common.callApi({
						                  url: "userExamQuestion/save", data: r,
						                  cb: function (r) {
						                      //me.$Message.success('操作成功');
						                      //me.next();
						                  }
						              });
			                  }
			              });               	
                },
                getQuestion: function () {
                    var me = this;
                me.hasPrev = false;
                me.hasNext = false;
                
                hp=false;
                hn=false;
                    common.callApi({
                        url: "userExamQuestion/get"
                        , data: { id: me.currentId }
                        , cb: function (r) {
                            me.ueq = r;
                            me.answer=r.answer;
                            me.isMark=r.isMark;
                            common.callApi({
		                        url: "question/get"
		                        , data: { id: r.questionId }
		                        , cb: function (r) {
		                            me.question = r;
                	me.hasPrev = (me.currentId > me.startId);
                	me.hasNext = (me.currentId < me.maxId);
	                                // 使用 UEditor 时，编辑的字段要加以下代码。
	                                //if (me.modelData.Remark) {
	                                //    bodyUe.setContent(me.modelData.Remark);
	                                //}
	                                // 使用 UEditor 时，编辑的字段要加以上代码。
		                        }
		                    });
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