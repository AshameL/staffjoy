package xyz.staffjoy.faraday.core.mappings;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import xyz.staffjoy.faraday.config.FaradayProperties;
import xyz.staffjoy.faraday.config.MappingProperties;
import xyz.staffjoy.faraday.core.http.HttpClientProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基于静态配置的MappingProvider
 */
public class ConfigurationMappingsProvider extends MappingsProvider {

    public ConfigurationMappingsProvider(
            ServerProperties serverProperties,
            FaradayProperties faradayProperties,
            MappingsValidator mappingsValidator,
            HttpClientProvider httpClientProvider
    ) {
        super(serverProperties, faradayProperties,
                mappingsValidator, httpClientProvider);
    }

    /**
     * 基于配置文件的不需要动态更新。
     * 返回false
     * @param request
     * @return
     */
    @Override
    protected boolean shouldUpdateMappings(HttpServletRequest request) {
        return false;
    }

    @Override
    protected List<MappingProperties> retrieveMappings() {
        return faradayProperties.getMappings().stream()
                .map(MappingProperties::copy)
                .collect(Collectors.toList());
    }
}
