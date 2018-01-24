var common = {};
function sucessTip(text){
	var index = layer.msg(text,{ icon: 1, shift: 0});
	layer.style(index, {
		width:"fit-content"
		});
}
function failTip(text){
	var index =  layer.msg(text,{icon: 2, shift: 6});
	layer.style(index, {
		width:"fit-content"
		});
}
var extend=function(obj1,obj2){ 
	 for(var key in obj2){ 
	    if(obj1.hasOwnProperty(key))continue;//有相同的属性则略过 
	    obj1[key]=obj2[key]; 
	 } 
	 return obj1; 
} 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function HashMap() {
	/** Map 大小 * */
	var size = 0;
	/** 对象 * */
	var entry = new Object();

	/** 存 * */
	this.put = function(key, value) {
		if (!this.containsKey(key)) {
			size++;
		}
		entry[key] = value;
	}

	/** 取 * */
	this.get = function(key) {
		if (this.containsKey(key)) {
			return entry[key];
		} else {
			return null;
		}
	}

	/** 删除 * */
	this.remove = function(key) {
		if (delete entry[key]) {
			size--;
		}
	}

	/** 是否包含 Key * */
	this.containsKey = function(key) {
		return (key in entry);
	}

	/** 是否包含 Value * */
	this.containsValue = function(value) {
		for ( var prop in entry) {
			if (entry[prop] == value) {
				return true;
			}
		}
		return false;
	}

	/** 所有 Value * */
	this.values = function() {
		var values = new Array();
		for ( var prop in entry) {
			values.push(entry[prop]);
		}
		return values;
	}

	/** 所有 Key * */
	this.keys = function() {
		var keys = new Array();
		for ( var prop in entry) {
			keys.push(prop);
		}
		return keys;
	}

	/** Map Size * */
	this.size = function() {
		return size;
	}
}
/***
 *  人性化下拉选择和回车键改变焦点* 
 * @param fms  from表单Id
 * @param index form表单里的select下拉选择框 重新渲染后的tabind赋值和焦点改变
 */
function formFocus( fms,index) {
    //setTimeout(function () {
    fms.each(function (i, fm) {
        var itemAll = $(fm).find('input,button,a.layui-btn');
        var inputs = [];
        var idx = 0;
        $.each(itemAll, function (i, item) {
            if (!$(item).is(':hidden') && !item.disabled && $(item).attr('readonly') == undefined) {
                $(item).attr('tabind', idx);
                
                idx++;
                if (item.tagName == 'A' && $(item).attr('href') == undefined)
                    $(item).attr('href', 'javascript:;');
               inputs.push(item);
            } else {
                $(item).removeAttr('tabind');
            }
        });
        if (idx == 0) return;
       	
        
        $(fm).find('[tabind]').each(function (j, item) {
            $(item).on('keyup', function (e) {
                if (e.keyCode == 13) {
                    var k = parseInt($(item).attr('tabind'));
                    var next = $(fm).find('[tabind=' + (k + 1) + ']');
                    if (next.length > 0) {
                        if (next[0].type == 'button' || next.hasClass('layui-btn')) {
                            next[0].click();                            
                        } else {                            
                            next[0].click();
                            if (next[0].createTextRange) {//IE浏览器
                                var range = next[0].createTextRange();
                                range.moveStart("character", 0);
                                range.moveEnd("character", 1);
                                range.select();
                            } else {
                                next.focus();
                            } 
                        }    
                    }
                }
            });
        });
        if(index){
        	 var next = $(fm).find('[tabind=' + (index-0 + 1) + ']');
                    if (next.length > 0) {
                        if (next[0].type == 'button' || next.hasClass('layui-btn')) {
                            next[0].click();                            
                        } else {                            
                            next[0].click();
                            if (next[0].createTextRange) {//IE浏览器
                                var range = next[0].createTextRange();
                                range.moveStart("character", 0);
                                range.moveEnd("character", 1);
                                range.select();
                            } else {
                                next.focus();
                            } 
                        }    
                    }
        }
        $(fm).find('select').each(function (i, select) {
        $(select).next().find('input').on('keyup', function (e) {
            $(select).next().addClass('layui-form-selected');

            var dd = $(select).next().find('dd').not('.layui-hide').first();
            if ($(select).next().find('dd.layui-this').not('.layui-hide').length > 0)
                dd = $(select).next().find('dd.layui-this').not('.layui-hide');

            if (e.keyCode == 13) {
            		$(dd)[0].click();
                	var k = parseInt($(this).attr('tabind'));
                	
                    var next = $(fm).find('[tabind=' + (k + 1) + ']');
                    if (next.length > 0) {
                        if (next[0].type == 'button' || next.hasClass('layui-btn')) {
                            next[0].click();                            
                        } else {                            
                            next[0].click();
                            if (next[0].createTextRange) {//IE浏览器
                                var range = next[0].createTextRange();
                                range.moveStart("character", 0);
                                range.moveEnd("character", 1);
                                range.select();
                            } else {
                                next.focus();
                            } 
                        }    
                    }
            }
            else if (e.keyCode == 40) {
                if (!dd.hasClass('layui-this')) {
                    dd.addClass('layui-this');
                }
                else if (dd.nextAll().not('.layui-hide').length > 0) {
                    dd.removeClass('layui-this');
                    dd = dd.nextAll().not('.layui-hide').first();
                    dd.addClass('layui-this');
                }
                if (dd.length > 0) {
                    var t = dd.position().top;
                    var h = dd.parent().height();
                    var st = dd.parent().scrollTop();
                    if (t + st > h) dd.parent().scrollTop(t + st - dd.parent().height() + 30);
                }
            } else if (e.keyCode == 38) {
                if (dd.prevAll().not('.layui-hide').length > 0) {
                    dd.removeClass('layui-this');
                    dd = dd.prev().not('.layui-hide');
                    dd.addClass('layui-this');
                }
                if (dd.length > 0) {
                    var t = dd.position().top;
                    var st = dd.parent().scrollTop();
                    if (t < 5) dd.parent().scrollTop(st + t - 5);
                }
            }
        });
    });
    });
}

