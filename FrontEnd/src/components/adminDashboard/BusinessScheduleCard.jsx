import React, { Component } from "react";
import { Card } from "antd";
const { Meta } = Card;

export default class BusinessScheduleCard extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <>
        <tr>
          <td scope="col">{this.props.businessId}</td>
          <td scope="col">{this.props.username}</td>
          <td scope="col">{this.props.email}</td>
          <td scope="col">{this.props.businessName}</td>
          <td scope="col">{this.props.created}</td>
          <td scope="col">{this.props.country}</td>
          <td scope="col">{this.props.city}</td>
          <td scope="col">{this.props.postCode}</td>
          <td scope="col">{this.props.street}</td>
          <td scope="col">
            <input
              class="btn btn-success"
              onClick={this.props.edit}
              type="submit"
              value="Edit"
              name="edit"
            ></input>
            <input
              class="btn btn-danger"
              type="submit"
              onClick={this.props.delete}
              value="Delete"
              name="delete"
            ></input>
          </td>
        </tr>
      </>
    );
  }
}
