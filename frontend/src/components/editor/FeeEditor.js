import AppContext from '../../AppContext';
import Fees from '../../api/Fees';
import FeeForm from './FeeForm';
import FeeRow from './FeeRow';
import FeeSeparator from './FeeSeparator';
import React, { useContext, useEffect, useState } from 'react';

export default function FeeEditor() {
  const [fees, setFees] = useState([]);
  const { showErrors } = useContext(AppContext);

  useEffect(() => {
    Fees.get()
      .then(fees => setFees(fees))
      .catch(errors => showErrors(errors));
  }, []);

  const addFee = fee => {
    setFees(fees.concat([fee]));
  };

  const removeFee = id => {
    Fees.remove(id)
      .then(() => setFees(fees.filter(fee => fee.id !== id)))
      .catch(errors => showErrors(errors));
  };

  return (
    <>
      <FeeForm addFee={addFee} />
      <FeeSeparator />
      {fees.map(fee => (
        <FeeRow key={fee.id} fee={fee} removeFee={removeFee} />
      ))}
    </>
  );
}
