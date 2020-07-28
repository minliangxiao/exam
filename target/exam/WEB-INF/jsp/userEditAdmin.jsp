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
        <title>编辑超级管理员用户信息</title>
        
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
            <tab-pane label="超级管理员用户信息">
                <Form-item label="校区" prop="schoolId">
                    <i-select v-model="modelData.schoolId" placeholder="请选择校区">
                        <i-option v-for="item in schoolIdSuggestDataList" :value="item.id" :key="item.id">{{ item.name }}</i-option>
                    </i-select>
                </Form-item>
                <Form-item label="名称" prop="name">
                    <i-input v-model="modelData.name" placeholder="名称"></i-input>
                </Form-item>
                <Form-item label="密码" prop="password">
                    <i-input v-model="modelData.password" placeholder="密码"></i-input>
                </Form-item>
                <Form-item label="姓名" prop="realname">
                    <i-input v-model="modelData.realname" placeholder="姓名"></i-input>
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
                    modelData: {id:entityId, is_deleted:0, url:'', pic:'', face:'', role:'管理员'},
                    schoolIdSuggestDataList: [],
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
                        //schoolId: [{ trigger: 'change', required: true, message: '必须选择学校！' }],
                        name: [{ required: true, message: '必须填写名称！', trigger: 'change' }],
                        password: [{ required: true, message: '必须填写密码！', trigger: 'change' }],
                        //role: [{ required: true, message: '必须填写身份！', trigger: 'change' }],
                    },
                };
            },
            created: function () {
                var me = this;
                me.getSchoolIdSuggestDataList();
                
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
                            url: "user/get"
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
                        getSchoolIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "school/suggest"
                                , data: { keyword: "" }
                                , cb: function (r) {
                                    me.schoolIdSuggestDataList = r;
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
                                url: "user/save", data: me.modelData,
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