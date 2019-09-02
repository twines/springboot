#说明

---

牛逼不是吹的,火车不是推的，少说话多做事，少点抱怨，多点承担 ヾ(◍°∇°◍)ﾉﾞ 

---

[后台监控地址](http://localhost:8080/druid/sql.html)，可以监控页面访问，SQL

##参考文档

[mybatis.plus](https://mybatis.plus)


---
当你解决不了问题的时候重启一下试试
---


目录结构
```
项目
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─twins
│  │  │          └─lee
│  │  │              ├─config       配置文件目录：例如 Druid的配置
│  │  │              ├─controller   控制器
│  │  │              ├─entity       实体，也可以说是模型
│  │  │              ├─mapper       mybatis接口
│  │  │              └─service      接口实现
│  │  └─resources
│  │      ├─mapping     mybatis的xml文件
│  │      ├─static      静态资源文件
│  │      │  └─layuiadmin
│  │      │      ├─json
│  │      │      │  ├─console
│  │      │      │  ├─content
│  │      │      │  ├─forum
│  │      │      │  ├─layer
│  │      │      │  ├─layim
│  │      │      │  ├─mall
│  │      │      │  ├─message
│  │      │      │  ├─table
│  │      │      │  ├─upload
│  │      │      │  ├─user
│  │      │      │  ├─useradmin
│  │      │      │  └─workorder
│  │      │      ├─layui
│  │      │      │  ├─css
│  │      │      │  │  └─modules
│  │      │      │  │      ├─laydate
│  │      │      │  │      │  └─default
│  │      │      │  │      ├─layer
│  │      │      │  │      │  └─default
│  │      │      │  │      └─layim
│  │      │      │  │          ├─html
│  │      │      │  │          ├─mobile
│  │      │      │  │          ├─skin
│  │      │      │  │          └─voice
│  │      │      │  ├─font
│  │      │      │  ├─images
│  │      │      │  │  └─face
│  │      │      │  └─lay
│  │      │      │      └─modules
│  │      │      │          └─mobile
│  │      │      ├─lib
│  │      │      │  └─extend
│  │      │      ├─modules
│  │      │      ├─style
│  │      │      │  └─res
│  │      │      │      └─template
│  │      │      └─tpl
│  │      │          ├─layim
│  │      │          └─system
│  │      └─templates   模板文件
│  │          └─layout
│  └─test
│      └─java
│          └─com
│              └─twins
│                  └─lee
└─target
    ├─classes
    ├─generated-sources
    │  └─annotations
    ├─generated-test-sources
    │  └─test-annotations
    └─test-classes



```