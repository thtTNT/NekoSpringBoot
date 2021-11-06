package top.nekoit.springboot.permission.handler

import top.nekoit.springboot.permission.role.UserRole

/**
@author HaotianTu
create at 2021/11/1
 **/
interface HeaderHandler {

    fun generateToken(): String

    fun setRole(token: String, role: UserRole)

    fun getRole(token: String) : UserRole
}