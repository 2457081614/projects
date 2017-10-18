package com.meession.market.staff.entity;

import java.sql.Blob;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.meession.market.common.RDU;
import com.meession.market.common.dateutil.DateUtil;
import com.meession.market.common.util.Md5;
import com.meession.market.task.entity.Task;
import com.meession.market.bulletin.entity.Bulletin;

@Entity
@Table(name = "Staff")
public class Staff {
	
	public static final String INITIALIZED_PASSWORD = "666666";
	
	
	/**
	 * 市场部管理者的身份标识符
	 */
	public static final int MANAGER = 1;
	/**
	 * 市场部普通员工的身份标识符
	 */
	public static final int ORDINARY_STAFF = 2;
	/**
	 * 数据库唯一标识符
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "staff_id")
	private long id;
	/**
	 * 员工的名字
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 电话
	 */

	@Column(length = 11, unique = true)
	private String tel;
	/**
	 * 登录密码
	 */
	@Column(length = 32)
	private String password;
	/**
	 * 员工身份标识符。<br/>
	 * 如果是1，那么它就代表管理者；如果是2，代表普通员工
	 */
	@Column(length = 1)
	private int identifier;
	/**
	 * 性别
	 */
	@Column(length = 10, name = "gender")
	private String gender;
	/**
	 * 员工的生日
	 */
	private String birthday;
	/**
	 * 员工的身份证号
	 */
	@Column(length = 18)
	private String idCardNumber;
	/**
	 * 员工的email
	 */
	@Column(unique = true)
	private String email;

	/**
	 *员工毕业学校 
	 */
	private String school;

	/**
	 * 员工头像
	 */
	private Blob icon;

	/**
	 * 员工住址
	 */
	private String address;
	/**
	 * 是否已被删除.<br/>
	 * 如果没有被删除，则这个值为0，否则为1.<br/>
	 * <br/>
	 */
	@Column(name = "deleted", length = 1, nullable = false, columnDefinition = "INT default 0")
	private int deleted;

	@OneToMany(mappedBy = "maker", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Bulletin> bulletins = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "staff")
	private List<Task> tasks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Blob getIcon() {
		return icon;
	}

	public void setIcon(Blob icon) {
		this.icon = icon;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Bulletin> getBulletins() {
		return bulletins;
	}

	public void setBulletins(Set<Bulletin> bulletins) {
		this.bulletins = bulletins;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", tel=" + tel + ", password=" + password + ", identifier="
				+ identifier + ", gender=" + gender + ", birthday=" + birthday + ", idCardNumber=" + idCardNumber
				+ ", email=" + email + ", address=" + address + ", deleted=" + deleted + "]";
	}

	/**
	 * 模拟生成员工
	 * 
	 * @return
	 */
	public static Staff mock() {
		Staff staff = new Staff();
		staff.setName(RDU.randomName());
		staff.setIcon(RDU.ranPhoto());
		staff.setGender(RDU.randomGender());
		staff.setPassword(Md5.makeMD5(Staff.INITIALIZED_PASSWORD));
		staff.setBirthday(DateUtil.dateToString(RDU.randomDate()));
		staff.setEmail(RDU.randomEmail());
		staff.setIdentifier(Staff.ORDINARY_STAFF);
		String idCardNumber = RDU.randomCardNumber(18);
		staff.setIdCardNumber(idCardNumber);
		staff.setTel(RDU.randomCellPhoneNumber());
		staff.setAddress("长沙大道" + RDU.random(300) + "号");
		return staff;

	}

	/**
	 * 把模拟生成的员工存在集合中作为返回值给前端
	 * 
	 * @param size
	 * @return
	 */
	public static List<Staff> mock(int size) {
		List<Staff> staffs = new ArrayList<Staff>();
		for (int i = 0; i < size; i++) {
			staffs.add(Staff.mock());
		}
		return staffs;
	}

	public static List<Staff> getStaffs(String[][] sourceData) {
		List<Staff> list = new ArrayList<>();
		if (sourceData != null && sourceData.length > 0) {
			int name = 0, tel = 0, address = 0, birthday = 0, email = 0, gender = 0, idCardNumber = 0,sc=0;
			for (int i = 0; i < sourceData[0].length; i++) {
				String s = sourceData[0][i];
				if (s != null && !"".equals(s.trim())) {
					if (s.contains("姓名") || s.contains("名字"))
						name = i;
					else if (s.contains("电话") || s.contains("手机号码"))
						tel = i;
					else if (s.contains("生日") || s.contains("出生日期"))
						birthday = i;
					else if (s.contains("住址") || s.contains("寝室") || s.contains("地址"))
						address = i;
					else if (s.contains("邮箱") || s.contains("电子邮箱"))
						email = i;
					else if (s.contains("性别"))
						gender = i;
					else if (s.contains("证件号码") || s.contains("身份证号"))
						idCardNumber = i;
					else if (s.contains("学校") || s.contains("毕业学校"))
						sc = i;
				}
			}
			Staff staff = null;
			for (int i = 1; i < sourceData.length; i++) {
				String[] s = sourceData[i];
				int j = 0;
				for (j = 0; s != null && j < s.length && StringUtils.isBlank(s[j]); j++);
				if (j < s.length) {
					staff = new Staff();
					staff.setName(sourceData[i][name]);
					staff.setTel(sourceData[i][tel]);
					staff.setAddress(sourceData[i][address]);
					staff.setEmail(sourceData[i][email]);
					staff.setGender(sourceData[i][gender]);
					staff.setIdCardNumber(sourceData[i][idCardNumber]);
					staff.setBirthday(sourceData[i][birthday]);
					staff.setSchool(sourceData[i][sc]);
					staff.setPassword("666666");
					staff.setIdentifier(Staff.ORDINARY_STAFF);
					list.add(staff);
				}
			}
			System.gc();
		}

		return list;
	}
	
	
	public static Staff cloneStaff(Staff origStaff){
		Staff staff = new Staff();
		try{
			BeanUtils.copyProperties(staff, origStaff);
			BeanUtils.cloneBean(origStaff);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}
}
