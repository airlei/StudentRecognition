layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 专业管理
     */
    var Major = {
        tableId: "majorTable"
    };

    /**
     * 初始化表格的列
     */
    Major.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'majorId', hide: true, title: ''},
            {field: 'name', sort: true, title: '专业名称'},
        
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Major.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Major.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Major.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加专业',
            content: Feng.ctxPath + '/major/add?collegeId='+ Feng.getUrlParam("collegeId"),
            end: function () {
                admin.getTempData('formOk') && table.reload(Major.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Major.exportExcel = function () {
        var checkRows = table.checkStatus(Major.tableId);
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
    Major.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改专业',
            content: Feng.ctxPath + '/major/edit?majorId=' + data.majorId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Major.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Major.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/major/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Major.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("majorId", data.majorId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Major.tableId,
        url: Feng.ctxPath + '/major/list?collegeId='+ Feng.getUrlParam("collegeId"),
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Major.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Major.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Major.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Major.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Major.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Major.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Major.onDeleteItem(data);
        }
    });
});
