<#import "/common/macro_paging.ftl" as html>
<#import "/common/macro_operate.ftl" as operate>
<#import "/common/macro_common.ftl" as common>
<@common.html title="餐桌列表">
<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i>餐桌管理</a></li>
            <li>餐桌列表</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="icon icon-user"></i>餐桌列表
                </div>
                <div class="panel-body">
                    <div class="tableToolContainer" style="margin-bottom:15px">
                        <form class="form-inline" action="/admin/table/list">
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;餐桌编号：&nbsp;</label>
                                <input type="text" class="form-control" name="tableSn" value="${(param.tableSn)!}">
                            </div>
                            &nbsp;&nbsp;
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;容纳人数：&nbsp;</label>
                                <input type="text" class="form-control" name="num" value="${(param.num)!}">
                            </div>
                            &nbsp;&nbsp;
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;无人：&nbsp;</label>
                                <input type="checkbox" name="state" <#if param.state!true>checked</#if>>
                            </div>
                            &nbsp;&nbsp;
                            <button class="btn btn-success" type="submit">
                                <i class="fa fa-fw fa-search"></i>搜索
                            </button>

                            <div class="tableToolContainer" style="margin-bottom:15px; float: right">
                            <@shiro.hasPermission name="table_man.add" >
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
                            <th class="tableCenter">餐桌编号</th>
                            <th class="tableCenter">容纳人数</th>
                            <th class="tableCenter">是否有人</th>
                            <th class="tableCenter">二维码缩略图</th>
                            <th style="width: 250px;" class="tableCenter">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list tables?if_exists as table>
                            <tr>
                                <td class="tableCenter">${table.tableSn}</td>
                                <td class="tableCenter">${table.num}</td>
                                <td class="tableCenter"><#if table.flag!false>有人<#else >无人</#if></td>
                                <td class="tableCenter"><#if table.code??><img id="down_img_${(table.id?default(0)?c)}" style="width: 60px;height: 60px;" src="/images/${table.code}"><#else >暂无二维码</#if></td>
                                <td class="tableCenter">
                            <@shiro.hasPermission name="table_man.del" >
                                    <a href="javascript:void(0)"
                                       onclick="del(${table.id?default(0)?c})"
                                       class="btn btn-sm btn-danger">
                                        <i class="icon icon-remove"></i>删除
                                    </a>
                                </@shiro.hasPermission>
                                    &nbsp;<a href="javascript:void(0)" class="btn btn-sm btn-primary" onclick="downloadCode(${table.id?default(0)?c})">
                                        <i class="icon icon-download-alt"></i>查看二维码大图
                                    </a>
                            </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer" style="text-align:center">
                    <@html.paging page=page param=param action="/admin/table/list"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function downloadCode(id) {
        console.log(id);
        var img = document.getElementById("down_img_"+id);
        var url=img.src;
        var name='show';
        var iWidth=350;
        var iHeight=350;
        var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
        window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
    }
    function doAdd() {
        $("#add_table").modal("show");
    }
    function onSave() {
        var tableSn = $("#table_sn").val();
        var num = $("#num").val();
        $.post(
                "/api/admin/table/add",
                {
                    tableSn: tableSn,
                    num: num
                },
                function (data) {
                    if (data.code < 0){
                        alertShow("warning", data.message, 2000);
                    }else{
                        alertShow("info", "新增成功", 2000);
                        setTimeout(function () {
                            window.location.reload();
                        }, 2000);
                    }
                }
        );
    }
    function del(id) {

        warningModal("确定删除该餐桌吗?", function () {
            $.post(
                    "/api/admin/table/delete",
                    {id: id},
                    function (data) {
                        if (data.code < 0) {
                            alertShow("danger", data.message, 3000);
                        } else {
                            alertShow("info", "删除成功", 2000);
                            setTimeout(function () {
                                window.location.reload();
                            }, 2000);
                        }
                    }
            );
        });
    }
</script>
</body>
    <@operate.modal "增加餐桌" "add_table" "onSave">
    <div class="modalForm" style="padding:10px;">
        <input type="hidden" id="edit_id" name="id" value="">
        <div class="form-group">
            <label class="col-sm-3  control-label">餐桌编号:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="table_sn" name="tableSn">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3  control-label">容纳人数:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="num" name="num">
                <span class="help-block"></span>
            </div>
        </div>
    </@operate.modal>
</@common.html>