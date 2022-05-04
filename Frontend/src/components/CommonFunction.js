import { notification } from 'antd';
import { useNavigate } from 'react-router';

export const deleteNotification = placement => {
  notification.info({
    message: `Deleted Successfully`,
    duration:1.5,
    placement,
  });
};
export const saveNotification = placement => {
  notification.success({
    message: `Saved Successfully`,
    duration:1.5,
    placement:'top'
  });
};

export const navigateFunction=()=>{
  notification.warning({
    message: `Please save your work before you proceed`,
    duration:1.5,
    placement:'top'
  });
};