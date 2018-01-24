var tabMap = new HashMap();
function initEnterToTab(){
	tabMap = new HashMap();
	$("form").find("input,select").each(function(i,val){
		if(typeof($(val).attr("tabindex")) != "undefined" ){
			if($(val)[0].tagName == "SELECT")
				tabMap.put($(val).attr("tabindex")-0,$(val).next().find("input"));
			else
				tabMap.put($(val).attr("tabindex")-0,$(val));
		}
	});
}
function nextFoucs(index){
	index = getNextIndex(index);
	tabMap.get(index).focus();
	if("undefined" == typeof(tabMap.get(index).attr("tabindex")))
		tabMap.get(index).parent().parent().addClass("layui-form-selected");
}

function getNextIndex(index){
	var result = 999;
	for(var i in tabMap.keys()){
		var key = tabMap.keys()[i] - 0;
		if(key > index&&result > key)
			result = key;
	}
	return result==999?1:result;
}


