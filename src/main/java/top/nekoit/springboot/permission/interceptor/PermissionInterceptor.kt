package top.nekoit.springboot.permission.interceptor

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import top.nekoit.springboot.permission.annotation.PermissionCheck
import top.nekoit.springboot.permission.configuration.NekoPermissionConfiguration
import top.nekoit.springboot.permission.enum.AuthorizationType
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
@author HaotianTu
create at 2021/10/16
 **/
class PermissionInterceptor : HandlerInterceptor {

    @Resource
    private lateinit var config: NekoPermissionConfiguration

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        if (handler.bean is BasicErrorController) {
            return true
        }
        if (!handler.hasMethodAnnotation(PermissionCheck::class.java)) {
            return !config.defaultPermission
        }

        val permissionCheck = handler.getMethodAnnotation(PermissionCheck::class.java)!!
        if (!permissionCheck.enable) return true

        if (config.allowType.contains(AuthorizationType.SESSION)) {
            val session = request.session
            val role = config.sessionHandler.getRole(session)
            if (permissionCheck.allowRole.contains(role.name)) {
                return true
            }
        }

        if (config.allowType.contains(AuthorizationType.HEADER_AUTHORIZATION)) {
            val token = request.getHeader(config.headerName)
            token?.let {
                val role = config.headerHandler.getRole(token)
                if (permissionCheck.allowRole.contains(role.name)) {
                    return true
                }
            }
        }

        return config.handler.onPermissionDenied(request, response, handler)
    }
}