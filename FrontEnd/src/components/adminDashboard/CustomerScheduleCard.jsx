import React, { Component } from "react";
import { Card } from "antd";
const { Meta } = Card;

export default class CustomerScheduleCard extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <>
        <tr>
          <td scope="col">{this.props.customerId}</td>
          <td scope="col">{this.props.username}</td>
          <td scope="col">{this.props.email}</td>
          <td scope="col">{this.props.created}</td>
          <td scope="col">
            <input
              class="btn btn-success"
              id={this.props.customerId}
              onClick={this.props.edit}
              type="submit"
              value="Edit"
              name="edit"
            ></input>
            <input
              class="btn btn-danger"
              type="submit"
              id={this.props.customerId}
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
