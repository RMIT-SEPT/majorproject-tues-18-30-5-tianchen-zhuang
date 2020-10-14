import React, { Component } from 'react'
import { Card } from 'antd';
const { Meta } = Card;


export default class EventShowingCard extends Component {
    constructor(props){
        super(props);
    }

    render() {
        return (
            <>
                
        <tr>
          <td scope="col">{this.props.eventId}</td>
          <td scope="col">{this.props.eventName}</td>
          <td scope="col">{this.props.eventDate}</td>
          <td scope="col">{this.props.eventTime}</td>
        </tr>
        </>
        )
    }
}