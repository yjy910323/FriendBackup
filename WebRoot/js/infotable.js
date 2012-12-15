
//创建分页栏

function createPagination(allPageNum,currentPage,type,f){
	var p = $('#'+type+'pagination');
	p.html("");//清空分页栏内容
	p.append("<li id='"+type+"paginationpre'><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(currentPage-1)+"' onclick='"+f+"'>«</a></li>");
	var pre = $('#'+type+'paginationpre');
	if(allPageNum==0){
		pre.addClass('disabled');
		p.append("<li class='active'><a href='#'>1</a></li>");
	   	p.append("<li id='"+type+"paginationnex' class='disabled'><a href='#'>»</a></li>");
	}else if(allPageNum<=3){
		for(var i=0;i<allPageNum+1;i++){
			if((currentPage-1)==i){
				p.append("<li class='active'><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(parseInt(i)+1)+"' onclick='"+f+"'>"+parseInt(parseInt(i)+1)+"</a></li>");
			}else{
				p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(parseInt(i)+1)+"' onclick='"+f+"'>"+parseInt(parseInt(i)+1)+"</a></li>");
			}
		}
		if((currentPage-1)==allPageNum){
			p.append("<li id='"+type+"paginationnex' class='disabled'><a href='#'>»</a></li>");
		}else{
			p.append("<li id='"+type+"paginationnex'  ><a data-type='"+type+"' data-currentpage='"+parseInt(parseInt(currentPage)+1)+"' onclick='"+f+"' href='#'>»</a></li>");
		}
		if(currentPage==1) pre.addClass('disabled');
	}else{
		if(currentPage==1){
			pre.addClass('disabled');
			p.append("<li class='active'><a href='#' data-type='"+type+"' data-currentpage='1' onclick='"+f+"'>1</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='2' onclick='"+f+"'>2</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='3' onclick='"+f+"'>3</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(parseInt(allPageNum)+1)+"' style='margin-left:15px;' onclick='"+f+"'>"+parseInt(parseInt(allPageNum)+1)+"</a></li>");
			p.append("<li id='"+type+"paginationnex' ><a data-type='"+type+"' data-currentpage='"+parseInt(parseInt(currentPage)+1)+"' onclick='"+f+"' href='#'>»</a></li>");
		}else if(currentPage==2){
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='1' onclick='"+f+"'>1</a></li>");
			p.append("<li class='active'><a data-type='"+type+"' data-currentpage='2' href='#' onclick='"+f+"'>2</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='3' onclick='"+f+"'>3</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(parseInt(allPageNum)+1)+"' style='margin-left:15px;' onclick='"+f+"'>"+parseInt(parseInt(allPageNum)+1)+"</a></li>");
			p.append("<li id='"+type+"paginationnex' ><a data-type='"+type+"' data-currentpage='"+parseInt(parseInt(currentPage)+1)+"' onclick='"+f+"' href='#'>»</a></li>");
		}else if((currentPage-1)==allPageNum){
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='1' style='margin-right:15px;' onclick='"+f+"'>1</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(currentPage-2)+"' onclick='"+f+"'>"+parseInt(currentPage-2)+"</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(currentPage-1)+"' onclick='"+f+"'>"+parseInt(currentPage-1)+"</a></li>");
			p.append("<li class='active'><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(currentPage)+"' onclick='"+f+"'>"+parseInt(currentPage)+"</a></li>");
			p.append("<li id='"+type+"paginationnex' class='disabled' ><a href='#'>»</a></li>");
		}else if(currentPage==allPageNum){
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='1' style='margin-right:15px;' onclick='"+f+"'>1</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(currentPage-1)+"' onclick='"+f+"'>"+parseInt(currentPage-1)+"</a></li>");
			p.append("<li class='active'><a data-type='"+type+"' data-currentpage='"+parseInt(currentPage)+"' href='#' onclick='"+f+"'>"+parseInt(currentPage)+"</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(parseInt(currentPage)+1)+"' onclick='"+f+"'>"+parseInt(parseInt(currentPage)+1)+"</a></li>");
			p.append("<li id='"+type+"paginationnex'><a data-type='"+type+"' data-currentpage='"+parseInt(parseInt(currentPage)+1)+"' onclick='"+f+"' href='#'>»</a></li>");
		}else{
			p.append("<li><a href='#' style='margin-right:15px;' data-type='"+type+"' data-currentpage='1' onclick='"+f+"'>1</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(currentPage-1)+"' onclick='"+f+"'>"+parseInt(currentPage-1)+"</a></li>");
			p.append("<li class='active'><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(currentPage)+"' onclick='"+f+"'>"+parseInt(currentPage)+"</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(parseInt(currentPage)+1)+"' onclick='"+f+"'>"+parseInt(parseInt(currentPage)+1)+"</a></li>");
			p.append("<li><a href='#' data-type='"+type+"' data-currentpage='"+parseInt(allPageNum+1)+"' style='margin-left:15px;' onclick='"+f+"'>"+parseInt(allPageNum+1)+"</a></li>");
			p.append("<li id='"+type+"paginationnex'><a data-type='"+type+"' data-currentpage='"+parseInt(parseInt(currentPage)+1)+"' onclick='"+f+"' href='#'>»</a></li>");	
		}
	}
}

//createPagination函数中F参数，表示对不同分夜数的不同动作

function paginationDetailInfo(e){
	if($(e).attr('data-currentpage')>0)
		getDetailInfo($(e).attr('data-type'),$(e).attr('data-currentpage'));
}



//向DetailInfo servlet请求当前页的人物数据

function getDetailInfo(type,currentpage,str){
	$.ajax({
		url:"servlet/DetailInfo",
		async:false,
		type:"get",
		dataType:"json",
		data:{type:type,currentPageNum:parseInt(currentpage-1)*10,str:str}
	}).done(function(data){
		//分析服务器传递的数据
		if(data.code=="nonuser"){
			//用户没有登录
			$('#modal_login').modal( {
				backdrop : true,
				keyboard : true
			});
		}else if(data.code=="nonlinkedin"){
			//linkedin没有授权，弹出授权框
			$('#modal_SNSaccess').modal( {
				backdrop : true,
				keyboard : true
			});
		}else if(data.code=="linkedin"){
			//处理linkedin信息
			
			//对页面进行css样式控制
			$('#linkedinli').removeClass('hide');
			$('#linkedininfo').removeClass('hide');
			
			$('#qqli').removeClass('active');$('#qqinfo').removeClass('active');
			$('#doubanli').removeClass('active');$('#doubaninfo').removeClass('active');
			$('#renrenli').removeClass('active');$('#renreninfo').removeClass('active');
			$('#weiboli').removeClass('active');$('#weiboinfo').removeClass('active');
			
			$('#linkedinli').addClass('active');
			$('#linkedininfo').addClass('active');
			
			$('#linkedintbody').html('');
			
			$('#linkedintab a:first').tab('show');//返回第一tab
			
			
			//生成table
			var allPageNum = parseInt(data.info._total/10);
			for(var i = 0;i<data.info.values.length;i++){
				var t = $('#linkedintbody');
				current = data.info.values[i];
				currentname = current.firstName+" "+current.lastName;
				currentid = current.id;
				currentHeadline = current.headline;
				currentindustry = current.industry;
				currentposition = "";currenteducation = "";currentPhoneNumber = "";
				if(current.positions._total!=0)
					currentposition = current.positions.values[0].company.name;
				if(current.educations._total!=0)
					currenteducation = current.educations.values[0].schoolName;
				if(current.location._total!=0)
					currentLocation = current.location.name;
				
				t.append("<tr id='"+currentid+"'  class='trhover'><td><strong>"+parseInt(i+(currentpage-1)*10)+"</strong></td><td><a href='#' onclick='oneDetail(this)' data-type='linkedin' data-iid='"+currentid+"'>"+currentname+"</a></td><td>"+currentHeadline+"</td>" +
						"<td>"+currentindustry+"</td><td>"+currentposition+"</td><td>"+currenteducation+"</td><td>"+currentLocation+"</td></tr>");
				//t.append('<td><i class="icon-search"></td>');
				//td.append("<td><a href='#' onclick='oneDetail(this)' data-type='linkedin' data-iid='"+currentid+"'>"+currentname+"</a></td>");
				
				
			}
			
			//关注按钮的出现地方，hover动作实现
			
			$("#linkedinalltable tbody tr").hover(function(){
				if($(this).data("attflag")==null||$(this).data("attflag")=="0"){
					//$("#linkedincancelatt").css({display:"none"});
					$("#linkedinatt").css({display:"block"});
					$("#linkedinatt").css({
						top:$(this).position().top+"px",
						left:parseInt($(this).position().left+1002)+"px",
						position:"absolute",
					});
					//iid为用户id（授权后根据api由服务器发送的唯一id）
					$("#linkedinatt").data("data-iid",$(this).find('td').eq(1).find("a").eq(0).attr('data-iid'));
					
					
				}else{
	
				}
				
			},function(){

			});
			
			
			//建立当前情况下分页栏。
			createPagination(allPageNum,currentpage,"linkedin","paginationDetailInfo(this)");
			
		}else if(data.code=="qq"){
			$('#qqli').removeClass('hide');
			$('#qqinfo').removeClass('hide');
			
			$('#linkedinli').removeClass('active');$('#linkedininfo').removeClass('active');
			$('#doubanli').removeClass('active');$('#doubaninfo').removeClass('active');
			$('#renrenli').removeClass('active');$('#renreninfo').removeClass('active');
			$('#weiboli').removeClass('active');$('#weiboinfo').removeClass('active');
			
			$('#qqli').addClass('active');
			$('#qqinfo').addClass('active');
		}else if(data.code=="douban"){
			$('#doubanli').removeClass('hide');
			$('#doubaninfo').removeClass('hide');
			
			$('#linkedinli').removeClass('active');$('#linkedininfo').removeClass('active');
			$('#qqli').removeClass('active');$('#qqinfo').removeClass('active');
			$('#renrenli').removeClass('active');$('#renreninfo').removeClass('active');
			$('#weiboli').removeClass('active');$('#weiboinfo').removeClass('active');
			
			
			$('#doubanli').addClass('active');
			$('#doubaninfo').addClass('active');
		}else if(data.code=="renren"){
			$('#renrenli').removeClass('hide');
			$('#renreninfo').removeClass('hide');
			
			$('#linkedinli').removeClass('active');$('#linkedininfo').removeClass('active');
			$('#qqli').removeClass('active');$('#qqinfo').removeClass('active');
			$('#doubanli').removeClass('active');$('#doubaninfo').removeClass('active');						
			$('#weiboli').removeClass('active');$('#weiboinfo').removeClass('active');
			
			
			$('#renrenli').addClass('active');
			$('#renreninfo').addClass('active');
		}else if(data.code=="weibo"){
			$('#weiboli').removeClass('hide');
			$('#weiboinfo').removeClass('hide');
			
			$('#linkedinli').removeClass('active');$('#linkedininfo').removeClass('active');
			$('#qqli').removeClass('active');$('#qqinfo').removeClass('active');
			$('#doubanli').removeClass('active');$('#doubaninfo').removeClass('active');
			$('#renrenli').removeClass('active');$('#renreninfo').removeClass('active');							
			
			
			$('#weiboli').addClass('active');
			$('#weiboinfo').addClass('active');
		}
	});
	
}

//向servlet DetailSearch返送搜索信息

function infosearch(e){
	$.ajax({
		url:"servlet/DetailSearch",
		async:false,
		type:"get",
		data:{type:$(e.infosearchbtn).attr('data-type'),key:$(e.infosearchtxt).val()}
	}).done(function(data){
		
		
		$('#linkedintab li:eq(1) a').tab('show');//将页面转向搜索tab
		
		//建立搜索结果table
		var t2 = $('#linkedinsearchtable');
		t2.html("");
		t2.append('<thead><tr><th>#</th><th>name</th><th>headline</th><th>industry</th><th>position</th><th>education</th><th>location</th></tr></thead>');
		t2.append('<tbody id=\"linkedinsearchtbody\">');		
		t2.append('</tbody>');
		for(var i = 0;i<data.result.length;i++){
			var t = $('#linkedinsearchtbody');
			current = data.result[i];
			currentname = current.firstName+" "+current.lastName;
			currentid = current.id;
			currentHeadline = current.headline;
			currentindustry = current.industry;
			currentposition = "";currenteducation = "";currentPhoneNumber = "";
			if(current.positions._total!=0)
				currentposition = current.positions.values[0].company.name;
			if(current.educations._total!=0)
				currenteducation = current.educations.values[0].schoolName;
			if(current.phoneNumbers._total!=0)
				currentLocation = current.location.name;
				
			t.append("<tr class='trhover' id='search_"+currentid+"'><td>"+i+"</td><td><a href='#' onclick='oneDetail(this)' data-type='linkedin' data-iid='"+currentid+"'>"+currentname+"</a></td><td>"+currentHeadline+"</td>" +
					"<td>"+currentindustry+"</td><td>"+currentposition+"</td><td>"+currenteducation+"</td><td>"+currentLocation+"</td></tr>");
			
			
			//为素搜索table绑定hover 关注动作。
			$("#linkedinsearchtable tbody tr").hover(function(){
				//alert("1");
				if($(this).data("attflag")==null||$(this).data("attflag")=="0"){
					//$("#linkedincancelatt").css({display:"none"});
					$("#linkedinatt").css({display:"block"});
					$("#linkedinatt").css({
						top:$(this).position().top+"px",
						left:parseInt($(this).position().left+1002)+"px",
						position:"absolute",
					});
					$("#linkedinatt").data("data-iid",$(this).find('td').eq(1).find("a").eq(0).attr('data-iid'));
					
					
				}else{

				}
				
			},function(){	
				
			});
		}
			
	});
}

//下载ajax。  as为下载格式，tp为类型

function download(as,tp){

	$.ajax({
		url:"servlet/UserAccess",
		async:false,
		type:"get",
		dataType:"json",
	}).done(function(data){
		if(data.code==0){
			if(as=="attasxls"){
				var listatt = "";
				var tmp = $("#linkedininfolettertable tr");
				
				tmp.each(function(){
					
					//alert($(this).find('td').eq(1).find("a").eq(0).attr('data-iid'));
					listatt += String($(this).find('td').eq(1).find("a").eq(0).attr('data-iid'))+"+";
				});
				
			}
			window.open("servlet/Download?as="+as+"&type="+tp+"&tmp="+listatt);
		}else if(data.code==1){
			$('#modal_access').modal( {
				backdrop : true,
				keyboard : true
			});
			
		}
	});
}

//设置提取码ajax


function setAccess(e){
	$.ajax({
		url:"servlet/UserAccess",
		async:false,
		type:"post",
		dataType:"json",
		data:{pd:e.acceesspd.value,ac:e.acceessac.value}
	}).done(function(data){
		if(data.code==0){
			$('#accessalert').removeClass('alert-error');
			$('#accessalert').removeClass('hide');
			$('#accessalert').addClass('alert-info');
			$('#accessalert').html('提取码设置成功，请牢记密码，再次点击下载！');
			setTimeout("$('#modal_access').modal('hide')",2000);
		}else if(data.code==2){
			$('#accessalert').removeClass('hide');
			$('#accessalert').removeClass('alert-info');
			$('#accessalert').addClass('alert-error');
			$('#accessalert').html("当前密码错误，请重新填写！");
			setTimeout("$('#accessalert').addClass('hide');",5000);
		}
		
	});
}


//获取一个人信息


function oneDetail(e){
	$("#linkedinatt").css({display:"none"});
	$("#linkedincancelatt").css({display:"none"});
	if($("#one_"+$(e).attr("data-iid")).length!=0){
		
		$("#linkedintab a[href='#one_"+$(e).attr("data-iid")+"']").tab('show');
		return;
	}
	$.ajax({
		url:"servlet/OneInfo",
		async:false,
		type:"get",
		dataType:"json",
		data:{id:$(e).attr("data-iid"),type:$(e).attr("data-type")}
	}).done(function(data){
		if(data.code=="linkedin"){
			
			//建立个人页面
			var result = data.info;
			var cont = $('#linkedintabcontent');
			$('#linkedintab').append("<li id='one_"+result.id+"li'><a href='#one_"+result.id+"' data-toggle='tab'>"+result.name+"<strong class='tab-close close' onclick='closetab(\"one_"+result.id+"\")'>x</strong></li>");
			
			cont.append("<div class='tab-pane' style='margin-top: 15px;' id='one_"+result.id+"'></div>");
			
			var cont2 = $('#one_'+result.id);
			
			
			
			resultPhoneNumber = "";
			if(result.phoneNumbers._total!=0)
				resultPhoneNumber = result.phoneNumbers.values[0].phoneNumber;
			
			
			cont2.html("<div class='row2' style='width: 1000px;background-color: rgb(240,240,240)'><div style='width: 350px;float: left;margin-left:10px;'><div class='row2'><div style='width:100px;height:100px;float: left;'><img src="+result.pictureUrl+"></div><div class='row2' style='width:250px;height:auto;float: left;'><div style='font-size: 27px;'>"+result.firstName+" "+result.lastName+"</div><div style='font-size: 17px;margin-top: 5px;'>"+result.headline+"</div><div style='font-size: 15px;margin-top: 5px;'>"+result.industry+"</div><div style='font-size: 15px;margin-top: 5px;'>"+result.location.name+"</div></div></div>" +
					"" +"<div class='row2' style='margin-top: 5px;'>Phone : "+resultPhoneNumber+"</div><div class='row2' style='margin-top: 5px;'>WebSite:</div><div class='row2' style='width:100px;margin-top: 5px;'><button class='btn btn-primary btn-block'>Send Mail</button></div><div class='row2' style='width:100px;margin-top: 5px;'><button class='btn btn-primary btn-block'>关注</button></div>	<div class='row2' style='width:100px;margin-top: 5px;'><button class='btn btn-primary btn-block'>PDF</button></div></div>"+
					"<div class='row2' style='width: 640px;float: left;margin-top: -10px;'><div style=''><h3>Summary</h3><p style='word-break: all;word-break:break-all;'>"+result.summary+"</p><hr /></div><div style=''><h3>Experience</h3><ul id='one_"+result.id+"exp'></ul><hr /></div><div style=''><h3>Education</h3><ul id='one_"+result.id+"edu'></ul><hr /></div></div></div>");
			
			
			var cont3 = $('#one_'+result.id+"exp");
			for( var i=0;i<result.positions._total;i++){
				endDateYear="";endDateMonth="";
				if(result.positions.values[i].isCurrent!="true"&&result.positions.values[i].endDate!=null){
					if(result.positions.values[i].endDate.month!=null)
						endDateMonth = result.positions.values[i].endDate.month;
					if(result.positions.values[i].endDate.year!=null)
						endDateYear = result.positions.values[i].endDate.year;
				}
				
				startDateYear="";startDateMonth="";
				if(result.positions.values[i].startDate!=null){
					if(result.positions.values[i].startDate.month!=null)
						startDateMonth = result.positions.values[i].startDate.month;
					if(result.positions.values[i].startDate.year!=null)
						startDateYear = result.positions.values[i].startDate.year;
				}
				
				
				
				
				
				
				cont3.append("<li style='font-size: 20px;'>"+result.positions.values[i].title+"</li><p>"+result.positions.values[i].company.name+"("+startDateYear+"."+startDateMonth+" - "+endDateYear+"."+endDateMonth+")</p><p>"+result.positions.values[i].summary+"</p>");
			}
			
			var cont4 = $('#one_'+result.id+"edu");
			for(var i=0;i<result.educations._total;i++){
				
				cont4.append("<li style='font-size: 17px;'>"+result.educations.values[i].schoolName+"  "+result.educations.values[i].fieldOfStudy+"  "+result.educations.values[i].degree+"</li>");
			}
			

			
			$("#linkedintab a[href='#one_"+result.id+"']").tab('show');//将页面转向个人页面 id为人物id
		
		}else if(data.code==2){
			
		}
		
	});
	
}


//关闭tab
function closetab(id){
	$("#"+id+"li").remove();
	$("#"+id).remove();
	$('#linkedintab a:first').tab('show');
}

//关注
function att(e){
	$('#linkedinaddatt').css({
		bottom:"0px",
		left:parseInt($(window).width()/2-50)+"px",
		//display:"none",
	});
	$('#linkedinaddatt').html("添加关注");
	
	//显示动画效果
	$('#linkedinaddatt').animate({
		bottom:"350px",
		opacity: "show",
		
	},"slow",function(){
		$('#linkedinaddatt').fadeOut();
	});
	
	$("#linkedinatt").css({display:"none"});
	$("#"+$("#linkedinatt").data("data-iid")).data("attflag","1");
	$("#search_"+$("#linkedinatt").data("data-iid")).data("attflag","1");
	currentTarget =  $("#"+$("#linkedinatt").data("data-iid"));
	$("#linkedininfolettertable").append("<tr class='trhover' id='att_"+$("#linkedinatt").data("data-iid")+"' data-iid='"+$("#linkedinatt").data("data-iid")+"'>"+currentTarget.html()+"</tr>");
	$("#attnum").html(parseInt(parseInt($("#attnum").html())+1));//关注数+1

	$("#linkedininfolettertable tr").hover(function(){
		//alert("1");
		$("#linkedinatt").css({display:"none"});
		$("#linkedincancelatt").css({display:"block"});
		$("#linkedincancelatt").css({
			top:$(this).position().top+"px",
			left:parseInt($(this).position().left+1002)+"px",
			position:"absolute",
		});
		$("#linkedincancelatt").attr("data-iid",$(this).find('td').eq(1).find("a").eq(0).attr('data-iid'));
	
	},function(){

	});

}

//取消关注
function cancelatt(e){
	$('#linkedinaddatt').css({
		bottom:"0px",
		left:parseInt($(window).width()/2-50)+"px",
	});
	$('#linkedinaddatt').html("取消关注");
	$('#linkedinaddatt').animate({
		bottom:"350px",
		opacity: "show",
	},"slow",function(){
		$('#linkedinaddatt').fadeOut();
	});
	$("#linkedincancelatt").css({display:"none"});
	$("#attnum").html(parseInt(parseInt($("#attnum").html())-1));
	$("#att_"+$("#linkedincancelatt").attr("data-iid")).detach();
	$("#"+$("#linkedincancelatt").attr("data-iid")).data("attflag","0");
	$("#search_"+$("#linkedincancelatt").attr("data-iid")).data("attflag","0");

}

