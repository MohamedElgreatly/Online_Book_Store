import React from "react";
import './BookCard.css'
export default function BookCard(props) {

    const [count, setCount] = React.useState(0);
    const [Error, setError] = React.useState(null);
    function increase() {
        if (count === props.available - 1) {
            //NO stock
            setCount(old => {
                return old + 1;
            });
            setError(() => {
                return (<div className="Error">
                    No more items in stock !!
                </div>);
            });
        }
        else if (count < props.available) {
            setCount(old => {
                return old + 1;
            });
        }
        else {
            setError(() => {
                return (<div className="Error">
                    No more items in stock !!
                </div>);
            });
        }
        // if he presed  + then send to the backend the book id
     /*   async function Addbook() {
            let result = await fetch(`${environment.env}/Addbook`, {
                method: "POST",
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(
                {
                  customerid:location.state.customerid,
                  bookid: props.id
                }
              )
            });
            let res = await result.json();
        }
        Addbook();*/

    };
    function decrease() {
        setCount(old => {
            return old > 0 ? old - 1 : 0;
        })
        //  // if he presed  - then send to the backend the book id 
        /*async function Removebook() {
            let result = await fetch(`${environment.env}/Removebook`, {
                method: "POST",
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(
                {
                  customerid:location.state.customerid,
                  bookid: props.id
                }
              )
            });
            let res = await result.json();
        }
    Removebook();*/
    };

    return (
        <div className="Card">
 <div className="Card">
            <img src={props.img} alt={props.name} />
            <div className="description">
                     <h3>{props.name}</h3>
                    <h3 id="price">${props.price}</h3>
                    <div className="quantity">
                        <button id="inc" onClick={increase}>+</button>
                        <p>{count}</p>
                        <button id="dec" onClick={decrease}>-</button>
                </div>
            </div>
            {Error}
        </div>
        </div>
    );
}