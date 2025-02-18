<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.materialSheets.form.label.title" path="title" />
	<acme:form-textbox code="administrator.materialSheets.form.label.providerName" path="providerName" />
	<acme:form-textarea code="administrator.materialSheets.form.label.description" path="description" />
	<acme:form-textbox code="administrator.materialSheets.form.label.homePage" path="homePage" />
	<acme:form-textbox code="administrator.materialSheets.form.label.stars" path="stars" />

	<acme:form-submit test="${command == 'show'}" code="administrator.materialSheets.form.button.update"
		action="/administrator/material-sheet/update" />
	<acme:form-submit test="${command == 'show'}" code="administrator.materialSheets.form.button.delete"
		action="/administrator/material-sheet/delete" />
	<acme:form-submit test="${command == 'create'}" code="administrator.materialSheets.form.button.create"
		action="/administrator/material-sheet/create" />
	<acme:form-submit test="${command == 'update'}" code="administrator.materialSheets.form.button.update"
		action="/administrator/material-sheet/update" />
	<acme:form-submit test="${command == 'delete'}" code="administrator.materialSheets.form.button.delete"
		action="/administrator/material-sheet/delete" />

	<acme:form-return code="administrator.materialSheets.form.button.return" />
</acme:form>
