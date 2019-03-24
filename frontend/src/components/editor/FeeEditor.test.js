import Fees from '../../api/Fees';
import FeeEditor from './FeeEditor';
import FeeRow from './FeeRow';
import { mount } from 'enzyme';
import React from 'react';

jest.mock('../../api/Fees');

describe('<FeeEditor />', () => {
  const fees = [
    { id: '1', from: 'EUR', to: 'GBP', amount: 0.01 },
    { id: '2', from: 'EUR', to: 'RUB', amount: 0.02 },
    { id: '3', from: 'EUR', to: 'USD', amount: 0.03 }
  ];
  Fees.get.mockName('Fees.get').mockResolvedValue(fees);

  it('gets fees', done => {
    const wrapper = mount(<FeeEditor />);

    setTimeout(() => {
      wrapper.update();

      const rows = wrapper.find(FeeRow);

      expect(Fees.get).toBeCalled();
      expect(rows).toHaveLength(3);
      done();
    });
  });

  it('removes fee', done => {
    const wrapper = mount(<FeeEditor />);

    setTimeout(() => {
      wrapper.update();

      Fees.remove.mockName('Fees.remove').mockResolvedValue({});

      wrapper
        .find('FeeRow')
        .at(1)
        .find('button')
        .simulate('click');

      setTimeout(() => {
        wrapper.update();

        const rows = wrapper.find(FeeRow);

        expect(rows).toHaveLength(2);
        done();
      });
    });
  });
});
