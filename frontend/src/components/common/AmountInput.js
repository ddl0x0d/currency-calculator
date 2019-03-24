import Form from 'react-bootstrap/Form';
import PropTypes from 'prop-types';
import React from 'react';

const propTypes = {
  id: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  value: PropTypes.number.isRequired,
  step: PropTypes.number,
  handleChange: PropTypes.func.isRequired
};

const defaultProps = {
  step: 1
};

const AmountInput = props => {
  return (
    <Form.Group>
      <Form.Label className="small mb-0">{props.label}</Form.Label>
      <Form.Control
        id={props.id}
        type="number"
        size="sm"
        value={props.value}
        min={0}
        step={props.step}
        onChange={e => props.handleChange(parseFloat(e.target.value))}
      />
    </Form.Group>
  );
};

AmountInput.propTypes = propTypes;
AmountInput.defaultProps = defaultProps;

export default AmountInput;
