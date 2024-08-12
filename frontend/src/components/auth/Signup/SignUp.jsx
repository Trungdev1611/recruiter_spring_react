import { Checkbox, Form } from "antd"
import ButtonCommon from "../../common/button/ButtonCommon"
import TypoAntd from "../../common/typo/TypoAntd"
import InputText from "../../common/input/InputText"
import InputPassWord from "../../common/input/InputPassWord"
import { ContainerForm, FlexContainer, ImageContainer } from "./style"
import signupImage from './../../../assets/image_signup.jpg'
import Optionlogin from "../Optionlogin"
import { Apiclient } from "../../../api/apiClient"

const SignUpComponent = () => {

  const onFinish = async (values) => {
    console.log('Success:', values);
    let res = await Apiclient.post("auth/signup", values)
    console.log("res sign up", res)
  };
  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };
  return (
    <FlexContainer>
      <ContainerForm>
        <TypoAntd>Chào mừng bạn đến với TopHire</TypoAntd>
        <Form
          name="basic"
          layout="vertical"
          initialValues={{
            remember: true,
          }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          autoComplete="off"
        >
          <Form.Item
            label="Username"
            name="username"
            rules={[
              {
                required: true,
                message: 'Please input your username!',
              },
            ]}
          >
            <InputText placeholder = "Nhập tên tài khoản của bạn" />
          </Form.Item>

          <Form.Item
            label="Email"
            name="email"
            rules={[
              {
                required: true,
                message: 'Please input your email!',
              },
            ]}
          >
            <InputText placeholder = "Nhập email của bạn"/>
          </Form.Item>

          <Form.Item
            label="Password"
            name="password"
            rules={[
              {
                required: true,
                message: 'Please input your password!',
              },
            ]}
          >
            <InputPassWord placeholder = "Nhập mật khẩu của bạn"/>
          </Form.Item>

          <Form.Item
            label="Xác nhận mật khẩu"
            name="confirm_password"
            rules={[
              {
                required: true,
                message: 'Please input your confirm password!',
              },
            ]}
          >
            <InputPassWord placeholder="Xác nhận mật khẩu của bạn"/>
          </Form.Item>


          <Form.Item
            name="remember"
            valuePropName="checked"
          >
            <Checkbox>
              Tôi đã đọc và đồng ý với Điều khoản dịch vụ và Chính sách bảo mật của <span style={{color:"#00b14f" }}>TopHire</span></Checkbox>
          </Form.Item>

          <Form.Item
          >
            <ButtonCommon htmlType="submit">
              Đăng ký
            </ButtonCommon>
          </Form.Item>
        </Form>

       <Optionlogin />
      </ContainerForm>
      <ImageContainer >
        <img src={signupImage} alt="Girl in a jacket" />
      </ImageContainer>
    </FlexContainer>
  )
}


export default SignUpComponent