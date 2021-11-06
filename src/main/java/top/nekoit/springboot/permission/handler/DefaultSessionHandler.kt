package top.nekoit.springboot.permission.handler

import top.nekoit.springboot.permission.role.UserRole
import javax.servlet.http.HttpSession

/**
@author HaotianTu
create at 2021/11/1
 **/
class DefaultSessionHandler : SessionHandler {

    override fun getRole(session: HttpSession): UserRole {
        val role = session.getAttribute("neko.permission.role.name") ?: return UserRole.anonymous()
        return UserRole.of(role as String)
    }

    override fun setRole(session: HttpSession, role: UserRole) {
        session.setAttribute("neko.permission.role.name", role.name)
    }
}