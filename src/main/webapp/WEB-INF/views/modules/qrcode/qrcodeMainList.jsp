<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物品数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/qrcode/qrcodeMain/">物品数据列表</a></li>
		<shiro:hasPermission name="qrcode:qrcodeMain:edit"><li><a href="${ctx}/qrcode/qrcodeMain/form">物品数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qrcodeMain" action="${ctx}/qrcode/qrcodeMain/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主分类：</label><sys:treeselect id="subjectmain" name="subjectmain.id" value="${qrcodeMain.subjectmain.id}" labelName="subjectmain.name" labelValue="${qrcodeMain.subjectmain.name}" 
				title="物品分类" url="/qrcode/qrcodeSubject/treeData?type=1" cssClass="input-small" allowClear="true"/></li>
			<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${qrcodeMain.office.id}" labelName="office.name" labelValue="${qrcodeMain.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true"/></li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>子分类：</label><sys:treeselect id="subjectbranch" name="subjectbranch.id" value="${qrcodeMain.subjectbranch.id}" labelName="subjectbranch.name" labelValue="${qrcodeMain.subjectbranch.name}" 
				title="物品分类" url="/qrcode/qrcodeSubject/treeData?type=2" cssClass="input-small" allowClear="true"/></li>
			<li><label>归属班组：</label><sys:treeselect id="team" name="team.id" value="${qrcodeMain.team.id}" labelName="team.name" labelValue="${qrcodeMain.team.name}" 
				title="班组" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true"/></li>	
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>物品名称</th>
				<th>主分类</th>
				<th>子分类</th>
				<th>责任人</th>
				<th>所在位置</th>
				<th>负责部门</th>
				<th>负责班组</th>
				<th>编码</th>
				<shiro:hasPermission name="qrcode:qrcodeMain:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qrcodeMain">
			<tr>
				<td>
					<!-- <a href="${ctx}/qrcode/qrcodeMain/form?id=${qrcodeMain.id}"> -->
					${qrcodeMain.name}
				</td>
				<td>
					${qrcodeMain.subjectmain.name}
				</td>
				<td>
					${qrcodeMain.subjectbranch.name}
				</td>
				<td>
					${qrcodeMain.user.name}
				</td>
				<td>
					${qrcodeMain.location}
				</td>
				<td>
					${qrcodeMain.office.name}
				</td>
				<td>
					${qrcodeMain.team.name}
				</td>
				<td>
					${qrcodeMain.subjectCode}
				</td>
				<shiro:hasPermission name="qrcode:qrcodeMain:edit"><td>
    				<a href="${ctx}/qrcode/qrcodeMain/form?id=${qrcodeMain.id}">修改</a>
					<a href="${ctx}/qrcode/qrcodeMain/delete?id=${qrcodeMain.id}" onclick="return confirmx('确认要删除该物品数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>