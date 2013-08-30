DROP FUNCTION IF EXISTS gen_seralno;
DELIMITER $$
CREATE FUNCTION gen_seralno(in_name varchar(64), in_date date)
  RETURNS varchar(83)
BEGIN
  DECLARE currentIndex INT;
  DECLARE count INT;
	select count(*) into count from Sys_SeralNo
	where name = in_name and date = in_date for update ;
	if count = 0 then
		-- 判断是否为当天第一次生成序列，若是则插入一条新记录
		begin
			insert into Sys_SeralNo(name,indexNo,date) values(in_name,0,in_date);
			set currentIndex = 0;
		end;
	else
		-- 若不是，则将当前indexNo取出
		select indexNo into currentIndex from Sys_SeralNo
			where name = in_name and date = in_date for update ;
	end if; 
	-- 序列每次获取自动+1，并从1开始
	set currentIndex  = currentIndex + 1;
	-- 新序列更新回表
	update Sys_SeralNo set indexNo = currentIndex;
	-- return
	return concat(in_name,in_date,lpad(currentIndex,4,'0'));
  return currentIndex;
END;
$$
DELIMITER ;
