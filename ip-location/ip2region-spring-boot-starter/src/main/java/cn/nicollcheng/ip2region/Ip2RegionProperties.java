package cn.nicollcheng.ip2region;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/8/3 9:28.
 */
@ConfigurationProperties(prefix = "ip2region")
public class Ip2RegionProperties {
    /**
     * Whether to use an external IP data file.
     */
    private boolean external = false;
    /**
     * ip2region.db file default path,classpath:ip2region/ip2region.db
     */
    private String location = "classpath:ip2region/ip2region.db";

    /**
     * total header data block size, must be times of 8; default 8192
     */
    private int totalHeaderSize = 8192;
    /**
     * max index data block size u should always choice the fastest read block
     * size;default 4 * 1024 = 4096
     */
    private int indexBlockSize = 4096;

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalHeaderSize() {
        return totalHeaderSize;
    }

    public void setTotalHeaderSize(int totalHeaderSize) {
        this.totalHeaderSize = totalHeaderSize;
    }

    public int getIndexBlockSize() {
        return indexBlockSize;
    }

    public void setIndexBlockSize(int indexBlockSize) {
        this.indexBlockSize = indexBlockSize;
    }
}
