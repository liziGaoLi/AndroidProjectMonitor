//验证手机号码
function isPoneAvailable(tel) {
     var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
     if (!myreg.test(tel)) {
         return false;
     } else {
         return true;
     }
 }


//获得短信验证码，60秒倒计时
var countdown=60;
function settime(val) {
    if (countdown == 0) {
        val.innerHTML="获取验证码";
        val.setAttribute("data-state",1);
        countdown = 60;
    } else {
        val.setAttribute("disabled", true);
        val.innerHTML="(" + countdown + ")s";
        countdown--;
        setTimeout(function() {
            settime(val)
        },1000)
    }

}


function getParamsArr(url){
  var vars = [], hash;
  var q = url.split('?')[1];
  if(q != undefined){
       q = q.split('&');
       for(var i = 0; i < q.length; i++){
            hash = q[i].split('=');
            vars.push(hash[1]);
            vars[hash[0]] = hash[1];
       }
       return vars;
  };

}
//格式化时间
function formatTime(ms){
  var date = new Date(ms*1000);
  var year = date.getFullYear();
  var month = (date.getMonth() + 1)<10?'0'+(date.getMonth() + 1):(date.getMonth() + 1);
  var day = date.getDate()<10?'0'+date.getDate():date.getDate();
  var hour = date.getHours()<10?'0'+date.getHours():date.getHours();
  var minute = date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes();
  return year+'/'+month+'/'+day+' '+ hour + ':'+minute;
}
//格式化字符串
function substrPrice(str){
  if(str.indexOf('.') > -1){
    return str.substring(0,str.indexOf('.'));
  }
  return str;
}
// 给金额添加正负号
function decoNum(num){
  if(num>0){
    return '+'+num;
  }
  return num;
}
