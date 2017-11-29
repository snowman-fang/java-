<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <style type="text/css">
        *{
            margin: 0;
            padding:0;
        }
        .music{
            width: 250px;
            height: 450px;
            background:pink;
            margin-top: 100px;
            float: left;
            margin-left: 350px;
            border-radius: 15px;
        }
        .musicchoose{
            width: 250px;
            height: 450px;
            background:#0dbfdd;
            margin-top: 100px;
            float: left;
            margin-left: 100px;
            border-radius: 15px;
        }
        .music-title{
            font-size: 24px;
            font-weight: 400;
            font-family: "微软雅黑", "宋体", Arial, sans-serif;
            line-height: 40px;
            text-align: center;
        }
        #music-content{
            height: 370px;
            background-image: url(../images/backgroundMusic.gif);
            background-size: cover;
        }
        ::-webkit-scrollbar{width:1px;height: 6px;background:green}
        ::-webkit-clear-button{background-color: #00aeef;}
        ::-webkit-scrollbar-track{background: #ffffff;}
        ::-webkit-scrollbar-track-piece{background:burlywood;}
        ::-webkit-scrollbar-thumb{background: #00aeef;}
        ::-webkit-scrollbar-corner{background: #00aeef;}
        ::-webkit-scrollbar-resizer{background: #00aeef;}
        ::-webkit-scrollbar-3dlight-color{background-color: #00aeef}
        ::-webkit-scrollbar-base-color{background-color: #00aeef}
        scrollbar{-moz-appearance: none !important;background:rgb(0,255,0) !important; }
    </style>
    <title>music</title>

</head>
<body style="background-image:url(../images/back.jpg);background-repeat: no-repeat;background-size:100%">
<div style="margin: auto" >
    <div style="float: right;color:darkorange;font-weight: bold">
        欢迎你：${name!} <a href="/user/loginOut">退出登录</a>
    </div>
   <div class="music">
        <p class="music-title">假情真爱</p>
        <div id="music-content" style="padding-left: 30px;line-height: 30px;overflow: auto">


        </div>
        <div id="music-control" style="text-align: center">
            <div style="text-align: center;margin-top: 6px">
                <img id="left" src="../images/left.png"/>
                <img id="stop" src="../images/start.png"/>
                 <img id="right" src="../images/right.png"/>
            </div>
        </div>


        <div id="music-liri" style="display: none">
            男：你时而的热度|
            又时而的冷漠|
            我不知道要|
            怎么去迎合|
            夜里的寂寞|
            想你的温柔|
            伤心的时候|
            就为你唱情歌|
            你是想离开我|
            却不对我说|
            我已看明白|
            却又能如何|
            夜里的孤独|
            想你的问候|
            人在伤心时|
            泪才会流|
            女Rap：|
            心里只有你一个请你相信我|
            请你别用你的假情折磨我|
            伤心时候忍不住说伤心话|
            寂寞夜里眼泪就会滴滴嗒|
            男：你是假的情|
            我是真的爱|
            我的心都碎了|
            你也不明白|
            修来的缘分|
            伤了我的心|
            我已知道|
            我们的爱不可能重来|
            你时而的热度|
            又时而的冷漠|
            我不知道要|
            怎么去迎合|
            夜里的寂寞|
            想你的温柔|
            伤心的时候|
            就为你唱情歌|
            你是想离开我|
            却不对我说|
            我已看明白|
            却又能如何|
            夜里的孤独|
            想你的问候|
            人在伤心时|
            泪才会流|
            女Rap：|
            心里只有你一个请你相信我|
            请你别用你的假情折磨我|
            伤心时候忍不住说伤心话|
            寂寞夜里眼泪就会滴滴嗒|
            男：你是假的情|
            我是真的爱|
            我的心都碎了|
            你也不明白|
            修来的缘分|
            伤了我的心|
            我已知道|
            我们的爱不可能重来|
            你是假的情|
            我是真的爱|
            我的心都碎了|
            你也不明白|
            修来的缘分|
            伤了我的心|
            我已知道|
            我们的爱不可能重来|
            修来的缘分|
            伤了我的心|
            我不知道怎么去|
            把你唤回来|
            女Rap：|
            我要你的真心快回来|
            没有你的生活不精彩|
            让你知道什么是真爱|
            我的真心对你永不变
        </div>
   </div>
   <div  class="musicchoose">
       <p class="music-title">我的列表</p>
       <div id="musicchoose-content" style="padding-left: 30px;line-height: 30px;overflow: auto;background-color: hotpink">

           <div>吴小豪-假情真爱 <span style="margin-left: 50px">移除</span><div>

       </div>
   </div>
</div>
   <script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
   <script type="text/javascript">
         var audioDom=document.createElement("audio");
         var is=true;
         audioDom.src="../mp3/jiaqingzhenai.mp3";
         audioDom.autoplay="autoplay";
         var lirc=$("#music-liri").text();
         var licArray=lirc.split("|")
         var message=''
         for(var i=0;i<licArray.length;i++){
             message+="<div style='color:#ffffff'>"+licArray[i]+"</div>"
         }
         $("#music-content").html(message)
       $("#stop").click(function(){
           if(is){
               audioDom.pause();
               is=false;
               $("#stop").attr('src','../images/stop.png');
           }else{
               audioDom.play()
               is=true;
               $("#stop").attr('src','../images/start.png');
           }
       })
   </script>
</body>
</html>
