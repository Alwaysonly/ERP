layui.config({
    base: '/lib/extend/'
}).extend({
    dataSource: 'dataSource'
}).use(['jquery', 'layer', 'table', 'dataSource', 'utils', 'form'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        utils = layui.utils,
        dataSource = layui.dataSource,
        table = layui.table;

    var tableHeight = ($("#btnTruncation").length > 0) ? 'full-104' : 'full-27';

    var tableIns = table.render({
        elem: '#logTable',
        url: '/page/system/logs/list',
        text: {
            none: '暂无数据'
        },
        response: {
            statusName: 'code'
            , statusCode: 200
            , msgName: 'message'
            , countName: 'count'
            , dataName: 'data'
        },
        height: tableHeight,
        page: true,
        loading: true,
        limit:15,
        id: 'logTable',
        cols: [[
            {type: 'numbers', align: 'center', width: 60, title: '序号'}
            , {field: 'moduleType', align: 'center', width: 100, title: '模块代码'}
            , {field: 'operateValue', align: 'center', width: 100, title: '操作类型'}
            , {field: 'remark', align: 'left,', title: '记录内容'}
            , {
                field: 'reqSource', align: 'center', title: '请求来源', width: 100,
                templet: function (d) {
                    if (d.reqSource == 'PC') {
                        return "电脑"
                    } else {
                        return "移动端"
                    }
                }
            }
            , {
                field: 'type', align: 'center', title: '日志类型', width: 100,
                templet: function (d) {
                    if (d.type == 'operate') {
                        return "操作日志"
                    } else {
                        return "异常日志"
                    }
                }
            }
            , {field: 'ip', align: 'center', width: 160, title: '来源IP'}
            , {field: 'userName', align: 'center', width: 120, title: '用户名'}
            , {field: 'userAccount', align: 'center', width: 120, title: '账号'}
            , {field: 'method', align: 'left,', title: '请求方法'}
            , {field: 'param', align: 'left', title: '请求参数'}
            , {field: 'exceptionDetail', align: 'left', title: '错误信息'}
            , {
                field: 'operateDate', width: 140, align: 'center', title: '创建时间',
                templet: function (d) {
                    return utils.toDateString(d.operateDate, 'yyyy-MM-dd');
                }
            }
            , {
                field: 'operateStatus', align: 'center', title: '操作状态', width: 100,
                templet: function (d) {
                    if (d.operateStatus == 'Y') {
                        return "<span style='color: green'>成功</span>"
                    } else {
                        return "<span style='color: red'>失败</span>"
                    }
                }
            }
        ]]
    });


    $("#btnTruncation").on('click', function () {
        layer.confirm('是否要重置日志[重置后将不可恢复]?', {icon: 3, title: '提示'}, function (index) {
            dataSource.getData({
                url: '/page/system/logs/truncation'
            }, function (data) {
                layer.msg('重置完成!');
                tableIns.reload({
                    page: {
                        curr: 1
                    }
                });
            });
            layer.close(index);
        });
    });
});