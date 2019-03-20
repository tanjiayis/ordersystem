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
            background-color: #0ab036;
            line-height: 40px;
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
        <ul class="layui-nav layui-nav-tree" style="width: auto; height: 100%" lay-filter="test">
        <#list typeAndMenus?if_exists as types>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">${types.typeName}</a>
                <dl class="layui-nav-child">
                    <div style="overflow: hidden;">
                        <#if types.menus??>
                            <#list types.menus?if_exists as menus>
                                <div style="float: left; margin-left: 20px;margin-top: 20px;text-align: center;overflow: hidden">
                                    <div style="width: 100px;height: 100px;">
                                        <#if menus.image??>
                                            <img style="width: 100px;height: 100px;" src="/images/menus/${menus.image}">
                                        <#else >
                                            <img style="width: 100px;height: 100px;" src="/images/menus/no.png">
                                        </#if>
                                    </div>
                                    <div style="padding: 0px;">
                                        <div style="padding: 0px;"><span style="font-size: 20px;color: red;">${menus.price}&nbsp;元</span></div>
                                        <div><input type="checkbox" name="menu" value="${menus.id?c}">${menus.name}</div>
                                    </div>
                                </div>
                            </#list>
                        <#else >
                            <dd><a href="javascript:;">此类型没有菜品了</a></dd>
                        </#if>
                    </div>
                </dl>
            </li>
        </#list>
        </ul>
        <div style="height: 40px;"></div>
    </div>
    <#--底部-->
    <div class="footer affix dock-bottom">
        <input id="tableSn" type="hidden" value="${tableSn}">
        <div class="price strong" href="/web?tableSn=${tableSn}" style="width: 70%;">
            <sapn class="iconfont icon-gouwuche"><#--<span class="label badge red circle">2</span>--></sapn>
            <span>￥23.3</span></div>
        <div class="confirm" style="width: 30%;" id="ok">去下单</div>
    </div>
<script>
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
</script>
</body>
</html>