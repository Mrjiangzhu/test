<%
var urlPath = "/"+kotlin.packageToUrl(model.pkg);
debug("urlPath:"+urlPath);
%>
require.config({
    paths: {
        text: ["/lib/require/text"],
        html: ["/lib/require/html"],
        link: ["/lib/require/link"],
        toolkit: ["/lib/require/toolkit"], //工具函数
        toast: ["/lib/muse-ui/muse-ui-toast"], //toast
        message: ["/lib/muse-ui/muse-ui-message"],
    }
});
require(["toolkit"], function (tool) {
    var formVar = {
<%
    var pkFieldName;
    for (attr in model.attrs) {
        var columnName = kotlin.dbcolToClzAttr(attr.columnName);
        var kotlinType = kotlin.turnSqlType(attr.dataType);
        print(columnName + ":'" + attr.columnComment + "',");
    }
%>
}
    ;
    var columnVar = [
        <%
    for (attr in model.attrs) {
        var columnName = kotlin.dbcolToClzAttr(attr.columnName);
        var kotlinType = kotlin.turnSqlType(attr.dataType);
        if (attr.columnKey == "PRI") {
            pkFieldName = columnName;
        }
        if (attrLP.index <= 5) {
        %>
{title:"${attr.columnComment}", name:"${columnName}", show:true, query:true},
        <%
        } else {
        %>
{title:"${attr.columnComment}", name:"${columnName}", show:false, query:false},
<%
        }
    }
%>
    {title: "操作", name:"_operate", show:true, query: true},
]


    var configVar = {
<%for(attr in model.attrs) {
        var columnName = kotlin.dbcolToClzAttr(attr.columnName);
        var kotlinType = kotlin.turnSqlType(attr.dataType);
        if (attrLP.index <= 5) {%>
${columnName}:true,
<%} else {%>
${columnName}:false,
<%}}%>

}
    ;
    window.vm = new Vue({
        el: "#app",
        data() {
            return {
                url: "${urlPath}",
                allColumn: columnVar,
                config: configVar,
                pageData: {
                    totalRow: '',
                    paras: {
                        pageNumber: 1,
                        pageSize: 15,
                    },
                    list: [],
                },
                form: formVar,
                role: false,
                temp: {
                    pkFieldName: "${pkFieldName}",
                    loading: false,
                    showEditDialog: false,
                    showSettings: false,
                }
            }
        },
        watch: {
            allColumn: {
                handler(after, before) {
                    var app = this
                    var afterShow = after.filter(this.isShowFun)
                    app.$data.config = {}
                    for (var i = 0; i < afterShow.length; i++) {
                        app.$data.config[afterShow[i].name] = afterShow[i].show;
                    }
                },
                immediate: true,
                deep: true
            }
        },
        methods: {
            query: function () {
                var data = this;
                this.temp.loading = true;
                data.pageData.list = []
                axios.get(data.url, {
                    params: data.pageData.paras
                }).then(function (response) {
                    data.pageData.list = response.data.list
                    data.pageData.totalRow = response.data.totalRow
                    data.pageData.paras.pageNumber = response.data.pageNumber
                    data.temp.loading = false
                })
            },
            reset: function () {
                window.vm.pageData.paras = {};
            },
            add: function () {
                var data = this;
                this.temp.showEditDialog = true;
                this.form = {};
            },
            edit: function (_row) {
                var data = this;
                this.temp.showEditDialog = true;
                this.form = _row;
            },
            save: function () {
                var app = this;
                var pkId = app.form[app.temp.pkFieldName];
                var methodType="";
                if (typeof(pkId) != "undefined" && pkId > 0) {
                    methodType="PUT"
                } else {
                    methodType="POST"
                }
                axios({
                    url: app.url,
                    method: methodType,
                    data: app.form
                }).then(function (response) {
                    MuseUIToast.info(response.data);
                })
            },
            deleteBtnFun: function (rowId) {
                var app = this;
                if (rowId != "") {
                    var param = {};
                    param["pkId"] = rowId;
                    var msg = "【 #：" + rowId + " 】将被删除！";
                    MuseUIMessage.confirm('确定要删除？', '提示', {
                        type: 'warning'
                    }).then(({result}) => {
                        if (result) {
                            axios({
                                url: app.url,
                                method: "DELETE",
                                data: app.form
                            }).then(function (response) {
                                MuseUIToast.info(response.data);
                            })
                        }
                    });
                }
            },
            setting: function () {
                this.temp.showSettings = !this.temp.showSettings;
            },
            isQueryFun: function (element) {
                return element.query === true;
            },
            isShowFun: function (element) {
                return element.show === true;
            }
        }
    });

})
