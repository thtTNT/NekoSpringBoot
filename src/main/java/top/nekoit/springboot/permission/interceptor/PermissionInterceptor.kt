package top.nekoit.springboot.permission.interceptor

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import top.nekoit.springboot.permission.configuration.NekoPermissionConfiguration
import top.nekoit.springboot.permission.handler.PermissionHandler
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

        val role = config.handler.analysisRequest(request)
        request.setAttribute("role", role)

        return if (config.handler.checkPermission(request, role, handler)){
            true
        }else {
            config.handler.onPermissionDenied(request,response,handler)
            false
        }
    }
}