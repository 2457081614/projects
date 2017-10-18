package com.meession.market.common.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.meession.market.common.util.Md5;
import com.meession.market.parttimestaff.entity.ParttimeStaff;
import com.meession.market.parttimestaff.service.IParttimeStaffService;
import com.meession.market.staff.entity.Staff;
import com.meession.market.staff.service.IStaffService;

@ManagedBean
@ViewScoped
public class PersonalCenterView implements Serializable {

	private static final long serialVersionUID = 1L;

	private String validationPng;

	private HttpSession session;
	private Staff staff;
	private ParttimeStaff parttimeStaff;
	@ManagedProperty(value = "#{pagesView}")
	private PagesView pagesView;
	@ManagedProperty(value = "#{staffService}")
	private IStaffService staffService;
	@ManagedProperty(value = "#{parttimeStaffService}")
	private IParttimeStaffService parttimeStaffService;

	/**
	 * 原密码
	 */
	private String originalPassword;
	/**
	 * 新密码
	 */
	private String newPassword;
	/**
	 * 确认新密码
	 */
	private String repeatedNewPassword;

	@PostConstruct
	public void init() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		initUsers();
		initAllPassword();
	}

	public void initUsers() {
		this.staff = new Staff();
		this.parttimeStaff = new ParttimeStaff();
	}

	/**
	 * 初始化所有密码
	 */
	public void initAllPassword() {
		this.originalPassword = null;
		this.newPassword = null;
		this.repeatedNewPassword = null;
		RequestContext.getCurrentInstance().execute("hideRightValidationPngs()");
	}

	/**
	 * 检查用户类型
	 */
	public String checkUserType() {
		Object loginedUser = session.getAttribute("loginedUser");
		if (loginedUser != null) {
			if (loginedUser instanceof Staff) {
				this.staff = (Staff) loginedUser;
				pagesView.setDynamicaPagesInclude("/pages/staff/personal_center.xhtml");
			} else if (loginedUser instanceof ParttimeStaff) {
				this.parttimeStaff = (ParttimeStaff) loginedUser;
				pagesView.setDynamicaPagesInclude("/pages/parttimeStaff/personal_center.xhtml");
			}
		}

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String currentUrl = request.getRequestURL().toString();
		return currentUrl;
	}

	/**
	 * 修改个人信息
	 */
	public void modifyInfo() {
		if (this.staff != null && this.staff.getTel() != null) {
			staffService.updateStaff(this.staff);
		} else if (this.parttimeStaff != null && this.parttimeStaff.getTel() != null) {
			parttimeStaffService.updateParttimeStaff(this.parttimeStaff);
		}
		showResultTip("修改成功", FacesMessage.SEVERITY_INFO);
	}

	/**
	 * 修改密码。<br/>
	 */
	public void modifyPassword() {
		boolean validatedResult = validateForm();
		if (validatedResult) {
			if (this.staff != null && this.staff.getId() != 0) {
				//this.staff.setPassword(newPassword);
				
				
				//zy  密码加密
				this.staff.setPassword(Md5.makeMD5(newPassword));
				
				staffService.updateStaff(this.staff);
			} else if (this.parttimeStaff != null && this.parttimeStaff.getId() != 0) {
				//this.parttimeStaff.setPassword(newPassword);
			
				//zy 密码加密
				this.parttimeStaff.setPassword(Md5.makeMD5(newPassword));
				
				
				parttimeStaffService.updateParttimeStaff(this.parttimeStaff);
			}
			showResultTip("修改成功", FacesMessage.SEVERITY_INFO);
			initAllPassword();
			RequestContext.getCurrentInstance().execute("hideRightValidationPngs()");
			RequestContext.getCurrentInstance().execute("hideWrongValidationPngs()");
		}
		else{
			RequestContext.getCurrentInstance().execute("hideRightValidationPngs()");
		}
	}

	public boolean validateForm() {
		Object loginedUser = session.getAttribute("loginedUser");
		if (loginedUser != null) {
			if (loginedUser instanceof Staff) {
				this.staff = (Staff) loginedUser;
				if (!staff.getPassword().equals(Md5.makeMD5(originalPassword))) {
					showResultTip("修改失败:原密码不正确", FacesMessage.SEVERITY_ERROR);
					return false;
				}
			} else if (loginedUser instanceof ParttimeStaff) {
				this.parttimeStaff = (ParttimeStaff) loginedUser;
				if (!parttimeStaff.getPassword().equals(Md5.makeMD5(originalPassword))) {
					showResultTip("修改失败:原密码不正确", FacesMessage.SEVERITY_ERROR);
					return false;
				}
			}
			if (newPassword == null || !newPassword.equals(repeatedNewPassword)) {
				showResultTip("修改失败:两次密码不一致", FacesMessage.SEVERITY_ERROR);
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 显示提示信息
	 * 
	 * @param tip
	 *            提示信息
	 * @param s
	 */
	public void showResultTip(String tip, FacesMessage.Severity s) {
		String realText = null;
		final int eachWordSize = 30;
		if (s != null && s == FacesMessage.SEVERITY_ERROR) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:red;font-size:18px;'>" + tip + "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_INFO) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:#003a6c;font-size:18px;'>" + tip + "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_WARN) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:orange;font-size:18px;'>" + tip + "</span>";
		} else if (s != null && s == FacesMessage.SEVERITY_FATAL) {
			realText = "<div style='text-align:center;width:" + (tip.length() * eachWordSize)
					+ "px;'><span style='color:red;font-weight:bold;font-size:18px;'>" + tip + "</span>";
		}
		FacesMessage message = new FacesMessage(s, "提示", realText);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpSession getSession() {
		return session;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public ParttimeStaff getParttimeStaff() {
		return parttimeStaff;
	}

	public void setParttimeStaff(ParttimeStaff parttimeStaff) {
		this.parttimeStaff = parttimeStaff;
	}

	public PagesView getPagesView() {
		return pagesView;
	}

	public void setPagesView(PagesView pagesView) {
		this.pagesView = pagesView;
	}

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatedNewPassword() {
		return repeatedNewPassword;
	}

	public void setRepeatedNewPassword(String repeatedNewPassword) {
		this.repeatedNewPassword = repeatedNewPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getValidationPng() {
		return validationPng;
	}

	public void setValidationPng(String validationPng) {
		this.validationPng = validationPng;
	}
	
}
