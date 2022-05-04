import axios from "axios";

const MAINURL="http://localhost:1234/"
const BE="http://localhost:3001/api/resume/"



//personal
export const getPersonal = async (id) => {
    return await axios.get(`${BE}user/id/${id}`);
}
export const updatePersonal = async (body) => {
    return await axios.put("http://localhost:3001/api/resume/user",body);
}

//education
export const getEducation = async (id) => {
    return await axios.get(`${BE}educationDetails/${id}`);
}
export const postEducation = async (body) => {
    return await axios.post(`${BE}allEducationDetails`,body);
}

//professional
export const getprofessional = async (id) => {
    return await axios.get(`${BE}professionalDetails/${id}`);
}
export const postprofessional = async (body) => {
    return await axios.post(`${BE}allProfessionalDetails`,body);
}


//certificates
export const getCertificates = async (id) => {
    return await axios.get(`${BE}certificationDetails/${id}`);
}
export const postCertificates = async (body) => {
    return await axios.post(`${BE}allCertificationsDetails`,body);
}

//projects
export const getProjects = async (id) => {
    return await axios.get(`${BE}projectDetails/${id}`);
}

export const postProjects = async (body) => {
    return await axios.post(`${BE}allProjectDetails`,body);
}



//allDetails

export const getAllDetails = async (id) => {
    
    console.log(id);
    return await axios.get(`${BE}allDetails/${id}`);
}
