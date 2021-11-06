# 权限模块

权限模块用于快速构建一个基于SpringBoot的权限验证系统，支持通过Session和Header两种方式进行验证，暂时只支持本机。

## 安装

模块可通过maven进行安装。

```xml

<project>
    <repositories>
        <repository>
            <id>neko-maven-release</id>
            <url>http://maven.nekoit.top/release/</url>
        </repository>
        <!--其他Repository-->
    </repositories>
    <dependencies>
        <dependency>
            <groupId>top.nekoit</groupId>
            <artifactId>spring-boot-neko-permission-starter</artifactId>
            <version>1.0</version>
        </dependency>
        <!--其他Dependence-->
    </dependencies>
</project>
```

## 快速入门

框架在添加之后默认不会对程序产生任何影响，若要让框架生效可以在Controller的方法中添加@PermissionCheck的注解。

```kotlin
    @GetMapping("HelloWorld")
@PermissionCheck(enable = true, allowRole = ["default"])
fun helloWorld(): String {
    // do something
}
```

上面的注解表示对于该方法启动权限检查，仅对于"default"角色的访问予以通过。
- --
对于权限设置需要现在Controller中注入NekoPermissionManager类

```kotlin
@RestController
class Controller {

    @Resource
    private lateinit var nekoPermissionManager: NekoPermissionManager

}
```

通过调用方法 NekoPermissionManager.setSessionRole() 或 NekoPermissionManager.setHeaderRole()来对请求进行角色的设置

```kotlin
@GetMapping("setRole")
fun setRole(
    session: HttpSession,
    role: String
) {
    // 以下为Session设置角色
    nekoPermissionManager.setSessionRole(session,UserRole.default())
    // 以下为Header设置角色
    val token = UUID.randomUUID().toString()
    nekoPermissionManager.setHeaderRole(token, UserRole.default())
}
```
## 执行器
为了提供更加好的可拓展性，框架可通过执行器来更好的自定插件的功能，这里简单介绍三个。
#### 权限执行器
```kotlin
interface PermissionHandler {
    
    // 在权限验证失败的时候处理返回
    fun onPermissionDenied(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handlerMethod: HandlerMethod
    ): Boolean
    
}
```
#### SESSION验证执行器
```kotlin
interface SessionHandler {

    fun getRole(session: HttpSession) : UserRole

    fun setRole(session: HttpSession, role: UserRole)
}
```
#### HEADER验证执行器
```kotlin
interface HeaderHandler {

    fun generateToken(): String

    fun setRole(token: String, role: UserRole)

    fun getRole(token: String) : UserRole
}
```
---
在创建完成自定义的执行器之后可以通过自定义Bean方式覆盖默认的配置类。
```kotlin
@Configuration
open class Configuration {

    @Bean
    open fun getNekoPermissionConfiguration(): NekoPermissionConfiguration {
        return NekoPermissionConfiguration().apply { 
            this.handler = myPermissionHandler
            this.sessionHandler = mySessionHandler
            this.headerHandler = myHeaderHandler
        }
    }
    
}
```
## 关于配置类

```kotlin
class NekoPermissionConfiguration {

    // 处理当权限验证失败时的返回
    var handler: PermissionHandler = DefaultPermissionHandler()

    // 是否默认开始权限验证
    var defaultPermission = false

    // 支持的验证方法
    var allowType = arrayOf(AuthorizationType.SESSION, AuthorizationType.HEADER_AUTHORIZATION)

    // SESSION验证执行器
    var sessionHandler = DefaultSessionHandler()

    // Header验证执行器
    var headerHandler = DefaultHeaderHandler()

    // Header验证时使用的Header名字
    var headerName = "Authorization"

}
```