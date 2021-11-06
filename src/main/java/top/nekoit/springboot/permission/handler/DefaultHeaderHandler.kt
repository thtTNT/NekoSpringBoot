package top.nekoit.springboot.permission.handler

import top.nekoit.springboot.permission.role.UserRole
import java.util.*
import kotlin.collections.HashMap

/**
@author HaotianTu
create at 2021/11/6
 **/
class DefaultHeaderHandler : HeaderHandler {

    private val roleMap = HashMap<String, UserRole>()

    override fun generateToken(): String {
        return UUID.randomUUID().toString()
    }

    override fun setRole(token: String, role: UserRole) {
        roleMap[token] = role
    }

    override fun getRole(token: String): UserRole {
        return roleMap[token] ?: UserRole.anonymous()
    }


}