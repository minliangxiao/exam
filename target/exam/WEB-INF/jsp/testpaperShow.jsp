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
        <title>查看试卷信息</title>
        
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
		    <i-form ref="modelData" model="modelData" rules="ruleValidate" label-width="120">
		        <tabs>
		            <tab-pane label="试卷信息">

                <Row>
                    <i-col span="24">
                        <Form-item label="编号"><span>{{modelData.code}}</span></Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Form-item label="名称"><span>{{modelData.name}}</span></Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Form-item label="分钟数"><span>{{modelData.minutes}}</span></Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Form-item label="总分数"><span>{{modelData.totalScore}}</span></Form-item>
                    </i-col>
                </Row>
		            </tab-pane>
		        </tabs>
		    </i-form>
		    <i-table border ref="selection" v-bind:columns="columnsList" v-bind:data="dataList"></i-table>
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
    	        var renderImage = function (h, src) {
    	            if (!src) return "";
    	            return [h("img", { attrs: { src: src }, style: { width: '40px', height: '40px',margin:'10px' } })];
    	        }
    	        var renderBoolean = function (h, v) {
    	            return v ? '是' : '否';
    	        }
    	        var renderCurrency = function (h, v) {
    	            if (!v) return "";
    	            return common.formatCurrency(v);
    	        }
    	        var renderPercentage = function (h, v) {
    	            if (!v) return "";
    	            return common.formatPercentage(v);
    	        }
    	        var renderNumberWithThousands = function (h, v) {
    	            if (!v) return "";
    	            return common.formatNumberWithThousands(v);
    	        }
    	        var renderDate = function (h, v) {
    	            if (!v) return "";
    	            v = v.replace("/Date(", "").replace(")/", "");
    	            if (v.indexOf("+") > 0) {
    	                v = v.substring(0, jsondate.indexOf("+"));
    	            }
    	            else if (v.indexOf("-") > 0) {
    	                v = v.substring(0, v.indexOf("-"));
    	            }
    	
    	            var date = new Date(parseInt(v, 10));
    	            return common.formatDateTime(date, "yyyy-MM-dd");
    	        }
    	        var renderDateTime = function (h, v) {
    	            if (!v) return "";
    	            v = v.replace("/Date(", "").replace(")/", "");
    	            if (v.indexOf("+") > 0) {
    	                v = v.substring(0, jsondate.indexOf("+"));
    	            }
    	            else if (v.indexOf("-") > 0) {
    	                v = v.substring(0, v.indexOf("-"));
    	            }
    	
    	            var date = new Date(parseInt(v, 10));
    	            return common.formatDateTime(date, "yyyy-MM-dd  hh:mm:ss");
    	        }
    	    </script>   
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
            data () { return { modelData: {} ,
            columnsList: [
                //{ type: 'selection', width: 55, align: 'center' },
	            //{ title: '【testpaperId】', render: (h, params) => { return params.row.testpaper.name } },
	            { title: '题目', render: (h, params) => { return params.row.question.name } },
	            { title: '分数', width: 80, key:'score' },
	        ],
	        dataList: [],
	        // 外键数据
	        testpaperIdSuggestDataList: [],
	        questionIdSuggestDataList: [],
	        pager: { PageNo: 1, PageSize: 10, ItemsCount: 0, PagesCount: 0 },
	        qry: { DoSearch: true },            
        } },
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
                    jsondate = jsondate.replace("/Date(", "").replace(")/", "");
                    if (jsondate.indexOf("+") > 0) {
                        jsondate = jsondate.substring(0, jsondate.indexOf("+"));
                    }
                    else if (jsondate.indexOf("-") > 0) {
                        jsondate = jsondate.substring(0, jsondate.indexOf("-"));
                    }

                    var date = new Date(parseInt(jsondate, 10));
                    return common.formatDateTime(date, "yyyy-MM-dd");
                },
                formatDateTime: function (jsondate) {
                    if (!jsondate) return "";
                    jsondate = jsondate.replace("/Date(", "").replace(")/", "");
                    if (jsondate.indexOf("+") > 0) {
                        jsondate = jsondate.substring(0, jsondate.indexOf("+"));
                    }
                    else if (jsondate.indexOf("-") > 0) {
                        jsondate = jsondate.substring(0, jsondate.indexOf("-"));
                    }

                    var date = new Date(parseInt(jsondate, 10));
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
                        url: 'testpaper/get'
                        , data: { id: entityId }
                        , cb: function (r) {
                            me.modelData = r;
                            console.log(me.modelData);
                        }
                    });
					common.callApi({
                         url: 'questionInTestpaper/search'
                         , data: {pageSize:10000, testpaperId:entityId}
                         , cb: function (r) {
                             me.dataList = r.records;
                         }
                     });                    
                    
                },


            }
        });
    })();

    </script>
 
    </body>
</html>  