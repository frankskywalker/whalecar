#城市字典表
Drop Table Dic_City;
CREATE TABLE IF NOT EXISTS Dic_City (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    provinceCname varchar(64) not null,
    provinceEname varchar(32) not null,
    cityCname varchar(64) not null,
    cityEname varchar(32) not null,
    orderIndex integer COMMENT '排序值',
    flagUseable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
    INDEX (orderIndex)
);


#颜色字典表
Drop Table Dic_Color;
CREATE TABLE IF NOT EXISTS Dic_Color (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    colorCname varchar(64) not null,
    colorEname varchar(32) not null,
    colorRgb varchar(16),
    colorImgPath varchar(256),
    orderIndex integer COMMENT '排序值',
    flagUseable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
    INDEX (orderIndex)
);
 

#汽车品牌
Drop Table Car_Brand;
CREATE TABLE IF NOT EXISTS Car_Brand (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    cname varchar(32) not null COMMENT '中文名',
    ename varchar(32) not null COMMENT '英文名',
    logoPath varchar(256) not null COMMENT 'logo文件名',
    orderIndex integer COMMENT '排序值',
    flagUseable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
    INDEX (orderIndex)
);

#汽车子品牌
Drop Table Car_Sub_Brand;
CREATE TABLE IF NOT EXISTS Car_Sub_Brand (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    carBrand integer not null COMMENT '所属品牌，外键：CarBrand.Id' REFERENCES Car_Brand (id),
    cname varchar(32) not null COMMENT '中文名',
    ename varchar(32) not null COMMENT '英文名',
    orderIndex integer COMMENT '排序值',
    flagUseable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
    INDEX (orderIndex)
);


#汽车型号Lv1
Drop Table Car_Model_Lv1;
CREATE TABLE IF NOT EXISTS Car_Model_Lv1 (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    carBrand integer not null COMMENT '外键Car_Brand.id' REFERENCES Car_Brand (id),
    carSubBrand integer COMMENT '外键Car_Sub_Brand.Id,可为空，为空说明无子品牌' REFERENCES Car_Sub_Brand (id),
    cname varchar(128) not null COMMENT '中文名',
    ename varchar(128) not null COMMENT '英文名',
    imgPath varchar(256) not null COMMENT 'logo文件名',
    orderIndex integer COMMENT '排序值',
    flagUseable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
    INDEX (orderIndex)
);


#汽车型号Lv2
Drop Table Car_Model_Lv2;
CREATE TABLE IF NOT EXISTS Car_Model_Lv2 (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    carModelLv1 integer not null COMMENT '外键Car_Model_Lv1' REFERENCES Car_Model_Lv1 (id),
    shortName varchar(128) not null COMMENT '名称简写',
    fullName varchar(256) not null COMMENT '名称全写',
    orderIndex integer COMMENT '排序值',
    flagUseable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
    INDEX (orderIndex)
);


#汽车型号Lv3
Drop Table Car_Model_Lv3;
CREATE TABLE IF NOT EXISTS Car_Model_Lv3 (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    carModelLv2 integer not null COMMENT '外键Car_Model_Lv2' REFERENCES Car_Model_Lv2 (id),
    shortName varchar(128) not null COMMENT '名称简写',
    fullName varchar(256) not null COMMENT '名称全写',
    colorOutsideCollection varchar(512) not null COMMENT '外观颜色集合，关联dic_color.id,逗号分割数组形式',
    colorInsideCollection varchar(512) not null COMMENT '内饰颜色集合，关联dic_color.id,逗号分割数组形式',
    factoryPrice DECIMAL not null COMMENT '厂商定价',
    orderIndex integer COMMENT '排序值',
    flagUseable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
    INDEX (orderIndex)
);

#4s店信息表
Drop Table Shop;
CREATE TABLE IF NOT EXISTS Shop (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    shopName varchar(128) not null COMMENT '4s店名称',
    shopAddress varchar(512) not null COMMENT '地址',
    shopTel varchar(32) not null COMMENT '电话',
    shopEmail varchar(32) not null COMMENT '邮件',
    shopCity integer not null COMMENT '城市' REFERENCES Dic_City (id),
    shopLocationLongtude DECIMAL not null COMMENT '经度',
    shopLocationDimensiong DECIMAL not null COMMENT '维度',
    loginName varchar(32) not null COMMENT '登录名',
    loginPassword char(41) not null COMMENT '登陆密码',
    flagUseable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
    INDEX (loginName , loginPassword)
);

#4s店库存表
Drop Table Shop_Stock;
CREATE TABLE IF NOT EXISTS Shop_Stock (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    shop Integer not null COMMENT '外键4s店,Shop.Id' REFERENCES Shop (id),
    carModelLv3 Integer not null COMMENT '外键车型Lv3,Car_Model_Lv3.Id' REFERENCES Car_Model_Lv3 (id),
    carOutsideColor Integer not null COMMENT '外键外观颜色，Dic_Color.Id' REFERENCES Dic_Color (id),
    carInsideColor Integer not null COMMENT '外键内饰颜色，Dic_Color.Id' REFERENCES Dic_Color (id),
    carAddition varchar(512) COMMENT '附加值（Car_Addition.Id以逗号隔开列表）',
    carPrice DECIMAL not null COMMENT '价格',
    carOnOrderNum Integer not null default 0 COMMENT '汽车现货库存数量',
    carOnHandeNum Integer not null default 0 COMMENT '汽车在途数量'
);

#用户表
Drop Table User;
CREATE TABLE IF NOT EXISTS User (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    userName varchar(64) not null COMMENT '用户姓名',
    userTel varchar(32) not null COMMENt '用户电话',
    userEmail varchar(32) not null COMMENT '用户邮箱',
    userCity integer not null COMMENT '城市' REFERENCES Dic_City (id),
    loginName varchar(32) not null COMMENT '登录名',
    loginPassword char(41) not null COMMENT '登陆密码',
    flagUseable varchar(8) not null default 'yes' COMMENT '标志位：是否启用',
    INDEX (loginName , loginPassword)
);

#用户订单表
Drop Table User_Order;
CREATE TABLE IF NOT EXISTS User_Order (
    id integer not null AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    orderSn varchar(64) not null COMMENT '序号',
    orderTitle varchar(256) not null COMMENT '订单标题',
    orderState varchar(16) not null COMMENT '订单状态（等待付款:waiting_pay、等待确认:waiting_confirm、预定成功:order_succ、已完成:finish、已取消cancel）',
    orderCreateDate TIMESTAMP not null default now() COMMENT '创建时间',
    orderPayDate TIMESTAMP COMMENT '付款时间',
    orderConfirmDate TIMESTAMP COMMENT '确认时间',
    orderCompleteDate TIMESTAMP COMMENT '完成时间',
    orderCancelDate TIMESTAMP COMMENT ' 取消时间',
    orderPrice decimal not null COMMENT '订单价格',
    shopStock Integer not null COMMENT '外键商店库存表ID，Shop_Stock.Id' REFERENCES Shop_Stock (id),
    shop Integer not null COMMENT '外键4s店,Shop.Id' REFERENCES Shop (id),
    INDEX (orderCreateDate)
);



 
 
 
 
 
