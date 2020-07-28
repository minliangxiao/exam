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
        <title>参加考试</title>
        
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
		        <legend>参加考试</legend>
		    </fieldset>
		    <div style="margin-bottom:15px;">
    		    <!-- 
    	        <i-button type="info" v-on:click="editItem()">增 加</i-button>
    	        <i-button type="error" v-on:click="deleteItems()">删 除</i-button>
    	         -->
    	        <!--搜索框开始-->
                <div style="padding-top:5px;">
                    <!--<div class="layui-inline"></div>--><!--用这个DIV包住可以换行-->
                    <i-select v-model="qry.testpaperId" placeholder="试卷" style="width:200px;" clearable="true">
                        <i-option v-for="item in testpaperIdSuggestDataList" v-bind:value="item.id" v-bind:key="item.id">{{ item.name }}</i-option>
                    </i-select>
                    <i-input placeholder="名称" v-model="qry.name" style="width:200px;"></i-input>
                    <!-- 
                    <i-input placeholder="开始时间" v-model="qry.startTime" style="width:200px;"></i-input>
                    <i-input placeholder="开始时间从" v-model="qry.startTimeFrom" style="width:200px;"></i-input>
                    <i-input placeholder="到" v-model="qry.startTimeTo" style="width:200px;"></i-input>
                    <i-input placeholder="【totalStudentQty】" v-model="qry.totalStudentQty" style="width:200px;"></i-input>
                    <i-input placeholder="【totalStudentQty】从" v-model="qry.totalStudentQtyFrom" style="width:200px;"></i-input>
                    <i-input placeholder="到" v-model="qry.totalStudentQtyTo" style="width:200px;"></i-input>
                    <i-input placeholder="【examStudentQty】" v-model="qry.examStudentQty" style="width:200px;"></i-input>
                    <i-input placeholder="【examStudentQty】从" v-model="qry.examStudentQtyFrom" style="width:200px;"></i-input>
                    <i-input placeholder="到" v-model="qry.examStudentQtyTo" style="width:200px;"></i-input>
                    <i-input placeholder="【endTime】" v-model="qry.endTime" style="width:200px;"></i-input>
                    <i-input placeholder="【endTime】从" v-model="qry.endTimeFrom" style="width:200px;"></i-input>
                    <i-input placeholder="到" v-model="qry.endTimeTo" style="width:200px;"></i-input>
                    <i-input placeholder="【finishStudentQty】" v-model="qry.finishStudentQty" style="width:200px;"></i-input>
                    <i-input placeholder="【finishStudentQty】从" v-model="qry.finishStudentQtyFrom" style="width:200px;"></i-input>
                    <i-input placeholder="到" v-model="qry.finishStudentQtyTo" style="width:200px;"></i-input>
                    <i-select v-model="qry.status" placeholder="请选择状态" style="width:130px;" clearable="true">
                        <i-option value="">全部</i-option>
                        <i-option value="未审核">未审核</i-option>
                        <i-option value="正常">正常</i-option>
                    </i-select>
                    <i-input placeholder="【totalScore】" v-model="qry.totalScore" style="width:200px;"></i-input>
                    <i-input placeholder="【totalScore】从" v-model="qry.totalScoreFrom" style="width:200px;"></i-input>
                    <i-input placeholder="到" v-model="qry.totalScoreTo" style="width:200px;"></i-input>
                    <i-input placeholder="【avgScore】" v-model="qry.avgScore" style="width:200px;"></i-input>
                    <i-input placeholder="【avgScore】从" v-model="qry.avgScoreFrom" style="width:200px;"></i-input>
                    <i-input placeholder="到" v-model="qry.avgScoreTo" style="width:200px;"></i-input>
                    <i-input placeholder="类型" v-model="qry.type" style="width:200px;"></i-input>
                    <i-input placeholder="【publicAnswer】" v-model="qry.publicAnswer" style="width:200px;"></i-input>
                     -->
                    <i-button type="ghost" icon="ios-search" v-on:click="getData()">搜 索</i-button>
    		        <!-- <i-button type="info" v-on:click="editItem()">增 加</i-button> -->
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
                            //{ title: '考试', render: (h, params) => { return params.row.exam.name } },
                            { title: '考试', render: (h, params) => { return params.row.name } },
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
                                        }, '参 加'),
                                    ];
                                },
                            }
                        ],
                        dataList: [],
                        // 外键数据
                            testpaperIdSuggestDataList: [],
                        pager: { PageNo: 1, PageSize: 10, ItemsCount: 0, PagesCount: 0 },
                        qry: { DoSearch: true, unexamOnly:true, status:'未参加' },
                        editModal: false,
                    },
                    created: function () {
                        var me = this;
                        //日期控件初始化
                        layui.use(['layer', 'form', 'laydate', 'element']);
                        this.getData();
                        // 外键 - 下拉框
                        me.getTestpaperIdSuggestDataList();
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
                                url: 'exam/search'		// url: 'userExam/search'
                                , data: {unexamOnly:true}
                                , cb: function (r) {
                                    me.dataList = r.records;
                                    me.pager = { PageNo: r.current, PageSize: r.size, ItemsCount: r.total, PagesCount: r.pages };
                                }
                            });
                        },
                        getTestpaperIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "testpaper/suggest"
                                , data: { keyword: "" }
                                , cb: function (r) {
                                    me.testpaperIdSuggestDataList = r;
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
                            var url = "exam/edit";
                            if (item)
                                url = url + "?id=" + item.id;
                            this.openWin(url);
                        },
                        displayItem: function (item) {
                            //this.openWin("exam/show?id=" + item.id);
                            layer.confirm('确认要参加考试吗？', { icon: 2, title: '提示' }, function (index) {
                                layer.close(index);
	                            window.location.href="exam/exam?id=" + item.id;
                            });
                        },
                        //删除数据
                        deleteItem: function (item) {
                            var me = this;
                            layer.confirm('删除后将无法恢复，确认要删除吗？', { icon: 2, title: '提示' }, function (index) {
                                common.callApi({
                                    url: "exam/delete"
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
                                    url: "exam/delete"
                                    , data: { ids: selectedItems }
                                    , cb: function (r) {
                                        me.getData();
                                        me.$Message.success('操作成功');
                                    }
                                });
                                layer.close(index);
                            });
                        }
                    }
                });
            })();
	    </script>
    </body>
</html>  