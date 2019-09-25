package com.nut2014.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.nut2014.config")
public class ConfigResource {
    private String serverHost = "";
    private String fileUploadPath;
    private String fileLocation;

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }


    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getDefaultAvatarPath() {
        return getServerHost()+"/image/default_avator.png";
    }
    public String getDefaulUserBg() {
        return getServerHost()+"/image/cover_bg.jpeg";
    }
}
