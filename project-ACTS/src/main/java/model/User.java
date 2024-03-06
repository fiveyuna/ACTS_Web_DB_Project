package model;

import java.util.Date;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. ACCOUNT 테이블과 대응됨
 */
public class User {
	private int userId;
	private String accountId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String regNum; // 주민 번호
	private Date joinDate;
	private String nickName;

	public User() {
	} // 기본 생성자

	public User(int userId, String accountId, String password, String name, String email, String phone, String regNum,
			Date joinDate, String nickName) {
		this.userId = userId;
		this.accountId = accountId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.regNum = regNum;
		this.joinDate = joinDate;
		this.nickName = nickName;
	}
	
	public User(String accountId, String password, String name, String email, String phone, String regNum,
			Date joinDate, String nickName) {
		this.accountId = accountId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.regNum = regNum;
		this.joinDate = joinDate;
		this.nickName = nickName;
	}
	
	// password 뺀 버전 (findUser에 이용)
	public User(String accountId, String name, String email, String phone, String regNum,
			Date joinDate, String nickName) {
		this.accountId = accountId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.regNum = regNum;
		this.joinDate = joinDate;
		this.nickName = nickName;
	}

	// joinDate없는 생성자(회원가입용)
	public User(String accountId, String password, String name, String email, String phone, String regNum,
			String nickName) {
		this.accountId = accountId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.regNum = regNum;
		this.nickName = nickName;
	}
	
	//(user update용)
	public User(String accountId, String password, String name, String email, String phone, String nickName) {
		this.accountId = accountId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.nickName = nickName;
	}
	
	

	
//	 public void update(User updateUser) { 
//		 this.password = updateUser.password;
//		 this.name = updateUser.name; 
//		 this.email = updateUser.email; 
//		 this.phone = updateUser.phone; 
//		 this.nickName = updateUser.nickName; 
//	 }
//	 
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}

	public boolean isSameUser(String accountId) {
		return this.accountId.equals(accountId);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + "accountId=" + accountId + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", regNum=" + regNum + ", joinDate=" + joinDate
				+ ", nickName=" + nickName + "]";
	}
}