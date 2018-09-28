layui.define(['layer', 'util'], function (exports) {
    "use strict";
    var $ = layui.jquery;
    var layer = layui.layer;
    var util = layui.util;
    var MOD_NAME = 'utils';
    var utils = {
        renderLayerForm: function (form, layero, filter) {
            this.expLayerForm(layero, filter);
            form.render();
        },
        expLayerForm: function (layero, filter) {
            layero.addClass("layui-form");
            layero.find(".layui-layer-btn0").attr("lay-filter", filter).attr("lay-submit", "");
        }
    };

    (function ($) {

        $.fn.serializeObject = function () {
            var obj = {};
            var count = 0;
            $.each(this.serializeArray(), function (i, o) {
                var n = o.name, v = o.value;
                count++;
                obj[n] = obj[n] === undefined ? v
                    : $.isArray(obj[n]) ? obj[n].concat(v)
                        : [obj[n], v];
            });
            //obj.nameCounts = count + "";//表单name个数
            return JSON.stringify(obj);
        };

        $.fn.serializeJson = function () {
            var serializeObj = {};
            var array = this.serializeArray();
            var str = this.serialize();
            $(array).each(function () {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [serializeObj[this.name], this.value];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
            return serializeObj;
        };
    })($);

    utils = $.extend({}, utils, util);

    exports(MOD_NAME, utils);
});