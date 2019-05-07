define(function (require) {
    axios.interceptors.request.use(function (config) {
        config.headers.Authorization = sessionStorage["token"];
        return config;
    });
    // 添加响应拦截器
    axios.interceptors.response.use(function (response) {
            return response;
        }, function (error) {
            console.log("axios.interceptors.response.use error:", error)
            switch (true) {
                case error.response.status == 401:
                    // 返回 401 清除token信息并跳转到登录页面
                    MuseUIToast.info("会话过期, 请重新登陆", {title: "警告", type: "warn"})
                    var app = window.top.vm;
                    app.$refs.login.title = "";
                    app.$refs.museAlert.showAlert = false;
                    app.$refs.login.showDialog = true;
                    break;
                case error.response.status == 404:
                    MuseUIMessage.alert("没有数据", {title: "警告", type: "warn"})
                    break;
                case  error.response.status >= 400 && error.response.status < 500:
                    MuseUIMessage.alert("没有找到对应的资源", {title: "警告", type: "warn"})
                    break;
                case  error.response.status >= 500 && error.response.status < 600:
                    MuseUIMessage.alert("没有找到对应的资源", {title: "错误", type: "error"})
                    break;
            }
            return Promise.reject(error)
        }
    );

    function urlEncodeFun(param, key, encode) {
        var app = this;
        console.log("++++++++++++++++++++++++:", app)
        if (param == null) return "";
        var paramStr = "";
        var t = typeof param;
        if (t == "string" || t == "number" || t == "boolean") {
            paramStr +=
                "&" +
                key +
                "=" +
                (encode == null || encode ? encodeURIComponent(param) : param);
        } else {
            for (var i in param) {
                var k =
                    key == null
                        ? i
                        : key + (param instanceof Array ? "[" + i + "]" : "." + i);
                if (typeof param[i] == "string") {
                    param[i] = param[i].trim();
                }
                paramStr += urlEncodeFun(param[i], k, encode);
            }
        }
        return paramStr;
    }

    var methods = {
        getLoginUser: function (vm) {
            var user;
            if (typeof (sessionStorage.user) != "undefined" && sessionStorage.user.length > 0 && sessionStorage.user != "undefined") {
                user = JSON.parse(sessionStorage.user);
            } else {
                var that = this;
                MuseUIMessage.alert('会话失效,请重新登陆', '提示').then(({result}) => {
                    if (result) {
                        that.login(vm)
                    }
                });
                user = {}
            }
            return user;
        },
        login: function (vm) {
            vm.$refs.museAlert.msg = "会话已过期,请重新登陆.....";
            vm.$refs.museAlert.showAlert = true;
            setTimeout(function () {
                vm.$refs.login.title = "";
                vm.$refs.museAlert.showAlert = false;
                vm.$refs.login.showDialog = true;
            }, 1000);
        },
        logout: function (vm) {
            vm.$confirm('确定要登出嘛？', '提示', {
                type: 'warning'
            }).then(({result}) => {
                if (result) {
                    sessionStorage.setItem("token", "")
                    sessionStorage.setItem("user", "")
                    window.location.href = window.location.href;
                } else {

                }
            });
        },
        param: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        },
        noPermission: function () {
        },
        urlEncode: urlEncodeFun
    };
    return methods;
});
