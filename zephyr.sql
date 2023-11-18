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
staff_status INT DEFAULT 1, -- trạng thái
id_position BIGINT , -- id chức vụ
)
GO
-- hạng
CREATE TABLE ranks(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
accumulated_score INT DEFAULT NULL, -- điểm tích luỹ
staff_status INT DEFAULT 1, -- trạng thái
)
GO
--khách hàng
CREATE TABLE client(
id BIGINT IDENTITY(1,1) PRIMARY KEY,
code VARCHAR(200) UNIQUE, -- mã
staff_name NVARCHAR(200) DEFAULT NULL, -- tên
date_of_birth DATE DEFAULT NULL, -- ngày sinh
phone_number VARCHAR(200) DEFAULT NULL, -- sđt
gmail VARCHAR(200) DEFAULT NULL, -- gmail
gender NVARCHAR(200) DEFAULT NULL, -- giới tính
staff_password VARCHAR(MAX) DEFAULT NULL, -- mật khẩu
staff_status INT DEFAULT 1, -- trạng thái
id_ranks BIGINT , -- id hạng
)
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
staff_status INT DEFAULT 1, -- trạng thái
id_client BIGINT , -- id client
)
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
<<<<<<< HEAD
insert into color values
('ma0001',N'White','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT), -- trắng
('ma0002',N'Beige','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT), -- be
('ma0003',N'Pink','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT), -- hồng
('ma0004',N'RosyBrown','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT), -- nâu
('ma0005',N'Green','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT) -- xanh
select *
from 
color
=======
>>>>>>> 5b152e281ae51b83f225e905b5938da565525b9f
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
GO
-- ChiTietSP
CREATE TABLE product_details(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
images NVARCHAR(200) DEFAULT NULL, -- image
describe NVARCHAR(50) DEFAULT NULL, -- mô tả
inventory INT DEFAULT NULL, -- số lượng tồn
import_price DECIMAL(20,3) DEFAULT NULL, -- giá nhập
price DECIMAL(20,3) DEFAULT NULL, -- giá bán
product_details_status INT DEFAULT 1, -- trạng thái
id_product BIGINT , -- id sản phẩm
id_origin BIGINT , -- id xuất xứ 
id_color BIGINT , -- id màu sắc
id_size BIGINT , -- id size
)
GO
-- phiếu giảm giá
CREATE TABLE voucher(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
voucher_name NVARCHAR(200) DEFAULT NULL, -- tên
quantity INT DEFAULT NULL, -- số lượng
date_begin DATE DEFAULT NULL, -- ngày bắt đầu
date_end DATE DEFAULT NULL, -- ngày kết thúc
minimum_price DECIMAL(20,3) DEFAULT NULL, -- giá tối thiểu
maximum_price DECIMAL(20,3) DEFAULT NULL, --	giá đối đa
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
voucher_status INT DEFAULT 1, -- trạng thái
)
<<<<<<< HEAD
insert into voucher values
('voucher43812',N'voucher 1',100,'2002-12-12','2002-12-12',0.000,300.000,10.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('voucher92374',N'voucher 2',100,'2002-12-12','2002-12-12',300.000,1000.000,15.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('voucher73465',N'voucher 3',100,'2002-12-12','2002-12-12',1000.000,2000.000,25.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('voucher34254',N'voucher 4',100,'2002-12-12','2002-12-12',2000.000,3500.000,40.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('voucher84267',N'voucher 5',100,'2002-12-12','2002-12-12',3500.000,6000.000,60.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('voucher92384',N'voucher 6',100,'2002-12-12','2002-12-12',6000.000,10000.000,90.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT),
('voucher82375',N'voucher 7',100,'2002-12-12','2002-12-12',10000.000,0.000,130.000,'2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT)
select *
from 
voucher

=======
>>>>>>> 5b152e281ae51b83f225e905b5938da565525b9f
GO
-- yêu thích 
CREATE TABLE favourite(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
favourite_status INT DEFAULT 1, -- trạng thái
<<<<<<< HEAD
)
insert into favourite values
('ma0001',DEFAULT),
('ma0002',DEFAULT),
('ma0003',DEFAULT),
('ma0004',DEFAULT),
('ma0005',DEFAULT),
('ma0006',DEFAULT),
('ma0007',DEFAULT)
select *
from 
favourite
GO
-- voucher khách hàng
CREATE TABLE voucher_client(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
quantity INT DEFAULT NULL, -- số lượng
date_begin DATE DEFAULT NULL, -- ngày bắt đầu
date_end DATE DEFAULT NULL, -- ngày kết thúc
reduced_price DECIMAL(20,3) DEFAULT NULL, --	giá trị giảm
date_create DATE DEFAULT NULL, -- ngày tạo
date_update DATE DEFAULT NULL, -- ngày sửa
user_create NVARCHAR(200) DEFAULT NULL, -- người tạo
user_update NVARCHAR(200) DEFAULT NULL, -- người sửa
in_ranks BIGINT DEFAULT NULL,-- id ranks
voucher_client_status INT DEFAULT 1, -- trạng thái
)
insert into voucher_client values
('vc73859',100,'2002-12-12','2002-12-12',15.000,'2002-12-12','2002-12-12',N'dương',N'dương',1,DEFAULT),
('vc62947',100,'2002-12-12','2002-12-12',20.000,'2002-12-12','2002-12-12',N'dương',N'dương',1,DEFAULT),
('vc73468',100,'2002-12-12','2002-12-12',20.000,'2002-12-12','2002-12-12',N'dương',N'dương',1,DEFAULT),
('vc92746',100,'2002-12-12','2002-12-12',25.000,'2002-12-12','2002-12-12',N'dương',N'dương',2,DEFAULT),
('vc82327',100,'2002-12-12','2002-12-12',25.000,'2002-12-12','2002-12-12',N'dương',N'dương',2,DEFAULT)
select *
from
voucher_client
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
id_favourite BIGINT , -- id yêu thích yêu thích
)
insert into client values
('ma0001',N'Hà Thị Kim Nhung','2002-12-12','0339115608','duongcf2k3@gmail.com',N'true',50,600,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,1,1,1),
('ma0002',N'Khách Hàng 02','2002-12-12','0898629635','duong021003@gmail.com',N'true',50,700,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,1,2,2),
('ma0003',N'Khách Hàng 03','2002-12-12','1234567890','duonglhph22902@fpt.edu.vn',N'true',50,800,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,3,3,3),
('ma0004',N'Khách Hàng 04','2002-12-12','1234512345','01@gmail.com',N'true',50,600,'1','2002-12-12','2002-12-12',N'dương',N'dương',0,1,4,4),
('ma0005',N'Khách Hàng 05','2002-12-12','1234512345','02@gmail.com',N'true',50,700,'1','2002-12-12','2002-12-12',N'dương',N'dương',0,2,5,5),
('ma0006',N'Khách Hàng 06','2002-12-12','1234512345','03@gmail.com',N'true',50,800,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,3,6,6),
('ma0007',N'Khách Hàng 07','2002-12-12','1234512345','03@gmail.com',N'true',50,800,'1','2002-12-12','2002-12-12',N'dương',N'dương',DEFAULT,2,7,7)
select *
from 
client
GO
-- voucher khách hàng chi tiết
CREATE TABLE voucher_client_detail(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
quantity INT DEFAULT NULL, -- số lượng
date_begin DATE DEFAULT NULL, -- ngày bắt đầu
date_end DATE DEFAULT NULL, -- ngày kết thúc
reduced_price DECIMAL(20,3) DEFAULT NULL, --	giá trị giảm
date_create DATE DEFAULT NULL, -- ngày tạo
voucher_client_detail_status INT DEFAULT 1, -- trạng thái
=======
>>>>>>> 5b152e281ae51b83f225e905b5938da565525b9f
id_client BIGINT , -- id khách hàng
)
GO
-- yêu thích chi tiết
CREATE TABLE favourite_details(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
date_create DATE DEFAULT NULL, -- ngày tạo
favourite_details_status INT DEFAULT 1, -- trạng thái
id_favourite BIGINT , -- id yêu thích
id_product_details BIGINT, -- id chi tiết sản phẩm
)
GO
-- giỏ hàng 
CREATE TABLE shopping_cart(
id BIGINT IDENTITY(1,1) PRIMARY KEY ,
code VARCHAR(200) UNIQUE, -- mã
total_shopping_cart DECIMAL(20,3) DEFAULT NULL, -- tổng tiền giỏ hàng
shopping_cart_status INT DEFAULT 1, -- trạng thái
id_client BIGINT , -- id khách hàng
)
<<<<<<< HEAD
insert into client_address values
('ma0001',N'Hà Thị Kim Nhung','0339115608',N'phú mỹ',N'Phường Mỹ Đình 2',N'Quận Nam Từ Liêm',N'Thành phố Hà Nội','2002-12-12',1,1),
('ma0002',N'Khách Hàng 02','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',DEFAULT,2),
('ma0003',N'Khách Hàng 02','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,2),
('ma0004',N'Khách Hàng 03','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',DEFAULT,3),
('ma0005',N'Hà Thị Kim Nhung','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',DEFAULT,1),
('ma0006',N'Khách Hàng 03','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,3),
('ma0007',N'Khách Hàng 04','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,4),
('ma0008',N'Khách Hàng 05','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,5),
('ma0009',N'Khách Hàng 06','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,6),
('ma00010',N'Khách Hàng 07','0898629635',N'phú mỹ',N'mỹ đình 2',N'nam từ liêm',N'HN','2002-12-12',1,7)
select *
from 
client_address
=======
>>>>>>> 5b152e281ae51b83f225e905b5938da565525b9f
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
<<<<<<< HEAD
insert into invoice values
('ma0001','8:00','2002-12-12',999.000,999.000,0.000,1000.000,1000.000,1.000,N'không',5,1,1,1,1,1,1),
('ma0002','8:00','2002-12-12',999.000,999.000,0.000,1000.000,1000.000,1.000,N'không',5,2,2,1,2,1,1),
('ma0003','8:00','2002-12-12',999.000,999.000,0.000,1000.000,1000.000,1.000,N'không',5,1,1,1,1,1,1)
select *
from 
invoice
=======
>>>>>>> 5b152e281ae51b83f225e905b5938da565525b9f
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
-- địa chỉ - khách hàng 
ALTER TABLE client_address ADD FOREIGN KEY(id_client) REFERENCES client(id)
-- yêu thích - khách hàng 
ALTER TABLE favourite ADD FOREIGN KEY(id_client) REFERENCES client(id)
-- yêu thích chi tiết - yêu thích
ALTER TABLE favourite_details ADD FOREIGN KEY(id_favourite) REFERENCES favourite(id)
-- yêu thích chi tiết - sản phẩm
ALTER TABLE favourite_details ADD FOREIGN KEY(id_product_details) REFERENCES product_details(id)
-- giỏ hàng - khách hàng
ALTER TABLE shopping_cart ADD FOREIGN KEY(id_client) REFERENCES client(id)
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




