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
            <select class="form-control" id="exampleFormControlSelect1">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
          </select></th>
          <td scope="col">{this.props.eventName}</td>
            <td scope="col">{this.props.currCapacity}</td>
        <td scope="col">{this.props.maxCapacity}</td>
          <td scope="col">{this.props.eventDate}</td>
          <td scope="col">{this.props.eventTime}</td>
          <td scope="col"><input  onClick={this.props.reserve} type ="submit" value="Reserve" name="reserve"></input></td>
        </tr>
            </>
        )
    }
}