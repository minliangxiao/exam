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
        <title>编辑考试结果信息</title>
        
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
            <tab-pane label="考试结果信息">
                <Form-item label="学生" prop="studentId">
                    <i-select v-model="modelData.studentId" placeholder="请选择学生">
                        <i-option v-for="item in studentIdSuggestDataList" :value="item.id" :key="item.id">{{ item.name }}</i-option>
                    </i-select>
                </Form-item>
                <Form-item label="【examId】" prop="examId">
                    <i-select v-model="modelData.examId" placeholder="请选择【examId】">
                        <i-option v-for="item in examIdSuggestDataList" :value="item.id" :key="item.id">{{ item.name }}</i-option>
                    </i-select>
                </Form-item>
                <Form-item label="老师" prop="teacherId">
                    <i-select v-model="modelData.teacherId" placeholder="请选择老师">
                        <i-option v-for="item in teacherIdSuggestDataList" :value="item.id" :key="item.id">{{ item.name }}</i-option>
                    </i-select>
                </Form-item>
                <Form-item label="时间" prop="time" label-width="120">
                    <date-picker type="date" v-model="modelData.time" placement="bottom" style="width:100%" placeholder="时间" format="yyyy年MM月dd日"></date-picker>
                </Form-item>
                <Form-item label="【rightQty】" prop="rightQty">
                    <i-input v-model="modelData.rightQty" placeholder="【rightQty】"></i-input>
                </Form-item>
                <!--
                <Form-item label="【rightQty】" prop="rightQty">
                    <i-input v-model="modelData.rightQty" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入【rightQty】..."></i-input>
                </Form-item>
                -->
                <Form-item label="【errorQty】" prop="errorQty">
                    <i-input v-model="modelData.errorQty" placeholder="【errorQty】"></i-input>
                </Form-item>
                <!--
                <Form-item label="【errorQty】" prop="errorQty">
                    <i-input v-model="modelData.errorQty" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入【errorQty】..."></i-input>
                </Form-item>
                -->
                <Form-item label="成绩" prop="score">
                    <i-input v-model="modelData.score" placeholder="成绩"></i-input>
                </Form-item>
                <!--
                <Form-item label="成绩" prop="score">
                    <i-input v-model="modelData.score" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入成绩..."></i-input>
                </Form-item>
                -->
                <Form-item label="评论" prop="comment">
                    <i-input v-model="modelData.comment" placeholder="评论"></i-input>
                </Form-item>
                <!--
                <Form-item label="评论" prop="comment">
                    <i-input v-model="modelData.comment" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入评论..."></i-input>
                </Form-item>
                -->
                <Form-item label="注册时间" prop="regTime" label-width="120">
                    <date-picker type="date" v-model="modelData.regTime" placement="bottom" style="width:100%" placeholder="注册时间" format="yyyy年MM月dd日"></date-picker>
                </Form-item>
                <Form-item>
                    <i-button type="primary" v-on:click="saveData">提交</i-button>
                    
                </Form-item>
            </tab-pane>
        </tabs>
    </i-form>
