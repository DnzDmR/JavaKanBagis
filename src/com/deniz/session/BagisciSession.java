package com.deniz.session;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BagisciSession {
	
	
	public static HttpSession getSession()
	{
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public static HttpServletRequest getRequest()
	{
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	 
	public static String getEposta()
	{
		HttpSession session = getSession();
		if (session != null && session.getAttribute("eposta")!=null){ 
			return session.getAttribute("eposta").toString();
		} else {
			return null;
		}
	}
	
	public static void sessionDestroy()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

}
