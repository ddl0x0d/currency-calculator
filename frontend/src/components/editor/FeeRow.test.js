import Col from 'react-bootstrap/Col';
import FeeRow from './FeeRow';
import React from 'react';
import { shallow } from 'enzyme';

describe('<FeeRow />', () => {
  const removeFee = jest.fn().mockName('removeFee');
  const fee = { id: '123', from: 'EUR', to: 'GBP', amount: 0 };
  const feeRow = () => <FeeRow fee={fee} removeFee={removeFee} />;

  it('renders correctly', () => {
    const wrapper = shallow(feeRow());
    expect(wrapper).toMatchSnapshot();
  });

  it('renders fee amount with 2 decimal places', () => {
    const wrapper = shallow(feeRow());
    const amount = wrapper.find(Col).at(2);
    expect(amount.text()).toBe('0.00');
  });

  it('removes fee', () => {
    const wrapper = shallow(feeRow());
    wrapper.find('Button').simulate('click');
    expect(removeFee).toHaveBeenCalledWith('123');
  });
});
