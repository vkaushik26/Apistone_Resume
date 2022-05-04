import {React,useEffect,useState} from 'react';
import 'antd/dist/antd.css';
import { getPersonal,updatePersonal } from '../../service/api';
import { useNavigate } from 'react-router';
import { Form, Input, Button, Space ,Layout,Affix,} from 'antd';
import { MinusCircleOutlined, PlusOutlined ,UploadOutlined,ArrowLeftOutlined, ArrowRightOutlined} from '@ant-design/icons';
import { UserOutlined } from '@ant-design/icons';
import { deleteNotification, saveNotification } from '../CommonFunction';
import { navigateFunction } from '../CommonFunction';
import {
    EditOutlined,
    EyeOutlined,
    DeleteOutlined,
    ExclamationCircleOutlined
} from '@ant-design/icons';
import Logout from '../Logout/Logout';
const { TextArea } = Input;
const Personal = () => {
  const navigate = useNavigate();
  const { Header, Footer, Sider, Content } = Layout;
    const [inputFields, setInputFields] = useState([]);
    const [isEdit, setIdEdit] = useState(false);
    useEffect(()=>{
      var key= sessionStorage.getItem('user_id');
      getPersonal(key).then((res)=>{
            console.log(res.data.response.personalDetails)
            setInputFields(res.data.response.personalDetails)
        })
    },[])
    const handleAddFields = () => {
        setInputFields([...inputFields, { id: inputFields.length? inputFields[inputFields.length-1].id+1:0,  userId:'' ,firstName:'',lastName:'',dob:'',contactNumber:'',emailId:'',address:'',linkedInId:''}])
        console.log(inputFields)
      }
      const handleRemoveFields = id => {
        const values  = [...inputFields];
        values.splice(values.findIndex(value => value.id === id), 1);
        setInputFields(values);
      }

      const handleChangeInput = (e,id) => {
        let key=e.target.name
        let v=e.target.value
        setInputFields({...inputFields,[key]:v}) 
        setIdEdit(true);
        console.log(inputFields)
      }

      const handleSubmit = (e) => {
    e.preventDefault();
    console.log("InputFields", inputFields);
    updatePersonal(inputFields);
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
      <Sider onClick={()=>{}}></Sider>
      <Layout>
      <Affix>
          <Header className='d-flex align-items-center justify-content-between'>
            <h2 className='heading'>Your Personal details</h2>
            <Space  className='space' >
            
            <Button shape="round"  type="submit" icon={<UploadOutlined />} size='large' onClick={handleSubmit}>
              Save
            </Button>
            <Logout/>
            </Space>
          </Header>
          </Affix>
        <Content>
          
       
        
        
            {
               
                 <div>
                 <div className='allItems' >
                <form key={inputFields.id} onSubmit={handleSubmit} style={{padding:'8px'}} >
                  <label><b>User Id</b></label>
                     <Input disabled required value={inputFields?.userId} name="userId" onChange={(e)=>{ handleChangeInput(e,inputFields.id)}} size="large"   />

                     <label><b>First Name</b></label>
                     <Input value={inputFields?.firstName} name="firstName" onChange={(e)=>{ handleChangeInput(e,inputFields.id)}} size="large" placeholder="Enter your first name"  />

                     <label><b>Last Name</b></label>
                     <Input value={inputFields?.lastName} name="lastName" onChange={(e)=>{ handleChangeInput(e,inputFields.id)}} size="large" placeholder="Enter your last name"  />

                     <label><b>Date of Birth</b></label>
                     <Input type="date" value={inputFields?.dob} name="dob" onChange={(e)=>{ handleChangeInput(e,inputFields.id)}} size="large" placeholder="Enter your date of birth"  />

                     <label><b>Contact number</b></label>
                     <Input value={inputFields?.contactNumber} name="contactNumber" onChange={(e)=>{ handleChangeInput(e,inputFields.id)}} size="large" placeholder="Enter your phone number"  />

                     <label><b>Email id</b></label>
                     
                     <Input value={inputFields?.emailId} name="emailId" onChange={(e)=>{ handleChangeInput(e,inputFields.id)}} size="large" placeholder="Enter your email ID"  />
                     <label><b>address</b></label>
                     <TextArea value={inputFields?.address} name="address"  onChange={(e)=>{ handleChangeInput(e,inputFields.id)}} size="large" rows={2} maxLength={200}  placeholder="maxLength is 6"  />
                     <label><b>linkedin id</b></label>
                     <Input value={inputFields?.linkedInId} name="linkedInId" onChange={(e)=>{ handleChangeInput(e,inputFields.id)}} size="large" placeholder="Enter your LinkedId"  />
                </form>
                
                  </div>
                  <div>
                    <br/><br/>
                  </div>
                </div>
               
               
            }
        </Content>
        <Footer>
        <Button shape="round"  style={{  float:'right',paddingRight: "20px"}} type="submit"  icon={<UploadOutlined />} size='large' onClick={handleSubmit}>
          Save
        </Button>
        </Footer>
        </Layout>
        <Sider onClick={()=>{{isEdit?navigateFunction():navigate("/education")}}}><Affix  style={{position: 'absolute',  top: 250, left:40}} >
      <Button  className='shadow1' shape="round"  type="primary">Education{<ArrowRightOutlined/>}</Button></Affix></Sider>
      </Layout>
    
         
        
         

    </div>
  );
};

export default Personal;