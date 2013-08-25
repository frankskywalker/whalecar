DROP function IF EXISTS `gen_seralno`;

DELIMITER $$
USE `whalecar`$$
CREATE FUNCTION gen_seralno(in_name varchar(64), in_date date)
  RETURNS varchar(83)
BEGIN
	DECLARE currentIndex INT;
	select indexNo into currentIndex from Sys_SeralNo
	where name = in_name and date = in_date for update ;
	-- 判断是否为当天第一次生成序列，是则插入一条新记录
	if currentIndex = null then
		begin
			insert into Sys_SeralNo(name,indexNo,date) values(in_name,in_date,0);
			set currentIndex = 0;
		end;
	end if; 
	-- 序列每次获取自动+1，并从1开始
	set currentIndex  = currentIndex + 1;
	-- 新序列更新回表
	update Sys_SeralNo set indexNo = currentIndex;
	-- return
	return concat(name,date,lpad(currentIndex,4,'0'));
  return currentIndex;

END$$

DELIMITER ;

