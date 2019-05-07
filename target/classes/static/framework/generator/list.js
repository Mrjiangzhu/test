require.config({
    paths: {
        text: ["/lib/require/text"],
        html: ["/lib/require/html"],
        link: ["/lib/require/link"],
        toolkit: ["/lib/require/toolkit"], //工具函数
    }
});
require(["toolkit"], function (tool) {
    var model = {
        tableSchema: "",
        tableName: "",
        tableComment: "",
        pageSize: 10,
        pageNumber: 1
    };
    var urlVar = "/ruofeng/app/framework/generator";
    var config = {
        el: "#app",
        data: function () {
            return {
                columns: [
                    {title: "tableSchema", name: "tableSchema"},
                    {title: "表名", name: "tableName"},
                    {title: "表备注", name: "tableComment"},
                    {title: "操作", name: "tableComment"},
                ],

                batsConfig: {
                    parentPkg: "",
                    version: "",
                    makeType: "",
                    projectPath: "",
                    tableList: []
                },
                currentRow: {},
                pageData: {
                    paras: model,
                    totalRow: 0,
                    list: []
                },
                temp: {
                    loading: false
                }
            }
        },
        methods: {
            query() {
                var url = "/ruofeng/app/framework/generator/tableList";
                var paramVar = this.pageData.paras;
                var app = this
                app.temp.loading = true
                setTimeout(function () {
                    axios.get(url, {
                        params: paramVar
                    }).then(function (response) {
                        console.log(response.data)
                        app.pageData.list = response.data.list
                        app.pageData.totalRow = response.data.totalRow
                        app.pageData.paras.pageNumber = response.data.pageNumber
                        app.temp.loading = false
                    }).catch(this.errorCatchFun)
                }, 500)
            },
            handleMake(row) {
                if (vm.checkMakeCondition()) {
                    row["makeType"] = vm.$data.batsConfig.makeType
                    window.vm.$data.currentRow = Object.assign({}, row);
                    vm.$data.batsConfig.tableList = [window.vm.$data.currentRow]
                    axios.post(urlVar + "/pojo", vm.$data.batsConfig).then(function (response) {
                        console.log(response)
                        MuseUIToast.info({message: "处理完毕!", position: 'top', dialogIcon: 'success'});
                    }).catch(this.errorCatchFun)
                }
            },
            handleSelectionChange(val) {
                vm.$data.batsConfig.tableList = val//选中的数据实体：tableName，注释
            },
            batsMakeFun() {
                if (vm.checkMakeCondition()) {
                    axios.post(urlVar + "/pojo", vm.$data.batsConfig).then(function (response) {
                        console.log(response)
                        layx.msg("处理完毕", {dialogIcon: 'success'});
                    }).catch(this.errorCatchFun)
                }
            },
            checkMakeCondition: function () {
                if (vm.$data.batsConfig.parentPkg == "" || vm.$data.batsConfig.projectPath == "") {
                    layx.msg("parentPkg和projectPath是必填项", {dialogIcon: 'error'});
                    return false
                } else {
                    return true
                }
            },
            errorCatchFun: function (error) {
                console.log(error);
                if (error.response.status >= 400 && error.response.status < 500) {
                    MuseUIToast.info({message: "没有找到对应的资源", position: 'top-end', dialogIcon: 'warn'});
                } else if (error.response.status >= 500 && error.response.status < 600) {
                    MuseUIToast.error({message: "系统出现异常!", position: 'top', dialogIcon: 'error'});
                }
            }

        }
    }
    window.vm = new Vue(config);
})
