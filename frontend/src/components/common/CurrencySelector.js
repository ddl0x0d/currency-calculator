import AppContext from '../../AppContext';
import Form from 'react-bootstrap/Form';
import PropTypes from 'prop-types';
import React, { useContext } from 'react';

const propTypes = {
  id: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  handleChange: PropTypes.func.isRequired
};

const CurrencySelector = props => {
  const { currencies } = useContext(AppContext);

  return (
    <Form.Group>
      <Form.Label className="small mb-0">{props.label}</Form.Label>
      <Form.Control
        as="select"
        id={props.id}
        size="sm"
        value={props.value}
        onChange={e => props.handleChange(e.target.value)}
      >
        <option key="" value="" />
        {currencies.map(currency => (
          <option key={currency} value={currency}>
            {currency}
          </option>
        ))}
      </Form.Control>
    </Form.Group>
  );
};

CurrencySelector.propTypes = propTypes;

export default CurrencySelector;
