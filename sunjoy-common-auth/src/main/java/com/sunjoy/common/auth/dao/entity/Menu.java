package com.sunjoy.common.auth.dao.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunjoy.framework.dao.BaseEntity;

public class Menu extends BaseEntity{
	
	private String url;
	
	private String path;
	
	private String component;
	
	private String name;
	
	private String iconCls;
	
	private Short keepAlive;
	
	private Short requireAuth;
	
	private String parentId;
	
	private Short enabled;
	
	private List<Role> roles;
	
	private List<Menu> children;
	
	private MenuMeta meta;

    
	@JsonIgnore
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return path;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	public String getComponent() {
		return component;
	}

	public String getName() {
		return name;
	}

	public String getIconCls() {
		return iconCls;
	}

	public Short getKeepAlive() {
		return keepAlive;
	}

	public Short getRequireAuth() {
		return requireAuth;
	}

	 @JsonIgnore
	public String getParentId() {
		return parentId;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setKeepAlive(Short keepAlive) {
		this.keepAlive = keepAlive;
	}

	public void setRequireAuth(Short requireAuth) {
		this.requireAuth = requireAuth;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	@JsonIgnore
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
	
	public MenuMeta getMeta() {
        return meta;
    }

    public void setMeta(MenuMeta meta) {
        this.meta = meta;
    }
}
