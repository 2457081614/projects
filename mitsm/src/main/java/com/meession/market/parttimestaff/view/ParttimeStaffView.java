package com.meession.market.parttimestaff.view;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import com.meession.market.common.entity.Resumable;
import com.meession.market.common.util.ExcelReader;
import com.meession.market.common.util.Md5;
import com.meession.market.parttimestaff.entity.ParttimeStaff;
import com.meession.market.parttimestaff.service.IParttimeStaffService;

@ManagedBean
@ViewScoped
public class ParttimeStaffView implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{parttimeStaffService}")
	private IParttimeStaffService parttimeStaffService;

	/**
	 * 当点击查询某个代理信息的时候，这个对象存储要查看的代理的信息
	 */
	private ParttimeStaff parttimeStaff = new ParttimeStaff();
	/**
	 * 没有被删除的兼职列表
	 */
	private List<ParttimeStaff> parttimeStaffList;
	/**
	 * 被删除的兼职列表
	 */
	private List<ParttimeStaff> deletedParttimeStaffList = new ArrayList<>();
	/**
	 * 使用过滤条件时得到的若干条符合条件的数据
	 */
	private List<ParttimeStaff> filteredParttimeStaffList;
	/**
	 * 批量删除时被选中的行
	 */
	private List<ParttimeStaff> selectedParttimeStaffList;

	/**
	 * 新注册的兼职
	 */
	private ParttimeStaff newParttimeStaff;

	private HttpSession session;

	private ParttimeStaff loginedParttimeStaff;

	@PostConstruct
	public void init() {
		parttimeStaffList = parttimeStaffService.findAllParttimeStaffInfo();
		removeDeleted(parttimeStaffList);
		newParttimeStaff = new ParttimeStaff();
		parttimeStaff = new ParttimeStaff();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Object obj = session.getAttribute("loginedUser");
		if (obj != null && obj instanceof ParttimeStaff) {
			loginedParttimeStaff = (ParttimeStaff) obj;
		}
		initNewParttimeStaff();

	}

	public void updateParttimeStaff() {
		parttimeStaffService.updateParttimeStaff(this.parttimeStaff);
		showResultTip("修改成功", FacesMessage.SEVERITY_INFO);
	}

	/**
	 * 新增兼职
	 */
	public void addNewParttimeStaff() {
		ParttimeStaff p = ParttimeStaff.cloneParttimeStaff(newParttimeStaff);
	
		p.setPassword(Md5.makeMD5(p.getPassword()));
		
		parttimeStaffService.addParttimeStaff(p);
		parttimeStaffList.add(p);
		showResultTip("添加成功", FacesMessage.SEVERITY_INFO);
		initNewParttimeStaff();

	}

	/**
	 * 恢复回收站中一个兼职
	 */
	public void recover() {
		if (parttimeStaff != null) {
			deletedParttimeStaffList.remove(parttimeStaff);
			if (filteredParttimeStaffList != null && filteredParttimeStaffList.size() > 0) {
				filteredParttimeStaffList.remove(parttimeStaff);
			}
			parttimeStaff.setDeleted(Resumable.UNDELETED);
			parttimeStaffList.add(parttimeStaff);
			parttimeStaffService.updateParttimeStaff(parttimeStaff);
			showResultTip("兼职" + parttimeStaff.getName() + "已从回收站恢复", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("恢复失败", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 把回收站中所有兼职全部恢复
	 */
	public void recoverAll() {
		if (deletedParttimeStaffList != null && deletedParttimeStaffList.size() > 0) {
			for (ParttimeStaff ps : deletedParttimeStaffList) {
				ps.setDeleted(Resumable.UNDELETED);
				parttimeStaffList.add(ps);
				parttimeStaffService.updateParttimeStaff(ps);
			}
			deletedParttimeStaffList.clear();
			if (filteredParttimeStaffList != null)
				filteredParttimeStaffList.clear();
			showResultTip("全部兼职已恢复", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("请至少选择一个兼职", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 从回收站中彻底删除一个兼职
	 * 
	 * @param p
	 */
	public void delete() {
		if (parttimeStaff != null) {
			deletedParttimeStaffList.remove(parttimeStaff);
			if (filteredParttimeStaffList != null && filteredParttimeStaffList.size() > 0) {
				filteredParttimeStaffList.remove(parttimeStaff);
			}
			parttimeStaffService.deleteById(parttimeStaff.getId());
			showResultTip("兼职" + parttimeStaff.getName() + "已被彻底删除", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("删除失败", FacesMessage.SEVERITY_ERROR);
		}

	}

	/**
	 * 删除回收站中所有兼职
	 */
	public void deleteAll() {
		List<Long> ids = new ArrayList<>();
		if (deletedParttimeStaffList != null && deletedParttimeStaffList.size() > 0) {
			for (ParttimeStaff p : deletedParttimeStaffList) {
				ids.add(p.getId());
			}
			parttimeStaffService.deleteByIds(ids);
			deletedParttimeStaffList.clear();
			if (filteredParttimeStaffList != null)
				filteredParttimeStaffList.clear();
			showResultTip("回收站中的所有兼职已被彻底清空", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("请至少选择一个兼职", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 丢弃一个兼职到回收站
	 */
	public void diacardParttimeStaff() {
		if (parttimeStaff != null) {
			parttimeStaffList.remove(parttimeStaff);
			if (filteredParttimeStaffList != null && filteredParttimeStaffList.size() > 0) {
				filteredParttimeStaffList.remove(parttimeStaff);
			}
			parttimeStaff.setDeleted(Resumable.DELETED);
			deletedParttimeStaffList.add(parttimeStaff);
			parttimeStaffService.updateParttimeStaff(parttimeStaff);
			showResultTip("删除成功", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("删除失败", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 批量丢弃兼职到回收站
	 */
	public void discardBatch() {
		if (selectedParttimeStaffList != null && selectedParttimeStaffList.size() > 0) {
			for (ParttimeStaff ps : selectedParttimeStaffList) {
				parttimeStaffList.remove(ps);
				ps.setDeleted(Resumable.DELETED);
				parttimeStaffService.updateParttimeStaff(ps);
				deletedParttimeStaffList.add(ps);
			}
			selectedParttimeStaffList.clear();
			if (filteredParttimeStaffList != null)
				filteredParttimeStaffList.clear();
			showResultTip("删除成功", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("请至少选择一个兼职", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 从员工或管理员上传的表格中批量导入兼职
	 * 
	 * @param event
	 */
	public void addBatch(FileUploadEvent event) {
		try {
			InputStream is = event.getFile().getInputstream();
			int sheetIndex = 0;
			int offsetX = 0;
			int offsetY = 0;
			String[][] sourceData = new ExcelReader(is).readExcel(sheetIndex, offsetX, offsetY);

			// parttimeStaffService.addParttimeStaffs(ParttimeStaff.getParttimeStaffs(sourceData));

			//zy   密码加密
			List<ParttimeStaff> parttimeStaffsOld = ParttimeStaff.getParttimeStaffs(sourceData);// 批量导入的兼职，密码都是没加密的
			List<ParttimeStaff> parttimeStaffsNew = new ArrayList<ParttimeStaff>();// 存放密码加密了的兼职
			for (ParttimeStaff p : parttimeStaffsOld) {
				p.setPassword(Md5.makeMD5(p.getPassword()));
				parttimeStaffsNew.add(p);
			}
			parttimeStaffService.addParttimeStaffs(parttimeStaffsNew);
			
			
			showResultTip("批量导入成功", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			String tip = "批量导入失败,请联系相关技术人员";
			showResultTip(tip, FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
		new Thread(new Runnable() {
			public void run() {
				reSelect();
			}
		}).start();
	}

	/**
	 * 从所有兼职列表中移除标记删除的
	 * 
	 * @param parttimeStaffList
	 *            所有客户列表
	 */
	public void removeDeleted(List<ParttimeStaff> parttimeStaffList) {
		if (parttimeStaffList != null) {
			Iterator<ParttimeStaff> iterator = parttimeStaffList.iterator();
			while (iterator.hasNext()) {
				ParttimeStaff p = iterator.next();
				if (p.getDeleted() == Resumable.DELETED) {
					iterator.remove();
					this.deletedParttimeStaffList.add(p);
				}
			}
		}
	}

	/**
	 * 重新查询数据库,更新parttimeStaffList
	 */
	public void reSelect() {
		this.parttimeStaffList = parttimeStaffService.findAllParttimeStaffInfo();
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

	public IParttimeStaffService getParttimeStaffService() {
		return parttimeStaffService;
	}

	public void setParttimeStaffService(IParttimeStaffService parttimeStaffService) {
		this.parttimeStaffService = parttimeStaffService;
	}

	public ParttimeStaff getParttimeStaff() {
		return parttimeStaff;
	}

	public void setParttimeStaff(ParttimeStaff parttimeStaff) {
		this.parttimeStaff = parttimeStaff;
	}

	public List<ParttimeStaff> getParttimeStaffList() {
		return parttimeStaffList;
	}

	public void setParttimeStaffList(List<ParttimeStaff> parttimeStaffList) {
		this.parttimeStaffList = parttimeStaffList;
	}

	public List<ParttimeStaff> getFilteredParttimeStaffList() {
		return filteredParttimeStaffList;
	}

	public void setFilteredParttimeStaffList(List<ParttimeStaff> filteredParttimeStaffList) {
		this.filteredParttimeStaffList = filteredParttimeStaffList;
	}

	public List<ParttimeStaff> getSelectedParttimeStaffList() {
		return selectedParttimeStaffList;
	}

	public void setSelectedParttimeStaffList(List<ParttimeStaff> selectedParttimeStaffList) {
		this.selectedParttimeStaffList = selectedParttimeStaffList;
	}

	public ParttimeStaff getNewParttimeStaff() {
		return newParttimeStaff;
	}

	public void setNewParttimeStaff(ParttimeStaff newParttimeStaff) {
		this.newParttimeStaff = newParttimeStaff;
	}

	public List<ParttimeStaff> getDeletedParttimeStaffList() {
		return deletedParttimeStaffList;
	}

	public void setDeletedParttimeStaffList(List<ParttimeStaff> deletedParttimeStaffList) {
		this.deletedParttimeStaffList = deletedParttimeStaffList;
	}

	public void setLoginedParttimeStaff(ParttimeStaff loginedParttimeStaff) {
		this.loginedParttimeStaff = loginedParttimeStaff;
	}

	public ParttimeStaff getLoginedParttimeStaff() {
		return loginedParttimeStaff;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpSession getSession() {
		return session;
	}
	
	public void initNewParttimeStaff(){
		newParttimeStaff = new ParttimeStaff();
		newParttimeStaff.setPassword("666666");
	}
}
