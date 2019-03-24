import Col from 'react-bootstrap/Col';
import React from 'react';
import Row from 'react-bootstrap/Row';

export default function FeeSeparator() {
  return (
    <Row className="justify-content-center">
      <Col md={8} xl={4}>
        <hr style={{ borderTop: '3px solid rgba(0, 0, 0, 0.5)' }} />
      </Col>
    </Row>
  );
}
