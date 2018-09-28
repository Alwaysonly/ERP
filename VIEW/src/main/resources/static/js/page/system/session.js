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

    var tableIns = null;

    dataSource.getData({
        url: '/page/system/sessions/list'
    }, function (data) {
        var newData = [];
        if (data.data instanceof Array) {
            for (var i = 0, temp = data.data, len = temp.length; i < len; i++) {
                var one = {};
                one.id = temp[i].id;
                one.startTimestamp = temp[i].startTimestamp;
                one.lastAccessTime = temp[i].lastAccessTime;
                one.timeout = temp[i].timeout;
                one.host = temp[i].host;
                one.expired = temp[i].expired;
                if (temp[i].attributes && temp[i].attributes['org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY'] && temp[i].attributes['org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY'].primaryPrincipal) {
                    one.userName = temp[i].attributes['org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY'].primaryPrincipal.userName;
                    one.userAccount = temp[i].attributes['org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY'].primaryPrincipal.userAccount;
                    newData.push(one);
                }
            }
        }
        renderTable(newData);
    });

    var renderTable = function (data) {
        tableIns = table.render({
            elem: '#sessionTable',
            data: data,
            text: {
                none: '暂无数据'
            },
            height: 'full-27',
            page: true,
            loading: true,
            id: 'sessionTable',
            cols: [[
                {type: 'numbers', align: 'center', width: 60, title: '序号'}
                , {field: 'id', align: 'center', width: 300, title: 'SESSIONID'}
                , {field: 'userAccount', align: 'center', title: '用户账号'}
                , {field: 'userName', align: 'center', title: '用户姓名'}
                , {field: 'host', align: 'center', width: 120, title: 'HOST'}
                , {
                    field: 'startTimestamp', width: 220, align: 'center', title: '授权时间',
                    templet: function (d) {
                        return utils.toDateString(d.startTimestamp, 'yyyy-MM-dd HH:mm:ss');
                    }
                },
                {
                    field: 'lastAccessTime', width: 220, align: 'center', title: '最后活动时间',
                    templet: function (d) {
                        return utils.toDateString(d.lastAccessTime, 'yyyy-MM-dd HH:mm:ss');
                    }
                },
                {width: 120, title: '操作', align: 'center', toolbar: '#toolsBar'}
            ]]
        });
    };


    table.on('tool(sessionTable)', function (obj) {
        var data = obj.data;

        if (obj.event === 'kickout') {//删除行
            layer.confirm('是否要强制用户[' + data.userName + ']下线?', {icon: 3, title: '提示'}, function (index) {
                dataSource.getData({
                    url: '/page/system/sessions/kickedout/' + data.id
                }, function (data) {
                    layer.msg('强制下线成功!');
                });
                layer.close(index);
            });
        }
    });
});