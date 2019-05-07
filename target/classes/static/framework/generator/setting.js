(function () {
    initVue();

    function initVue() {
        var conditionVar = {
caseCode:false,caseType:false,危害程度:false,发案时间:false,发案地点:false,发案单位:false,发案单位性质:false,立案日期:false,作案人数:false,损失价值:false,死亡人数:false,受伤人数:false,专案标识:false,单位作案:false,简要案情:false,现场特征描述:false,作案手段:false,作案特点:false,作案工具:false,选择处所:false,选择时机:false,破销结案标识:false,结销案原因:false,破销结案日期:false,串联号:false,串联依据:false,现场痕迹描述:false,现场物证描述:false,        };
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
                    localStorage.setItem("condition:/ruofeng/app/da011/case",JSON.stringify(vm.$data.condition))
                    localStorage.setItem("table:/ruofeng/app/da011/case",JSON.stringify(vm.$data.table))
                    layx.msg("配置保存完毕！", {dialogIcon: 'success'});
                },
                configUI:function(){
                    var urlVar = "/ruofeng/app/da011/case";
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