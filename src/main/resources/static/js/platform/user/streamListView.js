$(function() {
    $('#streamDataGrid').datagrid();

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

    $('#createDate').datebox({
        editable: false,
        formatter: function(date) {
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            return y + '-' + m + '-' + d;
        }});

    $('#value').textbox({
        disabled: true,
        validType: {
            length: [0, 10]},
        iconAlign: 'left'});

    $('#search').linkbutton({
        iconCls: 'icon-search',
        onClick: doSearch});

    $('#bankCommand').combobox({
        onSelect: function(rec) {
            if (rec.value == -1 || rec.value == 821) {
                $('#value').textbox('setValue', '');
                $('#value').textbox('disable');
            } else {
                $('#value').textbox('enable');
            }
        }});
});

/**
 * 查询
 * 
 * @returns
 */
function doSearch() {
    if (!$('#userName').textbox('isValid') || !$('#userNo').textbox('isValid') || !$('#value').textbox('isValid')) {
        $.messager.alert('警告', '输入不合规!');
        return false;
    }

    $('#streamDataGrid').datagrid('load', {
        userName: $('#userName').val(),
        userNo: $('#userNo').val(),
        createDate: $('#createDate').val(),
        bankCommand: $('#bankCommand').val(),
        value: $('#value').val()});
}

/**
 * @returns
 */
function doClean() {
    $('#userName').textbox('setValue', '');
    $('#userNo').textbox('setValue', '');
    $('#createDate').datebox('setValue', '');
    $('#bankCommand').combobox('setValue', '-1');
    $('#value').textbox('setValue', '');
}

/**
 * 根据交易类型格式化
 * 
 * @param val
 * @param row
 * @returns
 */
function formatValue(val, row) {
    if (row.bankCommand == '821')
        return formatMoney(val, row);
    else if (row.bankCommand == '850') return '流水号: ' + val;
}