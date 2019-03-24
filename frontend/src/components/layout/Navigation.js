import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { NavLink } from 'react-router-dom';
import React from 'react';

export default function Navigation() {
  return (
    <Navbar bg="dark" variant="dark" className="mb-3" expand="sm">
      <Navbar.Brand>
        <Nav.Link as={NavLink} to="/" className="p-0 text-white">
          Currency Calculator
        </Nav.Link>
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="navigation" />
      <Navbar.Collapse id="navigation">
        <Nav>
          <Nav.Item>
            <Nav.Link as={NavLink} to="/editor">
              Fee Editor
            </Nav.Link>
          </Nav.Item>
          <Nav.Item>
            <Nav.Link as={NavLink} to="/calculator">
              Calculator
            </Nav.Link>
          </Nav.Item>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
}
