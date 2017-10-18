package com.meession.education.core.model;

import java.util.Set;

/**
 * 
 * @author zy 
 * 组织机构
 */

public class Orga {
	private Long id;
	/**
	 * 机构代码
	 */
	private String no;
	/**
	 * 机构名称
	 */
	private String name;
	/**
	 * 机构性质
	 */
	private String nature;
	/**
	 * 是否撤销
	 */
	private boolean revoked;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 直属机构
	 */
	private Orga parent;
	/**
	 * 下属机构
	 */
	private Set<Orga> childs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Orga getParent() {
		return parent;
	}

	public void setParent(Orga parent) {
		this.parent = parent;
	}

	public Set<Orga> getChilds() {
		return childs;
	}

	public void setChilds(Set<Orga> childs) {
		this.childs = childs;
	}

}
