<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>那年榕树下的枫叶</title>
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.dcjqaccordion.2.7.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/index.css"/>
    <script type ="text/javascript" src="../js/artDialog.js?skin=default"></script>
    <#--<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>-->
</head>

<body>

<div class="sidenav">
    <ul class="nav">
        <li>
        	<a href="#"><i class="navIcon01"></i>青春论坛</a>
            <ul class="subnav dn">
            	<li><a href="../forum/list">论坛首页</a></li>
                <li><a href="01_preLoan02_evaluation01_inputHouseInfo.html">我的发帖</a></li>
                <li><a href="01_preLoan03_history.html">我的回复</a></li>
            </ul>
        </li>
        <li>
        	<a href="#"><i class="navIcon02"></i>诗词散文</a>
            <ul class="subnav dn">
            	<li><a href="02_riskManagement01_index.html">押品风控</a></li>
                <li><a href="02_riskManagement02_list.html">押品列表</a></li>
                <li><a href="02_riskManagement03_importData.html">导入数据</a></li>
            </ul>
        </li>
        <li>
        	<a href="#"><i class="navIcon03"></i>艺术作品</a>
            <ul class="subnav dn">
            	<li><a href="03_set01_personalInfo.html">个人信息</a></li>
            </ul>
        </li>
        <li>
        	<a href="#"><i class="navIcon04"></i>小说中心</a>
            <ul class="subnav dn">
            	<li><a href="04_companyManagement01_companyInfo.html">企业信息</a></li>
                <li><a href="04_companyManagement02_companySet.html">企业设置</a></li>
                <li><a href="04_companyManagement03_userManagement.html">用户管理</a></li>
            </ul>
        </li>
    </ul>
</div>

<div class="header">
    <ul class="user">
        <#if name??>
            <li><i></i><span>${name!}</span></li>
            <li><a href="/user/loginOut">退出</a></li>
            <li><a onclick="WriteTie()" href="#" >发帖</a></li>
            <#else >
                <li><i></i><span id="isRight">未登录</span></li>
                <li><a href="/user/login">登录</a></li>
                <li><a onclick="WriteTie()" href="#" >发帖</a></li>
        </#if>
    </ul>
</div>



<div class="indexBoxWin" style="width: 800px;text-align: center;margin: auto">
    <p style="text-align: center;font-size: 30px">${forum.title!}</p>
    </hr>
    </br>
    <div id="content" style="border: solid gray 1px; height: 200px;float: left">
        ${forum.content!}
    </div>
    <div style="border: solid gray 1px;text-align: center">
         发帖人：${forum.uname!}&nbsp;&nbsp;&nbsp;&nbsp;  发帖时间：${forum.writeTime!}
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" id="reply" >回复</a>
    </div>
    <div id="dialog" style="border: solid gray 1px;">
           <textarea rows="10" cols="18"></textarea>
    </div>
    <div>

    </div>
    <div style="text-align: center;">
        共${sumTiao!}条数据 第${page!}页/共${sumPage!}页
    </div>
    <div style="text-align: right;">
        <a id="first" href="#">首页</a> <a id="previous" href="#">上一页</a> <a id="next" href="#">
        下一页</a> <a id="last" href="#">末页</a>
    </div>
    </div>


    <div class="indexBox">
        <div class="indexLogo"></div>
    </div>



<script src="../js/main.js"></script>

<script>

/* 判断浏览器版本 */
function myBrowser(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
    var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器
    if (isIE) {
        var IE5 = IE55 = IE6 = IE7 = IE8 = false;
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        IE55 = fIEVersion == 5.5;
        IE6 = fIEVersion == 6.0;
        IE7 = fIEVersion == 7.0;
        IE8 = fIEVersion == 8.0;
        if (IE55) {
            return "IE55";
        }
        if (IE6) {
            return "IE6";
        }
        if (IE7) {
            return "IE7";
        }
        if (IE8) {
            return "IE8";
        }
    }//isIE end
    if (isFF) {
        return "FF";
    }
    if (isOpera) {
        return "Opera";
    }
}//myBrowser() end
//以下是调用上面的函数
if (myBrowser() == "IE55") {
    alert("请使用IE9版本以上的浏览器");
}
if (myBrowser() == "IE6") {
    alert("请使用IE9版本以上的浏览器");
}
if (myBrowser() == "IE7") {
    alert("请使用IE9版本以上的浏览器");
}
if (myBrowser() == "IE8") {
    alert("请使用IE9版本以上的浏览器");
}
function WriteTie(){
    var is=$("#isRight").text();
    if(is=='未登录'){
        alert("您还没登录！请先登录哦！")
    }else{
        window.location.href="/forum/add"
    }
}


$("#reply").click(function(){

/*    var dialog = art.dialog({
        title: '欢迎',

        content: '欢迎使用artDialog对话框组件！',

        icon: 'succeed',

        follow: document.getElementById('btn2'),

        ok: function(){

            return false;

        },okVal: '确定',

        cancelVal: '关闭',

        cancel: function(){

            console.log(123);

        },fixed:true,

        resize:true
    })*/
})
</script>




</body>
</html>
