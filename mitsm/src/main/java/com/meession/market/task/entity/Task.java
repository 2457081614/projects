package com.meession.market.task.entity;

/*
 * createdDate
 assignedDate
 planedfinishedDate
 finishedDate
 description
 customerFeedback
 qqChatRecord
 status
 parttimeStaff
 customer
 staff
 Map<String,String> map

 **/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.annotations.ManyToAny;

import com.meession.market.customer.entity.Customer;
import com.meession.market.parttimestaff.entity.ParttimeStaff;
import com.meession.market.question.entity.Question;
import com.meession.market.staff.entity.Staff;

/**
 * 每次对一个客户进行交涉，就会生成一个task，task由Staff创建，指派给ParttimeStaff，或者自己去完成。
 * 最后parttimeStaff将此次交涉信息（包含问卷信息反馈给Staff，整个过程的内容都会保存在task中
 * 
 * @author yanglaixi
 *
 */
@Entity
@Table(name = "Task")
public class Task {

	public static final String UNASSIGNED = "已创建，未下达";

	public static final String ASSIGNED = "已下达";

	public static final String UNREVISIT = "待回访";
	
	public static final String REVISITING = "回访中";

	public static final String FINISHED = "已完成";
	
	public static final String FIRST_STAGE = "第一阶段";
	public static final String SECOND_STAGE = "第二阶段";
	public static final String THIRD_STAGE = "第三阶段";
	public static final String FOURTH_STAGE = "第四阶段";

	public static final int UNAVALUATED = 0;

	public static final int AVALUATED = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "task_id")
	private long id;

	/**
	 * 创建时间
	 */
	private String createdDate;
	/**
	 * 指派时间
	 */
	private String assignedDate;

	/**
	 * 计划完成时间
	 */
	private String plannedFinishedDate;

	/**
	 * 实际完成时间
	 */
	private String finishedDate;

	/**
	 * 任务描述
	 */
	@Column(length = 65530)
	private String taskDescription;

	/**
	 * 任务状态:已创建，已下达，待回访，已完成
	 */
	private String status;
	/**
	 * 任务阶段
	 */
	private String stage;
	/**
	 * 任务指派给谁
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "parttimeStaff_id", referencedColumnName = "parttimeStaff_id")
	private ParttimeStaff parttimeStaff;

	/**
	 * 任务是谁指派的
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
	private Staff staff;

	/**
	 * 任务针对的客户
	 */
	/*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;*/
	
	@ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinTable( name="Task_customer", joinColumns = {@JoinColumn(name = "Task_id")},   
    inverseJoinColumns = {@JoinColumn(name = "Customer_id")}) 
	private Set<Customer> customers=new HashSet<Customer>();
    /**
     * 任务的多个问题
     */
	@ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
	 @JoinTable( name="Task_question", joinColumns = {@JoinColumn(name = "Task_id")},   
	    inverseJoinColumns = {@JoinColumn(name = "Question_id")}) 
	private Set<Question> questions = new HashSet<Question>();

	/**
	 * 任务是否被评价
	 */
	private int isAvaluated;
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setPlannedFinishedDate(String plannedFinishedDate) {
		this.plannedFinishedDate = plannedFinishedDate;
	}
	public String getPlannedFinishedDate() {
		return plannedFinishedDate;
	}
	
	public String getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ParttimeStaff getParttimeStaff() {
		return parttimeStaff;
	}

	public void setParttimeStaff(ParttimeStaff parttimeStaff) {
		this.parttimeStaff = parttimeStaff;
	}
  
	
	

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}


	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	
	public void setIsAvaluated(int isAvaluated) {
		this.isAvaluated = isAvaluated;
	}
	public int getIsAvaluated() {
		return isAvaluated;
	}
	
	/**
	 * Return a new instance of Task according to the designated original task.
	 * @param origTask
	 * @return
	 */
	public static Task cloneTask(Task origTask){
		Task destTask = new Task();
		try{
			BeanUtils.copyProperties(destTask, origTask);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return destTask;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", createdDate=" + createdDate + ", assignedDate=" + assignedDate
				+ ", plannedFinishedDate=" + plannedFinishedDate + ", finishedDate=" + finishedDate
				+ ", taskDescription=" + taskDescription + ", status=" + status + ", stage=" + stage + ", questions="
				+ questions + ", isAvaluated=" + isAvaluated + "]";
	}

	
	

}
