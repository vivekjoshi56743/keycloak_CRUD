package com.vivek.keycloak.entity;

import java.util.Map;

public class User {
    private String id;
    private long createdTimestamp;
    private String username;
    private boolean enabled;
    private boolean totp;
    private boolean emailVerified;
    private String firstName;
    private String lastName;
    private String email;
    private String[] disableableCredentialTypes;
    private String[] requiredActions;
    private int notBefore;
    private Map<String, Boolean> access;

    

    public User() {
    }

    public User(String id, long createdTimestamp, String username, boolean enabled, boolean totp,
                boolean emailVerified, String firstName, String lastName, String email,
                String[] disableableCredentialTypes, String[] requiredActions, int notBefore,
                Map<String, Boolean> access) {
        this.id = id;
        this.createdTimestamp = createdTimestamp;
        this.username = username;
        this.enabled = enabled;
        this.totp = totp;
        this.emailVerified = emailVerified;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.disableableCredentialTypes = disableableCredentialTypes;
        this.requiredActions = requiredActions;
        this.notBefore = notBefore;
        this.access = access;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(long createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isTotp() {
		return totp;
	}

	public void setTotp(boolean totp) {
		this.totp = totp;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getDisableableCredentialTypes() {
		return disableableCredentialTypes;
	}

	public void setDisableableCredentialTypes(String[] disableableCredentialTypes) {
		this.disableableCredentialTypes = disableableCredentialTypes;
	}

	public String[] getRequiredActions() {
		return requiredActions;
	}

	public void setRequiredActions(String[] requiredActions) {
		this.requiredActions = requiredActions;
	}

	public int getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(int notBefore) {
		this.notBefore = notBefore;
	}

	public Map<String, Boolean> getAccess() {
		return access;
	}

	public void setAccess(Map<String, Boolean> access) {
		this.access = access;
	}
    
    
    
}