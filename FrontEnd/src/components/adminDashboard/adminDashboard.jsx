import React from "react";
import authenticate from "../authenticate/authenticate";
import BusinessScheduleCard from "./BusinessScheduleCard";
import CustomerScheduleCard from "./CustomerScheduleCard";

class adminDashboard extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      businessInfo: [],
      customerInfo: [],
    };
  }

  componentWillMount() {
    console.log(sessionStorage.getItem("businessId") + "business iD ");
    let res = authenticate.getBusinessList();
    res.then((response) => {
      this.setState({
        businessInfo: response.data,
      });
    });

    let customerRes = authenticate.getCustomerList();
    customerRes.then((response) => {
      this.setState({
        customerInfo: response.data,
      });
    });

    console.log(sessionStorage.getItem("customerId"));
  }

  getAllBusinesses() {
    authenticate.getBusinessList();
  }

  deleteBusiness(event)
  {
      const deleteId = event.target.getAttribute("id");
      console.log(deleteId +" delete business")
      authenticate.deleteBusiness(deleteId);
  }

  deleteCustomer(event)
  {
      const deleteId = event.target.getAttribute("id");
      console.log(deleteId +" delete customer")
      authenticate.deleteCustomer(deleteId);
  }
  testButton()
  {
      console.log("Button Pressed")
  }

  render() {
    const businessScheduleList = [];
    for (let i = 0; i < this.state.businessInfo.length; i++) {
        businessScheduleList.push(
        <BusinessScheduleCard
          businessId={this.state.businessInfo[i].businessId}
          username={this.state.businessInfo[i].username}
          email={this.state.businessInfo[i].email}
          businessName={this.state.businessInfo[i].businessName}
          created={this.state.businessInfo[i].created}
          country={this.state.businessInfo[i].country}
          city={this.state.businessInfo[i].city}
          postCode={this.state.businessInfo[i].postCode}
          street={this.state.businessInfo[i].street}
          edit={this.testButton.bind()}
          delete={this.deleteBusiness.bind(this)} 
        />
      );
    }
    const customerScheduleList = [];
    for (let i = 0; i < this.state.customerInfo.length; i++) {
        customerScheduleList.push(
        <CustomerScheduleCard
          customerId={this.state.customerInfo[i].customerId}
          username={this.state.customerInfo[i].username}
          email={this.state.customerInfo[i].email}
          created={this.state.customerInfo[i].created}
          edit={this.testButton.bind()}
          delete={this.deleteCustomer.bind(this)} 
        />
      );
    }

    return (
      <div>
        <h1>
          Welcome to the Admin Dashboard{sessionStorage.getItem("username")}
        </h1>
        <div>
          <h2>Business List</h2>
          <table class="table table-bordered">
            <thead>
              <tr>
                <th scope="col">Business ID</th>
                <th scope="col">Business Username</th>
                <th scope="col">Business Email</th>
                <th scope="col">Business Name</th>
                <th scope="col">Created Date</th>
                <th scope="col">Country</th>
                <th scope="col">City</th>
                <th scope="col">Postcode</th>
                <th scope="col">Street</th>
              </tr>
            </thead>
            <tbody>{businessScheduleList}</tbody>
          </table>
          <h2>Customer List</h2>
          <table class="table table-bordered">
            <thead>
              <tr>
                <th scope="col">Customer ID</th>
                <th scope="col">Customer Username</th>
                <th scope="col">Customer Email</th>
                <th scope="col">Created Date</th>
              </tr>
            </thead>
            <tbody>{customerScheduleList}</tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default adminDashboard;
