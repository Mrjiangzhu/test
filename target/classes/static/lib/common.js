if (typeof(layx) === "undefined") {
    if (typeof(parent.layx) === "undefined") {
        layx = parent.parent.layx
    } else {
        layx = parent.layx
    }
}
window.Toolkit = {
    param: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    },
    urlEncode: function (param, key, encode) {
        if (param == null) return '';
        var paramStr = '';
        var t = typeof (param);
        if (t == 'string' || t == 'number' || t == 'boolean') {
            paramStr += '&' + key + '=' + ((encode == null || encode) ? encodeURIComponent(param) : param);
        } else {
            for (var i in param) {
                var k = key == null ? i : key + (param instanceof Array ? '[' + i + ']' : '.' + i);
                if (typeof(param[i]) == "string") {
                    param[i] = param[i].trim()
                }
                paramStr += Toolkit.urlEncode(param[i], k, encode);
            }
        }
        return paramStr;
    },
    logout: function () {
        if (window != top) {
            top.location.href = location.href;
        }
        else {
            window.parent.location.href = "/view/page/login/login.html"
            layx.confirm('确认', '会话结束，请重新登录', function (id) {
                sessionStorage["token"] = ""
                sessionStorage["user"] = ""
                layx.destroy(id);
            });
        }
    },
    openWindow: function (urlVar, appconfig, titleVar) {
        if (typeof (titleVar) == "undefined") {
            titleVar = ""
        }
        if (appconfig.hasOwnProperty("winid") === false) {
            appconfig["winid"] = titleVar;//窗口的唯一标识，如果没有设置，就设置成标题
        }
        var btns = [
            {
                label: '确定',
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
            shadable: 0.8,
            shadeDestroy: true,   // 点击空白地方关闭
            useFrameTitle: true, maxable: true,
            width: 400, height: 600,
            storeStatus: false, skin: "default",
            controlStyle: 'background-color: #1AA094; color:#fff;',
            border: true, shadow: true,
            statusBar: true,
            buttons: btns
        }
        options = Object.assign(options, appconfig)
        layx.iframe(appconfig["winid"], titleVar, urlVar, options);
    },
    authorConfig: function (jq, dialog) {

        var clickCallbackFun = function (index) {
            if (window.location.href.endsWith("login/login.html")) {
                $(".layui-disabled").html("登录")
                $(".layui-disabled").removeClass("layui-disabled")
                layer.close(index);
            } else {
                location.href = "/view/page/login/login.html"
            }

        }
        jq.ajaxSetup({
            contentType: 'application/json;charset=UTF-8',
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", sessionStorage["token"]);
            },
            error: function (err) {
                switch (true) {
                    case err.status == 401:
                        // 返回 401 清除token信息并跳转到登录页面
                        dialog.alert(err.responseText, {
                            skin: 'layui-layer-molv' //样式类名
                            , closeBtn: 0
                        }, clickCallbackFun)
                        break;
                    case err.status == 404:
                        dialog.alert(err.responseText || "没有数据", {
                            skin: 'layui-layer-molv' //样式类名
                            , closeBtn: 0
                        }, clickCallbackFun)
                        break;
                    case  err.status >= 400 && err.status < 500:
                        dialog.alert(err.responseText || "没有找到对应的资源", {
                            skin: 'layui-layer-molv' //样式类名
                            , closeBtn: 0
                        }, clickCallbackFun)
                        break;
                    case  err.status >= 500 && err.status < 600:
                        dialog.alert(err.responseText || "程序出现错误", {
                            skin: 'layui-layer-molv' //样式类名
                            , closeBtn: 0
                        }, clickCallbackFun)
                        break;
                    default:
                        dialog.alert(err.responseText, {
                            skin: 'layui-layer-molv' //样式类名
                            , closeBtn: 0
                        }, clickCallbackFun)
                }
            }
        });
    },
    formatDate: function (dateType, time) {
        var date;
        if (time != undefined) {
            date = new Date(time);
        } else {
            date = new Date();
        }
        // console.log("date===" + date );
        var year = date.getFullYear();
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        switch (dateType) {
            case 'date':
                return year + "-" + month + "-" + day;
                break;
            case 'time':
                return hours + ":" + minutes + ":" + seconds;
                break;
            case 'datetime':
                return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
                break;
            default:
                break;
        }
    },
}
if (typeof jQuery != 'undefined') {
    window.Toolkit.authorConfig(jQuery, layx)
}
if (typeof $ != 'undefined') {
    window.Toolkit.authorConfig($, layx)
}
if (typeof layui != 'undefined' && typeof layui.jquery != 'undefined') {
    window.Toolkit.authorConfig(layui.jquery, layui.layer)
}
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

if (typeof(axios) != "undefined") {
    axios.interceptors.request.use(function (config) {
        config.headers.Authorization = sessionStorage["token"]
        return config
    })
    // 添加响应拦截器
    axios.interceptors.response.use(function (response) {
            return response;
        }, function (error) {
            switch (true) {
                case error.response.status == 401:
                    // 返回 401 清除token信息并跳转到登录页面
                    window.Toolkit.logout();
                    break;
                case error.response.status == 404:
                    layx.msg("没有数据", {dialogIcon: 'warn'});
                    break;
                case  error.response.status >= 400 && error.response.status < 500:
                    layx.msg("没有找到对应的资源", {dialogIcon: 'error'});
                    break;
                case  error.response.status >= 500 && error.response.status < 600:
                    layx.msg("程序出现错误", {dialogIcon: 'error'});
                    break;
            }
            return Promise.reject(error)
        }
    );
}