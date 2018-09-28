layui.define(['layer'], function (exports) {
    "use strict";
    var $ = layui.jquery;
    var layer = layui.layer;
    var MOD_NAME = 'dataSource';
    var dataSource = {
        options: {
            url: null,
            dataType: 'json',
            method: 'get',
            data: null,
            async: true,
            loading: true,
            loadText: '数据请求中，请稍候...'
        },
        getData: function (options, callback) {
            var that = this;
            options = $.extend({}, that.options, options);
            var index = that.options.loading && layer.msg(that.options.loadText, {icon: 16, time: false, shade: 0.8});
            $.ajax({
                url: options.url,
                type: options.method,
                data: options.data,
                async: options.async,
                dataType: options.dataType,
                success: function (result) {
                    layer.close(index);
                    var res = that.validationData(result);
                    callback && callback(res);
                },
                error: function (xhr) {
                    layer.msg("网络请求失败");
                    console.log(JSON.stringify(xhr.responseJSON));
                    layer.close(index);
                }
            });
        },

        validationData: function (data) {
            if (data && data.code) {
                switch (data.code) {
                    case 200:
                        return data;
                    case 301:
                        location.href = data.data;
                        if (data.message) {
                            layer.open({
                                title: '消息',
                                content: data.message
                            });
                        }
                        break;
                    case 404:
                        layer.open({
                            title: '错误404',
                            content: '抱歉，发生错误</br>' + data.message
                        });
                        throw '抱歉，发生错误:' + data.message;
                    case 400:
                        layer.open({
                            title: '错误400',
                            content: '参数错误,错误信息:</br>' + data.message
                        });
                        if (data.data) {
                            throw '参数错误,错误信息:' + data.data;
                        }
                        throw '参数错误,错误信息:' + data.message;
                    case 401:
                        layer.open({
                            title: '错误401',
                            content: '权限不足,错误信息:</br>' + data.message
                        });
                        throw '权限不足,错误信息:' + data.message;
                    case 500:
                        layer.open({
                            title: '错误500',
                            content: '未知错误,错误信息:</br>' + data.message
                        });
                        throw '未知错误,错误信息:' + data.message;
                }
            }
        }
    };
    exports(MOD_NAME, dataSource);
});