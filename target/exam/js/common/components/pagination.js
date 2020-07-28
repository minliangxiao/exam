Vue.component('pagination', {
    template: 
                    '<div class="layui-box layui-laypage layui-laypage-default" style="float:right">' +
                      '<li class="disabled" style="float:left;"><a>共 {{total}} 条记录</a></li>' +
                      '<a href="javascript:;" class="layui-laypage-prev" :class="{\'layui-disabled\': current == 1}" @click="setCurrent(current - 1)"><em>←</em></a>' +
                      '<a href="javascript:;" class="layui-laypage-first" :class="{\'layui-disabled\': current == 1}" title="首页" @click="setCurrent(1)">首页</a>' +
                      '<template v-for="p in grouplist">' +
                        '<a href="javascript:;"  v-if="current != p.val" @click="setCurrent(p.val)"> {{ p.text }} </a>' +
                        '<span class="layui-laypage-curr"  v-else><em class="layui-laypage-em"></em><em>{{ p.text }}</em></span>' +
                      '</template>'+
                      
                      '<a href="javascript:;" class="layui-laypage-last" :class="{\'layui-disabled\': current == page}" title="尾页" @click="setCurrent(page)">尾页</a>' +
                     '<a href="javascript:;" class="layui-laypage-next" :class="{\'layui-disabled\': current == page}" @click="setCurrent(current + 1)"><em>→</em></a>' +
                    '</div>',
    data: function () {
        return {
            current: this.currentPage
        }
    },
    props: {
        total: {// 数据总条数
            type: Number,
            default: 0
        },
        display: {// 每页显示条数
            type: Number,
            default: 10
        },
        currentPage: {// 当前页码
            type: Number,
            default: 1
        },
        pagegroup: {// 分页条数
            type: Number,
            default: 5,
            coerce: function (v) {
                v = v > 0 ? v : 5;
                return v % 2 === 1 ? v : v + 1;
            }
        }
    },
    computed: {
        page: function () { // 总页数
            return Math.ceil(this.total / this.display);
        },
        grouplist: function () { // 获取分页页码
            var len = this.page, temp = [], list = [], count = Math.floor(this.pagegroup / 2), center = this.current;

            if (len <= this.pagegroup) {
                while (len--) {
                    temp.push({ text: this.page - len, val: this.page - len });
                }
                ;
                return temp;
            }
            while (len--) {
                temp.push(this.page - len);
            }
            ;
            var idx = temp.indexOf(center);
            (idx < count) && (center = center + count - idx);
            (this.current > this.page - count) && (center = this.page - count);
            temp = temp.splice(center - count - 1, this.pagegroup);
            do {
                var t = temp.shift();
                list.push({
                    text: t,
                    val: t
                });
            } while (temp.length);
            if (this.page > this.pagegroup) {
                (this.current > count + 1) && list.unshift({ text: '...', val: list[0].val - 1 });
                (this.current < this.page - count) && list.push({ text: '...', val: list[list.length - 1].val + 1 });
            }
            return list;
        }
    },
    methods: {
        setCurrent: function (idx) {
            
            if (this.current != idx && idx > 0 && idx < this.page + 1) {
                this.current = idx;
                
                this.$emit('pagechange', this.current);
                
            }
        }
    }
});