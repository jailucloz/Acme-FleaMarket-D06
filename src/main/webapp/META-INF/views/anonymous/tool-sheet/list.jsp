
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.toolSheet.list.label.title" path="title" width="20%"/>
	<acme:list-column code="anonymous.toolSheet.list.label.providerName" path="providerName" width="40%"/>		
	<acme:list-column code="anonymous.toolSheet.list.label.stars" path="stars" width="40%"/>		
</acme:list>

