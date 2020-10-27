<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	
	<acme:list-column code="supplier.request.list.label.ticker" path="ticker" width="30%"/>
	<acme:list-column code="supplier.request.list.label.item" path="item" width="30%"/>
	<acme:list-column code="supplier.request.list.label.creationMoment" path="creationMoment" width="30%"/>
	<acme:list-column code="supplier.request.list.label.new" path="new" width="10%"/>
</acme:list>