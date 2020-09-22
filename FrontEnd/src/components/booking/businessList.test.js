import React from 'react';
import { mount,shallow } from 'enzyme';
import { render, screen } from '@testing-library/react'
import BusinessList from './businessList'
import { configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import logoImage from "./res.png";
configure({ adapter: new Adapter() });

//test if business exists
describe("check if business list isn't empty",()=>{
    it("test if business is exists",()=>{
        let bList = [{ 'title': 'res', 'id': 1 }];
        let item  = mount(<BusinessList></BusinessList>)
        item.setState({list:bList})
        expect(item.find("ClassCard")!=null)
    })
})
//mounting 2 business lists
describe("test mounting business list",()=>{
    it("test mounting two business",()=>{
        let bList = [{ 'title': 'res', 'id': 1 },
        { 'title': 'gym', 'id': 2 }];
        let item  = mount(<BusinessList></BusinessList>)
        item.setState({list:bList})
        expect(item.find("ClassCard")).toHaveLength(2)
    })
})
//testing if business list label correctly
describe("testing if business list label exists",()=>{
    it("test business label display correctly",()=>{
        render(<BusinessList />)
        screen.queryAllByLabelText('Businesses list');

    })
})
//testing if img path is correct or exists
describe("check img path is correct or exists",()=>{
    it("check img exists",()=>{
       
        let bList = [{ 'title': 'res', 'id': 1 }];
        let item  = mount(<BusinessList></BusinessList>)
        item.setState({list:bList})
        expect(item.find("img").prop('src')).toEqual(logoImage)
        

    })
})

