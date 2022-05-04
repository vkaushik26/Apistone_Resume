import { React, useEffect, useState } from "react";
import "antd/dist/antd.css";
import { getEducation, postEducation } from "../../service/api";
import { useNavigate } from "react-router";
import { Form, Input, Button, Space, Alert, Layout, Affix } from "antd";
import {
  MinusCircleOutlined,
  ArrowLeftOutlined,
  ArrowRightOutlined,
  PlusOutlined,
  UploadOutlined,
  CloseOutlined,
} from "@ant-design/icons";
import { UserOutlined } from "@ant-design/icons";
import "bootstrap/dist/css/bootstrap.min.css";
import { navigateFunction } from "../CommonFunction";
import { deleteNotification, saveNotification } from "../CommonFunction";
import {
  EditOutlined,
  EyeOutlined,
  DeleteOutlined,
  ExclamationCircleOutlined,
} from "@ant-design/icons";
import Logout from "../Logout/Logout";
const { TextArea } = Input;
const Education = () => {
  const { Header, Footer, Sider, Content } = Layout;
  const navigate = useNavigate();
  const [isEdit, setIdEdit] = useState(false);
  const [inputFields, setInputFields] = useState([]);
  var key = sessionStorage.getItem("user_id");
  useEffect(() => {
    getEducation(key).then((res) => {
      console.log(res.data.response.educationDetails);
      setInputFields(res.data.response.educationDetails);
    });
  }, []);
  const handleAddFields = () => {
    setInputFields([
      ...inputFields,
      {
        eId: inputFields.length
          ? inputFields[inputFields.length - 1].eId + 1
          : 0,
        university: "",
        specialization: "",
        cgpa: "",
        fromDate: "",
        toDate: "",
        achievements: "",
      },
    ]);
    console.log(inputFields);
  };
  const handleRemoveFields = (eId) => {
    const values = [...inputFields];
    values.splice(
      values.findIndex((value) => value.eId === eId),
      1
    );
    setInputFields(values);
    setIdEdit(true);
    deleteNotification();
  };

  const handleChangeInput = (e, eId) => {
    const newInputFields = inputFields.map((i) => {
      if (eId === i.eId) {
        i[e.target.name] = e.target.value;
      }
      return i;
    });
    setIdEdit(true);
    setInputFields(newInputFields);
    console.log(inputFields);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    let obj = { userId: parseInt(key), education: inputFields };
    console.log(obj);
    postEducation(obj).then((res) => {
      console.log(res);
    });
    setIdEdit(false);
    saveNotification();
  };

  const layout = {
    labelCol: {
      span: 8,
    },
    wrapperCol: {
      span: 16,
    },
  };
  return (
    <div>
      <Layout>
        <Sider
          onClick={() => {
            isEdit ? navigateFunction() : navigate("/personal");
          }}
        >
          <Affix style={{ position: "absolute", top: 250, left: 22 }}>
            <Button
              className="shadow1"
              shape="round"
              type="primary"
              icon={<ArrowLeftOutlined />}
            >
              Personal
            </Button>
          </Affix>
        </Sider>
        <Layout>
          <Affix>
            <Header className="d-flex align-items-center justify-content-between">
              <h2 className="heading">Your Education details</h2>
              <Space className="space">
                <Button
                  shape="round"
                  type="button"
                  size="large"
                  onClick={() => {
                    handleAddFields();
                  }}
                >
                  Add new Education
                </Button>
                <Button
                  shape="round"
                  type="submit"
                  icon={<UploadOutlined />}
                  size="large"
                  onClick={handleSubmit}
                >
                  Save
                </Button>
                <Logout />
              </Space>
            </Header>
          </Affix>
          <Content>
            {inputFields.length <= 0 && (
              <h3 style={{ textAlign: "center", padding: "40px" }}>
                you don't have any saved Items
              </h3>
            )}
            {inputFields.map((data) => (
              <div>
                <div className="allItems">
                  <form
                    key={data.eId}
                    onSubmit={handleSubmit}
                    style={{ padding: "8px" }}
                  >
                    <Button
                      className="btn btn-danger"
                      shape="round"
                      style={{
                        color: "white",
                        float: "right",
                        paddingRight: "20px",
                      }}
                      type="submit"
                      icon={<CloseOutlined />}
                      size="large"
                      onClick={() => {
                        handleRemoveFields(data.eId);
                      }}
                    ></Button>
                    <br />
                    <label>
                      <b>University/School Name</b>
                    </label>
                    <Input
                      required
                      value={data?.university}
                      name="university"
                      onChange={(e) => {
                        handleChangeInput(e, data.eId);
                      }}
                      size="large"
                      placeholder="Enter your University/School name"
                    />

                    <label>
                      <b>Specialization</b>
                    </label>
                    <Input
                      value={data?.specialization}
                      name="specialization"
                      onChange={(e) => {
                        handleChangeInput(e, data.eId);
                      }}
                      size="large"
                      placeholder="Enter your specialization"
                    />

                    <label>
                      <b>CGPA</b>
                    </label>
                    <Input
                      type="Number"
                      value={data?.cgpa}
                      name="cgpa"
                      onChange={(e) => {
                        handleChangeInput(e, data.eId);
                      }}
                      size="large"
                      placeholder="Enter your CGPA"
                    />
                    <label>
                      <b>Start date</b>
                    </label>

                    <Input
                      type="date"
                      value={data?.fromDate}
                      name="fromDate"
                      onChange={(e) => {
                        handleChangeInput(e, data.eId);
                      }}
                      size="large"
                      placeholder="Select your joining date"
                    />
                    <label>
                      <b>End date</b>
                    </label>

                    <Input
                      type="date"
                      value={data?.toDate}
                      name="toDate"
                      onChange={(e) => {
                        handleChangeInput(e, data.eId);
                      }}
                      size="large"
                      placeholder="Select your end date"
                    />
                    <label>
                      <b>Achievements</b>
                    </label>

                    <TextArea
                      rows={2}
                      maxLength={200}
                      value={data?.achievements}
                      name="achievements"
                      onChange={(e) => {
                        handleChangeInput(e, data.eId);
                      }}
                      size="large"
                      placeholder="Enter your all Achievements"
                    />
                  </form>
                </div>
                <div>
                  <br />
                  <br />
                </div>
              </div>
            ))}
          </Content>
          <Footer>
            <Space style={{ float: "right" }}>
              <Button
                shape="round"
                style={{ float: "right", paddingRight: "20px" }}
                type="button"
                size="large"
                onClick={() => {
                  handleAddFields();
                }}
              >
                Add new Education
              </Button>
              <Button
                shape="round"
                style={{ float: "right", paddingRight: "20px" }}
                type="submit"
                icon={<UploadOutlined />}
                size="large"
                onClick={handleSubmit}
              >
                Save
              </Button>
            </Space>
          </Footer>
        </Layout>
        <Sider
          onClick={() => {
            isEdit ? navigateFunction() : navigate("/experience");
          }}
        >
          <Affix style={{ position: "absolute", top: 250, left: 40 }}>
            <Button className="shadow1" shape="round" type="primary">
              Experience{<ArrowRightOutlined />}
            </Button>
          </Affix>
        </Sider>
      </Layout>
    </div>
  );
};

export default Education;
