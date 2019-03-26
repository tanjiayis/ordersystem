<#import "/common/macro_paging.ftl" as html>
<#import "/common/macro_common.ftl" as common>
<#import "/common/macro_operate.ftl" as operate>
<@common.html title="菜品列表">
<link href="/zui/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<script type="text/javascript" src="/zui/lib/uploader/zui.uploader.min.js"></script>
<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i>菜品管理</a></li>
            <li>菜品列表</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="icon icon-user"></i>菜品列表
                </div>
                <div class="panel-body">
                    <div class="tableToolContainer" style="margin-bottom:15px">
                        <form class="form-inline" action="/menu/list">
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;名称：&nbsp;</label>
                                <input type="text" class="form-control" name="name" value="${(param.name)!}">
                            </div>
                            &nbsp;&nbsp;
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;菜品类别：&nbsp;</label>
                                <select class="form-control" name="type" id="type">
                                    <option value="0">&nbsp;&nbsp;&nbsp;&nbsp;全部&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                    <#list types?if_exists as type>
                                        <option value="${(type.id?c)}">&nbsp;&nbsp;&nbsp;&nbsp;${(type.typeName)!}&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                    </#list>
                                </select>
                                <script> $("#type").val('${(param.type)!}'); </script>
                            </div>
                            &nbsp;&nbsp;
                            <div class="form-group">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;菜品充足：&nbsp;</label>
                                <input type="checkbox" name="state" <#if param.state!true>checked</#if>>
                            </div>
                            &nbsp;&nbsp;
                            <button class="btn btn-success" type="submit">
                                <i class="fa fa-fw fa-search"></i>搜索
                            </button>

                            <div class="tableToolContainer" style="margin-bottom:15px; float: right">
                            <@shiro.hasPermission name="menu_list.add" >
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
                            <th style="width:50px" class="tableCenter">
                                <input type="checkbox" name="" value="">
                            </th>
                            <th style="width:50px" class="tableCenter">#</th>
                            <th style="width:120px" class="tableCenter">名称</th>
                            <th style="width:100px" class="tableCenter">价格</th>
                            <th style="width:150px" class="tableCenter">展示</th>
                            <th style="width:100px" class="tableCenter">是否还有</th>
                            <th style="width:450px" class="tableCenter">备注</th>
                            <th class="tableCenter">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list menus?if_exists as menu>
                            <tr>
                                <td class="tableCenter">
                                    <input type="checkbox" value="1" name="">
                                </td>
                                <td class="tableCenter">${menu.id?c}</td>
                                <td class="tableCenter">${menu.name}</td>
                                <td class="tableCenter">￥:<span style="color: red;font-weight: bold">${menu.price}</span>&nbsp;元</td>
                                <td class="tableCenter">
                                    <#if menu.image??><img style="width: 60px;height: 60px;" src="/images/menus/${menu.image}" id="imageview_${(menu.id?default(0)?c)}"><#else >暂无图片</#if>
                                </td>
                                <td class="tableCenter"><#if menu.state!true>有<#else >无此菜品</#if></td>
                                <td class="tableCenter">${(menu.remark)!}</td>
                                <td class="tableCenter">
                            <@shiro.hasPermission name="menu_list.update" >
                                    <a href="#" onclick="doEdit(${(menu.id?c)!})"
                                       class="btn btn-sm btn-success"><i class="fa fa-pencil-square-o"></i>编辑
                                    </a>
                                </@shiro.hasPermission>
                            <@shiro.hasPermission name="menu_list.del" >
                                    <a href="javascript:void(0)" onclick="del(${menu.id?default(0)?c})"
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
                    <@html.paging page=page param=param action="/menu/list"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

