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

    var tableHeight = ($("#btnAdd").length > 0) ? 'full-104' : 'full-27';

    var tableIns = table.render({
        elem: '#positionDataTable',
        url: '/page/ac/positions/list',
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
        id: 'positionDataTable',
        cols: [[
            {type: 'numbers', align: 'center', width: 60, title: '序号'}
            , {field: 'code', align: 'left', title: '职位代码'}
            , {field: 'name', align: 'left,', title: '职位名称'}
            , {field: 'weight', align: 'left', width: 100, title: '权限值'}
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
            {width: 120, title: '操作', align: 'center', toolbar: '#toolsBar'}
        ]]
    });


    table.on('tool(positionDataTable)', function (obj) {

        var data = obj.data;

        if (obj.event === 'del') {//删除行
            layer.confirm('是否要删除[' + data.name + ']?', {icon: 3, title: '提示'}, function (index) {
                dataSource.getData({
                    url: '/page/ac/positions/delete/' + data.id
                }, function (data) {
                    layer.msg('删除成功!');
                    obj.del();
                });
                layer.close(index);
            });
        } else if (obj.event === 'update') { //修改行
            $("#positionForm")[0].reset();
            layer.open({
                title: '修改职位',
                type: 1,
                area: ['350px', '400px'],
                content: $('#positionFormLayout'),
                success: function (layero, index) {
                    utils.renderLayerForm(form, layero, 'formUpdateVerify');
                    data.available = data.available == 1;
                    form.val('positionForm', data);
                },
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    form.on("submit(formUpdateVerify)", function (data) {
                        var formData = data.field;
                        formData.available = formData.available == 'on' ? '1' : '0';
                        dataSource.getData({
                                url: '/page/ac/positions/update',
                                method: 'post',
                                data: formData
                            },
                            function (data) {
                                obj.update(formData);
                                layer.close(index);
                            }
                        );
                    });
                },
                btn2: function (index, layero) {
                    layer.close(index);
                }
            });
        }
    });

    $("#btnAdd").on('click', function () {
        $("#positionForm")[0].reset();
        layer.open({
            title: '新增职位',
            type: 1,
            area: ['350px', '400px'],
            content: $('#positionFormLayout'),
            btn: ['确定', '取消'],
            success: function (layero, index) {
                utils.renderLayerForm(form, layero, 'formAddVerify');
            },
            yes: function (index, layero) {
                form.on("submit(formAddVerify)", function (data) {
                    var formData = data.field;
                    formData.available = formData.available == 'on' ? '1' : '0';
                    dataSource.getData({
                            url: '/page/ac/positions/add',
                            method: 'post',
                            data: formData
                        },
                        function (data) {
                            tableIns.reload({
                                page: {
                                    curr: 1
                                }
                            });
                            layer.close(index);
                        }
                    )
                });

            },
            btn2: function (index, layero) {
                layer.close(index);
            }
        });
    });
});