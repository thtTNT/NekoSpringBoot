package top.nekoit.springboot.permission.annotation

import top.nekoit.springboot.permission.role.UserRole

/**
@author HaotianTu
create at 2021/10/31
 **/
annotation class PermissionCheck(
    val enable: Boolean = true,
    val allowRole: Array<String> = ["default"]
)