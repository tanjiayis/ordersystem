<#import "/common/macro_paging.ftl" as html>
<#import "/common/macro_operate.ftl" as operate>
<#import "/common/macro_common.ftl" as common>
<@common.html includeDate=true title="订单列表">
<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i>订单管理</a></li>
            <li>订单列表</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="icon icon-user"></i>订单列表
                </div>
                <div class="panel-body">
                    <div class="tableToolContainer" style="margin-bottom:15px">
                        <form class="form-inline" action="/admin/order/list">
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;餐桌编号：&nbsp;</label>
                                <input type="text" class="form-control" name="tableSn" value="${(param.tableSn)!}">
                            </div>
                            &nbsp;
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;创建时间区间：</label>
                                <input type="text" class="form-control" name="startTime" id="beginDate" value="${(param.startTime)!}">
                                <input type="text" class="form-control" name="endTime" id="endDate" value="${(param.endTime)!}">
                            </div>
                            &nbsp;
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;订单状态：</label>
                                <select class="form-control" name="complete" id="complete">
                                    <option value="all">&nbsp;&nbsp;&nbsp;&nbsp;全部&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                    <option value="completed">&nbsp;&nbsp;&nbsp;&nbsp;已完成&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                    <option value="todocompleted">&nbsp;&nbsp;&nbsp;&nbsp;未完成&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                </select>
                                <script> $("#complete").val('${(param.complete)!}'); </script>
                            </div>
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
                            <th class="tableCenter">就餐餐桌</th>
                            <th class="tableCenter">餐品Id</th>
                            <th class="tableCenter">消费</th>
                            <th class="tableCenter">创建时间</th>
                            <th class="tableCenter">是否完成</th>
                            <th class="tableCenter">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orders?if_exists as order>
                            <tr>
                                <td class="tableCenter">
                                    <input type="checkbox" value="1" name="">${order.id?c}
                                </td>
                                <td class="tableCenter">${order.tableSn}</td>
                                <td class="tableCenter">${order.menuId}</td>
                                <td class="tableCenter">￥:${order.totalPrice}&nbsp;元</td>
                                <td class="tableCenter">${(order.createTime?string("yyyy-MM-dd HH:mm:ss"))}</td>
                                <td class="tableCenter">
                                    <div onclick="switch_state(${order.id?c});" class="switch switch-inline" id="switch_${order.id?c}">
                                        <input type="checkbox" <#if order.complete.getCode() == 'completed'>checked<#else >${order.complete.getName()}</#if>>
                                        <label><#if order.complete.getCode() == 'completed'>已完成<#else >${order.complete.getName()}</#if></label>
                                    </div>
                                </td>
                                <td class="tableCenter">
                                    <a href="javascript:void(0)" onclick="del(${order.id?default(0)?c})"
                                       class="btn btn-sm btn-danger"><i class="icon icon-remove"></i>删除
                                    </a>
                                    <a href="/admin/order/detail?id=${(order.id?c)!}"
                                       class="btn btn-sm btn-success"><i class="fa fa-pencil-square-o"></i>&nbsp;详情</a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer" style="text-align:center">
                    <@html.paging page=page param=param action="/admin/order/list"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function switch_state(id) {
        console.log(id);
        $.post("/api/admin/order/update/state?id="+id,
                function (data) {
                    if (data.code < 0){
                        alertShow("warning", data.message, 2000);
                    }else{
                        var content = data.result == "completed"?'<input type="checkbox" checked><label>已完成</label>':'<input type="checkbox"><label>未完成</label>';
                        $("#switch_"+id).html(content);
                    }
                }
        );
    }
    function del(id) {
        warningModal("确定删除吗?", function () {
            $.post("/api/admin/order/delete?id="+id,
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