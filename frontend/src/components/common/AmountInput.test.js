import AmountInput from './AmountInput';
import { mount, shallow } from 'enzyme';
import React from 'react';

describe('<AmountInput />', () => {
  const handleChange = jest.fn().mockName('handleChange');
  const amountInput = () => (
    <AmountInput
      id="amount"
      label="Amount"
      value={0}
      handleChange={handleChange}
    />
  );

  it('renders correctly', () => {
    const wrapper = mount(amountInput());
    expect(wrapper).toMatchSnapshot();
  });

  it('handles change as number', () => {
    const wrapper = shallow(amountInput());
    wrapper.find('#amount').simulate('change', { target: { value: '42' } });
    expect(handleChange).toHaveBeenCalledWith(42);
  });
});
