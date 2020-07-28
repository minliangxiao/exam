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
        <title>试题库管理</title>
        
        <link rel="stylesheet" type="text/css" href="js/libs/layui/css/layui.css">
        <link rel="stylesheet" type="text/css" href="js/libs/iview/iview.css">
        <!-- LIST  -->
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
		</style>
        <!-- LIST -->
    </head>
    <body>
		<div style="padding: 10px;" id="app">
		    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		        <legend>试题库管理</legend>
		    </fieldset>
		    <div style="margin-bottom:15px;">
    		    <!-- 
    	        <i-button type="info" v-on:click="editItem()">增 加</i-button>
    	        <i-button type="error" v-on:click="deleteItems()">删 除</i-button>
    	         -->
    	        <!--搜索框开始-->
                <div style="padding-top:5px;">
                    <!--<div class="layui-inline"></div>--><!--用这个DIV包住可以换行-->
<!-- 
                    <i-select v-model="qry.userId" placeholder="请选择用户" style="width:200px;" clearable="true">
                        <i-option v-for="item in userIdSuggestDataList" v-bind:value="item.Id" v-bind:key="item.Id">{{ item.name }}</i-option>
                    </i-select>
                     -->

                    <i-select v-model="qry.subjectId" placeholder="请选择试题类型" style="width:200px;" clearable="true">
                        <i-option v-for="item in subjectIdSuggestDataList" v-bind:value="item.id" v-bind:key="item.id">{{ item.name }}</i-option>
                    </i-select>
                    <i-input placeholder="题目" v-model="qry.name" style="width:200px;"></i-input>
                    <i-select v-model="qry.type" placeholder="类型"style="width:200px;">
                        <i-option value="选择题" >选择题</i-option>
                        <i-option value="填空题" >填空题</i-option>
                        <i-option value="判断题" >判断题</i-option>

                    </i-select>
