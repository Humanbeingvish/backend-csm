package com.cognicx.AppointmentRemainder.Dto;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

 
public class UserDto{
 
	public BigInteger autogenUsersId;
	public String autogenUsersDetailsId;
	public BigInteger inventoryCategoryId;
	public String inventoryCategoryName;
	public String email;
	public String employeeId;
	public String firstName;
	public String lastName;
	public int loginAttempt;
	public String mobileNumber;
	public String password;
	public Date recAddDt;
	public Date recUpdateDt;
	public String status;
	public String supervisorUsersName;
    public Collection<? extends GrantedAuthority> authorities;
    public String supervisorUsersId;    
    public BigInteger autogenRolesId;
    public String createdBy;
    public String updatedBy;
    public String rolesName;
    public List<Object[]> resultObjList;
    public Object resultObj;
	// public List<SurveyTypeDto> surveyTypes;
    public String userName;
    public UserDto() {}

	public UserDto(UserDto userDto) {
		this.autogenUsersId = userDto.autogenUsersId;
		this.autogenUsersDetailsId = userDto.autogenUsersDetailsId;
		this.inventoryCategoryId = userDto.inventoryCategoryId;
		this.inventoryCategoryName = userDto.inventoryCategoryName;
		this.email = userDto.email;
		this.employeeId = userDto.employeeId;
		this.firstName = userDto.firstName;
		this.lastName = userDto.lastName;
		this.loginAttempt = userDto.loginAttempt;
		this.mobileNumber = userDto.mobileNumber;
		this.password = userDto.password;
		this.recAddDt = userDto.recAddDt;
		this.recUpdateDt = userDto.recUpdateDt;
		this.status = userDto.status;
		this.supervisorUsersName = userDto.supervisorUsersName;
		this.authorities = userDto.getAuthorities();
		this.supervisorUsersId = userDto.getSupervisorUsersId();
		this.autogenRolesId = userDto.getAutogenRolesId();
		this.rolesName = userDto.getRolesName();
		// this.surveyTypes=userDto.surveyTypes;
		this.userName=userDto.getUserName();
	}
    
    
    
	public BigInteger getAutogenUsersId() {
		return autogenUsersId;
	}
	public void setAutogenUsersId(BigInteger autogenUsersId) {
		this.autogenUsersId = autogenUsersId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getLoginAttempt() {
		return loginAttempt;
	}
	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRecAddDt() {
		return recAddDt;
	}
	public void setRecAddDt(Date recAddDt) {
		this.recAddDt = recAddDt;
	}
	public Date getRecUpdateDt() {
		return recUpdateDt;
	}
	public void setRecUpdateDt(Date recUpdateDt) {
		this.recUpdateDt = recUpdateDt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public String getSupervisorUsersId() {
		return supervisorUsersId;
	}

	public void setSupervisorUsersId(String supervisorUsersId) {
		this.supervisorUsersId = supervisorUsersId;
	}

	public String getAutogenUsersDetailsId() {
		return autogenUsersDetailsId;
	}

	public void setAutogenUsersDetailsId(String autogenUsersDetailsId) {
		this.autogenUsersDetailsId = autogenUsersDetailsId;
	}

	public BigInteger getInventoryCategoryId() {
		return inventoryCategoryId;
	}

	public void setInventoryCategoryId(BigInteger inventoryCategoryId) {
		this.inventoryCategoryId = inventoryCategoryId;
	}

	public BigInteger getAutogenRolesId() {
		return autogenRolesId;
	}

	public void setAutogenRolesId(BigInteger autogenRolesId) {
		this.autogenRolesId = autogenRolesId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRolesName() {
		return rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}
	
	public String getInventoryCategoryName() {
		return inventoryCategoryName;
	}

	public void setInventoryCategoryName(String inventoryCategoryName) {
		this.inventoryCategoryName = inventoryCategoryName;
	}

	public String getSupervisorUsersName() {
		return supervisorUsersName;
	}

	public void setSupervisorUsersName(String supervisorUsersName) {
		this.supervisorUsersName = supervisorUsersName;
	}


	public List<Object[]> getResultObjList() {
		return resultObjList;
	}

	public void setResultObjList(List<Object[]> resultObjList) {
		this.resultObjList = resultObjList;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}




	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



}