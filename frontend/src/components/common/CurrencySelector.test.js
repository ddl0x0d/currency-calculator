import AppContext from '../../AppContext';
import CurrencySelector from './CurrencySelector';
import { mount, shallow } from 'enzyme';
import React from 'react';

describe('<CurrencySelector />', () => {
  const handleChange = jest.fn().mockName('handleChange');
  const currencySelector = value => (
    <CurrencySelector
      id="currency"
      label="Currency"
      value={value}
      handleChange={handleChange}
    />
  );

  it('renders correctly without currencies', () => {
    const wrapper = mount(currencySelector(''));
    expect(wrapper).toMatchSnapshot();
  });

  it('renders correctly with currencies', () => {
    const component = (
      <AppContext.Provider value={{ currencies: ['EUR', 'GBP'] }}>
        {currencySelector('EUR')}
      </AppContext.Provider>
    );
    const wrapper = mount(component);
    expect(wrapper).toMatchSnapshot();
  });

  it('handles change', () => {
    const wrapper = shallow(currencySelector(''));
    wrapper.find('#currency').simulate('change', { target: { value: 'EUR' } });
    expect(handleChange).toHaveBeenCalledWith('EUR');
  });
});
