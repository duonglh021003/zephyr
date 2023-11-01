CREATE DATABASE zephyr

USE zephyr
GO
-- ChucVu
CREATE TABLE position(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
position_name NVARCHAR(200) DEFAULT NULL, -- tên
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
staff_status INT DEFAULT 1, -- trạng thái
)
insert into position values
('ma0001',N'admin','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0002',N'nhân viên','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT)
select *
from 
position
GO
-- NhanVien
CREATE TABLE staff(
id BIGINT IDENTITY(1,1) PRIMARY KEY,
code VARCHAR(200) UNIQUE, -- mã
staff_name NVARCHAR(200) DEFAULT NULL, -- tên
date_of_birth DATE DEFAULT NULL, -- ngày sinh
phone_number VARCHAR(200) DEFAULT NULL, -- sđt
gmail VARCHAR(200) DEFAULT NULL, -- gmail
gender NVARCHAR(200) DEFAULT NULL, -- giới tính
staff_address NVARCHAR(MAX) DEFAULT NULL, -- địa chỉ
commune NVARCHAR(200) DEFAULT NULL, -- xã
district NVARCHAR(200) DEFAULT NULL, -- huyện
city NVARCHAR(200) DEFAULT NULL, -- thành phố 
staff_password VARCHAR(MAX) DEFAULT NULL, -- mật khẩu
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
staff_status INT DEFAULT 1, -- trạng thái
id_position BIGINT , -- id chức vụ
)
insert into staff values
('ma0001',N'Lê Huy Dương','2002-12-12','0339115608','d@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,1),
('ma0002',N'Nhân Viên 02','2002-12-12','0898629635','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma0003',N'Nhân Viên 03','2002-12-12','0312345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma0004',N'Nhân Viên 04','2002-12-12','0412345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma0005',N'Nhân Viên 05','2002-12-12','0512345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma0006',N'Nhân Viên 06','2002-12-12','0612345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma0007',N'Nhân Viên 07','2002-12-12','0712345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma0008',N'Nhân Viên 08','2002-12-12','0812345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma0009',N'Nhân Viên 09','2002-12-12','0912345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00010',N'Nhân Viên 10','2002-12-12','1012345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00011',N'Nhân Viên 11','2002-12-12','1112345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00012',N'Nhân Viên 12','2002-12-12','1212345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00013',N'Nhân Viên 13','2002-12-12','1312345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00014',N'Nhân Viên 14','2002-12-12','1412345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00015',N'Nhân Viên 15','2002-12-12','1512345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00016',N'Nhân Viên 16','2002-12-12','1612345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00017',N'Nhân Viên 17','2002-12-12','1712345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00018',N'Nhân Viên 18','2002-12-12','1812345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00019',N'Nhân Viên 19','2002-12-12','1912345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00020',N'Nhân Viên 20','2002-12-12','2012345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00021',N'Nhân Viên 21','2002-12-12','2112345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2),
('ma00022',N'Nhân Viên 22','2002-12-12','2298629635','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',0,2),
('ma00023',N'Nhân Viên 23','2002-12-12','2312345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',0,2),
('ma00024',N'Nhân Viên 24','2002-12-12','2412345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',0,2),
('ma00025',N'Nhân Viên 25','2002-12-12','2512345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',0,2),
('ma00026',N'Nhân Viên 26','2002-12-12','2612345678','02@gmail.com',N'false',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',0,2),
('ma00027',N'Nhân Viên 27','2002-12-12','2712345678','03@gmail.com',N'true',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','1','2002-12-12','2002-12-12',N'dương',N'dương',0,2)
select *
from 
staff
GO
-- hạng
CREATE TABLE ranks(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
ranks_name NVARCHAR(200) DEFAULT NULL, -- tên
ranks_percent DECIMAL(20,3) DEFAULT NULL, -- phần trăm
staff_status INT DEFAULT 1, -- trạng thái
)
insert into ranks values
('ma0001',N'Đồng hành',0.3,DEFAULT),
('ma0002',N'Thân thiết',0.5,DEFAULT),
('ma0003',N'Tri kỷ',0.8,DEFAULT),
('ma0004',N'Vip',1.5,DEFAULT)
select *
from 
ranks 
GO
-- sản phẩm
CREATE TABLE product(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
product_name NVARCHAR(200) DEFAULT NULL, -- tên
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
product_status INT DEFAULT 1, -- trạng thái
)
insert into product values
('ma0001',N'đầm 01','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0002',N'đầm 02','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0003',N'đầm 03','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0004',N'đầm 04','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0005',N'đầm 05','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0006',N'đầm 06','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0007',N'đầm 07','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0008',N'đầm 08','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0009',N'đầm 09','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma00010',N'đầm 10','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT)
select *
from 
product
GO
--xuất xứ
CREATE TABLE origin(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
origin_name NVARCHAR(200) DEFAULT NULL, -- tên
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
origin_status INT DEFAULT 1, -- trạng thái
)
insert into origin values
('ma0001',N'việt nam','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0002',N'trung quốc','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0003',N'nhật bản','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT)
select *
from 
origin
GO
-- màu sắc
CREATE TABLE color(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
color_name NVARCHAR(200) DEFAULT NULL, -- tên
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
color_status INT DEFAULT 1, -- trạng thái
)
insert into color values
('ma0001',N'Black','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0002',N'White','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0003',N'Red','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0004',N'Blue','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0005',N'Green','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT)
select *
from 
color
GO
-- size
CREATE TABLE size(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
size_name NVARCHAR(200) DEFAULT NULL, -- tên
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
size_status INT DEFAULT 1, -- trạng thái
)
insert into size values
('ma0001',N'XS','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0002',N'S','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0003',N'M','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0004',N'L','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0005',N'XL','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT)
select *
from 
size
GO
-- ChiTietSP
CREATE TABLE product_details(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
images NVARCHAR(200) DEFAULT NULL, -- image
describe NVARCHAR(50) DEFAULT NULL, -- mô tả
inventory INT DEFAULT NULL, -- số lượng tồn
import_price DECIMAL(20,3) DEFAULT NULL, -- giá nhập
price DECIMAL(20,3) DEFAULT NULL, -- giá bán
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
product_details_status INT DEFAULT 1, -- trạng thái
id_product BIGINT , -- id sản phẩm
id_origin BIGINT , -- id xuất xứ 
id_color BIGINT , -- id màu sắc
id_size BIGINT , -- id size
)
insert into product_details values
(N'1.jpg',N'không',101,200.000,299.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,1,1,1,1),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,1,1),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,1,2),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,1,3),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,1,4),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,1,5),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,2,1),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,2,2),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,2,3),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,2,4),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,2,5),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,3,1),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,3,2),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,3,3),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,3,4),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,3,5),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,4,1),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,4,2),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,4,3),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,4,4),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,4,5),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,5,1),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,5,2),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,5,3),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,5,4),
(N'2.jpg',N'không',201,300.000,399.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,1,5,5),
(N'3.jpg',N'không',301,400.000,499.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,3,1,1,1),
(N'4.jpg',N'không',401,500.000,599.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,4,1,1,1),
(N'5.jpg',N'không',501,600.000,699.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,5,1,1,1),
(N'6.jpg',N'không',601,700.000,799.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,6,1,1,1),
(N'7.jpg',N'không',701,800.000,899.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,7,1,1,1),
(N'8.jpg',N'không',801,900.000,999.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,8,1,1,1),
(N'9.jpg',N'không',101,800.000,899.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,9,1,1,2),
(N'10.jpg',N'không',101,800.000,899.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,10,1,1,1)
select *
from 
product_details
GO
-- phiếu giảm giá
CREATE TABLE voucher(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
voucher_name NVARCHAR(200) DEFAULT NULL, -- tên
date_begin DATE DEFAULT NULL, -- ngày bắt đầu
date_end DATE DEFAULT NULL, -- ngày kết thúc
minimum_price DECIMAL(20,3) DEFAULT NULL, -- giá tối thiểu
maximum_price DECIMAL(20,3) DEFAULT NULL, --	giá đối đa
reduced_price DECIMAL(20,3) DEFAULT NULL, --	giá trị giảm
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
voucher_status INT DEFAULT 1, -- trạng thái
)
insert into voucher values
('ma0001',N'voucher 15','2002-12-12','2002-12-12',0.000,100.000,15.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0002',N'voucher 20','2002-12-12','2002-12-12',0.000,100.000,20.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('ma0003',N'voucher 25','2002-12-12','2002-12-12',0.000,100.000,25.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT)
select *
from 
voucher
GO
-- yêu thích 
CREATE TABLE favourite(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
favourite_status INT DEFAULT 1, -- trạng thái
id_client BIGINT , -- id khách hàng
)
insert into favourite values
('ma0001','2002-12-12','2002-12-12',DEFAULT,1),
('ma0002','2002-12-12','2002-12-12',DEFAULT,2),
('ma0003','2002-12-12','2002-12-12',DEFAULT,3)
select *
from 
favourite
GO
-- yêu thích chi tiết
CREATE TABLE favourite_details(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
date_create DATE DEFAULT NULL, -- ngày tạo
favourite_details_status INT DEFAULT 1, -- trạng thái
id_favourite BIGINT , -- id yêu thích
id_product_details BIGINT, -- id chi tiết sản phẩm
)
insert into favourite_details values
('2002-12-12',DEFAULT,1,1),
('2002-12-12',DEFAULT,1,2),
('2002-12-12',DEFAULT,2,3),
('2002-12-12',DEFAULT,3,2),
('2002-12-12',DEFAULT,3,3)
select *
from 
favourite_details
GO
-- giỏ hàng 
CREATE TABLE shopping_cart(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
total_shopping_cart DECIMAL(20,3) DEFAULT NULL, -- tổng tiền giỏ hàng
shopping_cart_status INT DEFAULT 1, -- trạng thái
)
insert into shopping_cart values
('ma0001',200,DEFAULT),
('ma0002',300,DEFAULT),
('ma0003',400,DEFAULT),
('ma0004',500,DEFAULT),
('ma0005',600,DEFAULT),
('ma0006',700,DEFAULT),
('ma0007',800,DEFAULT)
select *
from 
shopping_cart
GO
--khách hàng
CREATE TABLE client(
id BIGINT IDENTITY(1,1) PRIMARY KEY,
code VARCHAR(200) UNIQUE, -- mã
client_name NVARCHAR(200) DEFAULT NULL, -- tên
date_of_birth DATE DEFAULT NULL, -- ngày sinh
phone_number VARCHAR(200) DEFAULT NULL, -- sđt
gmail VARCHAR(200) DEFAULT NULL, -- gmail
gender NVARCHAR(200) DEFAULT NULL, -- giới tính
point_use INT DEFAULT NULL,-- điểm sử dụng 
accumulated_score INT DEFAULT NULL, -- điểm tích luỹ
staff_password VARCHAR(MAX) DEFAULT NULL, -- mật khẩu
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
staff_status INT DEFAULT 1, -- trạng thái
id_ranks BIGINT , -- id hạng
id_shopping_cart BIGINT , -- id giỏ hàng
)
insert into client values
('ma0001',N'Khách Hàng 01','2002-12-12','0898629635','duongcf2k3@gmail.com',N'true',50,600,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,1,1),
('ma0002',N'Khách Hàng 02','2002-12-12','0898629635','duong021003@gmail.com',N'true',50,700,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,1,2),
('ma0003',N'Khách Hàng 03','2002-12-12','0898629635','duonglhph22902@fpt.edu.vn',N'true',50,800,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,3,3),
('ma0004',N'Khách Hàng 04','2002-12-12','0898629635','01@gmail.com',N'true',50,600,'1','2002-12-12','2002-12-12',N'dương',N'dương',0,1,4),
('ma0005',N'Khách Hàng 05','2002-12-12','0898629635','02@gmail.com',N'true',50,700,'1','2002-12-12','2002-12-12',N'dương',N'dương',0,2,5),
('ma0006',N'Khách Hàng 06','2002-12-12','0898629635','03@gmail.com',N'true',50,800,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,3,6),
('ma0007',N'Khách Hàng 07','2002-12-12','0898629635','03@gmail.com',N'true',50,800,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,7)
select *
from 
client
GO
-- địa chỉ
CREATE TABLE client_address(
id BIGINT IDENTITY(1,1) PRIMARY KEY,
code VARCHAR(200) UNIQUE, -- mã
client_name NVARCHAR(200) DEFAULT NULL, -- tên
phone_number VARCHAR(200) DEFAULT NULL, -- sđt
client_address NVARCHAR(MAX) DEFAULT NULL, -- địa chỉ
commune NVARCHAR(200) DEFAULT NULL, -- xã
district NVARCHAR(200) DEFAULT NULL, -- huyện
city NVARCHAR(200) DEFAULT NULL, -- thành phố 
date_create DATE DEFAULT NULL, -- ngày tạo
address_status INT DEFAULT 0, -- trạng thái
id_client BIGINT , -- id client
)
insert into client_address values
('ma0001',N'Khách Hàng 01','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,1),
('ma0002',N'Khách Hàng 02','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',DEFAULT,2),
('ma0003',N'Khách Hàng 02','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,2),
('ma0004',N'Khách Hàng 03','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',DEFAULT,3),
('ma0005',N'Khách Hàng 01','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',DEFAULT,1),
('ma0006',N'Khách Hàng 03','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,3),
('ma0007',N'Khách Hàng 04','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,4),
('ma0008',N'Khách Hàng 05','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,5),
('ma0009',N'Khách Hàng 06','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,6),
('ma00010',N'Khách Hàng 07','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,7)
select *
from 
client_address
GO
-- giỏ hàng chi tiết
CREATE TABLE detailed_shopping_cart(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
quantity INT, -- số lượng
unit_price DECIMAL(20,3) DEFAULT 0, -- đơn giá
capital_sum DECIMAL(20,3), -- tổng tiền
date_create DATE DEFAULT NULL, -- ngày tạo
detailed_shopping_cart_status INT DEFAULT 1, -- trạng thái
id_shopping_cart BIGINT , -- id giỏ hàng
id_product_details BIGINT, -- id chi tiết sản phẩm
)
insert into detailed_shopping_cart values
(1,399.000,399.000,'2002-12-12',DEFAULT,1,2),
(2,499.000,799.000,'2002-12-12',DEFAULT,1,3),
(1,299.000,299.000,'2002-12-12',DEFAULT,2,1),
(1,299.000,299.000,'2002-12-12',DEFAULT,3,1),
(1,399.000,399.000,'2002-12-12',DEFAULT,3,2)

select *
from 
detailed_shopping_cart
select * from product_details
GO
-- hoá đơn 
CREATE TABLE invoice(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
date_create DATE DEFAULT NULL, -- ngày tạo
total_invoice DECIMAL(20,3) DEFAULT NULL, -- tổng tiền hoá đơn
into_money DECIMAL(20,3) DEFAULT NULL, -- thành tiền
client_give_money DECIMAL(20,3) DEFAULT NULL, --  tiền khách đưa
return_client_money DECIMAL(20,3) DEFAULT NULL, --  tiền trả khách
note NVARCHAR(200) DEFAULT NULL, -- ghi chú
invoice_status INT DEFAULT 1, -- trạng thái
id_client BIGINT , -- id khách hàng
id_client_address BIGINT, -- id địa chỉ
id_voucher BIGINT , -- id phiếu giảm giá
id_staff BIGINT , -- id nhân viên
)
insert into invoice values
('ma0001','2002-12-12',999.000,999.000,1000.000,1.000,N'không',DEFAULT,1,1,1,1),
('ma0002','2002-12-12',999.000,999.000,1000.000,1.000,N'không',DEFAULT,2,2,1,2),
('ma0003','2002-12-12',999.000,999.000,1000.000,1.000,N'không',DEFAULT,1,1,1,1)
select *
from 
invoice
GO
-- hoá đơn chi tiết
CREATE TABLE detailed_invoice(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
quantity INT, -- số lượng
unit_price DECIMAL(20,3) DEFAULT 0, -- đơn giá
capital_sum DECIMAL(20,3), -- tổng tiền
detailed_shopping_cart_status INT DEFAULT 1, -- trạng thái
id_invoice BIGINT , -- id hoá đơn
id_product_details BIGINT, -- id chi tiết sản phẩm
)
insert into detailed_invoice values
(1,399.000,399.000,DEFAULT,1,2),
(2,499.000,799.000,DEFAULT,1,3),
(1,299.000,299.000,DEFAULT,2,1),
(1,299.000,299.000,DEFAULT,3,1),
(1,399.000,399.000,DEFAULT,3,2)
select *
from 
detailed_invoice
GO
-- phiếu giao hàng
CREATE TABLE delivery_notes(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
client_name NVARCHAR(200) DEFAULT NULL, -- tên người nhận
phone_number VARCHAR(200) DEFAULT NULL, -- sđt người nhận
date_order DATE DEFAULT NULL, -- ngày đặt hàng
date_deliver DATE DEFAULT NULL, -- ngày giao
date_receive DATE DEFAULT NULL, -- ngày nhận
note NVARCHAR(200) DEFAULT NULL, -- ghi chú
delivery_notes_status INT DEFAULT 1, -- trạng thái
id_invoice BIGINT , -- id hoá đơn
id_client_address BIGINT, -- id địa chỉ
)
insert into delivery_notes values
('ma0001',N'khách hàng 01','0898629635','2002-12-12','2002-12-12','2002-12-12',N'không',DEFAULT,1,1),
('ma0002',N'khách hàng 02','0898629635','2002-12-12','2002-12-12','2002-12-12',N'không',DEFAULT,1,2),
('ma0003',N'khách hàng 03','0898629635','2002-12-12','2002-12-12','2002-12-12',N'không',DEFAULT,1,3)
select *
from 
delivery_notes
GO
-- phiếu đổi hàng
CREATE TABLE exchange_note(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
date_by DATE DEFAULT NULL, -- ngày mua
date_end DATE DEFAULT NULL, -- ngày kết thúc
describe NVARCHAR(200) DEFAULT NULL, -- mô tả
reason VARCHAR(MAX) DEFAULT NULL, -- lý do
exchange_note_status INT DEFAULT 1, -- trạng thái
id_invoice BIGINT , -- id hoá đơn
)
insert into exchange_note values
('ma0001','2002-12-12','2002-12-12',N'không',N'không',DEFAULT,1)

select *
from 
exchange_note
GO

-- ChiTietSP - sản phẩm
ALTER TABLE product_details ADD FOREIGN KEY(id_product) REFERENCES product(id)
-- ChiTietSP - xuất xứ
ALTER TABLE product_details ADD FOREIGN KEY(id_origin) REFERENCES origin(id)
-- ChiTietSP - màu sắc
ALTER TABLE product_details ADD FOREIGN KEY(id_color) REFERENCES color(id)
-- ChiTietSP - size
ALTER TABLE product_details ADD FOREIGN KEY(id_size) REFERENCES size(id)
-- nhân viên - chức vụ 
ALTER TABLE staff ADD FOREIGN KEY(id_position) REFERENCES position(id)
-- khách hàng - hạng
ALTER TABLE client ADD FOREIGN KEY(id_ranks) REFERENCES ranks(id)
-- khách hàng - giỏ hàng
ALTER TABLE client ADD FOREIGN KEY(id_shopping_cart) REFERENCES shopping_cart(id)
-- địa chỉ - khách hàng 
ALTER TABLE client_address ADD FOREIGN KEY(id_client) REFERENCES client(id)
-- yêu thích - khách hàng 
ALTER TABLE favourite ADD FOREIGN KEY(id_client) REFERENCES client(id)
-- yêu thích chi tiết - yêu thích
ALTER TABLE favourite_details ADD FOREIGN KEY(id_favourite) REFERENCES favourite(id)
-- yêu thích chi tiết - sản phẩm
ALTER TABLE favourite_details ADD FOREIGN KEY(id_product_details) REFERENCES product_details(id)
-- giỏ hàng chi tiết - sản phẩm
ALTER TABLE detailed_shopping_cart ADD FOREIGN KEY(id_product_details) REFERENCES product_details(id)
-- giỏ hàng chi tiết - giỏ hàng
ALTER TABLE detailed_shopping_cart ADD FOREIGN KEY(id_shopping_cart) REFERENCES shopping_cart(id)
-- hoá đơn - khách hàng
ALTER TABLE invoice ADD FOREIGN KEY(id_client) REFERENCES client(id)
-- hoá đơn - địa chỉ
ALTER TABLE invoice ADD FOREIGN KEY(id_client_address) REFERENCES client_address(id)
-- hoá đơn - phiếu giảm giá
ALTER TABLE invoice ADD FOREIGN KEY(id_voucher) REFERENCES voucher(id)
-- hoá đơn - nhân viên
ALTER TABLE invoice ADD FOREIGN KEY(id_staff) REFERENCES staff(id)
-- hoá đơn chi tiết - sản phẩm
ALTER TABLE detailed_invoice ADD FOREIGN KEY(id_product_details) REFERENCES product_details(id)
-- hoá đơn chi tiết - hoá đơn
ALTER TABLE detailed_invoice ADD FOREIGN KEY(id_invoice) REFERENCES invoice(id)
-- phiếu giao hàng - hoá đơn
ALTER TABLE delivery_notes ADD FOREIGN KEY(id_invoice) REFERENCES invoice(id)
-- phiếu giao hàng - địa chỉ
ALTER TABLE delivery_notes ADD FOREIGN KEY(id_client_address) REFERENCES client_address(id)
-- phiếu đổi hàng - hoá đơn
ALTER TABLE exchange_note ADD FOREIGN KEY(id_invoice) REFERENCES invoice(id)




