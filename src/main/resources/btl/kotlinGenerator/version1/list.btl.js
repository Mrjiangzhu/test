(function () {
    var defaultTableData = {
            list: [],
            pageSize: 10,
            pageNumber: 1
        }
<%
    var urlPath = kotlin.packageToUrl(model.pkg);
    var pkFieldName;
    if (!isEmpty(model.attrs)) {
        print("        var model = {");
        for (attr in model.attrs) {
            var columnName = kotlin.dbcolToClzAttr(attr.columnName);
            var kotlinType = kotlin.turnSqlType(attr.dataType);
            if (attr.columnKey == "PRI") {
                pkFieldName = columnName;
            }
            var str = columnName + ":'',";
            print(str);
        }
        print("};");
    }
%>

    var urlVar = "${urlPath}";
    init();

    function init() {
        initVue();
        configUI();
        axiosData()
    }

    function initVue() {
        var config = {
            el: "#app",
            data: function () {
                return {
                    param: Object.assign({}, model),
                    conditionConfig: {},
                    tableConfig: {},
                    currentRow: {},
                    tableData: Object.assign({}, defaultTableData)
                }
            },
            methods: {
                query() {
                    axiosData()
                },
                setting() {
                    settingFun();
                },
                create() {
                    //防止因为model导致新建，编辑的数据互相污染
                    window.vm.$data.currentRow = Object.assign({}, model);
                    infoEditor()
                },
                handleEdit(row) {
                    //防止因为row导致行数据的显示，编辑的数据互相污染
                    window.vm.$data.currentRow = Object.assign({}, row);
                    infoEditor()
                },
                handleDelete(row) {
                    deleteData(row);
                },
                handleSizeChange(v) {
                    vm.$data.tableData.pageSize = v
                    axiosData()
                },
                handleCurrentChange(v) {
                    vm.$data.tableData.pageNumber = v
                    axiosData()
                },
                closeWin: function (id) {
                    layx.destroy(id);
                },
                loading: function () {
                    vm.$data.loader = vm.$loading({
                        lock: true,
                        text: '我在很努力的执行中....',
                        spinner: 'el-icon-loading',
                        background: 'rgba(0, 0, 0, 0.7)'
                    });
                }
            }
        }
        window.vm = new Vue(config);
    }

    function configUI() {
        var conditionConfigStr = localStorage.getItem("condition:" + urlVar)
        var tableConfigStr = localStorage.getItem("table:" + urlVar)
        if (conditionConfigStr != null && conditionConfigStr.trim().length > 0) {
            window.vm.$data.conditionConfig = JSON.parse(conditionConfigStr)
            window.vm.$data.tableConfig = JSON.parse(tableConfigStr)
        } else {
            var conditionIndex = 0;
            for (conditionKey in vm.$data.param) {
                if (conditionIndex < 3) {
                    vm.$data.conditionConfig[conditionKey] = true
                }
                if (conditionIndex < 8) {
                    vm.$data.tableConfig[conditionKey] = true
                }
                conditionIndex++;
            }
        }
    }

    function axiosData() {
        var url = "${urlPath}?pageSize=" + vm.$data.tableData.pageSize + "&pageNumber=" + vm.$data.tableData.pageNumber + Toolkit.urlEncode(vm.$data.param);
        axios.get(url).then(function (response) {
            vm.$data.tableData = response.data
        }).catch(function (error) {
            if (error.response.status >= 400 && error.response.status < 500) {
                layx.msg("没有数据", {dialogIcon: 'warn'});
                vm.$data.tableData = defaultTableData;
            } else if (error.response.status >= 500 && error.response.status < 600) {
                layx.msg("程序出现错误", {dialogIcon: 'error'});
            }
        })
    }

    function openWindow(urlVar, appconfig, title) {
        if (typeof (title) == "undefined") {
            title = ""
        }
        vm.loading();
        var btns = [
            {
                label: '保存',
                callback: function (id, button, event) {
                    var contentWindow = layx.getFrameContext(id);
                    contentWindow.vm.save()
                }
            },
            {
                label: '取消',
                callback: function (id, button, event) {
                    layx.destroy(id);
                }
            }
        ]
        var options = {
            useFrameTitle: true, maxable: true,
            width: 400, height: 600,
            storeStatus: false,
            controlStyle: 'background-color: #1AA094; color:#fff;',
            border: true, shadow: true,
            statusBar: true,
            buttons: btns,
            event: {
                ondestroy: { // 关闭事件
                    after: function () {
                        vm.$data.loader.close()
                        axiosData()
                    }
                },
            }
        }
        options = Object.assign(options, appconfig)
        layx.iframe('radiu-style', title || '信息', urlVar, options);
    }

    /**
     *新建和编辑的窗口操作
     **/
    function infoEditor() {
        openWindow("edit.html", {}, "编辑")
    }

    function settingFun() {
        var options = {
            width: 800,
            height: 600,
            event: {
                ondestroy: { // 关闭事件
                    after: function () {
                        vm.$data.loader.close()
                        configUI()//重置查询条件和表格列头
                        axiosData()
                    }
                },
            }
        }
        openWindow("setting.html", options, "选项设置")
    }

    /**
     *删除的提示窗口
     **/
    function deleteData(current) {
        vm.loading();
        var urlVar = "${urlPath}?${pkFieldName}=" + current.$
        {
            pkFieldName
        }
        ;
        layx.confirm('删除确认', '确定要删除当前的信息吗？', function (id) {
            axios.delete(urlVar).then(function (response) {
                console.log(response.data)
                axiosData()
            }).catch(function (error) {
                console.log(error);
            })
            //关闭遮罩
            vm.$data.loader.close()
            // destroy可以设置参数，设置后可以在ondestroy.before中判断
            layx.destroy(id, {noTip: true});
        }, {
            event: {
                ondestroy: {
                    after: function () {
                        vm.$data.loader.close()
                    }
                }
            },
            dialogIcon: 'help'
        })
    }
})()