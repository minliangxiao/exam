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
        <title>查看年级信息</title>
        
        <link rel="stylesheet" type="text/css" href="js/libs/layui/css/layui.css">
        <link rel="stylesheet" type="text/css" href="js/libs/iview/iview.css">
        <!-- DISPLAY  -->
	    <style>
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
	    </style>
        <!-- DISPLAY -->
    </head>
    <body>
		<div style="padding: 10px;" id="app">
		    <i-form ref="modelData" model="modelData" rules="ruleValidate" label-width="80">
		        <tabs>
		            <tab-pane label="年级信息">
                <Row>
                    <i-col span="24">
                        <Form-item label="名称"><span>{{modelData.name}}</span></Form-item>
                    </i-col>
                </Row>
		            </tab-pane>
		        </tabs>
		    </i-form>
		</div>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/libs/layui/layui.js"></script>
        <script type="text/javascript" src="js/libs/vue.min.js"></script>
        <script type="text/javascript" src="js/libs/iview/iview.min.js"></script>
        <script type="text/javascript" src="js/common/common.js"></script>
		<script type="text/javascript" src="js/common/components/pagination.js"></script>
		<!-- DISPLAY COMMON -->
	    <script>
	        common.alert = function (msg) {
	            app.$Message.error(msg);
	        }
	    </script>
	    <!-- DISPLAY COMMON -->   
     <script type="text/javascript">
    var entityId = "${id}";
    var app;
    (function () {
        app = new Vue({
            el: "#app",
            data () { return { modelData: {} }; },
            filters:{
                formatCurrency: function (value) {
                    if (!value) return "";
                    return common.formatCurrency(value);
                },
                formatPercentage: function (value) {
                    if (!value) return "";
                    return common.formatPercentage(value);
                },
                formatNumberWithThousands: function (num) {
                    if (!num) return "";
                    return common.formatNumberWithThousands(num);
                },
                formatDate: function (jsondate) {
                    if (!jsondate) return "";
                    /*
                    jsondate = jsondate.replace("/Date(", "").replace(")/", "");
                    if (jsondate.indexOf("+") > 0) {
                        jsondate = jsondate.substring(0, jsondate.indexOf("+"));
                    }
                    else if (jsondate.indexOf("-") > 0) {
                        jsondate = jsondate.substring(0, jsondate.indexOf("-"));
                    }
                    */
                    //var date = new Date(parseInt(jsondate, 10));
                    var date = new Date(jsondate);
                    return common.formatDateTime(date, "yyyy-MM-dd");
                },
                formatDateTime: function (jsondate) {
                    if (!jsondate) return "";
                    /*
                    jsondate = jsondate.replace("/Date(", "").replace(")/", "");
                    if (jsondate.indexOf("+") > 0) {
                        jsondate = jsondate.substring(0, jsondate.indexOf("+"));
                    }
                    else if (jsondate.indexOf("-") > 0) {
                        jsondate = jsondate.substring(0, jsondate.indexOf("-"));
                    }
                    */
                    var date = new Date(jsondate);
                    return common.formatDateTime(date, "yyyy-MM-dd hh:mm:ss");
                },
                formatBoolean: function (value) {
                    return value ? "是" : "否";
                }
            },
            created: function () { this.getData();},
            methods: {
                getData: function () {
                    var me = this;
                    common.callApi({
                        url: 'grade/get'
                        , data: { id: entityId }
                        , cb: function (r) {
                            me.modelData = r;
                            console.log(me.modelData);
                        }
                    });
                },
            }
        });
    })();
    </script>
 
    </body>
</html>  