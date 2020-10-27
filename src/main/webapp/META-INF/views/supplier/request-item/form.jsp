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

<acme:form readonly="false">
	
	<acme:form-textbox code="supplier.request.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-textbox code="supplier.request.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="supplier.request.form.label.quantity" path="quantity" readonly="true"/>
	<acme:form-textbox code="supplier.request.form.label.notes" path="notes" readonly="true"/>
	<acme:form-textbox code="supplier.request.form.label.item.ticker" path="item.ticker" readonly="true"/>
	<acme:form-textbox code="supplier.request.form.label.item.title" path="item.title" readonly="true"/>
	
	<jstl:if test="${!isPending && command == 'show'}">
			<jstl:set var= "readOnly" value = "true"/>
	</jstl:if>
	
	<acme:form-textarea code="supplier.request.form.label.rejectJustification" path="rejectJustification" readonly = "${readOnly}"/>

	
	<acme:form-submit test="${(command == 'show' && isPending) || command == 'accept' || command == 'reject'}"		
		code="supplier.request.form.button.accept"
		action="/supplier/request-item/accept"/>
		
	<acme:form-submit test="${(command == 'show' && isPending) || command == 'accept' || command == 'reject'}"		
		code="supplier.request.form.button.reject"
		action="/supplier/request-item/reject"/>
	
	<acme:form-return code="supplier.request.form.button.return"/>
</acme:form>
