package top.nekoit.springboot.permission.handler

import top.nekoit.springboot.permission.role.UserRole
import javax.servlet.http.HttpSession

/**
@author HaotianTu
create at 2021/10/31
 **/
interface SessionHandler {

    fun getRole(session: HttpSession) : UserRole

    fun setRole(session: HttpSession, role: UserRole)
}