<#import "/common/macro_common.ftl" as common>
<@common.html>
<body>
<style>
    .count {
        text-align: center;
        width: 100px;
    }
</style>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i>菜品管理</a></li>
            <li><a href="#">菜品分类</a></li>
        </ol>
    </div>
    <div class="row content-bottom">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-body">
                    <form class="form-horizontal" id="project_form">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <i class="fa fa-comments-o"></i>
                                ${(type.typeName)!}详情
                            </div>
                            <div class="tableToolContainer" style="margin-top:15px">
                                <div class="tableToolContainer" style="margin-top:15px">
                                    <div class="form-group">
                                        <label class="col-md-2">类别名称:</label>
                                        <div class="col-md-3">
                                            <input class="form-control" readonly value="${(type.typeName)!}" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <i class="fa fa-comments-o"></i>
                            《${(type.typeName)!}》类下所有菜品清单
                            </div>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th style="width: 15%">#</th>
                                    <th>菜名</th>
                                    <th class="tableCenter">价格</th>
                                </tr>
                                </thead>
                                <tbody id="project_spares">
                                    <#list menus?if_exists as menu>
                                    <tr>
                                        <td>${(menu.id?c)}</td>
                                        <td>${menu.name!}</td>
                                        <td class="tableCenter">${menu.price!}</td>
                                    </tr>
                                    </#list>

                                </tbody>
                            </table>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-5  col-md-4">
                                <input type="button" class="btn btn-default" onclick="javascript:history.back(-1);" value="返回"></input>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</@common.html>