$(function () {
    var applyJuicer = function ($e, data) {
        var tmplId = $e.attr('tmpl');
        var tmpl = $('#' + tmplId).html();
        $e.html(juicer(tmpl, data));
    }
    //参数
    //type  ajax请求类型，
    //data  传送的数据，
    //url  传送的地址，
    //method 传送成功后调用的方法。
    function test(type, data, url, method) {
        $.ajax({
            type: type,
            data: data,
            url: url,
            dataType: "json",
            success: function (data) {
                method(data);
            },
            error: function () {
                location.reload();
            }
        })
    }
    //测试数据
    var test = [{
        "title":"gwe",
        "detail":"gwgwww",
        "price":11,
        "code":"GEWG222",
        "belong":"广东",
        "carrier":"gwgew",
        "operator":"gwgwgew"
    },{
        "title":"gwe",
        "detail":"gwgwww",
        "price":11,
        "code":"GEWG222",
        "belong":"广东",
        "carrier":"gwgew",
        "operator":"gwgwgew"
    },{
        "title":"gwe",
        "detail":"gwgwww",
        "price":11,
        "code":"GEWG222",
        "belong":"广东",
        "carrier":"gwgew",
        "operator":"gwgwgew"
    },{
        "title":"gwe",
        "detail":"gwgwww",
        "price":11,
        "code":"GEWG222",
        "belong":"广东",
        "carrier":"gwgew",
        "operator":"gwgwgew"
    }]

    //	初始化数据
    function init() {
        var p = $("#nowPage").val();
        var type = "GET",
            data = {
                "page": p
            },
            url = "/flow/background/goodsapi";

        function method() {
            applyJuicer($('#detailMsg'), {
                list: test
            });
        }
        method();

        //test(type, data, url, method);
    }

    init();
    //	重新刷新页面
    function refresh() {
        //location.reload();
    }

    //	删除功能
    function sendDelete() {
        var nowId = $(this).parent().parent().find("td").eq(0).text();
        var target = $("#s-delete").text(nowId);
    }

    function deleteData(id) {
        var x = $("#" + id).html();
    }

    function deleteAction() {
        var tar = $("#s-delete").text(),
            type = "POST",
            data = {
                "id": tar
            },
            url = "/flow/background/goodsdelete";

        function method(res) {
            refresh();
        }

        //test(type, data, url, method);
    }

    //	编辑功能
    function sendEdit() {
        var nowId = $(this).parent().parent().find("td").eq(0).text();
        $("#s-edit").text(nowId);
        var url = "/flow/background/getgoodsbyid",
            type = "GET",
            data = {
                "id": nowId,
            };

        function method(res) {
            var edit = $("#editdaily").find(".modal-body").find("p").find("input");
            edit.eq(0).val(res[0].code);
            edit.eq(1).val(res[0].status);
            edit.eq(2).val(res[0].belong);
            edit.eq(3).val(res[0].carrier);
            edit.eq(4).val(res[0].cost / 100);

            edit.eq(5).val(res[0].title);
            edit.eq(6).val(res[0].detail);
            edit.eq(7).val(res[0].price);

            //设置selecte
            var sel = $("#editdaily").find(".modal-body").find("p").find("select");
            sel.eq(0).find("option[name=" + res[0].belong + "]").prop("selected", true);
            sel.eq(1).find("option[name=" + res[0].carrier + "]").prop("selected", true);
        }

        //test(type, data, url, method);
    }

    function editAction() {
        var dataObj = [],
            id = $("#s-edit").text();
        //for (var i = 0; i < 4; i++) {
        //    var node = $(this).parent().parent().find(".modal-body")
        //        .find("p");
        //    var x = node.eq(i).find("input").val();
        //    dataObj.push(x);
        //}
        var node = $(this).parent().parent().find(".modal-body").find("p");
        dataObj[5] = node.eq(5).find("input").val();
        dataObj[6] = node.eq(6).find("input").val();
        dataObj[7] = node.eq(7).find("input").val();
        console.log(dataObj);
        var type = "POST",
            data = {
                "id": id,
                "title": dataObj[5],
                "detail": dataObj[6],
                "price": dataObj[7]
            },
            url = "/flow/background/goodsupdate";

        function method(data) {
            if (data[0].status == 1) {
                refresh();
            }
        }

        //test(type, data, url, method);
    }

    //	分页功能
    /*var btn = $(".pagination").find("li").find("a");
     btn.on("click", page);*/

    function page() {
        var $this = $(this).text();
    }

    //添加功能

    function add() {
        //		将数据存储在dataObj中
        var dataObj = [];
        for (var i = 0; i < 4; i++) {
            var x = $(this).parent().parent().find(".modal-body")
                .find("p").eq(i).find("input").val();
            dataObj.push(x);
        }
        var node = $(this).parent().parent().find(".modal-body")
            .find("p");
        dataObj[4] = node.find("select").eq(0).find("option:selected").text();
        dataObj[5] = node.find("select").eq(1).find("option:selected").text();

        var url = "/flow/background/goodsapi",
            type = "POST",
            data = {
                "title": dataObj[0],
                "detail": dataObj[1],
                "price": dataObj[2],
                "code": dataObj[3],
                "belong": dataObj[4],
                "carrier": dataObj[5],
            };

        function method(res) {
            if (res[0].status == 1) {
                //refresh();
            }
        }

        //test(type, data, url, method);
    }

    $(document).on('click', ".sendDelete", sendDelete);
    $(document).on('click', ".dailydelect", deleteAction);
    $(document).on("click", ".sendEdit", sendEdit);
    $(document).on('click', ".sureadd", add);
    $(document).on('click', "#update", editAction);
})