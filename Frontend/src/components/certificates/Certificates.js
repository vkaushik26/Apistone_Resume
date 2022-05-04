import { React, useEffect, useState } from "react";
import "antd/dist/antd.css";
import { getCertificates, postCertificates } from "../../service/api";
import { useNavigate } from "react-router";
import {
  Form,
  Input,
  Button,
  Space,
  Layout,
  Affix,
  notification,
  Checkbox,
} from "antd";
import {
  MinusCircleOutlined,
  PlusOutlined,
  UploadOutlined,
  ArrowLeftOutlined,
  ArrowRightOutlined,
  CloseOutlined,
} from "@ant-design/icons";
import { UserOutlined } from "@ant-design/icons";
import { deleteNotification, saveNotification } from "../CommonFunction";
import "bootstrap/dist/css/bootstrap.min.css";
import { navigateFunction } from "../CommonFunction";
import {
  EditOutlined,
  EyeOutlined,
  DeleteOutlined,
  ExclamationCircleOutlined,
} from "@ant-design/icons";
import "./Certificates.css";
import Logout from "../Logout/Logout";
const { TextArea } = Input;
const { Header, Footer, Sider, Content } = Layout;
const Certificates = () => {
  const navigate = useNavigate();
  const [isEdit, setIdEdit] = useState(false);
  const [inputFields, setInputFields] = useState([]);
  let isExpire = false;
  var key = sessionStorage.getItem("user_id");
  useEffect(() => {
    getCertificates(key).then((res) => {
      console.log(res.data);
      setInputFields(res.data.response.certificationsDetail);
    });
  }, []);

  const handleAddFields = () => {
    setInputFields([
      ...inputFields,
      {
        certificateId: inputFields.length
          ? inputFields[inputFields.length - 1].certificateId + 1
          : 0,
        certificateName: "",
        issuer: "",
        issueDate: "",
        expire: "",
        expiryDate: "",
        credentialId: "",
        credentialUrl: "",
      },
    ]);
    console.log(inputFields);
  };
  const handleRemoveFields = (certificateId) => {
    const values = [...inputFields];
    values.splice(
      values.findIndex((value) => value.certificateId === certificateId),
      1
    );
    setInputFields(values);
    setIdEdit(true);
    deleteNotification();
  };

  const handleChangeInput = (e, id) => {
    const newInputFields = inputFields.map((i) => {
      if (id === i.certificateId) {
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

    let obj = { userId: parseInt(key), certifications: inputFields };
    console.log(obj);
    postCertificates(obj).then((res) => {
      console.log("saved");
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
  const isExp = (e, certificateId) => {
    console.log(e.target.name);
    const newInputFields = inputFields.map((i) => {
      if (certificateId === i.certificateId) {
        console.log("hello");
        i[e.target.name] = e.target.checked;
      }
      return i;
    });

    setInputFields(newInputFields);
    console.log(inputFields);
  };
  return (
    <div>
      <Layout>
        <Sider
          onClick={() => {
            isEdit ? navigateFunction() : navigate("/experience");
          }}
        >
          <Affix style={{ position: "absolute", top: 250, left: 22 }}>
            <Button
              className="shadow1"
              shape="round"
              type="primary"
              icon={<ArrowLeftOutlined />}
            >
              Experience
            </Button>
          </Affix>
        </Sider>

        <Layout>
          <Affix>
            <Header className="d-flex align-items-center justify-content-between">
              <div>
                <h2 className="heading">Your Certificate details</h2>
              </div>
              <Space className="space">
                <Button
                  shape="round"
                  type="button"
                  size="large"
                  onClick={() => {
                    handleAddFields();
                  }}
                >
                  Add new Certificate
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
                    key={data.certificateId}
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
                        handleRemoveFields(data.certificateId);
                      }}
                    ></Button>
                    <br />
                    <label>
                      <b>Certificate Name</b>
                    </label>
                    <Input
                      required
                      value={data?.certificateName}
                      name="certificateName"
                      onChange={(e) => {
                        handleChangeInput(e, data.certificateId);
                      }}
                      size="large"
                      placeholder="Enter Certificate name"
                    />

                    <label>
                      <b>Issuer Name</b>
                    </label>
                    <Input
                      value={data?.issuer}
                      name="issuer"
                      onChange={(e) => {
                        handleChangeInput(e, data.certificateId);
                      }}
                      size="large"
                      placeholder="Enter issuer name"
                    />

                    <label>
                      <b>issued on</b>
                    </label>
                    <Input
                      type="date"
                      value={data?.issueDate}
                      name="issueDate"
                      onChange={(e) => {
                        handleChangeInput(e, data.certificateId);
                      }}
                      size="large"
                      placeholder="large size"
                    />
                    <Checkbox
                      checked={data?.expire}
                      onChange={(e) => isExp(e, data.certificateId)}
                      name="expire"
                    >
                      is it has expire date
                    </Checkbox>
                    <br />

                    {!data?.expire && (
                      <div>
                        <label>
                          <b>valid upto</b>
                        </label>
                        <Input
                          type="date"
                          value={data?.expiryDate}
                          name="expiryDate"
                          onChange={(e) => {
                            handleChangeInput(e, data.certificateId);
                          }}
                          size="large"
                          placeholder="large size"
                        />
                      </div>
                    )}
                    <label>
                      <b>Credential Id</b>
                    </label>
                    <Input
                      value={data?.credentialId}
                      name="credentialId"
                      onChange={(e) => {
                        handleChangeInput(e, data.certificateId);
                      }}
                      size="large"
                      placeholder="Enter credential id"
                    />

                    <label>
                      <b>Credential url</b>
                    </label>
                    <Input
                      value={data?.credentialUrl}
                      name="credentialUrl"
                      onChange={(e) => {
                        handleChangeInput(e, data.certificateId);
                      }}
                      size="large"
                      placeholder="Enter credential URL"
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
                Add new Certificate
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
            isEdit ? navigateFunction() : navigate("/projects");
          }}
        >
          <Affix style={{ position: "absolute", top: 250, left: 40 }}>
            <Button className="shadow1" shape="round" type="primary">
              Projects{<ArrowRightOutlined />}
            </Button>
          </Affix>
        </Sider>
      </Layout>

      {/* <Button style={{  float:'right',paddingRight: "20px"}} type="submit"  icon={<UploadOutlined />} size='large' onClick={handleSubmit}>
          Save
        </Button>
         <PlusOutlined  onClick={()=>{handleAddFields()}}
               style={{ cursor: "pointer", color: "blue", paddingRight: "20px",float:'right' }} />
                
        <br/><br/><br/>
        <Button  type="button"   size='large' onClick={()=>{navigate('/experience')}}>
          Previous
        </Button>
        <Button  type="button"  style={{  float:'right'}}  size='large' onClick={()=>{navigate('/projects')}}>
          Next
        </Button> */}
    </div>
  );
};

export default Certificates;
