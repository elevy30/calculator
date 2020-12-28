import React from "react";

class Form extends React.Component {


   constructor(props) {
     super(props);
     this.state = {
       equation: '',
       answer: ''
     };
   }

   mySubmitHandler = (event) => {
     var that = this;
     event.preventDefault();

//     let body={
//        equation: this.state.equation
//     };

     console.log(JSON.stringify(this.state.equation))

     const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.state.equation)
    };

     fetch('http://localhost:8081/equation/calc',requestOptions)
       .then(res => res.json())
       .then(data => {
         if(data){
           console.log('got data ' + data);
           that.setState({answer: data});
         }
     })
     .catch(console.log);

     console.log('22222222222222222')
   }

   myChangeHandler = (event) => {
     let name = event.target.name;
     let value = event.target.value;
     this.setState({[name]: value});
   }

   render() {
     return (
       <form onSubmit={this.mySubmitHandler}>
       <h1 style={{color: "red"}}>Hello</h1>

       <p>Enter your Equation:</p>
       <input
         type='text'
         name='equation'
         onChange={this.myChangeHandler}
       />

       <p>Equation={this.state.equation}</p>
       <input type='submit' />
       <br/>

       <p style={{color: "blue", bold: "true"}}>Answer: {this.state.answer}</p>

       </form>
     );
   }
}

export default Form;