package cn.nicollcheng.ip.controller;

import cn.nicollcheng.ip.ip2region.RegionInfo;
import cn.nicollcheng.ip2region.Ip2regionTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nutz.plugins.ip2region.DataBlock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/8/2 15:18.
 */
@RestController
public class IpLocationController {

    private final Ip2regionTemplate ip2regionTemplate;

    public IpLocationController(Ip2regionTemplate ip2regionTemplate) {
        this.ip2regionTemplate = ip2regionTemplate;
    }

    @GetMapping("/ip2location")
    private String ip2location(String ip) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DataBlock dataBlock = ip2regionTemplate.memorySearch(ip);
        return mapper.writeValueAsString(RegionInfo.buildRegionInfo(dataBlock.getRegion()));
    }
}
