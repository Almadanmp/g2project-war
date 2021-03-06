import React, {Component} from 'react';
import {Form, FormGroup, Input, Label} from "reactstrap";
import US003Redux from "./US003Redux";

class TypeAreaSelector extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      isLoaded: false,
      typeArea: '',
      value: ''
    };
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://localhost:8443/geoAreas/areaTypes', {
      headers: {
        'Authorization': token,
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then((json) => {
        this.setState({
          isLoaded: true,
          item: json,
        })
      })
      .catch(console.log)
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  render() {

    var {isLoaded, item} = this.state;
    if (!isLoaded) {
      return <div>Loading...</div>
    } else {
      if (!item.error) {
        return (
          <div>
            <Form action="" method="post">
              <FormGroup>
                <Label>Select area type</Label>
                <Input type="select" name="select" id="select" value={this.state.value} onChange={this.handleChange}>
                  <option value="0" onChange={this.handleChange}>Please select</option>
                  {item.map(items => (
                    <option value={items.name} key={items.name}>
                      Type: {items.name}
                    </option>
                  ))}
                </Input>
              </FormGroup>
            </Form>
            <US003Redux typeArea={this.props.typeArea}/>
          </div>
        );
      } else {
        return "ERROR: Non-authorized user."
      }
    }
  }
}

export default TypeAreaSelector;
