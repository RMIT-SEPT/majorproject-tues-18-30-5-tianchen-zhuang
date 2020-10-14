import React, { Component } from 'react'
import { Card } from 'antd';
const { Meta } = Card;


export default class schedulteCard extends Component {
    constructor(props){
        super(props);
    }

    render() {
        return (
            <>
                
        <tr>
          <th scope="row">
          {this.props.eventId}
          </th>
          <td scope="col">{this.props.eventName}</td>
            <td scope="col">{this.props.currCapacity}</td>
        <td scope="col">{this.props.maxCapacity}</td>
          <td scope="col">{this.props.eventDate}</td>
          <td scope="col">{this.props.eventTime}</td>
          <td scope="col" ><input class="btn btn-success" onClick={this.props.edit} type ="submit" value="Edit" name="edit"></input>
          <input class="btn btn-danger" type ="submit" onClick={this.props.delete} value="Delete" name="delete"></input></td>
        </tr>
            </>
        )
    }
}