</div>
            <%--
                <!--使用UEditor的示例 开始-->
                <tab-pane label="商品描述">
                    <Form-item label="商品描述" prop="Remark">
                        <script id="body" type="text/plain" style="width:100%; height:320px;">
                        </script>
                    </Form-item>
                </tab-pane>
                <!--使用UEditor的示例 结束-->
                <tab-pane label="示例组件">
                    <Row>
                        <i-col span="12">
                            <Form-item label="多选" prop="mail">
                                <checkbox-group>
                                    <checkbox label="香蕉"></checkbox>
                                    <checkbox label="苹果"></checkbox>
                                    <checkbox label="西瓜"></checkbox>
                                </checkbox-group>
                            </Form-item>
                        </i-col>
                        <i-col span="12">
                            <Form-item label="单选" prop="mail">
                                <radio-group v-model="radioVal">
                                    <radio label="apple">
                                        <icon type="social-apple"></icon>
                                        <span>Apple</span>
                                    </radio>
                                    <radio label="android">
                                        <icon type="social-android"></icon>
                                        <span>Android</span>
                                    </radio>
                                    <radio label="windows">
                                        <icon type="social-windows"></icon>
                                        <span>Windows</span>
                                    </radio>
                                </radio-group>
                            </Form-item>
                        </i-col>
                    </Row>
                    <!--多图片上传的示例 开始-->
                    <Form-item label="多图片上传">
                        <div class="demo-upload-list" v-for="(item,index) in imgList">
                            <template>
                                <img v-bind:src="item">
                                <div class="demo-upload-list-cover">
                                    <icon type="ios-trash-outline" v-on:click.native="handleImgRemove(index)"></icon>
                                </div>
                            </template>
                            <!--<template v-else><i-progress v-if="item.showProgress" percent="item.percentage" hide-info></i-progress></template>-->
                        </div>
                        <upload ref="upload"
                                v-bind:show-upload-list="false"
                                v-bind:on-success="handleImgSuccess"
                                v-bind:format="['jpg','jpeg','png']"
                                v-bind:max-size="2048"
                                name="upfile"
                                multiple
                                type="drag"
                                action="/Ueditor/ControllerHandler.ashx?action=uploadimage&encode=utf-8"
                                style="display: inline-block;width:58px;">
                            <div style="width: 58px;height:58px;line-height: 58px;">
                                <icon type="camera" size="20"></icon>
                            </div>
                        </upload>
                    </Form-item>
                    <!--多图片上传的示例 结束-->
                    <!--自动完成的示例 开始-->
                    <Form-item label="自动完成">
                        <i-select v-model="searchVaule"
                                  filterable
                                  remote
                                  v-bind:loading="searchLoading"
                                  v-bind:remote-method="handleSearch">
                            <i-option v-for="(option, index) in searchDataList" v-bind:value="option.value" v-bind:key="index">{{option.label}}</i-option>
                        </i-select>
                    </Form-item>
                    <!--自动完成的示例 结束-->
                    <Form-item label="介绍" prop="desc">
                        <i-input v-model="modelData.desc" type="textarea" autosize="{minRows: 2,maxRows: 5}" placeholder="请输入..."></i-input>
                    </Form-item>
                </tab-pane>
            --%>
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
                    modelData: {id:entityId, is_deleted:0, status:'正常', url:'', pic:'', face:''},
                    studentIdSuggestDataList: [],
                    examIdSuggestDataList: [],
                    teacherIdSuggestDataList: [],
                    //radioVal: 'apple',
                    //radioList: [{ title: '阅读', ck: false }, { title: '运动', ck: true }, { title: '音乐', ck: false }],
                    //checkBoxList: [{ title: '阅读', ck: false }, { title: '运动', ck: true }, { title: '音乐', ck: false }],

                    //searchDataList: [],
                    //searchVaule: '',
                    //searchLoading: false,
                    postIng: false,
                    imgList: [],
                    //校验
                    ruleValidate: {
                        studentId: [{ trigger: 'change', required: true, message: '必须选择学生！' }],
                        examId: [{ trigger: 'change', required: true, message: '必须选择【examId】！' }],
                        time: [{ required: true, message: '必须填写时间！', trigger: 'change' }],
                        rightQty: [{ required: true, message: '必须填写【rightQty】！' }, { message: '请输入数字', pattern: numberPattern }],
                        errorQty: [{ required: true, message: '必须填写【errorQty】！' }, { message: '请输入数字', pattern: numberPattern }],
                        score: [{ required: true, message: '必须填写成绩！' }, { message: '请输入数字', pattern: numberPattern }],
                    },
                };
            },
            created: function () {
                var me = this;
                me.getStudentIdSuggestDataList();
                me.getExamIdSuggestDataList();
                me.getTeacherIdSuggestDataList();
                
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
                            url: "userExam/get"
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
                        getStudentIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "user/suggest"
                                , data: { keyword: "" }
                                , cb: function (r) {
                                    me.studentIdSuggestDataList = r;
                                }
                            });
                        },
                        getExamIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "exam/suggest"
                                , data: { keyword: "" }
                                , cb: function (r) {
                                    me.examIdSuggestDataList = r;
                                }
                            });
                        },
                        getTeacherIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "user/suggest"
                                , data: { keyword: "" }
                                , cb: function (r) {
                                    me.teacherIdSuggestDataList = r;
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
                                url: "userExam/save", data: me.modelData,
                                cb: function (r) {
                                    me.postIng = false;
                                    me.$Message.success('操作成功');
                                    parent.app.closeWin(true);
                                }
                            });
                        }
                    })
                },
                /*
                //商品图片上传成功回调
                handlePicSuccess (res, file, fileList) {
                    this.modelData.Pic = res.url;
                },
                //多图片实例移除图片
                handleImgRemove (index) {
                    this.imgList.splice(index, 1);
                },
                //多图片实例图片上传成功回调函数
                handleImgSuccess (res, file, fileList) {
                    console.log(res.url);
                    this.imgList.push(res.url);
                },
                自动完成框搜索
                handleSearch: function (query) {
                    //这里可以请求后台获取数据
                    if (query !== '') {
                        var listData = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New hampshire', 'New jersey', 'New mexico', 'New york', 'North carolina', 'North dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode island', 'South carolina', 'South dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West virginia', 'Wisconsin', 'Wyoming'];
                        this.searchLoading = true;
                        setTimeout(() => {
                            this.searchLoading = false;
                            const list = listData.map(item => {
                                return {
                                    value: item,
                                    label: item
                                };
                            });
                            this.searchDataList = list.filter(item => item.label.toLowerCase().indexOf(query.toLowerCase()) > -1);
                        }, 200);
                    } else {
                        this.searchDataList = [];
                    }
                }                
                */
            }
        });
    })();
</script>
    </body>
</html>  