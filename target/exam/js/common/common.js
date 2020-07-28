var common = {};
(function () {
    common.callApi = function (o) {
        common.ajaxRequest({
            url: o.url, data: o.data, type:o.type || "POST", dataType: o.dataType || "json", cb: function (r) {
                if (r) {
                    console.log(r)
                    if (r.successful) {
                        if (o.cb && typeof (o.cb) === "function") {
                            o.cb(r.data);
                        }
                    } else {
                        common.alert(r.message);
                    }
                } else {
                    alert("请求出错！");
                }
            }
        });
    },
    common.callApi2 = function (o) {
        common.ajaxRequest({
            url: o.url, data: o.data, type:o.type || "POST", dataType: o.dataType || "json", cb: function (r) {
                if (o.cb && typeof (o.cb) === "function") {
                    o.cb(r);
                }
            }
        });
    }
    common.ajaxRequest = function (o) {
        if (!o || !o.url)
            return;
        $.ajax({
            type: o.type || 'POST',
            url: o.url,
            data: o.data || {},
            dataType: o.dataType || 'json',
            success: function (data) {
                if (o.cb && typeof (o.cb) === "function")
                    o.cb(data);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(o.url)
                alert(XMLHttpRequest.status);
                 alert(XMLHttpRequest.readyState);
                 alert(textStatus);
            },
            //error: function (res) {
            //    alert("AJAX请求出错！");
            //},
            complete: function (res) {
            }

        });
    }
    common.getCookie = function (name)
    {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }
    common.setCookie = function (name, value, time)
    {
        var exp = new Date();
        exp.setTime(exp.getTime() + time * 1000);
        document.cookie = name + "=" + value + ";expires=" + exp.toGMTString();
    }
    common.removeCookie = function (name) {
        common.setCookie(name, "", -1);
    }
    common.alert = function (msg) {
        alert(msg);
    }
    
    common.formatDate = function (dt) {
        return dt.getFullYear() + "-" + (dt.getMonth() + 1) + "-" + dt.getDate();
    }
    common.formatCurrency = function (value) {
        var html, _val;
        value = Number(value).toFixed(2);
        if (value === 0) {
            value = 0;
            return html = "￥0.00";
        } else if (value.split('.')[1] && value.split('.')[1].substring(1) === 0) {
            value = Number(value).toFixed(1);
        }
        _val = value.split('.');
        return html = '￥' + common.formatNumberWithThousands(_val[0]) + '.' + _val[1] + '';
    }
    common.formatPercentage = function (value) {
        var html, _val;
        value = Number(value * 100).toFixed(2);
        if (value === 0) {
            value = 0;
            return html = "0.00%";
        } else if (value.split('.')[1] && value.split('.')[1].substring(1) === 0) {
            value = Number(value).toFixed(1);
        }
        _val = value.split('.');
        return html = '' + common.formatNumberWithThousands(_val[0]) + '.' + _val[1] + '%';
    }
    common.formatNumberWithThousands = function (num) {
        var num = (num || 0).toString(), result = '';
        while (num.length > 3) {
            result = ',' + num.slice(-3) + result;
            num = num.slice(0, num.length - 3);
        }
        if (num) { result = num + result; }
        return result;
    }
    common.formatDateTime = function(date,fmt){
        function padLeftZero(str){
            return ('00'+str).substr(str.length);
        }

        if(/(y+)/.test(fmt)){
            fmt = fmt.replace(RegExp.$1, (date.getFullYear()+'').substr(4-RegExp.$1.length));
        }
        let o = {
            'M+': date.getMonth()+1,
            'd+': date.getDate(),
            'h+': date.getHours(),
            'm+': date.getMinutes(),
            's+': date.getSeconds()
        }
        for(let k in o){    
            let str = o[k]+'';
            if(new RegExp(`(${k})`).test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length===1)?str:padLeftZero(str));
            }
        }
        return fmt;
    }
    common.formatJsonDateTime = function (jsondate, fmt) {
        if (!jsondate) return "";
        jsondate = jsondate.replace("/Date(", "").replace(")/", "");
        if (jsondate.indexOf("+") > 0) {
            jsondate = jsondate.substring(0, jsondate.indexOf("+"));
        }
        else if (jsondate.indexOf("-") > 0) {
            jsondate = jsondate.substring(0, jsondate.indexOf("-"));
        }

        var date = new Date(parseInt(jsondate, 10));
        return common.formatDateTime(date, fmt);
    }
}());