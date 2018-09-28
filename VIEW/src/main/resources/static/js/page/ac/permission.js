layui.config({
    base: '/lib/extend/'
}).extend({
    treeGrid: 'treeGrid',
    dataSource: 'dataSource',
    iconPicker: 'iconPicker'
}).use(['jquery', 'treeGrid', 'layer', 'dataSource', 'util', 'iconPicker', 'form'], function () {
    var $ = layui.jquery;
    var treeGrid = layui.treeGrid;//很重要
    var layer = layui.layer;
    var dataSource = layui.dataSource;
    var iconPicker = layui.iconPicker;
    var util = layui.util;
    var form = layui.form;

    var treeGridId = treeGrid.render({
        id: 'permissionTreeTable'
        , elem: '#permissionTreeTable'
        , idField: 'id'
        , url: '/page/ac/permissions/list'
        , cellMinWidth: 100
        , treeId: 'id'//树形id字段名称
        , treeUpId: 'pid'//树形父id字段名称
        , treeShowName: 'name'//以树形式显示的字段
        , iconOpen: false
        , response: {
            statusName: 'code'
            , statusCode: 200
            , msgName: 'message'
            , countName: 'count'
            , dataName: 'data'
        }
        , height: 'full-28'
        , cols: [[
            {type: 'numbers'},
            {field: 'id', width: 50, title: 'ID'},
            {width: 180, title: '操作', align: 'center', toolbar: '#toolsBar'},
            {
                width: 60, title: '图标', align: 'center'
                , templet: function (d) {
                    return '<i class="layui-icon ' + d.icon + '"></i>';
                }
            },
            {field: 'name', align: 'left', width: 160, title: '名称'},
            {field: 'code', align: 'left', width: 200, title: '编码'},
            {
                field: 'type', align: 'center', width: 80, title: '类型',
                templet: function (d) {
                    return d.type === 'menu' ? '菜单' : '按钮';
                }
            },
            {field: 'url', align: 'left', title: 'URL'},
            {
                field: 'createTime', width: 140, align: 'center', title: '创建时间',
                templet: function (d) {
                    return util.toDateString(d.createTime, 'yyyy-MM-dd');
                }
            },
            {
                field: 'updateTime', width: 140, align: 'center', title: '更新时间',
                templet: function (d) {
                    return util.toDateString(d.updateTime, 'yyyy-MM-dd');
                }
            },
            {field: 'order', align: 'center', width: 100, title: '顺序号'},

        ]]
        , page: false
        , done: function () {
            treeGrid.treeOpenAll("permissionTreeTable", false);
        }
    });

    treeGrid.on('tool(permissionTreeTable)', function (obj) {

        var data = obj.data;

        if (obj.event === 'del') {//删除行
            layer.confirm('是否要删除[' + data.name + ']?', {icon: 3, title: '提示'}, function (index) {
                dataSource.getData({
                    url: '/page/ac/permissions/delete/' + data.id
                }, function (data) {
                    layer.msg('删除成功!');
                    obj.del();
                });
                layer.close(index);
            });

        } else if (obj.event === 'update') { //修改行
            $("#btnReset").click();
            layer.open({
                title: '修改权限',
                type: 1,
                area: ['400px', '550px'],
                content: $('#permissionFormLayout'),
                success: function (layero, index) {
                    console.log(data.icon);
                    iconPicker.checkIcon('iconPicker', data.icon);
                    var insData = {};
                    insData = $.extend({}, insData, data);
                    delete insData['icon'];
                    form.val('permissionForm', insData);
                }
            });
        } else if (obj.event === "add") {//添加行
            $("#btnReset").click();
            layer.open({
                title: '新增权限',
                type: 1,
                area: ['400px', '550px'],
                content: $('#permissionFormLayout'),
                success: function (layero, index) {
                    iconPicker.checkIcon('iconPicker', 'layui-icon-home');
                    form.val('permissionForm', {pid: data.id});
                }
            });
        }
    });

    form.on('submit(commit)', function (data) {
        var fromData = data.field;
        if (fromData.id) {
            dataSource.getData({
                    url: '/page/ac/permissions/update',
                    method: 'post',
                    loadText: '数据保存中...请稍后',
                    data: fromData
                },
                function (data) {
                    treeGrid.reload('permissionTreeTable', {
                        page: {
                            curr: 1
                        }
                    });
                    layer.closeAll();
                });
        } else {
            dataSource.getData({
                    url: '/page/ac/permissions/add',
                    method: 'post',
                    loadText: '数据保存中...请稍后',
                    data: fromData
                },
                function (data) {
                    treeGrid.reload('permissionTreeTable', {
                        page: {
                            curr: 1
                        }
                    });
                    layer.closeAll();
                });
        }
        return false;
    });


    iconPicker.render({
        // 选择器，推荐使用input
        elem: '#iconPicker',
        // 数据类型：fontClass/unicode，推荐使用fontClass
        type: 'fontClass',
        // 是否开启搜索：true/false
        search: true
    });
});
