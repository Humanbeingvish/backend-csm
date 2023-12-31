package com.cognicx.AppointmentRemainder.Dto;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserPrinciple  implements UserDetails {
	private static final long serialVersionUID = 1L;

	public BigInteger autogenUsersId;
	public String email;
	public String employeeId;
	public String mobileNumber;
	private String userId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String password;
	public String status;
	//private Set<Roles> roles = new HashSet<>();
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(UserDto userDto) {

		this.autogenUsersId = userDto.getAutogenUsersId();
		this.email = userDto.getEmail();
		this.employeeId = userDto.getEmployeeId();
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		this.mobileNumber = userDto.getMobileNumber();
		this.password = userDto.getPassword();
		this.status = userDto.getStatus();
		this.authorities = userDto.getAuthorities();
		this.firstName=userDto.getFirstName();
		this.lastName=userDto.getLastName();
		this.password=userDto.getPassword();
	}

	public static UserPrinciple build(UserDto userDto) {
		return new UserPrinciple(userDto);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPrinciple other = (UserPrinciple) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
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


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}