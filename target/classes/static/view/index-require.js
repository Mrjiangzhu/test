require.config({
    paths: {
        text: ["/lib/require/text"],
        html: ["/lib/require/html"],
        link: ["/lib/require/link"],
        toolkit: ["/lib/require/toolkit"]
    }
});

require(["toolkit"], function (tool) {
    var config = {
        el: "#app",
        data() {
            return {
                temp: {
                    menuState: "menu",
                    menuBarIcon:":iconfont icon-repairfill",
                    menuBarColor:"indigo400",
                    iframeSrc:"/xiou/user/list.html"
                },
                events: false,
                calls: false,
                messages: false,
                notifications: false,
                sounds: false,
                videoSounds: false,
                open: 'send'
            }
        },
        methods: {
            toggleMenu:function(){
                var app=this;
                if(app.temp.menuState==="menu"){
                    app.temp.menuState="config";
                    app.temp.menuBarIcon=":iconfont icon-menu";
                    app.temp.menuBarColor="primary";
                }else{
                    app.temp.menuState="menu";
                    app.temp.menuBarIcon=":iconfont icon-repairfill";
                    app.temp.menuBarColor="indigo400";
                }
            }
        }
    }
    window.vm = new Vue(config);
});
