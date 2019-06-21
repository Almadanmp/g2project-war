import React, {Component} from 'react';
import {Button, Col, Container, Input, InputGroup, InputGroupAddon, InputGroupText, Row} from "reactstrap";

class UnderMaintenance extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (
      <div>
        <Container>
          <Row className="justify-content-start">
            <Col md="15">
              <h2 className="pt-12">The dev team!</h2>
              <p>Meet everyone involved in the making of the SmartHome Web app.</p></Col></Row>
          <Row>
            <Col>
              <img src="https://imgur.com/ICquRJa.png" width="40%" height="60%"/>
              <h3>André</h3>
              <p>Benfica</p></Col>
            <Col>
              <img src="https://imgur.com/MIORjL7.png" width="40%" height="60%"/>
              <h3>Cárina</h3>
              <p>Kitties</p></Col>
            <Col>
              <img src="https://imgur.com/a78ZEfT.png" width="40%" height="60%"/>
              <h3>Daniel</h3>
              <p>Sporting</p></Col>
          </Row>
          <Row>
            <Col>
              <img src="https://imgur.com/7yG7o0m.png" width="40%" height="60%"/>
              <h3>Daniela</h3>
              <p>Death</p></Col>
            <Col>
              <img src="https://imgur.com/O0UJCta.png" width="40%" height="60%"/>
              <h3>João</h3>
              <p>K-pop</p></Col>
            <Col>
              <img src="https://imgur.com/VzeQmTf.png" width="40%" height="60%"/>
              <h3>Maria</h3>
              <p>Communism</p></Col>
          </Row>
          <Row>
            <Col>
              <img src="https://imgur.com/5bkoNJE.png" width="40%" height="60%"/>
              <h3>Miguel</h3>
              <p>Porto</p></Col>
            <Col>
              <img src="https://imgur.com/ezHuUEn.png" width="40%" height="60%"/>
              <h3>Nuno</h3>
              <p>Dark</p></Col>
            <Col>
              <img src="https://imgur.com/GTWerXw.png" width="40%" height="60%"/>
              <h3>Teresa</h3>
              <p>Filipinos</p></Col>
          </Row>
        </Container>

      </div>
    );
  }
}

export default UnderMaintenance;
