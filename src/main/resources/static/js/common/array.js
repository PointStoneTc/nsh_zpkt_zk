/**
 * 最大值
 */
Array.prototype.max = function() {
    return cacl(this, function(item, max) {
        if (!(max > item)) {
            return item;
        } else {
            return max;
        }
    });
};

/**
 * 最小值
 */
Array.prototype.min = function() {
    return cacl(this, function(item, min) {
        if (!(min < item)) {
            return item;
        } else {
            return min;
        }
    });
};

/**
 * 合计
 */
Array.prototype.sum = function() {
    return cacl(this, function(item, sum) {
        if (typeof (sum) == 'undefined') {
            return item;
        } else {
            return sum += item;
        }
    });
};

/**
 * 平均值
 */
Array.prototype.avg = function() {
    if (this.length == 0) { return 0; }
    return this.sum(this) / this.length;
};