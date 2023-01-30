# VueFx Demo

一个基于 Spring Boot + JavaFX 的 Vue2 的 _**简易**_ 融合和示例代码.

> 跑在 JavaFX WebView 里面的 Vue2

* 优点
  * 可以用上 Web 侧各类技术, 比如基于 Web 的响应式布局等.
    本示例项目就用了 Vue 做数据双向绑定
  * 所有的东西挂在 JVM 上,
    可以基于 JVM 做各种 native 操作,
    比如访问数据库
* 缺点
  * ~~我们吃掉的内存真是太他妈多了~~ 内存占用极高.
    经测试, 一个 hello world 级项目启动即占用 200M 内存
  * 性能低下. 目前 Java 侧和 Web 侧交换数据时会产生额外的 JSON 序列化开销
* 有啥用
  * 如果你知道自己准备干啥 + 为啥要这么干, 那就有用
* 为什么不用 _纯 JavaFX / Electron / ..._ ?
  * 就是想 **玩**

## 如何使用

> 本项目默认基于 Java 19

* 引入本项目作为依赖
* 创建原生 Web 页面和相关资源, 需在页面内使用标签引入 vue.js (`<script src='/firok/spring/vuefx/vue.js'></script>`)
* 创建 JavaFX controller 类, 需继承自 `firok.spring.vuefx.VueFxController`
* 创建 JavaFX application 类, 需继承自 `firok.spring.vuefx.VueFxApplicationFx`
* 创建 Spring Boot Application 类, 在 main 方法内执行 `javafx.application.Application.launch(AnyVueFxApplication.class);` (将类名替换为上一步创建的类)
* 启动项目
