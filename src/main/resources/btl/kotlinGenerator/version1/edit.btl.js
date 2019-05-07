<%
var urlPath = kotlin.packageToUrl(model.pkg);
var pkFieldName;
if (!isEmpty(model.attrs)) {
    for (attr in model.attrs) {
        var columnName = kotlin.dbcolToClzAttr(attr.columnName);
        var kotlinType = kotlin.turnSqlType(attr.dataType);
        if (attr.columnKey == "PRI") {
            pkFieldName = columnName;
        }
    }
}
%>
(function () {
    initVue()
    vm.$data.param = parent.vm.$data.currentRow;

    function initVue() {
        var config = {
            el: "#app",
            data: function () {
                return {
                    param: {},
                    tableData: {
                        list: [],
                        pageSize: 10,
                        pageNumber: 1
                    }
                }
            },
            methods: {
                save: function () {
                    var urlvar = "${urlPath}";
                    var responseFun = function (response) {
                        console.log(response)
                        layx.msg("数据存储成功...", {dialogIcon: 'success'});
                    }
                    var catchFun = function (error) {
                        if (error.response.status >= 400 && error.response.status < 500) {
                            layx.msg("没有找到数据", {dialogIcon: 'warn'});
                        } else if (error.response.status >= 500 && error.response.status < 600) {
                            layx.msg("程序出现错误", {dialogIcon: 'error'});
                        }
                    }

                    var pkId = document.getElementById("pkId").value;
                    if (typeof (pkId) == "undefined" || pkId == "") {
                        axios.post(urlvar, vm.$data.param).then(responseFun).catch(catchFun)
                    } else {
                        axios.put(urlvar, vm.$data.param).then(responseFun).catch(catchFun)
                    }
                },
            }
        }
        window.vm = new Vue(config);
    }

    function axiosData() {
        if (vm.$data.param.pkId != null) {
            var urlvar = "${urlPath}" + vm.$data.param.pkId;
            axios.get(urlvar).then(function (response) {
                vm.$data.param = response.data.topic;
            })
        }
    }
})();