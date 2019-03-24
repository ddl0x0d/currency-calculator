import AmountInput from '../common/AmountInput';
import AppContext from '../../AppContext';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import CurrencySelector from '../common/CurrencySelector';
import Fees from '../../api/Fees';
import Form from 'react-bootstrap/Form';
import PropTypes from 'prop-types';
import React, { useContext, useState } from 'react';
import Row from 'react-bootstrap/Row';

const propTypes = {
  addFee: PropTypes.func.isRequired
};

const FeeForm = props => {
  const [from, setFrom] = useState('');
  const [to, setTo] = useState('');
  const [amount, setAmount] = useState(0);
  const { showErrors } = useContext(AppContext);

  const addFee = () => {
    Fees.add({ from, to, amount })
      .then(fee => {
        props.addFee(fee);
        showErrors([]);
      })
      .catch(errors => {
        showErrors(errors);
      });
  };

  return (
    <Row className="justify-content-center">
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
      <Col xs={3} md={2} xl={1}>
        <AmountInput
          id="amount"
          label="Fee"
          value={amount}
          step={0.01}
          handleChange={value => setAmount(value)}
        />
      </Col>
      <Col xs={3} md={2} xl={1}>
        <Form.Label className="mb-0">&nbsp;</Form.Label>
        <Form.Group>
          <Button
            id="add"
            block="true"
            disabled={!(from && to && amount) || from === to}
            size="sm"
            onClick={() => addFee()}
          >
            Add
          </Button>
        </Form.Group>
      </Col>
    </Row>
  );
};

FeeForm.propTypes = propTypes;

export default FeeForm;
