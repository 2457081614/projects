package com.meession.market.revisit.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.meession.market.common.RDU;
import com.meession.market.customer.entity.Customer;

@Entity
@Table(name = "Revisit")
public class Revisit {

	public static final String REAL = "属实";
	public static final String UNREAL = "虚假";

	public static final int UNAPPRAISED = 0;
	public static final int APPRAISED = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "revisit_id")
	private long id;
	/**
	 * 一次回访对应的客户
	 */

	private long customerId;

	/**
	 * 一次回访对应的干活的兼职
	 */
	@Column(name = "patttimeStaff_id", nullable = false)
	private long parttimeStaffId;
	/**
	 * 回访者
	 */
	@Column(name = "revisiter_id", nullable = false)
	private long revisiterId;

	@Column(name = "task_id", nullable = false)
	private long task_id;

	/**
	 * 兼职做的工作是否属实
	 */
	@Column(name = "realness")
	private String realness;
	/**
	 * 客户的兼职的印象、评价
	 */
	@Column(name = "impression_from_customer", length = 65530)
	private String impressionFromCustomer;

	/**
	 * 是否已经评价
	 */
	private int hasAppraised;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getParttimeStaffId() {
		return parttimeStaffId;
	}

	public void setParttimeStaffId(long parttimeStaffId) {
		this.parttimeStaffId = parttimeStaffId;
	}

	public void setRealness(String realness) {
		this.realness = realness;
	}

	public String getRealness() {
		return realness;
	}

	public String getImpressionFromCustomer() {
		return impressionFromCustomer;
	}

	public void setImpressionFromCustomer(String impressionFromCustomer) {
		this.impressionFromCustomer = impressionFromCustomer;
	}

	public int getHasAppraised() {
		return hasAppraised;
	}

	public void setHasAppraised(int hasAppraised) {
		this.hasAppraised = hasAppraised;
	}

	public long getRevisiterId() {
		return revisiterId;
	}

	public void setRevisiterId(long revisiterId) {
		this.revisiterId = revisiterId;
	}

	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}

	public long getTask_id() {
		return task_id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Revisit(long id, long customerId, long parttimeStaffId,
			long revisiterId, long task_id, String realness,
			String impressionFromCustomer, int hasAppraised) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.parttimeStaffId = parttimeStaffId;
		this.revisiterId = revisiterId;
		this.task_id = task_id;
		this.realness = realness;
		this.impressionFromCustomer = impressionFromCustomer;
		this.hasAppraised = hasAppraised;
	}

	public Revisit() {
		super();
	}

	@Override
	public String toString() {
		return "Revisit [id=" + id + ", customerId=" + customerId
				+ ", parttimeStaffId=" + parttimeStaffId + ", revisiterId="
				+ revisiterId + ", task_id=" + task_id + ", realness="
				+ realness + ", impressionFromCustomer="
				+ impressionFromCustomer + ", hasAppraised=" + hasAppraised
				+ "]";
	}

	public static Revisit mock() {
		Revisit revisit = new Revisit();
		revisit.setParttimeStaffId(RDU.random(20) + 1);
		revisit.setTask_id(RDU.random(20) + 1);
		revisit.setHasAppraised(Revisit.UNAPPRAISED);
		return revisit;
	}
}
