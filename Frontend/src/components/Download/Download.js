//### Imports ###
import { getAllDetails } from '../../service/api';

import { saveAs } from 'file-saver';

//### Imports for Docx.js ###
import { Document, 
    Packer, 
    Paragraph, 
    TextRun, 
    HeadingLevel,
    AlignmentType, 
    Table,
    TableRow,
    TableCell, 
    SectionType,
   WidthType,
   TabStopType,
   TabStopPosition,
   BorderStyle, 
   ImageRun,
   VerticalAlign,
   Header} from "docx";

//### Imports for images ###



import certificateImg from "../../images/certificate.png"

import eduImg from'../../images/education.png'
import projectImg from '../../images/project.png'
import skills from '../../images/skills.png'
import expImg from '../../images/experience.png'
import email from '../../images/email.png'
import phone from '../../images/phone.png'
import address from '../../images/address.png'
import linkedin from '../../images/linkedin.png'


var empDetails='';
var key= sessionStorage.getItem('user_id') || 9
    //Download button event handler
   export const DownloadHandler = async(data)=>{
  
        //empDetails = JSON.parse(empDetails.toString())
      //  await getAllUsersDetails();
        empDetails=data
        console.log(empDetails)
        // Used to export the file into a .docx file
        const blob = await Packer.toBlob(createDoument())
        var name=empDetails.PersonalDetails.firstName+'_'+empDetails.PersonalDetails.lastName+'_resume'
        saveAs(blob, `${name}.docx`)
    }

    const  getAllUsersDetails=async ()=>{
        var temp= await getAllDetails(key)
        console.log(" reaches temp");
        console.log(temp);
        empDetails=temp.data[0];
     
    }
//### Table for content ###
    const getContentTable = () => {
        return new Table({
            rows: [

                new TableRow({
                    children:[
                        new TableCell({
                            children: [
                                new Paragraph({
                                    children: empDetails.ProfessionalDetails.length>0? [
                                        new ImageRun({
                                            data: expImg,
                                            transformation: {
                                                width: 28,
                                                height: 28,
                                            }
                                        }),
                                    ]:[]
                                })
                            ],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width:{
                                type: WidthType.PERCENTAGE,
                                size: 10
                            },
                            margins: {
                                right: 20
                            }
                        }),
                        new TableCell({
                            children:empDetails.ProfessionalDetails.length>0? getExperience():[],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width:{
                                type: WidthType.PERCENTAGE,
                                size: 90
                            }
                        })
                    ]
                }),
                new TableRow({
                    children:[
                        new TableCell({
                            children: [
                                new Paragraph({
                                    children:empDetails.EducationDetails.length>0?[
                                        new ImageRun({
                                            data: eduImg,
                                            transformation: {
                                                width: 28,
                                                height: 28,
                                            }
                                        }),
                                    ]:[]
                                })
                            ],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width:{
                                type: WidthType.PERCENTAGE,
                                size: 10
                            },
                            margins: {
                                right: 20
                            }
                        }),
                        new TableCell({
                            children:empDetails.EducationDetails.length>0?getEducation():[],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width:{
                                type: WidthType.PERCENTAGE,
                                size: 90
                            }
                        })
                    ]
                }),
                new TableRow({
                    children:[
                        new TableCell({
                            children: [
                                new Paragraph({
                                    children:empDetails.ProjecDetails.length>0?[
                                        new ImageRun({
                                            data: projectImg,
                                            transformation: {
                                                width: 28,
                                                height: 28,
                                            }
                                        }),
                                    ]:[]
                                })
                            ],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width:{
                                type: WidthType.PERCENTAGE,
                                size: 10
                            },
                            margins: {
                                right: 20
                            }
                        }),
                        new TableCell({
                            children:empDetails.ProjecDetails.length>0? getProject():[],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width:{
                                type: WidthType.PERCENTAGE,
                                size: 90
                            }
                        })
                    ]
                }),
                new TableRow({
                    children:[
                        new TableCell({
                            children: [
                                new Paragraph({
                                    children:empDetails.CertificationDetails.length>0?[
                                        new ImageRun({
                                            data: certificateImg,
                                            transformation: {
                                                width: 28,
                                                height: 28,
                                            }
                                        }),
                                    ]:[]
                                })
                            ],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width:{
                                type: WidthType.PERCENTAGE,
                                size: 10
                            },
                            margins: {
                                right: 20
                            }
                        }),
                        new TableCell({
                            children: empDetails.CertificationDetails.length>0?getCertification():[],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width:{
                                type: WidthType.PERCENTAGE,
                                size: 90
                            }
                        })
                    ]
                })
            ]
        })
    }

