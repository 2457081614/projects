package com.meession.market.bulletin.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.meession.market.bulletin.entity.Bulletin;
import com.meession.market.bulletin.service.IBulletinService;
import com.meession.market.common.dateutil.DateUtil;
import com.meession.market.staff.entity.Staff;
import com.meession.market.staff.service.IStaffService;

@ManagedBean
@ViewScoped
public class BulletinView implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{bulletinService}")
	private IBulletinService bulletinService;
	@ManagedProperty(value = "#{staffService}")
	private IStaffService staffService;

	private Bulletin newBulletin;
	/**
	 * 查看、修改、删除某一个任务时，这个对象保存要被执行操作的对象的所有属性
	 */
	private Bulletin bulletin;
	private List<Bulletin> publishedBulletinList;
	/**
	 * 所有任务列表
	 */
	private List<Bulletin> bulletinList;
	/**
	 * 被过滤后的任务列表
	 */
	private List<Bulletin> filteredBulletinList;
	/**
	 * 被选择的任务
	 */
	private List<Bulletin> selectedBulletinList;

	private HttpSession session;
	private boolean needPublish;

	@PostConstruct
	public void init() {
		bulletinList = bulletinService.findAll();
		publishedBulletinList = removeDispublishedBulletin(bulletinList);
		newBulletin = new Bulletin();
		bulletin = new Bulletin();
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	/**
	 * 保存公告
	 */
	public void save() {
		if (newBulletin != null) {
			newBulletin.setCreatedDate(DateUtil.dateToString(new Date()));
			if (needPublish){
				newBulletin.setStatus(Bulletin.PUBLISHED);
				newBulletin.setPublishedDate(DateUtil.dateToString(new Date()));
			}
			else
				newBulletin.setStatus(Bulletin.SAVED_AND_DISPUBLISHED);
			Staff staff = (Staff) session.getAttribute("loginedUser");
			newBulletin.setMaker(staff);
			bulletinService.saveBulletin(newBulletin);
			bulletinList.add(newBulletin);
			if (needPublish) {
				publishedBulletinList.add(newBulletin);
				showResultTip("发布成功", FacesMessage.SEVERITY_INFO);
			} else {
				showResultTip("保存成功", FacesMessage.SEVERITY_INFO);
			}
			newBulletin = new Bulletin();
			needPublish = false;
		} else {
			showResultTip("系统发生错误", FacesMessage.SEVERITY_FATAL);
		}
	}

	/**
	 * 发布公告
	 */
	public void publish() {
		if (bulletin != null) {
			if (Bulletin.PUBLISHED.equals(bulletin.getStatus())) {
				showResultTip("此公告已被发布，不能再次发布", FacesMessage.SEVERITY_ERROR);
				return;
			} else {
				bulletin.setStatus(Bulletin.PUBLISHED);
				bulletin.setPublishedDate(DateUtil.dateToString(new Date()));
				publishedBulletinList.add(bulletin);
				bulletinService.updateBulletin(bulletin);
				showResultTip("发布成功", FacesMessage.SEVERITY_INFO);
			}
		} else {
			showResultTip("系统内部发生错误，发布失败", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 对未发布的公告进行修改
	 */
	public void updateBulletin() {
		if (bulletin != null) {
			if(Bulletin.PUBLISHED.equals(bulletin.getStatus())){
				showResultTip("此公告已被发布，无法修改", FacesMessage.SEVERITY_ERROR);
			}
			else{
				bulletinService.updateBulletin(bulletin);
				showResultTip("修改成功", FacesMessage.SEVERITY_INFO);
			}
		} else {
			showResultTip("修改失败", FacesMessage.SEVERITY_ERROR);
		}
	}

	/**
	 * 删除公告
	 * 
	 * @param bulletin
	 */
	public void deleteBulletin() {
		if (bulletin != null) {
			bulletinList.remove(bulletin);
			if(Bulletin.PUBLISHED.equals(bulletin.getStatus())){
				publishedBulletinList.remove(bulletin);
			}
			if(filteredBulletinList != null && filteredBulletinList.contains(bulletin)){
				filteredBulletinList.remove(bulletin);
			}
			bulletinService.deleteBulletin(bulletin.getId());
			showResultTip("删除成功", FacesMessage.SEVERITY_INFO);
		} else {
			showResultTip("系统内部发生错误,删除失败", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void deleteBatch(){
		if(selectedBulletinList != null && selectedBulletinList.size() > 0){
			this.bulletin = new Bulletin();
			List<Long> ids = new ArrayList<>();
			for(Bulletin b : selectedBulletinList){
				ids.add(b.getId());
				bulletinList.remove(b);
				if(Bulletin.PUBLISHED.equals(b.getStatus())){
					publishedBulletinList.remove(b);
				}
			}
			bulletinService.deleteBatch(ids);
			if(filteredBulletinList != null)filteredBulletinList.clear();
			showResultTip("删除成功", FacesMessage.SEVERITY_INFO);
		}else{
			showResultTip("请至少选择一个任务", FacesMessage.SEVERITY_ERROR);
		}
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

	/**
	 * 从数据库中查询到的所有公告中去除没有发布的
	 * 
	 * @param bulletinList
	 */
	public List<Bulletin> removeDispublishedBulletin(List<Bulletin> bulletinList) {
		List<Bulletin> publishedBulletinList = new ArrayList<>();
		if (bulletinList != null) {
			for (Bulletin b : bulletinList) {
				if (Bulletin.PUBLISHED.equals(b.getStatus()))
					publishedBulletinList.add(b);
			}
		}
		return publishedBulletinList;
	}

	/**
	 * 新建公告时，点击【清除内容】按钮，清除内容
	 */
	public void clearContent() {
		if (newBulletin != null) {
			newBulletin.setMessageHead("");
			newBulletin.setMessageBody("");
		}
		this.needPublish = false;
	}

	/**
	 * 判断公告是否可以被修改
	 */
	public void validatePublication(Bulletin b){
		if(b != null && Bulletin.PUBLISHED.equals(b.getStatus())){
			showResultTip("此公告已被发布，无法修改", FacesMessage.SEVERITY_ERROR);
			RequestContext.getCurrentInstance().execute("init()");
		}
		else if(b != null && Bulletin.SAVED_AND_DISPUBLISHED.equals(b.getStatus())){
			RequestContext.getCurrentInstance().execute("hideDataTable()");
		}
	}
	
	/* All are setter and getter method below. */
	/* All are setter and getter method below. */
	/* All are setter and getter method below. */
	/* All are setter and getter method below. */

	public Bulletin getNewBulletin() {
		return newBulletin;
	}

	public void setNewBulletin(Bulletin newBulletin) {
		this.newBulletin = newBulletin;
	}

	public List<Bulletin> getSelectedBulletinList() {
		return selectedBulletinList;
	}

	public void setSelectedBulletinList(List<Bulletin> selectedBulletinList) {
		this.selectedBulletinList = selectedBulletinList;
	}

	public List<Bulletin> getFilteredBulletinList() {
		return filteredBulletinList;
	}

	public void setFilteredBulletinList(List<Bulletin> filteredBulletinList) {
		this.filteredBulletinList = filteredBulletinList;
	}

	public List<Bulletin> getBulletinList() {
		return bulletinList;
	}

	public void setBulletinList(List<Bulletin> bulletinList) {
		this.bulletinList = bulletinList;
	}

	public IStaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(IStaffService staffService) {
		this.staffService = staffService;
	}

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	public IBulletinService getBulletinService() {
		return bulletinService;
	}

	public void setBulletinService(IBulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}

	public boolean isNeedPublish() {
		return needPublish;
	}

	public void setNeedPublish(boolean needPublish) {
		this.needPublish = needPublish;
	}

	public List<Bulletin> getPublishedBulletinList() {
		return publishedBulletinList;
	}

	public void setPublishedBulletinList(List<Bulletin> publishedBulletinList) {
		this.publishedBulletinList = publishedBulletinList;
	}
	
}
