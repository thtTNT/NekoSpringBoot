package top.nekoit.springboot.permission.configuration

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import top.nekoit.springboot.permission.common.NekoPermissionManager
import top.nekoit.springboot.permission.interceptor.PermissionInterceptor
import javax.annotation.Resource

/**
@author HaotianTu
create at 2021/11/6
 **/

@Configuration
open class NekoPermissionAutoconfiguration : WebMvcConfigurer {

    @Resource
    private lateinit var permissionInterceptor: PermissionInterceptor

    @Bean
    open fun getNekoPermissionManager(): NekoPermissionManager {
        return NekoPermissionManager()
    }

    @Bean
    @ConditionalOnMissingBean
    open fun getNekoPermissionConfiguration(): NekoPermissionConfiguration {
        return NekoPermissionConfiguration()
    }

    @Bean
    open fun getPermissionInterceptor(): PermissionInterceptor {
        return PermissionInterceptor()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(permissionInterceptor)
            .addPathPatterns("/**")
    }
}