import AppContext from '../../AppContext';
import FeeForm from './FeeForm';
import Fees from '../../api/Fees';
import { mount, shallow } from 'enzyme';
import React from 'react';

jest.mock('../../api/Fees');

describe('<FeeForm />', () => {
  const addFee = jest.fn().mockName('addFee');
  const feeForm = () => <FeeForm addFee={addFee} />;

  it('renders correctly', () => {
    const wrapper = mount(feeForm());
    expect(wrapper).toMatchSnapshot();
  });

  describe('"Add" button', () => {
    const wrapper = shallow(feeForm());
    const addDisabled = () => wrapper.find('#add').prop('disabled');
    const fillForm = (from, to, amount) => {
      wrapper.find('#from').prop('handleChange')(from);
      wrapper.find('#to').prop('handleChange')(to);
      wrapper.find('#amount').prop('handleChange')(amount);
    };

    it('is disabled by default', () => {
      expect(addDisabled()).toBe(true);
    });

    it('is disabled when "From" and "To" are equal', () => {
      fillForm('EUR', 'EUR', 0.01);
      expect(addDisabled()).toBe(true);
    });

    it('is enabled', () => {
      fillForm('EUR', 'GBP', 0.01);
      expect(addDisabled()).toBe(false);
    });
  });

  describe('add fee', () => {
    beforeEach(() => {
      jest.clearAllMocks();
    });

    const showErrors = jest.fn().mockName('showErrors');
    const wrapper = mount(
      <AppContext.Provider value={{ currencies: [], showErrors }}>
        {feeForm()}
      </AppContext.Provider>
    );

    const change = (selector, value) => {
      const element = wrapper.find(selector);
      element.simulate('change', { target: { value } });
    };

    change('select#from', 'EUR');
    change('select#to', 'GBP');
    change('input#amount', 0.01);
    const feeRequest = { from: 'EUR', to: 'GBP', amount: 0.01 };

    it('succeeds', done => {
      const fee = { id: '123', ...feeRequest };
      Fees.add.mockName('Fees.add').mockResolvedValue(fee);

      wrapper.find('button#add').simulate('click');

      setTimeout(() => {
        expect(Fees.add).toBeCalledWith(feeRequest);
        expect(addFee).toBeCalledWith(fee);
        expect(showErrors).toBeCalledWith([]);
        done();
      });
    });

    it('fails', done => {
      const errors = [{ message: 'error' }];
      Fees.add.mockName('Fees.add').mockRejectedValue(errors);

      wrapper.find('button#add').simulate('click');

      setTimeout(() => {
        expect(Fees.add).toBeCalledWith(feeRequest);
        expect(addFee).not.toBeCalled();
        expect(showErrors).toBeCalledWith(errors);
        done();
      });
    });
  });
});
