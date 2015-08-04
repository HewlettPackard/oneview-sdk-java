package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class RefreshStateConfig implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private RefreshForceOptions refreshForceOptions;
    private RefreshState refreshState;

    public class RefreshForceOptions {
        private String address;
        private String password;
        private String username;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public RefreshForceOptions getRefreshForceOptions() {
        return refreshForceOptions;
    }

    public void setRefreshForceOptions(RefreshForceOptions refreshForceOptions) {
        this.refreshForceOptions = refreshForceOptions;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public RefreshForceOptions getNewRefreshForceOptions() {
        return new RefreshForceOptions();
    }

}
