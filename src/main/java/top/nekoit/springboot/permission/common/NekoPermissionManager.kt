package top.nekoit.springboot.permission.common

import org.springframework.stereotype.Component
import top.nekoit.springboot.permission.configuration.NekoPermissionConfiguration
import top.nekoit.springboot.permission.role.UserRole
import javax.annotation.Resource
import javax.servlet.http.HttpSession

/**
@author HaotianTu
create at 2021/11/6
 **/

class NekoPermissionManager {

    @Resource
    private lateinit var nekoPermissionConfiguration: NekoPermissionConfiguration

    /**
     * Same as setSessionRole()
     */
    fun setRole(session: HttpSession, role: UserRole) {
        setSessionRole(session, role)
    }

    /**
     * Same as getRole()
     */
    fun getRole(session: HttpSession): UserRole {
        return getSessionRole(session)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun setSessionRole(session: HttpSession, role: UserRole) {
        nekoPermissionConfiguration.sessionHandler.setRole(session, role)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun getSessionRole(session: HttpSession): UserRole {
        return nekoPermissionConfiguration.sessionHandler.getRole(session)
    }

    fun setHeaderRole(token: String, role: UserRole) {
        nekoPermissionConfiguration.headerHandler.setRole(token, role)
    }

    fun getHeaderRole(token: String): UserRole {
        return nekoPermissionConfiguration.headerHandler.getRole(token)
    }

}