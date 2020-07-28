/**
 * Created with JetBrains PhpStorm.
 * User: kk
 * Date: 13-8-28
 * Time: 下午4:44
 */
function U() {
    var url = arguments[0] || [];
    var param = arguments[1] || {};
    var url_arr = url.split('/');

    if (!$.isArray(url_arr) || url_arr.length < 2 || url_arr.length > 3) {
        return '';
    }

    if (url_arr.length == 2)
        url_arr.unshift(_GROUP_);

    var pre_arr = ['g', 'm', 'a'];

    var arr = [];
    for (d in pre_arr)
        arr.push(pre_arr[d] + '=' + url_arr[d]);

    for (d in param)
        arr.push(d + '=' + param[d]);

    return _APP_+'?'+arr.join('&');
}
//===================以下是自己的。
function jqBindCombo(comboId, data, includeEmptyOption) {
    var $combo = $("#" + comboId);
    $combo.empty();
    $combo.text("");
    if (includeEmptyOption) {
        $combo.append("<option value=''>全部</option>");
    }
    for (i = 0; i < data.length; i++) {
        $combo.append("<option value='" + data[i].Id + "'>" + data[i].Name + "</option>");
    }
}
//用法：jqCasscadeCombo("ProvinceId", "CityId", "/DataApi/GetCities", function () { jqCasscadeCombo("CityId", "DistrictId", "/DataApi/GetDistricts"); });
function jqCasscadeCombo(parentComboId, childrenComboId, apiUrl, callback, includeEmptyOption) {
    $("#" + parentComboId).change(function () {
        var pid = $("#" + parentComboId).val();
        $.post(apiUrl, { parentId: pid }, function (result) {
            if (result.Successful) {
                jqBindCombo(childrenComboId, result.Data, includeEmptyOption);
                $("#" + childrenComboId).trigger("change"); //if(result.Data.length==0) 
            } else {
                showErrMsg(result.Message);
            }
        }, "json");
    });
    if (typeof callback === "function") callback();
}
function jqThreeLevelCasscadeCombo(firstComboId, secondComboId, thirdComboId, getSecondComboDataUrl, getThirdComboDataUrl) {
    $("#" + firstComboId).change(function () {
        $("#" + secondComboId).empty();
        $("#" + thirdComboId).empty();
        var pid = $("#" + firstComboId).val();
        $.post(getSecondComboDataUrl, { parentId: pid }, function (result) {
            if (result.Successful) {
                jqBindCombo(secondComboId, result.Data);
                $("#" + secondComboId).trigger("change");
            } else {
                showErrMsg(result.Message);
            }
        }, "json");
    });
    $("#" + secondComboId).change(function () {
        $("#" + thirdComboId).empty();
        var cid = $("#" + secondComboId).val();
        $.post(getThirdComboDataUrl, { parentId: cid }, function (result) {
            if (result.Successful) {
                jqBindCombo(thirdComboId, result.Data);
            } else {
                showErrMsg(result.Message);
            }
        }, "json");
    });
}
