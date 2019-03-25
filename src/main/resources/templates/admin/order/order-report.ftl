<#import "/common/macro_paging.ftl" as html>
<#import "/common/macro_common.ftl" as common>
<@common.html title = "订单报表">
<style>
    .expand{
        width: 60%;height:400px;float:left;
    }
    .expand1{
        width: 40%;height:400px;float:left;
    }
</style>
<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><i class="icon icon-user"></i>订单管理</a></li>
            <li>订单报表</li>
        </ol>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="icon icon-user"></i>报表统计
                </div>
                <div class="panel-body">
                    <div class="tableToolContainer" style="margin-bottom:15px">
                        <form class="form-inline" action="/admin/remark/list">
                            <div class="tableToolContainer" style="margin-bottom:15px; float: right">
                                <label class="exampleInputAccount4">&nbsp;&nbsp;查看销量前：</label>
                                <label>&nbsp;<input type="radio" name="num" value="5">5</label>&nbsp;&nbsp;
                                <label>&nbsp;<input type="radio" name="num" value="8">8</label>&nbsp;&nbsp;
                                <label>&nbsp;<input type="radio" name="num" value="10">10</label>
                                <label class="exampleInputAccount4">&nbsp;&nbsp;的菜品</label>
                            </div>
                        </form>
                    </div>
                    <div id="expendpermonth" class="expand">

                    </div>
                    <div id="hotMenu" class="expand1" >

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("input[name='num']").change(function () {
        var num = $("input[name='num']:checked").val();
        hotMenu(num);
    });
    function consume(date, orderCount, totalPrice) {
        var dom = document.getElementById("expendpermonth");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        app.title = '坐标轴刻度与标签对齐';
        option = {
            title : {
                text: 'XXX餐厅日消费情况',
                subtext: '真实数据'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['订单量','消费总额']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : date,
                }
    ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'订单量',
                    type:'bar',
                    data:orderCount,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                },
                {
                    name:'消费总额',
                    type:'bar',
                    data:totalPrice,
                    markPoint : {
                        data : [
                            {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
                            {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name : '平均值'}
                        ]
                    }
                }
            ]
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }
    function hot(menuname, hot) {
        var dom = document.getElementById("hotMenu");
        var myChart = echarts.init(dom);
        option = null;
        option = {
            title : {
                text: 'XXX餐厅热销菜品',
                subtext: '真实数据',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data: menuname
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {
                        show: true,
                        type: ['pie', 'funnel'],
                        option: {
                            funnel: {
                                x: '25%',
                                width: '50%',
                                funnelAlign: 'left',
                                max: 1548
                            }
                        }
                    },
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:hot
                }
            ]
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }
    function dayconsume() {
        $.post(
                "/api/admin/order/dayorder",
                function (data) {
                    if (data.code < 0){
                        alertShow("warning", data.message, 2000);
                    }else{
                        var date = [];
                        var order_count = [];
                        var total_price = [];
                        var result = data.result;
                        for(var i = 0; i<result.length; i++){
                            date.push(result[i].dateDay);
                            order_count.push(result[i].orderCount);
                            total_price.push(result[i].totalPrice);
                        }
                        consume(date, order_count, total_price);
                    }
                }
        );
    }
    function hotMenu(num=6) {
        $.post(
            "/api/admin/order/hot",
                {
                    num: num
                },
                function (data) {
                    if (data.code < 0){
                        alertShow("warning", data.message, 2000);
                    }else{
                        var menuname = [];
                        var result = data.result;
                        for(var i = 0; i< result.length; i++){
                            menuname.push(result[i].name);
                        }
                        hot(menuname, result);
                    }
                }
        )
    }
    $(function () {
        dayconsume();
        hotMenu();
    });
</script>
</body>
</@common.html>