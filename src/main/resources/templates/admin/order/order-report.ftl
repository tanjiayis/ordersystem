<#import "/common/macro_paging.ftl" as html>
<#import "/common/macro_common.ftl" as common>
<@common.html title = "订单报表">
<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i>订单管理</a></li>
            <li>订单报表</li>
        </ol>
    </div>
    <#--<input type="hidden" id="order" value="${dayOrder}">-->
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="icon icon-user"></i>报表统计
                </div>
                <div id="expendpermonth" style="width: 60%;height:400px;float:left;"></div>
            </div>
        </div>
    </div>
</div>
<script>
    var dom = document.getElementById("expendpermonth");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    app.title = '坐标轴刻度与标签对齐';
    option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : [],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
                data:[10, 52, 200, 334, 390, 330, 220]
            }
        ]
    };
    if (option && typeof option === "object") {
        <#--var dayOrder = ${"#order"}.value;-->

        <#--var dayOrder = ${dayOrder};-->
//        console.log(dayOrder);
        <#--for (var i =0;i<dayOrder.length;i++){-->
            <#--option.xAxis.data[i] = dayOrder[0].dateDay;-->
        <#--}-->
        myChart.setOption(option, true);
    }
</script>
</body>
</@common.html>