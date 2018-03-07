# 目录
- Github
- Keycloak配置
- Spring Security集成Keycloak
- 启动Keycloak客户端
- SSO授权码模式访问过程
- 总结

在企业众多的应用系统中，如果每个应用都有独立的用户认证和权限管理，这不仅需要维护多套用户管理系统，用户使用每个系统也非常的不便。如果能够将所有应用系统的用户集中管理，用户使用一套用户名登录所有系统，将会大大改善用户体验。下面将基于Keycloak搭建单点登录系统。

# GitHub
- https://github.com/pjhu/keycloak

# Keycloak Setup
- 创建Client: login-app, login-backup
![](http://upload-images.jianshu.io/upload_images/5511393-c30a1fb76c1a23de.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/620)
- 创建client Roles
![](http://upload-images.jianshu.io/upload_images/5511393-d8525d031772b45c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/620)
- 创建用户, 并分配角色
![](http://upload-images.jianshu.io/upload_images/5511393-19f111f198641da0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/620)
如果需要获取所有用户信息，配置realm-management
![](http://upload-images.jianshu.io/upload_images/5511393-a76a7413fbf022b3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/620)
- 启动Keycloak standalone 模式，端口号为8180
    ```
    ./standalone.sh -Djboss.socket.binding.port-offset=100
    ```
    如果需要配置连接postgresql，请参考[Github](https://github.com/pjhu/keycloak)
# Spring Security集成Keycloak
- 配置application.properties
    ```
    keycloak.realm=demo
    keycloak.resource=login-app
    keycloak.auth-server-url=http://localhost:8180/auth
    keycloak.ssl-required=external
    keycloak.public-client=true
    keycloak.use-resource-role-mappings=true
    keycloak.confidential-port=0
    keycloak.principal-attribute=preferred_username
    ```
    **其中keycloak.resource根据不同的client配置不同的变量**，内容请参考Keycloak client installation，如下图
    ![](http://upload-images.jianshu.io/upload_images/5511393-c5dbee4e696dee12.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/620)
# 启动Keycloak客户端
- 启动client: login-app, login-backup，端口号分别为8081，8082，**在keycloak client 配置页面 ‘Valid Redirect URIs’ 填写相应的端口号**
    ```
    java -Dserver.port=8081 -jar login-app.jar
    java -Dserver.port=8082 -jar login-backup.jar
    ```
# SSO授权码模式访问过程
![](http://upload-images.jianshu.io/upload_images/5511393-769fcb2629c0840b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 访问login-app，图中http://rp.example.com，当浏览器登录Keycloak(OP)后，会在浏览器保存KEYCLOAK_SESSION
- 当访问login-backup时，图中http://rp-2.example.com，浏览器将KEYCLOAK_SESSION一起请求Keycloak(OP), Keycloak(OP)根据KEYCLOAK_SESSION判断用户已登录，直接授权，不需要用户再次输入用户名和密码。
- 登录login-app页面跳转，包的抓取
![](http://upload-images.jianshu.io/upload_images/5511393-abb64ae9c6c28ded.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/620)


# 总结
- keycloak有对应的单点登出，通过postman请求测试
- 集成LDAP，测试可进行连接，用户属性Map，并可选择用户数据同步方式
- 使用keycloak docker时偶尔不能获取client role, 没有解决
- 使用keycloak docker时不能获取用户列表, 没有解决，即使配置了相应的获取权限
- keycloak session没有持久化的postgresql数据库中，没有解决




