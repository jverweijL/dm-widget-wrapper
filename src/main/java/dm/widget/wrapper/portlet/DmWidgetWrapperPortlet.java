package dm.widget.wrapper.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import dm.widget.wrapper.constants.DmWidgetWrapperPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import javax.portlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @author jverweij
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=DmWidgetWrapper",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DmWidgetWrapperPortletKeys.DMWIDGETWRAPPER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DmWidgetWrapperPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		//TODO find folder with name/description xyz based on org. or userid.
		//TODO define rootfolder to start searching
		//TODO define to search for org or userid
		//TODO get/set preferences for embedded DMPortlet (set instanceID based on parent instanceID)


		PortletPreferences preferences = renderRequest.getPreferences();
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute (com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);
		Layout layout = themeDisplay.getLayout();

		List<com.liferay.portal.kernel.model.Portlet> portlets = layout.getEmbeddedPortlets();
		for (com.liferay.portal.kernel.model.Portlet p:portlets){
			System.out.printf("portletID: %s\n",p.getPortletId());
		}


		long plid = layout.getPlid() ;
		String portletId = (String) renderRequest.getAttribute(WebKeys.PORTLET_ID);


		System.out.printf("ns: %s / plid: %s / portletID: %s / pref: %s ",this.getDefaultNamespace(),plid,portletId,preferences.getValue("rootFolderId" ,"nothing found"));

		String prefs = "<portlet-preferences><preference><name>rootFolderId</name><value>225781</value></preference></portlet-preferences>";
		renderRequest.setAttribute("dmPrefs",prefs);
		renderRequest.setAttribute("dmInstanceId",portletId + "_DM");
		// com_liferay_document_library_web_portlet_DLPortlet_INSTANCE_dm_widget_wrapper_DmWidgetWrapperPortlet_INSTANCE_rsdd_DM


		//PortletPreferences prefs = (PortletPreferences) _PortletPreferencesLocalService.getPortletPreferences().get(0);
		//preferences.setValue("rootFolderId","225781");

		super.doView(renderRequest, renderResponse);
	}

	@Reference
	PortletPreferencesLocalService _PortletPreferencesLocalService;
}