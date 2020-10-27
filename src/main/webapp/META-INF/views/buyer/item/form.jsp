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
	<acme:form-textbox code="buyer.item.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="buyer.item.form.label.title" path="title"/>
	<acme:form-moment code="buyer.item.form.label.creationMoment" path="creationMoment"/>
	<acme:form-money code="buyer.item.form.label.price" path="price"/>
	<acme:form-url code="buyer.item.form.label.additionalInformation" path="additionalInformation"/>
	<acme:form-textarea code="buyer.item.form.label.descriptionText" path="descriptionText"/>
	<acme:form-url code="buyer.item.form.label.photo" path="photo"/>

	
	<acme:form-hidden path="id"/>
	<acme:form-submit code="buyer.item.form.label.request" action="/buyer/request-item/create?id=${id}" method="get"/>
	
	<acme:form-return code="buyer.item.form.button.return"/>
</acme:form>

