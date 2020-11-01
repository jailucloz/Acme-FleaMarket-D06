<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.jaime" action="http://www.google.com/"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.news.list" action="/anonymous/news/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.material-sheet.list" action="/anonymous/material-sheet/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.tool-sheet.list" action="/anonymous/tool-sheet/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.advertisement.list" action="/anonymous/advertisement/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.anonymous.bulletin" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.bulletin.lucas" action="/anonymous/lucas-bulletin/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.bulletin.lucas.create" action="/anonymous/lucas-bulletin/create"/>
		
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			
			<acme:menu-suboption code="master.menu.authenticated.news.list" action="/authenticated/news/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.material-sheet.list" action="/authenticated/material-sheet/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.tool-sheet.list" action="/authenticated/tool-sheet/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.advertisement.list" action="/authenticated/advertisement/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.suggestion.list" action="/authenticated/suggestion/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.figment.list" action="/authenticated/figment/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.item.list" action="/authenticated/item/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.news.create" action="/administrator/news/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.news.list" action="/administrator/news/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.material-sheet.create" action="/administrator/material-sheet/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.tool-sheet.create" action="/administrator/tool-sheet/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.advertisement.create" action="/administrator/advertisement/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.suggestion.create" action="/administrator/suggestion/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.figment.create" action="/administrator/figment/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.customisation.display" action="/administrator/customisation/display"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		
		<acme:menu-option code="master.menu.supplier" access="hasRole('Supplier')">
			<acme:menu-suboption code="master.menu.supplier.item.list-mine" action="/supplier/item/list_mine"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.supplier.item.create" action="/supplier/item/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.supplier.request.list-mine" action="/supplier/request-item/list_mine"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.supplier.request.list-order" action="/supplier/request-item/list-order"/>
		</acme:menu-option>
		

		<acme:menu-option code="master.menu.buyer" access="hasRole('Buyer')">
			<acme:menu-suboption code="master.menu.buyer.request.list-mine" action="/buyer/request-item/list_mine"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.buyer.item.list-mine" action="/buyer/item/list_mine"/>
			
		
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">

			<acme:menu-suboption code="master.menu.user-account.become-supplier" action="/authenticated/supplier/create" access="!hasRole('Supplier')"/>
			<acme:menu-suboption code="master.menu.user-account.supplier" action="/authenticated/supplier/update" access="hasRole('Supplier')"/>
			<acme:menu-suboption code="master.menu.user-account.become-buyer" action="/authenticated/buyer/create" access="!hasRole('Buyer')"/>
			<acme:menu-suboption code="master.menu.user-account.buyer" action="/authenticated/buyer/update" access="hasRole('Buyer')"/>
				</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>


