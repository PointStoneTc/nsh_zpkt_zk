$(function() {
    $('#userDataGrid').datagrid(
                    {
                        view: detailview,
                        detailFormatter: function(index, row) {
                            return '<div style="padding:10px 5px 5px 5px;position:relative;"><table name="payment" title="缴费记录" class="ddv" width="80%"></table></div>'
                                            + '<div style="padding:10px 5px 5px 5px;position:relative;"><table name="cost" title="欠费记录" class="ddv1" width="80%"></table></div>';
                        },
                        onExpandRow: function(index, row) {
                            if (!$(this).datagrid('getRowDetail', index).find('table.ddv').is(":hidden")) {
                                var ddv = $(this).datagrid('getRowDetail', index).find('table.ddv');

                                ddv.datagrid({
                                    url: 'findUserAllPayment?userNo=' + row.userNo,
                                    fitColumns: true,
                                    singleSelect: true,
                                    rownumbers: true,
                                    loadMsg: '',
                                    height: 'auto',
                                    columns: [[{
                                        field: 'transactionFlow',
                                        title: '协议流水号',
                                        width: 100,
                                        align: 'center'}, {
                                        field: 'paymentSum',
                                        title: '缴费金额',
                                        width: 120,
                                        align: 'center',
                                        formatter: formatYuan}, {
                                        field: 'operatorNo',
                                        title: '操作工号',
                                        width: 120,
                                        align: 'center'}, {
                                        field: 'month',
                                        title: '缴费月份',
                                        width: 90,
                                        align: 'center'}, {
                                        field: 'createDate',
                                        title: '缴费时间',
                                        width: 150,
                                        align: 'center',
                                        formatter: formatDate}]],
                                    onResize: function() {
                                        $('#userDataGrid').datagrid('fixDetailRowHeight', index);
                                    },
                                    onLoadSuccess: function() {
                                        setTimeout(function() {
                                            $('#userDataGrid').datagrid('fixDetailRowHeight', index);
                                        }, 0);
                                    }});
                            }

                            if (!$(this).datagrid('getRowDetail', index).find('table.ddv1').is(":hidden")) {
                                var ddv1 = $(this).datagrid('getRowDetail', index).find('table.ddv1');
                                ddv1.datagrid({
                                    url: 'findUserAllCost?userNo=' + row.userNo,
                                    fitColumns: true,
                                    singleSelect: true,
                                    rownumbers: true,
                                    loadMsg: '',
                                    height: 'auto',
                                    columns: [[{
                                        field: 'month',
                                        title: '月份',
                                        width: 100,
                                        align: 'center'}, {
                                        field: 'prevVal',
                                        title: '上次示度(m³)',
                                        width: 120,
                                        align: 'center'}, {
                                        field: 'currVal',
                                        title: '本次示度(m³)',
                                        width: 120,
                                        align: 'center'}, {
                                        field: 'airVal',
                                        title: '气量(m³)',
                                        width: 90,
                                        align: 'center'}, {
                                        field: 'airCost',
                                        title: '气费(元)',
                                        width: 150,
                                        align: 'center',
                                        formatter: formatYuan}, {
                                        field: 'payAbleAirNum',
                                        title: '应缴气费(元)',
                                        width: 150,
                                        align: 'center',
                                        formatter: formatYuan}, {
                                        field: 'lateFee',
                                        title: '滞纳金(元)',
                                        width: 150,
                                        align: 'center',
                                        formatter: formatYuan}, {
                                        field: 'payAbleAirCost',
                                        title: '应缴费用(元)',
                                        width: 150,
                                        align: 'center',
                                        formatter: formatYuan}, {
                                        field: 'airVal1',
                                        title: '气量1',
                                        width: 80,
                                        align: 'center'}, {
                                        field: 'airCost1',
                                        title: '气价1',
                                        width: 80,
                                        align: 'center'}, {
                                        field: 'airVal2',
                                        title: '气量2',
                                        width: 80,
                                        align: 'center'}, {
                                        field: 'airCost2',
                                        title: '气价2',
                                        width: 80,
                                        align: 'center'}, {
                                        field: 'airVal3',
                                        title: '气量3',
                                        width: 80,
                                        align: 'center'}, {
                                        field: 'airCost3',
                                        title: '气价3',
                                        width: 80,
                                        align: 'center'}, {
                                        field: 'airVal4',
                                        title: '气量4',
                                        width: 80,
                                        align: 'center'}, {
                                        field: 'airCost4',
                                        title: '气价4',
                                        width: 80,
                                        align: 'center'}]],
                                    onResize: function() {
                                        $('#userDataGrid').datagrid('fixDetailRowHeight', index);
                                    },
                                    onLoadSuccess: function() {
                                        setTimeout(function() {
                                            $('#userDataGrid').datagrid('fixDetailRowHeight', index);
                                        }, 0);
                                    }});
                            }
                            $('#userDataGrid').datagrid('fixDetailRowHeight', index);
                        },
                        onLoadSuccess: function(data) {
                            $('a[name="oper"]').linkbutton({
                                text: '提示',
                                iconCls: 'icon-tip'});
                        }});

    $('#userName').textbox({
        validType: {
            length: [0, 5]},
        iconCls: 'icon-man',
        iconAlign: 'left'});

    $('#userNo').textbox({
        validType: {
            length: [0, 5]},
        iconCls: 'icon-man',
        iconAlign: 'left'});

    $('#search').linkbutton({
        iconCls: 'icon-search',
        onClick: doSearch});
});

/**
 * 查询
 * 
 * @returns
 */
function doSearch() {
    if (!$('#userName').textbox('isValid') || !$('#userNo').textbox('isValid')) {
        $.messager.alert('警告', '输入不合规!');
        return false;
    }

    $('#userDataGrid').datagrid('load', {
        userName: $('#userName').val(),
        userNo: $('#userNo').val()});
}

/**
 * @returns
 */
function doClean() {
    $('#userName').textbox('setValue', '');
    $('#userNo').textbox('setValue', '');
}

function formatOper(val, row, index) {
    return '<a href="#" name="oper" class="easyui-linkbutton" onclick="tip(' + index + ')" ></a>';
}

function tip(index) {
    $('#userDataGrid').datagrid('selectRow', index);
    var row = $('#userDataGrid').datagrid('getSelected');
    if (row) {
        // ToDo
    }
}