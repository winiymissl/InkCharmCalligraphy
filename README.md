# InkCharmCalligraphy

使用mvvm架构，整体项目使用组件化搭建，livedata+viewmodel+navigation+rxjava+retrofit+databinding+dagger2

目标：

- [x] 阿里的ARouter针对Navigation框架做个路由库
- [ ] webView使用多进程，避免内存泄漏
- [ ] 完善提示功能
- [ ] 比赛模块
- [x] 在compiler中自定义Logger 
- [x] 封装了网络请求框架，net模块
- [x] 本地数据库采用Room，高度低耦合，在上层提供接口
- [x] 给room数据库写一个compiler，生成接口，因为一个模块单独写接口，太麻烦

遇到的问题：

- [x] dataBinding集合include的使用。
- [x] navigation无法跳转到其他模块的问题。
