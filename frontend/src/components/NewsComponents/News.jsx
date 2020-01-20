import React, { Component } from 'react';

import Parser from "rss-parser";
import Spinner from '../commonsComponents/Spinner';

class News extends Component {
  constructor() {
    super();
    this.state = {

    }
  }

  componentDidMount() {
    let parser = new Parser();
    const CORS_PROXY = "https://cors-anywhere.herokuapp.com/";

    (async () => {
      let feed = await parser.parseURL(CORS_PROXY + "https://www.avis.it/feed/");
      this.setState({ feed: feed, ready:true })
    })();
  }

  render() {
    return (
      <div>
        <Spinner ready={this.state.ready} />

        <div className="row m-3">

          {this.state.feed &&
            this.state.feed.items.map(
              (item, i) => {

                var match = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/.exec(item.content);

                if (match) 
                  var link_img = match[0].substring(0, match[0].length - 1)
                else 
                  link_img = "/img/avis.png"
                
                return (
                  <div key={i} className="col-sm-12 col-md-6 col-lg-4 col-xl-4 mb-5">
                    <div className="card card-news">
                      <img className="card-img-top img_news" src={link_img} alt="Card image" />
                      <div className="card-body d-flex flex-column">
                        <h6 className="card-title">{item.title}</h6>
                        <a href={item.link} target="_blank" className="btn btn-primary mt-auto">Leggi</a>
                      </div>
                    </div>
                  </div>
                )
              })
          }
        </div>
      </div>
    );
  }
}

export default News