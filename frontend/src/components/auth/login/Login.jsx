
import {  Flex, Form } from "antd"

import InputText from "../../common/input/InputText"
import InputPassWord from "../../common/input/InputPassWord"
import { Apiclient } from "../../../api/apiClient";
import { ContainerForm, FlexContainer, ImageContainer } from "../Signup/style";
import TypoAntd, { TextGreen } from "../../common/typo/TypoAntd";
import ButtonCommon from "@src/components/common/button/ButtonCommon";
import Optionlogin from "../Optionlogin";
import signupImage from '@src/assets/image_signup.jpg'
const Login = () => {

  const onFinish = async (values) => {
    console.log('Success:', values);
    let res = await Apiclient.post("/signup", values)
    console.log("res sign up", res)
  };
  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };
  return (
    <FlexContainer>
      <ContainerForm>
        <TypoAntd>Chào mừng bạn đã quay trở lại</TypoAntd>
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

          <Flex align="center" justify="flex-end" >
            <TextGreen  text={`Quên mật khẩu`} style={{marginBottom: "20px"}}  />
          </Flex>

          <Form.Item
          >
            <ButtonCommon htmlType="submit">
              Đăng nhập
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


export default Login