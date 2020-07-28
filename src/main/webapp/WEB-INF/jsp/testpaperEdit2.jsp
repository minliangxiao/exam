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
        <title>随机组卷</title>
        
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
            <tab-pane label="试卷信息">
                <Form-item label="年级" prop="gradeId">
                    <i-select v-model="modelData.gradeId" placeholder="请选择年级" filterable="true"  clearable="true">
                        <i-option v-for="item in gradeIdSuggestDataList" :value="item.id" :key="item.id">{{ item.name }}</i-option>
                    </i-select>
                </Form-item>            
                <Form-item label="试题类型" prop="subjectId">
                    <i-select v-model="modelData.subjectId" placeholder="请选择试题类型"  @on-change="subjectChange"  filterable="true" clearable="true">
                        <i-option v-for="item in subjectIdSuggestDataList" :value="item.id" :key="item.id">{{ item.name }}</i-option>
                    </i-select>
                </Form-item>
                <Form-item label="编号" prop="code">
                    <i-input v-model="modelData.code" placeholder="编号"></i-input>
                </Form-item>
                <Form-item label="名称" prop="name">
                    <i-input v-model="modelData.name" placeholder="名称"></i-input>
                </Form-item>
                <Form-item label="分钟数" prop="minutes">
                    <i-input v-model="modelData.minutes" placeholder="分钟数"></i-input>
                </Form-item>
                <row>
                	<i-col span="12">
		                <Form-item label="选择题数量" prop="qty1">
		                    <i-input v-model="modelData.qty1" placeholder="选择题数量"></i-input>
		                </Form-item>
                	</i-col>
                	<i-col span="12">
		                <Form-item label="选择题分数" prop="score1">
		                    <i-input v-model="modelData.score1" placeholder="选择题分数"></i-input>
		                </Form-item>
                	</i-col>
                </row>
                <row>
                	<i-col span="12">
		                <Form-item label="填空题数量" prop="qty2">
		                    <i-input v-model="modelData.qty2" placeholder="填空题数量"></i-input>
		                </Form-item>
                	</i-col>
                	<i-col span="12">
		                <Form-item label="填空题分数" prop="score2">
		                    <i-input v-model="modelData.score2" placeholder="填空题分数"></i-input>
		                </Form-item>
                	</i-col>
                </row>
                <row>
                	<i-col span="12">
		                <Form-item label="判断题数量" prop="qty3">
		                    <i-input v-model="modelData.qty3" placeholder="判断题数量"></i-input>
		                </Form-item>
                	</i-col>
                	<i-col span="12">
		                <Form-item label="判断题分数" prop="score3">
		                    <i-input v-model="modelData.score3" placeholder="判断题分数"></i-input>
		                </Form-item>
                	</i-col>
                </row>
              
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
                    modelData: {id:entityId, is_deleted:0, url:'', pic:'', face:'', totalScore:0, subjectId:0, gradeId:0,
                    	qty1:0, score1:0, qty2:0, score2:0, qty3:0, score3:0, qty4:0, score4:0, qty5:0, score5:0},
                    subjectIdSuggestDataList: [],
                    gradeIdSuggestDataList:[],
            columnsList: [
                //{ type: 'selection', width: 55, align: 'center' },
            //{ title: '【testpaperId】', render: (h, params) => { return params.row.testpaper.name } },
            { title: '题目', render: (h, params) => { return params.row.question.name } },
            { title: '分数', width: 80, key:'score' },
            {
                title: '操作', align: 'center', width: 100,
                render: (h, params) => {
                    return [
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
                    
        removedList:[],
        addedList:[],
        scoreList:[],
        // 外键数据
        questionIdSuggestDataList: [],
        score:0,
        questionId:null,
                    postIng: false,
                    imgList: [],
                    //校验
                    ruleValidate: {
                        //subjectId: [{ trigger: 'change', required: true, message: '必须选择课程！' }],
                        name: [{ required: true, message: '必须填写名称！', trigger: 'change' }],
                        minutes: [{ required: true, message: '必须填写分钟数！' }, { message: '请输入数字', pattern: numberPattern }],
                        //totalScore: [{ required: true, message: '必须填写总分数！' }, { message: '请输入数字', pattern: numberPattern }],
                    },
                };
            },
            created: function () {
                var me = this;
                me.getSubjectIdSuggestDataList();
                me.getQuestionIdSuggestDataList();
                me.getGradeIdSuggestDataList();
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
            subjectChange:function(v){
            	this.getQuestionIdSuggestDataList();
            },
                getData: function () {
                    var me = this;
                    //this.postIng = true;
                    if(entityId>0) {
                        common.callApi({
                            url: "testpaper/get"
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
						common.callApi({
	                         url: 'questionInTestpaper/search'
	                         , data: {pageSize:10000, testpaperId:entityId}
	                         , cb: function (r) {
	                             me.dataList = r.records;
	                         }
	                     });     
						common.callApi({
	                         url: 'question/search'
	                         , data: {pageSize:10000, subjectId: me.modelData.subjectId}
	                         , cb: function (r) {
	                             me.questionIdSuggestDataList = r.records;
	                         }
	                     });     	                     
	                     //me.getQuestionIdSuggestDataList();  
					    me.getSubjectIdSuggestDataList();  
                    }
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
                       	getQuestionIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "question/suggest"
                                , data: { keyword: "", subjectId: me.modelData.subjectId }
                                , cb: function (r) {
                                    me.questionIdSuggestDataList = r;
                                }
                            });
                        },
                          getGradeIdSuggestDataList: function () {
                            var me = this;
                            common.callApi({
                                url: "grade/suggest"
                                , data: { keyword: "" }
                                , cb: function (r) {
                                    me.gradeIdSuggestDataList = r;
                                }
                            });
                        },
                //保存数据
                saveData: function () {
                    var me = this;
                    if((me.modelData.qty1 * me.modelData.score1 + me.modelData.qty2 * me.modelData.score2 + me.modelData.qty3 * me.modelData.score3 + 
                    	me.modelData.qty4 * me.modelData.score4 + me.modelData.qty5 * me.modelData.score5) != 100){
                    	me.$Message.success('试卷总分必须等于100！');
                    	return;
                   	}
                    this.$refs.modelData.validate((valid) => {
                        if (valid) {
                            this.postIng = true;

                            common.callApi({
                                url: "testpaper/save2", data: me.modelData,
                                cb: function (r) {
                                    me.postIng = false;
                                    me.$Message.success('操作成功');
                                    parent.app.closeWin(true);
                                }
                            });
                        }
                    })
                },
                //删除问题
                deleteItem: function (item) {
                    var me = this;
                    var itemId = item.id;
                    var questionId = item.questionId;
                    me.removedList.push(itemId);
					removeItem(me.dataList, item);
					var idx = getIndex2(me.addedList, questionId);
					removeItemAtIndex(me.addedList, idx);
					removeItemAtIndex(me.scoreList, idx);
					me.modelData.totalScore -= (item.score * 1);
                },
                addItem:function(){
                	var me = this;
                	var qid = me.questionId;
                	if(qid<=0) return;
                    common.callApi({
                        url: 'question/get'
                        , data: { id: qid }
                        , cb: function (r) {
                            console.log(r);
                            me.dataList.push({id:Math.random(), questionId:r.id, score:me.score, question:{name:r.name}});
                            me.modelData.totalScore += (me.score * 1);
		                	me.addedList.push(qid);
		                	me.scoreList.push(me.score);
                            me.score = 0;
                            //me.questionId=null;
                        }
                    });                	
                }
                
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
    		if(arr[i]==ele) {
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