import React, { useState } from 'react';
import 'antd/dist/antd.css';
import { Steps, Divider } from 'antd';
import { useNavigate } from 'react-router';
import { UserOutlined, SolutionOutlined, LoadingOutlined, SmileOutlined ,ProjectTwoTone,TrophyFilled,ReadFilled,ProjectFilled} from '@ant-design/icons';
const { Step } = Steps;

const Header =()=>  {
  
  const  arr=["/personal","/education","/experience","/certificates","/projects"]          
  const navigate = useNavigate();
  const onChange = current => {
    console.log('onChangdfvvbe:', current);
    console.log(arr[current])
    
    navigate(arr[current])
   
  };

  
    

   
    return (
      <>
        <Steps current={34} onChange={onChange}>
          <Step status="finished" title="personal details" description="This is a description." icon={<UserOutlined />} />
          <Step status="error" title="Education" description="This is a description." icon={<ReadFilled />}/>
          <Step status="wait" title="Professional " description="This is a description." icon={<SolutionOutlined/>} />
          <Step title="certificates" description="This is a description." icon={<TrophyFilled  />} />
          <Step title="projects" description="This is a description." icon={<ProjectFilled />}/>
        </Steps>

        <Divider />

      </>
    );
  }


export default Header;