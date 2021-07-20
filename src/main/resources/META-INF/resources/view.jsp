<%@ include file="/init.jsp" %>

<h1>prefs ${dmPrefs}</h1>

<liferay-portlet:runtime portletName="com_liferay_document_library_web_portlet_DLPortlet" instanceId="${dmInstanceId}" defaultPreferences="${dmPrefs}" />