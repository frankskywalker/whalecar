#城市字典表
Drop Table Dic_City;
CREATE TABLE IF NOT EXISTS Dic_City(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 Province_Cname varchar(64) not null,
 Province_Ename varchar(32) not null,
 City_Cname varchar(64) not null,
 City_Ename varchar(32) not null,
 order_index integer COMMENT '排序值',
 flag_useable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
 INDEX(order_index)
);


#颜色字典表
Drop Table Dic_Color;
CREATE TABLE IF NOT EXISTS Dic_Color(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 color_cname varchar(64) not null,
 color_ename varchar(32) not null,
 color_rgb varchar(16),
 color_img_path varchar(256),
 order_index integer COMMENT '排序值',
 flag_useable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
 INDEX(order_index)
);
 

#汽车品牌
Drop Table Car_Brand;
CREATE TABLE IF NOT EXISTS Car_Brand(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 cname varchar(32) not null COMMENT '中文名',
 ename varchar(32) not null COMMENT '英文名',
 logo_path varchar(256) not null COMMENT 'logo文件名',
 order_index integer COMMENT '排序值',
 flag_useable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
 INDEX(order_index)
);


#汽车型号Lv1
Drop Table Car_Model_Lv1;
CREATE TABLE IF NOT EXISTS Car_Model_Lv1(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 car_brand integer not null COMMENT '外键Car_Brand.id' REFERENCES Car_Brand(id) ,
 cname varchar(128) not null COMMENT '中文名',
 ename varchar(128) not null COMMENT '英文名',
 img_path varchar(256) not null COMMENT 'logo文件名',
 order_index integer COMMENT '排序值',
 flag_useable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
 INDEX(order_index)
);


#汽车型号Lv2
Drop Table Car_Model_Lv2;
CREATE TABLE IF NOT EXISTS Car_Model_Lv2(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 Car_Model_Lv1 integer not null COMMENT '外键Car_Model_Lv1' REFERENCES Car_Model_Lv1(id) ,
 short_name varchar(128) not null COMMENT '名称简写',
 full_name varchar(256) not null COMMENT '名称全写',
 order_index integer COMMENT '排序值',
 flag_useable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
 INDEX(order_index)
);


#汽车型号Lv3
Drop Table Car_Model_Lv3;
CREATE TABLE IF NOT EXISTS Car_Model_Lv3(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 Car_Model_Lv2 integer not null COMMENT '外键Car_Model_Lv2' REFERENCES Car_Model_Lv2(id) ,
 short_name varchar(128) not null COMMENT '名称简写',
 full_name varchar(256) not null COMMENT '名称全写',
 Color_Outside_Collection varchar(512) not null COMMENT '外观颜色集合，关联dic_color.id,逗号分割数组形式',
 Color_Inside_Collection varchar(512) not null COMMENT '内饰颜色集合，关联dic_color.id,逗号分割数组形式',
 Factory_Price DECIMAL not null COMMENT '厂商定价',
 order_index integer COMMENT '排序值',
 flag_useable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
 INDEX(order_index)
);

#4s店信息表
Drop Table Shop;
CREATE TABLE IF NOT EXISTS Shop(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 Shop_Name varchar(128) not null COMMENT '4s店名称',
 Shop_Address varchar(512) not null COMMENT '地址',
 Shop_Tel varchar(32) not null COMMENT '电话',
 Shop_Email varchar(32) not null COMMENT '邮件',
 Shop_City integer not null COMMENT '城市' REFERENCES Dic_City(id),
 Shop_Location_Longtude DECIMAL not null COMMENT '经度',
 Shop_Location_Dimensiong DECIMAL not null COMMENT '维度',
 Login_Name varchar(32) not null COMMENT '登录名',
 Login_Password char(41) not null COMMENT '登陆密码',
 flag_useable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
 INDEX(Login_Name,Login_Password)
);

#4s店库存表
Drop Table Shop_Stock;
CREATE TABLE IF NOT EXISTS Shop_Stock(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 Shop Integer not null COMMENT '外键4s店,Shop.Id' REFERENCES Shop(id),
 Car_Model_Lv3 Integer not null COMMENT '外键车型Lv3,Car_Model_Lv3.Id' REFERENCES Car_Model_Lv3(id),
 Car_Outside_Color Integer not null COMMENT '外键外观颜色，Dic_Color.Id' REFERENCES Dic_Color(id),
 Car_Inside_Color Integer not null COMMENT '外键内饰颜色，Dic_Color.Id' REFERENCES Dic_Color(id),
 Car_Addition varchar(512) COMMENT '附加值（Car_Addition.Id以逗号隔开列表）',
 Car_Price DECIMAL not null COMMENT '价格',
 Car_On_Order_Num Integer not null default 0 COMMENT '汽车现货库存数量',
 Car_On_Hande_num Integer not null default 0 COMMENT '汽车在途数量'
);

#用户表
Drop Table User;
CREATE TABLE IF NOT EXISTS User(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 User_Name varchar(64) not null COMMENT '用户姓名',
 User_Tel varchar(32) not null COMMENt '用户电话',
 User_Email varchar(32) not null COMMENT '用户邮箱',
 User_City integer not null COMMENT '城市' REFERENCES Dic_City(id),
 Login_Name varchar(32) not null COMMENT '登录名',
 Login_Password char(41) not null COMMENT '登陆密码',
 Flag_Useable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
 INDEX(Login_Name,Login_Password)
);

#用户订单表
Drop Table User_Order;
CREATE TABLE IF NOT EXISTS User_Order(
 id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
 Order_SN varchar(64) not null COMMENT '序号',
 Order_Title varchar(256) not null COMMENT '订单标题',
 Order_State varchar(16) not null COMMENT '订单状态（等待付款:waiting_pay、等待确认:waiting_confirm、预定成功:order_succ、已完成:finish、已取消cancel）',
 Order_Create_Date TIMESTAMP not null default now() COMMENT '创建时间',
 Order_Pay_Date TIMESTAMP COMMENT '付款时间',
 Order_Confirm_Date TIMESTAMP COMMENT '确认时间',
 Order_Complete_Date TIMESTAMP COMMENT '完成时间',
 Order_Cancel_Date TIMESTAMP COMMENT ' 取消时间',
 Order_Price decimal not null COMMENT '订单价格',
 Shop_Stock Integer not null COMMENT '外键商店库存表ID，Shop_Stock.Id' REFERENCES Shop_Stock(id),
 Shop Integer not null COMMENT '外键4s店,Shop.Id' REFERENCES Shop(id),
 INDEX(Order_Create_Date)
);



 
 
 
 
 
