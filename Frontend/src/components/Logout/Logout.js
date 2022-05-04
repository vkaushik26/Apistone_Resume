import React from "react";
import { useNavigate } from "react-router-dom";
export default function Logout() {
  const navigate = useNavigate();
  const handleClick = () => {
    sessionStorage.removeItem("user_id");
    navigate("/");
  };
  return (
    <div>
      <button
        type="button"
        class="btn btn-primary"
        id="logoutButton"
        onClick={() => {
          handleClick();
        }}
      >
        Logout
      </button>
    </div>
  );
}