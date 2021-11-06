package top.nekoit.springboot.permission.role

import java.util.*

/**
@author HaotianTu
create at 2021/11/1
 **/
class UserRole(val name: String) {
    companion object {
        private val anonymous = of("anonymous")
        private val default = of("default")

        fun anonymous(): UserRole {
            return anonymous
        }

        fun default(): UserRole {
            return default
        }

        fun of(name: String): UserRole {
            return UserRole(name)
        }
    }
}