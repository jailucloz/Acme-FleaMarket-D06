

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${finalMode == false && command == 'create'}">
		<acme:form-textbox code="supplier.item.form.label.ticker" path="ticker" placeholder="ABC-123-123456"/>
		<acme:form-textbox code="supplier.item.form.label.title" path="title"/>
		<acme:form-textbox code="supplier.item.form.label.itemCategory" path="itemCategory"/>
		<acme:form-money code="supplier.item.form.label.price" path="price"/>
		<acme:form-url code="supplier.item.form.label.additionalInformation" path="additionalInformation"/>
		<acme:form-textarea code="supplier.item.form.label.descriptionText" path="descriptionText"/>
		<acme:form-url code="supplier.item.form.label.photo" path="photo"/>
		<acme:form-hidden path="finalMode"/>
	</jstl:if>
	
	<jstl:if test="${finalMode == false && command != 'create'}">
		<acme:form-textbox code="supplier.item.form.label.ticker" path="ticker" placeholder="ABC-123-123456"/>
		<acme:form-textbox code="supplier.item.form.label.title" path="title"/>
		<acme:form-textbox code="supplier.item.form.label.itemCategory" path="itemCategory"/>
		<acme:form-moment code="supplier.item.form.label.creationMoment" path="creationMoment"/>
		<acme:form-money code="supplier.item.form.label.price" path="price"/>
		<acme:form-url code="supplier.item.form.label.additionalInformation" path="additionalInformation"/>
		<acme:form-textarea code="supplier.item.form.label.descriptionText" path="descriptionText"/>
		<acme:form-checkbox code="supplier.item.form.label.finalMode" path="finalMode"/>
		<acme:form-url code="supplier.item.form.label.photo" path="photo" />
	</jstl:if>

	<jstl:if test="${finalMode == true  }">
		<acme:form-textbox code="supplier.item.form.label.ticker" path="ticker" placeholder="ABC-123-123456" readonly="true"/>
		<acme:form-textbox code="supplier.item.form.label.title" path="title" readonly="true"/>
		<acme:form-textbox code="supplier.item.form.label.itemCategory" path="itemCategory" readonly="true"/>
		<acme:form-moment code="supplier.item.form.label.creationMoment" path="creationMoment" readonly="true"/>
		<acme:form-money code="supplier.item.form.label.price" path="price" readonly="true"/>
		<acme:form-url code="supplier.item.form.label.additionalInformation" path="additionalInformation" readonly="true"/>
		<acme:form-textarea code="supplier.item.form.label.descriptionText" path="descriptionText" readonly="true"/>
		<acme:form-checkbox code="supplier.item.form.label.finalMode" path="finalMode" readonly="true"/>
		<acme:form-url code="supplier.item.form.label.photo" path="photo" readonly="true"/>
	</jstl:if>
	

		<acme:form-submit test="${command != 'create'}" code="supplier.item.form.label.sheet" action="/supplier/sheet/list?id=${id}" method="get"/>
	
	
	<acme:form-submit test="${command == 'show' && finalMode == false}" code="supplier.item.form.button.update" action="/supplier/item/update"/>
	<acme:form-submit test="${command == 'show' }" code="supplier.item.form.button.delete" action="/supplier/item/delete"/>
	<acme:form-submit test="${command == 'create' }" code="supplier.item.form.button.create" action="/supplier/item/create"/>
	<acme:form-submit test="${command == 'update' }" code="supplier.item.form.button.update" action="/supplier/item/update"/>
	<acme:form-submit test="${command == 'delete' }" code="supplier.item.form.button.delete" action="/supplier/item/delete"/>
	
	<acme:form-return code="supplier.item.form.button.return"/>
</acme:form>

