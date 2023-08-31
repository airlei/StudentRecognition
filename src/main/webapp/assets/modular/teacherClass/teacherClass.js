layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 教师管理班级管理
     */
    var TeacherClass = {
        tableId: "teacherClassTable"
    };

    /**
     * 初始化表格的列
     */
    TeacherClass.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'teacherClassId', hide: true, title: ''},
         
            {field: 'classes', sort: true, title: '班级'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    TeacherClass.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(TeacherClass.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    TeacherClass.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加教师管理班级',
            content: Feng.ctxPath + '/teacherClass/add?teacherId='+Feng.getUrlParam("teacherId"),
            end: function () {
                admin.getTempData('formOk') && table.reload(TeacherClass.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    TeacherClass.exportExcel = function () {
        var checkRows = table.checkStatus(TeacherClass.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    TeacherClass.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改教师管理班级',
            content: Feng.ctxPath + '/teacherClass/edit?teacherClassId=' + data.teacherClassId,
            end: function () {
                admin.getTempData('formOk') && table.reload(TeacherClass.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    TeacherClass.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/teacherClass/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(TeacherClass.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("teacherClassId", data.teacherClassId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + TeacherClass.tableId,
        url: Feng.ctxPath + '/teacherClass/list?teacherId='+Feng.getUrlParam("teacherId"),
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: TeacherClass.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        TeacherClass.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        TeacherClass.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        TeacherClass.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + TeacherClass.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            TeacherClass.openEditDlg(data);
        } else if (layEvent === 'delete') {
            TeacherClass.onDeleteItem(data);
        }
    });
});
