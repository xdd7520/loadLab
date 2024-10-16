# loadLab
性能测试演示服务



> 需求如下
> 1. 慢查询
> 2. 先查后插的场景
> 3. 查询的时候打一堆日志
> 4. 一个正常的查询场景没有日志的一个方法里面效率很慢的方法内存泄漏的场景
> 5. 一个啥都不干等 500ms 的接口返回

## 接口信息如下

a. 创建用户

- 方法: POST

- 路径: /users

- 请求体: User对象 (JSON格式，包含name和email字段)

- 响应:

- 成功: 201 Created，返回创建的用户对象

- 失败: 409 Conflict，返回错误信息（用户名或邮箱已存在）


b. 获取所有用户

- 方法: GET

- 路径: /users

- 响应: 200 OK，返回用户列表


c. 获取特定用户

- 方法: GET

- 路径: /users/{id}

- 响应: 200 OK，返回用户对象；如果用户不存在，返回null


d. 更新用户

- 方法: PUT

- 路径: /users/{id}

- 请求体: User对象 (JSON格式，包含更新的name和email字段)

- 响应: 200 OK，返回更新后的用户对象；如果用户不存在，返回null


e. 删除用户

- 方法: DELETE

- 路径: /users/{id}

- 响应: 200 OK

- PerformanceTestController 接口 (基础路径: /performance)


a. 慢查询测试

- 方法: GET

- 路径: /performance/slow-query

- 响应: 200 OK，返回 "慢查询完成"（延迟2秒）


b. 查询后插入测试

- 方法: GET

- 路径: /performance/query-then-insert

- 响应: 200 OK，返回查询结果和新插入的数据


c. 带日志的查询测试

- 方法: GET

- 路径: /performance/query-with-logs

- 响应: 200 OK，返回 "查询结果"（同时在日志中记录多条信息）


d. 低效查询测试

- 方法: GET

- 路径: /performance/inefficient-query

- 响应: 200 OK，返回低效查询完成信息和结果长度


e. 内存泄漏测试

- 方法: GET

- 路径: /performance/memory-leak

- 响应: 200 OK，返回 "可能的内存泄漏操作完成"


f. 延迟测试

- 方法: GET

- 路径: /performance/delay

- 响应: 200 OK，返回 "延迟500ms后返回"（延迟500毫秒）


