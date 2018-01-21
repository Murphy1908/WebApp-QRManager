<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物品数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/qrcode/qrcodeMain/">物品数据列表</a></li>
		<li class="active"><a href="${ctx}/qrcode/qrcodeMain/form?id=${qrcodeMain.id}">物品数据<shiro:hasPermission name="qrcode:qrcodeMain:edit">${not empty qrcodeMain.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="qrcode:qrcodeMain:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="qrcodeMain" action="${ctx}/qrcode/qrcodeMain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">归属用户：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${qrcodeMain.user.id}" labelName="user.name" labelValue="${qrcodeMain.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true" isAll="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${qrcodeMain.office.id}" labelName="office.name" labelValue="${qrcodeMain.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属班组：</label>
			<div class="controls">
				<sys:treeselect id="team" name="team.id" value="${qrcodeMain.team.id}" labelName="" labelValue="${qrcodeMain.team.id}"
					title="班组" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主分类：</label>
			<div class="controls">
			<sys:treeselect id="subjectmain" name="subjectmain.id" value="${qrcodeMain.subjectmain.id}" labelName="" labelValue="${qrcodeMain.subjectmain.id}"
					title="物品分类" url="/qrcode/qrcodeSubject/treeData?type=1" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子分类：</label>
			<div class="controls">
			<sys:treeselect id="subjectbranch" name="subjectbranch.id" value="${qrcodeMain.subjectbranch.id}" labelName="" labelValue="${qrcodeMain.subjectbranch.id}"
					title="物品分类" url="/qrcode/qrcodeSubject/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在地：</label>
			<div class="controls">
				<form:input path="location" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物品码：</label>
			<div class="controls">
				<form:input path="subjectCode" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<!--
		<div class="control-group">
			<label class="control-label">加入日期：</label>
			<div class="controls">
				<input name="inDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${qrcodeMain.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="qrcode:qrcodeMain:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>