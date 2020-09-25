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
        this.getClassList = this.getClassList.bind(this)
    }

    showDetail(id) {
        console.log("show detail", id);
        this.props.history.push(`/booking/${id}`);
    }

    componentWillMount() {
        this.getClassList();
    }

    async getClassList() {
        // let res = await _getClassList();
        // if (res['status'] == 1){
        //     this.setState({
        //         list:res.classes
        //     })
        // }
        let items  = this.state.list;

        items[0] = {};
        items[0].businessname = "mrkitchen";
        items[0].bid = 1;
        items[0].desc = "its kitchen";
        items[1] = {};
        items[1].businessname = "gym";
        items[1].bid = 2;
        items[1].desc = "its gym";

        console.log(items);
        this.setState({
            list:items
        })
        
    }

    render() {
        let classList = [];
        
        for (let i = 0; i<this.state.list.length; i++) {
           
            var bname = this.state.list[i].businessname;
            var bid = this.state.list[i].bid;
            var desc = this.state.list[i].desc;
          
            classList.push(<ClassCard showDetail={this.showDetail.bind(this, this.state.list[i])} title={bname} id={bid} description={desc}  />)
            
        }
        return (
            <div>
                <label>Business list</label>
                <div className="classList">
                    {classList}
                </div>
            </div>
        )
    }
}
