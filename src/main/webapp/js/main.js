// JavaScript Document

/* 勾选操作 */
$(".check").click(function(){
	if($(this).hasClass("selected")){
		$(this).removeClass("selected");
	}else{
		$(this).addClass("selected");
	}
})

$(".roleType .check").click(function(){
	if($(this).hasClass("selected")){
		$(this).parent().next().children().addClass("selected");
	}else{
		$(this).parent().next().children().removeClass("selected");
	}
})

/* 导航折叠 */
$(function() {
    $(".nav0").dcAccordion({
        eventType: "click",
        autoClose: true,
        saveState: true,
        disableLink: true,
        speed: "slow",
        showCount: false,
        autoExpand: true,
        //cookie: "dcjq-accordion-1",
        classExpand: "dcjq-current-parent"
    });
});

/* 用户管理-城市切换 */
$(".editContent_right .titleBar li").click(function(){
	var a=$(".editContent_right .titleBar li");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".editContent_right .titleBar li").removeClass("on");
	$(".editContent_right .titleBar li").eq(x).addClass("on");
	$(".cityBox>div").hide();
	$(".cityBox>div").eq(x).show();
})

/* 点击相应区域，让其内的input获取焦点 */
$(".inputInfo0_content div span").click(function(){
	$(this).find("input").focus();
})

$(".inputInfo div span").click(function(){
	$(this).find("input").focus();
})

$(".inputInfo0 div span").click(function(){
	$(this).find("input").focus();
})

$(".dialogBox_content ul li span.span1").click(function(){
	$(this).find("input").focus();
})

/* 精准评估内容收缩 */
$(".more_btn").click(function(){
    $(this).parent(".separate").next("div").show();
    $(this).hide();
	$(this).next(".retract_btn").show();
})
$(".retract_btn").click(function(){
    $(this).parent(".separate").next("div").hide();
    $(this).hide();
	$(this).prev(".more_btn").show();
})

/* 增加押品房产 */
/*$(".addHouse").click(function(){
    var a=$(".addHouse");
    var x;
    for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
    }
    $(this).hide();
    $(".addHouse").eq(x+1).show();
    $(".inputInfo").eq(x+1).show();
    if(x>=2){
    	$(".inputInfoBox02").show();
    }
})*/

/* 删除押品房产 */
/*$(".delete").click(function(){
	var a=$(".delete");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".inputInfo").eq(x+1).hide();
	$(".addHouse").hide();
	$(".addHouse").eq(x).show();
	if(x<3){
    	$(".inputInfoBox02").hide();
    }
})*/

/* 款项用途类型选择 */
$(".radio").click(function(){
	var a=$(".radio");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".radio span").removeClass("current");
	$(".radio span").eq(x).addClass("current");
	$(".radio span i").removeClass("current");
	$(".radio span i").eq(x).addClass("current");
})

/* 说明文字动效 */
$(".note").hover(function(){
	n=$(".note").index(this);
	$(".noteText").eq(n).show();
},function(){
	n=$(".note").index(this);
	$(".noteText").eq(n).hide();
})
$(".card05 .description .table:nth-child(1) p").hover(function(){
	n=$(".note").index(this);
	$(".noteText").eq(n).show();
},function(){
	n=$(".note").index(this);
	$(".noteText").eq(n).hide();
})

/* 申请信息显示&隐藏 */
$(".applicationInfoBtn02a").click(function(){
	$(".applicationInfoBox").stop().animate({right:-496},1000,function(){
		$(".applicationInfoBtn02a").hide();
		$(".applicationInfoBtn02b").show();
	})
})
$(".applicationInfoBtn02b").click(function(){
	$(".applicationInfoBox").stop().animate({right:0},1000,function(){
		$(".applicationInfoBtn02b").hide();
		$(".applicationInfoBtn02a").show();
	})
})
$(".card07 .confirm_btn").click(function(){
	$(".card07a1").hide();
	$(".card07a2_processed").show();
})
$(".card07 .cancel_btn").click(function(){
	$(".card07a1").hide();
	$(".card07a2_abandoned").show();
})

/* 内容切换 */

// 申请管理页tab
$(".mainContainer>.tabBox li").click(function(){
	var a=$(".mainContainer>.tabBox li");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".mainContainer>.tabBox li").removeClass("current");
	$(".mainContainer>.tabBox li").eq(x).addClass("current");
	$(".card06").hide();
	$(".card06").eq(x).show();
})

// 押品详情页tab
$(".detail .tabBox li").click(function(){
	var a=$(".detail .tabBox li");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".detail .tabBox li").removeClass("current");
	$(".detail .tabBox li").eq(x).addClass("current");
	$(".detail .content").hide();
	$(".detail .content").eq(x).show();
})

// 押品详情页topic
$(".topic02 li").click(function(){
	var a=$(".topic02 li");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".topic02 li").removeClass("current");
	$(".topic02 li").eq(x).addClass("current");
	charts.reflow();
})

// 押品风控页positionTab
$(".positionTabBox li").click(function(){
	var a=$(".positionTabBox li");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".positionTabBox li").removeClass("current");
	$(".positionTabBox li").eq(x).addClass("current");
	$(".positionInfoBox .positionInfo").hide();
	$(".positionInfoBox .positionInfo").eq(x).show();
})

