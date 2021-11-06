package top.nekoit.springboot.permission.configuration

import top.nekoit.springboot.permission.enum.AuthorizationType
import top.nekoit.springboot.permission.handler.*
import top.nekoit.springboot.permission.role.UserRole

/**
@author HaotianTu
create at 2021/10/31
 **/
@Suppress("RedundantVisibilityModifier")
class NekoPermissionConfiguration {

    // Handler of permission denied
    public var handler: PermissionHandler = DefaultPermissionHandler()

    // If check permission in default
    public var defaultPermission = false

    // Support method of permission. (SESSION, HEADER)
    public var allowType = arrayOf(AuthorizationType.SESSION, AuthorizationType.HEADER_AUTHORIZATION)

    // Handler of session checker
    public var sessionHandler = DefaultSessionHandler()

    // Handler of header checker
    public var headerHandler = DefaultHeaderHandler()

    // Header name of token
    public var headerName = "Authorization"


}