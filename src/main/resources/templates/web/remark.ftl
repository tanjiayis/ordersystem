<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>评论列表</title>
    <link href="/mzui/css/mzui.min.css" rel="stylesheet">
    <script type="text/javascript" src="/mzui/lib/jquery/jquery-3.2.1.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="/mzui/js/mzui.min.js"></script>
</head>
<body>
<nav class="nav nav-auto affix dock-top justified">
    <a  href="/web?tableSn=${tableSn}">点餐</a>
    <a  class="success strong" href="/admin/remark/web?tableSn=${tableSn}">评价</a>
    <a  href="/web/my_order?tableSn=${tableSn}">我的订单</a>
</nav>
<div style="margin-top: 48px;" class="section" data-display="modal" data-selector=".btn-reply" data-remote="doc/part/examples/comments-reply.html" data-placement="bottom">
    <div class="heading">
        <div class="title"><i class="icon icon-comments-alt muted"></i> <strong>评论</strong> <small class="muted">(${count})</small></div>
        <button id="new-remark" class="btn primary">新建评论</button>
    </div>
    <div class="list comments">
        <#list remarks?if_exists as remark>
            <div class="item with-avatar multi-lines">
                <a class="avatar circle" data-dark="true" data-skin="" style="background-color: rgb(162, 81, 224); border-color: rgb(162, 81, 224); color: rgb(255, 255, 255);">匿</a>
                <div class="content">
                    <div>
                        <a class="text-link strong">匿名用户</a>
                        <div class="muted small pull-right">${(remark.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</div>
                    </div>
                    <div class="state">${remark.content}</div>
                </div>
            </div>
        </#list>
    </div>
    <div class="divider"></div>
    <nav class="nav justified pager">
        <a class="pager-more"><span>已显示 <strong>${count}</strong> 项，共 <strong>${count}</strong> 项 &nbsp; <span class="text-link">显示更多 <i class="icon icon-double-angle-down"></i></span></span></a></nav>
    <div style="display: none;" class="display modal enter-from-bottom in affix dock-bottom" id="displayTarget-display1260" data-display-name="display1260">
        <div class="heading">
            <div class="title"><i class="icon icon-reply"></i> <strong>发表评价</strong> </div>
            <nav id="cancel" class="nav"><a data-dismiss="display"><i class="icon icon-remove muted"></i></a></nav>
        </div>
    <#--<form action="" class="content has-padding">-->
        <div class="control">
            <textarea name="content" id="content" cols="30" rows="5" class="textarea" placeholder="请和谐评论"></textarea>
        </div>
    <#--</form>-->
        <div class="footer has-padding">
            <button id="submit-remark" class="btn primary">发布</button>
        </div>
    </div>
</div>

<script>
    $("#new-remark").click(function () {
        $("#displayTarget-display1260").css("display", "block");
    });
    $("#cancel").click(function () {
        $("#displayTarget-display1260").css("display", "none");
    });
    $("#submit-remark").click(function () {
        var content = $("#content").val();
        $.post(
                "/api/admin/remark/add",
                {
                    content: content
                },
                function (data) {
                    console.log(data);
                    if (data.code < 0){
                        $.messager.show(data,message, {type: 'warning', placement: 'top'});
                    }else{
                        console.log('留言成功');
                        $.messager.show('留言成功', {type: 'success', placement: 'top'});
                        setTimeout(function () {
                            window.location.reload();
                        }, 1000);
                    }
                },"json");
    });
</script>
</body>
</html>