//格式化金额
common.priceFormat = function (price) {
    if (!price) price = 0;
    if (price.toString().length < 1) {
        return '';
    }
    //格式匹配
    if (price.toString().indexOf('.') < 0) {
        price += ".00";
    }

    var rs = [];
    var parts = price.toString().split('.');
    var part1 = parts[0];
    var part2 = parts[1];
    var count = 0;
    for (var i = part1.length - 1; i > -1; --i) {
        count++;
        if (count % 3 == 0) {
            rs.push(part1.substr(i, 3));
        }
        if (i == 0 && count % 3 != 0) {
            rs.push(part1.substr(0, count % 3));
        }
    }
    if (part2.length < 2) {
        part2 = "." + part2 + "0";
    } else {
        part2 = "." + part2.substr(0, 2);
    }
    price = '';
    for (var j = rs.length - 1; j > -1; --j) {
        price += rs[j];
        if (j != 0 && rs[j] != '-') {
            price += ",";
        }
    }
    price += part2;
    return price;
}

//小写转大写
common.chinesePrice = function (Num) {
    Num = Num.toString();
    for (var i = Num.length - 1; i >= 0; i--) {
        Num = Num.replace(",", "")//替换tomoney()中的“,”
        Num = Num.replace(" ", "")//替换tomoney()中的空格
    }
    Num = Num.replace("￥", "")//替换掉可能出现的￥字符
    if (isNaN(Num)) { //验证输入的字符是否为数字
        console.log("请检查小写金额是否正确");
        return;
    }
    //---字符处理完毕，开始转换，转换采用前后两部分分别转换---//
    part = String(Num).split(".");
    newchar = "";
    //小数点前进行转化
    for (var i = part[0].length - 1; i >= 0; i--) {
        if (part[0].length > 10) { console.log("位数过大，无法计算"); return ""; } //若数量超过拾亿单位，提示
        tmpnewchar = ""
        perchar = part[0].charAt(i);
        switch (perchar) {
            case "0": tmpnewchar = "零" + tmpnewchar; break;
            case "1": tmpnewchar = "壹" + tmpnewchar; break;
            case "2": tmpnewchar = "贰" + tmpnewchar; break;
            case "3": tmpnewchar = "叁" + tmpnewchar; break;
            case "4": tmpnewchar = "肆" + tmpnewchar; break;
            case "5": tmpnewchar = "伍" + tmpnewchar; break;
            case "6": tmpnewchar = "陆" + tmpnewchar; break;
            case "7": tmpnewchar = "柒" + tmpnewchar; break;
            case "8": tmpnewchar = "捌" + tmpnewchar; break;
            case "9": tmpnewchar = "玖" + tmpnewchar; break;
        }
        switch (part[0].length - i - 1) {
            case 0: tmpnewchar = tmpnewchar + "元"; break;
            case 1: if (perchar != 0) tmpnewchar = tmpnewchar + "拾"; break;
            case 2: if (perchar != 0) tmpnewchar = tmpnewchar + "佰"; break;
            case 3: if (perchar != 0) tmpnewchar = tmpnewchar + "仟"; break;
            case 4: tmpnewchar = tmpnewchar + "万"; break;
            case 5: if (perchar != 0) tmpnewchar = tmpnewchar + "拾"; break;
            case 6: if (perchar != 0) tmpnewchar = tmpnewchar + "佰"; break;
            case 7: if (perchar != 0) tmpnewchar = tmpnewchar + "仟"; break;
            case 8: tmpnewchar = tmpnewchar + "亿"; break;
            case 9: tmpnewchar = tmpnewchar + "拾"; break;
        }
        newchar = tmpnewchar + newchar;
    }
    //小数点之后进行转化
    if (Num.indexOf(".") != -1) {
        if (part[1].length > 2) {
            console.log("小数点之后只能保留两位,系统将自动截段");
            part[1] = part[1].substr(0, 2)
        }
        for (var i = 0; i < part[1].length; i++) {
            tmpnewchar = ""
            perchar = part[1].charAt(i)
            switch (perchar) {
                case "0": tmpnewchar = "零" + tmpnewchar; break;
                case "1": tmpnewchar = "壹" + tmpnewchar; break;
                case "2": tmpnewchar = "贰" + tmpnewchar; break;
                case "3": tmpnewchar = "叁" + tmpnewchar; break;
                case "4": tmpnewchar = "肆" + tmpnewchar; break;
                case "5": tmpnewchar = "伍" + tmpnewchar; break;
                case "6": tmpnewchar = "陆" + tmpnewchar; break;
                case "7": tmpnewchar = "柒" + tmpnewchar; break;
                case "8": tmpnewchar = "捌" + tmpnewchar; break;
                case "9": tmpnewchar = "玖" + tmpnewchar; break;
            }
            if (i == 0) tmpnewchar = tmpnewchar + "角";
            if (i == 1) tmpnewchar = tmpnewchar + "分";
            newchar = newchar + tmpnewchar;
        }
    }


    //替换所有无用汉字
    while (newchar.search("零零") != -1)
        newchar = newchar.replace("零零", "零");
    newchar = newchar.replace("零亿", "亿");
    newchar = newchar.replace("亿万", "亿");
    newchar = newchar.replace("零万", "万");
    newchar = newchar.replace("零元", "元");
    newchar = newchar.replace("零角", "");
    newchar = newchar.replace("零分", "");
    if (newchar.charAt(newchar.length - 1) == "元" || newchar.charAt(newchar.length - 1) == "角")
        newchar = newchar + "整"
    //  document.write(newchar);
    return newchar;
}
/**  
* js时间对象的格式化; 
* eg:format="yyyy-MM-dd hh:mm:ss";   
*/  
Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1,  //month   
        "d+": this.getDate(),     //day   
        "h+": this.getHours(),    //hour   
        "m+": this.getMinutes(),  //minute   
        "s+": this.getSeconds(), //second   
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter   
        "S": this.getMilliseconds() //millisecond   
    }  
    var week=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    }  
    if (/(w+)/.test(format)){  
    	format = format.replace(RegExp.$1, week[this.getDay()]);  
    }  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
}; 