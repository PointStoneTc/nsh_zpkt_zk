//时间格式化
Date.prototype.format = function (format,value) {
	if (!format) {
		format = "yyyy-MM-dd hh:mm:ss";
	}
	//value为空或null，返回空值
	if(value==''||value==null){
		return '';
	}else{
		
		var strdata=value.replace(/-/g,"/");
		var index=strdata.indexOf(".");
		if(index>0)
		{
			strdata=strdata.substr(0,index);
		}
		var date= new Date(Date.parse(strdata));
		//不是data类型,返回value
		if (date.toString().startsWith("Invali")){
			return value;
		}else {
			var o = {
					"M+" : date.getMonth() + 1, // month
					"d+" : date.getDate(), // day
					"h+" : date.getHours(), // hour
					"m+" : date.getMinutes(), // minute
					"s+" : date.getSeconds(), // second
					"q+" : Math.floor((date.getMonth() + 3) / 3), // quarter
					"S" : date.getMilliseconds()
					// millisecond
			};
			
			if (/(y+)/.test(format)) {
				format = format.replace(RegExp.$1, strdata.substr(4-RegExp.$1.length,RegExp.$1.length));
				//format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
			}
			
			for (var k in o) {
				if (new RegExp("(" + k + ")").test(format)) {
					format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
				}
			}
			return format;
		}
	}
};
//时间戳转日期
Date.prototype.datefor = function (value) {
  var date = new Date(value);
  Y = date.getFullYear() + '-';
  M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
  D = date.getDate() + ' ';
  h = date.getHours() + ':';
  m = date.getMinutes() + ':';
  s = date.getSeconds(); 
  return Y+M+D+h+m+s;
};
//标准日期转成yyyy-MM-dd
Date.prototype.formatDat = function (format,value) {
if (!format) {
  format = "yyyy-MM-dd hh:mm:ss";
}
//value为空或null，返回空值
if(value==''||value==null){
  return '';
}else{
  var seperator1 = "-";
  var date= new Date(value);
  //不是data类型,返回value
  if (date.toString().startsWith("Invali")){
    return value;
  }else {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
  }
}
};