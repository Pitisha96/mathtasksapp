package by.itransition.mathtasksapp.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

    @Value("${CLOUDINARY_CLOUD_NAME}")
    private String cloud;

    @Value("${CLOUDINARY_API_KEY}")
    private String key;

    @Value("${CLOUDINARY_API_SECRET}")
    private String secret;

    @Bean
    public Cloudinary getCloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cloud,
                "api_key",key,
                "api_secret",secret
        ));
    }
}
