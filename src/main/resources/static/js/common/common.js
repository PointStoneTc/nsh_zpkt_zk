/**
 * easy-ui渲染货币
 * 
 * @param val
 * @param row
 * @returns
 */
function formatMoney(val, row) {
    if (isNaN(val)) return 'NaN';

    var res = '-';
    if (parseFloat(val) != 0) res = formatNull(val, row);

    return '<span class="easyui-formatter-money money-rmb">' + res + '</span>';
}

/**
 * easy-ui断字渲染
 * 
 * @param val
 * @param row
 * @returns
 */
function txtBreak(val, row) {
    return '<span class="txtbreak">' + val + '</span>';
}

/**
 * easy-ui数字渲染
 * 
 * @param val
 * @param row
 * @returns
 */
function formatNumNoDisplay(val, row) {
    if (isNaN(val)) return 'NaN';
    var res = '-';
    if (parseFloat(val) > 0)
        res = val;
    else if (parseFloat(val) < 0) res = '<span class="num_negative">' + val + '</span>';
    return res;
}

/**
 * easy-ui时间格式渲染
 * 
 * @param val
 * @param row
 * @returns
 */
function formatTime(val, row) {
    var dateee = new Date(val).toJSON();
    var date = new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    return date;
}

/**
 * easy-ui日期格式渲染
 * 
 * @param val
 * @param row
 * @returns
 */
function formatDate(val, row) {
    var dateee = new Date(val).toJSON();
    var date = new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
    return new Date().format('yyyy-MM-dd', date);
}

/**
 * easy-ui使用次数渲染
 * 
 * @param val
 * @param row
 * @returns
 */
function subFrequency(val, row) {
    if (isNaN(val)) return 'NaN';
    var res = '-';
    if (0 < parseFloat(val) && parseFloat(val) < 6)
        res = val;
    else if (parseFloat(val) > 6) res = '<span class="alarm_normal">' + val + '</span>';
    return res;
}

/**
 * easy-ui间隔天数
 * 
 * @param val
 * @param row
 * @returns
 */
function intervalDay(val, row) {
    if (isNaN(val)) return 'NaN';
    var res = '-';
    if (0 < parseFloat(val) && parseFloat(val) < 60) res = val;
    if (parseFloat(val) > 60) res = '<span class="alarm_terror">' + val + '</span>';
    return res;
}