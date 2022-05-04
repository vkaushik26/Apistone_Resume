import { React, useEffect, useState } from "react";
import "antd/dist/antd.css";
import { getProjects, postProjects, getAllDetails } from "../../service/api";
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
import Logout from "../Logout/Logout";
import "bootstrap/dist/css/bootstrap.min.css";
import { navigateFunction } from "../CommonFunction";
import { deleteNotification, saveNotification } from "../CommonFunction";
import {
  EditOutlined,
  EyeOutlined,
  DeleteOutlined,
  ExclamationCircleOutlined,
} from "@ant-design/icons";
const { TextArea } = Input;
const Projects = () => {
  const navigate = useNavigate();
  var key = sessionStorage.getItem("user_id");
  const [isEdit, setIdEdit] = useState(false);
  const { Header, Footer, Sider, Content } = Layout;
  const [inputFields, setInputFields] = useState([]);
  useEffect(() => {
    getAllDetails(key).then((res) => {
      console.log(res.data);
    });
    getProjects(key).then((res) => {
      console.log(res.data.response.projectsDetails);
      setInputFields(res.data.response.projectsDetails);
    });
  }, []);
  const handleAddFields = () => {
    setInputFields([
      ...inputFields,
      {
        projectId: inputFields.length
          ? inputFields[inputFields.length - 1].projectId + 1
          : 0,
        projectName: "",
        clientName: "",
        startDate: "",
        endDate: "",
        expire: "",
        stack: "",
        description: "",
      },
    ]);
    console.log(inputFields);
  };
  const handleRemoveFields = (projectId) => {
    const values = [...inputFields];
    values.splice(
      values.findIndex((value) => value.projectId === projectId),
      1
    );
    setInputFields(values);
    setIdEdit(true);
    deleteNotification();
  };

  const handleChangeInput = (e, projectId) => {
    const newInputFields = inputFields.map((i) => {
      if (projectId === i.projectId) {
        i[e.target.name] = e.target.value;
      }
      return i;
    });
    setIdEdit(true);
    setInputFields(newInputFields);
    console.log(inputFields);
  };

  const isExp = (e, projectId) => {
    console.log(e.target.name);
    const newInputFields = inputFields.map((i) => {
      if (projectId === i.projectId) {
        console.log("hello");
        i[e.target.name] = e.target.checked;
      }
      return i;
    });

    setInputFields(newInputFields);
    console.log(inputFields);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    var obj = { userId: parseInt(key), projectdetails: inputFields };
    console.log(obj);
    postProjects(obj).then((res) => {
      console.log(res);
    });
    setIdEdit(false);
    saveNotification();
  };
  const handleSubmitOnNavigate = (e, des) => {
    var obj = { userId: parseInt(key), projectdetails: inputFields };
    console.log(obj);
    postProjects(obj).then((res) => {
      console.log(res);
    });
    saveNotification();
    navigate(`/${des}`);
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
            isEdit ? navigateFunction() : navigate("/certificates");
          }}
        >
          <Affix style={{ position: "absolute", top: 250, left: 22 }}>
            <Button
              className="shadow1"
              shape="round"
              type="primary"
              icon={<ArrowLeftOutlined />}
            >
              Certificate
            </Button>
          </Affix>
        </Sider>
        <Layout>
          <Affix>
            <Header className="d-flex align-items-center justify-content-between">
              <h2 className="heading">Your Project details</h2>
              <Space className="space">
                <Button
                  shape="round"
                  type="button"
                  size="large"
                  onClick={() => {
                    handleAddFields();
                  }}
                >
                  Add new Project
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
                    key={data.projectId}
                    onSubmit={handleSubmit}
                    style={{ padding: "20px" }}
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
                        handleRemoveFields(data.projectId);
                      }}
                    ></Button>
                    <br />
                    <label>
                      <b>Project Name</b>
                    </label>
                    <Input
                      required="true"
                      value={data?.projectName}
                      name="projectName"
                      onChange={(e) => {
                        handleChangeInput(e, data.projectId);
                      }}
                      size="large"
                      placeholder="Enter project name"
                    />

                    <label>
                      <b>Client Name</b>
                    </label>
                    <Input
                      value={data?.clientName}
                      name="clientName"
                      onChange={(e) => {
                        handleChangeInput(e, data.projectId);
                      }}
                      size="large"
                      placeholder="Enter client name"
                    />

                    <label>
                      <b>Project Start date</b>
                    </label>
                    <Input
                      type="date"
                      value={data?.startDate}
                      name="startDate"
                      onChange={(e) => {
                        handleChangeInput(e, data.projectId);
                      }}
                      size="large"
                      placeholder="large size"
                    />

                    <Checkbox
                      checked={data?.expire}
                      onChange={(e) => isExp(e, data.projectId)}
                      name="expire"
                    >
                      is it ongoing project
                    </Checkbox>
                    <br />

                    {!data?.expire && (
                      <div>
                        <label>
                          <b>Project End date:</b>
                        </label>
                        <Input
                          type="date"
                          value={data?.endDate}
                          name="endDate"
                          onChange={(e) => {
                            handleChangeInput(e, data.projectId);
                          }}
                          size="large"
                          placeholder="large size"
                        />
                      </div>
                    )}

                    <label>
                      <b>Project description</b>
                    </label>
                    <TextArea
                      rows={2}
                      maxLength={200}
                      value={data?.description}
                      name="description"
                      onChange={(e) => {
                        handleChangeInput(e, data.projectId);
                      }}
                      size="large"
                      placeholder="Enter your description"
                    />

                    <label>
                      <b>Project Stack</b>
                    </label>
                    <TextArea
                      value={data?.stack}
                      name="stack"
                      onChange={(e) => {
                        handleChangeInput(e, data.projectId);
                      }}
                      size="large"
                      rows={2}
                      maxLength={200}
                      placeholder="enter project stack"
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
                style={{
                  float: "right",
                  paddingRight: "20px",
                  boxShadow: "5px 5px",
                }}
                type="button"
                size="large"
                onClick={() => {
                  handleAddFields();
                }}
              >
                Add new Project
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
            isEdit ? navigateFunction() : navigate(`/preview`);
          }}
        >
          <Affix style={{ position: "absolute", top: 250, left: 40 }}>
            <Button className="shadow1" shape="round" type="primary">
              Preview{<ArrowRightOutlined />}
            </Button>
          </Affix>
        </Sider>
      </Layout>
    </div>
  );
};
export default Projects;
