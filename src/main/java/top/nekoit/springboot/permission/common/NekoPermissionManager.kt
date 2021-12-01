package top.nekoit.springboot.permission.common

import top.nekoit.springboot.permission.configuration.NekoPermissionConfiguration
import top.nekoit.springboot.permission.defaultimpl.role.UserRole
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
@author HaotianTu
create at 2021/11/6
 **/

class NekoPermissionManager {

    @Resource
    private lateinit var nekoPermissionConfiguration: NekoPermissionConfiguration

    fun setRole(request: HttpServletRequest, role: Any) {
        nekoPermissionConfiguration.handler.setRole(request, role)
    }

}