//### Personal Details ###
    const getTableHeader = ()=>{
        const {firstName, lastName,contactNumber,emailId,linkedInId } = empDetails.PersonalDetails
        return new Table({
            rows:[
                new TableRow({
                    children: [
                        new TableCell({
                            children : [
                                new Paragraph({
                                    children:[
                                        new TextRun({
                                            text: firstName,
                                            allCaps:true,
                                            font: "Calibri Light",
                                            size: 66
                                        })
                                    ]
                                }),
                                new Paragraph({
                                    children:[
                                        new TextRun({
                                            text: lastName,
                                            allCaps:true,
                                            font: "Calibri",
                                            size: 66,
                                            bold: true
                                        })
                                    ]
                                })
                            ],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width: {
                                size: 70,
                                type: WidthType.PERCENTAGE
                            }
                        }),
                        new TableCell({
                            children:[
                                new Paragraph({
                                    children: [
                                        new ImageRun({
                                            data: phone,
                                            transformation: {
                                                width: 14,
                                                height: 14,
                                            },
                                        }),
                                        new TextRun({
                                            text: `  ${contactNumber}`,
                                            font: "Calibri Light",
                                            size: 22
                                        })
                                    ],
                                    alignment: AlignmentType.LEFT,
                                    spacing: {
                                        after: 15
                                    }
                                }),
                                new Paragraph({
                                    children: [    
                                        new ImageRun({
                                            data: email,
                                            transformation: {
                                                width: 14,
                                                height: 14,
                                            },
                                        }),
                                        new TextRun({
                                            text: `  ${emailId}  `,
                                            font: "Calibri Light",
                                            size: 22
                                        })
                                    ],
                                    alignment: AlignmentType.LEFT,
                                    spacing: {
                                        after: 15
                                    }
                                }),
                                new Paragraph({
                                    children: [
                                       
                                        new ImageRun({
                                            data: linkedin,
                                            transformation: {
                                                width: 14,
                                                height: 14,
                                            },
                                        }),
                                        new TextRun({
                                            text: `  ${linkedInId}  `,
                                            font: "Calibri Light",
                                            size: 22
                                        })
                                    ],
                                    alignment: AlignmentType.LEFT,
                                    spacing: {
                                        after: 15
                                    }
                                }),
                            ],
                            borders:{
                                top: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                left: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                right: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                                bottom: {
                                    style: BorderStyle.NIL,
                                    size: 0
                                },
                            },
                            width: {
                                size: 30,
                                type: WidthType.PERCENTAGE
                            },
                            verticalAlign: VerticalAlign.CENTER
                        })
                    ]
                }),
            ],
            width:{
                size: 100,
                type: WidthType.PERCENTAGE
            },
        })
    }

