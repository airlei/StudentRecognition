/**
 * 详情对话框
 */
var ClassRoomInfoDlg = {
    data: {
        name: "",
        startTime: "",
        endTime: "",
        userId: "",
        classesId: "",
        demand: ""
    }
};

layui.use(['form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    getList();
    laydate.render({
        elem: '#startTime'
        ,type: 'datetime'
        ,trigger: 'click'
      });
    laydate.render({
        elem: '#endTime'
        ,type: 'datetime'
        ,trigger: 'click'
      });
    //让当前iframe弹层高度适应
    admin.iframeAuto();

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/classRoom/detail?roomId=" + Feng.getUrlParam("roomId"));
    var result = ajax.start();
    form.val('classRoomForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/classRoom/editItem", function (data) {
            Feng.success("更新成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});