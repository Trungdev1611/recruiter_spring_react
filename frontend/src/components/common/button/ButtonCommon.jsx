import { Button } from "antd";
import "./btn.css"
import styled from "styled-components";

const ButtonStyled = styled(Button)`
background: ${props => props.background || `linear-gradient(to right, #2daa65, #57c557)`};
border: none; /* Optional: Removes default border if you want a cleaner look */
color: white; 
width: ${props => props.width || '100%'}; 
transition: background-color 0.3s ease;
&:hover {
  background: #32a465 !important; 
  color: white !important; 
  }
`
const ButtonCommon = (props) => {

  return (
    <ButtonStyled 
    size={props.size || "default"}
     {...props}>
      {props.children}
    </ButtonStyled>
  );
};



export default ButtonCommon;
