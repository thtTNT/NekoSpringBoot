package top.nekoit.springboot.permission.defaultimpl.handler

import org.springframework.http.HttpStatus
import org.springframework.web.method.HandlerMethod
import top.nekoit.springboot.permission.defaultimpl.annotation.PermissionCheck
import top.nekoit.springboot.permission.defaultimpl.role.UserRole
import top.nekoit.springboot.permission.handler.PermissionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
@author HaotianTu
create at 2021/10/31
 **/
class SessionPermissionHandler : PermissionHandler<UserRole> {

    companion object {
        const val PERMISSION_SESSION_KEY = "neko.permission"
    }

    override fun onPermissionDenied(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handlerMethod: HandlerMethod
    ): Boolean {
        response.status = HttpStatus.FORBIDDEN.value()
        return false
    }

    override fun analysisRequest(request: HttpServletRequest): UserRole {
        val session = request.session
        return (session.getAttribute(PERMISSION_SESSION_KEY) as UserRole?) ?: UserRole.anonymous()
    }

    override fun checkPermission(request: HttpServletRequest, role: UserRole, handlerMethod: HandlerMethod): Boolean {
        val permissionCheck = handlerMethod.getMethodAnnotation(PermissionCheck::class.java) ?: return false
        if (!permissionCheck.enable) return true
        return permissionCheck.allowType.contains(role.type)
    }

    override fun setRole(request: HttpServletRequest, role: UserRole) {
        val session = request.session
        session.setAttribute(PERMISSION_SESSION_KEY, role)
    }

    override fun removeRole(request: HttpServletRequest, role: UserRole) {
        val session = request.session
        session.removeAttribute(PERMISSION_SESSION_KEY)
    }

}