//### Experience ###

    //Experience Array
    const getExperience = () =>{
        const expirenceHeading = new Paragraph({
            children:[
              new TextRun({
                text: "WORK EXPERIENCE",
                heading: HeadingLevel.HEADING_1,
                alignment: AlignmentType.CENTER,
                font: "Calibri",
                size: 32,
                allCaps:true,
                bold: true
              })
            ],
            spacing:{
                after: 15
            }
          })
      const experienceBlankSpace = new Paragraph({
            children: [
              new TextRun({size: 24})
            ],
      })

      const {ProfessionalDetails} = empDetails

    const expArr = ProfessionalDetails.map((experience)=>{
        const {companyName, startDate, endDate, role, achievements, expire} = experience
        const end = expire ? "Present" : endDate
        const date = startDate===null || startDate === "" ? "" : `${startDate} to ${end}`
        return [
          new Paragraph({
            children:[
              new TextRun({
                text: `${role} | `,
                size: 26,
                font: "Calibri",
                color: "#77448B",
                bold: true,
              }),
              new TextRun({
                text: `${companyName}`,
                font: "Calibri",
                size: 26,
                color: "#4C4C4C"
              }),
            ]
          }),
          new Paragraph({
            children:[
              new TextRun({
                text: `${date}`,
                size: 24,
                font: "Calibri",
                color: "#4C4C4C",
              })
            ]
          }),
          new Paragraph({
            children:[
              new TextRun({
                text: `${achievements}`,
                size: 22,
                font: "Calibri Light",
                color: "#4C4C4C"
              }),
            ],
            spacing: {
                after: 30
            }
          }), 
        ]
    })

    var merged = [].concat.apply([], expArr);
    merged.unshift(expirenceHeading)
    merged.push(experienceBlankSpace)
    return merged
    }

//### Education ###

    //Education Array
    const getEducation = () =>{
        const educationHeading = new Paragraph({
            children:[
            new TextRun({
                text: "Education",
                heading: HeadingLevel.HEADING_1,
                alignment: AlignmentType.CENTER,
                font: "Calibri",
                size: 32,
                allCaps:true,
                bold: true
            })
            ],
            spacing:{
                after: 15
            }
        })
    const educationBlankSpace = new Paragraph({
            children: [
            new TextRun({size: 24})
            ],
    })

    const {EducationDetails} = empDetails

    const eduArr = EducationDetails.map((edu)=>{
        const {specialization,university, cgpa, fromDate, toDate, achievements} = edu

        const marks = cgpa
        const date = fromDate===null || fromDate==="" ?  "": (toDate==="" || toDate ===null ? "" : `${fromDate} to ${toDate}`)

        return [
        new Paragraph({
            children:[
            new TextRun({
                text: `${university} | `,
                size: 26,
                font: "Calibri",
                color: "#77448B",
                bold: true,
            }),
            new TextRun({
                text: `${specialization}`,
                font: "Calibri",
                size: 26,
                color: "#4C4C4C"
            }),
            ]
        }),
        new Paragraph({
            children:[
            new TextRun({
                text: `${date}`,
                size: 24,
                font: "Calibri",
                color: "#4C4C4C",
            })
            ]
        }),
        new Paragraph({
            children: cgpa===null || cgpa==="" ? [] : [
            new TextRun({
                text: `CGPA: `,
                size: 22,
                font: "Calibri Light",
                color: "#4C4C4C",
                bold: true,
            }),
            new TextRun({
                text: `${cgpa}`,
                size: 22,
                font: "Calibri Light",
                color: "#4C4C4C"
            })
            ]
        }),
        new Paragraph({
            children:[
            new TextRun({
                text: `${achievements}`,
                size: 22,
                font: "Calibri Light",
                color: "#4C4C4C"
            }),
            ],
            spacing: {
                after: 30
            }
        }), 
        ]
    })

    var merged = [].concat.apply([], eduArr);
    merged.unshift(educationHeading)
    merged.push(educationBlankSpace)
    return merged
    }

