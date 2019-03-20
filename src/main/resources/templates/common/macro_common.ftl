<#macro html includeDate=false title="">
<!DOCTYPE html>
<html>
    <#setting url_escaping_charset='utf-8'>
<head>
    <title>${title}</title>
    <meta charset="utf-8">
    <script src="/js/alerts.js"></script>
    <link rel="stylesheet" type="text/css" href="/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/SPSStyle.css">
    <link rel="stylesheet" type="text/css" href="/zui/zui.min.css">
    <script src="/zui/jquery.js" charset="UTF-8"></script>
    <script src="/zui/zui.min.js" charset="UTF-8"></script>
    <script src="/echarts/echarts.js" charset="UTF-8"></script>
    <link href="/datatable/zui.datatable.min.css" rel="stylesheet" media="screen">
    <script src="/datatable/zui.datatable.min.js" charset="UTF-8"></script>
    <#if includeDate>
        <link rel="stylesheet" href="/plugins/datepicker/datepicker3.css"/>
        <script src="/plugins/datepicker/bootstrap-datepicker.js"></script>
        <script src="/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    </#if>
    <style>
        .table>tbody>tr>td{
            vertical-align: middle;
        }
    </style>
    <link rel="icon" type="image/x-icon" href="/img/favicon.ico">
</head>
    <#nested>
</html>
</#macro>
