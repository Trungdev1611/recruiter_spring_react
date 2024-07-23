import { FacebookFilled, GoogleOutlined, LinkedinFilled } from '@ant-design/icons'
import ButtonCommon from '../common/button/ButtonCommon'
import  { TextGreen } from '../common/typo/TypoAntd'
import { BtnContainer, CheckboxStyled, TypoStyled } from './Signup/style'
import {  Divider, Form } from 'antd'



const Optionlogin = () => {
    return (
        <div>
            <TypoStyled level={5}
            >Hoặc đăng nhập bằng</TypoStyled>
            <BtnContainer>
                <ButtonCommon icon={<GoogleOutlined />} type="primary" danger >Google</ButtonCommon>
                <ButtonCommon icon={<FacebookFilled />} background="#1877f2">Facebook</ButtonCommon>
                <ButtonCommon icon={<LinkedinFilled />} background="#084da7">Linkedin</ButtonCommon>
            </BtnContainer>

            <Form
                name="optionlogin"
                initialValues={{
                    remember1: true,
                }}>
                <Form.Item
                    name="remember1"
                    valuePropName="checked"
                >
                    <CheckboxStyled >
                        Bằng việc đăng nhập bằng tài khoản mạng xã hội, tôi đã đọc và đồng ý với
                        Điều khoản dịch vụ và Chính sách bảo mật của <TextGreen style={{ color: "#00b14f" }} text={"TopHire"} /> </CheckboxStyled>
                </Form.Item>
            </Form>
            <TypoStyled level={5}>
                Bạn đã có tài khoản? <TextGreen text={`Đăng nhập ngay`} /> </TypoStyled>
                <Divider />
                <p style={{textAlign: "center"}}>Bạn gặp khó khăn khi tạo tài khoản?</p>
                <TypoStyled level={5}>Vui lòng gọi tới số <TextGreen text={`(024) 6680 5588`} style={{ fontWeight: "600"}}/> (giờ hành chính).</TypoStyled>
                <div style={{textAlign: "center", margin: "20px"}}>
                <TextGreen  text={`© 2016. All Rights Reserved. TopCV Vietnam JSC.`} />
                </div>
        </div>
    )
}

export default Optionlogin