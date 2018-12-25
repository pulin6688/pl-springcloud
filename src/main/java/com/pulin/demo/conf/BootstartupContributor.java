package com.pulin.demo.conf;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by pulin on 2018/6/30.
 */
@Component
@Scope("singleton")
public class BootstartupContributor implements InfoContributor {

    private static final Logger logger = LoggerFactory.getLogger(BootstartupContributor.class);

    @Autowired
    private Environment environment;

    private Date date;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public BootstartupContributor(){
        date = new Date();
    }

    public void contribute(Info.Builder builder) {
        try{
            String active = environment.getProperty("spring.profiles.active");
            String projectName = environment.getProperty("spring.application.name");

            if(Objects.nonNull(sdf) && Objects.nonNull(date)){
                builder.withDetail("startup-completed-time", sdf.format(date));
            }
            if(StringUtils.isNotBlank(projectName)){
                builder.withDetail("projectName", projectName);
            }
            if(StringUtils.isNotBlank(active)){
                builder.withDetail("active", active);
            }

            //builder.withDetail("time", Collections.singletonMap("startup-completed-time",sdf.format(date)));
            //builder.withDetail("build", Maps.newHashMap());
        }catch(Exception e){
            logger.error("",e);
        }
    }
}
