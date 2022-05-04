import './Preview.css';
import { React, useEffect, useState } from 'react';
import img from '../../images/education.png'
import project1 from '../../images/project.png'
import skills from '../../images/skills.png'
import experience from '../../images/experience.png'
import email from '../../images/email.png'
import phone from '../../images/phone.png'
import address from '../../images/address.png'
import linkedin from '../../images/linkedin.png'
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router';
import { Form, Input, Button, Space, Alert, Layout, Affix } from 'antd';
import { MinusCircleOutlined, PlusOutlined, UploadOutlined, ArrowLeftOutlined, DownloadOutlined, ArrowRightOutlined } from '@ant-design/icons';
import { DownloadHandler } from '../Download/Download';
import { getAllDetails } from '../../service/api';
const data2 = [
  {
    "ProjecDetails": [

    ],
    "ProfessionalDetails": [

    ],
    "EducationDetails": [

    ],
    "PersonalDetails": {

    },
    "CertificationDetails": [

    ]
  }
]

//var data=data2[0]


function Preview() {
  var data2 = '';
  const [data, setData] = useState()
  const { Header, Footer, Sider, Content } = Layout;
  var key = sessionStorage.getItem('user_id');

  useEffect(() => {
    getAllUsersDetails();
  }, [])
  const getAllUsersDetails = async () => {

    var temp = await getAllDetails(key)

    // data=temp.data[0];
    setData(temp.data[0])

  }
  console.log(data)


  const navigate = useNavigate();
  var education = data?.EducationDetails.map(function (education) {
    return <div key={education.university}>

      <div className="t m0 x1 h5 y11 ff3 fs3 fc2 sc0 ls0 ws0" style={{ color: 'violet' }}>{education.university}
        <span className="ls3">| </span><span className="fc0" >{education.specialization}</span>
      </div>
      <div className="t m0 x1 h6" >{education.fromDate}
        <span className="ff5">{` to `}</span> <span className="ls4">{education.toDate}</span>
      </div>
      <div class="flex-container-2">

        <div class="tech">
          CGPA :
        </div>
        <div>
          {education.cgpa}
        </div>
        


      </div>
      {education.achievements.length>0 &&<div >{education.achievements}
      </div>}

    </div>
  })
  var work = data?.ProfessionalDetails.map(function (work) {
    return <div key={work.companyName}>

      <div class="t m0 x1 h5 y1b ff3 fs3 fc2 sc0 ls0 ws0" style={{ color: 'violet' }}>{work.role}<span class="ls5">| </span><span class="fc0">{work.companyName}<span class="_ _4"></span></span>
      </div>
      {work.startDate.length>0 &&<div class="t m0 x1 ha y1c ff4 fs4 fc0 sc0 ls0 ws0">{work.startDate} <span class="ff5">to</span> <span class="ls4">{work.expire ? "Present" : work.endDate}</span>
      </div>}
      {work.achievements.length>0 &&<div >{work.achievements}
      </div>}
    </div>
  })

  var project = data?.ProjecDetails.map(function (projects) {
    return <div key={projects.projectName}><h5 style={{ color: 'violet' }}>{projects.projectName} | {projects.clientName} </h5>
      <p className="info">{projects.startDate}
        {projects.expire ? "" : ` to ${projects.endDate}`}</p>
      {projects.description.length>0 &&<p>{projects.description}</p>}
      { projects.stack.length>0 && <div>
      <div class="tech">
      Technology Stack :  
      </div>
      <div>
      {projects.stack}
      </div>
      </div>
  }
    </div>
  })



  var certification = data?.CertificationDetails.map(function (certifications) {
    return <div key={certifications.certificateName}><h5 style={{ color: 'violet' }}>{certifications.certificateName} <span> | </span>{certifications.issuer}</h5>
      {certifications.issueDate.length>0 &&<p className="date">{certifications.issueDate}<span></span>{certifications.expire ? "" : ` to ${certifications.expiryDate}`}</p>}

    </div>
  })

  return (
    <Layout>
      <Sider onClick={() => { navigate('/projects') }}><Affix style={{ position: 'absolute', top: 250, left: 22 }} >
        <Button   className='shadow1' shape="round"  type="primary" icon={<ArrowLeftOutlined />}>Edit</Button></Affix></Sider>
      <Layout>
        <Content>
          <section id="resume">

            <div class="row" style={{ height: '220px' }}>

              <div class="flex-container align-items-center">

                <div class="div1">
                  <span style={{ fontWeight: "lighter" }}>{data?.PersonalDetails.firstName}</span> <span style={{ fontStyle: "normal" }} >{data?.PersonalDetails.lastName}</span>
                </div>
                <div class="div2">
                  <ul>
                    <li><img src={email} alt="EDUCATION" width="20" height="20" /></li>
                    <li><img src={phone} alt="EDUCATION" width="20" height="20" /></li>
                   <li><img src={linkedin} alt="EDUCATION" width="20" height="20" /></li>
                  </ul>
                </div>
                <div class="div3">
                  <ul>
                    <li>{data?.PersonalDetails.emailId}</li>
                    <li>{data?.PersonalDetails.contactNumber}</li>
                    <li>{data?.PersonalDetails.linkedInId}</li>
                  </ul>
                </div>

              </div>
           
            </div>

            { data?.ProfessionalDetails.length>0 && <div className="row work">

              <div className="nine columns main-col">
                <p class="bold" ><img src={experience} alt="Experience" width="35" height="35" style={{ padding: "0px 10px 3px 0px" }} />WORK EXPERIENCE</p>


              </div>
              <div className="row item">
                <div className="twelve columns">
                  <div className="nine columns main-col">

                    {work}
                  </div>
                </div>
              </div>
            </div>
}
          { data?.EducationDetails.length>0 && <div className="row work">

              <div className="nine columns main-col">
                <p class="bold" ><img src={img} alt="EDUCATION" width="35" height="35" style={{ padding: "0px 10px 3px 0px" }} /><span></span>EDUCATION
                </p>
              </div>


              <div className="row item">
                <div className="twelve columns">
                  <div className="nine columns main-col">
                    {education}
                  </div>
                </div>
              </div>
            </div>
}

           { data?.ProjecDetails.length>0 && <div className="row work">

              <div className="nine columns main-col">
                <p class="bold" ><img src={project1} alt="PROJECTS" width="35" height="35" style={{ padding: "0px 10px 3px 0px" }} />PROJECTS</p>
                {/* <h1><span>PROJECTS</span></h1> */}
              </div>
              <div className="row item">
                <div className="twelve columns">
                  <div className="nine columns main-col">
                    {project}
                  </div>
                </div>
              </div>
            </div>
}
          { data?.CertificationDetails.length>0 && <div className="row projects">

              <div className="nine columns main-col">
               <p class="bold"><img src={skills} alt="SKILLS" width="35" height="35" style={{ padding: "0px 10px 3px 0px" }} />CERTIFICATIONS
                  {/* <h2><span>CERTIFICATIONS</span></h2> */}
                </p>
              </div>
              <div className="row item">
                <div className="twelve columns">
                  <div className="nine columns main-col">
                    {certification}
                  </div>
                </div>
           
              </div>
          
            </div>
}
          </section>
        </Content>
      </Layout>
      <Sider onClick={() => { DownloadHandler(data) }}><Affix style={{ position: 'absolute', top: 250, left: 22 }} >
        <Button  className='shadow1' shape="round"  type="primary" icon={<DownloadOutlined />}>Download</Button></Affix></Sider>
    </Layout>


  );
}

export default Preview;