<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>那年榕树下的枫叶</title>
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.dcjqaccordion.2.7.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/index.css"/>

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
            <li><a href="/forum/add">发帖</a></li>
            <#else >
                <li><i></i><span>未登录</span></li>
                <li><a href="/user/login">登录</a></li>
                <li><a href="/forum/add">发帖</a></li>
        </#if>
    </ul>
</div>



<div class="indexBoxWin">
    <p style="text-align: center;font-size: 48px">我来发帖</p>
    </br>
    <div>
       <form method="get" action="../forum/save" onsubmit="return insert()" style="line-height: 50px">
           <label>标题：</label><input type="text" id="title" name="title" style="background-color: burlywood;height: 30px"/></br>
           <label>内容：</label><textarea rows="8" cols="120" id="content" name="content"  style="background-color:burlywood"></textarea></br>
          <span style="margin-left: 300px"><input  onclick="insertOne()" type="submit" value="确认发帖" /></span>
       </form>
    </div>
    </div>
</div>

    <div class="indexBox">
        <div class="indexLogo"></div>


<script type="text/javascript" src="../js/main.js"></script>

</body>
</html>
<script type="text/javascript">
    var is=true
    function  insertOne(){
        var title=$("#title").val();
        var content =$("#content").val();
        if(title==''||content==''){
            alert("请填写帖子标题或者内容！")
          is=false;
        }
    }

    function insert(){
        return is;
    }
</script>