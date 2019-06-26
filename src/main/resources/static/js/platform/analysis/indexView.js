$(function() {
    var url = 'listDatagrid';
    $.ajax({
        type: 'GET',
        url: url,
        async: true,
        dataType: "json",
        error: function() {
            alert("请求有误");
        },
        success: function(data) {
            createPaymentStreamYMChart(data.psym);
            createOperationYMChart(data.otym);
        }});
});

function createPaymentStreamYMChart(json) {
    var days = monthHasDays();
    var month = new Date().getMonth() + 1;
    xdata = new Array();
    ydata = new Array();
    var map = new Map();

    $.each(json, function(i, n) {
        map.put(n.day, n);
    });

    for (var i = 1; i <= days; i++) {
        xdata.push(month + '-' + i);
        var val = 0;
        if (map.get(i) != null) val = map.get(i).sumMoney;
        ydata.push(val);
    }

    var psym = echarts.init(document.getElementById('psym'));

    option = {
        color: ['#55A3B2'],
        title: [{
            text: '当月日缴费金额',
            x: '50%',
            textAlign: 'center'}],
        tooltip: {},

        xAxis: [{
            type: 'category',
            data: xdata}],
        yAxis: [{
            type: 'value'}],
        series: [{
            name: '缴费金额',
            type: 'bar',
            data: ydata,
            markPoint: {
                data: [{
                    itemStyle: {
                        color: '#0033dd'},
                    type: 'max',
                    name: '最大值'}, {
                    itemStyle: {
                        color: '#ff3300'},
                    type: 'min',
                    name: '最小值'}]},
            markLine: {
                data: [{
                    type: 'average',
                    name: '平均值'}]}}]};

    psym.setOption(option);
}

function createOperationYMChart(json) {
    var days = monthHasDays();
    var month = new Date().getMonth() + 1;
    xdata = new Array();
    ydata = new Array();
    var map = new Map();

    $.each(json, function(i, n) {
        map.put(n.day, n);
    });

    for (var i = 1; i <= days; i++) {
        xdata.push(month + '-' + i);
        var val = 0;
        if (map.get(i) != null) val = map.get(i).ct;
        ydata.push(val);
    }

    var otym = echarts.init(document.getElementById('otym'));

    option = {
        color: ['#FF9CDD'],
        title: [{
            text: '当月日访问量',
            x: '50%',
            textAlign: 'center'}],
        tooltip: {},

        xAxis: [{
            type: 'category',
            data: xdata}],
        yAxis: [{
            type: 'value'}],
        series: [{
            name: '访问次数',
            type: 'line',
            smooth: true,
            data: ydata,
            markPoint: {
                data: [{
                    itemStyle: {
                        color: '#0033dd'},
                    type: 'max',
                    name: '最大值'}, {
                    itemStyle: {
                        color: '#ff3300'},
                    type: 'min',
                    name: '最小值'}]},
            markLine: {
                data: [{
                    type: 'average',
                    name: '平均值'}]}}]};

    otym.setOption(option);
}

/**
 * @returns
 */
function monthHasDays() {
    var date = new Date();
    var firstWeekDate = 1;// 默认第一周是本月1号 为了模拟本月1号是否为本月第1周的判断
    if (date.getDay() === 1) { // 判断1号是周一
        firstWeekDatek = 1;
    } else if (date.getDay() === 0) { // 判断1号是周日
        firstWeekDate = 8 - 7 + 1;
    } else { // 判断1号是周二至周六之间
        firstWeekDate = 8 - date.getDay() + 1;
    }
    date.setMonth(date.getMonth() + 1);
    date.setDate(0);
    return date.getDate();// 本月天数
}