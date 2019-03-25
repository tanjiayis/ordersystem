<#import "/common/macro_common.ftl" as common>
<#import "/common/macro_paging.ftl" as html>
<#import "/common/macro_operate.ftl" as operate>
<!DOCTYPE html>
<html>
<@common.html>
<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i> 权限管理</a></li>
            <li class="active">角色管理</li>
        </ol>
    </div>
    <div class="row content-bottom">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="icon icon-user"></i>
                   角色
                </div>
                <div class="panel-body">
                    <div class="tableToolContainer" style="margin-bottom:15px; float: right">
                        <@shiro.hasPermission name="pri_role.add" >
                            <a href="#" class="btn btn-primary" id="roleAddButton">
                                <i class="icon icon-plus"></i>添加
                            </a>
                        </@shiro.hasPermission>
                    </div>
                    <table id="" class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th style="width:50%" class="tableCenter">角色名称</th>
                            <th style="width:50%" class="tableCenter">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list roles?if_exists as role>
                            <tr>
                                <td class="tableCenter">${role.name}</td>
                                <td class="tableCenter">
                                    <@shiro.hasPermission name="pri_role.update" >
                                    <a href="/privilege/permissions?id=${role.id?default(0)?c}"
                                       class="btn btn-success permissions"><i class="icon icon-check"></i>授权</a>
                                </@shiro.hasPermission>
                                <@shiro.hasPermission name="pri_role.del" >
                                    <a href="javascript:void(0)" onclick="delRole(${role.id?default(0)?c})"
                                       class="btn btn-danger"><i class="icon icon-remove"></i>删除</a>
                                </@shiro.hasPermission>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer" style="text-align:center">
                    <#--<@html.paging page=page action="/privilege/roles"/>-->
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script>
    jQuery(document).ready(function () {
        $("#roleAddButton").on("click", function () {
            $("#roleAddModal").modal("show");
        });
        $("#submitButton").on("click", function () {
            addRole();
        });
        $("#roleAddModal").on("hidden.bs.modal", function () {
            $("#roleAddForm")[0].reset();
        });
    });
</script>
</body>


<@operate.add "新增" "#roleAddButton" "addRole">
    <div class="modalForm" style="padding:10px;">
        <div class="form-group">
            <label class="col-sm-3  control-label">角色名称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="roleName" name="roleName">
                <span class="help-block"></span>
            </div>
        </div>
    </div>
</@operate.add>
<script>
    function addRole() {
        var name = $("#roleName").val();
        $.post(
                "/api/role/add",
                {name: name},
                function (data) {
                    if (data.code < 0) {
                        alertShow("warning", data.message, 3000);
                    } else {
                        $("#roleAddModal").modal("hide");
                        window.location.reload();
                    }
                }
        );
    }
    function delRole(roleId){
        warningModal("确定要删除吗?", function(){
            $.post(
                    "/api/role/delete",
                    {id:roleId},
                    function(data){
                        console.log(data);
                        if(data.code < 0){
                            alertShow("warning", data.message, 3000);
                        }else{
                            window.location.reload();
                        }
                    }
            );
        });
    }
</script>

</@common.html>