import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import PropTypes from 'prop-types';
import React from 'react';
import Row from 'react-bootstrap/Row';

const propTypes = {
  fee: PropTypes.shape({
    id: PropTypes.string.isRequired,
    from: PropTypes.string.isRequired,
    to: PropTypes.string.isRequired,
    amount: PropTypes.number.isRequired
  }).isRequired,
  removeFee: PropTypes.func.isRequired
};

const FeeRow = props => {
  return (
    <Row className="justify-content-center align-items-baseline">
      <Col className="text-center" xs={3} md={2} xl={1}>
        {props.fee.from}
      </Col>
      <Col className="text-center" xs={3} md={2} xl={1}>
        {props.fee.to}
      </Col>
      <Col className="text-center" xs={3} md={2} xl={1}>
        {props.fee.amount.toFixed(2)}
      </Col>
      <Col xs={3} md={2} xl={1}>
        <Button
          className="mb-3"
          block="true"
          size="sm"
          variant="danger"
          onClick={() => props.removeFee(props.fee.id)}
        >
          Remove
        </Button>
      </Col>
    </Row>
  );
};

FeeRow.propTypes = propTypes;

export default FeeRow;
