import styled from "styled-components";
import { media } from "../../../utils/media";
import TypoAntd from "../../common/typo/TypoAntd";
import { Checkbox } from "antd";

export const ContainerForm = styled.div`
  margin: 10px 0.5rem;
  overflow-y: auto;
  max-height: 100vh;
  flex: 1;
  /* Ẩn thanh cuộn */
  ::-webkit-scrollbar {
    width: 0px;
    background: transparent; /* Ẩn thanh cuộn cho Chrome/Safari/Webkit */
  }

  -ms-overflow-style: none; /* Ẩn thanh cuộn cho Internet Explorer và Edge */
  scrollbar-width: none; /* Ẩn thanh cuộn cho Firefox */
  @media (min-width: ${media.tablet}) {
    margin: 10px 2rem;
  }
  @media (min-width: ${media.desktop}) {
    margin: 20px 3rem;
  }
  @media (min-width: ${media.largeDesktop}) {
    margin: 20px 4rem;
  }
`;

export const FlexContainer = styled.div`
  display: flex;
  justify-content: space-between;
  flex-direction: column-reverse;
  @media (min-width: ${media.desktop}) {
    flex-direction: row;
    overflow: hidden; /* Đảm bảo chỉ ContainerForm mới cuộn */
    height: 100vh;
  }
`;

export const ImageContainer = styled.div`
  height: 300px;
  & img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: top;
  }
  @media (min-width: ${media.desktop}) {
    position: relative;
    top: 0;
    bottom: 0;
    height: 100vh;
    overflow: hidden;
    width: 450px;
  }
`;

export const BtnContainer = styled.div`
  display: flex;
  justify-content: space-between;
  gap: 1.5rem;
  margin-bottom: 15px;
`;

export const TypoStyled = styled(TypoAntd)`
  text-align: center;
  font-weight: 400 !important;
  font-size: 14px !important;
  color: #6f7882 !important;
`;

export const CheckboxStyled = styled(Checkbox) `
  display: flex;
  margin-top: 10px;
  & span.ant-checkbox {
    position: relative;
    top: -10px;
  }
`