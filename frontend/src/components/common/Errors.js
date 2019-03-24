import Alert from 'react-bootstrap/Alert';
import AppContext from '../../AppContext';
import Col from 'react-bootstrap/Col';
import PropTypes from 'prop-types';
import React, { useContext } from 'react';
import Row from 'react-bootstrap/Row';

const propTypes = {
  messages: PropTypes.array.isRequired
};

const Errors = props => {
  const context = useContext(AppContext);

  const hide = () => {
    context.showErrors([]);
  };

  return (
    <Row className="justify-content-center">
      <Col md={8} xl={4}>
        <Alert
          className="small"
          dismissible={true}
          show={props.messages.length > 0}
          onClose={hide}
          variant="danger"
        >
          {props.messages.map(error => (
            <div key={props.messages.indexOf(error)}>
              {error.field || ''} {error.message}
            </div>
          ))}
        </Alert>
      </Col>
    </Row>
  );
};

Errors.propTypes = propTypes;

export default Errors;
