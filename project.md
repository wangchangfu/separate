# 项目介绍

1. RESTful API 
2. Maven集成mybatis plus（包含代码生成器）
3. Shiro + Java-JWT实现无状态鉴权机制(Token) 
4. 密码加密(采用AES-128 + Base64的方式) 
5. 集成Redis(Jedis) 
6. 重写Shiro缓存机制(Redis，不再使用传统session登录验证)
7. Redis中保存RefreshToken信息(做到JWT的可控性) 
8. 根据RefreshToken自动刷新AccessToken  

# 框架结构

整体框架采用maven module多模块搭建，模块任务明了，配置不再混乱

separate：项目父类

peoject-admin：项目主要module为整个框架核心，其中整合有mybatis、shiro、redis等。

project-core：项目核心配置，其主要目的是所有项目中所有核心配置以及工具类等

project-generator：代码生成器，根据数据表生成model，service，mapper，（controller暂无法生成）

# Shiro + Java-JWT实现无状态鉴权机制(Token) 

首先Post用户名与密码到login进行登入，如果成功返回一个加密的AccessToken，失败的话直接返回401错误(帐号或密码不正确)，以后访问都带上这个AccessToken即可，鉴权流程主要是重写了Shiro的入口过滤器JWTFilter(BasicHttpAuthenticationFilter)，判断请求
Header里面是否包含Authorization字段，有就进行Shiro的Token登录认证授权(用户访问每一个需要权限的请求必须在Header中添加Authorization字段存放AccessToken)，没有就以游客直接访问(有权限管控的话，以游客访问就会被拦截)

# 关于AES-128 + Base64当两个用户的明文密码相同时进行加密，会发现数据库中存在相同结构的暗文密码 

大部分是以MD5 + 盐的形式解决了这个问题(详细自己百度)，我采用AES-128 + Base64是以帐号+密码的形式进行加密密码，因为帐号具有唯一性，所以也不会出现相同结构的暗文密码这个问题

# 关于将Jedis工具类与SpringBoot整合 

本来是直接将JedisUtil注入为Bean，每次使用直接@Autowired注入使用即可，但是在重写Shiro的CustomCache无法注入JedisUtil，所以就改成静态注入JedisPool连接池，JedisUtil工具类还是直接调用静态方法，无需@Autowired注入

# 关于Redis中保存RefreshToken信息(做到JWT的可控性) 

登录认证通过后返回AccessToken信息(在AccessToken中保存当前的时间戳和帐号)，同时在Redis中设置一条以帐号为Key，Value为当前时间戳(登录时间)的RefreshToken，现在认证时必须AccessToken没失效以及Redis存在所对应的RefreshToken，且RefreshToken时间戳和AccessToken信息中时间戳一致才算认证通过，这样可以做到JWT的可控性，如果重新登录获取了新的AccessToken，旧的AccessToken就认证不了，因为Redis中所存放的的RefreshToken时间戳信息只会和最新的AccessToken信息中携带的时间戳一致，这样每个用户就只能使用最新的
AccessToken认证，Redis的RefreshToken也可以用来判断用户是否在线，如果删除Redis的某个RefreshToken，那这个RefreshToken所对应的AccessToken之后也无法通过认证了，就相当于控制了用户的登录，可以剔除用户

# 关于根据RefreshToken自动刷新AccessToken 

本身AccessToken的过期时间为5分钟(配置文件可配置)，RefreshToken过期时间为30分钟(配置文件可配置)，当登录后时间过了5分钟之后，当前AccessToken便会过期失效，再次带上AccessToken访问JWT会抛出TokenExpiredException异常说明Token过期，开始判断是否要进行AccessToken刷新，首先Redis查询RefreshToken是否存在，以及时间戳和过期AccessToken所携带的时间戳是否一致，如果存在且一致就
进行AccessToken刷新，过期时间为5分钟(配置文件可配置)，时间戳为当前最新时间戳，同时也设置RefreshToken中的时间戳为当前最新时间戳，刷新过期时间重新为30分钟过期(配置文件可配置)，最终将刷新的AccessToken存放在Response的Header中的Authorization字段返回(前端进行获取替换，下次用新的AccessToken进行访问)

# 软件架构 

1. spring boot+mybatis plus 基础框架
2. mybatis plus 代码生成器
3. Shiro + Java-JWT无状态鉴权认证机制 
4. Redis(Jedis)缓存框架 