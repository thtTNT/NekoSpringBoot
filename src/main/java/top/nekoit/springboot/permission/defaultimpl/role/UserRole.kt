package top.nekoit.springboot.permission.defaultimpl.role

/**
@author HaotianTu
create at 2021/11/1
 **/
class UserRole(val type: UserType, val id: Int?) {
    companion object {
        private val anonymous = UserRole(UserType.ANONYMOUS, null)

        fun anonymous(): UserRole {
            return anonymous
        }

    }
}