<%var urlPath=kotlin.packageToUrl(model.pkg);%>
(function () {
    initVue();

    function initVue() {
<%
if (!isEmpty(model.attrs))
{%>
        var conditionVar = {
<%
for (attr in model.attrs) {
    var columnName = kotlin.dbcolToClzAttr(attr.columnName);
    var str = columnName+":false,";
    print(str);
}
%>
        };
<%}%>
        var tableVar = Object.assign({},conditionVar);
        var config = {
            el: "#app",
            data: function () {
                return {
                    condition: conditionVar,
                    table:tableVar
                }
            },
            methods: {
                save:function(){
                    localStorage.setItem("condition:${urlPath}",JSON.stringify(vm.$data.condition))
                    localStorage.setItem("table:${urlPath}",JSON.stringify(vm.$data.table))
                    layx.msg("配置保存完毕！", {dialogIcon: 'success'});
                },
                configUI:function(){
                    var urlVar = "${urlPath}";
                    var conditionConfigStr = localStorage.getItem("condition:" + urlVar)
                    var tableConfigStr = localStorage.getItem("table:" + urlVar)
                    if(conditionConfigStr!=null&&conditionConfigStr.trim().length>0){
                        window.vm.$data.condition=JSON.parse(conditionConfigStr)
                        window.vm.$data.table=JSON.parse(tableConfigStr)
                    }
                }
            }
        }
        window.vm = new Vue(config);
        window.vm.configUI();
    }
})();