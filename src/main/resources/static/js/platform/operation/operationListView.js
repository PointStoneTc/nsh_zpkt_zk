$(function() {
    var dg = $('#operationDataGrid').datagrid({
        view: detailview,
        detailFormatter: function(index, row) {
            if (row.bankCommand == '查询欠费明细')
                return split811(row.bankSend, row.gasEcho);
            else if (row.bankCommand == '交易缴费')
                return split821(row.bankSend, row.gasEcho);
            else if (row.bankCommand == '用户冲帐')
                return split850(row.bankSend, row.gasEcho);
            else if (row.bankCommand == '对总账') return split860(row.bankSend, row.gasEcho);
        },
        onExpandRow: function(index, row) {
            var ddv1Table = $(this).datagrid('getRowDetail', index).find('.ddv1 table');
            if (ddv1Table != undefined) {
                ddv1Table.datagrid();
                $('#operationDataGrid').datagrid('fixDetailRowHeight', index);
            }
        }});

    $('#startTime').datebox({
        editable: false,
        formatter: function(date) {
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            return y + '-' + m + '-' + d;
        }});

    $('#endTime').datebox({
        editable: false,
        formatter: function(date) {
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            return y + '-' + m + '-' + d;
        }});
});

/**
 * 查询
 * 
 * @returns
 */
function doSearch() {
    if ($('#startTime').datebox('getValue') != '' && $('#endTime').datebox('getValue') != '') {
        var small_date = new Date($('#startTime').datebox('getValue'));
        var big_data = new Date($('#endTime').datebox('getValue'));
        if (small_date > big_data) {
            $.messager.alert('警告', '时间选择错误!');
            return false;
        }
    }

    $('#operationDataGrid').datagrid('load', {
        bankCommand: $('#bankCommand').val(),
        startTime: $('#startTime').val(),
        endTime: $('#endTime').val()});
}

/**
 * @returns
 */
function doClean() {
    $('#startTime').datebox('setValue', '');
    $('#endTime').datebox('setValue', '');
    $('#bankCommand').combobox('setValue', '-1');
}

/**
 * bank协议头
 */
var bankHead = {
    'txt': '',
    'cnt': '',
    show: function() {
        var html = '<ul>';
        html += '<li><span class=title>银行发送</span></li>';
        html += '<li><span class=text>包头:</span><span class=text1>' + this.txt.substr(0, 2) + '</span></li>';
        html += '<li><span class=text>缴费点代码:</span><span class=text1>' + this.txt.substr(2, 4) + '</span></li>';
        html += '<li><span class=text>缴费点密码:</span><span class=text1>' + this.txt.substr(6, 4) + '</span></li>';
        html += '<li><span class=text>备用:</span><span class=text1>' + this.txt.substr(11, 1) + '</span></li>';
        html += '<li><span class=text>交易方式:</span><span class=text1>' + this.txt.substr(12, 2) + '</span></li>';
        html += '<li><span class=text>交易流水号:</span><span class=text1>' + this.txt.substr(14, 14) + '</span></li>';
        html += '<li><span class=text>包体长度字节数:</span><span class=text1>' + this.txt.substr(28, 4) + '</span></li>';
        html += '<li><span class=text>包头结束符:</span><span class=text1>' + this.txt.substr(32, 1) + '</span></li>';
        html += '<li><span class=text>请求包:</span><span class=text1>' + this.txt.substr(33) + '</span></li>';
        html += '</ul>';
        this.cnt = this.txt.substr(33);
        return html;
    }};

/**
 * gas协议头
 */
var gasHead = {
    'txt': '',
    'cnt': '',
    show: function() {
        var html = '<ul>';
        html += '<li><span class=title>天然气返回</span></li>';
        html += '<li><span class=text>请求标识:</span><span class=text1>' + this.txt.substr(0, 4) + '</span></li>';
        html += '<li><span class=text>银行编号:</span><span class=text1>' + this.txt.substr(4, 3) + '</span></li>';
        html += '<li><span class=text>银行设备编号:</span><span class=text1>' + this.txt.substr(7, 10) + '</span></li>';
        html += '<li><span class=text>银行业务流水号:</span><span class=text1>' + this.txt.substr(17, 10) + '</span></li>';
        html += '<li><span class=text>请求方或者响应方本地时间戳:</span><span class=text1>' + this.txt.substr(27, 14) + '</span></li>';
        html += '<li><span class=text>响应字响应方回馈:</span><span class=text1>' + this.txt.substr(41, 4) + '</span></li>';
        html += '<li><span class=text>包体长度字节数:</span><span class=text1>' + this.txt.substr(45, 5) + '</span></li>';
        html += '<li><span class=text>是否有下一包:</span><span class=text1>' + this.txt.substr(50, 1) + '</span></li>';
        html += '<li><span class=text>响应包:</span><span class=text1>' + this.txt.substr(51) + '</span></li>';
        html += '</ul>';
        this.cnt = this.txt.substr(51);
        return html;
    }};

