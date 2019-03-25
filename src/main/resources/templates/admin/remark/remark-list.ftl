<#import "/common/macro_paging.ftl" as html>
<#import "/common/macro_operate.ftl" as operate>
<#import "/common/macro_common.ftl" as common>
<@common.html includeDate=true title="评论列表">
<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i>评论管理</a></li>
            <li>评论列表</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="icon icon-user"></i>评论列表
                </div>
                <div class="panel-body">
                    <div class="tableToolContainer" style="margin-bottom:15px">
                        <form class="form-inline" action="/admin/remark/list">
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;创建时间区间：</label>
                                <input type="text" class="form-control" name="startTime" id="beginDate" value="${(param.startTime)!}">
                                <input type="text" class="form-control" name="endTime" id="endDate" value="${(param.endTime)!}">
                            </div>
                            &nbsp;
                            <button class="btn btn-success" type="submit">
                                <i class="fa fa-fw fa-search"></i>搜索
                            </button>
                        </form>
                    </div>
                    <table id="" class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th class="tableCenter">
                                <input type="checkbox" name="" value="">
                            </th>
                            <th class="tableCenter">评论Id</th>
                            <th class="tableCenter">创建时间</th>
                            <th class="tableCenter">内容</th>
                            <th class="tableCenter">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list remarks?if_exists as remark>
                            <tr>
                                <td class="tableCenter">
                                    <input type="checkbox" value="1" name="">
                                </td>
                                <td class="tableCenter">${(remark.id?c)}</td>
                                <td class="tableCenter">${(remark.createTime?string("yyyy-MM-dd HH:mm:ss"))}</td>
                                <td class="tableCenter">${(remark.content)!}</td>
                                <td class="tableCenter">
                            <@shiro.hasPermission name="remark_man.del" >
                                    <a href="javascript:void(0)" onclick="del(${remark.id?default(0)?c})"
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
                    <@html.paging page=page param=param action="/admin/remark/list"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function del(id) {
        warningModal("确定删除吗?", function () {
            $.post("/api/admin/remark/delete?id="+id,
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
    $(function () {
        $("#beginDate").datepicker({
            language: "zh-CN",
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            maxView: 1,
            forceParse: 0,
            format: 'yyyy-mm-dd'
        });
    });
    $(function () {
        $("#endDate").datepicker({
            language: "zh-CN",
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            maxView: 1,
            forceParse: 0,
            format: 'yyyy-mm-dd'
        });
    });
</script>
</body>
</@common.html>