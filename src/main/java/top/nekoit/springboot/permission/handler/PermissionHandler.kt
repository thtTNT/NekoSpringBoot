package top.nekoit.springboot.permission.handler

import org.springframework.web.method.HandlerMethod
import java.lang.invoke.MethodHandle
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
@author HaotianTu
create at 2021/10/31
 **/
interface PermissionHandler<T : Any> {

    fun onPermissionDenied(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handlerMethod: HandlerMethod
    ): Boolean

    fun analysisRequest(request: HttpServletRequest): T

    fun checkPermission(request: HttpServletRequest, role: T, handlerMethod: HandlerMethod): Boolean

    fun setRole(request: HttpServletRequest, role: T)

    fun removeRole(request: HttpServletRequest, role: T)
}