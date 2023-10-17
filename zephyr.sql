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




