import React, { Component } from 'react'
import { Card } from 'antd';
const { Meta } = Card;

export default class ClassCard extends Component {
    constructor(props){
        super(props);
    }

    render() {
        return (
            <div className="classCard">
                <Card
                    hoverable
                    style={{ width: 240, height: 400, overflow: "hidden" }}
                    cover={<img alt="example" src={require("./res.png")} />}
                    onClick={this.props.showDetail}
                >
                    <Meta title={this.props.title} description={this.props.description} />
                </Card>
            </div>
        )
    }
}
