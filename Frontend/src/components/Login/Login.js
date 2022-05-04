import { Form, Input, Button } from "antd";
import message from "antd-message";
import logo from "./logoFinal.png";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
const Login = () => {
  const [pwd, setpwd] = useState(""); //password state
  const [userName, setUsername] = useState(""); //username state
  const navigate = useNavigate();

  const onFinish = (values) => {
    //function called once details submitted
    if (userName) {
      const cred = {
        emailId: userName,
        password: pwd,
      };
      axios
        .post(`http://localhost:3001/api/resume/user/credentials`, cred) //endpoint for authentication
        .then((response) => {
          if (!response.data.error) {
            message.success(
              <div className="messageInfo">Authentication Successful</div>
            );
            var key = Object.keys(response.data);
            var value = Object.values(response.data);
            sessionStorage.setItem(key, value); //session storage to keep track of userId
            navigate("/personal");
          } else {
            message.error(<div className="messageInfo">Wrong password</div>);
          }
        })
        .catch((err) => {
          if (err.response.data.code === 409) {
            message.error(
              <div className="messageInfo">Email is not registered</div>
            );
          }
        });
    }
  };

  const onFinishFailed = (errorInfo) => {
    //function called once no proper inputs given
    console.log("Failed:", errorInfo);
  };
  return (
    <section className="vh-100">
      <div className="container py-5 h-100">
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
                <h3>Login</h3>
              </center>
              <br />
            </div>
            <Form //form imported from antd
              name="basic"
              className="finalForm"
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
                  className="formItem"
                  name="password"
                  rules={[
                    {
                      required: true,
                      message: "Please input your password!",
                    },
                  ]}
                >
                  <Input
                    autoComplete="off"
                    onChange={(e) => setpwd(e.target.value)} //state updated once entries entered
                    type="password"
                    className="form-control form-control-lg"
                    minLength={8}
                    id="form1Example13"
                  />
                </Form.Item>
              </div>
              <Form.Item>
                <br />
                <Button
                  className="btn btn-primary btn-lg btn-block"
                  htmlType="submit"
                  id="button"
                  type="primary"
                >
                  Login
                </Button>
                <br />
                <br />
                <Link to="/Signup">
                  <Button //button directs to Signup page
                    className="btn btn-primary btn-lg btn-block"
                    type="link"
                    id="button1"
                  >
                    Sign Up
                  </Button>
                </Link>
              </Form.Item>
            </Form>
          </div>
        </div>
      </div>
    </section>
  );
};
export default Login;
