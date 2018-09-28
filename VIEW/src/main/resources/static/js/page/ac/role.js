layui.config({
    base: '/lib/extend/'
}).extend({
    dataSource: 'dataSource',
    authtree: 'authtree',
    utils: 'utils',
}).use(['jquery', 'layer', 'table', 'dataSource', 'utils', 'form', 'authtree'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        utils = layui.utils,
        dataSource = layui.dataSource,
        table = layui.table,
        authtree = layui.authtree;

    var tableHeight = ($("#btnAdd").length > 0) ? 'full-104' : 'full-27';

    var tableIns = table.render({
        elem: '#roleDataTable',
        url: '/page/ac/roles/list',
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
        id: 'roleDataTable',
        cols: [[
            {type: 'numbers', align: 'center', width: 60, title: '序号'}
            , {field: 'roleCode', align: 'left', width: 140, title: '角色编码'}
            , {field: 'roleName', align: 'left,', title: '角色名称'}
            , {
                field: 'createTime', width: 140, align: 'center', title: '创建时间',
                templet: function (d) {
                    return utils.toDateString(d.createTime, 'yyyy-MM-dd');
                }
            },
            {
                field: 'updateTime', width: 140, align: 'center', title: '更新时间',
                templet: function (d) {
                    return utils.toDateString(d.updateTime, 'yyyy-MM-dd');
                }
            }
            , {
                field: 'available', width: 100, title: '状态', align: 'center',
                templet: function (d) {
                    var html;
                    if (d.available == 1) {
                        html = '<span style="color: green">可用</span>';
                    } else {
                        html = '<span style="color: red">禁用</span>';
                    }
                    return html;
                }
            },
            {width: 200, title: '操作', align: 'center', toolbar: '#toolsBar'}
        ]]
    });


    table.on('tool(roleDataTable)', function (obj) {

        var data = obj.data;

        if (obj.event === 'del') {//删除行
            layer.confirm('是否要删除[' + data.roleName + ']?', {icon: 3, title: '提示'}, function (index) {
                dataSource.getData({
                    url: '/page/ac/roles/delete/' + data.id
                }, function (data) {
                    layer.msg('删除成功!');
                    obj.del();
                });
                layer.close(index);
            });
        } else if (obj.event === 'update') { //修改行
            $("#roleForm")[0].reset();
            layer.open({
                title: '修改角色',
                type: 1,
                area: ['350px', '400px'],
                content: $('#roleFormLayout'),
                success: function (layero, index) {
                    utils.renderLayerForm(form,layero,'formUpdateVerify');
                    data.available = data.available == 1;
                    form.val('roleForm', data);
                },
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    form.on('submit(formUpdateVerify)', function (data) {
                        var formData = data.field;
                        var _obj = obj;
                        formData.available = formData.available === 'on' ? '1' : '0';
                        dataSource.getData({
                                url: '/page/ac/roles/update',
                                method: 'post',
                                data: formData
                            },
                            function (data) {
                                _obj.update(formData);
                                layer.close(index);
                            }
                        );
                        return false;
                    });
                },
                btn2: function (index, layero) {
                    layer.close(index);
                }
            });
        } else if (obj.event === 'edit') { //编辑权限
            dataSource.getData({
                url: '/page/ac/roles/editPermission/getTree',
                data: {
                    roleId: data.id
                }
            }, function (_data) {
                authtree.render('#authTree', _data.data, {
                    inputname: 'authids[]',
                    layfilter: 'lay-check-auth',
                    openall: true,
                    autowidth: true,
                    datacolumn: {
                        title: 'name',
                        value: 'id'
                    }
                });
                layer.open({
                    title: '编辑权限',
                    type: 1,
                    area: ['350px', '600px'],
                    content: $('#roleEditFormLayout'),
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var ids = $("#roleEditForm").serializeJson();
                        ids.roleId = data.id;
                        dataSource.getData({
                            url: '/page/ac/roles/editPermission/update',
                            method: 'post',
                            data: ids
                        }, function (data) {

                        });
                    },
                    btn2: function (index, layero) {
                        layer.close(index);
                    }
                });
            });
        }
    });

    $("#btnAdd").on('click', function () {
        $("#roleForm")[0].reset();
        layer.open({
            title: '新增角色',
            type: 1,
            area: ['350px', '400px'],
            content: $('#roleFormLayout'),
            btn: ['确定', '取消'],
            success: function (layero, index) {
                utils.renderLayerForm(form,layero,'formAddVerify');
            },
            yes: function (index, layero) {
                form.on('submit(formAddVerify)', function (data) {
                    var fromData = data.field;
                    fromData.available = fromData.available === 'on' ? '1' : '0';
                    dataSource.getData({
                            url: '/page/ac/roles/add',
                            method: 'post',
                            data: fromData
                        },
                        function (data) {
                            tableIns.reload({
                                page: {
                                    curr: 1
                                }
                            });
                            layer.close(index);
                        }
                    );
                    return false;
                });
            },
            btn2: function (index, layero) {
                layer.close(index);
            }
        });
    });
});