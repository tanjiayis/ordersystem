<#import "/common/macro_common.ftl" as common>
<@common.html>
<head>
    <style>
        body{
            font-family: Helvetica, Tahoma, Arial, sans-serif;
            font-size: 14px;
            line-height: 1.53846154;
            color: #FFFFFF;
            background-color:#364150;
        }
        nav{
            width:160px;
            display:block;
            margin-top:20px;
        }
        nav.menu>ul.nav{
            padding-left: 0;
            margin-bottom: 0;
            list-style: none;
            margin-top:0;
        }
        nav.menu>ul.nav>li{
            display:block;
            position:relative;
            float:none;
        }
        nav.menu>ul.nav>li,nav.menu>ul.nav>li>a{
            margin-left:0;
            color:#FFFFFF;
        }
        nav.menu>ul.nav>li:first-child{
            margin-top:0;
        }
        nav.menu>ul.nav>li:first-child>a,nav.menu>ul.nav>li:last-child>a{
            border-top-left-radius: 0;
            border-top-right-radius: 0;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        nav.menu>ul.nav-primary>li>a{
            border-top:1px solid #6F6F6F;
            border-bottom:1px solid #6F6F6F;
            border-left:none;
            border-right:none;
        }
        nav.menu>ul.nav-primary>li.active>a,nav.menu>ul.nav-primary>li>a:focus,nav.menu>ul.nav-primary>li>a:hover{
            background-color:#2c3542;
            color:#FFFFFF;
            border-color: #6F6F6F;
        }
        nav.menu>ul.nav>li>ul.nav>li>a,.menu>ul.nav>li>ul.nav>li>a:focus,.menu>ul.nav >li>ul.nav>li>a:hover{
            border:none;
        }
        nav.menu>ul.nav>li>ul.nav>li>a{
            background-color:#384552;
            color:#FFFFFF;
        }
        nav.menu>ul.nav>li>ul.nav>li>a:hover{
            background-color:#A0A0A0;
            color:#FFFFFF;
        }
        .menu>ul.nav>li>ul.nav>li.active>a,.menu>.nav>li>.nav>li.active>a:hover,.menu>.nav>li>.nav> li.active>a:focus{
            background-color:#1caf9a;
            color:#FFFFFF;
            border:none;
        }
        nav.menu > ul.nav > li > ul.nav > li > a {
            background-color: #384552;
            color: #FFFFFF;
        }
        .menu>.nav>li.show>a, .menu>.nav>li.show>a:focus, .menu>.nav>li.show>a:hover{
            color:#FFFFFF;
            background-color: #384552;
            border-color: #6F6F6F;
        }
        .menu>.nav>li.show>a:focus>[class*=icon-], .menu>.nav>li.show>a:hover>[class*=icon-], .menu>.nav>li.show>a>[class*=icon-] {
            color: #FFFFFF;
        }
    </style>
</head>
<body>
<nav class="menu" data-toggle="menu">
    <ul class="nav nav-primary" id="menusList">
        <#--<li id="primary-0" class="nav-parent active">-->
            <#--<a href="javascript:void(0)"><i class="icon icon-user"></i><span> 权限管理 </span><i class="icon-chevron-right nav-parent-fold-icon"></i></a>-->
            <#--<ul class="nav ulNav" style="display: block;">-->
                <#--<li><a target="mainFrame" href="javascript:void(0)"><i class="fa fa-circle-o"></i>&nbsp;&nbsp;分配权限</a></li>-->
            <#--</ul>-->
        <#--</li>-->
        <li id="primary-0" class="nav-parent active">
            <a href="javascript:void(0)"><i class="icon icon-user"></i><span> 餐桌管理 </span><i class="icon-chevron-right nav-parent-fold-icon"></i></a>
            <ul class="nav ulNav" style="display: block;">
                <li><a target="mainFrame" href="/admin/table/list"><i class="fa fa-circle-o"></i>&nbsp;&nbsp;餐桌列表</a></li>
            </ul>
        </li>
        <li id="primary-0" class="nav-parent active">
            <a href="javascript:void(0)"><i class="icon icon-user"></i><span> 菜品管理 </span><i class="icon-chevron-right nav-parent-fold-icon"></i></a>
            <ul class="nav ulNav" style="display: block;">
                <li><a target="mainFrame" href="/menu/list"><i class="fa fa-circle-o"></i>&nbsp;&nbsp;菜品列表</a></li>
                <li><a target="mainFrame" href="/menu/category/list"><i class="fa fa-circle-o"></i>&nbsp;&nbsp;菜品分类</a></li>
            </ul>
        </li>
        <li id="primary-0" class="nav-parent active">
            <a href="javascript:void(0)"><i class="icon icon-user"></i><span> 订单管理 </span><i class="icon-chevron-right nav-parent-fold-icon"></i></a>
            <ul class="nav ulNav" style="display: block;">
                <li><a target="mainFrame" href="/admin/order/list"><i class="fa fa-circle-o"></i>&nbsp;&nbsp;订单列表</a></li>
                <#--<li><a target="mainFrame" href="/admin/order/report"><i class="fa fa-circle-o"></i>&nbsp;&nbsp;订单报表</a></li>-->
            </ul>
        </li>
        <li id="primary-0" class="nav-parent active">
            <a href="javascript:void(0)"><i class="icon icon-user"></i><span> 评论管理 </span><i class="icon-chevron-right nav-parent-fold-icon"></i></a>
            <ul class="nav ulNav" style="display: block;">
                <li><a target="mainFrame" href="/admin/remark/list"><i class="fa fa-circle-o"></i>&nbsp;&nbsp;评论列表</a></li>
            </ul>
        </li>
    </ul>
</nav>
<script type="text/javascript">
    function sidebarSlide(){
        $("#menusList").on("click","li.list>a",function(){
            $(this).parent("li.nav-parent").find("ul.nav").slideToggle(400);
            $(this).parent("li.nav-parent").toggleClass("active");
            $(this).parent("li.nav-parent").siblings('li').removeClass('show');
            $(this).find("i.nav-parent-fold-icon").toggleClass("icon-rotate-90");
            $(this).parent("li.nav-parent").siblings("li.nav-parent").find(".ulNav").slideUp(400);
            $(this).parent("li.nav-parent").siblings("li.nav-parent").removeClass("active");
            $(this).parent("li.nav-parent").siblings("li.nav-parent").find("a>i.icon-rotate-90").removeClass("icon-rotate-90");
        });
        var prevClick=$("ul.nav>li>a");
        $("#menusList").on("click","ul.nav>li>a",function(){
            prevClick.parent("li").removeClass("active");
            $(this).parent("li").addClass("active");
            prevClick=$(this);
        });
    }
    $(document).ready(function(){
        sidebarSlide();
    });
</script>
</body>
</@common.html>