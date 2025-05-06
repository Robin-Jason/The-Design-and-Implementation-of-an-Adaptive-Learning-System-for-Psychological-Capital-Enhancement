# 项目部署

## 文件说明

data下存放部署的配置文件以及打包好的dist和jar

Heart-s-Journey放的是部署项目的源码（实际部署服务器不需要存放源码）

## 前端部署

1. 本地build生成的dist文件夹放在服务器data某个路径下如data/vue

2. 这里前端分为 front 和 admin 端，则在data下新建 vue-front 和 vue-admin 文件夹，分别放入各自对应的 dist 文件夹，并各自写好docker-compose.yml，Dockerfile，nginx配置文件ngx.conf

3. **在各自的目录下**运行如下命令构建镜像，镜像名即为配置文件里面的镜像名，然后启动docker-compose

   ```bash
   docker build . -t [镜像名] --no-cache
   ```

   ```bash
   docker compose up -d
   ```

## 后端部署

1. 本地IDEA中的maven点击clean和package生成jar包，放在服务器data某个路径下如data/springboot

2. 在jar包同级目录下写好Dockerfile和docker-compose.yml

3. **在各自的目录下**运行如下命令构建镜像，镜像名即为配置文件里面的镜像名，然后启动docker-compose

   ```bash
   docker build . -t [镜像名] --no-cache
   ```

   ```bash
   docker compose up -d
   ```

## 数据库

1. 服务器data下创建数据库的文件夹如data/mysql

2. 写好docker-compose.yml文件并配置好生成的数据放在同级目录下以做数据隔离

3. 如前文所述启动服务

   ```bash
   docker compose up -d
   ```

4. 本地navicat连接服务器数据库，运行事先写好的sql脚本进行初始化等操作
