package cn.nicollcheng.ip2region;


import org.nutz.plugins.ip2region.DBReader;
import org.nutz.plugins.ip2region.DbConfig;
import org.nutz.plugins.ip2region.DbMakerConfigException;
import org.nutz.plugins.ip2region.DbSearcher;
import org.nutz.plugins.ip2region.impl.ByteArrayDBReader;
import org.nutz.plugins.ip2region.impl.RandomAccessFileDBReader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/8/3 9:47.
 */
@ConditionalOnClass(value = {Ip2RegionProperties.class, Ip2regionTemplate.class, DbSearcher.class,})
@EnableConfigurationProperties(Ip2RegionProperties.class)
public class Ip2RegionAutoConfiguration implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    private final Ip2RegionProperties properties;

    public Ip2RegionAutoConfiguration(Ip2RegionProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Ip2regionTemplate ip2regionTemplate() throws IOException, DbMakerConfigException {

        DbSearcher dbSearcher = null;
        if (properties.isExternal()) {

            DBReader reader = null;
            // load external resource
            Resource resource = resourceLoader.getResource("file:/" + properties.getLocation());

            if (resource.isFile() && resource.exists()) {
                reader = new RandomAccessFileDBReader(new RandomAccessFile(resource.getFile(), "r"));
            } else {

                ByteArrayOutputStream output = new ByteArrayOutputStream();
                FileCopyUtils.copy(resource.getInputStream(), output);
                reader = new ByteArrayDBReader(output.toByteArray());

            }

            DbConfig dbConfig = new DbConfig(properties.getTotalHeaderSize());
            dbConfig.setIndexBlockSize(properties.getIndexBlockSize());
            dbSearcher = new DbSearcher(dbConfig, reader);

        } else {
            dbSearcher = new DbSearcher();
        }

        return new Ip2regionTemplate(dbSearcher);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
