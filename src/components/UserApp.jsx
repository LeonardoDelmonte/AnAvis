import React, {Component} from 'react';
import ListUser from './ListUser';

class UserApp extends Component{
    render(){
        return(
            <div class="container" >        
                <h1>Users Signed</h1>
                <ListUser/>
            </div>
        );        
    }
}

export default UserApp