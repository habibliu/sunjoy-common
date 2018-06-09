package com.sunjoy.common.auth.dao.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sunjoy.framework.dao.BaseEntity;

public class User extends BaseEntity implements UserDetails {

	/**
	 * @serialField serialVersionUID
	 */
	private static final long serialVersionUID = 701772537439334555L;
	private String refId;
	private String refName;
	private String phone;
	private String telephone;
	private String address;
	private boolean enabled;
	private String username;
	private String password;
	private String remark;
	private List<Role> roles;
	private String userFace;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}



	public String getPhone() {
		return phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getAddress() {
		return address;
	}

	

	public String getRemark() {
		return remark;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public String getUserFace() {
		return userFace;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUserFace(String userFace) {
		this.userFace = userFace;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

}
