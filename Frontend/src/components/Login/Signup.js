import { Form, Input, Button } from "antd";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import message from "antd-message";
import axios from "axios";
import logo from "./logoFinal.png";
const Signup = () => {
  const [pwd, setPwd] = useState(""); //password state
  const [pwdc, setPwdc] = useState(""); //confirm password state
  const [username, setUsername] = useState(""); //username state
  const navigate = useNavigate();
  const onFinish = (values) => {
    //function called once details submitted
    if (pwd === pwdc) {
      const cred = {
        emailId: username,
        password: pwd,
      };
      axios
        .post(`http://localhost:3001/api/resume/user`, cred) //endpoint for saving credentials
        .then((res) => {
          message.success(
            <div className="messageInfo">user created successfully</div>
          );
          var key = Object.keys(res.data);
          var value = Object.values(res.data);
          sessionStorage.setItem(key, value); //session storage to keep track of userId
          navigate("/personal");
        })
        .catch((res) => {
          if (res.response.data.code === 409)
            message.error(
              <div className="messageInfo">Email already present</div>
            );
        });
    } else if (pwd !== pwdc) {
      message.error(
        <div className="messageInfo">
          password and confirm password dont match
        </div>
      );
    } else {
      message.error(<div className="messageInfo">user creation failed</div>);
    }
  };
  const onFinishFailed = (errorInfo) => {
    //function called once no proper inputs given
    console.log("Failed:", errorInfo);
  };
  return (
    <section className="vh-100">
      <div className="container py-0 h-100">
        <div className="row d-flex align-items-center justify-content-center h-100">
          <div className="col-md-8 col-lg-7 col-xl-6">
            <img
              src="https://www.resumebuilder.com/wp-content/themes/resumebuilder/assets/images/desktop-with-resume-.svg"
              className="img-fluid"
              alt="something"
            />
          </div>
          <div className="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
            <div className="heading">
              <img src={logo} className="imageR" />
              <center>
                <h3>Sign up</h3>
              </center>
              <br />
            </div>
            <Form //form imported from antd
              id="finalForm"
              name="basic"
              labelCol={{
                span: 8,
              }}
              wrapperCol={{
                span: 16,
              }}
              initialValues={{
                remember: true,
              }}
              onFinish={onFinish}
              onFinishFailed={onFinishFailed}
              autoComplete="off"
            >
              <div className="form-outline mb-4">
                <label className="form-label" for="form1Example13">
                  Email address
                </label>
                <Form.Item
                  name="Email"
                  className="formItem"
                  rules={[
                    {
                      required: true,
                      message: "Please input your Email!",
                    },
                  ]}
                >
                  <Input
                    onChange={(e) => setUsername(e.target.value)} //state updated once entries entered
                    type="email"
                    id="form1Example13"
                    className="form-control form-control-lg"
                  />
                </Form.Item>
              </div>
              <div className="form-outline mb-4">
                <label class="form-label" for="form1Example23">
                  Password
                </label>
                <Form.Item
                  name="password"
                  className="formItem"
                  rules={[
                    {
                      required: true,
                      message: "Please input your password!",
                    },
                  ]}
                >
                  <Input
                    autoComplete="off"
                    onChange={(e) => setPwd(e.target.value)} //state updated once entries entered
                    type="password"
                    className="form-control form-control-lg"
                    minLength={8}
                    id="form1Example13"
                  />
                </Form.Item>
              </div>
              <div className="form-outline mb-4">
                <label class="form-label" for="form1Example23">
                  Confirm-Password
                </label>
                <Form.Item
                  name="confirmPassword"
                  className="formItem"
                  rules={[
                    {
                      required: true,
                      message: "Please input your confirm password!",
                    },
                  ]}
                >
                  <Input
                    autoComplete="off"
                    onChange={(e) => setPwdc(e.target.value)} //state updated once entries entered
                    type="password"
                    className="form-control form-control-lg"
                    minLength={8}
                    id="form1Example13"
                  />
                </Form.Item>
              </div>
              <br />
              <br />
              <Form.Item>
                <Button htmlType="submit" id="buttonSingup" type="primary">
                  Sign Up
                </Button>
                <br />
                <br />
              </Form.Item>
            </Form>
          </div>
        </div>
      </div>
    </section>
  );
};
export default Signup;
