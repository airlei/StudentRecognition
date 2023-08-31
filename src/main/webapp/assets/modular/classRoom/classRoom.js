layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 课堂管理
     */
    var ClassRoom = {
        tableId: "classRoomTable"
    };

    /**
     * 初始化表格的列
     */
    ClassRoom.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'roomId', hide: true, title: ''},
            {align: 'center',  minWidth:300,toolbar: '#tableBar', title: '操作'},
            {field: 'name', sort: true, title: '课堂名称'},
            {field: 'startTime', sort: true, title: '开始时间'},
            {field: 'endTime', sort: true, title: '结束时间'},
            {field: 'userId', sort: true, title: '老师'},
            {field: 'classes', sort: true, title: '班级'},
            {field: 'demand', sort: true, title: '签到要求'}
          
        ]];
    };

    /**
     * 点击查询按钮
     */
    ClassRoom.search = function () {
        var queryData = {};
        queryData['name'] = $("#condition").val();
        table.reload(ClassRoom.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    ClassRoom.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加课堂',
            content: Feng.ctxPath + '/classRoom/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(ClassRoom.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    ClassRoom.exportExcel = function () {
        var checkRows = table.checkStatus(ClassRoom.tableId);
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
    ClassRoom.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改课堂',
            content: Feng.ctxPath + '/classRoom/edit?roomId=' + data.roomId,
            end: function () {
                admin.getTempData('formOk') && table.reload(ClassRoom.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    ClassRoom.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/classRoom/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ClassRoom.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("roomId", data.roomId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    ClassRoom.edit1 = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area : [ '650px', '600px' ], // 宽高
            title: '人脸考勤',
            content: Feng.ctxPath + '/attendance/add?roomId='+data.roomId,
            end: function () {
                admin.getTempData('formOk') && table.reload(ClassRoom.tableId);
            }
        });
    };

    ClassRoom.edit2 = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area : [ '70%', '70%' ], // 宽高
            title: '考勤记录',
            content: Feng.ctxPath + '/attendance/getAttendance?roomId='+data.roomId,
            end: function () {
                admin.getTempData('formOk') && table.reload(ClassRoom.tableId);
            }
        });
    };
    
    
    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ClassRoom.tableId,
        url: Feng.ctxPath + '/classRoom/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ClassRoom.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ClassRoom.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        ClassRoom.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        ClassRoom.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ClassRoom.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ClassRoom.openEditDlg(data);
        } else if (layEvent === 'delete') {
            ClassRoom.onDeleteItem(data);
        }else if (layEvent === 'edit1') {
            ClassRoom.edit1(data);
        }else if (layEvent === 'edit2') {
            ClassRoom.edit2(data);
        }
    });
});
