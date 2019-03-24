import AmountInput from '../common/AmountInput';
import AppContext from '../../AppContext';
import Col from 'react-bootstrap/Col';
import CurrencySelector from '../common/CurrencySelector';
import Currencies from '../../api/Currencies';
import Row from 'react-bootstrap/Row';
import React, { useContext, useEffect, useState } from 'react';

export default function Calculator() {
  const [amount, setAmount] = useState(0);
  const [from, setFrom] = useState('');
  const [to, setTo] = useState('');
  const [conversion, setConversion] = useState('');
  const { showErrors } = useContext(AppContext);

  useEffect(() => {
    if (amount && amount > 0 && from && to && from !== to) {
      Currencies.convert({ amount, from, to })
        .then(conversion => {
          setConversion(`${conversion} ${to}`);
          showErrors([]);
        })
        .catch(errors => {
          setConversion('');
          showErrors(errors);
        });
    } else {
      setConversion('');
    }
  }, [amount, from, to]);

  return (
    <>
      <Row className="justify-content-center">
        <Col xs={3} md={2} xl={1}>
          <AmountInput
            id="amount"
            label="Amount"
            value={amount}
            handleChange={value => setAmount(value)}
          />
        </Col>
        <Col xs={3} md={2} xl={1}>
          <CurrencySelector
            id="from"
            label="From"
            value={from}
            handleChange={value => setFrom(value)}
          />
        </Col>
        <Col xs={3} md={2} xl={1}>
          <CurrencySelector
            id="to"
            label="To"
            value={to}
            handleChange={value => setTo(value)}
          />
        </Col>
      </Row>
      <Row className="mt-3">
        <Col>
          <h3 id="conversion" className="text-center font-weight-bolder">
            {conversion}
          </h3>
        </Col>
      </Row>
    </>
  );
}
