layui.config({
    base: "/view/js/"
})
var form, $, areaData;
layui.use(['form', 'jquery', 'layer', 'upload', 'laydate'], function () {

    window.Toolkit.authorConfig(layui.jquery, layui.layer);
    window.$ = layui.jquery;
    var admin = layui.admin;
    var element = layui.element;
    window.form = layui.form;
    var upload = layui.upload;
    var laydate = layui.laydate;
});