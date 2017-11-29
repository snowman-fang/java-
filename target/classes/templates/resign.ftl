<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>sign-up</title>
    <link rel="stylesheet" href="../css/resign.css">
</head>
<body>
<header>
    <div class="header-line"></div>
</header>
<div class="content">
    <img class="content-logo" src="../images/timg.jpg" alt="logo">
    <h1 class="content-title">创建账户</h1>
    <div class="content-form">
        <form method="get" action="/user/doResign" onsubmit="return submitTest()">
            <div id="change_margin_1">
               <input class="user" type="text" value="<#if user??>${user.name!}</#if>" id="name" name="name" placeholder="请输入用户名">
                <span id="resultName"></span>
            </div>
            <p id="remind_1"></p>
            <div>
                <input  class="user" type="text" value="<#if user??>${user.account!}</#if>"  placeholder="请输入账号" required="" id="account" name="account" />
                <span id="resultAccount"></span>
            </div>
            <!-- input的value为空时弹出提醒 -->
            <p id="remind_2"></p>
            <div id="change_margin_2">
                <input class="password" type="password" value="<#if user??>${user.password!}</#if>"  id="password" name="password" placeholder="请输入密码" onblur="oBlur_2()" onfocus="oFocus_2()">
            </div>
            <!-- input的value为空时弹出提醒 -->
            <p id="remind_3"></p>
            <div id="change_margin_3">
                <input class="content-form-signup" onclick="submitTest()" type="submit" value="创建账户">
            </div>
            <span style="color: #00aeef">${success!}</span>
        </form>
    </div>
    <div class="content-login-description">已经拥有账户？</div>
    <div><a class="content-login-link" href="login">登录</a></div>
</div>
<script src="../js/common_form_test.js"></script>
<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    var isName=true;
    $("#name").blur(function(){
        var name=$("#name").val();
        $.ajax({
            url:"/ajax/againName",
            type:"POST",
            dataType:"json",
            data:{"name":name},
            async:false,
            success:function(result){
                if(result.isRight=='yes'){
                    isName=false
                    $("#resultName").css('color','red')
                    $("#resultName").html('已存在')
                }else{
                    if(name!=""){
                        $("#resultName").css('color','green')
                        $("#resultName").html('ok')
                    }
                }
            }
        })
    })
    $("#account").blur(function(){
        var account=$("#account").val();
        $.ajax({
            url:"/ajax/againAccount",
            type:"POST",
            dataType:"json",
            data:{"account":account},
            async:false,
            success:function(result){
                if(result.isRight=='yes'){
                    isName=false
                    $("#resultAccount").css('color','red')
                    $("#resultAccount").html('已存在')
                }else{
                    if(account!=""){
                        $("#resultAccount").css('color','green')
                        $("#resultAccount").html('ok')
                    }
                }
            }
        })
    })
    function submitTest(){
        var password=$("#password").val();
        if(password.length<6){
            alert('密码长度小于6')
            return false
        }
        return isName;
    }

</script>
</body>
</html>