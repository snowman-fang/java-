<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>点餐系统</title>
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/jquery.dcjqaccordion.2.7.js"></script>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/index.css"/>

</head>

<body>

<div class="sidenav">

    <ul class="nav0">
        <li>
            <a href="#"><i class="navIcon01"></i>基本信息</a>
            <ul class="subnav dn">
                <li><a href="../order/index">商户列表</a></li>
                <#if role!='2'>
                    <li><a href="../order/resign">注册用户</a></li>
                </#if>
                <li><a href="01_preLoan03_history.html">商户个人信息</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="navIcon02"></i>业务信息</a>
            <ul class="subnav dn">
                <li><a href="02_riskManagement01_index.html">历史订单</a></li>
                <li><a href="02_riskManagement02_list.html">未完成订单</a></li>
                <li><a href="02_riskManagement03_importData.html">订单首页</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="navIcon03"></i>菜单配置</a>
            <ul class="subnav dn">
                <li><a href="03_set01_personalInfo.html">分类配置</a></li>
                <li><a href="03_set01_personalInfo.html">菜品配置</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="navIcon04"></i>统计信息</a>
            <ul class="subnav dn">
                <li><a href="04_companyManagement01_companyInfo.html">销售额统计</a></li>

            </ul>
        </li>
    </ul>
</div>

<div class="header">
    <ul class="user">
    <#if name??>
        <li><i></i><span>${name!}</span></li>
        <li><a href="/user/loginOut">退出</a></li>
    <#else >
        <li><i></i><span>未登录</span></li>
        <li><a href="/user/login">登录</a></li>
    </#if>
    </ul>
</div>

<div style="text-align: center;">
    <h1 style="font-size: 28px">商户列表</h1>
</div>

<div class="search" style="padding: 50px 50px 10px;">
    <form class="bs-example bs-example-form" role="form">
        <div class="input-group" style="padding-left: 400px">
            <span class="input-group-addon" style="width: 70px">城市</span>
            <input type="text" class="form-control" placeholder="请输入所在城市" style="width: 200px">
            <span class="input-group-addon" style="width: 70px">用户名</span>
            <input type="text" class="form-control" placeholder="请输入用户名" style="width: 200px">
            <span class="input-group-addon" style="width: 70px">负责人</span>
            <input type="text" class="form-control" placeholder="请输入负责人" style="width: 200px">

            <input type="submit" class="form-control" placeholder="查询" style="width: 100px;background-color: #00aeef">
            <input type="reset" class="form-control" placeholder="重置" style="width: 100px;background-color: #7cb61b">
        </div>
    </form>
</div>

<div class="content" style="padding-left: 200px">
    <table class="table">
       <caption style="text-align: center">用户数：190</caption>
        <thead>
        <tr>
            <th>城市</th>
            <th>用户名</th>
            <th>手机号</th>
            <th>角色</th>
            <th>负责人</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>合肥</td>
            <td>张三</td>
            <td>19941193860</td>
            <td>高级管理员</td>
            <td></td>
        </tr>
        <tr>
            <td>合肥</td>
            <td>李四</td>
            <td>19741193860</td>
            <td>商户</td>
            <td>张三</td>
        </tr>
        <tr>
            <td>合肥</td>
            <td>李四</td>
            <td>19741193860</td>
            <td>商户</td>
            <td>张三</td>
        </tr>
        <tr>
            <td>合肥</td>
            <td>李四</td>
            <td>19741193860</td>
            <td>商户</td>
            <td>张三</td>
        </tr>
        <tr>
            <td>合肥</td>
            <td>李四</td>
            <td>19741193860</td>
            <td>商户</td>
            <td>张三</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="container">

    <ul class="pager">
        <li class="previous"><a href="#">上一页</a></li>
        <li class="1">1</li>
        <li class="next"><a href="#">下一页</a></li>
    </ul>
</div>

<script src="../js/main.js"></script>

<script>


</script>

</body>
</html>
