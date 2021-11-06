package top.nekoit.springboot.permission.handler

import org.springframework.http.HttpStatus
import org.springframework.web.method.HandlerMethod
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
@author HaotianTu
create at 2021/10/31
 **/
class DefaultPermissionHandler : PermissionHandler {

    override fun onPermissionDenied(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handlerMethod: HandlerMethod
    ): Boolean {
        response.status = HttpStatus.FORBIDDEN.value()
        return false
    }

}