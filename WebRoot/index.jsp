<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />

		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

		<title>index</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta name="description" content="" />
		<meta name="author" content="sivyan" />
		<meta name="viewport" content="width=device-width; initial-scale=1.0" />
		
		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico" />
		<link rel="apple-touch-icon" href="/apple-touch-icon.png" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/bootstrap-tooltip.js"></script>
		<script type="text/javascript" src="js/bootstrap-popover.js"></script>
		<script type="text/javascript" src="js/bootstrap-tab.js"></script>
		<script type="text/javascript" src="js/bootstrap-affix.js"></script>
		<script type="text/javascript" src="js/bootstrap-modal.js"></script>
		<script type="text/javascript" src="js/bootstrap-transition.js"></script>
		<script type="text/javascript" src="js/bootstrap-dropdown.js"></script>
		<script type="text/javascript" src="js/infotable.js"></script>
		
		<script type="text/javascript">
			function toreg(){
				$('#modal_login').modal('hide');
				$('#modal_register').modal('show');
			}
			
			function tologin(){
				$('#modal_register').modal('hide');
				$('#modal_login').modal('show');
				
			}
			
			function getRenRenCode () {
				var mw = window.open("https://graph.renren.com/oauth/authorize?client_id=bb7cdafa82d84d42aef2be1d2ebbecad&response_type=code&redirect_uri=http://127.0.0.1:8020/FriendsBackup/renrencode.html");
			}
			
			function getLinkedinCode(){
				$.ajax({
					url:"servlet/LinkedinAccess",
					async:false,
					type:"post",
				}).done(function(url){
					window.open(url);
				});
			}
			
			function getLinkedinAccess(vpid,success,name,form){
				var vp = $("#"+vpid).val();
				$.ajax({
					url:"servlet/LinkedinAccess?vp="+vp,
					async:false,
					type:"get",
				}).done(function(flag){
					if(flag!=""){
						$("#"+form).addClass("hide");
						$("#"+name).html(flag);
						$("#"+success).removeClass("hide");
						
					}else{
						alert("授权失败，请重新尝试！");
					}
				});
			}
			
			
			
			function reg(){
				$.post(
					"servlet/Reg",
					{name:$("#account").val(),pd:$("#password").val()},
					function(data){
						if(data==0){
							$('#regalert').removeClass('alert-error');
							$('#regalert').removeClass('hide');
							$('#regalert').addClass('alert-info');
							$('#regalert').html('注册成功！3秒后将自动打开登录页面。');
							setTimeout("$('#modal_register').modal('hide')",3000);
							setTimeout("$('#modal_login').modal('show')",3000);
							setTimeout("$('#regalert').addClass('alert-info hide')",3000);
						}else{
							$('#regalert').removeClass('hide');
							$('#regalert').removeClass('alert-info');
							$('#regalert').addClass('alert-error');
							$('#regalert').html("注册失败，请检查填写内容。");
							setTimeout("$('#regalert').addClass('hide');",5000);
						}
					}
				);
			}
			
			function setSNSAccess(){
				$.post(
					"servlet/SNSAccess",
					function(data){
						if(data==0){
							$('#snsalert').removeClass('alert-error');
							$('#snsalert').removeClass('hide');
							$('#snsalert').addClass('alert-info');
							$('#snsalert').html('授权成功！2秒后将自动打开登录页面。');
							setTimeout("$('#modal_SNSaccess').modal('hide')",1500);
							setTimeout("$('#snsalert').addClass('alert-info hide')",1500);
						}else{
							$('#snsalert').removeClass('hide');
							$('#snsalert').removeClass('alert-info');
							$('#snsalert').addClass('alert-error');
							$('#snsalert').html("授权失败，请检查填写内容。");
							setTimeout("$('#snsalert').addClass('hide');",5000);
						}
					}
				);
			}
			
			
			
			
			
			function login(){
				$.post(
					"servlet/Login",
					{name:$("#loginaccount").val(),pd:$("#loginpassword").val()},
					function(data){
						if(data==0){
							$('#loginalert').removeClass('hide');
							$('#loginalert').addClass('alert-info');
							$('#loginalert').html('登录成功，2秒后自动退出本页。');
							$('#page_in').addClass('hide');
							$('#page_out').removeClass('hide');
							setTimeout("$('#loginalert').addClass('hide');",1500);
							setTimeout("$('#modal_login').modal('hide')",1500);
						}else{
							$('#loginalert').removeClass('hide');
							$('#loginalert').addClass('alert-error');
							$('#loginalert').html('登入失败，重新检查用户名和密码。');
							$('#page_in').removeClass('hide');
							$('#page_out').addClass('hide');
							setTimeout("$('#loginalert').addClass('hide');",5000);
						}
					}
				);
			}
			
			function logout(){
				$.ajax({
					url:"servlet/Logout",
					async:false,
					type:"get",
				}).done(function(flag){
					if(flag==0){
						$('#page_in').removeClass('hide');
						$('#page_out').addClass('hide');
					}else{
						$('#page_in').addClass('hide');
						$('#page_out').removeClass('hide');
					}
				});
				
			}
			
			
			
			
			
			
			
			$(document).ready(function() {
			
			
				$('a[data-toggle="tab"]').on('shown', function (e) {
				 	$("#linkedinatt").css({display:"none"});
					$("#linkedincancelatt").css({display:"none"});
				});
				
				$("table").mouseout(function(){
					//$("#linkedinatt").css({display:"none"});
					//$("#linkedincancelatt").css({display:"none"});
				});
				
				$("tbody").mouseout(function(e){
					 if (!e) var e = window.event;
					 e.cancelBubble = true;
					 if (e.stopPropagation) e.stopPropagation();
				});
				
				$("#linkedinatt").hover(function(){
					$("#linkedinatt").css({
						display:"block"
					});
				},function(){
					$("#linkedinatt").css({
						display:"none"
					});
				});
				
				$("#linkedincancelatt").hover(function(){
					$("#linkedincancelatt").css({
						display:"block"
					});
				},function(){
					$("#linkedincancelatt").css({
						display:"none"
					});
				});
				
				$("form").submit(function(e){
				    e.preventDefault();
				});
				$('#page_content_item').popover( {
					selector : "div[rel='popover']",
					placement:"bottom"
				});
				
				$('#register').on('click', function(evt) {
					$('#modal_register').modal( {
						backdrop : true,
						keyboard : true
					});
				});
				
				$('#login').on('click', function(evt) {
					$('#modal_login').modal( {
						backdrop : true,
						keyboard : true
					});
				});
				
				
				$('#page_content_item div a').on('click', function(evt) {
					
					getDetailInfo($(this).attr('data-type'),$(this).attr('data-currentpage'),new Date());
					
				});

			});
		</script>
		
	</head>
	

	<body>
		<header id="page_header" >
			<nav id="page_in" style="float: right;">
				<li>
					<a  id="register"  href="#">注册</a>
				</li>
				|
				<li>
					<a id="login" href="#">登录</a>
				</li>
			</nav>
			
			<nav id="page_out" style="float: right;" class="hide">
				<li>
					<a  id="register" onclick="logout()"  href="#">登出</a>
				</li>	
			</nav>
			
			<a href="">
				<img src="img/glyphicons-halflings.png" alt="好友备份" width="200" height="100" />
			</a>
		</header>
		
		
		
		
		<section id="page_content"  style="background:rgb(240, 240, 240)" class="row">
			<div class="nav">
				<div id="page_nav" class="subnav float_center" >
				    <ul class="nav-pills" style="float: right;line-height: 20px;">
					    <div class="input-prepend">
			              	<span class="add-on"><i class="icon-envelope"></i></span>
			              	<input class="span2" id="inputIcon" type="text" placeholder="abc@efg.com" />
			            </div>
				    </ul>
				    <ul class="nav nav-pills" >
				      <li><a href="#">首页</a></li>
				      <li><a href="#">关于我们</a></li>
				      <li><a href="#">客户</a></li>
				    </ul>
			 	 </div>
			
			
				<div id="page_content_item" class="float_center" align="center">
					<div data-content="他来自山东，是一名残疾人，因思想独立、指谪政弊而被政府迫害。地方政府和当权政要派专人看管他，调拨专款作为对他的看管费用，他每天都活在别人的监视和囚禁中，其遭遇只能用令人发指来形容。历尽万难，他终于成功逃离了当地，在外国使节的帮助和护送下去到了国外。他，就是孙膑。"
					 rel="popover" href="#" data-original-title="Linkedin">
						<a href="#" data-type="linkedin" data-currentpage="1"><img src="img/index/linkedin.png" alt="Linkedin" /></a>
					</div>
					<div data-content="他来自山东，是一名残疾人，因思想独立、指谪政弊而被政府迫害。地方政府和当权政要派专人看管他，调拨专款作为对他的看管费用，他每天都活在别人的监视和囚禁中，其遭遇只能用令人发指来形容。历尽万难，他终于成功逃离了当地，在外国使节的帮助和护送下去到了国外。他，就是孙膑。"
						rel="popover" href="#" data-original-title="他是谁">
						<a href="#" data-type="douban" data-currentpage="1"><img src="img/index/douban.png" alt="DouBan" /></a>
					</div>
					<div data-content="他来自山东，是一名残疾人，因思想独立、指谪政弊而被政府迫害。地方政府和当权政要派专人看管他，调拨专款作为对他的看管费用，他每天都活在别人的监视和囚禁中，其遭遇只能用令人发指来形容。历尽万难，他终于成功逃离了当地，在外国使节的帮助和护送下去到了国外。他，就是孙膑。"
						rel="popover" href="#" data-original-title="RenRen">
						<a href="#" data-type="renren" data-currentpage="1"><img src="img/index/renren.png" alt="RenRen" /></a>
					</div>
					<div data-content="他来自山东，是一名残疾人，因思想独立、指谪政弊而被政府迫害。地方政府和当权政要派专人看管他，调拨专款作为对他的看管费用，他每天都活在别人的监视和囚禁中，其遭遇只能用令人发指来形容。历尽万难，他终于成功逃离了当地，在外国使节的帮助和护送下去到了国外。他，就是孙膑。"
						rel="popover" href="#" data-original-title="Weibo">
						<a href="#" data-type="weibo" data-currentpage="1"><img src="img/index/weibo.png" alt="Weibo" /></a>	
					</div>
					<div data-content="他来自山东，是一名残疾人，因思想独立、指谪政弊而被政府迫害。地方政府和当权政要派专人看管他，调拨专款作为对他的看管费用，他每天都活在别人的监视和囚禁中，其遭遇只能用令人发指来形容。历尽万难，他终于成功逃离了当地，在外国使节的帮助和护送下去到了国外。他，就是孙膑。"
						rel="popover" href="#" data-original-title="QQ">
						<a href="#" data-type="qq" data-currentpage="1"><img src="img/index/qq.png" alt="QQ" /></a>
					</div>
					
				</div>
			</div>	
		</section>
		
		<div class="baseback"></div>
		
		<section id="page_info" style="margin-top:10px;" class="row float_center tabbable tabs-left">
			<ul data-spy="affix" data-offset-top="352"  class="nav nav-tabs" style="margin-left:-100px;margin-top:104px;top:-102px;margin-right:0px;">
				<li id="linkedinli" class="hide">
					<a href="#linkedininfo" data-toggle="tab">Linkedin</a>
				</li>
				<li id="renrenli" class="hide">
					<a href="#renreninfo" data-toggle="tab">人人</a>
				</li>
				<li id="weiboli" class="hide">
					<a href="#weiboinfo" data-toggle="tab">微博</a>
				</li>
				<li id="qqli" class="hide">
					<a href="#qqinfo" data-toggle="tab">QQ</a>
				</li>
				<li id="doubanli" class="hide">
					<a href="#doubaninfo" data-toggle="tab">豆瓣</a>
				</li>
				
			</ul>

			<div class="tab-content content-width" style="margin-top:-5px;">
				<div class="tab-pane" id="linkedininfo">
					
					<ul class="nav nav-tabs" style="margin-top:10px;border-bottom:0px;">
						<li class="dropdown" style="margin-left:15px;float:right;">
							<a href="#" data-toggle="dropdown" class="dropdown-toggle">下载<b class="caret"></b></a>
							<ul class="dropdown-menu" style="right:0px;left:auto;">
								<li><a href="javascript:download('allasxls','linkedin')">全部结果为XLS</a></li>
								<li><a href="#">全部结果为PDF</a></li>
								<li class="divider"></li>
								<li><a href="javascript:download('searchasxls','linkedin')">搜索结果为XLS</a></li>
								<li><a href="#">搜索结果为PDF</a></li>
								<li class="divider"></li>
								<li><a href="javascript:download('attasxls','linkedin')">关注结果为XLS</a></li>
								<li><a href="#">关注结果为PDF</a></li>
							</ul>
						</li>
							
						<li style="margin-left:15px;float:right;">
							<div class="input-prepend">
								<form onsubmit="infosearch(this)" class="form-inline">
									<span class="add-on"><i class="icon-search"></i></span>
									<input name="infosearchtxt" class="span3" id="inputIcon" type="text" placeholder="key word" />
									<input name="infosearchbtn" type="submit" data-type="linkedin" class="btn btn-primary" style="height:30px" value="搜索" />
								</form>
							</div>
						</li>
					</ul>
					
					
					
					<section id="page_info" style="margin-top:-20px;" class="row float_center tabbable tabs-top">
						<ul class="nav nav-tabs" id="linkedintab" style="margin-bottom:21px;">
							<li>
								<a href="#linkeddeatailininfoall" data-toggle="tab">全部</a>
							</li>
							<li >
								<a href="#linkedininfosearch" data-toggle="tab">搜索</a>
							</li>
							<li >
								<a href="#linkedininfoletter" data-toggle="tab">关注<strong id="attnum" style="position:relative;left:5px;color:red;">0</strong></a>
							</li>
							
							
							
							
							
						</ul>
											
						<div class="tab-content" id="linkedintabcontent">
							<div class="tab-pane" style="margin-top: 15px;" id="linkeddeatailininfoall">
								<div  class="divself">
									<table id="linkedinalltable"  class="table table-bordered" style="border-radius:0px 0px 0px 0px;">
										<thead>
											<tr>
												<th>
													#
												</th>
												<th>
													name
												</th>
												<th>
													headline
												</th>
												<th>
													industry
												</th>
												<th>
													position
												</th>
												<th>
													education
												</th>
												<th>
													location
												</th>
											</tr>
										</thead>
										<tbody id="linkedintbody">
											
										</tbody>
									</table>
									
									
									<div class="pagination pagination-centered">
						              	<ul id="linkedinpagination">
							                
							            </ul>
							         </div>
								</div>	
							</div>
							<div class="tab-pane" style="margin-top: 15px;" id="linkedininfosearch">
								<div>
									<table id="linkedinsearchtable" class='table table-bordered table-condensed'>
									</table>	
								</div>
							</div>
							<div class="tab-pane" style="margin-top: 15px;" id="linkedininfoletter">
								<table class="table table-bordered" style="border-radius:0px 0px 0px 0px;">
										<thead>
											<tr>
												<th>
													#
												</th>
												<th>
													name
												</th>
												<th>
													headline
												</th>
												<th>
													industry
												</th>
												<th>
													position
												</th>
												<th>
													education
												</th>
												<th>
													location
												</th>
											</tr>
										</thead>
										<tbody id="linkedininfolettertable">
											
										</tbody>
									</table>
							</div>
						</div>
						<div style="width: auto;height: auto;position: fixed;background-color:rgb(235,235,235);color:red;font-size:25px;display: none" id="linkedinaddatt"></div>
						<div  class="" style="width:50px;height:50px;">
							<a data-iid="" id="linkedinatt" title="添加关注" class="att hide" onclick="att(this)">添加关注</a>
							<a data-iid="" id="linkedincancelatt" title="取消关注" class="cancelatt hide" onclick="cancelatt(this)">取消关注</a>
						</div>
					</section>
				</div>
				
				
				
				
				
				
				
				
				
				
				
				<div class="tab-pane hide" id="renreninfo">
					介绍content
				</div>
				<div class="tab-pane hide" id="weiboinfo">
					消息content
				</div>
				<div class="tab-pane hide" id="qqinfo">
					设置content
				</div>
				<div class="tab-pane hide" id="doubaninfo">
					设置content
				</div>
			</div>
		</section>
		
		
		
		<section id="page_howtodo" class="float_center row" style="height: 170px;background-color:red;margin-top:20px;">
			11
			
			
		</section>
		
		
		
		
		<!-- 登录  -->
		
			<div id="modal_login" class="modal hide fade">
				<div class="modal-header">
					<a class="close" data-dismiss="modal">&times;</a>
					<h3>
						用户登录
					</h3>
				</div>
				<form onsubmit="login()">
					<div class="modal-body">
			            <fieldset id="regform">
			            	<label for="loginaccount">填写您的邮箱地址:</label>
			            	<input type="text" id="loginaccount" value="" class="span3" placeholder="abc123@def456.com" pattern="\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" required autofocus/>
			            	<span class="help-inline">填写正确的邮箱地址</span>
			                <label for="loginpassword">密码:</label>
			            	<input type="password" id="loginpassword" value="" class="span3" placeholder="abcd1234" pattern="^[a-zA-Z0-9]{8,16}$" required />
			                <span class="help-inline">填写8-16位密码，只可以为字母和数字</span>
			            </fieldset>
			      	
					
					</div>
					<div class="modal-footer">
						<a onclick="toreg()" style="float: left">还没有注册</a>
						<input type="reset" value="重置" class="btn" />
						<input type="submit" value="提交" class="btn btn-primary" />
						<div style="margin-top:5px;" id="loginalert" class="alert hide"></div>
					</div>
				</form>
			</div>
		
		
			<!-- 注册  -->
		

			<div id="modal_register" class="modal hide fade">
				<div class="modal-header">
					<a class="close" data-dismiss="modal">&times;</a>
					<h3>
						用户注册
					</h3>
				</div>
				<form onsubmit="reg()">
					<div class="modal-body">
			            <fieldset id="regform">
			            	<label for="account">填写您的邮箱地址:</label>
			            	<input type="text" id="account" value="" class="span3" placeholder="abc123@def456.com" pattern="\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*" required autofocus/>
			            	<span class="help-inline">填写正确的邮箱地址</span>
			                <label for="password">密码:</label>
			            	<input type="password" id="password" value="" class="span3" placeholder="abcd1234" pattern="^[a-zA-Z0-9]{8,16}$" required />
			                <span class="help-inline">填写8-16位密码，只可以为字母和数字</span>
			                <label for="password2">重复密码:</label>
			            	<input type="password" id="password2" value="" class="span3" placeholder="abcd1234" pattern="^[a-zA-Z0-9]{8,16}$" required />
			                <label for="password">授权SNS账户：</label>
			                
			                <div id="linkedinform" class="form-inline rowline">
			                	<img src="img/index/linkedinsmall.png" alt="LinkedIn" />
			                	<label for="linkedinvp">输入授权码(<a onclick="getLinkedinCode()">点击获取</a>)</label>
			                	<input type="text" id="linkedinvp" class="input-small" />
			                	<input type="button" value="授权" onclick="getLinkedinAccess('linkedinvp','linkedinformsuccess','linkedinname','linkedinform')" class="btn"/>
			                </div>
			                <div id="linkedinformsuccess" class="alert alert-success form-inline rowline hide">
								您好！<strong id="linkedinname"></strong>，您已为LinkedIn授权！
							</div>
			                
			                <div class="form-inline rowline">
			                 	<img src="img/index/doubansmall.png" alt="DouBan" />
			                </div>
			                
			                <div class="form-inline rowline">
			                 	<img src="img/index/renrensmall.png" alt="RenRen" />
			                 	<label for="renrenvp">输入授权码(<a onclick="getRenRenCode()">点击获取</a>)</label>
			                	<input type="text" name="renrenvp" class="input-small" />
			                	<input type="button" value="授权" class="btn"/>
			                 	
			                </div>
			                
			                <div class="form-inline rowline">
			                 	<img src="img/index/weibosmall.png" alt="WeiBo" />
			                </div>
			                
			                <div class="form-inline rowline">
			                 	<img src="img/index/qqsmall.png" alt="QQ" />
			                </div>

			            </fieldset>
			      	
					
					</div>
					<div class="modal-footer">
						<a style="float: left;margin-right:10px;">查看使用协议</a>
						<a onclick="tologin()" style="float: left">立即登录</a>
						<input type="reset" value="重置" class="btn" />
						<input type="submit" value="提交" class="btn btn-primary" />
						<div style="margin-top:5px;" id="regalert" class="alert hide"></div>
					</div>
				</form>
			</div>


		
		
		
		<!-- 提取码设置  -->
		
			<div id="modal_access" class="modal hide fade">
				<div class="modal-header">
					<a class="close" data-dismiss="modal">&times;</a>
					<h3>
						提取码设置
					</h3>
				</div>
				<form onsubmit="setAccess(this)">
					<div class="modal-body">
			            <fieldset id="accessform">
			                <label for="loginpassword">登录密码:</label>
			            	<input type="password" name="acceesspd" value="" class="span3" placeholder="abcd1234" pattern="^[a-zA-Z0-9]{8,16}$" required />
			                <span class="help-inline">填写8-16位密码，只可以为字母和数字</span>
			                <label for="loginpassword">提取码</label>
			            	<input type="password" name="acceessac" value="" class="span3" placeholder="abcd1234" pattern="^[a-zA-Z0-9]{4,10}$" required />
			                <span class="help-inline">填写4-10位密码，只可以为字母和数字</span>
			                <label for="loginpassword">重复提取码</label>
			            	<input type="password" name="acceessac2" value="" class="span3" placeholder="abcd1234" pattern="^[a-zA-Z0-9]{4,10}$" required />
			                <span class="help-inline">重复提取码</span>
			                <div style="margin-top:5px;"  class="alert alert-info">为保护用户数据，一旦设置不可更改，请牢记密码！</div>
			            </fieldset>

					</div>
					<div class="modal-footer">
						<a style="float: left">查看提取码如何使用</a>
						<input type="reset" value="重置" class="btn" />
						<input type="submit" onclick="" value="提交" class="btn btn-primary" />
						<div style="margin-top:5px;" id="accessalert" class="alert hide"></div>
					</div>
				</form>
			</div>
		
		
		
		<!-- 授权 -->
		
			<div id="modal_SNSaccess" class="modal hide fade">
				<div class="modal-header">
					<a class="close" data-dismiss="modal">&times;</a>
					<h3>
						SNS授权
					</h3>
				</div>
				<form onsubmit="setSNSAccess()">
					<div class="modal-body">
			            <div id="linkedinform2" class="form-inline rowline">
			                	<img src="img/index/linkedinsmall.png" alt="LinkedIn" />
			                	<label for="linkedinvp">输入授权码(<a onclick="getLinkedinCode()">点击获取</a>)</label>
			                	<input type="text" id="linkedinvp2" class="input-small" />
			                	<input type="button" value="授权" onclick="getLinkedinAccess('linkedinvp2','linkedinformsuccess2','linkedinname2','linkedinform2')" class="btn"/>
			                </div>
			                <div id="linkedinformsuccess2" class="alert alert-success form-inline rowline hide">
								您好！<strong id="linkedinname2"></strong>，您已为LinkedIn授权！
							</div>
			                
			                <div class="form-inline rowline">
			                 	<img src="img/index/doubansmall.png" alt="DouBan" />
			                </div>
			                
			                <div class="form-inline rowline">
			                 	<img src="img/index/renrensmall.png" alt="RenRen" />
			                 	<label for="renrenvp">输入授权码(<a onclick="getRenRenCode()">点击获取</a>)</label>
			                	<input type="text" name="renrenvp" class="input-small" />
			                	<input type="button" value="授权" class="btn"/>
			                 	
			                </div>
			                
			                <div class="form-inline rowline">
			                 	<img src="img/index/weibosmall.png" alt="WeiBo" />
			                </div>
			                
			                <div class="form-inline rowline">
			                 	<img src="img/index/qqsmall.png" alt="QQ" />
			                </div>

					</div>
					<div class="modal-footer">
						<a style="float: left">如何取消授权</a>
						<input type="reset" value="重置" class="btn" />
						<input type="submit" value="提交" class="btn btn-primary" />
						<div style="margin-top:5px;" id="snsalert" class="alert hide"></div>
					</div>
				</form>
			</div>
		
		
		
		
		
		
		
		
		
		<footer id="page_footer" >
			<div class="baseback"></div>
			<div class="subnav">
			    <ul class="nav nav-pills">
			      <li><a href="index.html" style="color: #9B9BCE">首页</a></li>
			      <li><a href="about-salevision.html" style="color: #9B9BCE">关于我们</a></li>
			      <li><a href="clients.html" style="color: #9B9BCE">联系我们</a></li>
			      <li><a href="legal.html" style="color: #9B9BCE">法律信息</a></li>
			      <li><a href="privacy-policy.html" style="color: #9B9BCE">隐私条约</a></li>
			      <li><a href="careers.html" style="color: #9B9BCE">人才招聘 </a></li>
			    </ul>
			  </div>
			<div class="baseback" style="text-align: center">
				<p>
						&copy; Copyright2012  by Nat.Yan @ USST 
				</p>	
			</div>
		</footer>
		
		
	</body>
	
</html>