</script>
</body>
    <@operate.modal "增加菜品" "add_menu" "onSave">
    <div class="modalForm" style="padding:10px;">
        <input type="hidden" id="menu_id" name="id" value="">
        <div class="form-group">
            <label class="col-sm-3  control-label">所属类别:</label>
            <div class="col-sm-3">
                <select class="form-control" name="type" id="type">
                    <#list types?if_exists as type>
                        <option value="${(type.id?c)}">&nbsp;&nbsp;&nbsp;&nbsp;${(type.typeName)!}&nbsp;&nbsp;&nbsp;&nbsp;</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3  control-label">名称:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="menu_name" name="name">
                <span class="help-block"></span>
            </div>
            <label class="col-sm-2  control-label">价格:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="price" name="price">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3  control-label">上传图片:</label>
            <div id="uploaderExample" class="uploader col-sm-3">
                <div class="file-list" data-drag-placeholder="请拖拽文件到此处"></div>
                <button type="button" class="btn btn-primary uploader-btn-browse"><i class="icon icon-cloud-upload"></i> 选择文件</button>
            </div>
                <label class="col-sm-2  control-label" id="img_0" >图片展示:</label>
                <div class="col-sm-3" id="img_1">
                    <img src="" style="width: 60px;height: 60px;" id="img_show">
                    <span class="help-block"></span>
                </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3  control-label">菜品备注:</label>
            <div class="col-sm-8">
                <textarea class="form-control" name="remark" id="remark" placeholder="菜品备注"></textarea>
            </div>
        </div>
    </@operate.modal>
    <script>
        var menuImageName = '';
        $('#uploaderExample').uploader({
            autoUpload: true,
            url: '/api/admin/menu/uploader',
            mime_types: [
                {title: '图片', extensions: 'jpg,gif,png'}
            ],
            max_file_size: '2MB',
            multi_selection: false,
            file_data_name: 'file',
            previewImageSize: {width: 80, height: 100},
            deleteActionOnDone: function (file, doRemoveFile) {
                return true;
            }
        }).uploader().on('onUploadFile', function(file, data) {
            menuImageName = data.name;
            $("#img_0").show();
            $("#img_show").attr("src", "/images/menus/"+menuImageName);
            $("#img_1").show();
        });
        function doAdd() {
            $("#menu_id").val(0);
            $("#img_0").hide();
            $("#img_1").hide();
            $("#add_menu_form")[0].reset();
            $("#add_menu").modal("show");
        }
        function doEdit(id) {
            $.post(
                    "/api/admin/menu/detail?id="+id,
                    function (data) {
                        if (data.code < 0){
                            alertShow("warning", data.message, 2000);
                        }else{
                            var result = data.result;
                            $("#img_0").show();
                            $("#img_1").show();
                            $("#menu_id").val(result.id);
                            $("#type").val(result.typeId);
                            $("#menu_name").val(result.name);
                            $("#price").val(result.price);
                            $("#remark").val(result.remark);
                            $("#add_menu_header").text("编辑此菜");
                            if (result.image==null || result.image == ""){
                                $("#img_show").attr("src", "/images/menus/no.png");
                            }else{
                                $("#img_show").attr("src", "/images/menus/"+result.image);
                            }
                            $("#add_menu").modal("show");
                        }
                    }
            );
        }
        function del(id) {
            warningModal("确定删除吗?", function () {
                $.post("/api/admin/menu/delete?id="+id,
                        function (data) {
                            if (data.code < 0){
                                alertShow("warning", data.message, 2000);
                            }else{
                                alertShow("info", "删除成功!", 2000);
                            }
                        }
                );
            });
        }
        function onSave() {
            var id = $("#menu_id").val();
            var typeId = $("#type").val();
            var name = $("#menu_name").val();
            var price = $("#price").val();
            var remark = $("#remark").val();
            var imageName = menuImageName;
            $.post(
                    id == 0 ?"/api/admin/menu/add" : "/api/admin/menu/edit",
                    {
                        menuId:id,
                        typeId: typeId,
                        name: name,
                        price: price,
                        remark: remark,
                        imageName:imageName
                    },
                    function (data) {
                        if (data.code < 0){
                            alertShow("danger", data.message, 2000);
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
</@common.html>