/**
 * 811协议
 * 
 * @param bankSend
 * @param gasEcho
 * @returns
 */
function split811(bankSend, gasEcho) {
    bankHead.txt = bankSend;
    gasHead.txt = gasEcho;
    var html = '<div class=ddv><span class="num_tip atip">B</span>';
    html += bankHead.show();

    // 分解请求包
    var splits = bankHead.cnt.split('|');
    html += '<ul>';
    html += '<li><span class=title>明细</span></li>';
    html += '<li><span class=text>交易码:</span><span class=text1>' + splits[0] + '</span></li>';
    html += '<li><span class=text>用户编号:</span><span class=text1>' + splits[1] + '</span></li>';
    html += '<li><span class=text>费用月份:</span><span class=text1>' + splits[2] + '</span></li>';
    html += '<li><span class=text>查询标志:</span><span class=text1>' + splits[3] + '</span></li>';
    html += '</ul>';
    html += '</div>';

    html += '<div class=ddv><span class="num_tip btip">G</span>';
    html += gasHead.show();
    html += '<ul>';
    splits = gasHead.cnt.split('|');
    html += '<li><span class=title>明细</span></li>';
    html += '<li><span class=text>总应缴费用:</span><span class=text1>' + splits[0] + '</span></li>';
    html += '<li><span class=text>欠费月数:</span><span class=text1>' + splits[1] + '</span></li>';
    html += '</ul>'
    html += '</div>';

    html += '<div class=ddv1><table style="width:80%;" ownumbers="true" singleSelect="true"><thead><tr>';
    html += '<th data-options=field:\'item1\'>用户号</th>';
    html += '<th data-options=field:\'item2\'>用户名</th>';
    html += '<th data-options=field:\'item3\'>气费月份</th>';
    html += '<th data-options=field:\'item4\'>上次示度</th>';
    html += '<th data-options=field:\'item5\'>本次示度</th>';
    html += '<th data-options=field:\'item6\'>气量</th>';
    html += '<th data-options=field:\'item7\'>气费</th>';
    html += '<th data-options=field:\'item8\'>应缴气费</th>';
    html += '<th data-options=field:\'item9\'>滞纳金</th>';
    html += '<th data-options=field:\'item10\'>应缴费用</th>';
    html += '<th data-options=field:\'item11\'>气量 1</th>';
    html += '<th data-options=field:\'item12\'>气价 1</th>';
    html += '<th data-options=field:\'item13\'>气量 2</th>';
    html += '<th data-options=field:\'item14\'>气价 2</th>';
    html += '<th data-options=field:\'item15\'>气量 3</th>';
    html += '<th data-options=field:\'item16\'>气价 3</th>';
    html += '<th data-options=field:\'item17\'>气量 4</th>';
    html += '<th data-options=field:\'item18\'>气价 4</th>';
    html += '</thead></tr><tbody>';
    var detailHtml = '';
    for (var x = 0; x < splits[1]; x++) {
        detailHtml += '<tr>';
        var a = gasHead.cnt.substr(splits[0].length + splits[1].length + 2).split('$', splits[1]);
        $.each(a[x].split('|'), function(i, n) {
            detailHtml += '<td>' + n + '</td>';
        });
        detailHtml += '</tr>';
    }
    html += detailHtml + '</tbody></table></div>';
    return html;
}

/**
 * 821协议
 * 
 * @param bankSend
 * @param gasEcho
 * @returns
 */
