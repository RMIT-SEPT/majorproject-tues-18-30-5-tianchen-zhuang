import React from 'react';


class bookinghistoryU extends React.Component {



    render (){
        return (
            <div>
            <h1> User booking history</h1>
            <table class="table table-hover">
                    <thead>
                        <tr>
                        <th scope="col">#</th>
                        <th scope="col">business Name</th>
                        <th scope="col">booking Time</th>
                        <th scope="col">status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        <th scope="row">1</th>
                        <td>mr kitchen</td>
                        <td>2020/09/30 12:30pm</td>
                        <td>reserved</td>
                        </tr>
                        <tr>
                        <th scope="row">2</th>
                        <td>hello gym</td>
                        <td>2020/09/30 12:30pm</td>
                        <td>waiting for business respond</td>
                        </tr>

                    </tbody>
                    </table>
                    </div>

        )
    }
}

export default bookinghistoryU