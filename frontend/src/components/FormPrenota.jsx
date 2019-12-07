import React, {Component} from 'react';
import ListFreeDate from './ListFreeDate';

class FormPrenota extends Component{

    constructor(props) {
        super(props)
        this.state = {
            sede:'',
            bool:false
        }
    }

    changeSede(event){
        this.setState({sede:event.target.value});
    }

    onSubmit(event){
        this.setState({bool:true});   
        event.preventDefault();     
    }

    //componentDidMount(){
    //    
    //    
    //}
//
    //componentWillUnmount(){
    //    this.setState({sede:''});
    //    this.setState({bool:false});
    //}

    render(){
        return(
            <div>                   
            <form onSubmit={this.onSubmit.bind(this)}  >
                <fieldset >
                    <label>sede</label>
                    <input  type="text" onChange={this.changeSede.bind(this)} required />
                </fieldset>
                <button  type="submit">Search!</button>             
            </form>
            {this.state.bool && <ListFreeDate sede={this.state.sede}/>}
           </div>
       )
    }
}
    


export default FormPrenota