// 企业设置页tab
$(".tabBox01 li").click(function(){
	var a=$(".tabBox01 li");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".tabBox01 li").removeClass("current");
	$(".tabBox01 li").eq(x).addClass("current");
	$(".table").hide();
	$(".table").eq(x).show();
})

/* 城市选择设置切换 */
$(".defaultSet01").click(function(){
	$(".defaultSet01").hide();
	$(".defaultSet02").show();
})
$(".defaultSet02").click(function(){
	$(".defaultSet02").hide();
	$(".defaultSet01").show();
})

/* positionBox显示&隐藏 */
$(".positionIcon").click(function(){
	$(this).next().show();
})
$(".positionBox .close").click(function(){
	$(this).parent().hide();
})

/* 判断positionTabBox中的li个数，以便在li个数多的时候修改css样式 */
function CheckLI() {
    var ObjLi = document.getElementById("positionTabBox").getElementsByTagName("li"); // positionTabBox 是 ul 列表的 id
    var Num = ObjLi.length; // 取得 ul 中 li 的个数
    if (Num > 5) { // 如果 li 的个数大于 5 个
        $(".positionBox").css("width","460px");
		$(".positionTabBox").css("width","97px");
		$(".positionTabBox").css("overflow-x","hidden");
		$(".positionTabBox").css("overflow-y","auto");
    }
}

/* 押品列表-排序切换 */
$(".sequenceArrowArea").click(function(){
	var m = $(this).find("i.sequenceArrow02").hasClass("current");
	var n = $(this).find("i.sequenceArrow01").hasClass("current");
	if(m){
		$(this).parent().find("i").removeClass("current");
		$(this).find("i.sequenceArrow01").addClass("current");
	}else if(n){
		$(this).parent().find("i").removeClass("current");
		$(this).find("i.sequenceArrow02").addClass("current");
	}else{
		$(this).parent().find("i").removeClass("current");
		$(this).find("i.sequenceArrow02").addClass("current");
	}
})

/*$(".sequenceArrow i").click(function(){
	var a=$(".sequenceArrow i");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".sequenceArrow i").removeClass("current");
	$(".sequenceArrow i").eq(x).addClass("current");
})*/

/* 用户管理-用户列表内容切换 */
$(".edit").click(function(){
	$(".editContent01").show();
})

$(".save_btn,.return_btn").click(function(){
	$(".editContent01").hide();
})

$(".save01_btn").click(function(){
	$(".tabBox01 li").removeClass("current");
	$(".tabBox01 li").eq(0).addClass("current");
	$(".table").hide();
	$(".table").eq(0).show();
})

/* 打开弹窗 */

// 用户服务协议
$(".deal").click(function(){
	$(".mask").show();
	$(".dialogBox01").show();
})

// 贷款人详情信息
$(".number,.exist").click(function(){
	$(".mask").show();
	$(".dialogBox04").show();
})

// 评估历史
$(".addDatabase").click(function(){
	$(".mask").show();
	$(".dialogBox").show();
})

// 加到押品风控库
$(".confirm_btn").click(function(){
	var a=$(".confirm_btn");
	var x;
	for(var i=0;i<a.length;i++){
		if(a[i]==this){
			x=i;
			break;
		}
	}
	$(".addDatabase").eq(x).addClass("joined");
	$(".addDatabase").eq(x).text("已加入");
})

// 查看详情
$(".detailCheck").click(function(){
	$(".mask").show();
	$(".positionBox01").show();
})

// 贷款成本分析
$(".calculationBtn").click(function(){
	$(".mask").show();
	$(".dialogBox06").show();
})

// 导出报告
$(".exportReport").click(function(){
	$(".mask").show();
	$(".dialogBox02").show();
})

// 个人信息-变更手机号
$("#changePhonenumber").click(function(){
	$(".mask").show();
	$("#dialogBox_changePhonenumber").show();
})

// 个人信息-设置密码
$("#setPassword").click(function(){
	$(".mask").show();
	$("#dialogBox_setPassword").show();
})

// 参数设置
$(".change").click(function(){
	$(".mask").show();
	$(".dialogBoxa").show();
})

// 提醒设置
$(".ruleEdit,.addRule").click(function(){
	$(".mask").show();
	$(".dialogBoxb").show();
})

// 添加/编辑角色
$(".addRole,.editRole").click(function(){
	$(".mask").show();
	$(".dialogBox07").show();
})

// 查看权限
$(".checkRole").click(function(){
	$(".mask").show();
	$(".dialogBox08").show();
})

// 查看失败详情
$(".importFailed a").click(function(){
	$(".mask").show();
	$(".dialogBox10").show();
})

// 查看充值记录
$(".record").click(function(){
	$(".mask").show();
	$(".dialogBox11").show();
})

/* 直接关闭弹窗 */
$(".close").click(function(){
	$(".mask").hide();
	$(".dialogBox").hide();
})

/* 确定关闭弹窗 */
$(".confirm_btn").click(function(){
	$(".mask").hide();
	$(".dialogBox").hide();
})





