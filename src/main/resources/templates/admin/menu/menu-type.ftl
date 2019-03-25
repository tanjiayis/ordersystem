<#import "/common/macro_common.ftl" as common>
<#import "/common/macro_operate.ftl" as operate>
<@common.html>
<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i>菜品管理</a></li>
            <li>菜品分类</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="icon icon-user"></i>分类列表
                </div>
                <div class="panel-body">
                    <div class="tableToolContainer" style="margin-bottom:15px">
                        <form class="form-inline" action="/menu/category/list">
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;名称：&nbsp;</label>
                                <input type="text" class="form-control" name="typeName" value="${(param.typeName)!}">
                            </div>
                            &nbsp;&nbsp;
                            <button class="btn btn-success" type="submit">
                                <i class="fa fa-fw fa-search"></i>搜索
                            </button>

                            <div class="tableToolContainer" style="margin-bottom:15px; float: right">
                            <@shiro.hasPermission name="menu_type.add" >
                                <a href="#" class="btn btn-primary" onclick="doAdd()">
                                    <i class="icon icon-plus"></i>添加
                                </a>
                                </@shiro.hasPermission>
                            </div>
                        </form>
                    </div>
                    <table id="" class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th style="width: 20%;" class="tableCenter">#</th>
                            <th style="width: 50%;" class="tableCenter">类别名称</th>
                            <th style="width: 30%;" class="tableCenter">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list types?if_exists as type>
                            <tr>
                                <td class="tableCenter">${(type.id?c)!}</td>
                                <td class="tableCenter">${(type.typeName)!}</td>
                                <td class="tableCenter">
                            <@shiro.hasPermission name="menu_type.update" >
                                    <a href="javascript:void(0)" onclick="doEdit(${type.id?default(0)?c})"
                                       class="btn btn-sm btn-success"><i class="fa fa-pencil-square-o"></i>&nbsp;编辑
                                    </a>
                                </@shiro.hasPermission>
                                <@shiro.hasPermission name="menu_type.del" >
                                    <a href="javascript:void(0)" onclick="del(${type.id?default(0)?c})"
                                       class="btn btn-sm btn-danger"><i class="icon icon-remove"></i>删除
                                    </a>
                                </@shiro.hasPermission>
                                    <@shiro.hasPermission name="menu_type.query" >
                                    <a href="/menu/type?id=${(type.id?c)!}"
                                       class="btn btn-sm btn-info"><i class="fa fa-pencil-square-o"></i>&nbsp;查看分类下菜品
                                    </a>
                                    </@shiro.hasPermission>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <#--<div class="box-footer" style="text-align:center">-->
                    <#--<@html.paging page=page param=param action="/sparepart/tools"/>-->
                <#--</div>-->
            </div>
        </div>
    </div>
</div>
<script>
    function doAdd() {
        $("#type_id").val(0);
        $("#add_type").modal("show");
    }
    function doEdit(id) {
        $.post("/api/admin/menu/type/detail?id="+id,
        function (data) {
            $("#type_id").val(id);
            $("#type_name").val(data.result.typeName);
            $("#add_type_header").text("编辑分类");
            $("#add_type").modal("show");
        });
    }
    function del(id) {
        warningModal("确定删除吗?", function () {
            $.post("/api/admin/menu/type/delete?id="+id,
                    function (data) {
                        if (data.code < 0){
                            alertShow("warning", data.message, 2000);
                        }else{
                            alertShow("info", "删除成功!", 2000);
                            setTimeout(function () {
                                window.location.reload();
                            }, 2000);
                        }
                    }
            );
        });
    }
    function onSave() {
        var id = $("#type_id").val();
        var typeName = $("#type_name").val();
        $.post(
                id==0?"/api/admin/menu/type/add":"/api/admin/menu/type/edit",
                {
                    id:id,
                    typeName: typeName
                },
                function (data) {
                    if (data.code < 0){
                        alertShow("warning", data.message, 2000);
                    }else{
                        alertShow("info", id==0?"新增成功":"编辑成功", 2000);
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    }
                }
        );
    }
</script>
</body>
    <@operate.modal "增加类别" "add_type" "onSave">
    <div class="modalForm" style="padding:10px;">
        <input type="hidden" id="type_id" name="id" value="">
        <div class="form-group">
            <label class="col-sm-3  control-label">类别名称:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="type_name" name="type_name">
                <span class="help-block"></span>
            </div>
        </div>
    </@operate.modal>
</@common.html>