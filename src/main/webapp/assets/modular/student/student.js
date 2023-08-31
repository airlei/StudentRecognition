layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 学生管理管理
     */
    var Student = {
        tableId: "studentTable"
    };

    /**
     * 初始化表格的列
     */
    Student.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'studentId', hide: true, title: ''},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'userName', sort: true, title: '学号'},
            {field: 'password', sort: true, title: '密码'},
            {field: 'tel', sort: true, title: '联系电话'},
            {field: 'college', sort: true, title: '学院'},
            {field: 'major', sort: true, title: '专业'},
            {field: 'classes', sort: true, title: '班级'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Student.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Student.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Student.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加学生管理',
            content: Feng.ctxPath + '/student/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Student.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Student.exportExcel = function () {
        var checkRows = table.checkStatus(Student.tableId);
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
    Student.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改学生管理',
            content: Feng.ctxPath + '/student/edit?studentId=' + data.studentId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Student.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Student.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/student/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Student.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("studentId", data.studentId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Student.tableId,
        url: Feng.ctxPath + '/student/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Student.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Student.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Student.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Student.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Student.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Student.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Student.onDeleteItem(data);
        }
    });
});
