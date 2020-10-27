<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.buyer.form.label.email" path="email" />
	<acme:form-textbox code="authenticated.buyer.form.label.phoneNumber" path="phoneNumber" />
	<acme:form-textbox code="authenticated.buyer.form.label.deliveryAddres" path="deliveryAddres" />
	<acme:form-textbox code="authenticated.buyer.form.label.creditCard" path="creditCard" />
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.buyer.form.button.create" action="/authenticated/buyer/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.buyer.form.button.update" action="/authenticated/buyer/update"/>
	<acme:form-return code="authenticated.buyer.form.button.return"/>
</acme:form>
