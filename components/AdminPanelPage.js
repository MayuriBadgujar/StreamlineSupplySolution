import React, { useState } from "react";
import { Link } from "react-router-dom";
// import RegisterUser from "./RegisterUser";
// import RegisterCompany from "./RegisterCompany";
import ManageTenders from "./ManageTenders";
import ManageTickets from "./ManageTickets";
import ManageOrders from "./ManageOrders"; // Import ManageOrders Component
import '../components/AdminPanelPage.css';

const AdminPanelPage = () => {
  // State to track active section
  const [activeSection, setActiveSection] = useState("tickets");

  // Render different admin sections
  const renderSection = () => {
    switch (activeSection) {
      case "tickets":
        return <ManageTickets />;
      case "tenders":
        return <ManageTenders />;
      // case "registerUser":
      //   return <RegisterUser />;
      // case "registerCompany":
      //   return <RegisterCompany />;
      case "manageOrders":
        return <ManageOrders />; // Render ManageOrders component
      default:
        return <ManageTickets />;
    }
  };

  return (
    <div>
      {/* Navbar */}
      <nav className="navbar navbar-expand-lg bg-dark">
        <div className="container-fluid">
          <Link className="navbar-brand text-light" to="/">SE</Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item"><Link className="nav-link text-light" to="/admin">Home</Link></li>
              {/* <li className="nav-item"><Link className="nav-link text-light" to="/ticketingtool">Ticketing Tool</Link></li> */}
              <li className="nav-item"><Link className="nav-link text-light" to="/adminpanel">Admin Panel</Link></li>
            </ul>
          </div>
        </div>
      </nav>

      {/* Admin Panel Content */}
      <div className="admin-panel">
        <h1>Admin Panel</h1>

        {/* Admin Panel Buttons */}
        <div className="buttons">
          <button onClick={() => setActiveSection("tickets")}>Manage Tickets</button>
          <button onClick={() => setActiveSection("tenders")}>Manage Tenders</button>
          {/* <button onClick={() => setActiveSection("registerUser")}>Manage Users</button>
          <button onClick={() => setActiveSection("registerCompany")}>Manage Companies</button> */}
          <button onClick={() => setActiveSection("manageOrders")}>Manage Orders</button> {/* New Orders Section */}
        </div>

        {/* Dynamic Section Rendering */}
        <div className="content1">{renderSection()}</div>
      </div>
    </div>
  );
};

export default AdminPanelPage;
