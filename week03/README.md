1.httpClient整合api网关，访问网关http://localhost:8888，随机访问目标服务http://localhost:8801http://localhost:8802
com.nio.SendRequest java类使用httpClient 访问http://localhost:8888
com.nio.HttpServer01      http://localhost:8801
com.nio.HttpServer02      http://localhost:8802

2.过滤器:请求的时候，给请求头里边加一个xjava的key,value:youzhijiang