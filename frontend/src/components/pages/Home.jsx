import React, { Component } from 'react';
//Components
import Login from '../Forms/Login'
import Register from '../Forms/Register'
import News from '../Other/News';
import Faq from '../pages/Faq';


class Home extends Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }

    //-----Render
    render() {
        return (
            <div>
                <div className="jumbotron text-center">
                    <h1>AnAvis</h1>
                    <h3>Il portare che ti permette di donare il sangue con un click</h3>
                </div>
                <div className="container">
                    <div className="row">
                        <div className="col-sm-12 col-md-12 col-lg-4 col-xl-4 shadow-lg p-3 mb-5 bg-white rounded" >
                            <Login {...this.props} logIn={this.props.logIn}/>
                        </div>
                        <div className="col-sm-12 col-md-12 col-lg-8 col-xl-8 shadow-lg p-3 mb-5 bg-white rounded" >
                            <Register />
                        </div>
                    </div>

                    <News />

                    <Faq />

                </div>
            </div>
        );
    }
}

export default Home