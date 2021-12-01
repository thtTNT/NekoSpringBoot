@file:Suppress("UNCHECKED_CAST")

package top.nekoit.springboot.permission.configuration

import top.nekoit.springboot.permission.defaultimpl.handler.SessionPermissionHandler
import top.nekoit.springboot.permission.handler.*

/**
@author HaotianTu
create at 2021/10/31
 **/
@Suppress("RedundantVisibilityModifier")
class NekoPermissionConfiguration {

    // Handler of permission denied
    public var handler: PermissionHandler<Any> = SessionPermissionHandler() as PermissionHandler<Any>

}