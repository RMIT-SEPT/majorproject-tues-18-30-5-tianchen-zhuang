import React from "react";
import authenticate from "../authenticate/authenticate";
import SchedleCard from "./scheduleCard";
class adminDashboard extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      list: [],
    };
  }

  componentWillMount() {
    console.log(sessionStorage.getItem("businessId") + "business iD ");
    let res = authenticate.getBusinessList();
    res.then((response) => {
      this.setState({
        list: response.data,
      });
    });

    console.log(sessionStorage.getItem("customerId"));
  }


  getAllBusinesses() {
    authenticate.getBusinessList();
  }

  render() {
    const scheduleList = [];
    for (let i = 0; i < this.state.list.length; i++) {
      scheduleList.push(
        <SchedleCard
          businessId={this.state.list[i].businessId}
          username={this.state.list[i].username}
          email={this.state.list[i].email}
          businessName={this.state.list[i].businessName}
          created={this.state.list[i].created}
          country={this.state.list[i].country}
          city={this.state.list[i].city}
          postCode={this.state.list[i].postCode}
          street={this.state.list[i].street}
        />
      );
    }

    return (
      <div>
        <h1>Welcome to the Admin Dashboard{sessionStorage.getItem("username")}</h1>
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
            <tbody>{scheduleList}</tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default adminDashboard;
