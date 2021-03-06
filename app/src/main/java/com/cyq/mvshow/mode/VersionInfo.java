package com.cyq.mvshow.mode;

/**
 * Created by win7 on 2016/11/1.
 */

public class VersionInfo {

    /**
     * name : O2Player
     * version : 73
     * changelog : version:0.67
     1.优化了播放和暂停歌曲的功能。
     2.修正和优化了切换播放模式的功能。
     3.增加了对过期的账号播放歌曲时的处理。
     4.替换了友盟的自动更新，添加了友盟的消息推送功能。
     5.修正了给海外版MP添加闹钟的功能。
     6.修改已知的bug。
     * updated_at : 1475033860
     * versionShort : 0.67
     * build : 73
     * installUrl : http://download.fir.im/v2/app/install/56091a23f2fc421b1a000013?download_token=4a9a5520d1410449092ccfb5b1097a33
     * install_url : http://download.fir.im/v2/app/install/56091a23f2fc421b1a000013?download_token=4a9a5520d1410449092ccfb5b1097a33
     * direct_install_url : http://download.fir.im/v2/app/install/56091a23f2fc421b1a000013?download_token=4a9a5520d1410449092ccfb5b1097a33
     * update_url : http://fir.im/h541
     * binary : {"fsize":6280460}
     */

    private String name;
    private String version;
    private String changelog;
    private int updated_at;
    private String versionShort;
    private String build;
    private String installUrl;
    private String install_url;
    private String direct_install_url;
    private String update_url;
    /**
     * fsize : 6280460
     */

    private BinaryBean binary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getInstall_url() {
        return install_url;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }

    public String getDirect_install_url() {
        return direct_install_url;
    }

    public void setDirect_install_url(String direct_install_url) {
        this.direct_install_url = direct_install_url;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public BinaryBean getBinary() {
        return binary;
    }

    public void setBinary(BinaryBean binary) {
        this.binary = binary;
    }

    public static class BinaryBean {
        private int fsize;

        public int getFsize() {
            return fsize;
        }

        public void setFsize(int fsize) {
            this.fsize = fsize;
        }
    }
}