<!--                     
                    <i-input placeholder="【a】" v-model="qry.a" style="width:200px;"></i-input>
                    <i-input placeholder="【b】" v-model="qry.b" style="width:200px;"></i-input>
                    <i-input placeholder="【c】" v-model="qry.c" style="width:200px;"></i-input>
                    <i-input placeholder="【d】" v-model="qry.d" style="width:200px;"></i-input>
                    <i-input placeholder="【correct】" v-model="qry.correct" style="width:200px;"></i-input>
                    <i-input placeholder="成绩" v-model="qry.score" style="width:200px;"></i-input>
                    <i-input placeholder="成绩从" v-model="qry.scoreFrom" style="width:200px;"></i-input>
                    <i-input placeholder="到" v-model="qry.scoreTo" style="width:200px;"></i-input>
                    <i-input placeholder="解析" v-model="qry.explainText" style="width:200px;"></i-input>
 -->
                    <i-button type="ghost" icon="ios-search" v-on:click="getData()">搜 索</i-button>
    		        <i-button type="info" v-on:click="editItem()">增 加</i-button>
                </div>
                <div style="padding-top:5px;">
                <upload ref="upload"
                            v-bind:show-upload-list="false"
                            v-bind:on-success="fileUploadSuccess"
                            v-bind:format="['csv']"
                            v-bind:max-size="2048000000"
                            name="file"
                            type="drag"
                            action="question/upload"
                            style="width:200px;">
                        <div style="width: 200px;">
                            <span>文件上传</span>
                        </div>
                    </upload>
                </div>
                <!--搜索框结束-->
            </div>
            <i-table border ref="selection" v-bind:columns="columnsList" v-bind:data="dataList"></i-table>
            <div style="float:right; margin-top:15px;">
                <page v-bind:total="pager.ItemsCount" v-bind:page-size="pager.PageSize" v-bind:page-size-opts="[10,20,30]" v-on:on-change="getData" v-on:on-page-size-change="pageSizeChange" show-total show-sizer></page>
            </div>
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
    		<!-- LIST COMMON -->
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
    	    <!-- LIST COMMON -->   
            <script type="text/javascript">
                //layui.use(['layer']);
                var app;
                (function () {
                    app = new Vue({
                        el: "#app",
                        data: {
                            columnsList: [
                                //{ type: 'selection', width: 55, align: 'center' },
                            //{ title: '老师', render: (h, params) => { return params.row.user.name } },
                            { title: '课程', render: (h, params) => { return params.row.subject.name } },
                            { title: '类型', key: 'type' },
                            { title: '题目', key: 'name' },
//                            { title: '【a】', key: 'a' },
//                            { title: '【b】', key: 'b' },
//                            { title: '【c】', key: 'c' },
//                            { title: '【d】', key: 'd' },
//                            { title: '【correct】', key: 'correct' },
//                            { title: '成绩', render: (h, params) => { return renderNumberWithThousands(h, params.row.score); } },
//                            { title: '解析', key: 'explainText' },
                            {
                                title: '操作', align: 'center', width: 320,
                                render: (h, params) => {
                                    return [
                                        h("Button", {
                                            props: { type: 'ghost', }, on: {
                                                click: () => {
                                                    app.displayItem(params.row);
                                                }
                                            }
                                        }, '查 看'),
                                        h("Button", {
                                            props: { type: 'info' }, on: {
                                                click: () => {
                                                    app.editItem(params.row)
                                                }
                                            }, style: { marginLeft: '5px' }
                                        }, '编 辑'),
                                        h("Button", {
                                            props: { type: 'error' }, on: {
                                                click: () => {
                                                    app.deleteItem(params.row)
                                                }
                                            }, style: { marginLeft: '5px' }
                                        }, '删 除'),
                                    ];
                                },
                            }
                        ],
                        dataList: [],
                        // 外键数据
                            userIdSuggestDataList: [],
                            subjectIdSuggestDataList: [],
                        pager: { PageNo: 1, PageSize: 10, ItemsCount: 0, PagesCount: 0 },
                        qry: { DoSearch: true },
                        editModal: false,
                    },
                    created: function () {
                        var me = this;
                        //日期控件初始化
                        layui.use(['layer', 'form', 'laydate', 'element']);
                        this.getData();
                        // 外键 - 下拉框
                        me.getUserIdSuggestDataList();
                        me.getSubjectIdSuggestDataList();
                    },
                    methods: {
                        getData: function (pageNo) {
                            var me = this;
                            if (pageNo > this.pager.PagesCount) {
                                return;
                            }
                            if (pageNo) {
                                this.pager.PageNo = pageNo;
                            }
                            this.qry.pageNo = this.pager.PageNo;
                            this.qry.pageSize = this.pager.PageSize;

                            // 日期范围的特殊处理。

                            common.callApi({
                                url: 'question/search'
                                , data: this.qry
                                , cb: function (r) {
                                    me.dataList = r.records;
                                    me.pager = { PageNo: r.current, PageSize: r.size, ItemsCount: r.total, PagesCount: r.pages };
                                }
                            });
                        },
                        getUserIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "user/suggest"
                                , data: { keyword: "" }
                                , cb: function (r) {
                                    me.userIdSuggestDataList = r;
                                }
                            });
                        },
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
                        openWin: function (url) {
                            $("#editFrame").attr("src", url);
                            this.editModal = true;
                        },
                        closeWin: function (isLoadData) {
                            this.editModal = false;
                            if (isLoadData == true)
                                this.getData();
                        },
                        //分页回调函数
                        pageSizeChange: function (pageSize) {
                            this.pager.PageSize = pageSize;
                            this.getData(1);
                        },
                        editItem: function (item) {
                            var url = "question/edit";
                            if (item)
                                url = url + "?id=" + item.id;
                            this.openWin(url);
                        },
                        displayItem: function (item) {
                            this.openWin("question/show?id=" + item.id);
                        },
                        //删除数据
                        deleteItem: function (item) {
                            var me = this;
                            layer.confirm('删除后将无法恢复，确认要删除吗？', { icon: 2, title: '提示' }, function (index) {
                                common.callApi({
                                    url: "question/delete"
                                    , data: { id: item.id }
                                    , cb: function (r) {
                                        me.getData();
                                        me.$Message.success('操作成功');
                                    }
                                });
                                layer.close(index);
                            });
                        },
                        //删除数据
                        deleteItems: function () {
                            var result = this.$refs.selection.getSelection();
                            if (result.length == 0) {
                                this.$Message.error('请选择删除项');
                                return;
                            }
                            var selectedItems = [];
                            for (var i = 0, len = result.length; i < len; i++) {
                                selectedItems.push(result[i].Id);
                            }
                            var me = this;
                            layer.confirm('删除后将无法恢复，确认要删除吗？', { icon: 2, title: '提示' }, function (index) {
                                common.callApi({
                                    url: "question/delete"
                                    , data: { ids: selectedItems }
                                    , cb: function (r) {
                                        me.getData();
                                        me.$Message.success('操作成功');
                                    }
                                });
                                layer.close(index);
                            });
                        },
                        fileUploadSuccess (res, file, fileList) {
		                    this.getData();
		                },
                    }
                });
            })();
	    </script>
    </body>
</html>  