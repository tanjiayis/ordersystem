<#import "/common/macro_paging.ftl" as html>
<#import "/common/macro_operate.ftl" as operate>
<#import "/common/macro_common.ftl" as common>
<@common.html includeDate=true title="用户管理">
<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i>权限管理</a></li>
            <li>用户管理</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="icon icon-user"></i>用户列表
                </div>
                <div class="panel-body">
                    <div class="tableToolContainer" style="margin-bottom:15px">
                        <form class="form-inline" action="/privilege/users">
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;姓名：</label>
                                <input type="text" class="form-control" name="realName" id="realName" value="${(param.realName)!}">
                            </div>
                            &nbsp;
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;所属角色：</label>
                                <select class="form-control" name="roleId" id="role">
                                    <option value="0">&nbsp;&nbsp;&nbsp;&nbsp;全部&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                    <#list roles?if_exists as role>
                                        <option value="${(role.id?default(0)?c)}">&nbsp;&nbsp;&nbsp;&nbsp;${(role.name)!}&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                    </#list>
                                </select>
                                <script> $("#role").val('${(param.roleId?default(0)?c)}'); </script>
                            </div>
                            &nbsp;
                            <button class="btn btn-success" type="submit">
                                <i class="fa fa-fw fa-search"></i>搜索
                            </button>
                            <div class="tableToolContainer" style="margin-bottom:15px; float: right">
                                <@shiro.hasPermission name="pri_user.add" >
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
                            <th class="tableCenter">
                                <input type="checkbox" name="" value="">
                            </th>
                            <th class="tableCenter">用户名</th>
                            <th class="tableCenter">姓名</th>
                            <th class="tableCenter">所属角色</th>
                            <th class="tableCenter">性别</th>
                            <th class="tableCenter">更新时间</th>
                            <th class="tableCenter">联系电话</th>
                            <th class="tableCenter">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list users?if_exists as user>
                            <tr>
                                <td class="tableCenter">
                                    <input type="checkbox" value="1" name="">${(user.id?default(0)?c)}
                                </td>
                                <td class="tableCenter">${(user.username)!}</td>
                                <td class="tableCenter">${(user.realName)!}</td>
                                <td class="tableCenter">${(user.roleName)!}</td>
                                <td class="tableCenter">${(user.gender.getValue())!}</td>
                                <td class="tableCenter">${(user.updateTime?string("yyyy-MM-dd HH:mm:ss"))}</td>
                                <td class="tableCenter">${(user.mobile)!}</td>
                                <td class="tableCenter">
                                    <@shiro.hasPermission name="pri_user.update">
                                        <a href="javascript:void(0)" onclick="doEdit(${user.id?default(0)?c})"
                                           class="btn btn-sm btn-success"><i class="fa fa-pencil-square-o"></i>编辑
                                        </a>
                                    </@shiro.hasPermission>
                                    <@shiro.hasPermission name="pri_user.del" >
                                            <a href="javascript:void(0)" onclick="del(${user.id?default(0)?c})"
                                               class="btn btn-sm btn-danger"><i class="icon icon-remove"></i>删除
                                            </a>
                                    </@shiro.hasPermission>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer" style="text-align:center">
                    <#--<@html.paging page=page param=param action="/admin/remark/list"/>-->
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function doAdd() {
        $("#user_id").val(0);
        $("#add_user").modal("show");
    }
    function doEdit(id) {
        $.post("/api/user/find?id="+id,
            function (data) {
                if (data.code < 0){
                    alertShow("warning", data.message, 2000);
                }else{
                    $("#user_id").val(id);
                    $("#user_name").val(data.result.username);
                    $("#real_name").val(data.result.realName);
                    $("#password").val(data.result.password);
                    $("#mobile").val(data.result.mobile);
                    $("#add_user_header").html("编辑")
//                    $("#user_id").val(id);
                    $("input:radio[value='"+data.result.gender+"']").attr('checked','true');
                    $("input:radio[value='"+data.result.roleId+"']").attr('checked','true');
                    $("#add_user").modal("show");
                }
            });
    }
    function onSave() {
        var userId = $("#user_id").val();
        var username = $("#user_name").val();
        var password = $("#password").val();
        var realName = $("#real_name").val();
        var mobile = $("#mobile").val();
        var gender = $("input[name='gender']:checked").val();
        var roleId = $("input[name='role']:checked").val();
        $.post(
            userId == 0? "/api/user/add" : "/api/user/update",
                {
                    userId: userId,
                    username: username,
                    password: password,
                    realName: realName,
                    mobile: mobile,
                    gender: gender,
                    roleId: roleId
                },
                function (data) {
                    if (data.code < 0){
                        alertShow("warning", data.message, 2000);
                    }else{
                        window.location.reload();
                    }
                }
        );
    }

    function del(id) {
        warningModal("确定删除吗?", function () {
            $.post("/api/user/delete?id="+id,
                function (data) {
                    if (data.code < 0){
                        alertShow("warning", data.message, 2000);
                    }else{
                        window.location.reload();
                    }
                }
            );
        });
    }
</script>
</body>
    <@operate.modal "增加用户" "add_user" "onSave">
    <div class="modalForm" style="padding:10px;">
        <input type="hidden" id="user_id" name="id" value="">
        <div class="form-group">
            <label class="col-sm-3  control-label">*用户名:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="user_name" name="username">
                <span class="help-block"></span>
            </div>
            <label class="col-sm-2  control-label">密 码:</label>
            <div class="col-sm-3">
                <input type="password" class="form-control" placeholder="默认密码123456" readonly id="password" name="password">
                <span class="help-block"></span>
            </div>

        </div>
        <div class="form-group">
            <label class="col-sm-3  control-label">姓名:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="real_name" name="realName">
                <span class="help-block"></span>
            </div>
            <label class="col-sm-2  control-label">*联系电话:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="mobile" name="mobile">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3  control-label">性别:</label>
            <div class="col-sm-8" style="padding-top:3px;">
                <#--<input type="radio" class="form-control" id="gender" name="gender">-->
                    <label><input type="radio"  value="man" name="gender">&nbsp;男</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label><input type="radio"  value="women" name="gender">&nbsp;女</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label><input type="radio"  value="other" name="gender">&nbsp;其他</label>&nbsp;&nbsp;&nbsp;&nbsp;
                <span class="help-block"></span>
            </div>
        </div>
        <@shiro.hasPermission name="user_role.update">
        <div class="form-group">
            <label class="col-sm-3  control-label">*所属角色:</label>
            <div class="col-sm-8" style="padding-top:3px;">
                <#list roles?if_exists as role>
                <label><input type="radio"  name="role" value="${role.id}">&nbsp;${role.name}</label>&nbsp;&nbsp;&nbsp;&nbsp;
                </#list>
                <span class="help-block"></span>
            </div>
        </div>
        </@shiro.hasPermission>
    </@operate.modal>
</@common.html>