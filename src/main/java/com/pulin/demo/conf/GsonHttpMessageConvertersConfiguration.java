package com.pulin.demo.conf;

import com.google.common.collect.Lists;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

//@Configuration
//@ConditionalOnProperty(name = "conf.json.flag",havingValue = "true",matchIfMissing = true)
public class GsonHttpMessageConvertersConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GsonHttpMessageConvertersConfiguration.class);


    /*
     * GsonHttpMessageConverter 转换配置
     */
    @Bean
    public HttpMessageConverters gsonHttpMessageConverters() {
        logger.info("GsonHttpMessageConvertersConfiguration init........");
        Collection<HttpMessageConverter<?>> messageConverters = new ArrayList();
        // Gson 配置
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setSupportedMediaTypes( Lists.newArrayList(MediaType.ALL) );
        Gson gson = new GsonBuilder().registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter()).create();
        gsonHttpMessageConverter.setGson(gson);
        messageConverters.add(gsonHttpMessageConverter);

//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        messageConverters.add(mappingJackson2HttpMessageConverter);

        return new HttpMessageConverters(true, messageConverters);
    }


    public static class SpringfoxJsonToGsonAdapter implements JsonSerializer<Json> {
        @Override
        public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonParser parser = new JsonParser();
            return parser.parse(src.value());
        }
    }
}
