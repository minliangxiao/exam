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
        <title>批改试题答案</title>
        
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
		</style>
        <!-- EDIT -->
    </head>
    <body>

<div style="padding: 10px;" id="app">
    <i-form ref="modelData" v-bind:model="modelData" v-bind:rules="ruleValidate" label-width="80">
        <tabs>
            <tab-pane label="批改试题答案">
                <Form-item label="成绩" prop="score">
                    {{modelData.question.name}}
                </Form-item>
                <Form-item label="成绩" prop="score">
                    <i-input v-model="modelData.score" placeholder="成绩"></i-input>
                </Form-item>
                <!--
                <Form-item label="成绩" prop="score">
                    <i-input v-model="modelData.score" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入成绩..."></i-input>
                </Form-item>
                -->
                <Form-item label="正确答案" prop="correctAnswer">
                    <i-input v-model="modelData.correctAnswer" placeholder="正确答案"></i-input>
                </Form-item>
                <!--
                <Form-item label="正确答案" prop="correctAnswer">
                    <i-input v-model="modelData.correctAnswer" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入正确答案..."></i-input>
                </Form-item>
                -->
                <Form-item label="答案" prop="answer">
                    <i-input v-model="modelData.answer" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="未填写答案" disabled></i-input>
                </Form-item>
                <Form-item label="正确" prop="isRight">
                              <i-switch size="large" v-model="modelData.isRight">
                                <span slot="open">是</span>
                                <span slot="close">否</span>
                            </i-switch>
                </Form-item>
                <Form-item label="解析" prop="explainText">
                    <i-input v-model="modelData.explainText" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入解析..."></i-input>
                </Form-item>
                <Form-item>
                    <i-button type="primary" v-on:click="saveData">提交</i-button>
                    
                </Form-item>
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

    var app;
    (function () {
        //var bodyUe = UE.getEditor('body');
        app = new Vue({
            el: "#app",
            data () {
                var numberPattern = /^(\-)?\d+(\.\d{1,2})?$/;
                return {
                    modelData: {id:entityId, is_deleted:0, url:'', pic:'', face:''},
                    questionIdSuggestDataList: [],
                    userexamIdSuggestDataList: [],
                    postIng: false,
                    imgList: [],
                    //校验
                    ruleValidate: {
                        //questionId: [{ trigger: 'change', required: true, message: '必须选择试题！' }],
                        //userexamId: [{ trigger: 'change', required: true, message: '必须选择【userexamId】！' }],
                        /*
                        score: [{ required: true, message: '必须填写成绩！' }, { message: '请输入数字', pattern: numberPattern }],
                        isRight: [{ required: true, message: '必须填写是否正确！' }, { message: '请输入数字', pattern: numberPattern }],
                        isMark: [{ required: true, message: '必须填写是否标注！' }, { message: '请输入数字', pattern: numberPattern }],
                        totalQty: [{ required: true, message: '必须填写奖品总数！' }, { message: '请输入数字', pattern: numberPattern }],
                        answerQty: [{ required: true, message: '必须填写答题数量！' }, { message: '请输入数字', pattern: numberPattern }],
                        */
                    },
                };
            },
            created: function () {
                var me = this;
                me.getQuestionIdSuggestDataList();
                me.getUserexamIdSuggestDataList();
                
                if (entityId) {
                    me.getData();
                    // 使用 UEditor 时，使用以下代码。
                    //bodyUe.ready(function () {
                    //     me.getData();
                    //});
                    // 使用 UEditor 时，使用以上代码。
                }
            },
            methods: {
                getData: function () {
                    var me = this;
                    //this.postIng = true;
                    if(entityId>0) {
                        common.callApi({
                            url: "userExamQuestion/get"
                            , data: { id: entityId }
                            , cb: function (r) {
                                me.modelData = r;
                                // 日期类型要加以下代码。
                                //me.modelData.ExpiredDate = common.formatJsonDateTime(r.ExpiredDate, "yyyy-MM-dd");
                                // 使用 UEditor 时，编辑的字段要加以下代码。
                                //if (me.modelData.Remark) {
                                //    bodyUe.setContent(me.modelData.Remark);
                                //}
                                // 使用 UEditor 时，编辑的字段要加以上代码。
                            }
                        });
                    }
                },
                        getQuestionIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "question/suggest"
                                , data: { keyword: "" }
                                , cb: function (r) {
                                    me.questionIdSuggestDataList = r;
                                }
                            });
                        },
                        getUserexamIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "userExam/suggest"
                                , data: { keyword: "" }
                                , cb: function (r) {
                                    me.userexamIdSuggestDataList = r;
                                }
                            });
                        },
                //保存数据
                saveData: function () {
                    var me = this;
                    // UEditor 用以下代码
                    //this.modelData.Remark = bodyUe.getContent();
                    //console.log(typeof(me.modelData.ExpiredDate));
                    this.$refs.modelData.validate((valid) => {
                        if (valid) {
                            this.postIng = true;
                            // 日期类型要加以下代码。
                            //me.modelData.ExpiredDate = common.formatDateTime(me.modelData.ExpiredDate, "yyyy-MM-dd");
                            common.callApi({
                                url: "userExamQuestion/review", data: me.modelData,
                                cb: function (r) {
                                    me.postIng = false;
                                    me.$Message.success('操作成功');
                                    parent.app.closeWin(true);
                                }
                            });
                        }
                    })
                },
            }
        });
    })();
</script>
    </body>
</html>  