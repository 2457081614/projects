package com.meession.market.common.view;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.meession.market.parttimestaff.entity.ParttimeStaff;
import com.meession.market.parttimestaff.service.IParttimeStaffService;
import com.meession.market.staff.entity.Staff;
import com.meession.market.staff.service.IStaffService;

@ManagedBean
@ViewScoped
public class LoginView {
	@ManagedProperty(value = "#{staffService}")
	private IStaffService staffService;
	@ManagedProperty(value = "#{parttimeStaffService}")
	private IParttimeStaffService parttimeStaffService;

	private String tel;
	private String password;

	private HttpSession session;
	private Map<String, Object> applicationMap;

	@PostConstruct
	public void init() {
		applicationMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	public String login() throws Exception {
		if (tel == null || "".equals(tel.trim())) {
			showErrorMessage("用户名不能为空");
		} else if (password == null || "".equals(password)) {
			showErrorMessage("密码不能为空");
		} else {
			/** Modified here */
			Object o = session.getAttribute("loginedUser");
			if (o != null) {
				if (o instanceof Staff) {
					Staff s = (Staff) o;
					if (s.getIdentifier() == Staff.MANAGER) {
						return "/pages/manager/index?faces-redirect=true";
					} else if (s.getIdentifier() == Staff.ORDINARY_STAFF) {
						return "/pages/staff/index?faces-redirect=true";
					}
				} else if (o instanceof ParttimeStaff) {
					return "/pages/parttimeStaff/index?faces-redirect=true";
				}
			}
			/** Modified here */
			else {
				Object obj = staffService.login(tel, password);
				if (obj == null) {
					obj = parttimeStaffService.login(tel, password);
					if (obj != null && obj instanceof ParttimeStaff) {
						session.setAttribute("loginedUser", obj);
						applicationMap.put(tel + password, session);
						return "/pages/parttimeStaff/index?faces-redirect=true";
					} else {// obj == null
						showErrorMessage("用户名或密码错误");
					}
				} else {// obj instanceof Staff
					Staff staff = (Staff) obj;
					session.setAttribute("loginedUser", staff);
					if (staff.getIdentifier() == Staff.MANAGER) {
						return "/pages/manager/index?faces-redirect=true";
					} else if (staff.getIdentifier() == Staff.ORDINARY_STAFF) {
						return "/pages/staff/index?faces-redirect=true";
					}
				}
			}
		}
		return "login";// 相对路径
	}

	/**
	 * 检查登录是否合法
	 * 
	 * @return
	 */
	public String checkIn() {
		Object obj = session.getAttribute("loginedUser");// 从session中得到登录的对象
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = request.getRequestURL().toString();// 得到请求的URL
		String suburl = url.substring(0, url.lastIndexOf("/"));// 得到index.xhtml之前的字符串
		String suburl2 = suburl.substring(suburl.lastIndexOf("/") + 1);// 得到输入的类型
		if (obj == null) {
			showErrorMessage("请先登录");
			return "/pages/common/login?facs-request=true";
		} else if (obj instanceof Staff) {
			Staff staff = (Staff) obj;
			if (staff.getIdentifier() == Staff.MANAGER && !StringUtils.isBlank(suburl2) && !"manager".equals(suburl2)) {
				showErrorMessage("请先登录");
				return "/pages/common/login?facs-request=true";
			} else if (staff.getIdentifier() == Staff.ORDINARY_STAFF && !StringUtils.isBlank(suburl2)
					&& !"staff".equals(suburl2)) {
				showErrorMessage("请先登录");
				return "/pages/common/login?facs-request=true";
			}
		} else if (obj instanceof ParttimeStaff) {
			if (!StringUtils.isBlank(suburl2) && !"parttimeStaff".equals(suburl2)) {
				showErrorMessage("请先登录");
				return "/pages/common/login?faces-redirect=true";
			}
		}
		return url;
	}

	/**
	 * 
	 * @param tip
	 *            提示信息
	 */
	public void showErrorMessage(String tip) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tip, null));
	}

	public IStaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(IStaffService staffService) {
		this.staffService = staffService;
	}

	public IParttimeStaffService getParttimeStaffService() {
		return parttimeStaffService;
	}

	public void setParttimeStaffService(IParttimeStaffService parttimeStaffService) {
		this.parttimeStaffService = parttimeStaffService;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
