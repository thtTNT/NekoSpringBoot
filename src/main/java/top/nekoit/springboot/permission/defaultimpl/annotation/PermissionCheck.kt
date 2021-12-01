package top.nekoit.springboot.permission.defaultimpl.annotation

import top.nekoit.springboot.permission.defaultimpl.role.UserType

/**
@author HaotianTu
create at 2021/10/31
 **/
annotation class PermissionCheck(
    val enable: Boolean = true,
    val allowType: Array<UserType> = [UserType.ADMIN]
)