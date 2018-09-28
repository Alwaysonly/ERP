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
        elem: '#departmentDataTable',
        url: '/page/ac/departments/list',
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
        id: 'departmentDataTable',
        cols: [[
            {type: 'numbers', align: 'center', width: 60, title: '序号'}
            , {field: 'code', align: 'left',  width: 140, title: '职位代码'}
            , {field: 'name', align: 'left,', title: '职位名称'}
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


    table.on('tool(departmentDataTable)', function (obj) {

        var data = obj.data;

        if (obj.event === 'del') {//删除行
            layer.confirm('是否要删除['+data.name+']?', {icon: 3, title:'提示'}, function(index){
                dataSource.getData({
                    url: '/page/ac/departments/delete/' + data.id
                }, function (data) {
                    layer.msg('删除成功!');
                    obj.del();
                });
                layer.close(index);
            });
        } else if (obj.event === 'update') { //修改行
            $("#departmentForm")[0].reset();
            layer.open({
                title: '修改部门',
                type: 1,
                area: ['350px', '400px'],
                content: $('#departmentFormLayout'),
                success: function (layero, index) {
                    data.available = data.available == 1;
                    form.val('departmentForm', data);
                },
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var formData = $("#departmentForm").serializeJson();
                    formData.available = formData.available ? '1' : '0';
                    dataSource.getData({
                            url: '/page/ac/departments/update',
                            method: 'post',
                            data: formData
                        },
                        function (data) {
                            obj.update(formData);
                            layer.close(index);
                        }
                    )
                    ;
                },
                btn2: function (index, layero) {
                    layer.close(index);
                }
            });
        }
    });

    $("#btnAdd").on('click', function () {
        $("#departmentForm")[0].reset();
        layer.open({
            title: '新增部门',
            type: 1,
            area: ['350px', '400px'],
            content: $('#departmentFormLayout'),
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                var formData = $("#departmentForm").serializeJson();
                formData.available = formData.available ? '1' : '0';
                dataSource.getData({
                        url: '/page/ac/departments/add',
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
                ;
            },
            btn2: function (index, layero) {
                layer.close(index);
            }
        });
    });
});