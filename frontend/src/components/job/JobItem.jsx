import { DollarOutlined } from "@ant-design/icons";
import { faHeart } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";

export default function JobItem({image}) {
    return (
        <div className='p-2 bg-[#ebf0ee] rounded-md  flex shadow-md border border-primary gap-x-3 '>
            <div><img src={image} className=" w-[100px] h-[100px]" alt="logo company" /></div>
            <div className="flex-1">
                <div className='flex flex-col md:flex-row gap-y-2 gap-x-3 justify-between'>
                    <div className="flex-1">
                        <Link to={"id"} className='font-semibold text-lg'>Software Tester (Manual/ Automation) - Thu Nhập Upto $1400</Link>
                        <p className='text-sm font-light '>Công Ty Cổ Phần Công Nghệ SellerSmith</p>
                    </div>
                    <div className='text-primary font-medium md:text-base text-sm'>
                        <DollarOutlined />
                        <span className="ml-2">25 - 40 triệu</span>
                    </div>

                </div>
                <div className="flex justify-between mt-4 items-center">
                    <p className="bg-gray-300 text-xs font-normal p-2 rounded-md ">Hà Nội</p>
                    <p className="text-primary "> <FontAwesomeIcon icon={faHeart} /></p>
                </div>  
            </div>




        </div>
    )
}
