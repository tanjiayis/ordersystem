<#import "/common/macro_operate.ftl" as operate>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>评论列表</title>
    <link href="/mzui/css/mzui.min.css" rel="stylesheet">
    <script type="text/javascript" src="/mzui/lib/jquery/jquery-3.2.1.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="/mzui/js/mzui.min.js"></script>
    <script src="/js/alerts.js"></script>
    <style>
        .size{
            width: 3rem;
            height: 3rem;
        }
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
    <a href="/web?tableSn=${tableSn}">点餐</a>
    <a href="/admin/remark/web?tableSn=${tableSn}">评价</a>
    <a class="success strong" href="/web/my_order?tableSn=${tableSn}">我的订单</a>
</nav>
<div style="height: 48px;"></div>
<div id="partial" class="container display fade in" data-display-name="navs"><div class="heading">
    <div class="title"><strong>您的桌号：</strong> <small class="muted">${tableSn}</small></div>
    <nav>温馨提示</nav>
    <nav class="nav">
        <span style="height: 40px;line-height: 40px;">${(order.createTime?string("HH:mm:ss"))!}<i class="icon icon-long-arrow-left"></i>下单</span>
    </nav>
</div>
<div class="list section">
    <#if order_menus?? && (order_menus?size > 0)>
        <#list order_menus?if_exists as menu>
            <a class="item multi-lines with-avatar" style="padding: 5px;">
                <div class="avatar circle red outline size">
                    <#if menu.image??>
                        <img src="/images/menus/${(menu.image)!}">
                    <#else >
                        <img src="/images/menus/no.png">
                    </#if>
                </div>
                <div class="content" style="line-height: 1.5rem;">
                    <span class="title" id="menu_${(menu.menuId?default(0)?c)}">${(menu.name)!}</span>
                    <div class="pull-right label green-pale text-tint"onclick="subMenu(${(menu.menuId?default(0)?c)}, ${(order.id?default(0)?c)})">退餐</div>
                    <div>
                        <small class="text-blue">单价：${(menu.price)!}</small>&nbsp;
                        <small class="muted">份数：${(menu.menuNum?c)}</small>
                        <div class="pull-right label red-pale text-tint">进行中</div>
                    </div>
                </div>
            </a>
        </#list>

    <#else>
        <div style="width: 100%;height: 100px;font-size: 1em;line-height: 100px;text-align: center">你暂时还未点餐的，快去点餐吧！</div>
    </#if>
</div>
<#--底部-->
    <div class="footer affix dock-bottom">
        <input id="tableSn" type="hidden" value="${tableSn}">
        <div class="price strong" href="/web?tableSn=${tableSn}" style="width: 70%;">
            <span>总共消费：￥：${(order.totalPrice)!}元</span>
        </div>
        <a class="confirm" style="width: 30%;" id="ok" href="/web?tableSn=${tableSn}">加菜</a>
    </div>
</body>
<script>
    function subMenu(menuId, orderId) {
        console.log("menuId:"+menuId+",orderId:"+orderId);
        $.post(
                "/api/web/sub",
                {
                    menuId: menuId,
                    orderId: orderId
                },
                function (data) {
                    if (data.code < 0) {
                        $.messager.show(data.message, {type: 'warning', placement: 'top'});
                    } else {
                        window.location.reload();
                    }
                }
        )
    }
</script>
</html>