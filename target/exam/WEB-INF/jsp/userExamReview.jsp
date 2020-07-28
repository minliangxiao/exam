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
        <title>批改试卷</title>
        
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
		            <tab-pane label="考试结果">
                <Row>
                    <i-col span="24">
                        <Form-item label="学生"><span>{{modelData.student.realname}}</span></Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Form-item label="考试"><span>{{modelData.exam.name}}</span></Form-item>
                    </i-col>
                </Row>
                <!-- 
                <Row>
                    <i-col span="24">
                        <Form-item label="老师"><span>{{modelData.teacher.name}}</span></Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Form-item label="时间"><span>{{modelData.time|renderDateTime}}</span></Form-item>
                    </i-col>
                </Row>
                 -->
                <Row>
                    <i-col span="24">
                        <Form-item label="正确数量"><span>{{modelData.rightQty}}</span></Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Form-item label="错题数量"><span>{{modelData.errorQty}}</span></Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Form-item label="成绩"><span>{{modelData.score}}</span></Form-item>
                    </i-col>
                </Row>
              
                <!-- 
                <Row>
                    <i-col span="24">
                        <Form-item label="状态"><span>{{modelData.status}}</span></Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Form-item label="报考时间"><span>{{modelData.regTime|formatDate}}</span></Form-item>
                    </i-col>
                </Row>
                 -->
		            </tab-pane>
		        </tabs>
		    </i-form>
		    <i-table border ref="selection" v-bind:columns="columnsList" v-bind:data="dataList"></i-table>
		                <modal v-model="editModal" v-bind:width="600">
                <iframe style="width:100%; border-width:0px; height:400px;" id="editFrame"></iframe>
                <div slot="footer"></div>
            </modal>
		</div>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/libs/layui/layui.js"></script>
        <script type="text/javascript" src="js/libs/vue.min.js"></script>
        <script type="text/javascript" src="js/libs/iview/iview.min.js"></script>
        <script type="text/javascript" src="js/common/common.js"></script>
		<script type="text/javascript" src="js/common/components/pagination.js"></script>
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
    	
    	            var date = new Date(parseInt(v, 10));
    	            return common.formatDateTime(date, "yyyy-MM-dd");
    	        }
    	        var renderDateTime = function (h, v) {
    	            if (!v) return "";
    	
    	            var date = new Date(parseInt(v, 10));
    	            return common.formatDateTime(date, "yyyy-MM-dd  hh:mm:ss");
    	        }
    	    </script>   		
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
            data () { return { modelData: {}, 
                            columnsList: [
                                //{ type: 'selection', width: 55, align: 'center' },
//                            { title: '【userexamId】', render: (h, params) => { return params.row.userexam.name } },
                            { title: '试题', render: (h, params) => { return params.row.question.name } },
                            { title: '得分', render: (h, params) => { return renderNumberWithThousands(h, params.row.score); } },
                            { title: '答案', key: 'answer' },
                            { title: '正确/参考答案', key: 'correctAnswer' },
                            { title: '是否正确', render: (h, params) => { return renderBoolean(h, params.row.isRight); } },
//                            { title: '是否标注', render: (h, params) => { return renderNumberWithThousands(h, params.row.isMark); } },
//                            { title: 'totalQty总数', render: (h, params) => { return renderNumberWithThousands(h, params.row.totalQty); } },
//                            { title: '答题数量', render: (h, params) => { return renderNumberWithThousands(h, params.row.answerQty); } },
                            { title: '解析', key: 'explainText' },
                            {
                                title: '操作', align: 'center', width: 320,
                                render: (h, params) => {
                                    return [
                                    /*
                                        h("Button", {
                                            props: { type: 'ghost', }, on: {
                                                click: () => {
                                                    app.displayItem(params.row);
                                                }
                                            }
                                        }, '查 看'),
                                        */
                                        h("Button", {
                                            props: { type: 'info' }, on: {
                                                click: () => {
                                                    app.editItem(params.row)
                                                }
                                            }, style: { marginLeft: '5px' }
                                        }, '批 改'),
                                    ];
                                },
                            }                            
                        ],
                        dataList: [],           
                        editModal: false,
                        }; 
                        }, 
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
                        url: 'userExam/get'
                        , data: { id: entityId }
                        , cb: function (r) {
                            me.modelData = r;
                            console.log(me.modelData);
                        }
                    });
                    common.callApi({
                        url: 'userExamQuestion/search'
                        , data: {userexamId:entityId, pageNo:1, pageSize:1000}
                        , cb: function (r) {
                            me.dataList = r.records;
                            //me.pager = { PageNo: r.current, PageSize: r.size, ItemsCount: r.total, PagesCount: r.pages };
                        }
                    });                    
                },
                  openWin: function (url) {
                            $("#editFrame").attr("src", url);
                            this.editModal = true;
                        },
                        closeWin: function (isLoadData) {
                            this.editModal = false;
                            if (isLoadData == true)
                                this.getData();
                        },
                        
          editItem: function (item) {
                            var url = "userExamQuestion/edit";
                            if (item)
                                url = url + "?id=" + item.id;
                            this.openWin(url);
                        },
                        displayItem: function (item) {
                            this.openWin("userExam/show?id=" + item.id);
                        },
                
            }
        });
    })();
    </script>
 
    </body>
</html>  