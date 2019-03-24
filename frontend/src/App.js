import AppContext from './AppContext';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Calculator from './components/calculator/Calculator';
import Container from 'react-bootstrap/Container';
import Currencies from './api/Currencies';
import Errors from './components/common/Errors';
import FeeEditor from './components/editor/FeeEditor';
import Navigation from './components/layout/Navigation';
import React, { useEffect, useState } from 'react';

export default function App() {
  const [currencies, setCurrencies] = useState([]);
  useEffect(() => {
    Currencies.get()
      .then(currencies => setCurrencies(currencies))
      .catch(errors => showErrors(errors));
  }, []);

  const [errors, setErrors] = useState([]);
  const showErrors = errors => {
    setErrors(errors);
  };

  return (
    <Router>
      <Navigation />
      <AppContext.Provider value={{ currencies, showErrors }}>
        <Container>
          <Errors messages={errors} />
          <Route path="/" exact component={FeeEditor} />
          <Route path="/editor" component={FeeEditor} />
          <Route path="/calculator" component={Calculator} />
        </Container>
      </AppContext.Provider>
    </Router>
  );
}
