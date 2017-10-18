package com.meession.market.common.view;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class LogoutView {
	private HttpSession session;
	@ManagedProperty(value = "#{pagesView}")
	private PagesView pagesView;

	@PostConstruct
	public void init() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public String logout() {
		session.invalidate();
		pagesView.setDynamicaPagesInclude("/pages/common/welcome.xhtml");
		return "/pages/common/login?faces-redirect=true";
	}

	public PagesView getPagesView() {
		return pagesView;
	}

	public void setPagesView(PagesView pagesView) {
		this.pagesView = pagesView;
	}

}