function split821(bankSend, gasEcho) {
    bankHead.txt = bankSend;
    gasHead.txt = gasEcho;
    var html = '<div class=ddv><span class="num_tip atip">B</span>';
    html += bankHead.show();

    // 分解请求包
    var splits = bankHead.cnt.split('|');
    html += '<ul>';
    html += '<li><span class=title>明细</span></li>';
    html += '<li><span class=text>交易码:</span><span class=text1>' + splits[0] + '</span></li>';
    html += '<li><span class=text>用户编号:</span><span class=text1>' + splits[1] + '</span></li>';
    html += '<li><span class=text>缴费金额:</span><span class=text1>' + splits[2] + '</span></li>';
    html += '<li><span class=text>应交总额:</span><span class=text1>' + splits[3] + '</span></li>';
    html += '<li><span class=text>缴费时间:</span><span class=text1>' + splits[4] + '</span></li>';
    html += '<li><span class=text>操作工号:</span><span class=text1>' + splits[5] + '</span></li>';
    html += '<li><span class=text>缴费月份:</span><span class=text1>' + splits[6] + '</span></li>';
    html += '<li><span class=text>打印发票:</span><span class=text1>' + splits[7] + '</span></li>';
    html += '<li><span class=text>发票号码:</span><span class=text1>' + splits[8] + '</span></li>';
    html += '</ul>';
    html += '</div>';

    html += '<div class=ddv><span class="num_tip btip">G</span>';
    html += gasHead.show();
    html += '<ul>';
    html += '<li><span class=title>明细</span></li>';
    html += '</ul>'
    html += '</div>';

    return html;
}

/**
 * 850协议
 * 
 * @param bankSend
 * @param gasEcho
 * @returns
 */
function split850(bankSend, gasEcho) {
    bankHead.txt = bankSend;
    gasHead.txt = gasEcho;
    var html = '<div class=ddv><span class="num_tip atip">B</span>';
    html += bankHead.show();

    // 分解请求包
    var splits = bankHead.cnt.split('|');
    html += '<ul>';
    html += '<li><span class=title>明细</span></li>';
    html += '<li><span class=text>交易码:</span><span class=text1>' + splits[0] + '</span></li>';
    html += '<li><span class=text>用户号码:</span><span class=text1>' + splits[1] + '</span></li>';
    html += '<li><span class=text>银行流水号:</span><span class=text1>' + splits[2] + '</span></li>';
    html += '</ul>';
    html += '</div>';

    html += '<div class=ddv><span class="num_tip btip">G</span>';
    html += gasHead.show();
    html += '<ul>';
    html += '<li><span class=title>明细</span></li>';
    html += '</ul>'
    html += '</div>';

    return html;
}

/**
 * 860协议
 * 
 * @param bankSend
 * @param gasEcho
 * @returns
 */
function split860(bankSend, gasEcho) {
    bankHead.txt = bankSend;
    gasHead.txt = gasEcho;
    var html = '<div class=ddv><span class="num_tip atip">B</span>';
    html += bankHead.show();

    // 分解请求包
    var splits = bankHead.cnt.split('|');
    html += '<ul>';
    html += '<li><span class=title>明细</span></li>';
    html += '<li><span class=text>交易码:</span><span class=text1>' + splits[0] + '</span></li>';
    html += '<li><span class=text>收费日期:</span><span class=text1>' + splits[1] + '</span></li>';
    html += '<li><span class=text>缴费笔数:</span><span class=text1>' + splits[2] + '</span></li>';
    html += '<li><span class=text>缴费金额:</span><span class=text1>' + splits[3] + '</span></li>';
    html += '</ul>';
    html += '</div>';

    html += '<div class=ddv><span class="num_tip btip">G</span>';
    html += gasHead.show();
    html += '<ul>';
    html += '<li><span class=title>明细</span></li>';
    html += '</ul>'
    html += '</div>';

    return html;
}

/**
 * 警告渲染
 * 
 * @param val
 * @param row
 * @returns
 */
function alarm(val, row) {
    var color = 'alarm_start';
    if (val < 1)
        color = 'alarm_normal';
    else if (1 < val && val <= 500)
        color = 'alarm_danger';
    else if (val > 500) color = 'alarm_terror';
    return '<span class=' + color + '>' + val + '</span>';
}

/**
 * 比较两个时间的大小
 * 
 * @param small_date1
 * @param big_data2
 * @returns
 */
function compareDate(small_date1, big_data2) {
    return small_date1 > big_data2 ? false : true;
}