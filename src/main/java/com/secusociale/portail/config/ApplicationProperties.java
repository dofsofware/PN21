package com.secusociale.portail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Portail Css Ipres V 2.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private String docdir;
    private String docuri;
    private String ftpaddress;
    private int ftpport;
    private String ftplogin;
    private String ftppass;
    private String ftppath;

    public String getDocdir() {
        return docdir;
    }

    public void setDocdir(String docdir) {
        this.docdir = docdir;
    }

    public String getDocuri() {
        return docuri;
    }

    public void setDocuri(String docuri) {
        this.docuri = docuri;
    }

    public String getFtpaddress() {
        return ftpaddress;
    }

    public void setFtpaddress(String ftpaddress) {
        this.ftpaddress = ftpaddress;
    }

    public int getFtpport() {
        return ftpport;
    }

    public void setFtpport(int ftpport) {
        this.ftpport = ftpport;
    }

    public String getFtplogin() {
        return ftplogin;
    }

    public void setFtplogin(String ftplogin) {
        this.ftplogin = ftplogin;
    }

    public String getFtppass() {
        return ftppass;
    }

    public void setFtppass(String ftppass) {
        this.ftppass = ftppass;
    }

    public String getFtppath() {
        return ftppath;
    }

    public void setFtppath(String ftppath) {
        this.ftppath = ftppath;
    }
}
