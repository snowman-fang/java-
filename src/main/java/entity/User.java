package entity;

public class User {
	private int id;//主键id
	//用户名
   private String name;
	//用户密码
   private String password;
	//用户账号
   private String account;
	//用户角色   0-最高权限管理员   1-城市管理员      2-商户
	private String role;
	//用户手机号
	private String phone;
    //会员到期日期
	private String endDate;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPAccount() {
		return pAccount;
	}

	public void setPAccount(String pAccount) {
		this.pAccount = pAccount;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	//所在城市
	private String city;
	//负责人账号
	private String pAccount;
	//负责人名
	private String pName;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
}
