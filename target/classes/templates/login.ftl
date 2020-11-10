<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/login.css"/>

    <title>登录</title>

</head>
<body>
<div class="container">
    <section id="content">
        <form action="/user/doLogin">
            <h1>点餐系统后台</h1>
            <div>
                <input type="text" name="account" placeholder="Account" required="" id="account" />
            </div>
            <div>
                <input type="password" name="password" placeholder="Password" required="" id="password" />
            </div>
            ${message!}
            <div style="padding-left: 100px">
                <input type="submit" value="Log in" />
                <#--<a href="resign">Don't have account?</a>
                <a href="#">Register</a>-->
            </div>
        </form><!-- form -->
        <div class="button">
           <#-- <a href="#">Download source file</a>-->
        </div><!-- button -->
    </section><!-- content -->

</body>
</html>
