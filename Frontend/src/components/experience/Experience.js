import { React, useEffect, useState } from "react";
import "antd/dist/antd.css";
import { getprofessional, postprofessional } from "../../service/api";
import { useNavigate } from "react-router";
import { Form, Input, Button, Space, Layout, Affix, Checkbox } from "antd";
import {
  MinusCircleOutlined,
  PlusOutlined,
  UploadOutlined,
  ArrowLeftOutlined,
  ArrowRightOutlined,
  CloseOutlined,
} from "@ant-design/icons";
import { UserOutlined } from "@ant-design/icons";
import "bootstrap/dist/css/bootstrap.min.css";
import Logout from "../Logout/Logout";
import { navigateFunction } from "../CommonFunction";
import { deleteNotification, saveNotification } from "../CommonFunction";
import {
  EditOutlined,
  EyeOutlined,
  DeleteOutlined,
  ExclamationCircleOutlined,
} from "@ant-design/icons";
const { TextArea } = Input;
const Experience = () => {
  const navigate = useNavigate();
  var key = sessionStorage.getItem("user_id");
  const [isEdit, setIdEdit] = useState(false);
  const { Header, Footer, Sider, Content } = Layout;
  const [inputFields, setInputFields] = useState([]);
  useEffect(() => {
    getprofessional(key).then((res) => {
      console.log(res.data.response.professionalDetails);
      setInputFields(res.data.response.professionalDetails);
    });
  }, []);
  const handleAddFields = () => {
    setInputFields([
      ...inputFields,
      {
        pId: inputFields.length
          ? inputFields[inputFields.length - 1].pId + 1
          : 0,
        companyName: "",
        role: "",
        startDate: "",
        expire: "",
        endDate: "",
        achievements: "",
      },
    ]);
    console.log(inputFields);
  };
  const handleRemoveFields = (pId) => {
    const values = [...inputFields];
    values.splice(
      values.findIndex((value) => value.pId === pId),
      1
    );
    setInputFields(values);
    setIdEdit(true);
    deleteNotification();
  };

  const handleChangeInput = (e, pId) => {
    const newInputFields = inputFields.map((i) => {
      if (pId === i.pId) {
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
    console.log("InputFields", inputFields);
    let obj = { userId: parseInt(key), professional: inputFields };
    postprofessional(obj);
    setIdEdit(false);
    saveNotification();
  };
  const isExp = (e, pId) => {
    console.log(e.target.name);
    const newInputFields = inputFields.map((i) => {
      if (pId === i.pId) {
        console.log("hello");
        i[e.target.name] = e.target.checked;
      }
      return i;
    });

    setInputFields(newInputFields);
    console.log(inputFields);
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
            isEdit ? navigateFunction() : navigate("/education");
          }}
        >
          <Affix style={{ position: "absolute", top: 250, left: 22 }}>
            <Button
              className="shadow1"
              shape="round"
              type="primary"
              icon={<ArrowLeftOutlined />}
            >
              Education
            </Button>
          </Affix>
        </Sider>
        <Layout>
          <Affix>
            <Header className="d-flex align-items-center justify-content-between">
              <h2 className="heading">Your Professional details</h2>
              <Space className="space">
                <Button
                  shape="round"
                  type="button"
                  size="large"
                  onClick={() => {
                    handleAddFields();
                  }}
                >
                  Add new Profession
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
                    key={data.pId}
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
                        handleRemoveFields(data.pId);
                      }}
                    ></Button>
                    <br />
                    <label>
                      <b>Company Name</b>
                    </label>
                    <Input
                      value={data?.companyName}
                      name="companyName"
                      onChange={(e) => {
                        handleChangeInput(e, data.pId);
                      }}
                      size="large"
                      placeholder="Enter your Company name"
                    />

                    <label>
                      <b>Role</b>
                    </label>
                    <Input
                      value={data?.role}
                      name="role"
                      onChange={(e) => {
                        handleChangeInput(e, data.pId);
                      }}
                      size="large"
                      placeholder="Enter your role"
                    />

                    <label>
                      <b>Start date</b>
                    </label>
                    <Input
                      type="date"
                      value={data?.startDate}
                      name="startDate"
                      onChange={(e) => {
                        handleChangeInput(e, data.pId);
                      }}
                      size="large"
                      placeholder="select "
                    />

                    <Checkbox
                      checked={data?.expire}
                      onChange={(e) => isExp(e, data.pId)}
                      name="expire"
                    >
                      is currently working
                    </Checkbox>
                    <br />

                    {!data?.expire && (
                      <div>
                        <label>
                          <b>Last working day</b>
                        </label>
                        <Input
                          type="date"
                          value={data?.endDate}
                          name="endDate"
                          onChange={(e) => {
                            handleChangeInput(e, data.pId);
                          }}
                          size="large"
                          placeholder="large"
                        />
                      </div>
                    )}

                    <label>
                      <b>Achievements</b>
                    </label>
                    <TextArea
                      rows={2}
                      maxLength={200}
                      value={data?.achievements}
                      name="achievements"
                      onChange={(e) => {
                        handleChangeInput(e, data.pId);
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
                Add new Profession
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
            isEdit ? navigateFunction() : navigate("/certificates");
          }}
        >
          <Affix style={{ position: "absolute", top: 250, left: 40 }}>
            <Button className="shadow1" shape="round" type="primary">
              Certificate{<ArrowRightOutlined />}
            </Button>
          </Affix>
        </Sider>
      </Layout>
    </div>
  );
};

export default Experience;
