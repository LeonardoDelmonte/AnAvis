import React, {Component} from 'react';
import ListFreeDate from './ListFreeDate';

class FormPrenota extends Component{

    constructor(props) {
        super(props)   
    }

    render(){
        return(                        
            <form onSubmit={this.onSubmit.bind(this)}  class="pure-form pure-form-stacked">
                <fieldset className="form-group">
                    <label>sede</label>
                    <input className="form-control" type="text" required />
                </fieldset>
                <button className="btn btn-success" type="submit" >Search!</button>
            </form>
            //<ListFreeDate>
       )
    }
}
    


export default FormPrenota