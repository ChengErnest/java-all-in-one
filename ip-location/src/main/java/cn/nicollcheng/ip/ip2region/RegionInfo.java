package cn.nicollcheng.ip.ip2region;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/8/2 17:50.
 */
public class RegionInfo {
    /**
     * 国家
     */
    private String country;
    /**
     * 区域
     */
    private String area;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 互联网服务供应商
     */
    private String isp;

    public String getCountry() {
        return country;
    }

    public RegionInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getArea() {
        return area;
    }

    public RegionInfo setArea(String area) {
        this.area = area;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public RegionInfo setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public RegionInfo setCity(String city) {
        this.city = city;
        return this;
    }

    public String getIsp() {
        return isp;
    }

    public RegionInfo setIsp(String isp) {
        this.isp = isp;
        return this;
    }

    public static RegionInfo buildRegionInfo(String ip2regionInfo){
        String[] ip2region = ip2regionInfo.split("\\|");
        RegionInfo regionInfo = new RegionInfo();
        return regionInfo.setCountry(ip2region[0])
                .setArea(ip2region[1])
                .setProvince(ip2region[2])
                .setCity(ip2region[3])
                .setIsp(ip2region[4]);
    }
}
