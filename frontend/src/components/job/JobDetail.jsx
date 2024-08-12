import ButtonCommon from '../common/button/ButtonCommon'
import { Button } from 'antd'

export default function JobDetail() {
    return (
        <div className='mx-4 p-3 bg-slate-200'>
            {/*Start General Info */}
            <div className='flex flex-col gap-3 bg-white shadow-sm p-3 rounded-md'>
                <h2 className='text-lg font-semibold'>Leader Marketing Facebook Ads/ Trưởng Nhóm Marketing - Thu Nhập 15 - 50tr - Cơ Hội Thăng Tiến Cao Và Định Hướng Cổ Phần</h2>
                <div className='flex justify-between items-center'>
                    <div className='flex flex-row gap-x-2 items-center '>
                        <div className='bg-primary text-white w-[36px] h-[36px] rounded-full flex justify-center items-center'><span>$</span></div>
                        <div>
                            <p >Mức lương</p>
                            <p className='font-medium'>15 - 50 triệu</p>
                        </div>
                    </div>
                    <div className='flex flex-row gap-x-2 items-center '>
                        <div className='bg-primary text-white w-[36px] h-[36px] rounded-full flex justify-center items-center'><span>$</span></div>
                        <div>
                            <p >Mức lương</p>
                            <p className='font-medium'>15 - 50 triệu</p>
                        </div>
                    </div>
                    <div className='flex flex-row gap-x-2 items-center '>
                        <div className='bg-primary text-white w-[36px] h-[36px] rounded-full flex justify-center items-center'><span>$</span></div>
                        <div>
                            <p >Mức lương</p>
                            <p className='font-medium'>15 - 50 triệu</p>
                        </div>
                    </div>
                </div>
                <div className='flex'>
                    <span className='bg-slate-100 font-light px-2 py-1 rounded-sm text-sm'>Hạn nộp hồ sơ: 30/09/2024</span>
                </div>
                <div className='flex justify-between items-center gap-8'>
                    <ButtonCommon>Ứng tuyển ngay</ButtonCommon>
                    <Button >Lưu tin</Button>
                </div>
            </div>
            {/*End General Info */}

            {/* Start Details */}
            <div className='flex flex-col gap-3 bg-white shadow-sm py-3 px-6 rounded-md mt-6'>
                <div className='flex justify-between items-center'>
                    <h2 className='text-lg font-semibold'>Chi tiết tin tuyển dụng</h2>
                    <Button className='text-primary font-semibold border-primary' >Gửi tôi việc làm tương tự</Button>
                </div>

                <div>
                    <h4 className='text-base font-medium mb-2 mt-4'>Mô tả công việc</h4>
                    <ul className='list-disc pl-5 text-sm'>
                        <li>Lập kế hoạch quý, tháng, tuần và triển khai các chiến dịch marketing trên nền tảng Facebook Ads.</li>
                        <li>Quản lý đội nhóm FB Ads (5-10 người). Dẫn dắt và hỗ trợ đội nhóm đạt được các mục tiêu đã đề ra.</li>
                        <li>Quản lý ngân sách MKT</li>
                        <li>Theo dõi, thống kê và phân tích số liệu, đưa ra các phương án tối ưu cho đội nhóm.</li>
                        <li>Công tác tuyển dụng và đào tạo nhân viên mới</li>
                        <li>Công tác đào tạo, họp giao ban, họp tổng kết team.</li>
                        <li>Tổng hợp, phân tích, báo cáo kết quả hoạt động lên BLĐ</li>
                        <li>Chịu trách nhiệm về kết quả hoạt động của team trước BLĐ</li>
                    </ul>

                    <h4 className='text-base font-medium mb-2 mt-4'>Yêu cầu ứng viên</h4>
                    <ul className='list-disc pl-5 text-sm'>
                        <li>Kinh nghiệm
                            <ul className='list-disc pl-5'>
                                <li>Chuyên môn Facebook Ads:
                                    <ul className='list-disc pl-5'>
                                        <li>Thời gian: Tối thiểu 2 năm</li>
                                        <li>Ngân sách chạy tổng: 2 tỷ</li>
                                        <li>Ngân sách trung bình/tháng: 100tr</li>
                                        <li>Kỹ năng chạy: Chuyển đổi & mess</li>
                                    </ul>
                                </li>
                                <li>Kinh nghiệm quản lý:
                                    <ul className='list-disc pl-5'>
                                        <li>Đã từng quản lý team tối thiểu 3 người</li>
                                        <li>Đã từng làm quản lý tối thiểu 1 năm</li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>Kỹ năng
                            <ul className='list-disc pl-5 text-sm'>
                                <li>Tiếng anh căn bản</li>
                                <li>Tin học văn phòng căn bản (excel, word, sheet, hàm cơ bản,...)</li>
                                <li>Thành thạo các công cụ media (capcut, canva, pts căn bản,...)</li>
                                <li>Kỹ năng giao tiếp tốt</li>
                                <li>Biết lắng nghe, ghi nhận và đánh giá tổng quan</li>
                                <li>Biết gọi tên vấn đề</li>
                                <li>Kỹ năng truyền đạt, thuyết trình tốt</li>
                                <li>Biết tổ chức teamwork hiệu quả</li>
                                <li>Kỹ năng lập kế hoạch, phân tích, tổng hợp, làm báo cáo</li>
                            </ul>
                        </li>
                        <li>Tính cách
                            <ul className='list-disc pl-5 text-sm'>
                                <li>Thẳng thắn, trung thực, chính trực</li>
                                <li>Vui vẻ, hoà đồng, biết lắng nghe</li>
                            </ul>
                        </li>
                    </ul>

                    <h4 className='text-base font-medium mb-2 mt-4'>Quyền lợi</h4>
                    <ul className='list-disc pl-5 text-sm'>
                        <li>Lương: 10 - 18tr (Deal chi tiết khi PV)</li>
                        <li>Thưởng doanh thu cá nhân: 1.5% doanh thu cá nhân</li>
                        <li>Thưởng doanh thu team: 1 - 1.5% tổng doanh thu team</li>
                        <li>Thưởng KPI cá nhân</li>
                        <li>Thưởng Best MKT tháng</li>
                        <li>Thưởng kiểm soát chi phí</li>
                        <li>Thưởng test sản phẩm win</li>
                        <li>Tổng thu nhập: 15tr - 100tr (Chủ yếu thu nhập ở các khoản % hoa hồng doanh thu)</li>
                        <li>Hỗ trợ bữa trưa</li>
                        <li>Thưởng lễ, tết, thưởng cuối năm, thưởng thi đua...</li>
                        <li>Xét thăng tiến, tăng lương theo chu kỳ của công ty.</li>
                        <li>Cấp trên thân thiện, sẵn sàng lắng nghe và hướng dẫn giúp nâng cao năng lực.</li>
                        <li>Môi trường làm việc chuyên nghiệp, năng động, công bằng, rõ ràng.</li>
                        <li>Chế độ nghỉ mát, du lịch hàng năm.</li>
                        <li>Gửi xe: Free</li>
                    </ul>
                </div>


            </div>
            {/*End Details */}
        </div>
    )
}
