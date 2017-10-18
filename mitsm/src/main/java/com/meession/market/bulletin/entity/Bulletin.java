package com.meession.market.bulletin.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.meession.market.common.RDU;
import com.meession.market.common.dateutil.DateUtil;
import com.meession.market.staff.entity.Staff;

@Entity
@Table(name = "Bulletin")
public class Bulletin {
	
	public static final String SAVED_AND_DISPUBLISHED = "已保存,未发布";
	public static final String PUBLISHED = "已发布";
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "bulletin_id")
	private long id;
	
	/**
	 * 任务的创建者
	 */
	@ManyToOne(targetEntity = Staff.class)
	@JoinColumn(name = "staff_id",referencedColumnName = "staff_id")
	private Staff maker;
	/**
	 * 创建日期
	 */
	private String createdDate;
	/**
	 * 任务被下达的时间
	 */
	private String publishedDate;
	/**
	 * 公告的消息头
	 */
	@Column(name = "message_head",length = 1024,nullable = false)
	private String messageHead;
	/**
	 * 公告的消息体.<br/>
	 * 这里定义了公告内容的最大长度为65530个字节，MySQL5.0以上的版本定义了在UTF-8的字符集下，<br/>
	 * 一个汉字占3个字节。也就是说，这里公告内容的最大长度为65530/3 = 21843个汉字，这已经足够保存公告的内容了。
	 * <br/><br/>
	 */
	@Column(name = "message_body",length = 65530,nullable = false)
	private String messageBody;
	/**
	 * 任务状态,有:
	 * <b>已保存,未发布</b>、
	 * <b>已发布</b>、
	 * 两种状态
	 */
	@Column(name = "status",nullable = false,length = 50)
	private String status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Staff getMaker() {
		return maker;
	}
	public void setMaker(Staff maker) {
		this.maker = maker;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getMessageHead() {
		return messageHead;
	}
	public void setMessageHead(String messageHead) {
		this.messageHead = messageHead;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	@Override
	public String toString() {
		return "Bulletin [id=" + id + ", maker=" + maker + ", createdDate=" + createdDate + ", publishedDate="
				+ publishedDate + ", messageHead=" + messageHead + ", messageBody=" + messageBody + ", status=" + status
				+ "]";
	}
	public static Bulletin mock(){
		Bulletin task = new Bulletin();
		task.setStatus(RDU.ranTaskStatus());
		task.setCreatedDate(DateUtil.dateToString(DateUtil.randomDate("2000-01-01",
				DateUtil.dateToString(DateUtil.getCurrentTime()))));
		if(!task.getStatus().equals(Bulletin.SAVED_AND_DISPUBLISHED))
		task.setPublishedDate(DateUtil.dateToString(DateUtil.randomDate(task.getCreatedDate(),
				DateUtil.dateToString(DateUtil.getCurrentTime()))));
		task.setMessageHead(RDU.randomBulletinTitle());
		task.setMessageBody(RDU.randomBulletinContent());
		return task;
	}
	
	
	
}
