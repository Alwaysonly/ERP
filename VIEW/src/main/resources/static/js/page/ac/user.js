layui.config({
    base: '/lib/extend/'
}).extend({
    dataSource: 'dataSource'
}).use(['jquery', 'layer', 'table', 'dataSource', 'utils', 'form', 'laydate'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        utils = layui.utils,
        dataSource = layui.dataSource,
        table = layui.table,
        laydate = layui.laydate;

    var tableHeight = ($("#btnAdd").length > 0) ? 'full-104' : 'full-27';

    laydate.render({
        elem: '#addDate'
    });

    var tableIns = table.render({
        elem: '#userDataTable',
        url: '/page/ac/users/list',
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
        id: 'userDataTable',
        cols: [[
            {type: 'numbers', align: 'center', width: 60, title: '序号'}
            , {field: 'userAccount', align: 'left', title: '账号'}
            , {field: 'userName', align: 'left', title: '用户姓名'}
            , {
                field: 'userSex', align: 'center', width: 60, title: '性别',
                templet: function (d) {
                    switch (d.userSex) {
                        case '0':
                            return '未知';
                        case '1':
                            return '男';
                        case '2':
                            return '女';
                        case '9':
                            return '未说明';
                        default:
                            return '不详';
                    }
                }
            }
            , {
                field: 'userBirthday', align: 'center', width: 140, title: '生日',
                templet: function (d) {
                    return utils.toDateString(d.userBirthday, 'yyyy-MM-dd');
                }
            }
            , {field: 'userPhone', align: 'center', width: 140, title: '联系方式'}
            , {field: 'userDepartmentDesc', align: 'center', width: 100, title: '部门'}
            , {field: 'userPositionDesc', align: 'center', width: 100, title: '职位'}
            , {field: 'userRoleName', align: 'center', width: 100, title: '权限角色'}
            , {
                field: 'createTime', width: 140, align: 'center', title: '创建时间',
                templet: function (d) {
                    return utils.toDateString(d.createTime, 'yyyy-MM-dd');
                }
            }
            , {
                field: 'locked', width: 100, title: '状态', align: 'center',
                templet: function (d) {
                    var html;
                    if (d.locked == 0) {
                        html = '<span style="color: green">正常</span>';
                    } else {
                        html = '<span style="color: red">已禁用</span>';
                    }
                    return html;
                }
            },
            {
                field: 'deleted', width: 100, title: '离职', align: 'center',
                templet: function (d) {
                    var html;
                    if (d.deleted == 0) {
                        html = '<span style="color: green">正常</span>';
                    } else {
                        html = '<span style="color: red">已离职</span>';
                    }
                    return html;
                }
            },
            {width: 120, title: '操作', align: 'center', toolbar: '#toolsBar'}
        ]]
    });


    table.on('tool(userDataTable)', function (obj) {

        var data = obj.data;

        if (obj.event === 'del') {//删除行
            layer.confirm('是否要删除[' + data.userName + ']?', {icon: 3, title: '提示'}, function (index) {
                dataSource.getData({
                    url: '/page/ac/users/delete/' + data.id
                }, function (data) {
                    layer.msg('删除成功!');
                    obj.del();
                });
                layer.close(index);
            });
        } else if (obj.event === 'update') { //修改行
            $("#userUpdateForm")[0].reset();
            layer.open({
                title: '修改职位',
                type: 1,
                content: $('#userUpdateFormLayout'),
                success: function (layero, index) {
                    data.locked = data.locked == '0';
                    data.deleted = data.deleted == '0';
                    data.userBirthday = data.userBirthday ? utils.toDateString(data.userBirthday,'yyyy-MM-dd') : null;
                    form.val('userUpdateForm', data);
                },
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var formData = $("#userUpdateForm").serializeJson();
                    formData.locked = formData.locked ? '0' : '1';
                    formData.deleted = formData.deleted ? '0' : '1';
                    dataSource.getData({
                            url: '/page/ac/users/update',
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
        $("#userAddForm")[0].reset();
        layer.open({
            title: '新增用户',
            type: 1,
            // area: ['350px', '400px'],
            content: $('#userAddFormLayout'),
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                var formData = $("#userAddForm").serializeJson();
                formData.locked = formData.locked ? '0' : '1';
                formData.deleted = formData.deleted ? '0' : '1';
                dataSource.getData({
                        url: '/page/ac/users/add',
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

    $("#btnQuery").on("click", function () {
        var parmUserAccount = $("#parmUserAccount").val().trim();
        if (parmUserAccount) {
            tableIns.reload({
                where: {
                    userAccount: parmUserAccount
                },
                page: {
                    curr: 1
                }
            });
        } else {
            tableIns.reload({
                where: {},
                page: {
                    curr: 1
                }
            });
        }
    })
});