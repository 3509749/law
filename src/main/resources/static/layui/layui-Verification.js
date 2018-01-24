var Verification = function (form) {
    var config = {
    	      verify: {
    	          required: [
    	            /[\S]+/
    	            ,'必填项不能为空'
    	          ]
    	          ,phone: [
    	            /^1\d{10}$/
    	            ,'请输入正确的手机号'
    	          ]
    	          ,email: [
    	            /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
    	            ,'邮箱格式不正确'
    	          ]
    	          ,url: [
    	            /(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/
    	            ,'链接格式不正确'
    	          ]
    	          ,number: function(value){
    	            if(!value || isNaN(value)) return '只能填写数字'
    	          }
    	          ,date: [
    	            /^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/
    	            ,'日期格式不正确'
    	          ]
    	          ,identity: [
    	            /(^\d{15}$)|(^\d{17}(x|X|\d)$)/
    	            ,'请输入正确的身份证号'
    	          ],
    	          oldPwd : function(value,item){
    	                if (value.length == 0) {
    	                    return '请输入旧密码';
    	                }
    	                if(value!=$(form).find("#p").val()){
    	                	return "旧密码输入错误，请重新输入！";
    	                }
    	            }, 
    	          equalTo : function(value, param){
	                if ($(form).find("#password").val()!= value) {
	                    return '二次密码不一致';
	                }
	              
    	          }
    	        }
    	      };
    formElem = $(form);

    var button = $(this), verify = config.verify, stop = null
    ,DANGER = 'layui-form-danger', field = {} 	
    
    ,verifyElem = formElem.find('*[lay-verify]') //获取需要校验的元素
    ,fieldElem = formElem.find('input,select,textarea') //获取所有表单域
 
    //开始校验
    layui.each(verifyElem, function(_, item){
      var othis = $(this), ver = othis.attr('lay-verify').split('|');
      var tips = '', value = othis.val();
      othis.removeClass(DANGER);
      layui.each(ver, function(_, thisVer){
        var isFn = typeof verify[thisVer] === 'function';
        if(verify[thisVer] && (isFn ? tips = verify[thisVer](value, item) : !verify[thisVer][0].test(value)) ){
          layer.msg(tips || verify[thisVer][1], {
            icon: 5
            ,shift: 6
          });
          //非移动设备自动定位焦点
          if (!layui.device().android && !layui.device().ios) {
              item.focus();
          }
          othis.addClass(DANGER);
          return stop = true;
        }
      });
      if(stop) return stop;
    });
    
    if(stop) return false;
    
    layui.each(fieldElem, function(_, item){
      if(!item.name) return;
      if(/^checkbox|radio$/.test(item.type) && !item.checked) return;
      field[item.name] = item.value;
    });

    //返回序列化表单元素， JSON 数据结构数据。
    return formElem.serializeArray();
    //return true;
};