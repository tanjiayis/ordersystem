<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>请点餐</title>
    <link href="/mzui/css/mzui.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/icon/iconfont.css">
    <script type="text/javascript" src="/mzui/lib/jquery/jquery-3.2.1.min.js" charset="UTF-8"></script>
    <script src="/mzui/js/mzui.min.js"></script>
    <style>
        .footer{
            width:100%;
            height:40px;
            display: flex;
            color: #fff;
            background-color: grey;
        }
        .footer .price{
            padding-left: 30px;
            height:40px;
            line-height: 40px;
        }
        .footer .confirm{
            text-align: center;
            width:30%;
            height: 40px;
            line-height: 40px;
        }
        #typeList li{
            list-style-type: none;
        }
        .menus{
            float: left; margin-left: 20px;margin-top: 20px;text-align: center;overflow: hidden;
        }
        .menus img{
            width: 100px;height: 100px;
        }
    </style>
</head>
<body>
    <nav class="nav nav-auto affix dock-top justified">
        <a class="success strong" href="/web?tableSn=${tableSn}">点餐</a>
        <a  href="/admin/remark/web?tableSn=${tableSn}">评价</a>
        <a  href="/web/my_order?tableSn=${tableSn}">我的订单</a>
    </nav>
    <#--中部内容-->
    <div style="margin-top: 48px;">
        <ul style="width: auto; height: 100%" id="typeList">
        <#list typeAndMenus?if_exists as types>
            <li class="nav-parent">
                <a href="javascript:;">${types.typeName}</a>
                <div>
                    <div style="overflow: hidden;">
                        <#if types.menus??>
                            <#list types.menus?if_exists as menus>
                                <div class="menus">
                                    <div>
                                        <#if menus.image??>
                                            <img src="/images/menus/${menus.image}">
                                        <#else >
                                            <img  src="/images/menus/no.png">
                                        </#if>
                                    </div>
                                    <div style="padding: 0px;">
                                        <div style="padding: 0px;font-size: 20px;color: red;"><span id="menu_${menus.id?c}">${menus.price}</span>&nbsp;元</div>
                                        <div><input class="type_menu"  type="checkbox" name="menu" value="${menus.id?c}">${menus.name}</div>
                                    </div>
                                </div>
                            </#list>
                        <#else >
                            <dd><a href="javascript:;">此类型没有菜品了</a></dd>
                        </#if>
                    </div>
                </div>
            </li>
        </#list>
        </ul>
        <div style="height: 40px;"></div>
    </div>
    <#--底部-->
    <div class="footer affix dock-bottom">
        <input id="tableSn" type="hidden" value="${tableSn}">
        <div id="footer_price" class="price strong" href="/web?tableSn=${tableSn}" style="width: 70%;">
            <sapn class="iconfont icon-gouwuche"></sapn>
            <span id="total_price"></span></div>
        <div class="confirm"  id="ok">去下单</div>
    </div>
<script>
    var total_price = 0;
    function sidebarSlide() {
        var num0 = 0;
        var id = document.getElementsByName("menu");
        for (var i = 0; i<id.length; i++){
            if (id[i].checked){
                num0++;
            }
        }
        if (num0 < 1){
            $("#ok").removeClass("green");
            $("#total_price").html("未选购菜品");
        }
        $(".type_menu").click(function () {
            var value = $("#menu_"+$(this).val()).html();
            if ($(this).is(":checked")){
                total_price = Number(total_price)+Number(value);
                $("#total_price").html(total_price.toFixed(2)+"元");
                $("#ok").addClass("green");
            }else{
                var num = 0;
                total_price = Number(total_price)-Number(value);
                $("#total_price").html(total_price.toFixed(2)+"元");
                var id = document.getElementsByName("menu");
                for (var i = 0; i<id.length; i++){
                    if (id[i].checked){
                        num++;
                    }
                }
                if (num < 1){
                    $("#ok").removeClass("green");
                    $("#total_price").html("未选购菜品");
                }
            }
        });

        $("#typeList li a").click(function () {
            console.log(123445);
            $(this).parent("li.nav-parent").find("div.menus").slideToggle(400);
        });
    };

    $("#ok").click(function () {
        var value = new Array;
        var tableSn = $("#tableSn").val();
        var id = document.getElementsByName("menu");
        for (var i = 0; i<id.length; i++){
            if (id[i].checked){
                value.push(parseInt(id[i].value))
            }
        }
        $.post(
                "/api/admin/order/add",
                {
                    menuIds: value.join(','),
                    tableSn: tableSn
                },
                function (data) {
                    if (data.code < 0){
                        $.messager.show(data.message, {type: 'warning', placement: 'top'});
                    }else{
                        $.messager.show('稍后为你上餐', {type: 'success', placement: 'top'});
                    }
                }
        );
    });
    $(document).ready(function(){
        sidebarSlide();
    });
</script>
</body>
</html>