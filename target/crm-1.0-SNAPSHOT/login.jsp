<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登入</title>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>
<style>
    html {
        height: 100%;
    }

    body {
        margin: 0;
        padding: 0;
        font-family: sans-serif;
        background: linear-gradient(#141e30, #243b55);
    }

    .login-box {
        position: absolute;
        top: 50%;
        left: 50%;
        width: 400px;
        padding: 40px;
        transform: translate(-50%, -50%);
        background: rgba(0, 0, 0, .5);
        box-sizing: border-box;
        box-shadow: 0 15px 25px rgba(0, 0, 0, .6);
        border-radius: 10px;
    }

    .login-box h2 {
        margin: 0 0 30px;
        padding: 0;
        color: #fff;
        text-align: center;
    }

    .login-box .user-box {
        position: relative;
    }

    .login-box .user-box input {
        width: 100%;
        padding: 10px 0;
        font-size: 16px;
        color: #fff;
        margin-bottom: 30px;
        border: none;
        border-bottom: 1px solid #fff;
        outline: none;
        background: transparent;
    }

    .login-box .user-box label {
        position: absolute;
        top: 0;
        left: 0;
        padding: 10px 0;
        font-size: 16px;
        color: #fff;
        pointer-events: none;
        transition: .5s;
    }

    .login-box .user-box input:focus ~ label,
    .login-box .user-box input:valid ~ label {
        top: -20px;
        left: 0;
        color: #03e9f4;
        font-size: 12px;
    }

    .login-box form a {
        position: relative;
        display: inline-block;
        padding: 10px 20px;
        color: #03e9f4;
        font-size: 16px;
        text-decoration: none;
        text-transform: uppercase;
        overflow: hidden;
        transition: .5s;
        margin-top: 40px;
        letter-spacing: 4px
    }

    .login-box a:hover {
        background: #03e9f4;
        color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 5px #03e9f4,
        0 0 25px #03e9f4,
        0 0 50px #03e9f4,
        0 0 100px #03e9f4;
    }

    .login-box a span {
        position: absolute;
        display: block;
    }

    .login-box a span:nth-child(1) {
        top: 0;
        left: -100%;
        width: 100%;
        height: 2px;
        background: linear-gradient(90deg, transparent, #03e9f4);
        animation: btn-anim1 1s linear infinite;
    }

    @keyframes btn-anim1 {
        0% {
            left: -100%;
        }
        50%, 100% {
            left: 100%;
        }
    }

    .login-box a span:nth-child(2) {
        top: -100%;
        right: 0;
        width: 2px;
        height: 100%;
        background: linear-gradient(180deg, transparent, #03e9f4);
        animation: btn-anim2 1s linear infinite;
        animation-delay: .25s
    }

    @keyframes btn-anim2 {
        0% {
            top: -100%;
        }
        50%, 100% {
            top: 100%;
        }
    }

    .login-box a span:nth-child(3) {
        bottom: 0;
        right: -100%;
        width: 100%;
        height: 2px;
        background: linear-gradient(270deg, transparent, #03e9f4);
        animation: btn-anim3 1s linear infinite;
        animation-delay: .5s
    }

    @keyframes btn-anim3 {
        0% {
            right: -100%;
        }
        50%, 100% {
            right: 100%;
        }
    }

    .login-box a span:nth-child(4) {
        bottom: -100%;
        left: 0;
        width: 2px;
        height: 100%;
        background: linear-gradient(360deg, transparent, #03e9f4);
        animation: btn-anim4 1s linear infinite;
        animation-delay: .75s
    }

    @keyframes btn-anim4 {
        0% {
            bottom: -100%;
        }
        50%, 100% {
            bottom: 100%;
        }
    }

</style>
</head>
<body>
<div class="login-box">
    <h2>Login</h2>
    <form id="f" onsubmit="return false;">
        <div class="user-box">
            <input type="text" name="loginName" id="loginName"/>
            <label>Username</label>
        </div>
        <div class="user-box">
            <input type="password" id="loginPwd" name="loginPwd"/>
            <label>Password</label>
        </div>
        <input id="btnn" type="submit" value="Submit"><span id="msg"
                                                            style="color: red;font-weight: bolder"></span>
    </form>
</div>
</body>
<script>

    $(function () {

        if (window.top != window) {
            window.top.location = window.location;
        }

        //页面加载成功后，让用户文本框自动获得焦点
        $("#loginName").focus();

        //为登入按钮绑定事件 执行登入操作
        $("#btnn").click(function () {

            login()
        })
        //敲回车登入
        //event这个参数可以获取我们敲的是哪个键  13表示回车键
        $(window).keydown(function (event) {

            if (event.keyCode == 13) {

                login()
            }

        })
    })

    //登入操作
    function login() {

        //前端验证账号 密码不能为空
        var name = $.trim($("#loginName").val());
        var pw = $.trim($("#loginPwd").val());

        if (name == "" || pw == "") {

            $("#msg").html("账号或密码不能为空！！")

            $("#f").attr("action", "javascript:;")

            return false
        }

        //后台验证登入
        $.ajax({
            url: "${pageContext.request.contextPath}/user/login.do",
            data: {
                "loginName": name,
                "loginPwd": pw
            },
            type: "get",
            dataType: "json",
            success: function (data) {

                /*
                后端传回的数据格式
                {"sucess":true/false"}
                 */

                if (data.success) {
                    //登入成功 跳转到默认欢迎也
                    window.location.href = "workbench/index.jsp"

                } else {

                    $("#msg").html(data.msg)

                }

            }

        })
    }
</script>
</html>