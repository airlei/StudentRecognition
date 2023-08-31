layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     *
     */
    var College = {
        tableId: "collegeTable"
    };

    /**
     * 初始化表格的列
     */
    College.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'collegeId', hide: true, title: ''},
            {field: 'name', sort: true, title: '学院'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    College.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(College.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    College.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加学院',
            content: Feng.ctxPath + '/college/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(College.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    College.exportExcel = function () {
        var checkRows = table.checkStatus(College.tableId);
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
    College.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改学院',
            content: Feng.ctxPath + '/college/edit?collegeId=' + data.collegeId,
            end: function () {
                admin.getTempData('formOk') && table.reload(College.tableId);
            }
        });
    };

    College.edit1 = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area : [ '650px', '600px' ], // 宽高
            title: '专业管理',
            content: Feng.ctxPath + '/major?collegeId=' + data.collegeId,
            end: function () {
                admin.getTempData('formOk') && table.reload(College.tableId);
            }
        });
    };

    
    
    
    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    College.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/college/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(College.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("collegeId", data.collegeId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + College.tableId,
        url: Feng.ctxPath + '/college/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: College.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        College.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        College.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        College.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + College.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            College.openEditDlg(data);
        } else if (layEvent === 'delete') {
            College.onDeleteItem(data);
        }else if (layEvent === 'edit1') {
            College.edit1(data);
        }
    });
});
