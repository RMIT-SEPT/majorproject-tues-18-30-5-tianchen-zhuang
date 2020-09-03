import React, { Component } from 'react'
import ClassCard from './ClassCard'
// import {_getClassList} from '../../api/class'

import './booking.css'

export default class businessList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list: []
        };
        // this.getClassList = this.getClassList.bind(this)
    }

    showDetail(id) {
        console.log("show detail", id);
        this.props.history.push(`/booking/${id}`);
    }

    // componentWillMount() {
    //     this.getClassList();
    // }

    // async getClassList() {
    //     let res = await _getClassList();
    //     if (res['status'] == 1){
    //         this.setState({
    //             list:res.classes
    //         })
    //     }
    // }

    render() {
        let classList = [];
        for (let i = 0; i < 3; i++) {
            classList.push(<ClassCard showDetail={this.showDetail.bind(this, i )} title="res" id='1' description="desc"  />)
        }
        return (
            <div>
                <div className="classList">
                    {classList}
                </div>
            </div>
        )
    }
}
