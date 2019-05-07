var baseUrl = 'https://daoweicloud.com/demo/area';
var app = new Vue({
    el: '#app',
    data() {
        return {
            obj:{},
            data: [
                {
                    name:'张三',
                    sex:'男'
                }, {
                    name:'李四',
                    sex:'女'
                }, {
                    name:'王五',
                    sex:'女'
                }, {
                    name:'赵六',
                    sex:'男'
                }
            ],
            option:{
                page:false,
                align:'center',
                menuAlign:'center',
                dialogFullscreen:true,
                stripe:true,
                border:true,
                column:[
                    {
                        label:'姓名',
                        prop:'name'
                    },
                    {
                        label:'性别',
                        prop:'sex'
                    }
                ]
            }
        }
    }
})