//### Certification ###
    
    //Certification Array
    const getCertification = () =>{
        const certificationHeading = new Paragraph({
            children:[
            new TextRun({
                text: "CERTIFICATION",
                heading: HeadingLevel.HEADING_1,
                alignment: AlignmentType.CENTER,
                font: "Calibri",
                size: 32,
                allCaps:true,
                bold: true
            })
            ],
            spacing:{
                after: 15
            }
        })
        const certificationBlankSpace = new Paragraph({
            children: [
            new TextRun({size: 24})
            ],
    })

    const {CertificationDetails} = empDetails

    const certificationArr = CertificationDetails.map((certificate)=>{
       
        const {certificateName, issuer, issueDate, expire, expiryDate} = certificate

        const certificateDate = (expire === true || expire === "true") ? issueDate ===null || issueDate ===""  ? "" : expiryDate===null || expiryDate==="" ? `${issueDate}`: `${issueDate} to ${expiryDate}` : `${issueDate}` 
          
        return [
        new Paragraph({
            children:[
            new TextRun({
                text: `${certificateName} | `,
                size: 26,
                font: "Calibri",
                color: "#77448B",
                bold: true,
            }),
            new TextRun({
                text: `${issuer}`,
                font: "Calibri",
                size: 26,
                color: "#4C4C4C"
            }),
            ]
        }),
        new Paragraph({
            children:[
            new TextRun({
                text: `${certificateDate}`,
                size: 24,
                font: "Calibri",
                color: "#4C4C4C",
            })
            ]
        })
        ]
    })

    var merged = [].concat.apply([], certificationArr);
    merged.unshift(certificationHeading)
    merged.push(certificationBlankSpace)
    return merged
    }

//### Project ###
    //Project Array
    const getProject = () =>{
        const certificationHeading = new Paragraph({
            children:[
            new TextRun({
                text: "PROJECT",
                heading: HeadingLevel.HEADING_1,
                alignment: AlignmentType.CENTER,
                font: "Calibri",
                size: 32,
                allCaps:true,
                bold: true
            })
            ],
            spacing:{
                after: 15
            }
        })
    const certificationBlankSpace = new Paragraph({
            children: [
            new TextRun({size: 24})
            ],
    })

    const {ProjecDetails} = empDetails

    const certificationArr = ProjecDetails.map((project)=>{
        console.log(project)
        const {projectName, clientName, startDate, endDate,stack, description,expire, } = project
        const clientField = clientName ? `| ${clientName}` : ""
        const certificateDate = ((expire === false || expire === "false")) ? (startDate!=null || endDate!=null || startDate!="" || endDate!="") ? `${startDate} to ${endDate}`:"" :  startDate != null ?`${startDate}`:'' 

        return [
        new Paragraph({
            children:[
            new TextRun({
                text: `${projectName}`,
                size: 26,
                font: "Calibri",
                color: "#77448B",
                bold: true,
            }),
            new TextRun({
                text: `${clientField}`,
                font: "Calibri",
                size: 26,
                color: "#4C4C4C"
            }),
            ]
        }),
        new Paragraph({
            children:certificateDate.length>0?[
            new TextRun({
                text: `${certificateDate}`,
                size: 24,
                font: "Calibri",
                color: "#4C4C4C",
            })
            ]:''
        }),
        new Paragraph({
            children:[
            new TextRun({
                text: `${description}`,
                size: 22,
                font: "Calibri Light",
                color: "#4C4C4C"
            }),
            ],
            spacing: {
                after: 30
            }
        }), 
        new Paragraph({
            children:stack.length>0?[
            new TextRun({
                text: `Technology Stack: `,
                size: 22,
                font: "Calibri Light",
                color: "#4C4C4C",
                bold: true
            }),
            new TextRun({
                text: `${stack}`,
                size: 22,
                font: "Calibri Light",
                color: "#4C4C4C"
            }),
            ]:[],
            spacing: {
                after: 30
            }
        })
        ]
    })

    var merged = [].concat.apply([], certificationArr);
    merged.unshift(certificationHeading)
    merged.push(certificationBlankSpace)
    return merged
    }
//### Document ###
    const createDoument = ()=>{
        const doc = new Document({
            sections: [{
                properties: {
                    type: SectionType.CONTINUOUS,
                    
                },
                children: [
                    getTableHeader(),
                    new Paragraph({
                        children: [
                          new TextRun({size: 24})
                        ],
                      }),
                      new Paragraph({
                        children: [
                          new TextRun({size: 24})
                        ],
                      }),
                ]
            },{
                properties:{
                    type: SectionType.CONTINUOUS,
                },
                children: [
                    getContentTable()
                ]
            }],
        });
    
      return doc
      }

 


